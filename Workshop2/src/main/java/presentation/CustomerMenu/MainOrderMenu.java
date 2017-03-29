package main.java.presentation.CustomerMenu;

import main.java.infrastructure.ColorConsole;
import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.infrastructure.MainController;
import main.java.controller.UserController;

@Component
public class MainOrderMenu {
	
	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private UserController userController;
	
	public void runMenu() {
		
				
				console.println("================================================"+
				"\n-- Uw Winkel Wagen --"+
				"\n================================================="+
				"\n Product - Categorie - Aantal - Prijs - Subtotaal"+
				"\n 1: ......"+
				"\n 2: ......"+
				"\n 3: "+
				"\n          -          -        -      Totaal:  xx.x"+
				"\n====================================================/n", Color.YELLOW);
	
				console.println("================================================"+
				"\nSelecteer een catagorie"+
				"\n================================================="+
				"\n 1: Alles kazen"+
				"\n 2: Medium tot Harde kazen"+
				"\n 3: Zacht Schimmel kazen"+
				"\n 4: Blauwe kazen, CREAM, GOAT, ALL;"+
				"\n 5: Room kazen"+
				"\n 6: Geiten kazen"+
				"\n 0: uitloggen"+
				"\n====================================================/n", Color.CYAN);
	}
	
	}
