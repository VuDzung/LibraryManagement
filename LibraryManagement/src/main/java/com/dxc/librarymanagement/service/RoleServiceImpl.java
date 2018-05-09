package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dxc.librarymanagement.entities.*;
import com.dxc.librarymanagement.dao.LibRoleDAO;
import com.dxc.librarymanagement.dto.RoleDTO;

@Service
@Transactional
public class RoleServiceImpl {
	@Autowired
	private LibRoleDAO libRoleDAO;

	public List<LibRole> findAllRole() {
		return this.libRoleDAO.findAll();
	}
	public LibRole findByIdRole(int idRole) {
		return this.libRoleDAO.findByIdRole(idRole);
	}
//	public List<RoleDTO> findByNameRole(String namerole){
//		List<RoleDTO> listRoleDTO = new ArrayList<RoleDTO>();
//		List<LibRole> listRole= this.libRoleDAO.findAll(namerole);
//		for (LibRole libRole : listRole) {
//			RoleDTO roleDTO = new RoleDTO(libRole.getIdRole(), libRole.getNameRole());
//			listRoleDTO.add(roleDTO);
//		}
//		return listRoleDTO;
//	}
}
