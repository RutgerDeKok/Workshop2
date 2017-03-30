package main.java.presentation;

import main.java.infrastructure.TextIO;
import main.java.infrastructure.Validator;
import main.java.model.Adress;

public class CreateAdressMenu {
	
	public Adress createAdress() {
		Adress result = new Adress();
		String temp;
		System.out.println("voer uw voornaam in");
		temp = TextIO.getln();
		result.setFirstName(temp);
		System.out.println("voer uw achternaam in zonder tussen voegsel");
		temp = TextIO.getln();
		result.setFamilyName(temp);
		System.out.println("voer uw tussenvoegsel in of laat blanco");
		temp = TextIO.getln();
		result.setInsertion(temp);
		System.out.println("voer uw straat in");
		temp = TextIO.getln();
		result.setStreet(temp);
		System.out.println("voer uw huisnummer in zonder toevoegingen");
		int num = TextIO.getInt();
		result.setNumber(num);
		System.out.println("voer een eventuele nummer toevoeging in of laat blanco");
		temp = TextIO.getln();
		result.setNumAddition(temp);
		do{
		System.out.println("voer  uw postcode in");
		temp = TextIO.getln();
		} while(!Validator.getInstance().validatePostcode(temp));
		result.setZipCode(temp);
		System.out.println("voer uw plaatsnaam in");
		temp = TextIO.getln();
		result.setCity(temp);
		
		return result;
	}

}
