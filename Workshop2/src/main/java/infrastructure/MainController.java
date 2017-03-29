package main.java.infrastructure;

import main.java.presentation.EmployeeMenu.MainEmployeeMenu;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import main.java.model.UserAccount;
import main.java.model.UserType;
import test.main.ConsoleDemo;
import main.java.presentation.InlogMenu;
import main.java.presentation.MainMenu;
import main.java.presentation.CustomerMenu.MainOrderMenu;

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

	
	private final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("javabook");
	

	public void start() {

//		 consoleTest.tester();
		 mainMenu.runStartMenu();
	}
	



	public void inlogControle() {
		UserAccount user = loginMenu.Login();
		if(user==null) start();
		if(user.getUserType()==UserType.CUSTOMER){
			mainOrderMenu.runMenu();
		}
		if(user.getUserType()==UserType.EMPLOYEE){
			mainEmployeeMenu.runMenu();
		}
	}


	@Bean
	public EntityManagerFactory getEntityManagerFactory() {
		return emFactory;
	}

}
