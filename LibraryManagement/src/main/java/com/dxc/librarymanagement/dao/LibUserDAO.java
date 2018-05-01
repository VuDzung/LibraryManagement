package com.dxc.librarymanagement.dao;

import com.dxc.librarymanagement.entities.LibUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibUserDAO extends JpaRepository<LibUser, Integer> {
    LibUser findByUserName(String username);
}
