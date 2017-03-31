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
import main.java.controller.CartController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.CartController;
import main.java.controller.MainController;
import main.java.controller.UserController;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.model.CartSubOrder;
import main.java.model.ProductCategory;
import main.java.infrastructure.ColorConsole;
import main.java.presentation.DisplayCart;
import main.java.presentation.MainMenu;
import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.model.ProductCategory;
import main.java.presentation.MainMenu;
import main.java.presentation.employee.MainEmployeeMenu;

@Component
public class CustomerCheckOutMenu implements DisplayCart{
    
    
    @Autowired
        private MainMenu mainMenu;
	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private CartController cartController;
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

	private Cart userCart;
        public void runMenu() {
		// met de db checken of er iets in de opgeslagen cart van de user zit
		long userId = mainController.getCurrentUser().getId();
		userCart = cartController.getCart(userId);
		// cart inhoud laten zien
		displayCart(console,userCart);

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
                                // Voor Tjeerd!
                                //print address
                                //klopt dit??
                                console.println(Formatter.LINE +
				"Zijn deze gegevens correct?[y/n]", 
                                Color.CYAN);
                                //voeg bestelling toe aan final suborder
                                //cart leegmaken
                                //terug naar hoofdpagina
				break;
			case "n":
				console.println("Ooh,, jammer", Color.ORANGE);
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
