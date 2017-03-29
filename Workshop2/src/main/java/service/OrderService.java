package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.daos.repositories.OrderRepository;
import main.java.model.*;

//@Service
public class OrderService {
    
//    @Autowired
    OrderRepository orderRepository;
    Order order;
    
    
    public List<Order> getAllOrders(){
        List<Order> list = new ArrayList<>();
        orderRepository.findAll().forEach(list::add);
        return list;
    }
    
    public Order getOrder(Long id){
        return orderRepository.findOne(id);
    }
    
    public void updateOrder(Long id, Order order){
         orderRepository.save(order);
    }
    
     public void deleteOrder(Long id){
         orderRepository.delete(id);
    }
     
      public void createOrder(Order order){
         orderRepository.save(order);
    }
}
