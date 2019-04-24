package com.zhuangjb.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import com.zhuangjb.busy.DBC;
import com.zhuangjb.system.Constants;
import com.zhuangjb.util.MD5;
import com.zhuangjb.web.server.WebServerConfigUtils;
import com.mongodb.BasicDBObject;

public class LoginAction extends AbstractAction {

	@At("/login")
	public View login(HttpServletRequest request, HttpServletResponse response) {
		// 进入登陆页面
		return new JspView("/WEB-INF/jsp/login.jsp");
	}

	/**
	 * 登录校验(ajax)
	 */
	@At("/loginCheck")
	public View loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 登陆校验
		String username = request.getParameter(DBC.username);
		String password = request.getParameter(DBC.password);

		if ("POST".equalsIgnoreCase(request.getMethod()) && this.checkUser(username, password, request)) {
			request.getSession().setAttribute(Constants.USERNAME, username);
			return new ServerRedirectView("index");
		} else {
			request.setAttribute("msg", "用户名或密码错误!");
			return new JspView("/WEB-INF/jsp/login.jsp");
		}
	}

	private boolean checkUser(String username, String password, HttpServletRequest request) throws IOException {
		String role = "";

		List<String[]> realms = WebServerConfigUtils.getRealms();

		// 从数据库获取
		BasicDBObject filter = new BasicDBObject();
		filter.put(DBC.dr, 0);
		filter.put(DBC.username, username);
		List<Document> list = dao.find(DBC.USER_用户, filter);
		if (list.size() > 0) {
			Document doc = list.get(0);
			String pass = doc.getString(DBC.password);
			if (password != null && password.equals(pass)) {
				for (String[] arr : realms) {
					if (arr != null && arr.length > 0 && arr[0] != null && arr[0].trim().startsWith(username + ":")) {
						request.getSession().setAttribute(Constants.ROLECODE, arr[1]);
						request.getSession().setAttribute(Constants.USERID, doc.get(DBC.id).toString());
						return true;
					}
				}

				request.getSession().setAttribute(Constants.ROLECODE, Constants.ROLECODE_USER);
				request.getSession().setAttribute(Constants.USERID, doc.get(DBC.id).toString());
				return true;
			} else {
				return false;
			}
		}

		String user = null;
		for (String[] arr : realms) {
			if (arr != null && arr.length > 0 && arr[0] != null && arr[0].trim().startsWith(username + ":")) {
				user = arr[0];
				role = arr[1];
				request.getSession().setAttribute(Constants.ROLECODE, role);
				break;
			}
		}

		if (user != null) {
			// fangw:MD5:c4ca4238a0b923820dcc509a6f75849b,admins
			// username:password,group1,group2,group3
			// admin:admin,admins
			String[] arr = user.split(":");
			if (arr.length == 2) {
				// 明文用户名密码
				if (arr[1].equals(password)) {
					return true;
				}
			} else {
				// 加密
				if ("MD5".equalsIgnoreCase(arr[1])) {
					MD5 md5 = new MD5();
					if (md5.encrypt(password).equalsIgnoreCase(arr[2])) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
