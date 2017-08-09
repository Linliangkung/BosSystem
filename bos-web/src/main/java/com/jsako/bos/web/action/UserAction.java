package com.jsako.bos.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.User;
import com.jsako.bos.service.IUserService;
import com.jsako.bos.utils.BOSUtils;
import com.jsako.bos.utils.MD5Utils;
import com.jsako.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	// 属性驱动,接收页面输入的验证码
	private String checkcode;
	// 接收密码修改页面窗来的参数
	private String newPassword;
	private String rePassword;
	private String oldPassword;

	@Autowired
	private IUserService userService;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String login() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 校验验证码是否输入正确
		String validatecode = (String) session.get("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)) {
			// 输入的验证码正确
			User user = userService.login(getModel());
			if (user != null) {
				// 登录成功
				session.put("existUser", user);
				return HOME;
			} else {
				this.addActionError("用户名或密码错误");
				return LOGIN;
			}
		} else {
			// 输入的验证码错误,设置提示信息,跳转到登录页面
			this.addActionError("输入的验证码错误");
			return LOGIN;
		}
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if (session != null && session.getAttribute("existUser") != null) {
			// 说明用户已经登录,注销session
			session.invalidate();
		}
		return "toLogin";
	}

	public String editPassword() throws IOException {
		// 400代表失败，200代表成功
		int code = 0;
		String msg = null;
		// 校验三个参数是否为空
		if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(rePassword) || StringUtils.isBlank(oldPassword)) {
			code = 400;
			msg = "输入框不能为空";
		} else {
			// 三个参数都不为空
			// 校验新密码是否一致
			if (!newPassword.equals(rePassword)) {
				// 不一致
				code = 400;
				msg = "两次密码输入不一致";
			} else {
				// 两次新密码输入一致
				if(oldPassword.equals(newPassword)){
					//新旧密码一致
					code = 400;
					msg = "新旧密码不能相同";
				}else{
					//新旧密码不一致
					//校验旧密码是否正确
					User loginUser = BOSUtils.getLoginUser();
					User user=userService.getUserById(loginUser.getId());
					if(!MD5Utils.md5(oldPassword).equals(user.getPassword())){
						//旧密码不正确
						code = 400;
						msg = "旧密码不正确";
					}else{
						//旧密码正确
						//修改密码
						userService.editPassword(loginUser.getId(),newPassword);
						BOSUtils.getSession().invalidate();
						code=200;
						msg="密码修改成功";
					}
				}
			}
		}
		String data="{\"code\":"+code+",\"msg\":\""+msg+"\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		return NONE;
	}
}
