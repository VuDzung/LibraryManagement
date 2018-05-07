package com.dxc.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.librarymanagement.entities.LibBook;

public interface LibBookDAO extends JpaRepository<LibBook, Integer> {

	LibBook findByTitleOfBookAndAuthor(String titleOfBook, String author);
}
