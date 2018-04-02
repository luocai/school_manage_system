package com.cai.mapper;

import java.util.List;

import com.cai.po.PageBean;
import com.cai.po.TeacherCustom;

public interface TeacherMapperCustom {
	
	public List<TeacherCustom> findByPage(PageBean pb);
	
}
