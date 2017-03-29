package main.java.presentation;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.MainController;
import main.java.controller.UserController;
import main.java.infrastructure.PassHasher;
import main.java.infrastructure.Validator;
import main.java.model.UserAccount;

@Component
public class InlogMenu {

	@Autowired
	private MainController mainController;
	@Autowired
	private UserController userController;
	@Autowired
	private ColorConsole console;

	public UserAccount Login() {

		UserAccount user = new UserAccount();

		boolean emailValid;
		String email;

		do {
			email = console.printResponse("\nvoer uw email adress in " + "\nType x, om naar het hoofmenu te gaan", "",
					Color.CYAN);
			if (isX(email))
				mainController.start();

			if (emailValid(email)) {

				user = userController.getUserByEmail(email);
				if (user != null) // user exists
					break;
			} else {
				emailValid = false;
				console.println("Onbekende invoer", Color.magenta);
			}
		} while (true);

		// check password
		char[] pass = console.printResponseMask("Voer uw password in", Color.cyan);
		if(checkPass(pass, user)){
			console.println("Gegevens correct\n", Color.MAGENTA);
			return user;
		}
		pass = console.printResponseMask("Voer uw password in, laatste kans", Color.cyan);
		if(checkPass(pass, user)){
			console.println("Gegevens correct\n", Color.MAGENTA);
			return user;
		}
		return null;
	}

	private boolean checkPass(char[] pass, UserAccount user) {
		boolean passGood = false;
		try {
			passGood = PassHasher.check(pass, user.getPassHash());
		} catch (Exception e) {e.printStackTrace();}
		return passGood;
	}



	private boolean emailValid(String s) {
		return Validator.getInstance().validateEmail(s);
	}

	private boolean isX(String s) {
		return (s.toLowerCase() == "x");
	}
}
