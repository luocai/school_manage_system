package com.cai.mapper;

import java.util.List;

import com.cai.po.PageBean;
import com.cai.po.StudentCustom;


public interface StudentMapperCustom {
	//分页查询学生信息
    List<StudentCustom> findByPage(PageBean pageBean) throws Exception;

    //查询学生信息，和其选课信息
   public StudentCustom findStudentAndSelectCourseListById(Integer id) throws Exception;
   
   public List<StudentCustom> findAll() throws Exception;

}
