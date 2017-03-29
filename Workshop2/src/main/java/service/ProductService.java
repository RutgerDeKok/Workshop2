package main.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.daos.*;
import main.java.daos.repositories.ProductRepository;
import main.java.model.*;
import org.springframework.stereotype.Component;

//@Service
@Component
public class ProductService {
    
    //@Autowired
    ProductRepository productRepository;
    Product product;
    
    
    public List<Product> getAllProducts(){
        List<Product> list = new ArrayList<>();
        productRepository.findAll().forEach(list::add);
        return list;
    }
    
    public Product getProduct(Long id){
        return productRepository.findOne(id);
    }
    
    public void updateProduct(Long id, Product product){
         productRepository.save(product);
    }
    
     public void deleteProduct(Long id){
         productRepository.delete(id);
    }
     
      public void createProduct(Product product){
         productRepository.save(product);
    }
}
