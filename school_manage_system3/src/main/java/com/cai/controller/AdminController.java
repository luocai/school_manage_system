package com.cai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cai.po.College;
import com.cai.po.CourseCustom;
import com.cai.po.PageBean;
import com.cai.po.StudentCustom;
import com.cai.po.TeacherCustom;
import com.cai.po.Userlogin;
import com.cai.service.CollegeService;
import com.cai.service.CourseService;
import com.cai.service.StudentService;
import com.cai.service.TeacherService;
import com.cai.service.UserloginService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private UserloginService userloginService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = "showStudent")
	public String showStudent(Model model,PageBean pb) throws Exception{
		
		List<StudentCustom> l = studentService.findAll();
		pb.setTotalRecord(l.size());
		pb.caculateSomePer();
		List<StudentCustom> list = studentService.findByPage(pb);
		
		//是否分页
		boolean flag = true;
		
		model.addAttribute("flag", flag);
		model.addAttribute("list", list);
		model.addAttribute("page", pb);
		
		return "admin/showStudent";
	}
	
	@RequestMapping(value = "editStudent", method = {RequestMethod.GET})
	public String editStudentUI(Integer id, Model model)throws Exception{
		
		StudentCustom student = studentService.findById(id);
		List<College> collegelist = collegeService.findAll();
		model.addAttribute("student", student);
		model.addAttribute("collegelist", collegelist);
		
		return "admin/editStudent";
	}
	
	@RequestMapping(value = "editStudent", method = {RequestMethod.POST})
	public String editStudent(StudentCustom studentCustom) throws Exception{
		
		studentService.updataById(studentCustom.getUserid(), studentCustom);
		
		return "redirect:/admin/showStudent";
	}
	
	@RequestMapping(value = "deleteStudent",method = {RequestMethod.GET})
	public String deleteStudent(Integer id) throws Exception{
	
		studentService.removeById(id);
		userloginService.removeByName(id.toString());
System.out.println("delete successful");
		
		return "redirect:showStudent";
	}
	
	@RequestMapping(value = "addStudent",method = {RequestMethod.GET})
	public String addStudentUI(Model model)throws Exception{
		
		List<College> collegelist = collegeService.findAll();
System.out.println(collegelist);
		model.addAttribute("collegelist", collegelist);
		
		return "admin/addStudent";
	}
	
	@RequestMapping(value = "addStudent", method = {RequestMethod.POST})
	public String addStudent(StudentCustom studentCustom) throws Exception{
		
		studentService.save(studentCustom);
		// 与之相关联的表也要进行改变
		Userlogin userlogin = new Userlogin();
		userlogin.setUserid(studentCustom.getUserid());
		userlogin.setUsername(studentCustom.getUsername());
		userlogin.setRole(2);
		userlogin.setPassword("123");
		userloginService.save(userlogin);
		
		return "redirect:showStudent";
	}
	
	@RequestMapping(value = "selectStudent",  method = {RequestMethod.POST})
	public String selectStudent(String findByName,Model model)throws Exception{
		
		List<StudentCustom> list = studentService.findByName(findByName);
		boolean flag = false;
		model.addAttribute("list", list);
for(StudentCustom s : list){
	System.out.println(s);
}
		model.addAttribute("flag", flag);
		
		return "admin/showStudent";
	}
	
	@RequestMapping(value = "showCourse", method = {RequestMethod.GET})
	public String showCourse(Model model,PageBean pb) throws Exception{
		
		List<CourseCustom> l = courseService.findAll();
		pb.setTotalRecord(l.size());
		pb.caculateSomePer();
		List<CourseCustom> list = courseService.findByPage(pb);
		boolean flag = true;
		
		model.addAttribute("flag", flag);
		model.addAttribute("courselist", list);
		model.addAttribute("page", pb);
		
		return "admin/showCourse";
		
	}
	
	@RequestMapping(value = "addCourse", method = {RequestMethod.GET})
	public String addCourseUI(Model model) throws Exception{
		
		List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.findAll();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("teacherList", list);

        return "admin/addCourse";
	}
	
	@RequestMapping(value = "addCourse", method = {RequestMethod.POST})
	public String addCourse(CourseCustom courseCustom)throws Exception{
		
		courseService.save(courseCustom);
		
		return "redirect:showCourse";
	}
	
	@RequestMapping(value = "selectCourse", method = {RequestMethod.POST})
	public String selectCourse(String name, Model model) throws Exception{
		
		List<CourseCustom> list = courseService.findByName(name);
		boolean flag = false;
		
		model.addAttribute("courselist", list);
		model.addAttribute("flag", flag);
		
		return "showCourse";
		
	}
	
	// 修改课程信息
    @RequestMapping(value = "editCourse", method = {RequestMethod.GET})
    public String editCourseUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showCourse";
        }
        CourseCustom courseCustom = courseService.findById(id);
//        if (courseCustom == null) {
//            throw new CustomException("未找到该课程");
//        }
        List<TeacherCustom> list = teacherService.findAll();
        List<College> collegeList = collegeService.findAll();

        model.addAttribute("teacherList", list);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("course", courseCustom);


        return "admin/editCourse";
    }

    //修改课程信息
    @RequestMapping(value = "editCourse", method = {RequestMethod.POST})
    public String editCourse(CourseCustom courseCustom) throws Exception {

        courseService.upadteById(courseCustom.getCourseid(), courseCustom);

        //重定向
        return "redirect:showCourse";
    }

    // 删除课程信息
    @RequestMapping("deleteCourse")
    public String deleteCourse(Integer id) throws Exception {
        if (id == null) {
            //加入没有带教师id就进来的话就返回教师显示页面
            return "admin/showCourse";
        }
        courseService.removeById(id);

        return "redirect:showCourse";
    }
    
 // 教师页面显示
    @RequestMapping("/showTeacher")
    public String showTeacher(Model model, PageBean pb) throws Exception {

    	List<TeacherCustom> l = teacherService.findAll();
    	pb.setTotalRecord(l.size());
    	pb.caculateSomePer();
    	
    	List<TeacherCustom> list = teacherService.findByPage(pb);
    	boolean flag = true;
    	
System.out.println(list);

    	model.addAttribute("teacherList", list);
    	model.addAttribute("page", pb);
    	model.addAttribute("flag", flag);

        return "admin/showTeacher";

    }

    // 添加教师信息
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
    public String addTeacherUI(Model model) throws Exception {

        List<College> list = collegeService.findAll();

        model.addAttribute("collegeList", list);

        return "admin/addTeacher";
    }

    // 添加教师信息处理
    @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
    public String addTeacher(TeacherCustom teacherCustom, Model model) throws Exception {

        Boolean result = teacherService.save(teacherCustom);

        if (!result) {
            model.addAttribute("message", "工号重复");
            return "error";
        }
        //添加成功后，也添加到登录表
        Userlogin userlogin = new Userlogin();
        userlogin.setUsername(teacherCustom.getUserid().toString());
        userlogin.setPassword("123");
        userlogin.setRole(1);
        userloginService.save(userlogin);;

        //重定向
        return "redirect:/admin/showTeacher";
    }

    // 修改教师信息页面显示
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
    public String editTeacherUI(Integer id, Model model) throws Exception {
        if (id == null) {
            return "redirect:/admin/showTeacher";
        }
        TeacherCustom teacherCustom = teacherService.findById(id);
//        if (teacherCustom == null) {
//            throw new CustomException("未找到该名学生");
//        }
        List<College> list = collegeService.findAll();

        model.addAttribute("collegeList", list);
        model.addAttribute("teacher", teacherCustom);


        return "admin/editTeacher";
    }

    // 修改教师信息页面处理
    @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
    public String editTeacher(TeacherCustom teacherCustom) throws Exception {

        teacherService.updateById(teacherCustom.getUserid(), teacherCustom);

        //重定向
        return "redirect:showTeacher";
    }

    //删除教师
    @RequestMapping("/deleteTeacher")
    public String removeTeacher(Integer id) throws Exception {
        if (id == null) {
            //加入没有带教师id就进来的话就返回教师显示页面
            return "admin/showTeacher";
        }
        teacherService.removeById(id);
        userloginService.removeByName(id.toString());

        return "redirect:showTeacher";
    }

    //搜索教师
    @RequestMapping(value = "selectTeacher", method = {RequestMethod.POST})
    private String selectTeacher(String findByName, Model model) throws Exception {

        List<TeacherCustom> list = teacherService.findByName(findByName);

        model.addAttribute("teacherList", list);
        return "admin/showTeacher";
    }
    
    // 普通用户账号密码重置
    @RequestMapping("/userPasswordRest")
    public String userPasswordRestUI() throws Exception {
        return "admin/userPasswordRest";
    }

    // 普通用户账号密码重置处理
    @RequestMapping(value = "/userPasswordRest", method = {RequestMethod.POST})
    public String userPasswordRest(Userlogin userlogin) throws Exception {

        Userlogin u = userloginService.findByName(userlogin.getUsername());

        if (u != null) {
//            if (u.getRole() == 0) {
//                throw new CustomException("该账户为管理员账户，没法修改");
//            }
            u.setPassword(userlogin.getPassword());
            userloginService.updateByName(userlogin.getUsername(), u);
        } else {
//            throw new CustomException("没找到该用户");
        }

        return "admin/userPasswordRest";
    }

    // 本账户密码重置
    @RequestMapping("/passwordRest")
    public String passwordRestUI() throws Exception {
        return "admin/passwordRest";
    }
    
    public String passwordRest(String oldPassword, String password1, String password2) throws Exception{
    	
    	return null;
    }
}
