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
	private int Quantity;
	@Column(length = 10, nullable = false)
	private BigDecimal subTotal;
	
	
	
	public CartSubOrder(){
	}
	
	//GETTERS AND SETTERS
	
	public long getId() {
		return id;
	}

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
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return subTotal;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.subTotal = totalPrice;
	}


}
