
package main.java.presentation.employee;

import main.java.infrastructure.ColorConsole;
import java.awt.Color;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.infrastructure.Kleur;
import main.java.infrastructure.PassHasher;
import main.java.infrastructure.Validator;
import main.java.controller.UserController;
import main.java.infrastructure.Formatter;
import main.java.model.Adress;
import main.java.model.UserAccount;
import main.java.model.UserType;
import main.java.presentation.DisplayAdress;
import main.java.presentation.DisplayUsers;

@Component
public class EmployeeAccountsMenu implements DisplayUsers, DisplayAdress {

	@Autowired
	private ColorConsole console;
	@Autowired
	private UserController userController;
	@Autowired
	private MainEmployeeMenu mainEmpMenu;

	private char eAc = '\u00E9'; // e accent
	private List<UserAccount> filteredUsers; // filter by UserType
	private UserType filterType = UserType.ALL;

	public void runMenu() {
		String response;
		boolean validResponse;

		filteredUsers = userController.getFilteredUsers();

		displayUsers(console, filteredUsers);

		console.println("\n" + Formatter.LINE, Kleur.CART);
		console.println("Product Menu", Kleur.RED_ORANGE);
		console.println(Formatter.LINE 
				+ "\n Wat wilt u met het UserAccount doen?" 
				+ "\n 1: ik wil er " + eAc + eAc + "n aanpassen." 
				+ "\n 2: ik wil er " + eAc + eAc + "n toevoegen." 
				+ "\n 3: ik wil er " + eAc + eAc + "n verwijderen." 
				+ "\n 4: ik wil Accounts filteren op type"
				+ "\n 0: ik wil terug naar Medewerker menu" 
				+ "\n" + Formatter.LINE, Kleur.CART);
		do {
			validResponse = true;
			response = console.printResponse("Maak uw keuze: \n", "1", Color.CYAN);

			switch (response) {
			case "0":
				console.println(" terug ", Color.RED);
				// gaat naar mainemployeemenu.
				mainEmpMenu.runMenu();
				break;
			case "1":
				console.println(" aanpassen.", Color.ORANGE);
				String keuze = console.printResponse("Kies een getal voor een Account om aan te passen", "",
						Color.CYAN);
				userController.updateUser(filteredUsers.get(Integer.parseInt(keuze) - 1));
				break;
			case "2":
				console.println("toevoegen", Color.ORANGE);
				userController.createNewUserForEmployee();;

				break;
			case "3":
				console.println(" verwijderen.", Color.ORANGE);
				String keuzeDelete = console.printResponse("Kies een getal voor een Account om te verwijderen", "",
						Color.CYAN);
				UserAccount delAccount = (filteredUsers.get(Integer.parseInt(keuzeDelete) - 1));

				userController.deleteAccount(delAccount);
				break;
			case "4":

				console.println("Filteren op type", Color.ORANGE);
				filterType = selectType(filterType);
				console.println("Filter " + filterType.getNL(), Color.YELLOW);

				userController.filterAccountsByType(filterType);

				runMenu();

				break;

			default:
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
				validResponse = false;
				break;
			}
		} while (!validResponse);

	}

	public UserAccount createUpdateUser(UserAccount user) {

		// Email
		console.println("Wat is de email van de gebruiker, dit wordt de login?", Color.ORANGE);
		String email;

		boolean emailValid = false;
		do {
			email = console.printResponse("email:", user.getEmail(), Color.ORANGE);
			emailValid = Validator.getInstance().validateEmail(email);
			if (emailValid) {
				break;
			}
			console.println("Onbekende invoer", Color.magenta);

		} while (!emailValid);
		console.println(email, Color.yellow);
		user.setEmail(email);

		// Adress
		Adress userAdress = user.getBillingAdress();
		console.println("Huidig adres", Color.ORANGE);
		displayAdress(console, userAdress, user.getEmail());
		
		
		String response = console.printResponse("Wilt u het adres toevoegen of aanpassen? [J/N]", "J", Color.ORANGE);
		if (response.toUpperCase().equals("J")) {

			
			console.println("Adres Aanpassen", Color.ORANGE);
			userAdress = (editAdress(console, user.getBillingAdress()));
			user.setBillingAdress(userAdress);
			console.println("Het aangepast adres:", Color.ORANGE);
			displayAdress(console, user.getBillingAdress(), user.getEmail());
			
		}

		// Gebruiker Type
		if (user.getUserType() == null) {
			user.setUserType(UserType.ALL);
		}
		user.setUserType(selectType(user.getUserType()));
		console.println(user.getUserType().getNL(), Color.yellow);

		// password
		String hash = user.getPassHash();
		if (hash == null) {
			hash = createNewPass();
		} else{
			response = console.printResponse("Wilt u het password aanpassen? [J/N]", "J", Color.ORANGE);
			if(response.toUpperCase().equals("J")){
				hash = createNewPass();
			}			
		}
		user.setPassHash(hash);
		
		console.println("\nGebruiker succesvol aangepast\n", Color.yellow);
		

		return user;

	}



	public UserType selectType(UserType type) {
		console.println("Selecteer het type gebruiker 1: Medewerker 2: Klant" + " 3:  alles/anders", Color.ORANGE);

		int typeNum = 0;
		boolean badResponse = false;
		do {
			try {

				typeNum = console.printResponseInt("type:", "" + (type.ordinal() + 1), Color.ORANGE);
				badResponse = false;

				if (typeNum < 1 || typeNum > UserType.values().length) {
					console.println("Foutieve invoer", Color.RED);
					badResponse = true;
				} else {
					type = UserType.values()[typeNum - 1];
				}
			} catch (NumberFormatException e) {
				console.println("Foutieve invoer, vul een getal in", Color.RED);
				badResponse = true;
			}

		} while (badResponse);

		return type;
	}
	
	
	
	private String createNewPass() {
		boolean validResponse = true;
		char[] pass;
		console.println("Voer een password in", Color.CYAN);
		do {
			 pass = console.printResponseMask("password:"
			 +", minimaal 4 characters", Color.ORANGE);
			
			 if (pass.length<4){
				 validResponse = false;
				 console.println("Onbekende invoer", Color.magenta);
			 }
		} while (!validResponse);
		String hash = null;
		try {
			hash = PassHasher.getSaltedHash(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hash;
	}
	

}
