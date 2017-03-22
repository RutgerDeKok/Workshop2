/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation;

import java.util.Arrays;
import main.java.infrastructure.TextIO;

/**
 *
 * @author Frank
 */
public class PresentationConsoleMenu {
    
    //niks (ding)
    
   public void runPresentationConsoleInlogMenu(){
    
    int cijfer;
      
      System.out.println("==========================");
      System.out.println("Welkom bij de Kaas Manager 2017.");
      System.out.println("Wat wilt u doen?");
      System.out.println(" 1: Inloggen.");
      System.out.println(" 2: Account aanmaken.");
      System.out.println(" 0: Afsluiten.");
      System.out.println("==========================");
      System.out.print("Maak uw keuze: ");
      
      cijfer = TextIO.getlnInt();
      


    switch (cijfer){
        case 0: System.out.println(" sluit af "); break;
        case 1: System.out.println("U gaat inloggen."); inlogControle();      break;
        case 2: System.out.println("U gaat een Account aanmaken"); inlogAccountAanmaken(); break; 
        default: System.out.println("Ongeldige invoer, probeer opnieuw."); runPresentationConsoleInlogMenu();
    }

  }
      
      
   public void inlogControle(){
          
        System.out.print("Voer uw gebruikers email in: ");
       
        String accountInlogEmail = TextIO.getln();
               
        System.out.print("Voer uw wachtwoord in: ");
       
       char[] wachtwoord = TextIO.getlnChars();
       
       System.out.println(Arrays.toString(wachtwoord) + "is het wachtwoord");
       System.out.println(wachtwoord.length);
       
       
       }
     
           
       
       
       
   
   
     
    
      public void inlogAccountAanmaken(){
       System.out.println("doet nog niks");
      }
      /*
          System.out.println("U gaat een account aanmaken, u krijgt automatisch een accountId.");
       System.out.println("Wat voor account wilt u aanmaken? \n1: Klant account.\n2: Medewerker account.");
       System.out.print("Geef uw keuze: ");
          
       int type = TextIO.getlnInt();  
        
       System.out.print("Wachtwoord: ");
       
       String wachtwoord = TextIO.getln();
       
       
       
        //System.out.print("uw nieuwe account id is: ");
        
        LOGGER.debug("In inputs waren wachtwoord: {} , en type {} ", wachtwoord, type);
                
          Account a = new Account();
        
        a= accountController.createAccount(type, wachtwoord);
       
       
         
                  
          System.out.println("Uw account is : "+ a.toStringWachtwoordloos() );
                  
          
         
          //System.out.println("uw wachtwoord is : "+ wachtwoord);
          
        
         // data in sql opslaan en id terug weergeven!!
         
         //dan terug naar loginmenu ALTIJD
         
        
         inlogMenu();
         */
      
}
