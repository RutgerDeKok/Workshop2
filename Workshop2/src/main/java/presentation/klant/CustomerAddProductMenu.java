/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation.klant;

import java.awt.Color;
import java.util.LinkedHashMap;
import main.java.controller.CartController;
import main.java.controller.CartSubOrderController;
import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.model.CartSubOrder;
import main.java.model.Product;
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

    public void setDisplayAndProductIds(LinkedHashMap<Integer, Long> displayAndProductIds) {
        this.displayAndProductIds = displayAndProductIds;
    }
  
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
        private LinkedHashMap<Integer, Long> displayAndProductIds;
        
    
    public String checkInput(String prompt, String cancelKey) {
        return console.printResponse(prompt + "\nVoer " + cancelKey + " in om te annuleren.", "", Color.CYAN);
    }    
                
    public void runMenu(){
      // met de db checken of er iets in de opgeslagen cart van de user zit
		long userId = mainController.getCurrentUser().getId();
		userCart = cartController.getCart(userId);
		// cart inhoud laten zien
		//displayCart(console,userCart);
                
                //voeg kaas toe
                //String response = checkInput("Wat is het id van het product wat u toe wilt voegen?", "x");
                String productId = console.printResponse(Formatter.LINE
				+ "\nWat is het id van het product wat u toe wil voegen???"
				+ "\nVoor 'x' in om te annuleren"
                                        + "\n"+Formatter.LINE, "1", Color.CYAN);
                
                //voeg amount toe
                String amount = console.printResponse(Formatter.LINE
				+ "\nHoeveel wilt u daarvan???"
				+ "\n"+Formatter.LINE, "1", Color.CYAN);
                
                //voeg suborder en cart toe aan database.. nog niet met elkaar verbonden
                int id = Integer.parseInt(productId);                
                long prodId = displayAndProductIds.get(id);
                //Product p = userCart.getSubOrders().get(id).getProduct();
                Product p = productController.getProduct(prodId);
                CartSubOrder cartSubOrder = new CartSubOrder();
                cartSubOrder.setProduct(p);                        
                cartSubOrder.setQuantity(Integer.parseInt(amount));
                // subOrder kan nu nog niet goed opgeslagen worden
                cartSubOrder.setSubTotal(p.getPrice(), Integer.parseInt(amount));
                cartSubOrderController.createCartSubOrder(cartSubOrder);
                userCart.addSubOrder(cartSubOrder);                
                cartController.updateCart(userCart);
                
                
                console.println(Formatter.LINE
				+ "Dit was het alweer,, we gaan nu terug naar Klantenmenu" , Color.CYAN);
		console.println(Formatter.LINE+"\n", Color.CYAN);
                
                customerMenu.runMenu();
    }
}