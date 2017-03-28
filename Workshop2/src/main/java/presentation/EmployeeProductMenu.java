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
import main.java.infrastructure.TextIO;
import main.java.model.Product;
import main.java.model.ProductCategory;
import main.java.service.ProductService;


@Component
public class EmployeeProductMenu {
    
    
        @Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;


public void runHey(){
console.println("hoi",Kleur.CART);
}




	//@Autowired
	//private ProductController pC;
       // @Autowired
        private MainEmployeeMenu mem;
        
	/*
*/

	public void runEmployeeProductMenu() {
		String response;
		boolean validResponse;
				
				console.println("================================================="+
				"\n Product Menu"+
				"\n================================================="+
				"\n Wat wilt u met het product doen?"+
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
				mem.runMenu();
				break;
			case "1":
				console.println(" aanpassen.",Color.ORANGE);
				//productController.();
				break;
			case "2":
				console.println("toevoegen",Color.ORANGE);
                                
                                String naam,merk,info,prijs;
                                
                                int categorie,voorraad;
                               
                                console.println("Wat is de     naam   van het nieuwe product?",Color.ORANGE);
                                naam = console.printResponse("naam :","",Color.ORANGE);
                                console.println(naam, Color.yellow);
                                
                               console.println("Wat is het    merk   van het nieuwe product?",Color.ORANGE);
                                merk = console.printResponse("merk:","",Color.ORANGE);
                                console.println(merk, Color.yellow);
                                
                                console.println("wat is de catogorie van het product 1: Hard-Medium 2: Zacht-gesmolten 3: blauw 4: room 5: geit 6: overige/weet niet ", Color.ORANGE);
                                categorie = console.printResponseInt("categorie:","",Color.ORANGE);
                                 console.println(""+categorie, Color.yellow);
                                 
                                                                  
                              
                              console.println("Wat is de informatie   van het nieuwe product?",Color.ORANGE);
                                info = console.printResponse("info:","",Color.ORANGE);
                                console.println(info, Color.yellow);
                                
                                
                              console.println("wat is de prijs van het product in CENTEN ", Color.RED);
                               prijs = console.printResponse("prijs:","",Color.ORANGE);
                                 console.println(""+prijs, Color.yellow);
                                 
                                                              
                                 
                             console.println("wat is de voorraad van het product ", Color.ORANGE);
                                voorraad = console.printResponseInt("voorraad:","",Color.ORANGE);
                                 console.println(""+voorraad, Color.yellow);
                               
                                Product productNew = new Product();
                                 productNew.setInfo(info);
                                 productNew.setName(naam);
                                 productNew.setBrand(merk);
                                //int in centen moet nog naar BigDecimal.
                                 productNew.setPrice(new BigDecimal(prijs));
                                 productNew.setStockCount(voorraad);
                                 
                                 
                                 
                                 
                                 switch(categorie){
                                   case  1:  productNew.setCategory(ProductCategory.MEDIUM_HARD); break;
                                   case  2:  productNew.setCategory(ProductCategory.SOFT_MOLD); break;
                                   case  3:  productNew.setCategory(ProductCategory.BLUE); break;
                                   case  4:  productNew.setCategory(ProductCategory.CREAM); break;
                                   case  5:  productNew.setCategory(ProductCategory.GOAT); break;
                                   case  6:  productNew.setCategory(ProductCategory.ALL); break;
                                 }
                                 
                                 console.println(productNew.getBrand(),Color.RED);
                                 console.println(productNew.getName(), Color.yellow);
                                 console.println(productNew.getInfo(), Color.cyan);
                                 console.println(productNew.getPrice().toString(), Color.cyan);
                                 
                             //    ProductService pC = new ProductService();
                             //   pC.createProduct(productNew);
                                 
                                // }catch(Exception ex){ console.print(ex.getMessage(),Color.RED);}
                                 
                           
                             
                             //  console.println("einde", Color.yellow);
                              // runEmployeeProductMenu();
				mem.runMenu();
                                
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

