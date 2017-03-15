package main.java.daos;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> entityClass;
	
//	@PersistenceContext
	protected EntityManager entityManager;
	
	  public GenericDaoJpaImpl(Class<T> entityClass, EntityManager entityManager ) {
	        this.entityClass = entityClass;
	        this.entityManager = entityManager;
	    }


//	@SuppressWarnings("unchecked")
//	public GenericDaoJpaImpl() {
//		
////		ParameterizedType genericSuperclass =  (ParameterizedType) getClass().getGenericSuperclass();
////		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
//		
//		Type sooper = T.getClass();
//		System.out.println(sooper);
//		this.entityClass = (Class<T>)((ParameterizedType) sooper).getActualTypeArguments()[ 0 ];
//		
////		Type mySuperclass = myFoo.getClass().getGenericSuperclass();
////		Type tType = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];
//	}
	
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