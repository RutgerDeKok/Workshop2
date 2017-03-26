package main.java.config;

import main.java.presentation.StartMenu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class StartProgram {
	
	

//	public final static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	public static void main(String[] args) {
		
		ApplicationContextProvider appContext = new ApplicationContextProvider();
		StartMenu inlogMenu = (StartMenu) appContext.getApplicationContext().getBean("testBean", main.java.presentation.StartMenu.class);
		
		StartProgram sp = new StartProgram();
//		StartMenu inlogMenu;
//		inlogMenu =(StartMenu)context.getBean(main.java.presentation.StartMenu.class);
		inlogMenu.runStartMenu();
	   

		System.out.println("Einde programma");
	}
	
}
