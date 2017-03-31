package main.java.presentation;

import main.java.infrastructure.ColorConsole;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import main.java.infrastructure.Formatter;
import main.java.model.Cart;
import main.java.model.CartSubOrder;

public interface DisplayCart {
	
	public default void displayCart(ColorConsole console, Cart userCart) {

		BigDecimal totaal = BigDecimal.ZERO;
		// header van tabel
		// id in deze lijst is het getalletje in de lijst 1,2,3...,n'de subOrder
		console.println(Formatter.LINE, Color.YELLOW);
		console.println("  -- Uw Winkel Wagen --", Color.GREEN);
		console.println(Formatter.LINE, Color.YELLOW);
//				+ "\n Id -\tProduct   -\tAantal  -\tPrijs  -\tSubtotaal", Color.YELLOW);
		

		// loop om alle suborders te printen en totaal bedrag uit te rekenen
		int[] padnums = { 4,4,25, 10, 10, 12}; 
		int[] allignLR = { 1,1, 0, 1, 1, 1}; 
		console.println(Formatter.padString(padnums,allignLR,"Id","  ",
				"Product         -","Aantal", "-  Prijs", "- Subtotaal"),Color.YELLOW);
		
		
		List<CartSubOrder> subOrders = userCart.getSubOrders();
                Integer i = 1;
		for (CartSubOrder sub : subOrders) {
			
			String product = sub.getProduct().getName();
			String aantal = (""+sub.getQuantity());
			String prijs = sub.getProduct().getPrice().toString();
			String subtotaal = sub.getTotalPrice().toString();
			console.println(Formatter.padString(padnums,allignLR,i.toString(),"  ", 
					product, aantal, prijs, subtotaal),Color.YELLOW);
			totaal = totaal.add(sub.getTotalPrice());
			i++;
		}
		
		totaal.setScale(2);  // aantal decimalen instellen
		
		// Footer van tabel
                if (subOrders.isEmpty()) {
                    console.println("----------------------------------------------------------------", Color.RED);
                    console.println(Formatter.padString(padnums,allignLR,"  ","  ",
				"                 ","      ", "        ", "            Totaal:  " + totaal),Color.GREEN);
                
                } else {
		console.println("                                             Totaal:  " + totaal, Color.GREEN);
		console.println(Formatter.LINE, Color.YELLOW);
                }
	}

}
