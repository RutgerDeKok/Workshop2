package main.java.presentation;

import main.java.infrastructure.ColorConsole;
import java.awt.Color;
import java.util.List;

import main.java.infrastructure.Formatter;
import main.java.infrastructure.Kleur;
import main.java.model.Adress;
import main.java.model.UserAccount;

public interface DisplayUsers {
	
	public default void displayUsers (ColorConsole console, List<UserAccount> users) {

		console.println(Formatter.LINE, Color.ORANGE);
		console.println("Overzicht Alle Gebruiker Accounts.", Kleur.RED_ORANGE);
		
		console.println(Formatter.LINE, Color.orange);
		int[] padnums = { 4, 3, 22, 28, 10, 8, 3, 14};
		int[] alignLR = { 1, 1,  0,  0, 1, 1, 1,  1};
		
	
		console.println(Formatter.padString(padnums, alignLR, "id"," ", "email", "naam", "postcode"
					, "huisnr",	" ", "type"), Kleur.RED_ORANGE);
		
		int i = 1;
		for (UserAccount ua : users) {
			Adress ad = ua.getBillingAdress();
			String id = (""+i);
			String email= ua.getEmail();
			if(ad==null){
				ad = new Adress();			
			}
			String firstName =ad.getFirstName();
			String familyName =ad.getFamilyName();
			
			
			String naam = (firstName+ " "+(ad.getInsertion()!= null?ad.getInsertion()+" ":"")+familyName);
			
			String postcode = ad.getZipCode();
			String huisnr = ad.getNumber() +(ad.getNumAddition()!=null?" "+ad.getNumAddition():"");
			String	type = ua.getUserType().getNL();
	
			console.println(
					Formatter.padString(padnums, alignLR, id," ", email, naam, postcode, huisnr, " ",type),
					Color.YELLOW);
			i++;
		}
		
		
	}

}
