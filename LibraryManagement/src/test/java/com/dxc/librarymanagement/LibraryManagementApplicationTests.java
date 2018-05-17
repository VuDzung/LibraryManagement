package com.dxc.librarymanagement;

import static org.junit.Assert.assertEquals;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.dxc.librarymanagement.dao.LibIsbnDAO;
import com.dxc.librarymanagement.dto.BookDTO;
import com.dxc.librarymanagement.dto.UserDTO;
import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.entities.LibBorrowBook;
import com.dxc.librarymanagement.entities.LibIsbn;
import com.dxc.librarymanagement.entities.LibRole;
import com.dxc.librarymanagement.entities.LibUser;
import com.dxc.librarymanagement.service.BookServiceImpl;
import com.dxc.librarymanagement.service.BorrowBookServiceImpl;
import com.dxc.librarymanagement.service.IsbnServiceImpl;
import com.dxc.librarymanagement.service.RoleServiceImpl;
import com.dxc.librarymanagement.service.UserServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryManagementApplicationTests {

	@Autowired
	IsbnServiceImpl isbnService;
	@Autowired
	BookServiceImpl bookService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	RoleServiceImpl roleservice;
	@Autowired
	BorrowBookServiceImpl borrowService;

	// TEST ISBN---------------------------------------------------------------------------------------
	@Test
	public void addIsbnValidTest() {
		BookDTO bookdto = new BookDTO();
		bookdto.setIsbn("184022570X");
		bookdto.setTitleOfBook("Sir Arthur Conan Doyle");
		bookdto.setAuthor("Conan Doyle");
		bookdto.setImage("book1.jpg");
		bookdto.setPublishYear(2007);
		bookdto.setTotalBook(1);
		bookdto.setShortDescription("This work aims to reveal Conan Doyle's different activities. Apart from being a prolific author - his literary output included historical novels, science fiction and histories of the Boer War and the First World War, he was an early champion of the Channel Tunnel, he played cricket for the MCC, was an advocate of Spiritualism, introduced cross country skiing to Switzerland and he was acquainted with many public figures of the late Victorian and Edwardian period.");
		List<String> result = new ArrayList<>();
		result.add("1");
		result.add("Add Successful!");
		result.add("1");
		assertEquals(result, isbnService.addIsbn(bookdto));			
		
	}
	
	@Test
	public void addIsbnInvalidTest() {
		BookDTO bookdto = new BookDTO();
		bookdto.setIsbn("1840225");
		bookdto.setTitleOfBook("Sir Arthur Conan Doyle");
		bookdto.setAuthor("Conan Doyle");
		bookdto.setImage("book1.jpg");
		bookdto.setPublishYear(2007);
		bookdto.setTotalBook(1);
		bookdto.setShortDescription("This work aims to reveal Conan Doyle's different activities. Apart from being a prolific author - his literary output included historical novels, science fiction and histories of the Boer War and the First World War, he was an early champion of the Channel Tunnel, he played cricket for the MCC, was an advocate of Spiritualism, introduced cross country skiing to Switzerland and he was acquainted with many public figures of the late Victorian and Edwardian period.");
		List<String> result = new ArrayList<>();
		result.add("");
		result.add("ISBN Code Is Not Valid!");
		assertEquals(result, isbnService.addIsbn(bookdto));			
		
	}
	
	@Test
	public void addIsbnWhenTitleIsNullTest() {
		BookDTO bookdto = new BookDTO();
		bookdto.setIsbn("184022570X");
		bookdto.setTitleOfBook(null);
		bookdto.setAuthor("Conan Doyle");
		bookdto.setImage("book1.jpg");
		bookdto.setPublishYear(2007);
		bookdto.setTotalBook(1);
		bookdto.setShortDescription("This work aims to reveal Conan Doyle's different activities. Apart from being a prolific author - his literary output included historical novels, science fiction and histories of the Boer War and the First World War, he was an early champion of the Channel Tunnel, he played cricket for the MCC, was an advocate of Spiritualism, introduced cross country skiing to Switzerland and he was acquainted with many public figures of the late Victorian and Edwardian period.");
		List<String> result = new ArrayList<>();
		result.add("");
		result.add("Please Fill All Information!");
		assertEquals(result, isbnService.addIsbn(bookdto));	
	}
	
	@Test
	public void deleteIsbnValidTest() {
		List<String> result = new ArrayList<>();
		result.add("1");
		result.add("Delete Successful!");
		assertEquals(result, isbnService.deleteIsbn("9781439144404"));
	}
	@Test
	public void deleteIsbnWhenIsbnInvalidTest() {
		List<String> result = new ArrayList<>();
		result.add("");
		result.add("ISBN Code Is Not Correct!");
		assertEquals(result, isbnService.deleteIsbn("980545010221"));
	}
	
// TEST USER SERVICE-----------------------------------------------------------------------------------------------
	
	@Test
	public void registerValidTest() {
		LibUser user = new LibUser();
		user.setUserName("bachbo@gmail.com");
		user.setPassword("123456");
		user.setFullName("Hoang Bach");
		assertEquals("redirect:register?error=true", userService.saveUser(user));
		
	}
	@Test
	public void registerInvalidTest() {
		LibUser user = new LibUser();
		user.setUserName("bachbo@gmail.com");
		user.setPassword("");
		user.setFullName("Hoang Bach");
		assertEquals("redirect:register?error=true", userService.saveUser(user));
		
	}
	
	@Test
	public void editTicketWhenUserNotExistTest() {
		UserDTO userdto = new UserDTO();
		userdto.setIduser(100);
		assertEquals("User Not Exist!", userService.editTicket(userdto));
	}
	
	@Test
	public void editTicketWhenEditUsernameTest() {
		UserDTO userdto = new UserDTO();
		userdto.setIduser(1);
		userdto.setUsername("hoang@gmail.com");
		assertEquals("Can Not Change Usename, User's Role, User' Borrowed Number!", userService.editTicket(userdto));
	}
	
	@Test
	public void addUserTicketWhenUsernameIsNullTest() {
		UserDTO userdto = new UserDTO();
		userdto.setUsername("");
		userdto.setFullname("Vu Dung");
		userdto.setPassword("abc");
		userdto.setRole("Admin");
		userdto.setLimit(5);
		List<String> status = new ArrayList<>();
		status.add("");
		status.add("Please Fill All Information!");
		assertEquals(status, userService.addUserTicket(userdto));
		
	}
//TEST BORROW BOOK SERVICE
	@Test
	public void returnBorrowBookTest() {
		assertEquals("Return Successful!", borrowService.returnBorrowBook(1));
	}

}



















