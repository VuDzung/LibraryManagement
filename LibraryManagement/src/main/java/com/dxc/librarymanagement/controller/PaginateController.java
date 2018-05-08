package com.dxc.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.service.BookServiceImpl;

@RestController
public class PaginateController {

	@Autowired
	private BookServiceImpl bookservice;

	@RequestMapping(value = "/home/page/{pagenumber}")
	@ResponseBody
	public List<LibIsbn> usersPageable(@PathVariable int pagenumber) {
		return bookservice.getPaginateBooks(pagenumber);
	}

	@RequestMapping(value = "/home")
	@ResponseBody
	public List<LibIsbn> getNewBooks() {
		return bookservice.getNewBook();
	}

	@RequestMapping(value = "/home/book/{isbn}")
	public LibIsbn getBookByIsbn(@PathVariable String isbn) {
		return bookservice.getSingleBook(isbn);
	}

}