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
import main.java.presentation.MainOrderMenu;

@Component
public class MainController {

	
	@Autowired
	private MainMenu mainMenu;
	@Autowired
	private InlogMenu loginMenu; 
	@Autowired
	private MainOrderMenu mainOrderMenu; 
	@Autowired
	private MainEmployeeMenu mainEmployeeMenu; 
	
	private UserAccount currentUser;

	
	private final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("webshop");
	

	public void start() {

		 mainMenu.runStartMenu();
	}
	



	public void inlogControle() {
		UserAccount user = loginMenu.Login();
		setCurrentUser(user);
		if(user==null) start();
		if(user.getUserType()==UserType.CUSTOMER){
			mainOrderMenu.runMenu();
		}
		if(user.getUserType()==UserType.EMPLOYEE){
			mainEmployeeMenu.runMenu();
		}
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
