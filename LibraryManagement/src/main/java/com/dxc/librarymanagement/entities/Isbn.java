package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the ISBN database table.
 * 
 */
@Entity
@Table(name = "ISBN")
@NamedQuery(name = "Isbn.findAll", query = "SELECT i FROM Isbn i")
public class Isbn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "NumberBooksBorrowed")
	private int numberBooksBorrowed;

	@Column(name = "Status")
	private boolean status;

	@Column(name = "TotalBook")
	private int totalBook;

	// bi-directional many-to-one association to BorrowBook
	@OneToMany(mappedBy = "isbnBean")
	private List<BorrowBook> borrowBooks;

	// bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name = "IdBook")
	private Book book;

	public Isbn() {
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNumberBooksBorrowed() {
		return this.numberBooksBorrowed;
	}

	public void setNumberBooksBorrowed(int numberBooksBorrowed) {
		this.numberBooksBorrowed = numberBooksBorrowed;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getTotalBook() {
		return this.totalBook;
	}

	public void setTotalBook(int totalBook) {
		this.totalBook = totalBook;
	}

	public List<BorrowBook> getBorrowBooks() {
		return this.borrowBooks;
	}

	public void setBorrowBooks(List<BorrowBook> borrowBooks) {
		this.borrowBooks = borrowBooks;
	}

	public BorrowBook addBorrowBook(BorrowBook borrowBook) {
		getBorrowBooks().add(borrowBook);
		borrowBook.setIsbnBean(this);

		return borrowBook;
	}

	public BorrowBook removeBorrowBook(BorrowBook borrowBook) {
		getBorrowBooks().remove(borrowBook);
		borrowBook.setIsbnBean(null);

		return borrowBook;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}