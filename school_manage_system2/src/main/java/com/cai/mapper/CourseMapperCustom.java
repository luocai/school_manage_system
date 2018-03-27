package com.cai.mapper;

import java.util.List;

import com.cai.po.CourseCustom;
import com.cai.po.PageBean;

public interface CourseMapperCustom {
	
	public List<CourseCustom> findByPage(PageBean pageBean) throws Exception;
	
	public List<CourseCustom> findAll() throws Exception;
}
