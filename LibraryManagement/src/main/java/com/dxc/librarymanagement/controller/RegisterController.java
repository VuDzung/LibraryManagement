package com.dxc.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Value("${LimitNumberDefault}")
	private int LimitNumberDefault;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute("libuser", new LibUser());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerForm(@ModelAttribute(value = "libuser") LibUser libUser) {
		if (libUser.getUserName() != "" && libUser.getPassword() != ""
				&& userServiceImpl.findByUserName(libUser.getUserName()) == null && libUser.getRole()!=null) {
			libUser.setPassword(passwordEncoder.encode(libUser.getPassword()));
			libUser.setLimitNumber(LimitNumberDefault);
			this.userServiceImpl.saveUser(libUser);
			// return "login";
			return new ModelAndView("redirect:register?error=false");
		} else {
			return new ModelAndView("redirect:register?error=true");
		}

	}
}
