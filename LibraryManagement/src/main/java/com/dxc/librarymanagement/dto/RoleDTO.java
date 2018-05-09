package com.dxc.librarymanagement.dto;

public class RoleDTO {
	private int idRole;
	private String nameRole;

	public RoleDTO() {
	}

	public RoleDTO(int idRole, String nameRole) {
		this.idRole = idRole;
		this.nameRole = nameRole;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

}
