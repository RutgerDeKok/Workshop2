package main.java.controller;

import main.java.model.*;
import main.java.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdressController {
    
    @Autowired
    AdressService adressService;
    
    public List<Adress> getAllAdresses(){
    return adressService.getAllAdresses();
    }
    
    public Adress getAdress(Long id){
    return adressService.getAdress(id);
    }
       
    public void createAdress(Adress adress){
    adressService.createAdress(adress);
    }
      
    public void updateAdress(Long id, Adress adress){
    adressService.updateAdress(id, adress);
    }
     
    public void deleteAdress(Long id){
    adressService.deleteAdress(id);
    }
}