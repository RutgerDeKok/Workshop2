package main.java.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartProgram {
    
    
    public static void main(String[] args){
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    
        
        PresentationConsoleMenu pcm = (PresentationConsoleMenu) context.getBean("presentationConsoleMenu");      
        pcm.runPresentationConsoleInlogMenu();
       
       
        System.out.println("Einde programma");
    }
}
