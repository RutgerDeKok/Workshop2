package main.java.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.java.infrastructure.MainController;

//@Component
public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> entityClass;
	
	/*
	 * trying to get the EntityManagerFactory in here directly autowired does not work
	 * trying by creating MainController . getEntityManagerFactory() Autowired controller
	 *  does not work
	 */
//	@Autowired does not work
	protected MainController controller = new MainController();
	
//	 @Autowire does not work
	 protected EntityManagerFactory emf = controller.getEntityManagerFactory();



	public GenericDaoJpaImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T create(T t) {
		System.out.println("creating new record for " + t);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		entityManager.clear();
		return t;
	}

	public T read(PK id) {
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		T result = entityManager.find(entityClass, id);
		entityManager.getTransaction().commit();
		entityManager.clear();
		

		return result;
	}

	public T saveOrUpdate(T t) {

		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		T result = entityManager.merge(t);
		entityManager.getTransaction().commit();
		entityManager.clear();

		return result;
	}

	public void delete(T t) {

		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		t = entityManager.merge(t);
		entityManager.remove(t);
		entityManager.getTransaction().commit();
		entityManager.clear();
	}


}