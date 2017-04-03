
package main.java.presentation;


import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.UserController;
import main.java.infrastructure.Kleur;
import main.java.controller.MainController;


@Component
public class MainMenu {
	
	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private UserController userController;
	
	public void runStartMenu() {
		String response;
		boolean validResponse;
				
				console.println(Formatter.LINE+
				"\nWelkom bij de Kaas Manager 2017."+
				"\nWat wilt u doen?"+
				"\n 1: Inloggen."+
				"\n 2: Klant Account aanmaken."+
				"\n 0: Afsluiten."+
				"\n"+Formatter.LINE, Kleur.CART);
		do{
			validResponse = true;
			response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		
			switch (response) {
			case "0":
				console.println(" sluit af ",Color.RED);
				System.exit(0);
				break;
			case "1":
				console.println("U gaat inloggen.",Color.ORANGE);
				mainController.inlogControle();
				break;
			case "2":
				console.println("U gaat een Klant Account aanmaken",Color.ORANGE);
				userController.createCustomerAccount();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.",Color.magenta);
				validResponse = false;
				break;
			}
		}while(!validResponse);

	}
	

}
