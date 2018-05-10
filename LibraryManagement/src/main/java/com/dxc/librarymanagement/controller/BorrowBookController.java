package com.dxc.librarymanagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.service.BorrowBookServiceImpl;

@RestController
public class BorrowBookController {
	@Autowired
	private BorrowBookServiceImpl borrowbookservice;
	
	@RequestMapping(value = "/home/borrow/{isbn}", method = RequestMethod.GET)
	
	public ResponseEntity<String> borrowBook(@PathVariable String isbn, Principal principal) {
		this.borrowbookservice.saveBorrowBook(isbn, principal);
		return new ResponseEntity<>(isbn,HttpStatus.OK);
	}
}	
