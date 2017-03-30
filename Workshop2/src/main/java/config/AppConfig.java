package main.java.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"main.java"})

public class AppConfig {
	
    @Bean
    public EntityManagerFactory emFactory() {
        return Persistence.createEntityManagerFactory("webshop");
    }
    
    /*@Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        return new LocalEntityManagerFactoryBean();
    }  */  
}
