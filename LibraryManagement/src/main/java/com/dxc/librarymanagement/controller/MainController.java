package com.dxc.librarymanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.librarymanagement.entities.LibBook;
import com.dxc.librarymanagement.service.BookServiceImpl;
import com.dxc.librarymanagement.service.IsbnServiceImpl;
import com.dxc.librarymanagement.service.UserServiceImpl;
import com.dxc.librarymanagement.utils.WebUtils;

@Controller
public class MainController {

	@Autowired
	private UserServiceImpl userservice;
	@Autowired
	private IsbnServiceImpl isbnservice;
	@Autowired
	private BookServiceImpl bookServiceImpl;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "login";
	}

	@RequestMapping(value = { "/ticketmanagement" }, method = RequestMethod.GET)
	public String welcometicketmanagement(Model model) {
		model.addAttribute("numofpageuser", this.userservice.getPaginatePageNum());
		return "ticketmanagement";
	}

	@RequestMapping(value = { "/bookmanagement" }, method = RequestMethod.GET)
	public String welcomebookmanagement(Model model) {
		model.addAttribute("numofpagebook", this.isbnservice.getPaginatePageNum());
		return "bookmanagement";
	}

	@RequestMapping(value = { "/reportsticket" }, method = RequestMethod.GET)
	public String welcomereportsTicket(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "reportsticket";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("numofpage", this.isbnservice.getPaginatePageNum());
		model.addAttribute("numofpageuser", this.userservice.getPaginatePageNum());
		return "admin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {

		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		System.out.println("User Name: " + userName);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("listnewbooks", this.isbnservice.getNewBook());
		model.addAttribute("numofpage", this.isbnservice.getPaginatePageNum());
		return "home";
	}

	@RequestMapping(value = "/home/search", method = RequestMethod.GET)
	public String searchBook(Model model, Principal principal, @RequestParam("txtSearch") String txtSearch) {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		System.out.println("User Name: " + userName);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
//		List<LibBook> listBook = this.bookServiceImpl.findFirst10ByTitleOfBookContaining(txtSearch);
//		model.addAttribute("listBook", listBook);
		return "homeSearchBook";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}
}
