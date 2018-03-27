package com.cai.controller;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
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
		
		UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(), userlogin.getPassword());
		Subject subject = SecurityUtils.getSubject();
		
		subject.login(token);
		
		if (subject.hasRole("admin")){
			return "redirect:/admin/showStudent";
		}else if (subject.hasRole("teacher")){
			return "redirect:/teacher/showCourse";
		}else if (subject.hasRole("student")){
			return "redirect:/student/showCourse";
		}
	
		return "login";
	}
}
