
package main.java.presentation;


import org.springframework.stereotype.Component;

import main.java.infrastructure.Validator;
import main.java.model.Adress;
import main.java.controller.AccountController;
import main.java.controller.PassHasher;
import main.java.infrastructure.TextIO;


@Component
public class StartMenu {
	
	public void runStartMenu() {

		int cijfer;

		System.out.println("==========================");
		System.out.println("Welkom bij de Kaas Manager 2017.");
		System.out.println("Wat wilt u doen?");
		System.out.println(" 1: Inloggen.");
		System.out.println(" 2: Klant Account aanmaken.");
		System.out.println(" 0: Afsluiten.");
		System.out.println("==========================");
		System.out.print("Maak uw keuze: ");

		cijfer = TextIO.getlnInt();

		switch (cijfer) {
		case 0:
			System.out.println(" sluit af ");
			break;
		case 1:
			System.out.println("U gaat inloggen.");
			// inlogControle();
			break;
		case 2:
			System.out.println("U gaat een Klant Account aanmaken");
			 klantAccountAanmaken();
			break;
		default:
			System.out.println("Ongeldige invoer, probeer opnieuw.");
			runStartMenu();
		}

	}

	public void klantAccountAanmaken() {

		boolean emailValid = false;
		String email;
		do {
			System.out.println("voer uw email adress in, dit wordt uw login naam");
			email = TextIO.getln();
			// Validate email syntax
			emailValid = Validator.getInstance().validateEmail(email);
			if (!emailValid)
				System.out.println("U heeft een ongeldig email adress ingevoerd");
		} while (!emailValid);

		boolean isNewEmail = checkExistanceEmail(email);
		if (!isNewEmail) {
			System.out.println("Dit email adres heeft reeds een account");
			runStartMenu();
		}
		String hash = null;
		boolean passValid = false;
		do {
			System.out.print("Voer een wachtwoord in van minimaal 4 charachters: ");

			char[] wachtwoord = TextIO.getlnChars();
			if (wachtwoord.length > 3) {
				passValid = true;
				try {
					hash = PassHasher.getSaltedHash(wachtwoord);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} while (!passValid);

		// factuur adress toevoegen
		Adress factuurAdress = createAdress();

		AccountController ac  = new AccountController();
		 ac.createAccount(email, hash, factuurAdress);
		
		// System.out.println("Uw account is : " + a.toStringWachtwoordloos());
		//
		// // System.out.println("uw wachtwoord is : "+ wachtwoord);
		//
		// // data in sql opslaan en id terug weergeven!!
		//
		// // dan terug naar loginmenu ALTIJD
		//
		// inlogMenu();
	}

	private Adress createAdress() {
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

	private boolean checkExistanceEmail(String email) {
		// method need to be implemented
		// check if email is already linked to an acccount
		return false;
	}
}
