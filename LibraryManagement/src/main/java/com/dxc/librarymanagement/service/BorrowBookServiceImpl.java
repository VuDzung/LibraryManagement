package com.dxc.librarymanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibBorrowBookDAO;
import com.dxc.librarymanagement.entities.LibBorrowBook;

@Service
@Transactional
public class BorrowBookServiceImpl {
	@Autowired
	private LibBorrowBookDAO borrowbookdao;
	@Autowired
	private UserServiceImpl userservice;
	
	public List<LibBorrowBook> getBorrowBookOfUser(int iduser) {
		return this.borrowbookdao.findByUser(this.userservice.findByIdUser(iduser));
	}
}
