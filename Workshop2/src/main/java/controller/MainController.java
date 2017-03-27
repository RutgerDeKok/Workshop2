package main.java.controller;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import main.java.presentation.MainMenuCns;

@Component
public class MainController {
	

//	@Autowired
//	private MainMenu mainMenu;
	@Autowired
	private MainMenuCns mainMenu;   // using console
//	@Autowired
//	private TestDB testMain;
//	@Autowired
//	private ConsoleDemo consoleTest;
	private EntityManagerFactory factory;
	
	
	public void start(){
//		consoleTest.tester();
//		testMain.populateDB();
		
		mainMenu.runStartMenu();
	}
	
	
	
	public void Inloggen() {
		
	}

	public void inlogControle() {
		// TODO Auto-generated method stub
		
	}
	
	@Bean
	public EntityManagerFactory getFactory(){
		return factory;
	}
	
	
}
