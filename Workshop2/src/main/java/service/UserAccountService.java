package main.java.service;

import java.util.ArrayList;
import java.util.List;
import main.java.daos.repositories.UserAccountRepository;
import main.java.model.*;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class UserAccountService {
    
//    @Autowired
    UserAccountRepository userAccountRepository;
    
    @Autowired
    CurrentUser currentUser;
    
    public UserAccount getCurrentUser() {
        return currentUser.getCurrentUser();
    }
    
    public List<UserAccount> getAllAccounts(){
        List<UserAccount> list = new ArrayList<>();
        userAccountRepository.findAll().forEach(list::add);
        //list.add(account);
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
