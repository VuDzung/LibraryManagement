package com.dxc.librarymanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.dxc.librarymanagement.entities.LibRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibRoleDAO {
	@Autowired
	private EntityManager entityManager;

	public String getRoleNames(int idRole) {

		String sql = "Select r from " + LibRole.class.getName() + " r " //
				+ " Where r.IdRole = :idRole ";
		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("idRole", idRole);
		return query.getSingleResult().toString();
	}
}
