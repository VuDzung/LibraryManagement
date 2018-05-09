package com.dxc.librarymanagement.dao;

import com.dxc.librarymanagement.entities.LibUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibUserDAO extends JpaRepository<LibUser, Integer> {
	List<LibUser> findByUserNameContaining(String username);
	LibUser findByUserName(String username);
	LibUser findByIdUser(int iduser);
}
