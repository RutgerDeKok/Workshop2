package main.java.controller;

import main.java.presentation.MainEmployeeMenu;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.model.UserAccount;
import main.java.model.UserType;
import main.java.presentation.InlogMenu;
import main.java.presentation.MainMenu;
import main.java.presentation.MainCustomerMenu;

@Component
public class MainController {

	
	@Autowired
	private MainMenu mainMenu;
	@Autowired
	private InlogMenu loginMenu; 
	@Autowired
	private MainCustomerMenu mainCustomerMenu; 
	@Autowired
	private MainEmployeeMenu mainEmployeeMenu; 
	
	private UserAccount currentUser;

	
	private final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("webshop");
	

	public void inlogControle() {
		UserAccount user = loginMenu.Login();
		setCurrentUser(user);
		if(user==null) mainMenu.runStartMenu();
		if(user.getUserType()==UserType.CUSTOMER){
			
			runMainCustomerMenu();
		}
		if(user.getUserType()==UserType.EMPLOYEE){
			mainEmployeeMenu.runMenu();
		}
	}
	
	
	public void runMainCustomerMenu() {
		
		mainCustomerMenu.runMenu();
		
	}




	public UserAccount getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserAccount currentUser) {
		this.currentUser = currentUser;
	}


//	@Bean
	public EntityManagerFactory getEntityManagerFactory() {
		return emFactory;
	}


	

}
