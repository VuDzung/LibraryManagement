package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private BookServiceImpl bookServiceImpl;

	@Autowired
	private IsbnServiceImpl isbnServiceImpl;
	@Value("${StatusAvailable}")
	private boolean StatusAvailable;
	@Value("${LimitRecords}")
	private int LimitRecords;

	public List<LibBook> findFirst10ByTitleOfBookContaining(String titleOfBook) {
		return this.bookdao.findFirst10ByTitleOfBookContaining(titleOfBook);
	}

	// save new book or ISBN of existing book
	public ResponseEntity<List<String>> saveBook(LibBook book, LibIsbn isbn) {
		List<String> status = new ArrayList<>();
		isbn.setStatus(StatusAvailable);
		LibBook bookexistcheck = this.bookdao.findByTitleOfBookAndAuthor(book.getTitleOfBook(), book.getAuthor());
		if (bookexistcheck == null) {
			isbn.setBook(this.bookdao.save(book));
			this.isbndao.save(isbn);
		} else {
			isbn.setBook(bookexistcheck);
			LibIsbn libIsbn = this.isbnServiceImpl.findByIsbn(isbn.getIsbn());
			if (libIsbn == null) {
				this.isbndao.save(isbn);
				status.add(String.valueOf(this.bookServiceImpl.getPaginatePageNum()));
				status.add("Save successful!");
				return new ResponseEntity<>(status, HttpStatus.OK);
			} else {
				isbn.setTotalBook(libIsbn.getTotalBook());
				isbn.setNumberBooksBorrowed(libIsbn.getNumberBooksBorrowed());
				this.isbndao.save(isbn);
				status.add("String.valueOf(this.bookServiceImpl.getPaginatePageNum())");
				status.add("The book exist");
				return new ResponseEntity<>(status, HttpStatus.OK);
				
			}
		}
		return null;
	}

	// get New Book List
	public List<LibIsbn> getNewBook() {
		return this.isbndao.findFirst10();
	}

	// get List Book for paginating
	public List<LibIsbn> getPaginateBooks(int number) {
		Pageable pageable = PageRequest.of(number - 1, this.LimitRecords);
		return isbndao.findAll(pageable).getContent();
	}

	// get Number of page paginate
	public int getPaginatePageNum() {
		double records = this.isbndao.countByStatus(true);
		double pageNum = records / this.LimitRecords;
		return (int) Math.ceil(pageNum);
	}

	// get book by ISBN
	public LibIsbn getSingleBook(String isbn) {
		return isbndao.findByIsbn(isbn);
	}

}