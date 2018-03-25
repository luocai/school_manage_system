package com.cai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cai.po.Userlogin;
import com.cai.service.UserloginService;

@Controller

public class LoginController {
	
	@Autowired
	private UserloginService userloginService;
	
	@RequestMapping(value = "login", method = {RequestMethod.GET})
	public String tologin() throws Exception{
System.out.println("tologin");
		return "../../login";
	}
	
	@RequestMapping(value = "login" , method = {RequestMethod.POST})
	public String login(Userlogin userlogin ) throws Exception{
System.out.println(userlogin);		
		Userlogin user = userloginService.findByName(userlogin.getUsername());
System.out.println(user);
		if (user != null || user.getPassword().equals(userlogin.getPassword())){
			if (user.getUsername().equals("admin")){
				return "redirect:/admin/showStudent";
			}else if (user.getUsername().equals("student")){
				return "redirect:/teacher/showCourse";
			}else{
				return "redirect:/student/showCourse";
			}
		}
		return "login";
	}
}
