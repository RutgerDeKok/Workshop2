package main.java.controller;

import main.java.model.*;
import main.java.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FinalSubOrderController {
    
    @Autowired
    FinalSubOrderService finalSubOrderService;
    
    public List<FinalSubOrder> getAllOrders(){
    return finalSubOrderService.getAllFinalSubOrders();
    }
    
    public FinalSubOrder getFinalSubOrder(Long id){
    return finalSubOrderService.getFinalSubOrder(id);
    }
       
    public void createFinalSubOrder(FinalSubOrder finalSubOrder){
    finalSubOrderService.createFinalSubOrder(finalSubOrder);
    }
      
    public void updateFinalSubOrder(Long id, FinalSubOrder finalSubOrder){
    finalSubOrderService.updateFinalSubOrder(id, finalSubOrder);
    }
     
    public void deleteOrder(Long id){
    finalSubOrderService.deleteFinalSubOrder(id);
    }
}