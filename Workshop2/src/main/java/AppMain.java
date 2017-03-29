package main.java;

import org.springframework.context.ApplicationContext;

import main.java.config.ApplicationContextProvider;
import main.java.controller.MainController;

public class AppMain {

	public static void main(String[] args) {

		ApplicationContextProvider appContext = new ApplicationContextProvider();
		ApplicationContext context = appContext.getApplicationContext();
		MainController controller = (MainController) context.getBean(main.java.controller.MainController.class);

		controller.start();

		// System.out.println("Einde programma");
	}

}
