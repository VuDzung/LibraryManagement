package com.dxc.librarymanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the Book database table.
 * 
 */
@Entity
@Table(name = "LibBook")
// @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class LibBook implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBook", nullable = false)
    private int idBook;

    @Column(name = "Author")
    private String author;

    @Column(name = "PublishYear")
    private int publishYear;

    @Column(name = "Image")
    private String image;

    @Column(name = "ShortDescription")
    private String shortDescription;

    @Column(name = "TitleOfBook")
    private String titleOfBook;

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

    public int getPublishYear() {
        return this.publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}