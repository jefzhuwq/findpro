package com.mediabox.findpro.data;

// Generated 2015-9-21 15:03:26 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Menu generated by hbm2java
 */
@Entity
@Table(name = "menu", catalog = "findpro")
public class Menu implements java.io.Serializable {

	private int idmenu;
	private String name;
	private String description;
	private Integer parentMenu;

	public Menu() {
	}

	public Menu(int idmenu) {
		this.idmenu = idmenu;
	}

	public Menu(int idmenu, String name, String description, Integer parentMenu) {
		this.idmenu = idmenu;
		this.name = name;
		this.description = description;
		this.parentMenu = parentMenu;
	}

	@Id
	@Column(name = "idmenu", unique = true, nullable = false)
	public int getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parent_menu")
	public Integer getParentMenu() {
		return this.parentMenu;
	}

	public void setParentMenu(Integer parentMenu) {
		this.parentMenu = parentMenu;
	}

}
