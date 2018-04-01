package com.cai.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.mapper.CollegeMapper;
import com.cai.mapper.StudentMapper;
import com.cai.mapper.StudentMapperCustom;
import com.cai.po.College;
import com.cai.po.PageBean;
import com.cai.po.SelectedcourseCustom;
import com.cai.po.Student;
import com.cai.po.StudentCustom;
import com.cai.po.StudentExample;
import com.cai.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private StudentMapperCustom studentMapperCustom;
	
	@Override
	public void updataById(Integer id, StudentCustom studentCustom) throws Exception {
		
		studentMapper.updateByPrimaryKey(studentCustom);
	}

	@Override
	public void removeById(Integer id) throws Exception {
		studentMapper.deleteByPrimaryKey(id);

	}

	@Override
	public List<StudentCustom> findByPage(PageBean pageBean) throws Exception {
		
		return studentMapperCustom.findByPage(pageBean);
	}

	@Override
	public Boolean save(StudentCustom studentCustoms) throws Exception {
		Student stu = studentMapper.selectByPrimaryKey(studentCustoms.getUserid());
        if (stu == null) {
            studentMapper.insert(studentCustoms);
            return true;
        }

        return false;
	}

	@Override
	public int getCountStudent() throws Exception {
		 //鑷畾涔夋煡璇㈠璞�
        StudentExample studentExample = new StudentExample();
        //閫氳繃criteria鏋勯�犳煡璇㈡潯浠�
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUseridIsNotNull();

        return studentMapper.countByExample(studentExample);
	}

	@Override
	public StudentCustom findById(Integer id) throws Exception {
		Student student  = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            //绫绘嫹璐�
            BeanUtils.copyProperties(student, studentCustom);
        }

        return studentCustom;
	}

	@Override
	public List<StudentCustom> findByName(String name) throws Exception {
		 StudentExample studentExample = new StudentExample();
	        //鑷畾涔夋煡璇㈡潯浠�
	        StudentExample.Criteria criteria = studentExample.createCriteria();

	        criteria.andUsernameLike("%" + name + "%");

	        List<Student> list = studentMapper.selectByExample(studentExample);

	        List<StudentCustom> studentCustomList = null;

	        if (list != null) {
	            studentCustomList = new ArrayList<StudentCustom>();
	            for (Student s : list) {
	                StudentCustom studentCustom = new StudentCustom();
	                //绫绘嫹璐�
	                BeanUtils.copyProperties(s, studentCustom);
	                //鑾峰彇璇剧▼鍚�
	                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
	                studentCustom.setCollegeName(college.getCollegename());

	                studentCustomList.add(studentCustom);
	            }
	        }

	        return studentCustomList;
	}

	@Override
	public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {
		
		 StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));

	        List<SelectedcourseCustom> list = studentCustom.getSelectedCourseList();

	        // 鍒ゆ柇璇ヨ绋嬫槸鍚︿慨瀹�
	        for (SelectedcourseCustom s : list) {
	            if (s.getMark() != null) {
	                s.setOver(true);
	            }
	        }
	        return studentCustom;
	}

	@Override
	public List<StudentCustom> findAll() throws Exception {
		return studentMapperCustom.findAll();
	}

}
