package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ISBN database table.
 * 
 */
@Entity
@Table(name = "LibISBN")
// @NamedQuery(name = "Isbn.findAll", query = "SELECT i FROM Isbn i")
public class LibIsbn implements Serializable {
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

    // bi-directional many-to-one association to Book
    @ManyToOne
    @JoinColumn(name = "IdBook")
    private LibBook book;

    public LibIsbn() {
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

    public LibBook getBook() {
        return this.book;
    }

    public void setBook(LibBook book) {
        this.book = book;
    }

}