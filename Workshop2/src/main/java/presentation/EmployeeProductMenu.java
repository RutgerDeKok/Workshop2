
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
public class EmployeeProductMenu {

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
	private List<Product> producten;

	public void runEmployeeProductMenu() {
		String response;
		boolean validResponse;

		console.println(Formatter.LINE + "\n Product Menu" + "\n" + Formatter.LINE
				+ "\n Wat wilt u met het product doen?" + "\n 1: ik wil het aanpassen." + "\n 2: ik wil er " + eAc + eAc
				+ "n toevoegen." + "\n 3: ik wil er " + eAc + eAc + "n verwijderen."
				+ "\n 4: ik wil een overzicht van alle producten."
				+ "\n 5: ik wil alle producten zoeken die voldoen aan ..." + "\n 0: ik wil terug naar Medewerker menu"
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
				Product aangepastProduct = createUpdateProduct(producten.get(Integer.parseInt(keuze)-1));
				productController.pasProductAan(aangepastProduct);
				break;
			case "2":
				console.println("toevoegen", Color.ORANGE);
				productController.createProduct();

				break;
			case "3":
				console.println(" verwijderen.", Color.ORANGE);
				// productController.inlogControle();
				break;
			case "4":
				console.clear();
				console.println("Overzicht Alle Producten.", Color.ORANGE);
				// displayed alle producten.
				console.println(Formatter.LINE, Color.orange);
				int[] padnums = { 6, 26, 16, 26, 8, 18, 8 };
				int[] alignLR = { 1, 1, 1, 1, 1, 0, 1 };

				console.println(Formatter.padString(padnums, alignLR, "id ", "naam ", "merk ", "info ", "prijs ",
						" catogorie", "voorraad"), Color.orange);
				producten = productController.getAllProducts();

				for (Product p : producten) {
					String idp = p.getId() + " ";
					String naamp = p.getName() + " ";// beetje lang
					String merkp = p.getBrand() + " ";
					String infop = p.getInfo() + " "; // lang
					String prijsp = p.getPrice() + " ";
					String catop = p.getCategory() + " "; // beetje lang
					String voorraadp = p.getStockCount() + " ";
					console.println(
							Formatter.padString(padnums, alignLR, idp, naamp, merkp, infop, prijsp, catop, voorraadp),
							Color.YELLOW);

				}

				runEmployeeProductMenu();

				break;
			case "5":
				console.println("zoeken opn", Color.ORANGE);
				// productController.();
				break;
			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);

	}
	
	
	
	

	public Product createUpdateProduct(Product product) {

		String naam, merk, info;
		BigDecimal prijs = null;
		int categorie, voorraad;

		console.println("Wat is de     naam   van het nieuwe product?", Color.ORANGE);
		naam = console.printResponse("naam :", product.getName(), Color.ORANGE);
		console.println(naam, Color.yellow);

		console.println("Wat is het    merk   van het nieuwe product?", Color.ORANGE);
		merk = console.printResponse("merk:", product.getBrand(), Color.ORANGE);
		console.println(merk, Color.yellow);

		console.println(
				"wat is de catogorie van het product 1: Hard-Medium 2: Zacht-gesmolten 3: blauw 4: room 5: geit 6: overige/weet niet ",
				Color.ORANGE);

		categorie = 0;
		boolean badResponse = false;
		do {
			try {
				if(product.getCategory()==null){product.setCategory(ProductCategory.ALL);
				};
				categorie = console.printResponseInt("categorie:", "" + (product.getCategory().ordinal()+1), Color.ORANGE);
				badResponse = false;

				if (categorie < 1 || categorie > ProductCategory.values().length) {
					console.println("Foutieve invoer", Color.RED);
					badResponse = true;
				}
			} catch (NumberFormatException e) {
				console.println("Foutieve invoer, vul een getal in", Color.RED);
				badResponse = true;
			}

		} while (badResponse);

		console.println("" + categorie, Color.yellow);

		console.println("Wat is de informatie   van het nieuwe product?", Color.ORANGE);
		info = console.printResponse("info:", product.getInfo(), Color.ORANGE);
		console.println(info, Color.yellow);

		badResponse = false;
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

		
		product.setInfo(info);
		product.setName(naam);
		product.setBrand(merk);
		product.setPrice(prijs);
		product.setStockCount(voorraad);

		switch (categorie) {
		case 1:
			product.setCategory(ProductCategory.MEDIUM_HARD);
			break;
		case 2:
			product.setCategory(ProductCategory.SOFT_MOLD);
			break;
		case 3:
			product.setCategory(ProductCategory.BLUE);
			break;
		case 4:
			product.setCategory(ProductCategory.CREAM);
			break;
		case 5:
			product.setCategory(ProductCategory.GOAT);
			break;
		case 6:
			product.setCategory(ProductCategory.ALL);
			break;
		}

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

	public void printProductLijst() {
		//
		System.out.print("hio");
	}

}
