package main.java.controller;

import main.java.model.*;
import main.java.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class UserAccountController {
    
//    @Autowired
    UserAccountService userService;
    
    public List<UserAccount> getAllAccounts(){
    return userService.getAllAccounts();
    }
    
    public UserAccount UserAccount(Long id){
    return userService.getAccount(id);
    }
       
    public void createUserAccount(UserAccount UserAccount){
    userService.createAccount(UserAccount);
    }
      
    public void updateUserAccount(Long id, UserAccount userAccount){
    userService.updateAccount(id, userAccount);
    }
     
    public void deleteUserAccount(Long id){
    userService.deleteAccount(id);
    }

    
    //Deze methode moest gemaakt worden, omdat hij al gebruikt wordt
    public void createAccount(String email, String hash, Adress factuurAdress) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}