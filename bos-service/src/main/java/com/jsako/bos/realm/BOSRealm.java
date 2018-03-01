package com.jsako.bos.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jsako.bos.dao.IFunctionDao;
import com.jsako.bos.dao.IUserDao;
import com.jsako.bos.domain.Function;
import com.jsako.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IFunctionDao functionDao;
	
	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user=(User) principals.getPrimaryPrincipal();
		List<Function> functions=functionDao.getFunctionsByUserId(user.getId());
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		for (Function function : functions) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}
	
	//认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
		UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		//从数据库中根据用户名查询用户对象
		User user=userDao.findUserByUsername(username);
		if(user==null){
			//说明账号不存在，抛出账号不存在异常
			throw new UnknownAccountException();
		}
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

}
