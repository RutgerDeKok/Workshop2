
package main.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.daos.UserAccountDao;
import main.java.model.Cart;
import main.java.model.UserAccount;
import main.java.presentation.CreateMenus.CreateUserAccountMenu;
import main.java.presentation.MainMenu;

@Component
public class UserController {
	
        @Autowired
        private MainMenu mainMenu;
	@Autowired
	private CreateUserAccountMenu menu;
	@Autowired
	private MainController mainController;
	@Autowired
	private UserAccountDao dao;// = new UserAccountDao();
	
	@Autowired
	private CartController cartController;

	

	public void createUserAccount() {
		
		// get Klant object back (adress can remain null at first)
		UserAccount user = menu.createUserAccount();
		
		// send Klant object to DAO to persist in DB
		// and get back the assigned id
		long id  = dao.create(user).getId();
		
		// create cart with the same id
		cartController.createCartByID(id);
		mainMenu.runStartMenu();
	}
	
	
	
	public boolean checkEmailAvailable(String email) {

		UserAccount user = getUserByEmail(email);
		return (user==null);
	}
	
	public UserAccount getUserByEmail(String email){
			UserAccount user = dao.findUserbyEmail(email);
			return user;
	}



	

	



}
