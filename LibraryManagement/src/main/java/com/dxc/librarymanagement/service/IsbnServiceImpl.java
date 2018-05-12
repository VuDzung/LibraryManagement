package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	@Value("${StatusUnavailable}")
	private boolean StatusUnavailable;
	@Value("${StatusAvailable}")
	private boolean StatusAvailable;
	@Value("${LimitRecords}")
	private int LimitRecords;

	//GET LIBISBN BY ISBN
	public LibIsbn findByIsbn(String isbn) {
		return isbndao.findByIsbn(isbn);
	}

	//GET LIBISBN BY BOOK
	public List<LibIsbn> findByBook(LibBook book) {
		return isbndao.findByBook(book);
	}

	//GET LIBISBN BY BOOK ID
	public List<LibIsbn> findByBookIds(Iterable<LibBook> books) {
        return isbndao.findByBookIn(books);
    }
	
	//SAVE ISBN
	public LibIsbn saveIsbn(LibIsbn isbn) {
		return isbndao.save(isbn);
	}
	
	//GET 'NEW BOOKS LIST'
	public List<LibIsbn> getNewBook() {
		return this.isbndao.findFirst10();
	}

	//GET BOOK LIST FOR PAGINATE
	public List<LibIsbn> getPaginateBooks(int number) {
		Pageable pageable = PageRequest.of(number - 1, this.LimitRecords);
		return isbndao.findAll(pageable).getContent();
	}

	//GET PAGES NUMBER
	public int getPaginatePageNum() {
		double records = this.isbndao.countByStatus(true);
		double pageNum = records / this.LimitRecords;
		return (int) Math.ceil(pageNum);
	}

	//GET LIBISBN BY ISBN
	public LibIsbn getSingleBook(String isbn) {
		return isbndao.findByIsbn(isbn);
	}
	
	//ADD ISBN
	public List<String> addIsbn(BookDTO bookDTO) {
		List<String> status = new ArrayList<>();
		LibIsbn libIsbn = isbndao.findByIsbn(bookDTO.getIsbn());
		if(libIsbn!=null) {
			libIsbn.setTotalBook(libIsbn.getTotalBook() + bookDTO.getTotalBook());
			isbndao.save(libIsbn);
			status.add(String.valueOf(this.getPaginatePageNum()));
			status.add("Add Succesful!");
			return status;
		}
		//else libIsbn==null
			LibBook libBook = bookService.findByTitleOfBookAndAuthor(bookDTO.getTitleOfBook(), bookDTO.getAuthor());
			if(libBook!=null) {
				libIsbn = new LibIsbn();
				libIsbn.setIsbn(bookDTO.getIsbn());
				libIsbn.setBook(libBook);
				libIsbn.setTotalBook(bookDTO.getTotalBook());
				libIsbn.setStatus(StatusAvailable);
				libIsbn.setNumberBooksBorrowed(0);
				isbndao.save(libIsbn);
				status.add(String.valueOf(this.getPaginatePageNum()));
				status.add("Add Succesful!");
				return status;
			}
			//else libBook==null
				libBook = new LibBook();
				libBook.setAuthor(bookDTO.getAuthor());
				libBook.setImage(bookDTO.getImage());
				libBook.setPublishYear(bookDTO.getPublishYear());
				libBook.setShortDescription(bookDTO.getShortDescription());
				libBook.setTitleOfBook(bookDTO.getTitleOfBook());
						
				libIsbn = new LibIsbn();
				libIsbn.setIsbn(bookDTO.getIsbn());
				libIsbn.setTotalBook(bookDTO.getTotalBook());
				libIsbn.setStatus(StatusAvailable);
				libIsbn.setNumberBooksBorrowed(0);
				libIsbn.setBook(this.bookService.save(libBook));
				isbndao.save(libIsbn);
				status.add(String.valueOf(this.getPaginatePageNum()));
				status.add("Add Succesful!");
				return status;	
	}
	
	//DELTETE ISBN
	public List<String> deleteIsbn(String isbn) {
		List<String> status = new ArrayList<>();
		LibIsbn libisbn = this.isbndao.findByIsbn(isbn);
		if(libisbn==null){
			status.add("");
			status.add("ISBN Code Is Not Correct!");
			return status;
		}
		if(libisbn.getNumberBooksBorrowed() > 0) {
			status.add("");
			status.add("Books Are Being Borrowed!");
			return status;
		}
		libisbn.setTotalBook(0);
		libisbn.setStatus(StatusUnavailable);
		this.isbndao.save(libisbn);
		status.add(String.valueOf(this.getPaginatePageNum()));
		status.add("Delete Successful!");
		return status;
	}
	
	// EDIT ISBN
	public String editIsbn(BookDTO bookDTO) {
		LibIsbn libIsbn = this.isbndao.findByIsbn(bookDTO.getIsbn());
		if(libIsbn==null) return "ISBN Code Is Not Correct!";
		LibBook libBook = libIsbn.getBook();
		if(bookDTO.getTotalBook()<libIsbn.getNumberBooksBorrowed()) return "Total Can Not Less Than Number Of Borrowed Book!";
		if(!libBook.getTitleOfBook().equals(bookDTO.getTitleOfBook()) || 
				!libBook.getAuthor().equals(bookDTO.getAuthor()) || 
				libBook.getPublishYear()!=bookDTO.getPublishYear()
				) return "Can Not Edit Title, Author, Publish Year!";
		libBook.setShortDescription(bookDTO.getShortDescription());
		libIsbn.setBook(this.bookdao.save(libBook));
		libIsbn.setTotalBook(bookDTO.getTotalBook());		
		this.isbndao.save(libIsbn);
		return "Edit Successful!";
	}
	
}
