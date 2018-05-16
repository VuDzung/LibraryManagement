package com.dxc.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dxc.librarymanagement.entities.LibUser;
import com.dxc.librarymanagement.service.UserServiceImpl;

@Controller
public class RegisterController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute("libuser", new LibUser());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerForm(@ModelAttribute(value = "libuser") LibUser libUser) {
		return new ModelAndView(this.userServiceImpl.saveUser(libUser));

	}
}
