package main.java.presentation;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.CartController;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;

@Component
public class MainCartMenu implements DisplayCart{
	
	@Autowired
	private ColorConsole console;
	@Autowired
	private CartController cartController;
	private Cart userCart;
	
	public void runMenu() {

		userCart = cartController.getCurrentCart();
		// cart inhoud laten zien
		displayCart(console,userCart);
		int size = userCart.getSubOrders().size();

		console.println(Formatter.LINE
				+ "\n 1 - "+size+":  Selecteer een order regel om aan te passen"
				+ "\n 0:      Terug naar bestel menu"
				+ "\n[enter]: Toets enter om door te gaan naar kassa\n"  // "" = enter
				+ Formatter.LINE,Color.CYAN);
		boolean validResponse;
		do {
			validResponse = true;
			int num=0;
			String response = console.printResponse("Maak uw keuze: \n", "", Color.CYAN);
				
			
			if(response == ""){
				console.println("Door naar Kassa", Color.ORANGE);
				validResponse = true;
			}else if(response == "0"){
				console.println("Terug naar bestel menu", Color.ORANGE);
				validResponse = true;
			}else 
				try{
				num = Integer.parseInt(response);
				}catch(Exception e){
					console.println("Ongeldige invoer", Color.RED);
					validResponse = false;
				}
			if(0<num&&num<=size){
				validResponse = true;
				console.println("Order regel "+num+" aanpassen", Color.ORANGE);
				EditSubOrder(num-1)	; // sub orders are stored in a list (0 to size-1)			
			}else{
				console.println("Ongeldige invoer", Color.RED);
				validResponse = false;
			}
			
		} while (!validResponse);
		
	}

	private void EditSubOrder(int i) {
		// TODO Auto-generated method stub
		
	}

}
