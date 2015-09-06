package com.lovi.hibernate.ex.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name="user_tbl")
public class User {

	/**
	 * If primary is @Embedded object,
	 * use @EmbeddedId insted of @Id
	 */
	@Id
	
	/**
	 * generated value for primary(depend on the data type),
	 * GenerationType.AUTO -> hibernate get decision according to used database
	 * 
	 */
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_contact")
	private String userContact;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="city",column=@Column(name="home_addr_city")),
		@AttributeOverride(name="state",column=@Column(name="home_addr_state"))
	})
	private Address homeAddress;
	
	@Embedded
	private Address officeAddress;
	
	@ElementCollection
	@JoinTable(name="user_office_tbl",joinColumns=@JoinColumn(name="user_id"))
	private Set<Office> listOfOffice = new HashSet();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = "+94 " + userContact;
	}

	public Address getAddress() {
		return homeAddress;
	}

	public void setAddress(Address address) {
		this.homeAddress = address;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Set<Office> getListOfOffice() {
		return listOfOffice;
	}

	public void setListOfOffice(Set<Office> listOfOffice) {
		this.listOfOffice = listOfOffice;
	}

	
	
	
	
}
