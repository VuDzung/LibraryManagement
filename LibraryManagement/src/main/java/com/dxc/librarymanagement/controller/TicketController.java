package com.dxc.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.dto.UserDTO;
import com.dxc.librarymanagement.service.UserServiceImpl;

@RestController
public class TicketController {
	@Autowired UserServiceImpl userservice;
	
	@RequestMapping(value = "/admin/edit/ticket",  method = RequestMethod.POST)
	public ResponseEntity<String> editTicket(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(this.userservice.editTicket(userDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/add_user", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(this.userservice.addUser(userDTO), HttpStatus.OK);
	}
}
