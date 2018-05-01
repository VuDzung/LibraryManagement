package com.dxc.librarymanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibUser;

@Repository
public interface TuanDAO extends JpaRepository<LibUser, Integer> {

    LibUser findByUserName(String username);
}
