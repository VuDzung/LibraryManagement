package com.dxc.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.entities.LibUser;
import com.dxc.librarymanagement.service.IsbnServiceImpl;
import com.dxc.librarymanagement.service.UserServiceImpl;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private IsbnServiceImpl isbnServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public List<LibIsbn> getBook(@RequestParam String titlebook) {
		return isbnServiceImpl.findByBookIds(titlebook);
	}

	@RequestMapping(value = "/resultlistbook/{titlebook}", method = RequestMethod.POST)
	public List<LibIsbn> returnSearchBook(@PathVariable("titlebook") String titlebook) {
		return isbnServiceImpl.findByBookIds(titlebook);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<LibUser> getUser(@RequestParam String username) {
		List<LibUser> listUser = this.userServiceImpl.findByUserNameContaining(username);
		return listUser;
	}

	@RequestMapping(value = "/resultlistuser/{username}", method = RequestMethod.POST)
	public List<LibUser> returnSearchUser(@PathVariable("username") String username) {
		List<LibUser> listUser = this.userServiceImpl.findByUserNameContaining(username);
		return listUser;
	}

	@RequestMapping(value = "/user/{iduser}", method = RequestMethod.GET)
	public ResponseEntity<LibUser> getSingleUser(@PathVariable("iduser") int iduser) {
		LibUser LibUser = this.userServiceImpl.findByIdUser(iduser);
		return new ResponseEntity<>(LibUser, HttpStatus.OK);
	}

}
