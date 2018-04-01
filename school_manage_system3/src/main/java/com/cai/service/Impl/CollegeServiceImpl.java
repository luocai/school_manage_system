package com.cai.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.mapper.CollegeMapper;
import com.cai.po.College;
import com.cai.po.CollegeExample;
import com.cai.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private CollegeMapper collegeMapper;
	@Override
	public List<College> findAll() throws Exception {
		
		CollegeExample collegeExample = new CollegeExample();
		CollegeExample.Criteria criteria = collegeExample.createCriteria();
		
		criteria.andCollegeidIsNotNull();
		
		return collegeMapper.selectByExample(collegeExample);
	}

}
