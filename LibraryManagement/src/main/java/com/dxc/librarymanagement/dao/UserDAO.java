package com.dxc.librarymanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.dxc.librarymanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAO {
	@Autowired
	private EntityManager entityManager;

	public User findUserAccount(String userName) {
		try {
//			String sql = "Select u from " + User.class.getName() + " u " //
//					+ " Where u.UserName = :userName ";
//			
//			Query query = entityManager.createQuery(sql, User.class);
//			query.setParameter("userName", userName);

			String sql2 = "Select * from " + User.class.getName() + " u " //
					+ " Where UserName = :userName ";
			Query query2 = entityManager.createNativeQuery(sql2);
			query2.setParameter("userName", userName);			
			
			return (User) query2.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
