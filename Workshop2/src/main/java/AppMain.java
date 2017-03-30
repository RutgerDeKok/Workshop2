package main.java;

import org.springframework.context.ApplicationContext;


import main.java.controller.MainController;
import main.java.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {

	public static void main(String[] args) {

	
		ApplicationContext context = new AnnotationConfigApplicationContext(main.java.config.AppConfig.class);
		MainController controller = context.getBean(main.java.controller.MainController.class);

		controller.start();

		// System.out.println("Einde programma");
	}

}
