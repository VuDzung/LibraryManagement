package com.dxc.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibRole;

@Repository
public interface LibRoleDAO extends JpaRepository<LibRole, Integer> {
    LibRole findByIdRole(int idRole);
    LibRole findByNameRole(String role);
}
