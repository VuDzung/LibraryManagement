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
	
	//GET BORROWED BOOK OF USER
	public List<LibBorrowBook> getBorrowBookOfUser(int iduser) {
		return this.borrowbookdao.findByUser(this.userservice.findByIdUser(iduser));
	}
	
	//SAVE LIBBORROWBOOK
	public LibBorrowBook save(LibBorrowBook borrowbook) {
		return borrowbookdao.save(borrowbook);
	}
	
	//BORROW BOOK FUNCTON
	public String saveBorrowBook(String isbn, Principal principal) {
		LibIsbn libisbn = this.isbnservice.findByIsbn(isbn);
		LibUser user = this.userservice.findByUserName(principal.getName());
		if(libisbn == null) {
			return "ISBN Code Is Not Correct!";
		}
		if(libisbn.getNumberBooksBorrowed() >= libisbn.getTotalBook()) {
			return "Book Is Not Available!";
		}
		if(user.getBorrowedNumber() >= user.getLimitNumber()) {
			return "Number Of Borowed Books Reached The Limit!";
		}		
		LibBorrowBook libborrow = new LibBorrowBook();
		libborrow.setDateBorrow(new Date());
		libborrow.setIsbnBean(libisbn);
		libborrow.setUser(user);
		this.borrowbookdao.save(libborrow);
		return "Borrow Successful!";
	}
	
	//RETURN BOOK FUNCTION
	public String returnBorrowBook(int idborrow) {
		LibBorrowBook libborrow = this.borrowbookdao.findByIdBorrow(idborrow);
		if(libborrow==null) return "Borrow Not Exist!";
		LibIsbn libisbn = libborrow.getIsbnBean();
		libisbn.setNumberBooksBorrowed(libisbn.getNumberBooksBorrowed()-1);
		LibUser user = libborrow.getUser();
		user.setBorrowedNumber(user.getBorrowedNumber()-1);		
		libborrow.setDateReturn(new Date());
		libborrow.setIsbnBean(this.isbnservice.saveIsbn(libisbn));
		libborrow.setUser(this.userservice.saveUser(user));
		this.borrowbookdao.save(libborrow);
		return "Return Successful!";
	}
}
