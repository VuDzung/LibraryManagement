package com.dxc.librarymanagement.service;

import java.security.Principal;
import java.util.ArrayList;
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
	
	//GET BORROWED BOOK OF USER
	public List<LibBorrowBook> getBorrowBookOfUser(int iduser) {
		return this.borrowbookdao.findByUser(this.userservice.findByIdUser(iduser));
	}
	
	//SAVE LIBBORROWBOOK
	public LibBorrowBook save(LibBorrowBook borrowbook) {
		return borrowbookdao.save(borrowbook);
	}
	
	//BORROW BOOK FUNCTON
	public List<String> saveBorrowBook(String isbn, Principal principal) {
		List<String> status = new ArrayList();
		LibIsbn libisbn = this.isbnservice.findByIsbn(isbn);
		LibUser user = this.userservice.findByUserName(principal.getName());
		if(libisbn == null) {
			status.add("");
			status.add("ISBN Code Is Not Correct!");
			return status;
		}
		if(libisbn.getNumberBooksBorrowed() >= libisbn.getTotalBook()) {
			status.add("");
			status.add("Book Is Not Available!");
			return status;
		}
		if(user.getBorrowedNumber() >= user.getLimitNumber()) {
			status.add("");
			status.add("Number Of Borowed Books Reached The Limit!");
			return status;
		}		
		LibBorrowBook libborrow = new LibBorrowBook();
		libborrow.setDateBorrow(new Date());
		libborrow.setIsbnBean(libisbn);
		libborrow.setUser(user);
		status.add(libisbn.getNumberBooksBorrowed()>=libisbn.getTotalBook()-1? "0":"1");
		this.borrowbookdao.save(libborrow);
		status.add("Borrow Successful!");
		return status;
	}
	
	//RETURN BOOK FUNCTION
	public String returnBorrowBook(int idborrow) {
		LibBorrowBook libborrow = this.borrowbookdao.findByIdBorrow(idborrow);
		if(libborrow==null) return "Borrow Not Exist!";	
		libborrow.setDateReturn(new Date());
		this.borrowbookdao.save(libborrow);
		return "Return Successful!";
	}
}
