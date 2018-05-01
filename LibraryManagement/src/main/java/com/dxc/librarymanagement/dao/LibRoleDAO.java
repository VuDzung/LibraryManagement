package com.dxc.librarymanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.dxc.librarymanagement.entities.LibRole;
import com.dxc.librarymanagement.entities.LibUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LibRoleDAO extends JpaRepository<LibRole, Integer> {
    LibRole findAllById(int id);
}
