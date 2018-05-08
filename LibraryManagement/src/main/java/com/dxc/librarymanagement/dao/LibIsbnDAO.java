package com.dxc.librarymanagement.dao;

import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dxc.librarymanagement.entities.LibIsbn;

public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
	@Query("FROM LibIsbn li WHERE li.status = True")
	Page<LibIsbn> findAll(Pageable pageable);
	@Query("FROM LibIsbn li WHERE li.status = True and li.isbn = :isbn")
	LibIsbn findByIsbn(@Param("isbn") String isbn);
}