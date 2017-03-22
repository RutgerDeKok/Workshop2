/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation;

import main.java.infrastructure.TextIO;

/**
 *
 * @author Frank
 */
public class PresentationConsoleMenu {
    
    //niks (ding)
    
   public void runPresentationConsoleMenu(){
    
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
        default: System.out.println("Ongeldige invoer, probeer opnieuw."); runPresentationConsoleMenu();
    }

  }
      
      
   public void inlogControle(){
       System.out.println("doet nog niks");
       /*
       Voor referentie:
       0: GEEN MD5 OF SHA-1 HASH!
       1: The user creates an account.
       2: Their password is hashed and stored in the database. At no point is the plain-text (unencrypted) password ever written to the hard drive.
       3: When the user attempts to login, the hash of the password they entered is checked against the hash of their real password (retrieved from the database).
       4: If the hashes match, the user is granted access. If not, the user is told they entered invalid login credentials.
       5: Steps 3 and 4 repeat everytime someone tries to login to their account.

       */
       
       System.out.print("Voer uw accountID in: ");
       /* int AcId = TextIO.getlnInt();

        
       System.out.print("Voer uw wachtwoord in: ");
       
       String wachtwoord = TextIO.getln();
       
       
       
       
       
       if (accountController.loginCheckAccount(AcId, wachtwoord)){
       // if ok -> cijfer 1  -> menu, ,  else inlogmenu();
       
       
       //controle uitvoeren met database, als dat goed is , -> niks-> cijfer is 1 -> toestemming menu.
  
      // if (functie) inlogMenu();
      
        Menu menu = new Menu();
       menu.hoofdmenu();
       
       }
       else{ inlogMenu();}
           
       
       
       
   }
   
      */
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
