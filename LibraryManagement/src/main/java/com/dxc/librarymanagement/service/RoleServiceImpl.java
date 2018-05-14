package com.dxc.librarymanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibRoleDAO;
import com.dxc.librarymanagement.entities.LibRole;

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
    
    public LibRole findByNameRole(String role) {
    	return this.libRoleDAO.findByNameRole(role);
    }
}
