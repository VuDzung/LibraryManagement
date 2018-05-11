package com.dxc.librarymanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibBookDAO;
import com.dxc.librarymanagement.dao.LibIsbnDAO;
import com.dxc.librarymanagement.dto.BookDTO;
import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;

@Service
@Transactional
public class IsbnServiceImpl {
	@Autowired
	private LibIsbnDAO isbndao;
	@Autowired
	private LibBookDAO bookdao;
	@Autowired
	private BookServiceImpl bookService;

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
	
	public LibIsbn saveIsbn(LibIsbn isbn) {
		return isbndao.save(isbn);
	}
	
	public void addIsbn(BookDTO bookDTO) {
		LibIsbn libIsbn = new LibIsbn();
		libIsbn.setIsbn(bookDTO.getIsbn());
		libIsbn.setTotalBook(bookDTO.getTotalBook());
		LibBook libBook = new LibBook();
		libBook.setAuthor(bookDTO.getAuthor());
		libBook.setImage(bookDTO.getImage());
		libBook.setPublishYear(bookDTO.getPublishYear());
		libBook.setShortDescription(bookDTO.getShortDescription());
		libBook.setTitleOfBook(bookDTO.getTitleOfBook());
		this.bookService.saveBook(libBook, libIsbn);
	}
	
	public void deleteIsbn(String isbn) {
		LibIsbn libisbn = this.isbndao.findByIsbn(isbn);
		libisbn.setTotalBook(0);
		libisbn.setStatus(false);
		this.isbndao.save(libisbn);
	}
	
	public void editIsbn(BookDTO bookDTO) {
		LibIsbn libIsbn = this.isbndao.findByIsbn(bookDTO.getIsbn());
		LibBook libBook = libIsbn.getBook();
		libBook.setAuthor(bookDTO.getAuthor());
		libBook.setPublishYear(bookDTO.getPublishYear());
		libBook.setShortDescription(bookDTO.getShortDescription());
		libBook.setTitleOfBook(bookDTO.getTitleOfBook());
		
		libIsbn.setBook(this.bookdao.save(libBook));
		libIsbn.setTotalBook(libIsbn.getTotalBook()+bookDTO.getTotalBook());
		this.isbndao.save(libIsbn);
	}
	
}
