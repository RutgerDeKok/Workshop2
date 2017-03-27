package main.java.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerBean {

	private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("webshop");
	private EntityManager em = emFactory.createEntityManager();
	
	@Bean
	public EntityManager getEntityManager(){
		return em;
	}

}
