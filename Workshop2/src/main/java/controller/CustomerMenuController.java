/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import main.java.daos.GenericDaoJpaImpl;
import main.java.infrastructure.ColorConsole;
import main.java.model.Cart;
import main.java.model.UserAccount;
import main.java.presentation.DisplayCart;
import main.java.presentation.DisplayOrders;
import main.java.presentation.DisplayProducts;
import main.java.presentation.MainMenu;
import main.java.presentation.klant.CustomerCheckOutMenu;
import main.java.presentation.klant.CustomerEditCartMenu;
import main.java.presentation.klant.CustomerOrderHistoryMenu;
import main.java.presentation.klant.CustomerProductMenu;
import main.java.presentation.klant.CustomerProfileMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jurjen
 */

@Controller
public class CustomerMenuController implements DisplayCart, DisplayOrders, DisplayProducts {
    
    @Autowired
    private MainMenu mainMenu;
    @Autowired
    private ColorConsole console;
    @Autowired
    private MainController mainController;
    @Autowired
    CustomerProductMenu productMenu;
    @Autowired
    CustomerEditCartMenu editCartMenu;
    @Autowired
    CustomerOrderHistoryMenu orderHistoryMenu;
    @Autowired
    CustomerCheckOutMenu checkOutMenu;
    @Autowired
    CustomerProfileMenu profileMenu;
    @Autowired
    private GenericDaoJpaImpl<Cart, Long> cartDao;    
    Cart userCart;
    
    public UserAccount getCurrentUser() {
        return mainController.getCurrentUser();
    }
    
    public Cart getCart() {
        System.out.println("User: " + getCurrentUser().toString());
        userCart = cartDao.read(Cart.class, getCurrentUser().getId());
        System.out.println("Cart: " + userCart.toString());
        return userCart;
    }
    
    public void displayCart(Cart userCart) {
        displayCart(console, userCart);
    }
    
    // save to database
    public void persistCart(Cart currentCart) {
        cartDao.saveOrUpdate(userCart);
    }
    
    // temporary save
    public void saveCart(Cart currentCart) {
        userCart = currentCart;
    }
    
    public void runMainMenu() {
        mainMenu.runStartMenu();
    }

    public void runProductMenu() {
        productMenu.runMenu();
    }

    public void runEditCartMenu() {
        editCartMenu.runMenu();
    }

    public void runCheckOutMenu() {
        checkOutMenu.runMenu();
    }

    public void runOrderHistoryMenu() {
        orderHistoryMenu.runMenu();
    }

    public void runProfileMenu() {
        profileMenu.runMenu();
    }
}
