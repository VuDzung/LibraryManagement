package com.dxc.librarymanagement.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibIsbn;

@Repository
public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
	Page<LibIsbn> findAll(Pageable pageable);

}