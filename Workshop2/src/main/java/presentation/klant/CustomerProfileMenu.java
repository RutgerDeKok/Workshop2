/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation.klant;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.controller.AdressController;
import main.java.controller.CartController;
import main.java.controller.CartSubOrderController;
import main.java.controller.MainController;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.presentation.CreateMenus.CreateAdressMenu;
import main.java.presentation.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import main.java.model.Adress;
import main.java.model.ProductCategory;
import main.java.model.UserAccount;

/**
 *
 * @author Nadkine
 */

@Component
public class CustomerProfileMenu {
    
    
    
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
        @Autowired
        CreateAdressMenu createAdressMenu;
        @Autowired
        MainController mainController;
        @Autowired
        AdressController adressController;
        @Autowired
        MainMenu mainMenu;
        UserAccount user;
        
    public void runMenu(){
    
        user = mainController.getCurrentUser();
        showUserDetails();
        
    if (user.getBillingAdress()==null){
        console.println(Formatter.LINE
				+ "\nU heeft nog geen emailadress aangemaakt"
                                + "\nWilt u er nu een maken? [Y/N]"
				+ "\n", Color.CYAN);
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
				console.println("U voegt een adress toe", Color.ORANGE);
                                //hij slaat combinatie user-adres nog niet op
                                user.setBillingAdress(createAdressMenu.createAdress());
				break;
			case "n":
				console.println("U kiest ervoor om geen adres door te geven. "
                                        + "\nHoudt er rekening mee dat u uw bestelling "
                                        + "niet af kunt ronden zonder adres  "
                                        + "\nU wordt nu teruggestuurd naar het klantenmenu", Color.ORANGE);
                                 try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CustomerMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }
				customerMenu.runMenu();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
                        }	
		} while (!validResponse);  
            }
        
            showUserDetails();
            showAdressDetails();
            
            console.println(Formatter.LINE
				+ "\n Kies een optie?"
				+ "\n"+Formatter.LINE
				+ "\n 1: Wijzig gegevens"
                                + "\n 2: Terug naar klantenmenu"
                                + "\n 0: uitloggen", Color.CYAN);
		
		console.println(Formatter.LINE+"\n", Color.CYAN);
                
                boolean validResponse;
                
            do {
			validResponse = true;
			String response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);

			switch (response) {
			case "0":
				console.println("Uitloggen. Uw Winkelwagen wordt opgeslagen", Color.RED);
				cartController.updateCart(userCart);
				mainMenu.runStartMenu();
				break;
			case "1":
				console.println("U gaat uw gegevens wijzigen", Color.ORANGE);
                                adressController.updateAdress(user.getId(), createAdressMenu.createAdress());
                                runMenu();
				break;
                        case "2":
				console.println("U gaat terug naar het hoofdnmenu", Color.ORANGE);
                                customerMenu.runMenu();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);
            customerMenu.runMenu();
            
    
    }
    
    
    
    public void showUserDetails(){
        user = mainController.getCurrentUser();
        String email = user.getEmail();
        String userType = user.getUserType().toString();
        long id = user.getId();
        Adress adress = user.getBillingAdress();
        
        console.println(Formatter.LINE
				+ "\nUw Gegevens:" 
				+ "\n"+Formatter.LINE
				+ "\nEmail: " + email 
				+ "\nSoort gebruiker: " + userType 
				+ "\nuw gebruikersId: " +  id  
				, Color.CYAN);
		console.println(Formatter.LINE+"\n", Color.CYAN);
    }
    
    public void showAdressDetails(){
        user  = mainController.getCurrentUser();
        Adress adress = adressController.getAdress(Long.parseLong("" + user.getId()));
        String city = adress.getCity();
        String familyName = adress.getFamilyName();
        String firstName = adress.getFirstName();
        String insertion = adress.getInsertion();
        String numAddition = adress.getNumAddition();
        String street = adress.getStreet();
        String zip = adress.getZipCode();
        int number = adress.getNumber();
    
        console.println(Formatter.LINE
				+ "\nUw Adresgegevens:" 
				+ "\n"+Formatter.LINE
				+ "\nVoornaam: " + firstName
				+ "\nTussenvoegsel: " + insertion 
				+ "\nAchternaam: " +  familyName  
                                + "\nStad: " + city
				+ "\nPostcode: " + zip 
				+ "\nStraat: " +  street 
                                + "\nHuisnummer: " + number 
				+ "\nhuisvoegsel: " + numAddition 
				, Color.CYAN);
		console.println(Formatter.LINE+"\n", Color.CYAN);
    }
    
    
    
}
