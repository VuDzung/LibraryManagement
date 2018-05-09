package com.dxc.librarymanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibBorrowBook;
import com.dxc.librarymanagement.entities.LibUser;
@Repository
public interface LibBorrowBookDAO extends JpaRepository<LibBorrowBook, Integer> {
	List<LibBorrowBook> findByUser(LibUser user);
}
