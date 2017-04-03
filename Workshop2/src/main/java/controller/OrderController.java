package main.java.controller;

import main.java.model.*;
import main.java.service.*;
import java.util.List;
import main.java.daos.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    
    @Autowired
    OrderService orderService;
    @Autowired 
    OrderDao orderDao;
    
    public List<Order> getAllOrders(){
    return orderService.getAllOrders();
    }
    
    public Order getOrder(Long id){
    return orderService.getOrder(id);
    }
       
    public void createOrder(Order order){
    orderService.createOrder(order);
    }
      
    public void updateOrder(Long id, Order order){
    orderService.updateOrder(id, order);
    }
     
    public void deleteOrder(Long id){
    orderService.deleteOrder(id);
    }
    
    public List<Order> getOrdersByUserId(Long id) {
        return orderDao.findOrdersByUserId(id);
    }
}