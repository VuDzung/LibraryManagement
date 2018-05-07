package com.dxc.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibBorrowBook;
@Repository
public interface LibBorrowBookDAO extends JpaRepository<LibBorrowBook, Integer> {

}
