package main.java.controller;

import main.java.model.*;
import main.java.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CartController {
    
    @Autowired
    CartService cartService;
    
    public List<Cart> getAllCarts(){
    return cartService.getAllCarts();
    }
    
    public Cart getCart(Long id){
    return cartService.getCart(id);
    }
       
    public void createCart(Cart cart){
    cartService.createCart(cart);
    }
      
    public void updateCart(Long id, Cart cart){
    cartService.updateCart(id, cart);
    }
     
    public void deleteCart(Long id){
    cartService.deleteCart(id);
    }
}