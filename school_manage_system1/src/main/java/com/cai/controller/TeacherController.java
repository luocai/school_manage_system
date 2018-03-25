package com.cai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cai.po.PageBean;
import com.cai.service.CourseService;
import com.cai.service.TeacherService;

@Controller
@RequestMapping("teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseService courseService;
	public String showCourse(Model model, PageBean pageBean) throws Exception{
		
		return "teacher/showCourse";
		
	}
}
