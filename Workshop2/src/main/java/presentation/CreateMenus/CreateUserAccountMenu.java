package main.java.presentation.CreateMenus;

import main.java.infrastructure.ColorConsole;
import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.UserController;
import main.java.infrastructure.MainController;
import main.java.infrastructure.PassHasher;
import main.java.infrastructure.Validator;
import main.java.model.UserAccount;
import main.java.model.UserType;

@Component
public class CreateUserAccountMenu {

	@Autowired
	private MainController mainController;
	@Autowired
	private UserController userController;
	@Autowired
	private ColorConsole console;

	public UserAccount createUserAccount() {

		UserAccount user = new UserAccount();
		user.setUserType(UserType.CUSTOMER);

		// get a valid user email adress

		boolean emailValid;
		String email;

		do {
			email = console.printResponse("\nvoer uw email adress in, dit wordt uw login naam \n", "", Color.CYAN);

			emailValid = Validator.getInstance().validateEmail(email);
			if (!emailValid)
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
		} while (!emailValid);
		console.println("Uw email keuze is: " + email, Color.cyan);

		// check of email nog beschikbaar is

		boolean isAvailable = userController.checkEmailAvailable(email);
		System.out.println("email isavailable?: " + isAvailable);
		if (!isAvailable) {
			console.println("\nDit email adres heeft reeds een account\n", Color.magenta);
			mainController.start();
		}

		user.setEmail(email);

		// get a valid user email password

		String hash = null;
		boolean passValid = false;
		console.print("\nVoer een wachtwoord in van minimaal 4 charachters: ", Color.CYAN);
		do {

			char[] wachtwoord = console.printResponseMask("", Color.CYAN);
			if (String.valueOf(wachtwoord).equals("Timeout!")) {
				console.print("\nVoer een wachtwoord in van minimaal 4 charachters: ", Color.CYAN);

			} else if (wachtwoord.length > 3) {
				passValid = true;

				try {
					hash = PassHasher.getSaltedHash(wachtwoord);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				console.println("Ongeldige invoer, probeer opnieuw.", Color.magenta);
			}
		} while (!passValid);

		console.println("Dank U, uw account wordt aangemaakt", Color.cyan);
		console.println("U kunt nu inloggen", Color.orange);
		
		user.setPassHash(hash);

		return user;
	}

}
