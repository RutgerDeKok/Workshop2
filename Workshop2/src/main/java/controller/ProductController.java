package main.java.controller;

import main.java.model.Product;
import main.java.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//@Controller
public class ProductController {
    
//    @Autowired
    ProductService productService;
    
    public List<Product> getAllProducts(){
    return productService.getAllProducts();
    }
    
    public Product getProduct(Long id){
    return productService.getProduct(id);
    }
       
    public void createProduct(Product product){
    productService.createProduct(product);
    }
      
    public void updateProduct(Long id, Product product){
    productService.updateProduct(id, product);
    }
     
    public void deleteProduct(Long id){
    productService.deleteProduct(id);
    }

   
}