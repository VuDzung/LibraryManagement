package com.dxc.librarymanagement.dao;

import com.dxc.librarymanagement.entities.LibRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibRoleDAO extends JpaRepository<LibRole, Integer> {
	LibRole findByIdRole(int idRole);
}