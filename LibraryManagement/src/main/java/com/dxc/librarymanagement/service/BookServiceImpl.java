package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	private IsbnServiceImpl isbnServiceImpl;
	@Value("${StatusAvailable}")
	private boolean StatusAvailable;
	@Value("${LimitRecords}")
	private int LimitRecords;

	// save new book or ISBN of existing book
	public void saveBook(LibBook book, LibIsbn isbn) {
		isbn.setStatus(StatusAvailable);
		LibBook bookexistcheck = this.bookdao.findByTitleOfBookAndAuthor(book.getTitleOfBook(), book.getAuthor());
		if (bookexistcheck == null) {
			isbn.setBook(this.bookdao.save(book));
			this.isbndao.save(isbn);
		} else {
			isbn.setBook(bookexistcheck);
			LibIsbn libIsbn = this.isbnServiceImpl.getSingleBook(isbn.getIsbn());
			if (libIsbn == null) {
				this.isbndao.save(isbn);
			} else {
				isbn.setTotalBook(libIsbn.getTotalBook() + isbn.getTotalBook());
				isbn.setNumberBooksBorrowed(libIsbn.getNumberBooksBorrowed());
				this.isbndao.save(isbn);
			}
		}
	}

	// get New Book List
	public List<LibIsbn> getNewBook() {
		int totalpage = this.getPaginatePageNum();
		Pageable pageable1 = PageRequest.of(totalpage - 1, this.LimitRecords);
		Pageable pageable2 = PageRequest.of(totalpage - 2, this.LimitRecords);
		List<LibIsbn> newbook = new ArrayList<LibIsbn>();
		newbook.addAll((isbndao.findAll(pageable2).getContent()));
		newbook.addAll(isbndao.findAll(pageable1).getContent());
		Collections.reverse(newbook);
		return newbook;
	}

	// get List Book for paginating
	public List<LibIsbn> getPaginateBooks(int number) {
		Pageable pageable = PageRequest.of(number - 1, this.LimitRecords);
		return isbndao.findAll(pageable).getContent();
	}

	// get Number of page paginate
	public int getPaginatePageNum() {
		double records = this.isbndao.count();
		double pageNum = records / this.LimitRecords;
		return (int) Math.ceil(pageNum);
	}

	// get book by ISBN
	public LibIsbn getSingleBook(String isbn) {
		return isbndao.findByIsbn(isbn);
	}

}