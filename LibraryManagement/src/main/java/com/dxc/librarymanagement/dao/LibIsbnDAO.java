package com.dxc.librarymanagement.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibIsbn;

public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
	@Query(value = "select top 10 * from LibISBN where Status = 1 order by IdBook desc", nativeQuery = true)
	List<LibIsbn> findFirst10();

	@Query("FROM LibIsbn li WHERE li.status = True")
	Page<LibIsbn> findAll(Pageable pageable);

	long countByStatus(boolean a);

	@Query("FROM LibIsbn li WHERE li.status = true and li.isbn = :isbn")
	LibIsbn findByIsbn(@Param("isbn") String isbn);

	List<LibIsbn> findByBook(LibBook book);
	
	@Query("FROM LibIsbn li WHERE li.status = true AND li.book IN :books")
	List<LibIsbn> findByBookIn(@Param("books")Iterable<LibBook> books);
	
	@Query(value="select num from (select *,ROW_NUMBER() OVER ( ORDER BY LibISBN.Status desc ) as num from LibISBN) as lib where ISBN=:isbn", nativeQuery = true)
	int getLibISBNRecordNum(@Param("isbn") String isbn);
}