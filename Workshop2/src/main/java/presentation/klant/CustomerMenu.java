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
//public class MainCustomerMenu{
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
        CustomerOrderMenu customerOrderMenu;
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
		console.println("\n 0: uitloggen", Color.PINK);
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
				cartController.runMainCartMenu();
				break;
			case "3":
				console.println("Bevestig/Plaats bestelling", Color.ORANGE);
				cartController.setCurrentCart(userCart);
                                customerOrderMenu.runEmployeeProductMenu();
				
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
	
	public void displayProducts(ProductCategory  filter){
		
	}
	
	
	
	

//	public void displayCart() {
//		BigDecimal totaal = BigDecimal.ZERO;
//		// header van tabel
//		// id in deze lijst is het getalletje in de lijst 1,2,3...,n'de subOrder
//		console.println(Formatter.LINE, Color.YELLOW);
//		console.println("  -- Uw Winkel Wagen --", Color.GREEN);
//		console.println(Formatter.LINE, Color.YELLOW);
//		
//
//		// loop om alle suborders te printen en totaal bedrag uit te rekenen
//		int[] padnums = { 4,4,25, 10, 10, 12}; 
//		int[] allignLR = { 1,1, 0, 1, 1, 1}; 
//		console.println(Formatter.padString(padnums,allignLR,"Id","  ",
//				"Product         -","Aantal", "-  Prijs", "- Subtotaal"),Color.YELLOW);
//		
//		
//		List<CartSubOrder> subOrders = userCart.getSubOrders();
//		Integer i = 1;
//		for (CartSubOrder sub : subOrders) {
//			
//			String product = sub.getProduct().getName();
//			String aantal = (""+sub.getQuantity());
//			String prijs = sub.getProduct().getPrice().toString();
//			String subtotaal = sub.getTotalPrice().toString();
//			console.println(Formatter.padString(padnums,allignLR,i.toString(),"  ", 
//					product, aantal, prijs, subtotaal),Color.YELLOW);
//			totaal = totaal.add(sub.getTotalPrice());
//			i++;
//		}
//		
//		totaal.setScale(2);  // aantal decimalen instellen
//		
//		// Footer van tabel
//		console.println("                                             Totaal:  " + totaal, Color.GREEN);
//		console.println(Formatter.LINE, Color.YELLOW);
//
//	}
	


}