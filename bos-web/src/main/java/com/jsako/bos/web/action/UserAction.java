package com.jsako.bos.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.User;
import com.jsako.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
}
