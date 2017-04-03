/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation;

import java.awt.Color;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.Formatter;
import main.java.infrastructure.Kleur;
import main.java.infrastructure.Validator;
import main.java.model.Adress;

/**
 *
 * @author jurjen
 */
public interface DisplayAdress {

	public default void displayAdress(ColorConsole console, Adress adress, String email) {

		console.println(Formatter.LINE, Color.CYAN);
		console.println("  -- Adress Gebruiker: " + email + " --", Color.ORANGE);
		console.println(Formatter.LINE, Color.CYAN);
		
		if(adress == null){
			adress = new Adress();
		}

		System.out.println("adres = "+adress);
		// Eerste adres regel , allemaal 1 String stukjes, ivm verschillende kleuren
		console.println(Formatter.padString(14, 0, "Voornaam:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(20, 0, adress.getFirstName()), Color.ORANGE);
		console.print(Formatter.padString(12, 0, "Tussenvgs:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(8, 0, adress.getInsertion()), Color.ORANGE);
		console.print(Formatter.padString(14, 0, "Achternaam:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(24, 0, adress.getFamilyName() + "\n"), Color.ORANGE);

		// Tweede adres regel
		console.println(Formatter.padString(14, 0, "Straat:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(20, 0, adress.getStreet()), Color.ORANGE);
		console.print(Formatter.padString(12, 0, "Nummer:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(8, 0, ("" + adress.getNumber())), Color.ORANGE);
		console.print(Formatter.padString(14, 0, "Nr. toev:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(20, 0, adress.getNumAddition() + "\n"), Color.ORANGE);

		// Derde adres regel
		console.println(Formatter.padString(14, 0, "Postcode:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(20, 0, adress.getZipCode()), Color.ORANGE);
		console.print(Formatter.padString(12, 0, "Plaats:"), Kleur.RED_ORANGE);
		console.print(Formatter.padString(24, 0, adress.getCity()), Color.ORANGE);

		// Footer van tabel
		console.println(Formatter.LINE, Color.CYAN);
	}

	public default Adress editAdress(ColorConsole console, Adress adress) {

		if(adress == null){
			adress = new Adress();
		}
		
		String response;
		response = console.printResponse("Voornaam?", adress.getFirstName(), Color.cyan);
		console.println(response, Color.ORANGE);
		adress.setFirstName(response);

		response = console.printResponse("Tusssen Voegsel?", adress.getInsertion(), Color.cyan);
		console.println(response, Color.ORANGE);
		adress.setInsertion(response);

		response = console.printResponse("Achternaam?", adress.getFamilyName(), Color.cyan);
		console.println(response, Color.ORANGE);
		adress.setFamilyName(response);

		response = console.printResponse("Straat?", adress.getStreet(), Color.cyan);
		console.println(response, Color.ORANGE);
		adress.setStreet(response);

		boolean badResponse = false;
		do {
			int num = 0;
			response = console.printResponse("Huisnummer (geheel getal)?", ""+adress.getNumber(), Color.cyan);
			try {
				num = Integer.parseInt(response);
				adress.setNumber(num);
				badResponse = false;
			} catch (NumberFormatException e) {
				console.println("omgeldige invoer", Color.magenta);
				badResponse = true;
			}
		} while (badResponse);
		console.println(response, Color.ORANGE);

		response = console.printResponse("Huisnummer Toevoeging? (of laat leeg)", adress.getNumAddition(), Color.cyan);
		console.println(response, Color.ORANGE);
		adress.setNumAddition(response);

		badResponse = false;
		do {
			response = console.printResponse("Postcode?", adress.getZipCode(), Color.cyan);
			response = response.toUpperCase();
			badResponse = (!Validator.getInstance().validatePostcode(response));
			if(badResponse){
				console.println("omgeldige invoer", Color.magenta);
			}		
			
		} while (badResponse);
		adress.setZipCode(response);
		console.println(response, Color.ORANGE);

		response = console.printResponse("Plaatsnaam?", adress.getCity(), Color.cyan);
		console.println(response, Color.ORANGE);
		adress.setCity(response);

		return adress;

	}

}
