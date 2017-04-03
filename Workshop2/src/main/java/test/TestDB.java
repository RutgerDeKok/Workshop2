package main.java.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.daos.GenericDao;
import main.java.infrastructure.PassHasher;
import main.java.model.Adress;
import main.java.model.Cart;
import main.java.model.CartSubOrder;
import main.java.model.FinalSubOrder;
import main.java.model.Product;
import main.java.model.ProductCategory;
import main.java.model.Order;
import main.java.model.UserAccount;
import main.java.model.UserType;

@Component
public class TestDB {
	
    @Autowired
    GenericDao<UserAccount, Long> userDao;
    
    public static void main(String[] args) {
        populateDB();
    }
	

	public static void populateDB() {

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
		factAdress.setInsertion("de");
		factAdress.setFamilyName("Boer");
		factAdress.setCity("Stremselgat");
		factAdress.setStreet("Kaasweg");
		factAdress.setNumber(12);

		UserAccount user1 = new UserAccount();
		user1.setEmail("abc@rsvier.com");
		user1.setUserType(UserType.CUSTOMER);
		user1.setBillingAdress(factAdress);
		char[] pass = { 1, 1, 1, 1 };
		try {
			user1.setPassHash(PassHasher.getSaltedHash(pass));
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
			user2.setPassHash(PassHasher.getSaltedHash(pass1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CartSubOrder subOrder1 = new CartSubOrder();
		subOrder1.setProduct(product1);
		subOrder1.setQuantity(2);
		subOrder1.setTotalPrice(new BigDecimal("24.60"));

		CartSubOrder subOrder2 = new CartSubOrder();
		subOrder2.setProduct(product1);
		subOrder2.setQuantity(1);
		subOrder2.setTotalPrice(new BigDecimal("12.30"));

		CartSubOrder subOrder3 = new CartSubOrder();
		subOrder3.setProduct(product1);
		subOrder3.setQuantity(1);
		subOrder3.setTotalPrice(new BigDecimal("12.30"));

		Cart cart1 = new Cart();
		cart1.setUser(user1);
		cart1.addSubOrder(subOrder1);
		cart1.addSubOrder(subOrder2);
		cart1.setDeliveryAdress(factAdress);
		cart1.setTotalPrice(new BigDecimal("99.99"));

		Cart cart2 = new Cart();
		cart2.setUser(user2);
		cart2.addSubOrder(subOrder3);
		cart2.setDeliveryAdress(factAdress);
		cart2.setTotalPrice(new BigDecimal("99.99"));

		FinalSubOrder finalSub1 = new FinalSubOrder(subOrder1);
		FinalSubOrder finalSub2 = new FinalSubOrder(subOrder2);
		FinalSubOrder finalSub3 = new FinalSubOrder(subOrder3);

		Order order1 = new Order();
		order1.setUser(user2);
		order1.addSubOrder(finalSub3);
		order1.setDeliveryAdress(factAdress);
		order1.setSaledate(LocalDate.now());
		order1.setTotalPrice(new BigDecimal("99.99"));

		Order order2 = new Order();
		order2.setUser(user2);
		order2.addSubOrder(finalSub1);
		order2.addSubOrder(finalSub2);
		order2.setDeliveryAdress(factAdress);
		order2.setSaledate(LocalDate.now());
		order2.setTotalPrice(new BigDecimal("99.99"));

		

		// create concrete Dao
                //GenericDao<UserAccount, Long> userDao; // = new GenericDaoJpaImpl<>();

		// Persist Objects

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("webshop");
		EntityManager em =  emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(factAdress);
		em.persist(product1);
		em.persist(subOrder1);
		em.persist(subOrder2);
		em.persist(subOrder3);
		em.persist(finalSub1);
		em.persist(finalSub2);
		em.persist(finalSub3);
		em.persist(user1);
		em.persist(user2);
		
		em.persist(cart1);
                //em.persist(cart2);
		em.persist(order1);
		em.persist(order2);
		em.getTransaction().commit();
		em.clear();

		
		//System.out.println(userDao.read(UserAccount.class, 1L).getBillingAdress().getFamilyName());

		// Some test operations
		
		UserAccount ua= em.find(UserAccount.class, 1L);
		System.out.println(ua.getEmail());
		// Baas zoekBaas = em.find(Baas.class, 2L);
		// Hond dbHond = em.find(Hond.class, 2L); //find(Class<Hond>
		// entityClass, Object primaryKey)
		// System.out.println(zoekBaas);
		// System.out.println(dbHond);

		// Close manager and factory
//		em.close();
//		emFactory.close();
//		
	}

}