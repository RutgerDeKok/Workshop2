/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.main.java.model;

import java.math.BigDecimal;
import main.java.model.CartSubOrder;
import main.java.model.FinalSubOrder;
import main.java.model.Order;
import main.java.model.Product;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jurjen
 */
public class OrderTest {
    
    Order instance = new Order();
    
    public OrderTest() {
        Product product = new Product();
        product.setName("kaas");
        product.setPrice(BigDecimal.ONE);
        CartSubOrder cso = new CartSubOrder();
        cso.setProduct(product);
        cso.setQuantity(1);
        cso.setSubTotal(BigDecimal.ONE, 2);
        FinalSubOrder fso = new FinalSubOrder(cso);
        instance.addSubOrder(fso);
        cso.setSubTotal(BigDecimal.ONE, 1);
        fso = new FinalSubOrder(cso);
        instance.addSubOrder(fso);
    }
    
    @Before
    public void setUp() {        
    }

    @Test
    public void testCalculateTotalPrice() {
        System.out.println("calculateTotalPrice");        
        instance.calculateTotalPrice();
        //fail("The test case is a prototype.");
        assert(instance.getTotalPrice().intValue() == 3);
    }
    
}
