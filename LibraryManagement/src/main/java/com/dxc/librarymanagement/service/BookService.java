package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibBookDAO;
import com.dxc.librarymanagement.dao.LibIsbnDAO;
import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;

@Service
@Transactional
public class BookService {
	@Autowired
	private LibBookDAO bookdao;
	@Autowired
	private LibIsbnDAO isbndao;

	private int limitRecords = 10;

	// save new book or ISBN of existing book
	public void saveBook(LibBook book, LibIsbn isbn) {
		LibBook bookexistcheck = this.bookdao.findByTitleOfBookAndAuthor(book.getTitleOfBook(), book.getAuthor());
		if (bookexistcheck == null) {
			isbn.setBook(this.bookdao.save(book));
			this.isbndao.save(isbn);
		} else {
			isbn.setBook(book);
			this.isbndao.save(isbn);
		}
	}

	// get New Book List
	public List<LibBook> getNewBook() {
		List<LibBook> listnewbook = new ArrayList<LibBook>();
		listnewbook = this.bookdao.findTop10ByOrderByIdBookDesc();
		return listnewbook;
	}

	// get List Book for paginating
	public List<LibBook> getPaginateBooks(int number) {
		Pageable pageable = PageRequest.of(number - 1, this.limitRecords);
		return bookdao.findAll(pageable).getContent();
	}

	// get Number of page paginate
	public int getPaginatePageNum() {
		double records = this.bookdao.count();
		double pageNum = records / this.limitRecords;
		return (int) Math.ceil(pageNum);

	}
	
}
