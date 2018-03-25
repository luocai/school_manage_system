package com.cai.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.mapper.RoleMapper;
import com.cai.po.Role;
import com.cai.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Override
	public Role findById(Integer id) throws Exception {
		
		
		return roleMapper.selectByPrimaryKey(id);
	}

}
