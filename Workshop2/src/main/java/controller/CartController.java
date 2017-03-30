package main.java.controller;

import main.java.daos.GenericDaoJpaImpl;
import main.java.model.*;
import main.java.presentation.klant.CustomerEditCartMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CartController {
    
//  @Autowired
//  CartService cartService;
	@Autowired
	private GenericDaoJpaImpl<Cart, Long> dao;// = new GenericDaoJpaImpl<>();
	@Autowired
	private CustomerEditCartMenu editCartMenu;
	
	private Cart currentCart;
    
	// get Cart from DB
    public Cart getCart(long id) { 
    	currentCart = dao.read(Cart.class, id);
    	return currentCart;
	}
    
    public Cart getCurrentCart() { 
    	return currentCart;
	}
    
    public void setCurrentCart(Cart userCart) { 
    	currentCart = userCart;
	}

	public void createCartByID(long id) {
		Cart cart = new Cart();
		cart.setId(id);
		dao.create(cart);
	}
	
	public Cart getCart(Long id) {
		return dao.read(Cart.class, id);
	}
	
	public void updateCart(Cart cart) {
		dao.saveOrUpdate(cart);
	}

	public void runMainCartMenu() {
		editCartMenu.runMenu();
	}
    
//    public List<Cart> getAllCarts(){
//    return cartService.getAllCarts();
//    }
//    
//    public Cart getCart(Long id){
//    return cartService.getCart(id);
//    }
//       
//    public void createCart(Cart cart){
//    cartService.createCart(cart);
//    }
//      
//    public void updateCart(Long id, Cart cart){
//    cartService.updateCart(id, cart);
//    }
//     
//    public void deleteCart(Long id){
//    cartService.deleteCart(id);
//    }
}