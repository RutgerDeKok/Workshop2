package main.java.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "carts")
public class Cart {

	@Id
	private long id;
	@OneToOne
	private UserAccount user;
	@OneToOne
	private Adress deliveryAdress;
	@OneToMany(cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "cart_id")
	private List<CartSubOrder> subOrders = new ArrayList<>();
	@Column(length = 10)
	private BigDecimal totalPrice;

	public Cart() {
	}


	public UserAccount getUser() {
		return user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public Adress getDeliveryAdress() {
		return deliveryAdress;
	}

	public void setDeliveryAdress(Adress deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}

	public void addSubOrder(CartSubOrder subOrder) {
		subOrders.add(subOrder);
	}

	public void setSubOrders(List<CartSubOrder> subOrders) {
		this.subOrders = subOrders;
	}

	public List<CartSubOrder> getSubOrders() {
		return subOrders;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/*
	 * @Jurjen Volgens mij is setTotalPrice niet nodig, maar kan het beter
	 * vervangen worden door een functie die de totaalprijs berekent
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
            System.out.println("CHECK SUBTOTALPRICE IN CART" + totalPrice);
            System.out.println("CHECK TOTALPRICE IN CART1: " + this.totalPrice);
            if (this.totalPrice == null) {
                this.totalPrice = new BigDecimal(0);
            }
            this.totalPrice.add(totalPrice);
            System.out.println("CHECK TOTALPRICE IN CART2: " + this.totalPrice);
	}
        
        public void calculateTotalPrice() {
            for (CartSubOrder cso : subOrders) {
                BigDecimal subTotal = cso.getTotalPrice();
                System.out.println("CHECK SUBTOTALPRICE IN CALCULATE" + subTotal);
                setTotalPrice(subTotal);
            }
        }

	protected void emptyCart() {
		subOrders.clear();
	}

}
