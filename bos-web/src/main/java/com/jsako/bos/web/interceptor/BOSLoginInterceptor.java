package com.jsako.bos.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.jsako.bos.domain.User;
import com.jsako.bos.utils.BOSUtils;
import com.jsako.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 自定义拦截器，实现用户未登录自动跳转到登录界面
 * @author linliangkung
 *
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user=BOSUtils.getLoginUser();
		if(user!=null){
			//说明用户已经登录
			return invocation.invoke();
		}
		//没有登录,重定向到登录界面
		return "toLogin";
	}
}
