/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.Spring.example.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nadkine
 */
@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepos;
    
    
    public List<Product> getAllProducts(){
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setName("kaas");
        product.setPrice(new BigDecimal("3.99"));
        product.setStockCount(50);
        productRepos.findAll().forEach(list::add);
        list.add(product);
        return list;
    }
    
    public Product getProduct(String id){
        Product product = new Product();
        product.setName("kaas");
        product.setPrice(new BigDecimal("3.99"));
        product.setStockCount(50);
        return productRepos.findOne(id);
    }
    
    public void updateProduct(String id, Product product){
         productRepos.save(product);
    }
    
     public void deleteProduct(String id){
         productRepos.delete(id);
    }
     
      public void createProduct(Product product){
         productRepos.save(product);
    }
}
