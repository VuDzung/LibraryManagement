package com.dxc.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.entities.LibBorrowBook;
import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.entities.LibUser;
import com.dxc.librarymanagement.service.BorrowBookServiceImpl;
import com.dxc.librarymanagement.service.IsbnServiceImpl;
import com.dxc.librarymanagement.service.UserServiceImpl;

@RestController
public class PaginateController {

	@Autowired
	private UserServiceImpl userservice;
	@Autowired
	private BorrowBookServiceImpl borrowbookservice;
	@Autowired IsbnServiceImpl isbnservice;

	@RequestMapping(value = "/home/page/{pagenumber}")
	public List<LibIsbn> usersPageable(@PathVariable int pagenumber) {
		return isbnservice.getPaginateBooks(pagenumber);
	}

	@RequestMapping(value = "/home")
	public List<LibIsbn> getNewBooks() {
		return isbnservice.getNewBook();
	}

	@RequestMapping(value = "/home/book/{isbn}")
	public LibIsbn getBookByIsbn(@PathVariable String isbn) {
		return isbnservice.getSingleBook(isbn);
	}

	@RequestMapping(value = "/admin/tickets/{pagenumber}")
	public List<LibUser> ticketsPageable(@PathVariable int pagenumber) {
		return userservice.getPaginateUsers(pagenumber);
	}

	@RequestMapping(value = "/admin/ticket/{userid}")
	public LibUser getUserById(@PathVariable int userid) {
		return userservice.findByIdUser(userid);
	}

	@RequestMapping(value = "/admin/borrowed/{userid}")
	public List<LibBorrowBook> getBorrowingBookOfUser(@PathVariable int userid) {
		return this.borrowbookservice.getBorrowBookOfUser(userid);
	}

	@RequestMapping("/home/new")
	public List<LibIsbn> get() {
		return isbnservice.getNewBook();
	}

}