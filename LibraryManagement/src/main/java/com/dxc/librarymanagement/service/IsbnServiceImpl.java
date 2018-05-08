package com.dxc.librarymanagement.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibIsbnDAO;
import com.dxc.librarymanagement.entities.LibIsbn;

@Service
@Transactional
public class IsbnServiceImpl {
	@Autowired
	private LibIsbnDAO isbndao;

	// get book by ISBN
	public LibIsbn getSingleBook(String isbn) {
		return isbndao.findByIsbn(isbn);
	}
}
