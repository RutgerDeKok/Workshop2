package main.java;

import org.springframework.context.ApplicationContext;

import main.java.presentation.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {

	public static void main(String[] args) {

	
		ApplicationContext context = new AnnotationConfigApplicationContext(main.java.config.AppConfig.class);
		MainMenu menu = context.getBean(main.java.presentation.MainMenu.class);

		menu.runStartMenu();

		// System.out.println("Einde programma");
	}

}
