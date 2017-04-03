/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controller;

import java.awt.Color;
import java.time.LocalDate;
import main.java.daos.GenericDaoJpaImpl;
import main.java.infrastructure.ColorConsole;
import main.java.model.Adress;
import main.java.model.Cart;
import main.java.model.CartSubOrder;
import main.java.model.FinalSubOrder;
import main.java.model.Order;
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
    @Autowired
    private GenericDaoJpaImpl<Order, Long> orderDao;
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

    public void completeOrder(UserAccount user) {
        Order order = new Order();
        order.setUser(user);
        for (CartSubOrder cso : userCart.getSubOrders()) {
            FinalSubOrder fso = new FinalSubOrder(cso);
            order.addSubOrder(fso);
        }
        // TO DO: display and confirm/edit Adress
        // Gaat er nu automatisch vanuit dat userBillingAdress == orderDeliveryAdress
        order.setDeliveryAdress(user.getBillingAdress());
        order.calculateTotalPrice();
        order.setSaledate(LocalDate.now());                                
        orderDao.create(order);
        console.println("Uw bestelling is geplaatst, gefeliciteerd!", Color.RED);
    }
    
    public void copyAdress(Adress targetAdress, Adress refAdress) {
        targetAdress.setCity(refAdress.getCity());
        targetAdress.setFamilyName(refAdress.getFamilyName());
        targetAdress.setFirstName(refAdress.getFirstName());
        targetAdress.setInsertion(refAdress.getInsertion());
        targetAdress.setNumAddition(refAdress.getNumAddition());
        targetAdress.setNumber(refAdress.getNumber());
        targetAdress.setStreet(refAdress.getStreet());
        targetAdress.setZipCode(refAdress.getZipCode());
    }
}
