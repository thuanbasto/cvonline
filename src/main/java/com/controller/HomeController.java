package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.SkillDTO;
import com.model.UserDTO;
import com.service.DetailSkillService;
import com.service.SkillService;
import com.service.UserService;
import com.validator.UserValidator;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	SkillService skillService;
	
	@Autowired
	DetailSkillService detailSkillService;
	
	@GetMapping(value= {"/home","/"})
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/{username}")
	public String cvUser(@PathVariable(name="username", required=false) String username, HttpServletRequest request) {
		if (username != null) {
			try {
				UserDTO user = userService.getUserByUserName(username);
				List<SkillDTO> listSkill = skillService.getAllSkillsByUserId(user.getUser_ID());
				for (SkillDTO skillDTO : listSkill) {
					skillDTO.setDetailSkills(detailSkillService.getAllDetailSkillsBySkillId(skillDTO.getSkill_ID()));
				}
				user.setSkills(listSkill);
				request.setAttribute("user", user);
			} catch (Exception e) {
				return "redirect:/home";
			}
			
			return "cvUser";
		}
		return "home";
	}
	
	@GetMapping("/signUp")
	public String signUpPage(@ModelAttribute("userDTO") UserDTO userDTO) {
		return "signUp";
	}
	
	@PostMapping("/signUp")
	public String signUp(@ModelAttribute("userDTO") UserDTO userDTO,
			BindingResult bindingResult) {
		
		userValidator.validate(userDTO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "signUp";
		}
		userService.addUser(userDTO);
		return "redirect:/home";
	}
	
	@GetMapping("/signIn")
	public String signInPage(HttpServletRequest request,
			@RequestParam(name="error" ,required=false) String error) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean anonymous = authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]");

		if (anonymous && error == null)
			return "signIn";
		if (anonymous && error != null)
			if (error.equals("failed")) 
				request.setAttribute("msg", "Username or password is incorret.");
			else 
				return "deny";
		if (!anonymous && error != null)
			if (error.equals("failed")) 
				return "redirect:/home";
			else 
				return "deny";
		if (!anonymous && error == null)
			return "redirect:/home";
		
		return "signIn";
	}

}
