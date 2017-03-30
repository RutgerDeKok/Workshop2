package main.java.controller;

import main.java.daos.GenericDao;
import main.java.daos.GenericDaoJpaImpl;
import main.java.model.Product;

import main.java.presentation.EmployeeProductMenu;
import main.java.service.ProductService;

import java.util.List;
import main.java.daos.ProductUniqueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ProductController {
//	@Autowired
//	private CreateProductMenu createProductMenu;
	@Autowired
	private GenericDao<Product, Long> dao;

//        private  ProductUniqueDao pdao = new ProductUniqueDao();
	
    
    @Autowired
    ProductService productService;
    @Autowired
    EmployeeProductMenu epm;
    
//    public List<Product> getAllProducts(){
//        
//    return productService.getAllProducts();
//    }
    
    public Product getProduct(Long id){
    return productService.getProduct(id);
    }
       
//    public void createProduct(Product product){
//        System.out.println("wordt aangeroepen.");
//        System.out.println(product);
//        System.out.println(product.getBrand());
//        productService.createProduct(product);
//    }
    
public void createProduct() {
		
		// get Klant object back (adress can remain null at first)
		Product product = epm.createProduct();
		
		// send Klant object to DAO to persist in DB
		dao.create(product);
		epm.runEmployeeProductMenu();
	}

public List<Product> getAllProducts(){
//    return pdao.getAllProducts();
	return dao.findAll(Product.class);
}



    
    public void pasProductAan() {
       // productService.getAllProducts();
        Product zoekproduct = epm.pasProductAan();
        
        //roep dao aan
        epm.runEmployeeProductMenu();
    }
    
    
      
    public void updateProduct(Long id, Product product){
    productService.updateProduct(id, product);
    }
     
    public void deleteProduct(Long id){
    productService.deleteProduct(id);
    }

	public void runEmployeeProductMenu() {
		epm.runEmployeeProductMenu();
		
	}

    

   
}