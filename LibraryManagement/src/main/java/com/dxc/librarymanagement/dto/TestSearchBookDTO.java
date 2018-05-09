package com.dxc.librarymanagement.dto;

public class TestSearchBookDTO {
	private String title;
	private String author;
	private int id;

	public TestSearchBookDTO(String title, int id, String author) {
		super();
		this.title = title;
		this.author = author;
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
