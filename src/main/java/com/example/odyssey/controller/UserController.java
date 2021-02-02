package com.example.odyssey.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.odyssey.models.User;

import com.example.odyssey.services.UserService;
import com.example.odyssey.validator.UserValidator;

@Controller
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
 
	@RequestMapping("/myAccount")
	public String myAccount(@ModelAttribute("user") User user) {
		return "myAccount";
	}
	
	@RequestMapping("/myAccount/login")
	public String myAccountLogin(@ModelAttribute("user") User user) {
		return "login";
	}
	
	@RequestMapping("/myAccount/register")
	public String myAccountRegister(@ModelAttribute("user") User user) {
		return "register";
	}
    
    @RequestMapping(value="/myAccount/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        // if result has errors, return the registration page 
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    	userValidator.validate(user, result);
    	if (result.hasErrors()) {
            return "register";
        } else {
        	user.setRole("USER");
        	User userSaved = userService.registerUser(user);
        	session.setAttribute("userId", userSaved.getId());
        	session.setAttribute("userName", userSaved.getUserName());
        	session.setAttribute("userRole", userSaved.getRole());
            return "redirect:/";
        }
    }
    
    @RequestMapping(value="/myAccount/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session,@ModelAttribute("user") User user) {
        // if the user is authenticated, save their user id in session
        // else, add error messages and return the login page
    	if(userService.authenticateUser(email, password) == true) {
    			User user1 = userService.findByEmail(email);
 				session.setAttribute("userId", user1.getId());
 				session.setAttribute("userName", user1.getUserName());
 				session.setAttribute("userRole", user1.getRole());
 				return "redirect:/";
    		
    	} else {
    		model.addAttribute("error","Invalid Credentials. Please try again.");
    		return "login";
    	}
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	session.invalidate();
    	return "redirect:/";
    }
}
