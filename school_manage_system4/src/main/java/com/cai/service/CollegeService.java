package com.cai.service;

import java.util.List;

import com.cai.po.College;

public interface CollegeService {
	
	public List<College> findAll() throws Exception; 
}
