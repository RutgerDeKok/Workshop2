package main.java.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	//protected Class<T> entityClass;
	
	/*
	 * trying to get the EntityManagerFactory in here directly autowired does not work
	 * trying by creating MainController . getEntityManagerFactory() Autowired controller
	 *  does not work
	 */
    
        //@Autowired does not work
	//protected MainController controller = new MainController();
	@Autowired
        EntityManagerFactory emf;
        //@Autowired
        //EntityManager entityManager;

        public GenericDaoJpaImpl() {}
        
        @Override
	public T create(T t) {
		System.out.println("creating new record for " + t);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		entityManager.clear();
		return t;
	}
        
        @Override
	public T read(Class<T> entityClass, PK id) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		T result = entityManager.find(entityClass, id);
		entityManager.getTransaction().commit();
		entityManager.clear();
		return result;
	}
        
        @Override
	public T saveOrUpdate(T t) {
                EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		T result = entityManager.merge(t);
		entityManager.getTransaction().commit();
		entityManager.clear();
                return result;
	}
        
        @Override
	public void delete(T t) {
                EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		t = entityManager.merge(t);
		entityManager.remove(t);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}

        @Override
        public List<T> findAll(Class<T> entityClass) {
            EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();        
            List list = entityManager.createQuery("from" + entityClass.getName()).getResultList();
            entityManager.getTransaction().commit();
            return list;
        }
}
    