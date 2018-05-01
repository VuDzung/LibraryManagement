package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.librarymanagement.dao.LibRoleDAO;
import com.dxc.librarymanagement.dao.TuanDAO;
import com.dxc.librarymanagement.entities.LibRole;
import com.dxc.librarymanagement.entities.LibUser;
import com.dxc.librarymanagement.dao.LibUserDAO;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private LibUserDAO libUserDAO;

    @Autowired
    private LibRoleDAO libRoleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        LibUser libUser = this.libUserDAO.findByUserName(userName);

        if (libUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException(
                    "User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + libUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        LibRole libRole = this.libRoleDAO.findAllById(libUser.getRole().getIdRole());
        String roleNames = libRole.getNameRole();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
        grantList.add(authority);

        UserDetails userDetails = (UserDetails) new User(libUser.getUserName(), //
                libUser.getPassword(), grantList);

        return userDetails;
    }
}
