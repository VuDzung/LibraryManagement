package com.dxc.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.service.IsbnServiceImpl;

@RestController
@RequestMapping("/bookmanagement")
public class AdminBookmanagement {

	@Autowired
	private IsbnServiceImpl isbnServiceImpl;

	@RequestMapping(value = "/checkisbn/{isbn}", method = RequestMethod.GET)
	public ResponseEntity<LibIsbn> apiWithAjax2(@PathVariable("isbn") String isbn) {
		System.out.println("This is value reciver from form: " + isbn);
		LibIsbn libIsbn = this.isbnServiceImpl.getSingleBook(isbn);
		return new ResponseEntity<>(libIsbn, HttpStatus.OK);
	}

	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public ResponseEntity<LibIsbn> apiWithAjax(@PathVariable("isbn") String isbn) {
		System.out.println("This is value reciver from form: " + isbn);
		LibIsbn libIsbn = this.isbnServiceImpl.getSingleBook(isbn);
		return new ResponseEntity<>(libIsbn, HttpStatus.OK);
	}

}
