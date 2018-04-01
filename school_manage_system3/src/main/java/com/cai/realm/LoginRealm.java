package com.cai.realm;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cai.po.Role;
import com.cai.po.Userlogin;
import com.cai.service.RoleService;
import com.cai.service.UserloginService;

@Component("loginRealm")
public class LoginRealm extends AuthorizingRealm{

	@Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;
	
	//权限认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		String username = (String) getAvailablePrincipal(principalCollection);
		Role role = null;
		
System.out.println("权限认证");
		try{
			 Userlogin userlogin = userloginService.findByName(username);
	            //获取角色对象
	           role = roleService.findById(userlogin.getRole());
System.out.println(role);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> r = new HashSet<String>();
		if (role != null){
			r.add(role.getRolename());
			info.setRoles(r);
		}
		
		return info;
	}

	//登录验证（身份验证）
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		Userlogin userlogin = null;
//System.out.println(username);
//System.out.println(password);
		try{
			userlogin = userloginService.findByName(username);
//System.out.println(userlogin);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (userlogin == null){
			System.out.println("没有该用户名");
		}else if (!password.equals(userlogin.getPassword())){
			System.out.println("密码错误");
		}
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
		
		return info;
	}

}
