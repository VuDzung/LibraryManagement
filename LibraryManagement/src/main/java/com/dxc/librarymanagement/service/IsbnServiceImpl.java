package com.dxc.librarymanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibIsbnDAO;
import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;

@Service
@Transactional
public class IsbnServiceImpl {
	@Autowired
	private LibIsbnDAO isbndao;

	// get book by ISBN
	public LibIsbn findByIsbn(String isbn) {
		return isbndao.findByIsbn(isbn);
	}

	public List<LibIsbn> findByBook(LibBook book) {
		return isbndao.findByBook(book);
	}

	public List<LibIsbn> findByBookIds(Iterable<LibBook> books) {
        return isbndao.findByBookIn(books);
    }
}
