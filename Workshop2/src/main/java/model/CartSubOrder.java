package main.java.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "cart_suborders")
public class CartSubOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private Product product;
	@Column(length = 10, nullable = false)
	private int quantity;
	@Column(length = 10, nullable = false)
	private BigDecimal subTotal;
        /* @Jurjen
        CartSubOrder heeft een referentie naar Cart nodig toch?
        @OneToOne
        @Column(name = "cart_id", nullable = false)
        private Cart cart;
        */
	
	public CartSubOrder(){
	}
	
	public long getId() {
		return id;
	}

	/* @Jurjen
        Overbodig, doet Hibernate al
        */
        public void setId(long id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return subTotal;
	}

        /* @Jurjen
        Volgens mij is setTotalPrice niet nodig, maar kan het beter vervangen
        worden door een functie die de totaalprijs berekent
        */
	public void setTotalPrice(BigDecimal totalPrice) { /* @Jurjen setSubTotal(BigDecimal subTotal) */
		this.subTotal = totalPrice;
	}
        
        /* @Jurjen
        Functie om suborder aan cart toe te voegen
        public void addToCart() {
            Cart.addSubOrder(this);
        }
        */


}
