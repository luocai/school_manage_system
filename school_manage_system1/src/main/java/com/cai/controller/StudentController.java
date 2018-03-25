package com.cai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cai.po.CourseCustom;
import com.cai.po.PageBean;
import com.cai.service.CourseService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = "showCourse")
	public String showStudent(Model model, PageBean pb) throws Exception{
		
		List<CourseCustom> l = courseService.findAll();
		pb.setTotalRecord(l.size());
		pb.caculateSomePer();
		List<CourseCustom> list = courseService.findByPaging(pb);
System.out.println("pb");
		model.addAttribute("page", pb);
		model.addAttribute("list", list);
		
		return "student/showCourse";
	}
	
}
