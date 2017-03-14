package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private UserAccount user;
	@OneToOne
	private Adress deliveryAdress;
	@OneToMany
	@JoinColumn(name = "Sale_id")
	private List<SubOrder> subOrders = new ArrayList<SubOrder>();
	private LocalDate saledate;
	
	public Sale(){
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserAccount getUser() {
		return user;
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

	public List<SubOrder> getSubOrders() {
		return subOrders;
	}

	public void addSubOrder(SubOrder subOrder) {
		subOrders.add(subOrder);
	}

	public LocalDate getSaledate() {
		return saledate;
	}

	public void setSaledate(LocalDate saledate) {
		this.saledate = saledate;
	}
	

}
