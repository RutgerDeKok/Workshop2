package main.java.presentation.CreateMenus;

import java.awt.Color;
import main.java.infrastructure.ColorConsole;
import main.java.infrastructure.TextIO;
import main.java.infrastructure.Validator;
import main.java.model.Adress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAdressMenu {
    
    @Autowired
    ColorConsole console;
	
	public Adress createAdress() {
		Adress result = new Adress();
		String temp;
		console.println("voer uw voornaam in",Color.cyan);
		temp = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		result.setFirstName(temp);
		console.println("voer uw achternaam in zonder tussen voegsel",Color.cyan);
		temp = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		result.setFamilyName(temp);
		console.println("voer uw tussenvoegsel in of laat blanco",Color.cyan);
		temp = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		result.setInsertion(temp);
		console.println("voer uw straat in",Color.cyan);
		temp = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		result.setStreet(temp);
		console.println("voer uw huisnummer in zonder toevoegingen",Color.cyan);
		int num = Integer.parseInt(console.printResponse("Maak uw keuze: \n", "1", Color.CYAN));
		result.setNumber(num);
		console.println("voer een eventuele nummer toevoeging in of laat blanco",Color.cyan);
		temp = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		result.setNumAddition(temp);
		do{
		console.println("voer  uw postcode in",Color.cyan);
		temp = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		} while(!Validator.getInstance().validatePostcode(temp));
		result.setZipCode(temp);
		console.println("voer uw plaatsnaam in",Color.cyan);
		temp = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);
		result.setCity(temp);
		
		return result;
	}

}
