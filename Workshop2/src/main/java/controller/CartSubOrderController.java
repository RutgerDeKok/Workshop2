package main.java.controller;

import main.java.model.*;
import main.java.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class CartSubOrderController {
    
    @Autowired
    CartSubOrderService cartSubOrderService;
    
    public List<CartSubOrder> getAllCartSubOrders(){
    return cartSubOrderService.getAllCartSubOrders();
    }
    
    public CartSubOrder getCartSubOrder(Long id){
    return cartSubOrderService.getCartSubOrder(id);
    }
       
    public void createCartSubOrder(CartSubOrder cartSubOrder){
    cartSubOrderService.createCartSubOrder(cartSubOrder);
    }
      
    public void updateCartSubOrder(Long id, CartSubOrder cartSubOrder){
    cartSubOrderService.updateCartSubOrder(id, cartSubOrder);
    }
     
    public void deleteCartSubOrder(Long id){
    cartSubOrderService.deleteCartSubOrder(id);
    }
}