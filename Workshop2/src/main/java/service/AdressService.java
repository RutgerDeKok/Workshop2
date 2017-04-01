package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.daos.repositories.AdressRepository;
import main.java.model.*;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class AdressService {
    
//    @Autowired
    //AdressRepository adressRepository;
    Adress adress;
    @Autowired
    @Qualifier("genericDaoJpaImpl")
    GenericDaoJpaImpl dao;
    
    
    public List<Adress> getAllAdresses(){
        return dao.findAll(Adress.class);
    }
    
    public Adress getAdress(Long id){
        return (Adress)dao.read(Adress.class, id);
    }
    
    public void updateAdress(Long id, Adress adress){
         dao.saveOrUpdate(adress);
    }
    
     public void deleteAdress(Long id){
         //moet een object meekrijgen
         //adressRepository.delete(id);
    }
     
      public void createAdress(Adress adress){
         dao.create(adress);
    }
}
