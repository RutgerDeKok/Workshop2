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
	
	private List<Product> producten;

//        private  ProductUniqueDao pdao = new ProductUniqueDao();
	
    
    @Autowired
    ProductService productService;
    @Autowired
    EmployeeProductMenu epMenu;
    
    
    public Product getProduct(Long id){
    return productService.getProduct(id);
    }
       
    
public void createProduct() {
		
		// get Klant object back (adress can remain null at first)
		Product product = epMenu.createUpdateProduct(new Product());
		
		// send Klant object to DAO to persist in DB
		dao.create(product);
		epMenu.runEmployeeProductMenu();
	}

public List<Product> getAllProducts(){
 
	return dao.findAll(Product.class);
}



    
    public void pasProductAan(Product aangepastProduct) {
    		dao.saveOrUpdate(aangepastProduct);
    		epMenu.runEmployeeProductMenu();
    }
    
    
      
    public void updateProduct(Long id, Product product){
    productService.updateProduct(id, product);
    }
     
    
    
    
//    public void deleteProduct(Long id){
//    productService.deleteProduct(id);
//    }
    
    public void deleteProductP(Product p){
    dao.delete(p);
    epMenu.runEmployeeProductMenu();
    }
    
    
    

	public void runEmployeeProductMenu() {
		epMenu.runEmployeeProductMenu();
		
	}

	public List<Product> getProducten() {
		return producten;
	}

	public void setProducten(List<Product> producten) {
		this.producten = producten;
	}

    

   
}