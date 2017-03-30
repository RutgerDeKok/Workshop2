/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.daos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import main.java.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author Frank
 */
@Component
public class ProductUniqueDao extends GenericDaoJpaImpl<Product, Long> {
    
    @Autowired
    EntityManagerFactory emf;
    
    public ProductUniqueDao() {}
    
    public List<Product> getAllProducts() {

		
		
    EntityManager entityManager = emf.createEntityManager();
			 System.out.println("entityman? "+entityManager);
                         try{
			
			Query query = entityManager.createQuery
					("select p from Product p ", Product.class);

			//query.setParameter("email", email);
                      //  List<Product> myList = new ArrayList<Product>();
			// List<Product> aProduct = (Product) query.getResultList();
                        ArrayList<Product> entries = (ArrayList<Product>) query.getResultList();
                        
			//return (List<Product>) aProduct;
                        return entries;

		} catch (NoResultException ex) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
    
    
    
    
    
    
    
}
}
