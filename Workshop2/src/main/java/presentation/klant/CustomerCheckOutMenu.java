/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation.klant;

/**
 *
 * @author Frank
 */

import java.awt.Color;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.CartController;
import main.java.presentation.DisplayCart;
import main.java.controller.MainController;
import main.java.controller.OrderController;
import main.java.controller.UserController;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;
import main.java.model.Adress;
import main.java.model.Cart;
import main.java.model.CartSubOrder;
import main.java.model.FinalSubOrder;
import main.java.model.Order;
import main.java.model.UserAccount;
import main.java.presentation.CreateMenus.CreateAdressMenu;
import main.java.presentation.DisplayAdress;
import main.java.presentation.MainMenu;

@Component
public class CustomerCheckOutMenu implements DisplayCart, DisplayAdress {
    
    
    @Autowired
        private MainMenu mainMenu;
	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private CartController cartController;
        @Autowired
        OrderController orderController;
        @Autowired
        UserController userController;
        @Autowired
        CustomerProductMenu customerProductMenu;
        @Autowired
        CustomerEditCartMenu customerEditCartMenu;
        @Autowired
        CustomerOrderHistoryMenu customerOrderHistoryMenu;
        @Autowired
        CustomerCheckOutMenu customerOrderMenu;
        @Autowired
        CustomerProfileMenu customerProfileMenu;
        @Autowired
        CreateAdressMenu createAdressMenu;
        @Autowired
        CustomerMenu customerMenu;


	private Cart userCart;
        public void runMenu(){
		// met de db checken of er iets in de opgeslagen cart van de user zit
                UserAccount user = mainController.getCurrentUser();
		long userId = user.getId();
		userCart = cartController.getCart(userId);
		// cart inhoud laten zien
		displayCart(console,userCart);

                //dit is de profielpagina. hier kun je een adres maken en wordt
                                // adres ook getoond
                // alleen adres ophalen als de user een adres heeft, anders aanmaken
                displayAdress(console,user.getBillingAdress(),user.getEmail());
                                //customerProfileMenu.showAdressDetails();
		console.println(Formatter.LINE +
				"Zijn deze gegevens correct?[y/n]", 
                                Color.CYAN);
                
		console.println("\n 0: uitloggen", Color.PINK);
		console.println(Formatter.LINE+"\n", Color.CYAN);
		
		boolean validResponse;
		
		do {
			validResponse = true;
			String response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN).toLowerCase();
                        

			switch (response) {
			case "0":
				console.println("Uitloggen. Uw Winkelwagen wordt opgeslagen", Color.RED);
				cartController.updateCart(userCart);
				mainMenu.runStartMenu();
				break;
			case "y":
				console.println("Topper!", Color.ORANGE);
                                
                                //to do (Ik begrijp niet welke klassen verantwoordelijk zijn
                                //voor cart-->order en hoe het domein dat hendelt):
                                //1)alle suborders --> finalsuborders
                                //2)cart --> order
                                Order order = new Order();
                                order.setUser(user);
                                for (CartSubOrder cso : userCart.getSubOrders()) {
                                    FinalSubOrder fso = new FinalSubOrder(cso);
                                    order.addSubOrder(fso);
                                }
                                // TO DO: display and confirm/edit Adress
                                // Gaat er nu automatisch vanuit dat userBillingAdress == orderDeliveryAdress
                                order.setDeliveryAdress(user.getBillingAdress());
                                order.calculateTotalPrice();
                                order.setSaledate(LocalDate.now());                                
                                orderController.createOrder(order);
                                console.println("Uw bestelling is geplaatst, gefeliciteerd!", Color.RED);
                                customerMenu.runMenu();
				break;
			case "n":
				console.println("Ooh,, jammer", Color.ORANGE);
                                Adress newAdress = editAdress(console, user.getBillingAdress());
                                System.out.println(newAdress.toString());
                                user.setBillingAdress(newAdress);
                                System.out.println(user.getBillingAdress().toString());
                                userController.updateUserAccount(user);
				customerEditCartMenu.runMenu();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);
	
		
	}
	
	}
