/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation.klant;

import java.awt.Color;
import main.java.controller.CartController;
import main.java.controller.CartSubOrderController;
import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.model.CartSubOrder;
import main.java.presentation.DisplayCart;
import main.java.presentation.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nadkine
 */
@Component
public class CustomerAddProductMenu implements DisplayCart {
  
   @Autowired
        private MainMenu mainMenu;
	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private CartController cartController;
        @Autowired
	private ProductController productController;
        @Autowired
        CartSubOrderController cartSubOrderController;
        @Autowired
	private CustomerAddProductMenu customerAddProductMenu;
        @Autowired
        private CustomerMenu customerMenu;
        Cart userCart;
        CartSubOrder cartSubOrder;
                
                
    public void runMenu(){
      // met de db checken of er iets in de opgeslagen cart van de user zit
		long userId = mainController.getCurrentUser().getId();
		userCart = cartController.getCart(userId);
		// cart inhoud laten zien
		displayCart(console,userCart);
                
                //voeg kaas toe
                String productId = console.printResponse(Formatter.LINE
				+ "\nWat is het id van het product wat u toe wil voegen???"
				+ "\n"+Formatter.LINE, "1", Color.CYAN);
                
                //voeg amount toe
                String amount = console.printResponse(Formatter.LINE
				+ "\nHoeveel wilt u daarvan???"
				+ "\n"+Formatter.LINE, "1", Color.CYAN);
                
                //voeg suborder en cart toe aan database.. nog niet met elkaar verbonden
                cartSubOrder.setProduct(productController.getProduct(Long.getLong("" + productId)));
                cartSubOrder.setQuantity(Integer.parseInt(amount));
                userCart.addSubOrder(cartSubOrder);
                cartSubOrderController.createCartSubOrder(cartSubOrder);
                cartController.updateCart(userCart);
                
                console.println(Formatter.LINE
				+ "Dit was het alweer,, we gaan nu terug naar Klantenmenu" , Color.CYAN);
		console.println(Formatter.LINE+"\n", Color.CYAN);
                
                customerMenu.runMenu();
    }
}