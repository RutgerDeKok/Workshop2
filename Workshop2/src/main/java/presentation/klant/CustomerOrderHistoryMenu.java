/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation.klant;

import static java.lang.System.console;
import java.util.List;
import main.java.controller.MainController;
import main.java.controller.OrderController;
import main.java.infrastructure.ColorConsole;
import main.java.model.Order;
import main.java.presentation.DisplayOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nadkine
 */

@Component
public class CustomerOrderHistoryMenu implements DisplayOrders {
    
    //Voor Jurjen
    @Autowired
    private MainController mainController;
    @Autowired
    private OrderController orderController;
    @Autowired
    private ColorConsole console;
    
    public void runMenu() {
        long userId = mainController.getCurrentUser().getId();
        List<Order> orders = orderController.getOrdersByUserId(userId);        
        displayOrders(console, orders);
    }
}
