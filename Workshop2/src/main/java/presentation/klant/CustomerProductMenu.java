package main.java.presentation.klant;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.CartController;
import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.model.ProductCategory;
import main.java.infrastructure.ColorConsole;
import main.java.model.Product;
import main.java.presentation.DisplayCart;
import main.java.presentation.DisplayProducts;
import main.java.presentation.MainMenu;

@Component
public class CustomerProductMenu implements DisplayCart, DisplayProducts {
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
	private CustomerAddProductMenu customerAddProductMenu;

	private Cart userCart;
        private List<Product> products;
        
                private ProductCategory category;
        
	public void runMenu() {
		// met de db checken of er iets in de opgeslagen cart van de user zit
		long userId = mainController.getCurrentUser().getId();
		userCart = cartController.getCart(userId);
		// cart inhoud laten zien
		displayCart(console,userCart);
                List<Product> filteredProducts = new ArrayList<>();
                
		console.println(Formatter.LINE
				+ "\n Uit welke categorie wilt u een product toevoegen?"
				+ "\n"+Formatter.LINE
				+ "\n 1: Alle kazen"
				+ "\n 2: Medium tot Harde kazen" 
				+ "\n 3: Zachte Schimmel kazen" 
				+ "\n 4: Blauwe kazen"
				+ "\n 5: Room kazen"
                                + "\n 6: Geiten kazen\n", Color.CYAN);
		console.println(" 9: Naar Kassa / Winkelwagen wijzigen"
				+"\n 0: uitloggen", Color.PINK);
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
				console.println("Alle Kazen", Color.ORANGE);
                                category = ProductCategory.ALL;
				break;
			case "2":
				console.println("Medium en Harde Kazen", Color.ORANGE);
				category = ProductCategory.MEDIUM_HARD;
				break;
			case "3":
				console.println("Zachte Schimmel Kazen", Color.ORANGE);
				category = ProductCategory.SOFT_MOLD;
				break;
			case "4":
				console.println("Blauwe Kazen", Color.ORANGE);
				category = ProductCategory.BLUE;
				break;
			case "5":
				console.println("Geiten Kazen", Color.ORANGE);
				category = ProductCategory.GOAT;
				break;
			case "9":
				console.println("Naar Kassa / Winkelwagen wijzigen", Color.ORANGE);
				if(userCart.getSubOrders().isEmpty()){
					console.println("Uw winkelwagen is nog leeg", Color.magenta);
					validResponse = false;
					break;
				}
				cartController.setCurrentCart(userCart); 
				cartController.runMainCartMenu();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);
        products = productController.getAllProducts();
        LinkedHashMap<Integer, Long> displayAndProductIds = new LinkedHashMap<>();
        int i = 1;
        for(Product prod: products){
            if(category ==ProductCategory.ALL || prod.getCategory()==category) {
                filteredProducts.add(prod);
                // Keep a list of displayId + productId combination
                displayAndProductIds.put(i,prod.getId());                
            }
            i++;
	}        
	displayProducts(console, filteredProducts);
        customerAddProductMenu.setDisplayAndProductIds(displayAndProductIds);
        customerAddProductMenu.runMenu();     
	}
}
