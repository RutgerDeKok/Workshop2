package main.java.presentation.klant;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.CustomerMenuController;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.infrastructure.ColorConsole;

@Component
public class CustomerMenu {
    
    @Autowired
    CustomerMenuController controller;
    @Autowired
    ColorConsole console;
    Cart userCart;

    public void runMenu() {
        userCart = controller.getCartfromDB();
        controller.displayCart(userCart);
        console.println(Formatter.LINE
                        + "\nSelecteer een optie:"
                        + "\n"+Formatter.LINE
                        + "\n 1: Voeg een product toe aan winkelwagen." 
                        + "\n 2: Wijzig winkelwagen." 
                        + "\n 3: Naar kassa."
                        + "\n 4: Overzicht bestellingen."     
                        + "\n 5: Mijn profiel.", Color.CYAN);
        console.println("\n 0: Uitloggen.", Color.PINK);
        console.println(Formatter.LINE+"\n", Color.CYAN);
        boolean validResponse;
        do {
            validResponse = true;
            String response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
            switch (response) {
                case "0":
                        console.println("Uitloggen. Uw Winkelwagen wordt opgeslagen.", Color.RED);
                        controller.persistCart(userCart);
                        controller.runMainMenu();
                        break;
                case "1":
                        console.println("Voeg een product toe aan winkelwagen.", Color.ORANGE);
                        controller.runProductMenu();
                        break;
                case "2":
                        console.println("Wijzig winkelwagen.", Color.ORANGE);
                        if(userCart.getSubOrders().isEmpty()) {
                            console.println("Uw winkelwagen is nog leeg.", Color.magenta);
                            validResponse = false;
                            break;
                        }
//                      controller.saveCart(userCart);
                        controller.runEditCartMenu();
                        break;
                case "3":
                        console.println("Naar kassa", Color.ORANGE);
//                       controller.saveCart(userCart);
                        controller.runCheckOutMenu();			
                        break;
                case "4":
                        console.println("Overzicht bestellingen", Color.ORANGE);
                        controller.runOrderHistoryMenu();
                        break;
                case "5":
                        console.println("Mijn Profiel", Color.ORANGE);
                        controller.runProfileMenu();
                        break;
                default:
                        console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
                        validResponse = false;
                        break;
                }
        } while (!validResponse);
    }
}
