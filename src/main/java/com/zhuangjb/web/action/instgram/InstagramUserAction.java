package com.zhuangjb.web.action.instgram;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.system.Constants;
import com.zhuangjb.util.MD5;
import com.zhuangjb.web.action.AbstractAction;
import com.zhuangjb.web.server.WebServerConfigUtils;
import org.bson.Document;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InstagramUserAction extends AbstractAction {


	/**
	 * 登录校验(ajax)
	 */
	@At("/userDetail")
	public View userDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 登陆校验
		String username = request.getParameter(DBC.username);

		request.setAttribute("msg", "用户名或密码错误!");
		return new JspView("/WEB-INF/jsp/userDetail.jsp");
	}


}
