package main.java.presentation;

import main.java.infrastructure.ColorConsole;
import java.awt.Color;
import java.util.List;

import main.java.infrastructure.Formatter;
import main.java.infrastructure.Kleur;
import main.java.model.Product;

public interface DisplayProducts {
	
	public default void displayProducts (ColorConsole console, List<Product> products) {

		console.println(Formatter.LINE, Color.ORANGE);
		console.println("Overzicht Alle Producten.", Kleur.RED_ORANGE);
		// displayed alle producten.
		console.println(Formatter.LINE, Color.orange);
		int[] padnums = { 4, 3, 20, 16, 22, 8, 3, 14, 10};
		int[] alignLR = { 1, 1,  0,  0,  0, 1,  1, 0,  1};

		console.println(Formatter.padString(padnums, alignLR, "id"," ", "naam ", "merk", "info"
					, "prijs",	" ", "catogorie", "voorraad"), Kleur.RED_ORANGE);
		
		int i = 1;
		for (Product p : products) {
			String idp = (""+i);
//			String idp = p.getId() + "";
			String naamp = p.getName();
			String merkp = p.getBrand();
			String infop = p.getInfo(); 
			String prijsp = p.getPrice() + "";
			String catop = p.getCategory().getNL();
			String voorraadp = ""+p.getStockCount();
			console.println(
					Formatter.padString(padnums, alignLR, idp," ", naamp, merkp, infop, prijsp, " ",catop, voorraadp),
					Color.YELLOW);
			i++;
		}
		
		
		
	}

}
