package com.dxc.librarymanagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.service.BorrowBookServiceImpl;

@RestController
@Transactional
public class BorrowBookController {
	@Autowired
	private BorrowBookServiceImpl borrowbookservice;
	
	@RequestMapping(value = "/home/borrow", method = RequestMethod.POST)
	
	public ResponseEntity<String> borrowBook(@RequestBody String isbn, Principal principal) {
		this.borrowbookservice.saveBorrowBook(isbn, principal);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}	
