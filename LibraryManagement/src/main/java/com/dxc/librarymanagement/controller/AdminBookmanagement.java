package com.dxc.librarymanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.librarymanagement.dto.BookDTO;
import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.service.BookServiceImpl;
import com.dxc.librarymanagement.service.IsbnServiceImpl;

@RestController
@RequestMapping("/bookmanagement")
public class AdminBookmanagement {

	@Autowired
	private IsbnServiceImpl isbnServiceImpl;

	@Autowired
	private BookServiceImpl bookServiceImpl;
	//GET ISBN FOR SEARCH FEATURE
	@RequestMapping(value = "/checkisbn/{isbn}", method = RequestMethod.GET)
	public ResponseEntity<LibIsbn> apiWithAjax2(@PathVariable("isbn") String isbn) {
		LibIsbn libIsbn = this.isbnServiceImpl.findByIsbn(isbn);
		return new ResponseEntity<>(libIsbn, HttpStatus.OK);
	}
	//SAVE BOOK FEATURE
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public ResponseEntity<String> saveNewIsbn(@RequestBody BookDTO bookDTO, HttpServletRequest request) {
		this.isbnServiceImpl.addIsbn(bookDTO);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	// @RequestMapping(value = "/getTags", method = RequestMethod.GET)
	// public List<LibIsbn> getTags(@RequestParam String tagName) {
	// List<LibBook> listBook =
	// this.bookServiceImpl.findByTitleOfBookContaining(tagName);
	// List<LibIsbn> listIsbn = new ArrayList<LibIsbn>();
	// for (LibBook libBook : listBook) {
	// List<LibIsbn> listIsbn2 = isbnServiceImpl.findByBook(libBook);
	// for (LibIsbn libIsbn : listIsbn2) {
	// listIsbn.add(libIsbn);
	// }
	// }
	// return listIsbn;
	//
	// }
	
	//DELETE BOOK FEATURE
	@RequestMapping(value = "/delete/{isbn}", method = RequestMethod.GET)
	public ResponseEntity<Integer> deleteBook(@PathVariable String isbn) {
		this.isbnServiceImpl.deleteIsbn(isbn);
		return new ResponseEntity<>(this.bookServiceImpl.getPaginatePageNum(), HttpStatus.OK);
	}
	
	//EDIT BOOK FEATURE
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public ResponseEntity<String> editBook(@RequestBody BookDTO bookDTO){
		this.isbnServiceImpl.editIsbn(bookDTO);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

}
