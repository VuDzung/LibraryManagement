package com.dxc.librarymanagement.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibBorrowBookDAO;
import com.dxc.librarymanagement.entities.LibBorrowBook;
import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.entities.LibUser;

@Service
@Transactional
public class BorrowBookServiceImpl {
	@Autowired
	private LibBorrowBookDAO borrowbookdao;
	@Autowired
	private UserServiceImpl userservice;
	@Autowired
	private IsbnServiceImpl isbnservice;
	
	public List<LibBorrowBook> getBorrowBookOfUser(int iduser) {
		return this.borrowbookdao.findByUser(this.userservice.findByIdUser(iduser));
	}
	
	public LibBorrowBook save(LibBorrowBook borrowbook) {
		return borrowbookdao.save(borrowbook);
	}
	
	public LibBorrowBook saveBorrowBook(String isbn, Principal principal) {
		LibIsbn libisbn = this.isbnservice.findByIsbn(isbn);
		libisbn.setNumberBooksBorrowed(libisbn.getNumberBooksBorrowed()+1);
		LibUser user = this.userservice.findByUserName(principal.getName());
		user.setBorrowedNumber(user.getBorrowedNumber()+1);
		LibBorrowBook libborrow = new LibBorrowBook();
		libborrow.setDateBorrow(new Date());
		libborrow.setIsbnBean(isbnservice.saveIsbn(libisbn));
		libborrow.setUser(userservice.saveUser(user));
		return borrowbookdao.save(libborrow);
	}
	
	public LibBorrowBook returnBorrowBook(int idborrow) {
		LibBorrowBook libborrow = borrowbookdao.findByIdBorrow(idborrow);
		LibIsbn libisbn = libborrow.getIsbnBean();
		libisbn.setNumberBooksBorrowed(libisbn.getNumberBooksBorrowed()-1);
		LibUser user = libborrow.getUser();
		user.setBorrowedNumber(user.getBorrowedNumber()-1);		
		libborrow.setDateReturn(new Date());
		libborrow.setIsbnBean(isbnservice.saveIsbn(libisbn));
		libborrow.setUser(userservice.saveUser(user));
		return borrowbookdao.save(libborrow);
	}
}
