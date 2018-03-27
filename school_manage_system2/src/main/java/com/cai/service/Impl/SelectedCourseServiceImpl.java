package com.cai.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.mapper.SelectedcourseMapper;
import com.cai.mapper.StudentMapper;
import com.cai.po.Selectedcourse;
import com.cai.po.SelectedcourseCustom;
import com.cai.po.SelectedcourseExample;
import com.cai.po.Student;
import com.cai.po.StudentCustom;
import com.cai.service.SelectedCourseService;

@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {
	
	@Autowired
	private SelectedcourseMapper selectedCourseMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Override
	public List<SelectedcourseCustom> findByCourseID(Integer id) throws Exception {
		
		SelectedcourseExample selectedCourseExample = new SelectedcourseExample();
		SelectedcourseExample.Criteria criteria = selectedCourseExample.createCriteria();
		criteria.andCourseidEqualTo(id);
		
		List<Selectedcourse> list = selectedCourseMapper.selectByExample(selectedCourseExample);
		List<SelectedcourseCustom> seclist = null;
		
		if (list != null){
			seclist = new ArrayList<SelectedcourseCustom>();
			for (Selectedcourse s : list){
				SelectedcourseCustom sec=  new SelectedcourseCustom();
				BeanUtils.copyProperties(sec, s);
				
				//判断是否完成类该课程
	            if (sec.getMark() != null) {
	                sec.setOver(true);
	            }
	            Student student = studentMapper.selectByPrimaryKey(sec.getStudentid());
	            StudentCustom studentCustom = new StudentCustom();
	            BeanUtils.copyProperties(student, studentCustom);

	            sec.setStudentCustom(studentCustom);

	            seclist.add(sec);
			}
		}
		
		return seclist;
	}

	@Override
	public List<SelectedcourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countByCourseID(Integer id) throws Exception {
		
		SelectedcourseExample selectedCourseExample = new SelectedcourseExample();
		SelectedcourseExample.Criteria criteria = selectedCourseExample.createCriteria();
		criteria.andCourseidIsNotNull();
		
		
		return selectedCourseMapper.countByExample(selectedCourseExample);
	}

	@Override
	public SelectedcourseCustom findOne(SelectedcourseCustom selectedCourseCustom) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());

        List<Selectedcourse> list = selectedCourseMapper.selectByExample(example);


        if (list.size() > 0) {
            SelectedcourseCustom sc = new SelectedcourseCustom();
            BeanUtils.copyProperties(list.get(0), sc);

            Student student = studentMapper.selectByPrimaryKey(selectedCourseCustom.getStudentid());
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);

            sc.setStudentCustom(studentCustom);

            return sc;
        }

        return null;
	}

	@Override
	public void updataOne(SelectedcourseCustom selectedCourseCustom) throws Exception {
		
		SelectedcourseExample selectedCourseExample = new SelectedcourseExample();
		SelectedcourseExample.Criteria criteria = selectedCourseExample.createCriteria();
		
		criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
		criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());
		
		selectedCourseMapper.updateByExampleSelective(selectedCourseCustom, selectedCourseExample);
	}

	@Override
	public void save(SelectedcourseCustom selectedCourseCustom) throws Exception {
		selectedCourseMapper.insert(selectedCourseCustom);

	}

	@Override
	public List<SelectedcourseCustom> findByStudentID(Integer id) throws Exception {
		
		return null;
	}

	@Override
	public void remove(SelectedcourseCustom selectedCourseCustom) throws Exception {
		SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());

        selectedCourseMapper.deleteByExample(example);

	}

}
