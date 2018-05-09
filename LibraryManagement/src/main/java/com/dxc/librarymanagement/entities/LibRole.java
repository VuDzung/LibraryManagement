package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the Role database table.
 * 
 */
@Entity
@Table(name = "LibRole")
// @NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class LibRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IdRole")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRole;

	@Column(name = "NameRole")
	private String nameRole;

	public LibRole() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getNameRole() {
		return this.nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

}