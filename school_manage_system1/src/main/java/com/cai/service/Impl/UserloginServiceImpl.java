package com.cai.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.mapper.UserloginMapper;
import com.cai.po.Userlogin;
import com.cai.po.UserloginExample;
import com.cai.service.UserloginService;

@Service
public class UserloginServiceImpl implements UserloginService{
	
	@Autowired
	private UserloginMapper userloginMapper;
	
	@Override
	public Userlogin findByName(String name) throws Exception {
		
		UserloginExample userloginExample = new UserloginExample();
		UserloginExample.Criteria criteria= userloginExample.createCriteria();
		
		criteria.andUsernameEqualTo(name);
		
		List<Userlogin> list = userloginMapper.selectByExample(userloginExample);
		
		return list.get(0);
	}

	@Override
	public void save(Userlogin userlogin) throws Exception {
		userloginMapper.insert(userlogin);
		
	}

	@Override
	public void removeByName(String name) throws Exception {
		UserloginExample userloginExample = new UserloginExample();
		UserloginExample.Criteria criteria = userloginExample.createCriteria();
		
		criteria.andUsernameEqualTo(name);
		
		Userlogin userlogin = userloginMapper.selectByExample(userloginExample).get(0);
		userloginMapper.deleteByPrimaryKey(userlogin.getUserid());
		
	}

	@Override
	public void updateByName(String name, Userlogin userlogin) {
		 UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.updateByExample(userlogin, userloginExample);
		
	}

}
