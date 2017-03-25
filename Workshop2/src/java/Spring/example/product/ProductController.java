/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.Spring.example.product;

import com.example.Product;
import com.example.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Nadkine
 */
@RestController
public class ProductController {
    
    @Autowired
    ProductService productService;
    
   
    @RequestMapping("/product")
    public List<Product> getAllProducts(){
    return productService.getAllProducts();
    }
    
     @RequestMapping("/product/{id}")
    public Product getProduct(@PathVariable String id){
    return productService.getProduct(id);
    }
    
     @RequestMapping(method=RequestMethod.POST, value="/product")
    public void createProduct(@RequestBody Product product){
    productService.createProduct(product);
    }
     /*
     @RequestMapping("/product{id}")
    public void updateProduct(@PathVariable String id, Product product){
    productService.updateProduct(id, product);
    }
    
     @RequestMapping("/product{id}")
    public void deleteProduct(@PathVariable String id){
    productService.deleteProduct(id);
    }*/
    
}
