package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the BorrowBook database table.
 * 
 */
@Entity
@NamedQuery(name = "BorrowBook.findAll", query = "SELECT b FROM BorrowBook b")
public class BorrowBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IdBorrow")
	private int idBorrow;

	@Column(name = "DateBorrow")
	private Object dateBorrow;

	@Column(name = "DateReturn")
	private Object dateReturn;

	// bi-directional many-to-one association to Isbn
	@ManyToOne
	@JoinColumn(name = "ISBN")
	private Isbn isbnBean;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "IdUser")
	private User user;

	public BorrowBook() {
	}

	public int getIdBorrow() {
		return this.idBorrow;
	}

	public void setIdBorrow(int idBorrow) {
		this.idBorrow = idBorrow;
	}

	public Object getDateBorrow() {
		return this.dateBorrow;
	}

	public void setDateBorrow(Object dateBorrow) {
		this.dateBorrow = dateBorrow;
	}

	public Object getDateReturn() {
		return this.dateReturn;
	}

	public void setDateReturn(Object dateReturn) {
		this.dateReturn = dateReturn;
	}

	public Isbn getIsbnBean() {
		return this.isbnBean;
	}

	public void setIsbnBean(Isbn isbnBean) {
		this.isbnBean = isbnBean;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}