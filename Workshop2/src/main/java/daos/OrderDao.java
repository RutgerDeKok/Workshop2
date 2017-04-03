/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.daos;

import java.util.List;
import static javafx.beans.binding.Bindings.select;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import main.java.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jurjen
 */
@Component
public class OrderDao extends GenericDaoJpaImpl<Order, Long> {
	        
        @Autowired
        EntityManagerFactory emf;

	public OrderDao() {}
    
        public List<Order> findOrdersByUserId(Long id) {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("select o from Order o where user_id = :id");
            query.setParameter("id", id);
            return query.getResultList();
        }
}
