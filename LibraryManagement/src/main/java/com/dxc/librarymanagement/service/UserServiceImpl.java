package com.dxc.librarymanagement.service;

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
}
