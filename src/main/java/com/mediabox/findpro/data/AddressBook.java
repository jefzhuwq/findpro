package com.mediabox.findpro.data;

// Generated 2015-10-23 15:45:32 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressBook generated by hbm2java
 */
@Entity
@Table(name = "address_book", catalog = "findpro")
public class AddressBook implements java.io.Serializable {

	private Integer idaddressBook;
	private String address;
	private Integer userId;
	private Boolean isPrimary;
	private Boolean isBilling;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zipcode;

	public AddressBook() {
	}

	public AddressBook(String address, Integer userId, Boolean isPrimary,
			Boolean isBilling, String firstName, String lastName,
			String street, String city, String state, String zipcode) {
		this.address = address;
		this.userId = userId;
		this.isPrimary = isPrimary;
		this.isBilling = isBilling;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idaddress_book", unique = true, nullable = false)
	public Integer getIdaddressBook() {
		return this.idaddressBook;
	}

	public void setIdaddressBook(Integer idaddressBook) {
		this.idaddressBook = idaddressBook;
	}

	@Column(name = "address", length = 45)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "is_primary")
	public Boolean getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Column(name = "is_billing")
	public Boolean getIsBilling() {
		return this.isBilling;
	}

	public void setIsBilling(Boolean isBilling) {
		this.isBilling = isBilling;
	}

	@Column(name = "first_name", length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "street", length = 45)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "city", length = 45)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", length = 45)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "zipcode", length = 45)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
