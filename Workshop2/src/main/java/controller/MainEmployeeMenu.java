package main.java.controller;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.presentation.ColorConsole;

@Component
public class MainEmployeeMenu {
	
	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private UserController userController;
	
	public void runMenu() {
		
				
	
				console.println("================================================"+
				"\n Welkom in het Medewerker Menu"+
				"\n================================================="+
				"\n 1: "+
				"\n 2: "+
				"\n 3: "+
				"\n 4: "+
				"\n 5: "+
				"\n 6: "+
				"\n 0: uitloggen"+
				"\n====================================================/n", Color.CYAN);
	}

}
