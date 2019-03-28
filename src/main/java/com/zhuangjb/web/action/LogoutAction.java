package com.zhuangjb.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

import com.zhuangjb.system.Constants;

public class LogoutAction extends AbstractAction {

	@At("/logout")
	@Ok("redirect:login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession(false) != null && request.getSession().getAttribute(Constants.USERNAME) != null) {
			request.getSession().removeAttribute(Constants.USERNAME);
		}
	}

}
