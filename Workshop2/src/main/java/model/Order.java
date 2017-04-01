package main.java.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private /* @Jurjen final */ UserAccount user;
	@OneToMany(cascade=CascadeType.ALL) // nog niet helemaal duidelijk welk type nodig is
	@JoinColumn(name = "order_id")
	private /* @Jurjen final */ List<FinalSubOrder> subOrders = new ArrayList<>();
	private /* @Jurjen final */ LocalDate orderDate;
	@Column(length = 10, nullable = false)
	private /* @Jurjen final */ BigDecimal totalPrice;
	
	// adress fields
        /* @Jurjen
        Is dit niet makkelijker op te lossen door:
        final Adress adress;
        */
	private String firstName;
	@Column(length = 50)
	private String insertion;
	@Column(length = 10, nullable = false)
	private String familyName;
	@Column(length = 50, nullable = false)
	private String street;
	@Column(length = 5)
	private int number;
	@Column(length = 10)
	private String numAddition;
	@Column(length = 10)
	private String zipCode;
	@Column(length = 50, nullable = false)
	private String city;
	
	public Order(){
	}
	
	public void setDeliveryAdress(Adress adress){
		
		firstName = adress.getFirstName();
		insertion = adress.getInsertion();
		familyName = adress.getFamilyName();
		street = adress.getStreet();
		number = adress.getNumber();
		numAddition = adress.getNumAddition();
		zipCode = adress.getZipCode();
		city =  adress.getCity();
                
                /* @Jurjen
                dit zou dan worden:
                this.Adress = adress;
                */
		
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

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public List<FinalSubOrder> getSubOrders() {
		return subOrders;
	}
        
        /* @Jurjen
        ipv addSubOrder de functie om SubOrders toe te voegen.
        Je hoeft toch niet de SubOrders 1 voor 1 toe te voegen aan de final Order?
        public void addSubOrders(List<FinalSubOrder> subOrders){
            this.subOrders = subOrders;
        */

	public void addSubOrder(FinalSubOrder subOrder) {
		subOrders.add(subOrder);
	}

	public LocalDate getSaledate() {
		return orderDate;
	}
        
        /* @Jurjen
        volgens mij is setSaleDate niet nodig omdat je niet wilt dat het nog veranderd kan worden
        Maar ik weet niet zeker of een final field + setter kan.
        Anders zou de datum in de constructor worden meegegeven (of Spring injectie?)
        */
	public void setSaledate(LocalDate saledate) {
		this.orderDate = saledate;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
        
        /* @Jurjen
        Niet nodig, totaalprijs kan berekend of doorgegeven worden ipv handmatige setTotalPrice
        */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
        
        public void calculateTotalPrice() {
            for (FinalSubOrder fso : subOrders) {
                int quantity = fso.getQuantity();
                BigDecimal subTotal = fso.getSubTotal();
                setTotalPrice(subTotal.multiply(new BigDecimal(quantity)));
            }
        }

}
