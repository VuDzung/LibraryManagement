package com.dxc.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dxc.librarymanagement.entities.LibBorrowBook;

public interface LibBorrowBookDAO extends JpaRepository<LibBorrowBook, Integer> {

}
