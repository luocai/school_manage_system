package com.cai.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cai.po.CourseCustom;
import com.cai.po.PageBean;
import com.cai.po.SelectedcourseCustom;
import com.cai.po.StudentCustom;
import com.cai.service.CourseService;
import com.cai.service.SelectedCourseService;
import com.cai.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private SelectedCourseService selectedCourseService;
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "showCourse")
	public String showStudent(Model model, PageBean pb) throws Exception{
		
		List<CourseCustom> l = courseService.findAll();
		pb.setTotalRecord(l.size());
		pb.caculateSomePer();
		List<CourseCustom> list = courseService.findByPage(pb);
System.out.println("pb");
		model.addAttribute("page", pb);
		model.addAttribute("list", list);
		
		return "student/showCourse";
	}
	
	 // 选课操作
    @RequestMapping(value = "stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedcourseCustom selectedCourseCustom = new SelectedcourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        SelectedcourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
//            throw new CustomException("该门课程你已经选了，不能再选");
        	System.out.println("该门课程你已经选了，不能再选");
        }

        return "redirect:/student/selectCourse";
    }

    // 退课操作
    @RequestMapping(value = "outCourse")
    public String outCourse(int id) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedcourseCustom selectedCourseCustom = new SelectedcourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        selectedCourseService.remove(selectedCourseCustom);

        return "redirect:/student/selectCourse";
    }

    // 已选课程
    @RequestMapping(value = "selectCourse")
    public String selectedCourse(Model model) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedcourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/selectCourse";
    }

    // 已修课程
    @RequestMapping(value = "overCourse")
    public String overCourse(Model model) throws Exception {

        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedcourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/overCourse";
    }

    //修改密码
    @RequestMapping(value = "passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }
}
