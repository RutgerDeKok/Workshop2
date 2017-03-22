package main.java.presentation;

public class StartProgram {
    
    
    public static void main(String[] args){
    /*    
         Logger logger = LogManager.getLogger(StartProgram.class);
                logger.info("Programma runt ");
       
    */
        //System.out.println("Eerst inloggen");
        
        PresentationConsoleMenu inlogmenu= new PresentationConsoleMenu();
               inlogmenu.runPresentationConsoleInlogMenu();
       
       
        System.out.println("Einde programma");
    }
}
