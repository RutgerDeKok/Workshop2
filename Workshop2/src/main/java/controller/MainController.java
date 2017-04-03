package main.java.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.model.UserAccount;
import main.java.model.UserType;
import main.java.presentation.InlogMenu;
import main.java.presentation.MainEmployeeMenu;
import main.java.presentation.MainMenu;
import main.java.presentation.klant.CustomerMenu;

@Component
public class MainController {
	
	@Autowired
	private MainMenu mainMenu;
	@Autowired
	private InlogMenu loginMenu; 
	@Autowired
	private CustomerMenu customerMenu; 
	@Autowired
	private MainEmployeeMenu mainEmployeeMenu; 
	private UserAccount currentUser;	

	public UserAccount getCurrentUser() {
		return currentUser;
	}
        
        public void setCurrentUser(UserAccount currentUser) {
            this.currentUser = currentUser;
	}
        
        public void inlogControle() {
		UserAccount user = loginMenu.Login();
		setCurrentUser(user);
		if(user==null) mainMenu.runStartMenu();
		if(user.getUserType()==UserType.CUSTOMER){
			customerMenu.runMenu();
		}
		if(user.getUserType()==UserType.EMPLOYEE){
			mainEmployeeMenu.runMenu();
		}
	}	       

}
