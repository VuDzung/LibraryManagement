package com.dxc.librarymanagement.service;

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

	
	public List<LibBook> findByTitleOfBookContaining(String titleOfBook) {
		return this.bookdao.findFirst7ByTitleOfBookContaining(titleOfBook);
	}
	
	// save new book or ISBN of existing book
	public void saveBook(LibBook book, LibIsbn isbn) {
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
			} else {
				isbn.setTotalBook(libIsbn.getTotalBook() + isbn.getTotalBook());
				isbn.setNumberBooksBorrowed(libIsbn.getNumberBooksBorrowed());
				this.isbndao.save(isbn);
			}
		}
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