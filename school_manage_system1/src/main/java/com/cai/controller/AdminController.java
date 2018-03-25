package com.cai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cai.po.PageBean;
import com.cai.po.StudentCustom;
import com.cai.service.StudentService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "showStudent")
	public String showStudent(Model model,PageBean pb) throws Exception{
		
		List<StudentCustom> l = studentService.findAll();
		pb.setTotalRecord(l.size());
		pb.caculateSomePer();
		List<StudentCustom> list = studentService.findByPage(pb);
		
		model.addAttribute("list", list);
		model.addAttribute("page", pb);
		
		return "admin/showStudent";
	}
}
