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

/**
 *
 * @author jurjen
 */

//@Component
public class DbFiller {
    
    //@Autowired
    static EntityManagerFactory emf; // = Persistence.createEntityManagerFactory("webshop");
    static EntityManager em; // = emf.createEntityManager();
    
    public static void main(String[] args) {
        setEntityManager();
        em.getTransaction().begin();
        dummyCustomers();
        dummyEmployees();
        em.getTransaction().commit();
        System.exit(0);
    }  
    
    static void dummyCustomers() {
        for (int i = 0; i <= 1; i++) {
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
            factAdress.setZipCode("1234 AA");
            user.setBillingAdress(factAdress);
            char[] pass = { 1, 1, 1, 1 };
            try {
                user.setPassHash(PassHasher.getSaltedHash(pass));
            } catch (Exception e) {
                e.getMessage();
            }
            em.persist(factAdress);
            em.persist(user);
            // Dummy orders aan de customer toevoegen
            dummyOrders(user, factAdress);
        }
    }
    
    static void dummyEmployees() {
        for (int i = 0; i <= 1; i++) {
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
            factAdress.setZipCode("4321 ZZ");
            user.setBillingAdress(factAdress);
            char[] pass = { 1, 1, 1, 1 };
            try {
                    user.setPassHash(PassHasher.getSaltedHash(pass));
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            em.persist(factAdress);
            em.persist(user);
        }
    }

    static void dummyOrders(UserAccount user, Adress adress) {
        for (int i = 0; i <= 1; i++) {
            Product product = new Product();
            product.setBrand("La vache qui rit");
            product.setCategory(ProductCategory.CREAM);
            product.setInfo("Hahahaha hmmmmmmm");
            product.setName("La vache qui dit");
            product.setPrice(BigDecimal.ONE);
            product.setStockCount(500);
            em.persist(product);            
            CartSubOrder cartSubOrder = new CartSubOrder();
            cartSubOrder.setProduct(product);
            cartSubOrder.setQuantity(i);
            cartSubOrder.setSubTotal(BigDecimal.ONE, i);
            System.out.println("CHECK SUBORDER TOTAL:" + cartSubOrder.getTotalPrice());
            em.persist(cartSubOrder);
            Cart cart = new Cart();
            cart.setId(user.getId());
            cart.setUser(user);
            cart.setDeliveryAdress(adress);
            cart.addSubOrder(cartSubOrder);            
            cart.calculateTotalPrice();
            System.out.println("CHECK CART TOTAL:" + cart.getTotalPrice());
            em.merge(cart);
            FinalSubOrder subOrder = new FinalSubOrder(cartSubOrder);
            Order order = new Order();
            order.addSubOrder(subOrder);                
            order.setDeliveryAdress(adress);
            order.setSaledate(LocalDate.now());
            order.calculateTotalPrice();
            order.setUser(user);
            em.persist(order);
        }
    }

    static void setEntityManager() {
        emf = Persistence.createEntityManagerFactory("webshop");
        em = emf.createEntityManager();
    }
}
