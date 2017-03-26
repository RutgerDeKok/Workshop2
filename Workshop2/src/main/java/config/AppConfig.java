package main.java.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"main.java", "main.java.presentation"})

class AppConfig {
	
//	 @Bean 
//	   public GreetingBean greetingBean(){
//	      return new GreetingBean();
//	   }
    
}
