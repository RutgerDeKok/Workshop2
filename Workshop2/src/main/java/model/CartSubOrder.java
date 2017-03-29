package main.java.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "cart_suborders")
public class CartSubOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@Fetch(FetchMode.JOIN)
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
       
		@Rutger 
		dit wordt automatisch geregeld door de annotaties in Cart
		@OneToMany
		@JoinColumn(name = "cart_id")
		private List<CartSubOrder> subOrders = new ArrayList<>();
		
		Het werkt prima, kijk maar in de DB
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

      
	public void setSubTotal(BigDecimal price, int quantity) { 
		subTotal =(price.multiply(new BigDecimal(quantity)));
		subTotal.setScale(2);
	}
        
        /* @Jurjen
        Functie om suborder aan cart toe te voegen
        public void addToCart() {
            Cart.addSubOrder(this);
        }
        */
	
	@Override
	public String toString(){
		//  Product   - Aantal  -  Prijs  - Subtotaal",
		int q =this.getQuantity();
		BigDecimal p = this.getProduct().getPrice();
		setSubTotal(p, q);
		p.setScale(2);
		String result = this.getProduct().getName()
				+ "\t" + q
				+ "\t" + p
				+ "\t" + subTotal;
	
		return result;
	}

	// this method was used in the persistence test to quickly 
	// add some numbers to a subOrder
	public void setTotalPrice(BigDecimal bigDecimal) {
		subTotal = bigDecimal;
		
	}


}
