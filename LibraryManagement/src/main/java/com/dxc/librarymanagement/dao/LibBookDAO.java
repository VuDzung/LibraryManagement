package com.dxc.librarymanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibBook;

@Repository
public interface LibBookDAO extends JpaRepository<LibBook, Integer> {

	List<LibBook> findFirst7ByTitleOfBookContaining(String titleOfBook);

	LibBook findByTitleOfBookAndAuthor(String titleOfBook, String author);

	List<LibBook> findTop10ByOrderByIdBookDesc();

}
