package main.java.presentation;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.CartController;
import main.java.controller.MainController;
import main.java.model.Cart;
import main.java.model.CartSubOrder;

@Component
public class MainOrderMenu {

	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private CartController cartController;

	private Cart userCart;

	public void runMenu() {
		// met de db checken of er iets in de opgeslagen cart van de user zit
		long userId = mainController.getCurrentUser().getId();
		userCart = cartController.getCart(userId);
		// cart inhoud laten zien
		displayCart();

		console.println("================================================" 
				+ "\nSelecteer een catagorie"
				+ "\n=================================================" 
				+ "\n 1: Alles kazen"
				+ "\n 2: Medium tot Harde kazen" + "\n 3: Zacht Schimmel kazen" 
				+ "\n 4: Blauwe kazen;"
				+ "\n 5: Room kazen" + "\n 6: Geiten kazen" + "\n 0: uitloggen"
				+ "\n====================================================\n", Color.CYAN);
	}

	public void displayCart() {
		BigDecimal totaal = BigDecimal.ZERO;
		// header van tabel
		// id in deze lijst is het getalletje in de lijst 1,2,3...,n'de subOrder
		console.println("============================================================", Color.YELLOW);
		console.println("  -- Uw Winkel Wagen --", Color.GREEN);
		console.println("============================================================", Color.YELLOW);
//				+ "\n Id -\tProduct   -\tAantal  -\tPrijs  -\tSubtotaal", Color.YELLOW);
		

		// loop om alle suborders te printen en totaal bedrag uit te rekenen
		int[] padnums = { 4,4,25, 10, 10, 12}; 
		int[] allignLR = { 1,1, 0, 1, 1, 1}; 
		console.println(padString(padnums,allignLR,"Id","  ",
				"Product         -","Aantal", "-  Prijs", "- Subtotaal"),Color.YELLOW);
		
		
		List<CartSubOrder> subOrders = userCart.getSubOrders();
		for (CartSubOrder sub : subOrders) {
			Integer i = 1;
			String product = sub.getProduct().getName();
			String aantal = (""+sub.getQuantity());
			String prijs = sub.getProduct().getPrice().toString();
			String subtotaal = sub.getTotalPrice().toString();
			console.println(padString(padnums,allignLR,i.toString(),"  ", 
					product, aantal, prijs, subtotaal),Color.YELLOW);
//			console.println(" " + i + "\t" + sub.toString(), Color.YELLOW);
			totaal = totaal.add(sub.getTotalPrice());
			i++;
		}
		
		totaal.setScale(2);  // aantal decimalen instellen
		
		// Footer van tabel
		console.println("                                             Totaal:  " + totaal, Color.GREEN);
		console.println("============================================================", Color.YELLOW);

	}
	
	public String padString(int[] padnums,int[] allignLR,String... strings) { 
														
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < strings.length; j++) {
			String tempString = strings[j];
			int padnum = padnums[j];
			int lr = allignLR[j];
			int rest = padnum - tempString.length();
			if (rest < 0)
				rest = 0;
			if (lr == 0){
				sb.append(tempString);
			}
			for (int i = 1; i < rest; i++) {
				sb.append(" ");
			}
			if(lr==1)sb.append(tempString);
		}
		return sb.toString();
	}

}
