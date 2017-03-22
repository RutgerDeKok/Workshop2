package main.java.daos;


import java.io.Serializable;

import javax.persistence.EntityManager;


public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> entityClass;
	
//	@PersistenceContext
	protected EntityManager entityManager;
	
	  public GenericDaoJpaImpl(Class<T> entityClass, EntityManager entityManager ) {
	        this.entityClass = entityClass;
	        this.entityManager = entityManager;
	    }


	
	public T create(T t) {
		this.entityManager.persist(t);
		return t;
	}

	public T read(PK id) {
		System.out.println(entityClass);
		System.out.println(id);
		return entityManager.find(entityClass, id);
	}

	public T saveOrUpdate(T t) {
		return this.entityManager.merge(t);
	}

	public void delete(T t) {

		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}





}