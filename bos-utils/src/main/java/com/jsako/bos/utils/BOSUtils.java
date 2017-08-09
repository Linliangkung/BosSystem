package com.jsako.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jsako.bos.domain.User;

/**
 * BOS项目的工具类
 * @author linliangkung
 *
 */
public class BOSUtils {
	/**
	 * 获得HttpSession对象
	 * @return
	 */
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public static User getLoginUser(){
		return (User) getSession().getAttribute("existUser");
	}
}
