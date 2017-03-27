package main.java.config;



import org.springframework.context.ApplicationContext;

import main.java.controller.MainController;





public class AppMain {
	
	

//	public final static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	public static void main(String[] args) {
		
		ApplicationContextProvider appContext = new ApplicationContextProvider();
		ApplicationContext context = appContext.getApplicationContext();
		MainController controller = (MainController) context.getBean(main.java.controller.MainController.class);
		
		controller.start();
	   

//		System.out.println("Einde programma");
	}
	
}
