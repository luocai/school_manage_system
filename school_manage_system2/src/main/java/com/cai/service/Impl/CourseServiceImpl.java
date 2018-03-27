package com.cai.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.mapper.CollegeMapper;
import com.cai.mapper.CourseMapper;
import com.cai.mapper.CourseMapperCustom;
import com.cai.mapper.SelectedcourseMapper;
import com.cai.po.College;
import com.cai.po.Course;
import com.cai.po.CourseCustom;
import com.cai.po.CourseExample;
import com.cai.po.PageBean;
import com.cai.po.Selectedcourse;
import com.cai.po.SelectedcourseExample;
import com.cai.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private CourseMapperCustom courseMapperCustom;
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private SelectedcourseMapper selectedcourseMapper;
	@Override
	public void upadteById(Integer id, CourseCustom courseCustom) throws Exception {
		
		 courseMapper.updateByPrimaryKey(courseCustom);
	}

	@Override
	public Boolean removeById(Integer id) throws Exception {
		
		 //自定义查询条件，查询这门课是否有人选
        SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidEqualTo(id);
        List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
        //如果选这门课的人为0,就可以删除
        if (list.size() == 0) {
            courseMapper.deleteByPrimaryKey(id);
            return true;
        }
        //如果有人选这门课，就无法删除
        return false;
	}

	@Override
	public List<CourseCustom> findByPaging(PageBean pageBean) throws Exception {
		
		return courseMapperCustom.findByPage(pageBean);
	}
	
	@Override
	public List<CourseCustom> findAll() throws Exception {
		
		return courseMapperCustom.findAll();
	}

	@Override
	public Boolean save(CourseCustom courseCustom) throws Exception {
		Course course = courseMapper.selectByPrimaryKey(courseCustom.getCourseid());
		
		//如果不存在，则插入
		if (course == null){
			courseMapper.insertSelective(course);
			return true;
		}
		return false;
	}

	@Override
	public int getCountCourse() throws Exception {
		
		CourseExample courseExample = new CourseExample();
		CourseExample.Criteria criteria = courseExample.createCriteria();
		
		criteria.andCollegeidIsNotNull();
		
		return  courseMapper.countByExample(courseExample);
	}

	@Override
	public CourseCustom findById(Integer id) throws Exception {
		
		Course course = courseMapper.selectByPrimaryKey(id);
		CourseCustom courseCustom = null;
		if(course != null){
			courseCustom = new CourseCustom();
			BeanUtils.copyProperties(courseCustom, course);
		}
		return courseCustom;
	}

	@Override
	public List<CourseCustom> findByName(String name) throws Exception {
		
		CourseExample courseExample = new CourseExample();
		CourseExample.Criteria criteria = courseExample.createCriteria();
		
		criteria.andCoursenameLike("%" + name + "%");
		
		List<Course> list = courseMapper.selectByExample(courseExample);
		List<CourseCustom> courseCustomList = null;
		if (list != null){
			courseCustomList = new ArrayList();
			for (Course course : list){
				CourseCustom courseCustom = new CourseCustom();
				BeanUtils.copyProperties(courseCustom, course);
				
				College college = collegeMapper.selectByPrimaryKey(course.getCollegeid());
				
				courseCustom.setCollegeName(college.getCollegename());
				courseCustomList.add(courseCustom);
			}
			
		}
		
		return courseCustomList;
	}

	@Override
	public List<CourseCustom> findByTeacherID(Integer id) throws Exception {
		
		CourseExample courseExample = new CourseExample();
		CourseExample.Criteria criteria = courseExample.createCriteria();
		criteria.andTeacheridEqualTo(id);
		

        List<Course> list = courseMapper.selectByExample(courseExample);
        List<CourseCustom> courseCustomList = null;

        if (list.size() > 0) {
            courseCustomList = new ArrayList<CourseCustom>();
            for (Course c : list) {
                CourseCustom courseCustom = new CourseCustom();
                //类拷贝
                BeanUtils.copyProperties(courseCustom, c);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(c.getCollegeid());
                courseCustom.setCollegeName(college.getCollegename());

                courseCustomList.add(courseCustom);
            }
        }

        return courseCustomList;
		
	}

}
