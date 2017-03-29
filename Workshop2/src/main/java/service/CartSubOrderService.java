package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.daos.repositories.CartSubOrderRepository;
import main.java.model.*;

//@Service
public class CartSubOrderService {
    
//    @Autowired
    CartSubOrderRepository cartSubOrderRepository;
    CartSubOrder cartSubOrder;
    
    
    public List<CartSubOrder> getAllCartSubOrders(){
        List<CartSubOrder> list = new ArrayList<>();
        cartSubOrderRepository.findAll().forEach(list::add);
        return list;
    }
    
    public CartSubOrder getCartSubOrder(Long id){
        return cartSubOrderRepository.findOne(id);
    }
    
    public void updateCartSubOrder(Long id, CartSubOrder cartSubOrder){
         cartSubOrderRepository.save(cartSubOrder);
    }
    
     public void deleteCartSubOrder(Long id){
         cartSubOrderRepository.delete(id);
    }
     
      public void createCartSubOrder(CartSubOrder cartSubOrder){
         cartSubOrderRepository.save(cartSubOrder);
    }
}
