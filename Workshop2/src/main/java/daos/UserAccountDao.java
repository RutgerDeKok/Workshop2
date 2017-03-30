package main.java.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import main.java.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDao extends GenericDaoJpaImpl<UserAccount, Long> {
	
	//@Autowired
        //EntityManager entityManager;
        
        @Autowired
        EntityManagerFactory emf;

	public UserAccountDao() {}		

	public UserAccount findUserbyEmail(String email) {

		try {
			EntityManager entityManager = emf.createEntityManager();
			System.out.println("entityman? "+entityManager);
			
			Query query = entityManager.createQuery
					("select p from UserAccount p where email = :email",
					UserAccount.class);

			query.setParameter("email", email);
			UserAccount user = (UserAccount) query.getSingleResult();
			return user;

		} catch (NoResultException ex) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

}
