package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the BorrowBook database table.
 * 
 */
@Entity
@Table(name="BorrowBook")
//@NamedQuery(name = "BorrowBook.findAll", query = "SELECT b FROM BorrowBook b")
public class BorrowBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "IdBorrow")
	private int idBorrow;

	@Column(name = "DateBorrow")
	@Temporal(TemporalType.DATE)
	private Date dateBorrow;

	@Column(name = "DateReturn")
	@Temporal(TemporalType.DATE)
	private Date dateReturn;

	// bi-directional many-to-one association to Isbn
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ISBN")
	private Isbn isbnBean;

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
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

	public Date getDateBorrow() {
		return this.dateBorrow;
	}

	public void setDateBorrow(Date dateBorrow) {
		this.dateBorrow = dateBorrow;
	}

	public Date getDateReturn() {
		return this.dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
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