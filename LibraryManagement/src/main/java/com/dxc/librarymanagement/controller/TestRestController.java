package com.dxc.librarymanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.entities.LibRole;

@RestController
@RequestMapping("/api/tuantest")
public class TestRestController {

	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	public ResponseEntity<LibRole> apiWithAjax() {
		LibRole myRole = new LibRole();
		myRole.setIdRole(1);
		myRole.setNameRole("Tuan Tien Ti");
		return new ResponseEntity<>(myRole, HttpStatus.OK);
	}

	@RequestMapping(value = "/getdata2/{myvalue}", method = RequestMethod.GET)
	public ResponseEntity<LibRole> apiWithAjax2(@PathVariable("myvalue") String myvalue) {
		System.out.println("This is value reciver from form: " + myvalue);
		LibRole myRole = new LibRole();
		myRole.setIdRole(1);
		myRole.setNameRole("Tuan Tien Ti2");
		return new ResponseEntity<>(myRole, HttpStatus.OK);
	}
}
