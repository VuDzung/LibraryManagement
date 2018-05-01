package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import com.dxc.librarymanagement.dao.RoleDAO;
import com.dxc.librarymanagement.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.dxc.librarymanagement.entities.User myUser = this.userDAO.findUserAccount(userName);

		if (myUser == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		System.out.println("Found User: " + myUser);

		// [ROLE_USER, ROLE_ADMIN,..]
		String roleNames = this.roleDAO.getRoleNames(myUser.getRole().getIdRole());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
		grantList.add(authority);

		UserDetails userDetails = (UserDetails) new User(myUser.getUserName(), //
				myUser.getPassword(), grantList);

		return userDetails;
	}
}
