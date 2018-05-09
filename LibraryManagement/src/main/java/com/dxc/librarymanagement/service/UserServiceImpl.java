package com.dxc.librarymanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibUserDAO;
import com.dxc.librarymanagement.entities.LibUser;

@Service
@Transactional
public class UserServiceImpl {
	@Autowired
	private LibUserDAO libUserDAO;
	public LibUser saveUser(LibUser libUser) {
		return libUserDAO.save(libUser);
	}
	public List<LibUser> findByUserNameContaining(String username){
		return libUserDAO.findByUserNameContaining(username);
	}
	
	public LibUser findByIdUser(int iduser){
		return libUserDAO.findByIdUser(iduser);
	}
}
