package com.dxc.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.librarymanagement.entities.LibIsbn;

public interface LibIsbnDAO extends JpaRepository<LibIsbn, String > {

}
