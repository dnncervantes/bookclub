package com.dannielcervantes.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dannielcervantes.bookclub.models.LoginUser;
import com.dannielcervantes.bookclub.models.User;
import com.dannielcervantes.bookclub.services.UserService;

@Controller
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "logReg.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, Model model, HttpSession session) {
		//TO-DO call register method in the service
		userService.register(newUser, result);
		
		if(result.hasErrors()) {
			
			model.addAttribute("newLogin", new LoginUser());
			return "logReg.jsp";
		}
		session.setAttribute("userId", newUser.getId());
		return "redirect:/dashboard";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		// Add once service is implemented
		User user = userService.login(newLogin, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "logReg.jsp";
		}
		//To-do Store their ID from the DB in session
		session.setAttribute("userId", user.getId());
		session.setAttribute("name", user.getName()); //Lets you call name in dashboard
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
