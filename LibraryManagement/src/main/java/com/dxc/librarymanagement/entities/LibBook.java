package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Date;

/**
 * The persistent class for the Book database table.
 * 
 */
@Entity
@Table(name="LibBook")
//@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class LibBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "IdBook", nullable = false)
	private int idBook;

	@Column(name = "Author")
	private String author;

	@Column(name = "PublishYear")
	@Temporal(TemporalType.DATE)
	private Date publishYear;

	@Column(name = "ShortDescription")
	private String shortDescription;

	@Column(name = "TitleOfBook")
	private String titleOfBook;

	// bi-directional many-to-one association to Isbn
	@OneToMany(mappedBy = "book")
	private List<LibIsbn> isbns;

	public LibBook() {
	}

	public int getIdBook() {
		return this.idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishYear() {
		return this.publishYear;
	}

	public void setPublishYear(Date publishYear) {
		this.publishYear = publishYear;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getTitleOfBook() {
		return this.titleOfBook;
	}

	public void setTitleOfBook(String titleOfBook) {
		this.titleOfBook = titleOfBook;
	}

	public List<LibIsbn> getIsbns() {
		return this.isbns;
	}

	public void setIsbns(List<LibIsbn> isbns) {
		this.isbns = isbns;
	}

	public LibIsbn addIsbn(LibIsbn isbn) {
		getIsbns().add(isbn);
		isbn.setBook(this);

		return isbn;
	}

	public LibIsbn removeIsbn(LibIsbn isbn) {
		getIsbns().remove(isbn);
		isbn.setBook(null);

		return isbn;
	}

}