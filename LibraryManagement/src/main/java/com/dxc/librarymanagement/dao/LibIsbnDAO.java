package com.dxc.librarymanagement.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dxc.librarymanagement.entities.LibIsbn;

public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
	@Query("from LibIsbn i where i.status = True")
	Page<LibIsbn> findAll(Pageable pageable);

	LibIsbn findByIsbn(String isbn);
}