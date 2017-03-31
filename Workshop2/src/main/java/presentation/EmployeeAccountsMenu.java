
package main.java.presentation;



import main.java.infrastructure.ColorConsole;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.infrastructure.Kleur;
import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.controller.UserController;
import main.java.infrastructure.Formatter;
import main.java.model.Product;
import main.java.model.ProductCategory;
import main.java.model.UserAccount;
import main.java.model.UserType;

@Component
public class EmployeeAccountsMenu implements DisplayUsers{

	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private UserController userController;
	@Autowired
	private MainEmployeeMenu mainEmpMenu;
	
	private char eAc = '\u00E9'; // e accent
	private List<UserAccount> users;
	private List<UserAccount> filteredUsers; // filter by UserType
	private UserType filterType = UserType.ALL;
	private boolean comingFromOutsideMenu = true;
	

	public void runMenu() {
		String response;
		boolean validResponse;
		
		if(comingFromOutsideMenu){
		users = userController.getAllUsers();  
		filteredUsers = new ArrayList<>(users);
		}
		comingFromOutsideMenu = false;
		displayUsers(console, filteredUsers);
		
		console.println("\n"+Formatter.LINE, Kleur.CART);
		console.println("Product Menu", Kleur.RED_ORANGE);
		console.println(Formatter.LINE 
				+ "\n Wat wilt u met het UserAccount doen?" 
				+ "\n 1: ik wil er " + eAc + eAc+ "n aanpassen." 
				+ "\n 2: ik wil er " + eAc + eAc+ "n toevoegen." 
				+ "\n 3: ik wil er " + eAc + eAc + "n verwijderen."
				+ "\n 4: ik wil Accounts filteren op type"
				+ "\n 0: ik wil terug naar Medewerker menu"
				+ "\n" + Formatter.LINE, Kleur.CART);
		do {
			validResponse = true;
			response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);

			switch (response) {
			case "0":
				console.println(" terug ", Color.RED);
				// gaat naar mainemployeemenu.
				mainEmpMenu.runMenu();
				break;
			case "1":
				console.println(" aanpassen.", Color.ORANGE);
				String keuze = console.printResponse("Kies een getal voor een Account om aan te passen", "", Color.CYAN);
				UserAccount aangepastAccount = createUpdateUser(filteredUsers.get(Integer.parseInt(keuze)-1));
				userController.updateUser(aangepastAccount);
				break;
			case "2":
				console.println("toevoegen", Color.ORANGE);
				userController.createUserAccount();

				break;
			case "3":
				console.println(" verwijderen.", Color.ORANGE);
                                String keuzeDelete = console.printResponse("Kies een getal voor een Account om te verwijderen", "", Color.CYAN);
                                UserAccount delAccount = (filteredUsers.get(Integer.parseInt(keuzeDelete)-1));
                comingFromOutsideMenu = true; 
				userController.deleteAccount(delAccount);
				break;
			case "4":
				
				console.println("Filteren op type", Color.ORANGE);
				filterType = selectType(filterType);
				console.println("Filter " + filterType.getNL(), Color.YELLOW);
				
				filterAccountsByType();
				
				comingFromOutsideMenu = false;
				runMenu();

				break;
		
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);

	}
	
	

	private void filterAccountsByType() {
		filteredUsers.clear();
		for(UserAccount user: users){
			if(filterType ==UserType.ALL || user.getUserType()==filterType){
				filteredUsers.add(user);
			}
		}
	}





	public UserAccount createUpdateUser(UserAccount user) {

//		String naam, merk, info;
//		BigDecimal prijs = null;
//		int  voorraad;
//		
//		
//		// Naam
//		console.println("Wat is de     naam   van het nieuwe product?", Color.ORANGE);
//		naam = console.printResponse("naam :", product.getName(), Color.ORANGE);
//		console.println(naam, Color.yellow);
//
//		// Merk
//		console.println("Wat is het    merk   van het nieuwe product?", Color.ORANGE);
//		merk = console.printResponse("merk:", product.getBrand(), Color.ORANGE);
//		console.println(merk, Color.yellow);
//
//		// Categorie
//		if(product.getCategory()==null){
//			product.setCategory(ProductCategory.ALL);
//		};
//		
//		ProductCategory prodCat = selectType(user.getCategory());
//		console.println("Categorie: " + prodCat.getNL(), Color.YELLOW);
//	
//		// Product Informatie
//		console.println("Wat is de informatie   van het nieuwe product?", Color.ORANGE);
//		info = console.printResponse("info:", product.getInfo(), Color.ORANGE);
//		console.println(info, Color.yellow);
//		
//		// Prijs
//		boolean badResponse = false;
//		do {
//
//			try {
//				if(product.getPrice()==null){
//					product.setPrice(new BigDecimal("0.01"));
//				}
//				product.getPrice().setScale(2);
//				prijs = new BigDecimal(
//						console.printResponse("wat is de prijs? ", product.getPrice().toString(), Color.RED));
//				badResponse = false;
//			} catch (NumberFormatException e) {
//				console.println("Foutieve invoer, vul een getal in", Color.RED);
//				badResponse = true;
//			}
//
//		} while (badResponse);
//
//		console.println("" + prijs, Color.yellow);
//
//		// Voorraad
//		console.println("wat is de voorraad van het product ", Color.ORANGE);
//
//		badResponse = false;
//		voorraad = -1;
//		do {
//			try {
//				voorraad = console.printResponseInt("voorraad:", "" + product.getStockCount(), Color.ORANGE);
//				badResponse = false;
//			} catch (Exception e) {
//				console.println("Foutieve invoer, vul een getal in", Color.RED);
//				badResponse = true;
//			}
//		} while (badResponse);
//		console.println("" + voorraad, Color.yellow);
//
//		
//		// Product populeren met waarden
//		product.setInfo(info);
//		product.setName(naam);
//		product.setBrand(merk);
//		product.setPrice(prijs);
//		product.setStockCount(voorraad);
//		product.setCategory(prodCat);
//		
//		console.println("\nProduct succesvol opgeslagen\n ", Color.magenta);
		return user;

	}
	
	
	
	
	

	public UserType selectType(UserType type){
		console.println(
				"Selecteer het type gebruiker 1: Medewrker 2: Klant"
				+" 3:  alles/anders",
				Color.ORANGE);

		int typeNum = 0;
		boolean badResponse = false;
		do {
			try {
				
				typeNum = console.printResponseInt("type:", "" + (type.ordinal()+1), Color.ORANGE);
				badResponse = false;

				if (typeNum < 1 || typeNum > UserType.values().length) {
					console.println("Foutieve invoer", Color.RED);
					badResponse = true;
				}else{
					type = UserType.values()[typeNum-1];
				}
			} catch (NumberFormatException e) {
				console.println("Foutieve invoer, vul een getal in", Color.RED);
				badResponse = true;
			}

		} while (badResponse);

		return type;
	}

}
