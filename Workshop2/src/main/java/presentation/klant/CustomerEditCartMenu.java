/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation.klant;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.CartController;
import main.java.controller.CartSubOrderController;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.infrastructure.ColorConsole;
import main.java.presentation.DisplayCart;


@Component
public class CustomerEditCartMenu implements DisplayCart{
    
	@Autowired
	private ColorConsole console;
	@Autowired
	private CartController cartController;
	private Cart userCart;
        @Autowired
        private CustomerCheckOutMenu customerCheckOutMenu;
        @Autowired
        private CustomerMenu customerMenu;
        @Autowired
        CustomerProductMenu customerProductMenu;
        @Autowired
        private CartSubOrderController cartSubOrderController;
	
	public void runMenu() {

		userCart = cartController.getCurrentCart();
		// cart inhoud laten zien
		displayCart(console,userCart);
		int size = userCart.getSubOrders().size();

		console.println(Formatter.LINE
				+ "\n 1 - "+(size+1)+":  Selecteer een order regel om aan te passen"
				+ "\n 0:      Terug naar hoofdmenu"
				+ "\n[x]: Toets x om door te gaan naar kassa\n"  // "" = enter
				+ Formatter.LINE,Color.CYAN);
		
                // Deze logica moet nog nagekeken worden, hij print nu bijv altijd ongeldige invoer bij 'x' 
                boolean validResponse;
		do {
			validResponse = true;
			int num=-1;
			String response = console.printResponse("Maak uw keuze: \n", "", Color.CYAN);
				try{
				num = Integer.parseInt(response);
				}catch(Exception e){
					console.println("Ongeldige invoer", Color.RED);
					validResponse = false;
				}
			
			if(response.equals("x")) {
				console.println("Door naar kassa", Color.ORANGE);
				validResponse = true;
                                customerCheckOutMenu.runMenu();
                                
			}else if(num == 0){
				console.println("Terug naar bestel menu", Color.ORANGE);
				validResponse = true;
                                customerMenu.runMenu();
                                
			}else 
				
			if(0<num&&num<=size){
				validResponse = true;
				console.println("Order regel "+num+" aanpassen", Color.ORANGE);
                                Long longNum = Long.parseLong("" + num);
				cartSubOrderController.deleteCartSubOrder(longNum);
                                customerProductMenu.runMenu();
			}else{
				console.println("Ongeldige invoer", Color.RED);
				validResponse = false;
			}
			
		} while (!validResponse);
		
	}
}
