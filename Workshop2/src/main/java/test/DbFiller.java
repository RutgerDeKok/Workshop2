/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.test;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        //dbFiller.dummyCustomers();
        //dbFiller.dummyEmployees();
        dbFiller.dummyOrders();        
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

    void dummyOrders() {
        for (int i = 0; i <= 10; i++) {
            DbFiller dbFiller = new DbFiller();
            dbFiller.em.getTransaction().begin();
            Product product = new Product();
            product.setBrand("La vache qui rit");
            product.setCategory(ProductCategory.CREAM);
            product.setInfo("Hahahaha hmmmmmmm");
            product.setName("La vache qui dit");
            product.setPrice(BigDecimal.ONE);
            product.setStockCount(500);
            dbFiller.em.persist(product);
            dbFiller.em.getTransaction().commit();
            dbFiller.em.getTransaction().begin();
            CartSubOrder cartSubOrder = new CartSubOrder();
            cartSubOrder.setProduct(product);
            cartSubOrder.setQuantity(i);
            cartSubOrder.setSubTotal(BigDecimal.ONE, i);
            dbFiller.em.persist(cartSubOrder);
            dbFiller.em.getTransaction().commit();
            dbFiller.em.getTransaction().begin();
            Adress delAdress = new Adress();
		delAdress.setFirstName("Piet");
		delAdress.setInsertion("de");
		delAdress.setFamilyName("Boer");
		delAdress.setCity("Stremselgat");
		delAdress.setStreet("Kaasweg");
		delAdress.setNumber(12);
            dbFiller.em.persist(delAdress);
            dbFiller.em.getTransaction().commit();
            dbFiller.em.getTransaction().begin();   
            FinalSubOrder subOrder = new FinalSubOrder(cartSubOrder);
            //dbFiller.em.persist(subOrder);
            //dbFiller.em.getTransaction().commit();
            //dbFiller.em.getTransaction().begin();   
            Order order = new Order();
            order.addSubOrder(subOrder);                
            order.setDeliveryAdress(delAdress);
            order.setSaledate(LocalDate.now());
            order.calculateTotalPrice();
            dbFiller.em.persist(order);
            dbFiller.em.getTransaction().commit();
            dbFiller.em.getTransaction().begin();   
        }
    }
}
