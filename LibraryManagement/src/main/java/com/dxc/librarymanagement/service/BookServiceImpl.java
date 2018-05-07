package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.Collections;
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
public class BookServiceImpl {
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
	public List<LibIsbn> getNewBook() {
		int totalpage = this.getPaginatePageNum();
		Pageable pageable1 = PageRequest.of(totalpage - 1, this.limitRecords);
		Pageable pageable2 = PageRequest.of(totalpage - 2, this.limitRecords);
		List<LibIsbn> newbook = new ArrayList<LibIsbn>();
		newbook.addAll((isbndao.findAll(pageable2).getContent()));
		newbook.addAll(isbndao.findAll(pageable1).getContent());
		Collections.reverse(newbook);
		return newbook;
	}

	// get List Book for paginating
	public List<LibIsbn> getPaginateBooks(int number) {
		Pageable pageable = PageRequest.of(number - 1, this.limitRecords);
		return isbndao.findAll(pageable).getContent();
	}

	// get Number of page paginate
	public int getPaginatePageNum() {
		double records = this.isbndao.count();
		double pageNum = records / this.limitRecords;
		return (int) Math.ceil(pageNum);

	}

}
