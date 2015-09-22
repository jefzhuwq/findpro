package com.mediabox.findpro.data;

// Generated 2015-9-21 15:03:26 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserRoles generated by hbm2java
 */
@Entity
@Table(name = "user_roles", catalog = "findpro")
public class UserRoles implements java.io.Serializable {

	private Integer iduserRoles;
	private String username;
	private String role;

	public UserRoles() {
	}

	public UserRoles(String username, String role) {
		this.username = username;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "iduser_roles", unique = true, nullable = false)
	public Integer getIduserRoles() {
		return this.iduserRoles;
	}

	public void setIduserRoles(Integer iduserRoles) {
		this.iduserRoles = iduserRoles;
	}

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "role", length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
