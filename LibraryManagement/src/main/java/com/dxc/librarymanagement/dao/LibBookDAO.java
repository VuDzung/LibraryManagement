package com.dxc.librarymanagement.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;

public interface LibBookDAO extends JpaRepository<LibBook, Integer>{

	LibBook findByTitleOfBookAndAuthor(String titleOfBook, String author);
	List<LibBook> findTop10ByOrderByIdBookDesc();
	Page<LibBook> findAll(Pageable pageable);
}
