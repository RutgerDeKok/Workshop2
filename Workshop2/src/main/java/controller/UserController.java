/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.daos.UserAccountDao;
import main.java.model.Adress;
import main.java.model.UserAccount;
import main.java.presentation.CreateUserAccountMenu;

@Component
public class UserController {
	
	@Autowired
	private CreateUserAccountMenu menu;
	@Autowired
	private MainController mainController;
	@Autowired
	EntityManager em;
//	@Autowired
//	@Qualifier("useraccountdao")
//	GenericDao<UserAccount, Long> userDao;
	private UserAccountDao dao;
	

	

	public void createUserAccount() {
		
		// get Klant object back (adress can remain null at first)
		UserAccount user = menu.createUserAccount();
		
		// send Klant object to DAO to persist in DB
//		dao.create(user);
		dao.create(user);
		mainController.start();
	}
	
	public boolean checkEmailAvailable(String email) {
		
		dao = new UserAccountDao(UserAccount.class,em);
		UserAccount user = dao.findUserbyEmail(email);
		return (user==null);
	}

	public boolean validatePasswordCallService(String string, char[] charwachtwoord) {
		return true;
		// NOG NIET AF, ER IS NOG GEEN SERVICE!
	}

}
