package main.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "userAccounts")
public class UserAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String passHash;
	@Column(name = "type")
	@Enumerated(EnumType.STRING) // komt in de tabel als string, alternatief is ORDINAL, komt als index (int)
	private UserType UserType;  //Enum
	@OneToOne
	private Adress billingAdress;
	
	
	public UserAccount() {
	}
	

	//GETTERS AND SETTERS


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public UserType getUserType() {
		return UserType;
	}

	public void setUserType(UserType userType) {
		UserType = userType;
	}

	public Adress getBillingAdress() {
		return billingAdress;
	}

	public void setBillingAdress(Adress billingAdress) {
		this.billingAdress = billingAdress;
	}


}
