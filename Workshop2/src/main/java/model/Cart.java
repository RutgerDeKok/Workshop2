package main.java.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private /* @Jurjen final */ UserAccount user;
	@OneToOne
	private Adress deliveryAdress;
	@OneToMany
	@JoinColumn(name = "cart_id")
	private List<CartSubOrder> subOrders = new ArrayList<>(); /* @Jurjen diamond operator gebruikt en project source naar 1.8 geupdate */
	@Column(length = 10, nullable = false)
	private BigDecimal totalPrice;
        /* @Jurjen willen we dat je in Cart de hoeveelheid nog aan kan passen?
        Zo ja, misschien Map gebruiken met (subOrder, hoeveelheid)
        */

	public Cart() {
	}
	
	//GETTERS AND SETTERS /* @Jurjen dit kan weg toch? */
	public UserAccount getUser() {
		return user;
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

	public void setSubOrders(List<CartSubOrder> subOrders) { /* @Jurjen ben even kwijt waar deze functie voor was */
		this.subOrders = subOrders;
	}
        
        /* @Jurjen
        Geeft de inhoud van de Cart weer, handig voor overzichtsfuncties
        public List<CartSubOrder> getSubOrders() {
            return subOrders;
        }
        */

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

        /* @Jurjen
        Volgens mij is setTotalPrice niet nodig, maar kan het beter vervangen
        worden door een functie die de totaalprijs berekent
        */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
        
        /* @Jurjen
        protected void emptyCart() {
            subOrders.clear();
        }
        */
            
        
	
	
}
