package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.model.*;

//@Service
public class FinalSubOrderService {
    
//    @Autowired
    FinalSubOrderRepository finalSubOrderRepository;
    FinalSubOrder finalSubOrder;
    
    
    public List<FinalSubOrder> getAllFinalSubOrders(){
        List<FinalSubOrder> list = new ArrayList<>();
        finalSubOrderRepository.findAll().forEach(list::add);
        return list;
    }
    
    public FinalSubOrder getFinalSubOrder(Long id){
        return finalSubOrderRepository.findOne(id);
    }
    
    public void updateFinalSubOrder(Long id, FinalSubOrder finalSubOrder){
         finalSubOrderRepository.save(finalSubOrder);
    }
    
     public void deleteFinalSubOrder(Long id){
         finalSubOrderRepository.delete(id);
    }
     
      public void createFinalSubOrder(FinalSubOrder finalSubOrder){
         finalSubOrderRepository.save(finalSubOrder);
    }
}
