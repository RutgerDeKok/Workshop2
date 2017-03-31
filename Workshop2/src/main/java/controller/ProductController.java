package main.java.controller;

import main.java.daos.GenericDao;
import main.java.daos.GenericDaoJpaImpl;
import main.java.model.Product;
import main.java.presentation.EmployeeAccountsMenu;
import main.java.presentation.EmployeeProductMenu;
import main.java.service.ProductService;

import java.util.List;
import main.java.daos.ProductUniqueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

	@Autowired
	private GenericDao<Product, Long> dao;

	// private List<Product> producten;

	// private ProductUniqueDao pdao = new ProductUniqueDao();

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
		epMenu.runEmployeeProductMenu();
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
		epMenu.runEmployeeProductMenu();
	}

	public void runEmployeeProductMenu() {
		epMenu.runEmployeeProductMenu();

	}

	public void runEmployeeAccountsMenu() {
		empAccountsMenu.runMenu();

	}
	
	
//	public List<Product> getProducten() {
//		return producten;
//	}
//
//	public void setProducten(List<Product> producten) {
//		this.producten = producten;
//	}

}