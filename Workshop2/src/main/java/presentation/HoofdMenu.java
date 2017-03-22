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
public class HoofdMenu {
   //Dit is het menu  waar je komt als de inlog controle succesvol was.
    
    public void runHoofdMenu(){

      
   
int cijfer = 100; 

System.out.println("=========================");
System.out.println("Welkom in het hoofdmenu van Kaas Manager 2017.");
System.out.println("Waar wilt u doen?");

        System.out.println(" 1: Productmenu.");  
        System.out.println(" 2: Klantmenu."); 
        System.out.println(" 3: Adres menu."); 
        System.out.println(" 4: Bestellingmenu.");  
        System.out.println(" 5: Medewerkermenu."); 
        System.out.println(" 6: Accountmenu.");    
        System.out.println(" 7: Factuurmenu.");  
        System.out.println(" 8: Bestelregelmenu.");
        System.out.println(" 9: Instellingen.");
        System.out.println(" 0: Afsluiten.");
        System.out.println("=========================");
System.out.print("Geef uw keuze: ");


 cijfer = TextIO.getlnInt();


 
    switch (cijfer){
        case 0: System.out.println("Afsluiten."); System.exit(0); break;
        case 1: System.out.println("1 Productmenu.");  //ProductMenu productmenu = new ProductMenu(); productmenu.productmenu(); break;
        case 2: System.out.println("2 Klantmenu."); //KlantMenu klantmenu = new KlantMenu(); klantmenu.klantenmenu();   break;
        case 3: System.out.println("3 Adresmenu."); // AdresMenu adresmenu = new AdresMenu(); adresmenu.adressenmenu(); break;
        case 4: System.out.println("4 Bestellingmenu."); // BestellingMenu bestellingmenu = new BestellingMenu(); bestellingmenu.bestellingenmenu(); break;
        case 5: System.out.println("5 Medewerkermenu."); // MedewerkerMenu medewerkersmenu = new MedewerkerMenu(); medewerkersmenu.medewerkersmenu(); break;
        case 6: System.out.println("6 Accountmenu.");  //AccountMenu am = new AccountMenu(); am.accountsmenu(); break;
        case 7: System.out.println("7 Factuurmenu.");   //FactuurMenu factuurmenu = new FactuurMenu(); factuurmenu.facturenmenu(); break;
        case 8: System.out.println("8 Bestelregelmenu.");// BestelregelMenu bestelregelmenu = new BestelregelMenu(); bestelregelmenu.bestelregelmenu(); break;
        case 9: System.out.println("9 Instellingen.");// InstellingenMenu instellingenmenu = new InstellingenMenu(); instellingenmenu.instellingenmenu(); break;
        default: System.out.println("Ongeldige invoer, probeer opnieuw."); runHoofdMenu();
    }
}



    

}

