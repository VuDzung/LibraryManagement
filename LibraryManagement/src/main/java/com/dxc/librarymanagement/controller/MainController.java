package com.dxc.librarymanagement.controller;

import java.security.Principal;

import com.dxc.librarymanagement.service.BookServiceImpl;
import com.dxc.librarymanagement.service.UserServiceImpl;
import com.dxc.librarymanagement.utils.WebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private BookServiceImpl bookServiceImpl;
    @Autowired
	private UserServiceImpl userServiceImpl;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "login";
    }

    @RequestMapping(value = { "/ticketmanagement" }, method = RequestMethod.GET)
    public String welcometicketmanagement(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "ticketmanagement";
    }

    @RequestMapping(value = { "/bookmanagement" }, method = RequestMethod.GET)
    public String welcomebookmanagement(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "bookmanagement";
    }

    @RequestMapping(value = { "/reportsTicket" }, method = RequestMethod.GET)
    public String welcomereportsTicket(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "reportsTicket";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("numofpage", this.bookServiceImpl.getPaginatePageNum());
        model.addAttribute("numofpageuser", this.userServiceImpl.getPaginatePageNum());
        return "admin2";
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
        model.addAttribute("listnewbooks", this.bookServiceImpl.getNewBook());
        model.addAttribute("numofpage", this.bookServiceImpl.getPaginatePageNum());
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
}
