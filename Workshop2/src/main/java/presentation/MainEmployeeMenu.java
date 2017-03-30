package main.java.presentation;

import java.awt.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import main.java.presentation.ColorConsole;
import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.UserController;
import main.java.infrastructure.Kleur;
import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.controller.MainController;
import main.java.controller.UserController;
import main.java.infrastructure.Formatter;
import main.java.presentation.MainMenu;

@Component
public class MainEmployeeMenu {

	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private ProductController productController;;
	@Autowired
	private UserController userController;
	@Autowired
	private MainMenu mainmenu;
	@Autowired
	private EmployeeProductMenu epm;

	public void runMenu() {
		String response;
		boolean validResponse;

		console.println(Formatter.LINE + "\n Welkom in het Medewerker Menu"
				+ "\n"+Formatter.LINE
				+ "\n 1: account(aanpassen/toevoegen/verwijderen/overzicht)"
				+ "\n 2: product(aanpassen/toevoegen/verwijderen/overzicht)" + "\n 3: bestelling (aanpassen/overzicht)"
				+ "\n 3: bestelling (aanpassen/overzicht)"
				+ "\n 4: naar klant menu" + "\n 5: " + "\n 6: " + "\n 0: uitloggen"
				+ "\n"+Formatter.LINE, Kleur.CART);

		do {
			validResponse = true;
			response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);

			switch (response) {
			case "0":
				console.println(" terug ", Color.RED);
				mainmenu.runStartMenu();
				break;
			case "1":
				console.println("account.", Color.ORANGE);
				// functie ontbreekt
				break;
			case "2":
				console.println(" ", Color.ORANGE);
                                // gaat naar EmployeeProductMenu
				productController.runEmployeeProductMenu();
				break;
			case "3":
				console.println("bestelling", Color.ORANGE);
				// functie ontbreekt
				break;
			case "4":
				console.println("Naar klant menu", Color.RED);
				mainController.runMainCustomerMenu();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);

	}

}
