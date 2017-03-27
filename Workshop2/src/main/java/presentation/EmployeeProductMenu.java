/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation;

/**
 *
 * @author Frank
 */

import java.awt.Color;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import main.java.infrastructure.Kleur;
import main.java.controller.MainController;
import main.java.controller.ProductController;


@Component
public class EmployeeProductMenu {
    
    
    @Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
}
	//@Autowired
	//private ProductController productController;
       // @Autowired
       // private MainEmployeeMenu m;
       // @Autowired
        //private Product product1;
	/*
	public void runEmployeeProductMenu() {
		String response;
		boolean validResponse;
				
				console.println("================================"+
				"\nWat wilt u met het product doen?"+
				"\n 1: ik wil het aanpassen."+
				"\n 2: ik wil er één toevoegen."+
				"\n 3: ik wil er één verwijderen."+
                                "\n 4: ik wil een overzicht van alle producten."+        
                                "\n 5: ik wil één producten zoeken die voldoen aan ..."+        
                                "\n 0: ik wil terug naar Medewerker menu"+
				"\n================================", Kleur.CART);
		do{
			validResponse = true;
			response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		
			switch (response) {
			case "0":
				console.println(" terug ",Color.RED);
				//m.runMenu();
				break;
			case "1":
				console.println(" aanpassen.",Color.ORANGE);
				//productController.();
				break;
			case "2":
				console.println("toevoegen",Color.ORANGE);
                             //   String naam,merk,info;
                                
                              //  BigDecimal prijs = null;
                               // ProductCategory categorie;
                              //  naam = console.printResponse("Wat is de     naam   van het nieuwe product?","",Color.ORANGE);
                              //  merk = console.printResponse("Wat is het    merk   van het nieuwe product?","",Color.ORANGE);
                              //  categorie = console.printResponse("Wat is de catogorie  van het nieuwe product?",,Color.ORANGE);
                              //  info = console.printResponse("Wat is de informatie van het nieuwe product?","",Color.ORANGE);
                              // prijs = console.printResponse("Wat is de     prijs  van het nieuwe product?","",Color.ORANGE);
                              // int voorraad = console.printResponseInt("Wat is de  voorraad  van het nieuwe product?",8, Color.ORANGE);
                               
                               
                             //  product1.setName(naam);
                              // product1.setBrand(merk);
                             //  product1.setInfo(info);
                             //  product1.setStockCount(voorraad);
                             //  product1.setCategory(main.java.model.ProductCategory.CREAM);
                              // product1.setPrice(prijs);
                               
				//productController.createProduct(product1);
				break;
                        case "3":
				console.println(" verwijderen.",Color.ORANGE);
				//productController.inlogControle();
				break;
			case "4":
				console.println("overzicht",Color.ORANGE);
				//productController.();
				break;
                        case "5":
				console.println("zoeken opn",Color.ORANGE);
				//productController.();
				break;                
			default:
				console.println("Ongeldige invoer, probeer opnieuw.",Color.magenta);
				validResponse = false;
				break;
			}
		}while(!validResponse);

	}
}

*/