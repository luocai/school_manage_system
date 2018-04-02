package com.cai.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cai.po.CourseCustom;
import com.cai.po.SelectedcourseCustom;
import com.cai.po.Userlogin;
import com.cai.service.CourseService;
import com.cai.service.SelectedCourseService;
import com.cai.service.TeacherService;
import com.cai.service.UserloginService;


@Controller
@RequestMapping("teacher")
public class TeacherController {
	
	@Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;
    
    @Autowired
    private UserloginService userloginService;
    // 显示我的课程
    @RequestMapping(value = "showCourse")
    public String stuCourseShow(Model model) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);

        return "teacher/showCourse";
    }

    // 显示成绩
    @RequestMapping(value = "gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        List<SelectedcourseCustom> list = selectedCourseService.findByCourseID(id);
        model.addAttribute("selectedCourseList", list);
        return "teacher/showGrade";
    }

    // 打分
    @RequestMapping(value = "mark", method = {RequestMethod.GET})
    public String markUI(SelectedcourseCustom scc, Model model) throws Exception {

        SelectedcourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);
System.out.println("mark");
        return "teacher/mark";
    }

    // 打分
    @RequestMapping(value = "mark", method = {RequestMethod.POST})
    public String mark(SelectedcourseCustom scc) throws Exception {

        selectedCourseService.updataOne(scc);

        return "redirect:/teacher/gradeCourse?id="+scc.getCourseid();
    }
    
    //修改密码
    @RequestMapping(value = "passwordRest", method = {RequestMethod.GET})
    public String passwordRestUI()throws Exception{
    	
    	return "teacher/passwordRest";
    }

    //修改密码
    @RequestMapping(value = "passwordRest",method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1,String password2) throws Exception {
    	
    	Subject subject = SecurityUtils.getSubject();
    	String username = (String)subject.getPrincipal();
 System.out.println(username);
 		Userlogin userlogin = userloginService.findByName(username);
 		if (userlogin.getPassword().equals(oldPassword)){
 			if (password1.equals(password2)){
 				userlogin.setPassword(password1);
 				userloginService.updateByName(username, userlogin);
 			}
 		}else{
 			System.out.println("密码输入错误");
 		}
    	
        return "redirect:/login";
    }
    
    @RequestMapping(value = "logout")
    public String logout()throws Exception{
    	return "redirect:/login";
    }
}
