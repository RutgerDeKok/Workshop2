/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import main.java.daos.GenericDao;
import main.java.daos.UserAccountDao;
import main.java.model.UserAccount;
import main.java.presentation.CreateUserAccountMenu;

@Component
public class UserController {
	
	@Autowired
	private CreateUserAccountMenu menu;
	@Autowired
	private MainController mainController;
//	@Autowired
//	@Qualifier("useraccountdao")
	private GenericDao<UserAccount, Long> userDao = new UserAccountDao(UserAccount.class);
	private UserAccountDao dao = (UserAccountDao)userDao;
	
	

	

	public void createUserAccount() {
		
		// get Klant object back (adress can remain null at first)
		UserAccount user = menu.createUserAccount();
		
		// send Klant object to DAO to persist in DB
		dao.create(user);
		mainController.start();
	}
	
	public boolean checkEmailAvailable(String email) {

		UserAccount user = getUserByEmail(email);
		return (user==null);
	}
	
	public UserAccount getUserByEmail(String email){
			UserAccount user = dao.findUserbyEmail(email);
			return user;
	}

	public boolean validatePasswordCallService(String string, char[] charwachtwoord) {
		return true;
		// NOG NIET AF, ER IS NOG GEEN SERVICE!
	}

}
