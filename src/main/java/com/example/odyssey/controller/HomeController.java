package com.example.odyssey.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.odyssey.models.Piano;
import com.example.odyssey.models.User;

import com.example.odyssey.services.UserService;

@Controller
public class HomeController {
	
    private final UserService userService;
    
    public HomeController(UserService userService) {
        this.userService = userService;     

    }
    
	@RequestMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "index";
	}
	
	@RequestMapping("/chat")
    public String chat(HttpSession session,  Model model) {
    	Long id = (Long)session.getAttribute("userId");
    	User user = userService.findUserById(id);
    	model.addAttribute("userName",user.getUserName());

    	return "chat";
    }
	
	@RequestMapping("/video")
    public String video(HttpSession session,  Model model) {
    	Long id = (Long)session.getAttribute("userId");
    	User user = userService.findUserById(id);
    	model.addAttribute("userName",user.getUserName());

    	return "webrtc_video";
    }
	
	@RequestMapping("/maps")
	public String maps(HttpSession session,  Model model) {
		Long id = (Long)session.getAttribute("userId");
	   	User user = userService.findUserById(id);
	    model.addAttribute("user",user);

	    return "maps";
	}
	
}
