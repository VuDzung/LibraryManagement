package com.dxc.librarymanagement.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dxc.librarymanagement.service.IsbnServiceImpl;
import com.dxc.librarymanagement.service.UserServiceImpl;
import com.dxc.librarymanagement.utils.WebUtils;

@Controller
public class MainController {

	@Autowired
	private UserServiceImpl userservice;
	@Autowired
	private IsbnServiceImpl isbnservice;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "login";
	}

	@RequestMapping(value = { "/admin/ticketmanagement" }, method = RequestMethod.GET)
	public String welcometicketmanagement(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("numofpageuser", this.userservice.getPaginatePageNum());
		return "ticketmanagement";
	}
	@RequestMapping(value = "/admin/ticketmanagement", params = {"txtSearch"}, method = RequestMethod.GET)
	public String welcometicketmanagementAdmin(Model model, @RequestParam(value="txtSearch") String txtSearch, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("txtSearch", txtSearch);
		return "ticketmanagement";
	}

	@RequestMapping(value = { "/admin/bookmanagement" }, method = RequestMethod.GET)
	public String welcomebookmanagement(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("numofpagebook", this.isbnservice.getPaginatePageNum());
		return "bookmanagement";
	}
	@RequestMapping(value = "/admin/bookmanagement", params = {"txtSearch"}, method = RequestMethod.GET)
	public String searchBookAdmin(Model model, @RequestParam(value="txtSearch") String txtSearch, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("txtSearch", txtSearch);
		return "bookmanagement";
	}

	@RequestMapping(value = { "/admin/reportsticket" }, method = RequestMethod.GET)
	public String welcomereportsTicket(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		return "reportsticket";
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
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("listnewbooks", this.isbnservice.getNewBook());
		model.addAttribute("numofpage", this.isbnservice.getPaginatePageNum());
		return "home";
	}

	@RequestMapping(value = "/home", params = {"txtSearch"}, method = RequestMethod.GET)
	public String searchBook(Model model, Principal principal, @RequestParam(value="txtSearch") String txtSearch) {

		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("txtSearch", txtSearch);
		return "home";
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
	@RequestMapping(value = "/home/history")
	public String history(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		return "history";
	}
	
	@RequestMapping(value = "/home/policy")
	public String policy(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		return "policy";
	}
	
	@RequestMapping(value= "/home/service-schedule")
	public String service(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("User Name: " + userName);
		List<String> userInfo = new ArrayList<>();
		userInfo.add(this.userservice.findByUserName(principal.getName()).getFullName());
		userInfo.add(this.userservice.findByUserName(principal.getName()).getRole().getNameRole());
		model.addAttribute("userInfo", userInfo);
		return "service";
	}
}
