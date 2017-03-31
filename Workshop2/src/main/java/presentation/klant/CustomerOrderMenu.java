/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.presentation.klant;

/**
 *
 * @author Frank
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.controller.MainController;
import main.java.controller.ProductController;
import main.java.infrastructure.ColorConsole;
import main.java.presentation.employee.MainEmployeeMenu;

@Component
public class CustomerOrderMenu {

	@Autowired
	private ColorConsole console;
	@Autowired
	private MainController mainController;
	@Autowired
	private ProductController productController;

	

	@Autowired
	private MainEmployeeMenu mem;


	public void runEmployeeProductMenu() {
	}


}
