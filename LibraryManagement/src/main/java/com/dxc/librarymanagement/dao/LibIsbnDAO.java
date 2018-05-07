package com.dxc.librarymanagement.dao;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======

>>>>>>> 1a456babfcf0c8b2cea1824a55da9972ea83ae04
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibIsbn;

<<<<<<< HEAD
public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
	Page<LibIsbn> findAll(Pageable pageable);
=======
@Repository
public interface LibIsbnDAO extends JpaRepository<LibIsbn, String> {
>>>>>>> 1a456babfcf0c8b2cea1824a55da9972ea83ae04
}
