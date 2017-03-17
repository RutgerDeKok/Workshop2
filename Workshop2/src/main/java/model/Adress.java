package main.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Adresses")
public class Adress /* @Jurjen implements Serializable */{
	// dit is een comment /* @Jurjen: en kan dus weg? */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
        /* @Jurjen misschien een idee om alle id's de Wrapperclass Long te geven, 
        zodat het makkelijker te gebruiken is bij de genericDAO? 
        Of zou casten voor gebruik van de DAO makkelijker zijn? */
	@Column(length = 50, nullable = false)
	private String firstName;
	@Column(length = 50)
	private String insertion; 
        /* @Jurjen misschien insertion scharen onder familyName, 
        bijv: "van der Plas" ? Dan hoef je ook niet moeilijk te doen met kijken of een naam 
        een tussenvoegsel bevat bij het printen */
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

	public Adress(){
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

        public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInsertion() {
		return insertion;
	}

	public void setInsertion(String insertion) {
		this.insertion = insertion;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getNumAddition() {
		return numAddition;
	}

	public void setNumAddition(String numAddition) {
		this.numAddition = numAddition;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}

