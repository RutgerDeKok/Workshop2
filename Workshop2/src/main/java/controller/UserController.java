
package main.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.daos.UserAccountDao;
import main.java.model.UserAccount;
import main.java.model.UserType;
import main.java.presentation.CreateMenus.CreateUserAccountMenu;
import main.java.presentation.EmployeeAccountsMenu;
import main.java.presentation.MainMenu;

@Component
public class UserController {

	@Autowired
	private MainMenu mainMenu;
	@Autowired
	private CreateUserAccountMenu menu;
	@Autowired
	private EmployeeAccountsMenu empAccMenu;
	@Autowired
	private UserAccountDao dao;
	@Autowired
	private CartController cartController;

	private List<UserAccount> users;
	private List<UserAccount> filteredUsers; // filter by UserType

	public void updateUserAccount(UserAccount user) {
		dao.saveOrUpdate(user);
	}

	public void createUserAccount() {

		// get Klant object back (adress can remain null at first)
		UserAccount user = menu.createUserAccount();

		// send Klant object to DAO to persist in DB
		// and get back the assigned id
		long id = dao.create(user).getId();

		// create cart with the same id
		cartController.createCartByID(id);
		mainMenu.runStartMenu();
	}

	public List<UserAccount> getAllUsers() {

		return dao.findAll(UserAccount.class);
	}

	public void createUser() {

		UserAccount user = empAccMenu.createUpdateUser(new UserAccount());
		dao.create(user);
		runEmployeeAccountsMenuUpdateFromDB();
	}

	public void updateUser(UserAccount updatedUser) {
		updatedUser = empAccMenu.createUpdateUser(updatedUser);
		dao.saveOrUpdate(updatedUser);
		empAccMenu.runMenu();
		// runEmployeeAccountsMenuUpdateFromDB();
	}

	public boolean checkEmailAvailable(String email) {

		UserAccount user = getUserByEmail(email);
		return (user == null);
	}

	public UserAccount getUserByEmail(String email) {
		UserAccount user = dao.findUserbyEmail(email);
		return user;
	}

	public void deleteAccount(UserAccount a) {
		dao.delete(a);
		runEmployeeAccountsMenuUpdateFromDB();
	}

	public void runEmployeeAccountsMenuUpdateFromDB() {
		users = getAllUsers();
		filteredUsers = new ArrayList<>(users);
		empAccMenu.runMenu();

	}

	public void filterAccountsByType(UserType filterType) {
		filteredUsers.clear();
		for (UserAccount user : users) {
			if (filterType == UserType.ALL || user.getUserType() == filterType) {
				filteredUsers.add(user);
			}
		}

	}

	public List<UserAccount> getFilteredUsers() {
		return filteredUsers;

	}

}
