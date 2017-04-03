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
import main.java.controller.CustomerMenuController;
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
    CustomerMenuController controller;
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
        // alleen adres ophalen als de cart een adres heeft, anders aanmaken
        displayAdress(console, userCart.getDeliveryAdress(), user.getEmail());
                        //customerProfileMenu.showAdressDetails();
        console.println(Formatter.LINE +
                        "Wilt u de order naar dit adres versturen?[y/n]", 
                        Color.CYAN);

        console.println("\n 0: uitloggen", Color.PINK);
        console.println(Formatter.LINE+"\n", Color.CYAN);

        boolean validResponse;

        do {
            validResponse = true;
            String response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN).toLowerCase();
            switch (response) {
                case "0":
                    console.println("Uitloggen. Uw winkelwagen wordt opgeslagen", Color.RED);
                    cartController.updateCart(userCart);
                    mainMenu.runStartMenu();
                    break;
                case "y":
                    console.println("Topper!", Color.ORANGE);
                    // Wel of niet eerst nog nar editCart om winkelwagen aan te kunnen passen?                    
                    controller.completeOrder(user);
                    //to do (Ik begrijp niet welke klassen verantwoordelijk zijn
                    //voor cart-->order en hoe het domein dat hendelt):
                    //1)alle suborders --> finalsuborders
                    //2)cart --> order   
                    
                    
                    console.println("U keert nu terug naar het hoofdmenu.", Color.GREEN);
                    customerMenu.runMenu();
                    break;
                case "n":
                    // HIER controleren of Cart wel een adres heeft
                    console.print("Ooh.. jammer.", Color.ORANGE);
                    displayAdress(console,user.getBillingAdress(),user.getEmail());
                    String input = console.printResponse("Wilt u de order bezorgen naar uw factuuradres?", "n", Color.yellow);
                    if (input.equalsIgnoreCase("y")) {
                        controller.copyAdress(userCart.getDeliveryAdress(), user.getBillingAdress());
                        displayAdress(console, userCart.getDeliveryAdress(), user.getEmail());
//userCart.setDeliveryAdress(user.getBillingAdress());
                        //cartController.updateCart(userCart);
                        //.setCurrentCart(userCart);
                        userController.updateUserAccount(user);
                        customerEditCartMenu.runMenu();
                        break;
                    } else if (input.equalsIgnoreCase("n")) {
                        console.println("Hieronder kunt u een nieuw afleveradres aanmaken.", Color.YELLOW);
                        //controller.copyAdress(userCart.getDeliveryAdress(), editAdress(console, userCart.getDeliveryAdress()));
                        editAdress(console, userCart.getDeliveryAdress());
                        displayAdress(console, userCart.getDeliveryAdress(), user.getEmail());
                        //userCart.setDeliveryAdress(newAdress);
                        //userCart.setDeliveryAdress(editAdress(console, userCart.getDeliveryAdress());
                        //cartController.updateCart(userCart);
                        //cartController.setCurrentCart(userCart);
                        userController.updateUserAccount(user);
                        customerEditCartMenu.runMenu();
                    } else {
                        console.print("Ongeldige invoer, probeer opnieuw.", Color.RED);
                        runMenu();
                        break;
                    }
                default:
                    console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
                    validResponse = false;
                    break;
            }
        } while (!validResponse);
    }
}
