
package main.java.presentation;

/**
 *
 * @author Frank
 */

import main.java.infrastructure.ColorConsole;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.infrastructure.Kleur;
import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.infrastructure.Formatter;
import main.java.model.Product;
import main.java.model.ProductCategory;

@Component
public class EmployeeProductMenu implements DisplayProducts{

	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private ProductController productController;

	// @Autowired
	// private ProductController pC;
	@Autowired
	private MainEmployeeMenu mem;
	private char eAc = '\u00E9'; // e accent
	private List<Product> products;
	private List<Product> filteredProducts;
	private boolean comingFromOutsideMenu = true;
	private ProductCategory filterCategory = ProductCategory.ALL;

	public void runEmployeeProductMenu() {
		String response;
		boolean validResponse;
		
		if(comingFromOutsideMenu){
		products = productController.getAllProducts();  
		filteredProducts = new ArrayList<>(products);
		}
		comingFromOutsideMenu = false;
		displayProducts(console, filteredProducts);
		
		console.println("\n"+Formatter.LINE, Kleur.CART);
		console.println("Product Menu", Kleur.RED_ORANGE);
		console.println(Formatter.LINE 
				+ "\n Wat wilt u met het product doen?" 
				+ "\n 1: ik wil er " + eAc + eAc+ "n aanpassen." 
				+ "\n 2: ik wil er " + eAc + eAc+ "n toevoegen." 
				+ "\n 3: ik wil er " + eAc + eAc + "n verwijderen."
				+ "\n 4: ik wil producten filteren op categorie"
				+ "\n 5: ik wil producten filteren op merk" 
				+ "\n 0: ik wil terug naar Medewerker menu"
				+ "\n" + Formatter.LINE, Kleur.CART);
		do {
			validResponse = true;
			response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);

			switch (response) {
			case "0":
				console.println(" terug ", Color.RED);
				// gaat naar mainemployeemenu.
				mem.runMenu();
				break;
			case "1":
				console.println(" aanpassen.", Color.ORANGE);
				String keuze = console.printResponse("Kies een getal om een product om aan te passen", "", Color.CYAN);
				Product aangepastProduct = createUpdateProduct(filteredProducts.get(Integer.parseInt(keuze)-1));
				productController.pasProductAan(aangepastProduct);
				break;
			case "2":
				console.println("toevoegen", Color.ORANGE);
				productController.createProduct();

				break;
			case "3":
				console.println(" verwijderen.", Color.ORANGE);
                                String keuzeDelete = console.printResponse("Kies een getal om een product om aan te verwijderen", "", Color.CYAN);
                                Product delProduct = (filteredProducts.get(Integer.parseInt(keuzeDelete)-1));
                comingFromOutsideMenu = true; 
				productController.deleteProductP(delProduct);
				break;
			case "4":
				
				console.println("Filteren op categorie", Color.ORANGE);
				filterCategory = selectCategory(filterCategory);
				console.println("Filter " + filterCategory.getNL(), Color.YELLOW);
				
				filterProductenByCat();
				
				comingFromOutsideMenu = false;
				runEmployeeProductMenu();

				break;
			case "5":
				console.println("Filteren op merk", Color.ORANGE);
				// productController.();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);

	}
	
	
	
	

	private void filterProductenByCat() {
		filteredProducts.clear();
		for(Product prod: products){
			if(filterCategory ==ProductCategory.ALL || prod.getCategory()==filterCategory){
				filteredProducts.add(prod);
			}
		}
	}





	public Product createUpdateProduct(Product product) {

		String naam, merk, info;
		BigDecimal prijs = null;
		int  voorraad;
		
		
		// Naam
		console.println("Wat is de     naam   van het nieuwe product?", Color.ORANGE);
		naam = console.printResponse("naam :", product.getName(), Color.ORANGE);
		console.println(naam, Color.yellow);

		// Merk
		console.println("Wat is het    merk   van het nieuwe product?", Color.ORANGE);
		merk = console.printResponse("merk:", product.getBrand(), Color.ORANGE);
		console.println(merk, Color.yellow);

		// Categorie
		if(product.getCategory()==null){
			product.setCategory(ProductCategory.ALL);
		};
		
		ProductCategory prodCat = selectCategory(product.getCategory());
		console.println("Categorie: " + prodCat.getNL(), Color.YELLOW);
	
		// Product Informatie
		console.println("Wat is de informatie   van het nieuwe product?", Color.ORANGE);
		info = console.printResponse("info:", product.getInfo(), Color.ORANGE);
		console.println(info, Color.yellow);
		
		// Prijs
		boolean badResponse = false;
		do {

			try {
				if(product.getPrice()==null){
					product.setPrice(new BigDecimal("0.01"));
				}
				product.getPrice().setScale(2);
				prijs = new BigDecimal(
						console.printResponse("wat is de prijs? ", product.getPrice().toString(), Color.RED));
				badResponse = false;
			} catch (NumberFormatException e) {
				console.println("Foutieve invoer, vul een getal in", Color.RED);
				badResponse = true;
			}

		} while (badResponse);

		console.println("" + prijs, Color.yellow);

		// Voorraad
		console.println("wat is de voorraad van het product ", Color.ORANGE);

		badResponse = false;
		voorraad = -1;
		do {
			try {
				voorraad = console.printResponseInt("voorraad:", "" + product.getStockCount(), Color.ORANGE);
				badResponse = false;
			} catch (Exception e) {
				console.println("Foutieve invoer, vul een getal in", Color.RED);
				badResponse = true;
			}
		} while (badResponse);
		console.println("" + voorraad, Color.yellow);

		
		// Product populeren met waarden
		product.setInfo(info);
		product.setName(naam);
		product.setBrand(merk);
		product.setPrice(prijs);
		product.setStockCount(voorraad);
		product.setCategory(prodCat);
		
		console.println("\nProduct succesvol opgeslagen\n ", Color.magenta);
		return product;

	}
	
	
	
	
	

	public Product pasProductAan() {

		String zoeknaam, zoekmerk;
		console.println("Wat is de     naam   van het nieuwe product?", Color.ORANGE);
		zoeknaam = console.printResponse("naam :", "", Color.ORANGE);
		console.println(zoeknaam, Color.yellow);

		console.println("Wat is het    merk   van het nieuwe product?", Color.ORANGE);
		zoekmerk = console.printResponse("merk:", "", Color.ORANGE);
		console.println(zoekmerk, Color.yellow);

		Product productdata = new Product();
		productdata.setName(zoekmerk);
		productdata.setBrand(zoekmerk);
		return productdata;

	}
	
	

	public ProductCategory selectCategory(ProductCategory cat){
		console.println(
				"Selecteer de catogorie van het product 1: Hard-Medium 2: Zacht-Schimmel"
				+"\n3: blauw 4: room 5: geit 6: alles/overige ",
				Color.ORANGE);

		int categorieNum = 0;
		boolean badResponse = false;
		do {
			try {
				
				categorieNum = console.printResponseInt("categorie:", "" + (cat.ordinal()+1), Color.ORANGE);
				badResponse = false;

				if (categorieNum < 1 || categorieNum > ProductCategory.values().length) {
					console.println("Foutieve invoer", Color.RED);
					badResponse = true;
				}else{
					cat = ProductCategory.values()[categorieNum-1];
				}
			} catch (NumberFormatException e) {
				console.println("Foutieve invoer, vul een getal in", Color.RED);
				badResponse = true;
			}

		} while (badResponse);

		return cat;
	}

}
