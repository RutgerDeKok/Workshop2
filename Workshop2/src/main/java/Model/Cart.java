package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private UserAccount user;
	@OneToOne
	private Adress deliveryAdress;
	@OneToMany
	@JoinColumn(name = "cart_id")
	private List<SubOrder> subOrders = new ArrayList<SubOrder>();

	public Cart() {
	}
	
	//GETTERS AND SETTERS
	

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

	public void addSubOrder(SubOrder subOrder) {
		subOrders.add(subOrder);
	}

	public void setSubOrders(List<SubOrder> subOrders) {
		this.subOrders = subOrders;
	}
	
	
}
