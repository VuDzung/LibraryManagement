package com.dxc.librarymanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibBookDAO;
import com.dxc.librarymanagement.entities.LibBook;


@Service
@Transactional
public class BookServiceImpl {
	
	@Autowired
	private LibBookDAO bookdao;

	//GET 10 FIRST BOOK BY TITLE CONTAINING 
	public List<LibBook> findFirst10ByTitleOfBookContaining(String titleOfBook) {
		return this.bookdao.findFirst10ByTitleOfBookContaining(titleOfBook);
	}
	
	//SAVE BOOK
	public LibBook save(LibBook libBook) {
		return this.bookdao.save(libBook);
	}
	
	//GET BOOK BY TITLE & AUTHOR
	public LibBook findByTitleOfBookAndAuthor(String title, String author) {
		return this.bookdao.findByTitleOfBookAndAuthor(title, author);
	}
}