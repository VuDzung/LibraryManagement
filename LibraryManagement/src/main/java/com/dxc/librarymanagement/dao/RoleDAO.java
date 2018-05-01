package com.dxc.librarymanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.dxc.librarymanagement.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAO {
	@Autowired
	private EntityManager entityManager;

	public String getRoleNames(int idRole) {

		String sql = "Select r from " + Role.class.getName() + " r " //
				+ " Where e.IdRole = :idRole ";
		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("idRole", idRole);
		return query.getSingleResult().toString();
	}
}
