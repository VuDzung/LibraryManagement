package com.dxc.librarymanagement.dao;

import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;

public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
	@Query("from LibIsbn order by IdBook desc")
	List<LibIsbn> findFirst10(Pageable pageable);

	@Query("FROM LibIsbn li WHERE li.status = True and li.isbn = :isbn")
	LibIsbn findByIsbn(@Param("isbn") String isbn);

	List<LibIsbn> findByBook(LibBook book);
	
	List<LibIsbn> findByBookIn(Iterable<LibBook> books);
}