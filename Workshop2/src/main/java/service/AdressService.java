package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.daos.repositories.AdressRepository;
import main.java.model.*;

//@Service
public class AdressService {
    
//    @Autowired
    AdressRepository adressRepository;
    Adress adress;
    
    
    public List<Adress> getAllAdresses(){
        List<Adress> list = new ArrayList<>();
        adressRepository.findAll().forEach(list::add);
        return list;
    }
    
    public Adress getAdress(Long id){
        return adressRepository.findOne(id);
    }
    
    public void updateAdress(Long id, Adress adress){
         adressRepository.save(adress);
    }
    
     public void deleteAdress(Long id){
         adressRepository.delete(id);
    }
     
      public void createAdress(Adress adress){
         adressRepository.save(adress);
    }
}
