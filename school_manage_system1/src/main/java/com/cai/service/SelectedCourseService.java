package com.cai.service;

import java.util.List;

import com.cai.po.SelectedcourseCustom;


public interface SelectedCourseService {
	//根据课程ID查询课程
    List<SelectedcourseCustom> findByCourseID(Integer id) throws Exception;

    //根据课程id分页查询课程
    List<SelectedcourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception;

    //获取该课程学生数
    Integer countByCourseID(Integer id) throws Exception;

    //查询指定学生成绩
    SelectedcourseCustom findOne(SelectedcourseCustom selectedCourseCustom) throws Exception;

    //打分
    void updataOne(SelectedcourseCustom selectedCourseCustom) throws Exception;

    //选课
    void save(SelectedcourseCustom selectedCourseCustom) throws Exception;

    //根据学生id查找课程
    List<SelectedcourseCustom> findByStudentID(Integer id) throws Exception;

    //退课
    void remove(SelectedcourseCustom selectedCourseCustom) throws Exception;
}
