package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the Book database table.
 * 
 */
@Entity
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IdBook")
	private int idBook;

	@Column(name = "Author")
	private Object author;

	@Column(name = "PublishYear")
	private Object publishYear;

	@Column(name = "ShortDescription")
	private Object shortDescription;

	@Column(name = "TitleOfBook")
	private Object titleOfBook;

	// bi-directional many-to-one association to Isbn
	@OneToMany(mappedBy = "book")
	private List<Isbn> isbns;

	public Book() {
	}

	public int getIdBook() {
		return this.idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public Object getAuthor() {
		return this.author;
	}

	public void setAuthor(Object author) {
		this.author = author;
	}

	public Object getPublishYear() {
		return this.publishYear;
	}

	public void setPublishYear(Object publishYear) {
		this.publishYear = publishYear;
	}

	public Object getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(Object shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Object getTitleOfBook() {
		return this.titleOfBook;
	}

	public void setTitleOfBook(Object titleOfBook) {
		this.titleOfBook = titleOfBook;
	}

	public List<Isbn> getIsbns() {
		return this.isbns;
	}

	public void setIsbns(List<Isbn> isbns) {
		this.isbns = isbns;
	}

	public Isbn addIsbn(Isbn isbn) {
		getIsbns().add(isbn);
		isbn.setBook(this);

		return isbn;
	}

	public Isbn removeIsbn(Isbn isbn) {
		getIsbns().remove(isbn);
		isbn.setBook(null);

		return isbn;
	}

}