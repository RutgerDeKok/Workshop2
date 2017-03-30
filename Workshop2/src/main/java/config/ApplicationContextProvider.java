//package main.java.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class ApplicationContextProvider implements ApplicationContextAware {
//
//	// private static ApplicationContext context;
//	private static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//	public ApplicationContext getApplicationContext() {
//		return context;
//	}
//
//	@Override
//	public void setApplicationContext(ApplicationContext ac) throws BeansException {
//		context = ac;
//	}
//}
