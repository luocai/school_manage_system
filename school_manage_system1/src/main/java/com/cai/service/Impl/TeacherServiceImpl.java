package com.cai.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.mapper.CollegeMapper;
import com.cai.mapper.TeacherMapper;
import com.cai.po.College;
import com.cai.po.PageBean;
import com.cai.po.Teacher;
import com.cai.po.TeacherCustom;
import com.cai.po.TeacherExample;
import com.cai.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	@Override
	public void updateById(Integer id, TeacherCustom teacherCustom) throws Exception {
		teacherMapper.updateByPrimaryKey(teacherCustom);
		
	}

	@Override
	public void removeById(Integer id) throws Exception {
		teacherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TeacherCustom> findByPage(PageBean pageBean) throws Exception {
		
		return null;
	}

	@Override
	public Boolean save(TeacherCustom teacherCustom) throws Exception {
		
		Teacher teacher = teacherMapper.selectByPrimaryKey(teacherCustom.getUserid());
		
		if (teacher == null){
			teacherMapper.insert(teacherCustom);
			return true;
		}
		return false;
	}

	@Override
	public int getCountTeacher() throws Exception {
		TeacherExample teacherExample = new TeacherExample();
		TeacherExample.Criteria criteria = teacherExample.createCriteria();
		criteria.andUseridIsNotNull();
		
		return teacherMapper.countByExample(teacherExample);
	}

	@Override
	public TeacherCustom findById(Integer id) throws Exception {
		
		Teacher teacher = teacherMapper.selectByPrimaryKey(id);
		
		if (teacher != null){
			TeacherCustom teacherCustom = new TeacherCustom();
			BeanUtils.copyProperties(teacherCustom, teacher);
			
			return teacherCustom;
		}
		return null;
	}

	@Override
	public List<TeacherCustom> findByName(String name) throws Exception {
		
		TeacherExample teacherExample = new TeacherExample();
		TeacherExample.Criteria criteria = teacherExample.createCriteria();
		
		criteria.andUsernameLike("%" + name + "%");
		
		 List<Teacher> list = teacherMapper.selectByExample(teacherExample);

	        List<TeacherCustom> teacherCustomList = null;

	        if (list != null) {
	            teacherCustomList = new ArrayList<TeacherCustom>();
	            for (Teacher t : list) {
	                TeacherCustom teacherCustom = new TeacherCustom();
	                //类拷贝
	                BeanUtils.copyProperties(t, teacherCustom);
	                //获取课程名
	                College college = collegeMapper.selectByPrimaryKey(t.getCollegeid());
	                teacherCustom.setCollegeName(college.getCollegename());

	                teacherCustomList.add(teacherCustom);
	            }
	        }

	        return teacherCustomList;

	}

	@Override
	public List<TeacherCustom> findAll() throws Exception {
		
		TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andUsernameIsNotNull();

        List<Teacher> list = teacherMapper.selectByExample(teacherExample);
        List<TeacherCustom> teacherCustomsList = null;
        if (list != null) {
            teacherCustomsList = new ArrayList<TeacherCustom>();
            for (Teacher t: list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                BeanUtils.copyProperties(t, teacherCustom);
                teacherCustomsList.add(teacherCustom);
            }
        }
        return teacherCustomsList;
    }
	
	
}
