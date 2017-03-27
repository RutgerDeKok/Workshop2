package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.model.*;

@Service
public class UserAccountService {
    
    @Autowired
    UserAccountRepository userAccountRepository;
    UserAccount account;
    
    
    public List<UserAccount> getAllAccounts(){
        List<UserAccount> list = new ArrayList<>();
        userAccountRepository.findAll().forEach(list::add);
        list.add(account);
        return list;
    }
    
    public UserAccount getAccount(Long id){
        return userAccountRepository.findOne(id);
    }
    
    public void updateAccount(Long id, UserAccount account){
         userAccountRepository.save(account);
    }
    
     public void deleteAccount(Long id){
         userAccountRepository.delete(id);
    }
     
      public void createAccount(UserAccount account){
         userAccountRepository.save(account);
    }
}
