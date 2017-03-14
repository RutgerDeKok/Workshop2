package main.java.controller;

import java.math.BigDecimal;
import java.time.LocalDate;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.java.model.Adress;
import main.java.model.Cart;
import main.java.model.Product;
import main.java.model.ProductCategory;
import main.java.model.Sale;
import main.java.model.SubOrder;
import main.java.model.UserAccount;
import main.java.model.UserType;



public class WebShopMain {

	public static void main(String[] args) {

		// Create some objects to persist

				Product product1 = new Product();
				product1.setBrand("Leerdammer");
				product1.setCategory(ProductCategory.MEDIUM_HARD);
				product1.setName("Jonge Leerdammer");
				product1.setPrice(new BigDecimal("12.30")); // Let op! BigDecimal als
															// String toewijzen niet als
															// double.
				product1.setStockCount(50);

				Adress factAdress = new Adress();
				factAdress.setFirstName("Piet");
				factAdress.setCity("Stremselgat");
				factAdress.setStreet("Kaasweg");
				factAdress.setNumber(12);

				UserAccount user1 = new UserAccount();
				user1.setEmail("abc@rsvier.com");
				user1.setUserType(UserType.CUSTOMER);
				user1.setBillingAdress(factAdress);
				char[] pass = { 1, 1, 1, 1 };
				try {
					user1.setPassHash(Password.getSaltedHash(pass));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				UserAccount user2 = new UserAccount();
				user2.setEmail("efg@rsvier.com");
				user2.setUserType(UserType.EMPLOYEE);
				user2.setBillingAdress(factAdress);
				char[] pass1 = { 1, 1, 1, 1 };
				try {
					user2.setPassHash(Password.getSaltedHash(pass1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				SubOrder subOrder1 = new SubOrder();
				subOrder1.setProduct(product1);
				subOrder1.setQuantity(2);
				subOrder1.setTotalPrice(new BigDecimal("24.60"));

				SubOrder subOrder2 = new SubOrder();
				subOrder2.setProduct(product1);
				subOrder2.setQuantity(1);
				subOrder2.setTotalPrice(new BigDecimal("12.30"));

				SubOrder subOrder3 = new SubOrder();
				subOrder3.setProduct(product1);
				subOrder3.setQuantity(1);
				subOrder3.setTotalPrice(new BigDecimal("12.30"));

				Cart cart1 = new Cart();
				cart1.setUser(user1);
				cart1.addSubOrder(subOrder1);
				cart1.addSubOrder(subOrder2);
				cart1.setDeliveryAdress(factAdress);

				Cart cart2 = new Cart();
				cart2.setUser(user2);
				cart2.addSubOrder(subOrder3);
				cart2.setDeliveryAdress(factAdress);

				Sale order1 = new Sale();
				order1.setUser(user2);
				order1.addSubOrder(subOrder3);
				order1.setDeliveryAdress(factAdress);
				order1.setSaledate(LocalDate.now());
				
				Sale order2 = new Sale();
				order2.setUser(user2);
				order2.addSubOrder(subOrder1);
				order2.addSubOrder(subOrder2);
				order2.setDeliveryAdress(factAdress);
				order2.setSaledate(LocalDate.now());

				// Start Entity Manager

				EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("webshop"); // Mijn
																									// DB
																									// heet
																									// webshop
																									// !

				EntityManager em = emFactory.createEntityManager();

				// Persist Objects

				em.getTransaction().begin();
				em.persist(factAdress);
				em.persist(product1);
				em.persist(subOrder1);
				em.persist(subOrder2);
				em.persist(subOrder3);
				em.persist(user1);
				em.persist(user2);
				em.persist(cart1);
				em.persist(cart2);
				em.persist(order1);
				em.persist(order2);
				em.getTransaction().commit();

				em.clear();

				// Some test operations

				// Baas zoekBaas = em.find(Baas.class, 2L);
				// Hond dbHond = em.find(Hond.class, 2L); //find(Class<Hond>
				// entityClass, Object primaryKey)
				// System.out.println(zoekBaas);
				// System.out.println(dbHond);

				// Close manager and factory
				em.close();
				emFactory.close();
             System.exit(0);
	}

}
