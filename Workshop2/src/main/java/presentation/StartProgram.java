package main.java.presentation;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartProgram {
    
    
    public static void main(String[] args){
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    
        
        InlogMenu inlogMenu = (InlogMenu) context.getBean("inlogMenu");      
        inlogMenu.runPresentationConsoleInlogMenu();
       
       
        System.out.println("Einde programma");
    }
}
