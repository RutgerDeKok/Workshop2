package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.daos.repositories.CartSubOrderRepository;
import main.java.model.*;

@Service
public class CartSubOrderService {
    
    //@Autowired
   // CartSubOrderRepository cartSubOrderRepository;
    CartSubOrder cartSubOrder;
    @Autowired
    GenericDaoJpaImpl genericDaoJpaImpl;
    
    
    public List<CartSubOrder> getAllCartSubOrders(){
        return genericDaoJpaImpl.findAll(CartSubOrder.class);   
    }
    
    public CartSubOrder getCartSubOrder(Long id){
        return (CartSubOrder) genericDaoJpaImpl.read(CartSubOrder.class, id);
    }
    
    public void updateCartSubOrder(CartSubOrder cartSubOrder){
             genericDaoJpaImpl.saveOrUpdate(cartSubOrder);
    }
    
     public void deleteCartSubOrder(Long id){
            genericDaoJpaImpl.delete(genericDaoJpaImpl.read(CartSubOrder.class, id));
    }
     
      public void createCartSubOrder(CartSubOrder cartSubOrder){
         genericDaoJpaImpl.create(cartSubOrder);
    }
}
