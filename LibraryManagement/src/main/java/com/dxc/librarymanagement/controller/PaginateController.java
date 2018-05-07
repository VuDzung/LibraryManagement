package com.dxc.librarymanagement.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.service.BookService;

@Controller
public class PaginateController {
	
	private BookService bookservice;
	@RequestMapping(value = "/page/{pagenumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LibBook> usersPageable(@PathVariable int pagenumber) {
		return bookservice.getPaginateBooks(pagenumber);
	}

}
