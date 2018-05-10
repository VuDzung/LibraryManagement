package com.dxc.librarymanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;

public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
	@Query(value="select top 10 * from LibISBN where Status = 1 order by IdBook desc", nativeQuery=true)//  Order by LibIsbn.IdBook desc
	List<LibIsbn> findFirst10();

	@Query("FROM LibIsbn li WHERE li.status = True and li.isbn = :isbn")
	LibIsbn findByIsbn(@Param("isbn") String isbn);

	List<LibIsbn> findByBook(LibBook book);
	
	List<LibIsbn> findByBookIn(Iterable<LibBook> books);
}