package com.dxc.librarymanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.entities.LibUser;
import com.dxc.librarymanagement.service.BookServiceImpl;
import com.dxc.librarymanagement.service.IsbnServiceImpl;
import com.dxc.librarymanagement.service.UserServiceImpl;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private IsbnServiceImpl isbnServiceImpl;

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public List<LibIsbn> getBook(@RequestParam String titlebook) {
		List<LibBook> listBook = this.bookServiceImpl.findByTitleOfBookContaining(titlebook);
		List<LibIsbn> listIsbn = new ArrayList<LibIsbn>();
		for (LibBook libBook : listBook) {
			List<LibIsbn> listIsbn2 = isbnServiceImpl.findByBook(libBook);
			for (LibIsbn libIsbn : listIsbn2) {
				listIsbn.add(libIsbn);
			}
		}
		return listIsbn;

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<LibUser> getUser(@RequestParam String username) {
		List<LibUser> listUser = this.userServiceImpl.findByUserNameContaining(username);
		return listUser;

	}

	@RequestMapping(value = "/user/{iduser}", method = RequestMethod.GET)
	public ResponseEntity<LibUser> getSingleUser(@PathVariable("iduser") int iduser) {
		LibUser LibUser = this.userServiceImpl.findByIdUser(iduser);
		return new ResponseEntity<>(LibUser, HttpStatus.OK);
	}
}
