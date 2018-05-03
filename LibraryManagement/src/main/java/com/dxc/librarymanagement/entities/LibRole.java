package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Role database table.
 * 
 */
@Entity
@Table(name="LibRole")
//@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class LibRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IdRole")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRole;

	@Column(name="NameRole")
	private String nameRole;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<LibUser> users;

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

	public List<LibUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<LibUser> users) {
		this.users = users;
	}

	public LibUser addUser(LibUser user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public LibUser removeUser(LibUser user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}