package com.dxc.librarymanagement.dao;

import com.dxc.librarymanagement.entities.LibRole;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibRoleDAO extends JpaRepository<LibRole, Integer> {
	LibRole findByIdRole(int idRole);

//	@Query("Select c.IdRole, c.NameRole from LibRole c where c.NameRole like %:namerole%")
//	List<LibRole> findAll(@Param("namerole") String namerole);
}
