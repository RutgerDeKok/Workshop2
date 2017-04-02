package main.java.presentation.klant;

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

@Component
public class CustomerMenu implements DisplayCart{
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
    CustomerEditCartMenu customerCartMenu;
    @Autowired
    CustomerOrderHistoryMenu customerOrderHistoryMenu;
    @Autowired
    CustomerCheckOutMenu customerCheckOutMenu;
    @Autowired
    CustomerProfileMenu customerProfileMenu;
    private Cart userCart;

    public void runMenu() {
        // met de db checken of er iets in de opgeslagen cart van de user zit
        long userId = mainController.getCurrentUser().getId();
        userCart = cartController.getCart(userId);
        // cart inhoud laten zien
        displayCart(console,userCart);
        console.println(Formatter.LINE
                        + "\nSelecteer een optie"
                        + "\n"+Formatter.LINE
                        + "\n 1: Voeg een product toe aan winkelwagen" 
                        + "\n 2: Wijzig winkelwagen" 
                        + "\n 3: Naar kassa"
                        + "\n 4: Overzicht bestellingen"     
                        + "\n 5: Mijn Profiel", Color.CYAN);
        console.println("\n 0: Uitloggen", Color.PINK);
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
                    console.println("Voeg een product toe aan winkelwagen", Color.ORANGE);
                    customerProductMenu.runMenu();
                    break;
            case "2":
                    console.println("Wijzig Winkelwagen", Color.ORANGE);
                    if(userCart.getSubOrders().size()==0){
                            console.println("Uw winkelwagen is nog leeg", Color.magenta);
                            validResponse = false;
                            break;
                    }
                    cartController.setCurrentCart(userCart); 
                    cartController.runMainCartMenu(); // = editCartMenu
                    break;
            case "3":
                    console.println("Naar kassa", Color.ORANGE);
                    cartController.setCurrentCart(userCart);
                    customerCheckOutMenu.runMenu();				
                    break;
            case "4":
                    console.println("Overzicht bestellingen", Color.ORANGE);
                    customerOrderHistoryMenu.runMenu();			
                    break;
            case "5":
                    console.println("Mijn Profiel", Color.ORANGE);
                    customerProfileMenu.runMenu();
                    break;
            default:
                    console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
                    validResponse = false;
                    break;
            }
        } while (!validResponse);
    }
}
