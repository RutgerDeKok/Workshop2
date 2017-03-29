package main.java.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"main.java"})

class AppConfig {
	
    @Bean
    public EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("javabook");
        return factory.createEntityManager();
    }
    
}
