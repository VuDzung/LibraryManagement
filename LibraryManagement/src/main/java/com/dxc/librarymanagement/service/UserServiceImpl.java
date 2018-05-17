package com.dxc.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.librarymanagement.dao.LibUserDAO;
import com.dxc.librarymanagement.dto.UserDTO;
import com.dxc.librarymanagement.entities.LibUser;

@Service
@Transactional
public class UserServiceImpl {
	@Autowired
	private LibUserDAO libUserDAO;
	@Autowired
	private RoleServiceImpl roleServices;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${LimitRecords}")
	private int LimitRecords;

	public LibUser saveUser(LibUser libUser) {
		return libUserDAO.save(libUser);
	}

	public List<LibUser> findByUserNameContaining(String username) {
		return libUserDAO.findFirst10ByUserNameContaining(username);
	}

	public LibUser findByIdUser(int iduser) {
		return libUserDAO.findByIdUser(iduser);
	}

	public LibUser findByUserName(String username) {
		return this.libUserDAO.findByUserName(username);
	}

	// get number of page for paginate in database
	public int getPaginatePageNum() {
		double records = this.libUserDAO.count();
		double pageNum = records / this.LimitRecords;
		return (int) Math.ceil(pageNum);
	}

	// get users paginate
	public List<LibUser> getPaginateUsers(int number) {
		Pageable pageable = PageRequest.of(number - 1, this.LimitRecords);
		return libUserDAO.findAll(pageable).getContent();
	}

	// edit user
	public String editTicket(UserDTO userDTO) {
		LibUser libuser = this.libUserDAO.findByIdUser(userDTO.getIduser());
		if (libuser == null)
			return "User Not Exist!";
		if (!libuser.getUserName().equals(userDTO.getUsername())
				|| !libuser.getRole().getNameRole().equals(userDTO.getRole())
				|| libuser.getBorrowedNumber() != userDTO.getBorrowed()) {
			return "Can Not Change Usename, User's Role, User' Borrowed Number!";
		}
		libuser.setFullName(userDTO.getFullname());
		libuser.setLimitNumber(userDTO.getLimit());
		this.libUserDAO.save(libuser);
		return "Edit Successful!";
	}
	//add user
	public List<String> addUser(UserDTO userDTO) {
		List<String> status = new ArrayList<>();
		if (userDTO.getUsername().trim() != "" && userDTO.getUsername() != null && userDTO.getPassword().trim() != ""
				&& userDTO.getPassword() != null && userDTO.getFullname() != null
				&& userDTO.getFullname().trim() != "") {
			if (findByUserName(userDTO.getUsername()) != null) {
				status.add("");
				status.add("Username Already Exist!");
				return status;
			} else {
				LibUser libuser = new LibUser();
				libuser.setUserName(userDTO.getUsername());
				libuser.setFullName(userDTO.getFullname());
				libuser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
				libuser.setRole(this.roleServices.findByNameRole(userDTO.getRole()));
				libuser.setLimitNumber(userDTO.getLimit());
				libuser.setBorrowedNumber(0);
				this.libUserDAO.save(libuser);
				status.add(String.valueOf(this.getPaginatePageNum()));
				status.add("Add User Successful!");
				return status;
			}

		} else {
			status.add("");
			status.add("Please Fill All Information!");
			return status;
		}
	}

}
