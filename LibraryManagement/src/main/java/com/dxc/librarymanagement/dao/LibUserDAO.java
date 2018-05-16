package com.dxc.librarymanagement.dao;

import com.dxc.librarymanagement.entities.LibUser;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibUserDAO extends JpaRepository<LibUser, Integer> {
	List<LibUser> findFirst10ByUserNameContaining(String username);
	LibUser findByUserName(String username);
	LibUser findByIdUser(int iduser);
	Page<LibUser> findAll(Pageable pageable);
}
