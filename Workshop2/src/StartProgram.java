


import main.java.presentation.InlogMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import main.java.daos.*;
import main.java.model.*;
import main.java.controller.*;
import main.java.service.*;

public class StartProgram {
    
    
    public static void main(String[] args){
        
        
        ApplicationContext context = new AnnotationConfigApplicationContext(main.java.config.AppConfig.class);
        
        InlogMenu inlogMenu = (InlogMenu) context.getBean("inlogMenu");
        AdressRepository adressRepository = (AdressRepository) context.getBean("adressRepository");
        CartRepository cartRepository = (CartRepository) context.getBean("cartRepository");
        CartSubOrderRepository cartSubOrderRepository = (CartSubOrderRepository) context.getBean("cartSubOrderRepository");
        FinalSubOrderRepository finalSubOrderRepository = (FinalSubOrderRepository) context.getBean("finalSubOrderRepository");
        OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository"); 
        ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
        UserAccountRepository userAccountRepository = (UserAccountRepository) context.getBean("userAccountRepository");
        AdressController adressController = (AdressController) context.getBean("adressController");
        CartController cartController = (CartController) context.getBean("cartController");
        CartSubOrderController cartSubOrderController = (CartSubOrderController) context.getBean("cartSubOrderController");
        FinalSubOrderController finalSubOrderController = (FinalSubOrderController) context.getBean("finalSubOrderController");
        OrderController orderController = (OrderController) context.getBean("orderController"); 
        ProductController productController = (ProductController) context.getBean("productController");
        UserAccountController userAccountController = (UserAccountController) context.getBean("userAccountController");
        AdressService adressService = (AdressService) context.getBean("adressService");
        CartService cartService = (CartService) context.getBean("cartService");
        CartSubOrderService cartSubOrderService = (CartSubOrderService) context.getBean("cartSubOrderService");
        FinalSubOrderService finalSubOrderService = (FinalSubOrderService) context.getBean("finalSubOrderService");
        OrderService orderService = (OrderService) context.getBean("orderService"); 
        ProductService productCService = (ProductService) context.getBean("productService");
        UserAccountService userAccountService = (UserAccountService) context.getBean("userAccountService");          
           
        inlogMenu.runPresentationConsoleInlogMenu();
       
       
        System.out.println("Einde programma");
    }
}
