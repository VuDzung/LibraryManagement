package com.dxc.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibBook;

<<<<<<< HEAD
public interface LibBookDAO extends JpaRepository<LibBook, Integer> {

	LibBook findByTitleOfBookAndAuthor(String titleOfBook, String author);
=======
@Repository
public interface LibBookDAO extends JpaRepository<LibBook, Integer> {

	LibBook findByTitleOfBookAndAuthor(String titleOfBook, String author);

	List<LibBook> findTop10ByOrderByIdBookDesc();

>>>>>>> 1a456babfcf0c8b2cea1824a55da9972ea83ae04
}
