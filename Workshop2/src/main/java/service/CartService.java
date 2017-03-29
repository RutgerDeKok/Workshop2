package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.daos.repositories.CartRepository;
import main.java.model.*;

//@Service
public class CartService {
    
//    @Autowired
    CartRepository cartRepository;
    Cart cart;
    
    
    public List<Cart> getAllCarts(){
        List<Cart> list = new ArrayList<>();
        cartRepository.findAll().forEach(list::add);
        return list;
    }
    
    public Cart getCart(Long id){
        return cartRepository.findOne(id);
    }
    
    public void updateCart(Long id, Cart cart){
         cartRepository.save(cart);
    }
    
     public void deleteCart(Long id){
         cartRepository.delete(id);
    }
     
      public void createCart(Cart cart){
         cartRepository.save(cart);
    }
}
