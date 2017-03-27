package main.java.daos;


import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

//@Component
public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> entityClass;
	
//	@PersistenceContext
	protected EntityManager entityManager;
	
	  public GenericDaoJpaImpl(Class<T> entityClass, EntityManager entityManager ) {
	        this.entityClass = entityClass;
	        this.entityManager = entityManager;
	    }


	
	public T create(T t) {
		System.out.println("creating new record for "+t);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(t);
		this.entityManager.getTransaction().commit();
		this.entityManager.clear();
		return t;
	}

	public T read(PK id) {
		this.entityManager.getTransaction().begin();
		T result = this.entityManager.find(entityClass, id);
		this.entityManager.getTransaction().commit();
		this.entityManager.clear();
		
		return result;
	}

	public T saveOrUpdate(T t) {
		
		this.entityManager.getTransaction().begin();
		T result = this.entityManager.merge(t);
		this.entityManager.getTransaction().commit();
		this.entityManager.clear();
		
		return result;
	}

	public void delete(T t) {
		
		this.entityManager.getTransaction().begin();
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
		this.entityManager.getTransaction().commit();
		this.entityManager.clear();
	}

	



}