/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.test;

import main.java.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import main.java.infrastructure.PassHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jurjen
 */

//@Component
public class DbFiller {
    
    //@Autowired
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
    private EntityManager em = emf.createEntityManager();
    
    public static void main(String[] args) {
        DbFiller dbFiller = new DbFiller();
        dbFiller.persistUserAccount();
        
    }  
    
    void dummyCustomers() {
        for (int i = 0; i <= 10; i++) {
            DbFiller dbFiller = new DbFiller();
            dbFiller.em.getTransaction().begin();
            UserAccount user = new UserAccount();
            user.setEmail("c"+i+"@c.nl");
            user.setUserType(UserType.CUSTOMER);
                Adress factAdress = new Adress();
		factAdress.setFirstName("Piet");
		factAdress.setInsertion("de");
		factAdress.setFamilyName("Boer");
		factAdress.setCity("Stremselgat");
		factAdress.setStreet("Kaasweg");
		factAdress.setNumber(12);
            user.setBillingAdress(factAdress);
            char[] pass = { 1, 1, 1, 1 };
            try {
                    user.setPassHash(PassHasher.getSaltedHash(pass));
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            dbFiller.em.persist(factAdress);
            dbFiller.em.persist(user);
            dbFiller.em.getTransaction().commit();
        }
    }
    
    void dummyEmployees() {
        for (int i = 0; i <= 10; i++) {
            DbFiller dbFiller = new DbFiller();
            dbFiller.em.getTransaction().begin();
            UserAccount user = new UserAccount();
            user.setEmail("e"+i+"@e.nl");
            user.setUserType(UserType.EMPLOYEE);
                Adress factAdress = new Adress();
		factAdress.setFirstName("Piet");
		factAdress.setInsertion("de");
		factAdress.setFamilyName("Boer");
		factAdress.setCity("Stremselgat");
		factAdress.setStreet("Kaasweg");
		factAdress.setNumber(12);
            user.setBillingAdress(factAdress);
            char[] pass = { 1, 1, 1, 1 };
            try {
                    user.setPassHash(PassHasher.getSaltedHash(pass));
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            dbFiller.em.persist(factAdress);
            dbFiller.em.persist(user);
            dbFiller.em.getTransaction().commit();
        }
    }
    
}
