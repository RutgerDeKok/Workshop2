package main.java.controller;

import main.java.daos.GenericDao;
import main.java.model.Product;
import main.java.model.ProductCategory;
import main.java.presentation.EmployeeAccountsMenu;
import main.java.presentation.EmployeeProductMenu;
import main.java.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

	@Autowired
	private GenericDao<Product, Long> dao;

	private List<Product> products;
	private List<Product> filteredProducts;


	@Autowired
	ProductService productService;
	@Autowired
	EmployeeProductMenu epMenu;
	@Autowired
	EmployeeAccountsMenu empAccountsMenu;

	public Product getProduct(Long id) {
		return productService.getProduct(id);
	}

	public void createProduct() {

		Product product = epMenu.createUpdateProduct(new Product());
		dao.create(product);
		runEmployeeProductMenuUpdateFromDB();
	}

	public List<Product> getAllProducts() {

		return dao.findAll(Product.class);
	}

	public void updateProduct(Product aangepastProduct) {
		dao.saveOrUpdate(aangepastProduct);
		epMenu.runEmployeeProductMenu();
	}

	public void deleteProductP(Product p) {
		dao.delete(p);
		runEmployeeProductMenuUpdateFromDB();
	}

	public void runEmployeeProductMenuUpdateFromDB() {
			products = getAllProducts();
			filteredProducts = new ArrayList<>(products);
		epMenu.runEmployeeProductMenu();

	}

//	public void runEmployeeProductMenu() {
//		epMenu.runEmployeeProductMenu();
//
//	}

	
	public void filterProductenByCat(ProductCategory filterCategory) {
		filteredProducts.clear();
		for(Product prod: products){
			if(filterCategory ==ProductCategory.ALL || prod.getCategory()==filterCategory){
				filteredProducts.add(prod);
			}
		}
	}
	
	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}


}