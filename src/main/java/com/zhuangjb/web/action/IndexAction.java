package com.zhuangjb.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import org.bson.Document;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;

import com.zhuangjb.system.Constants;

import java.util.List;

public class IndexAction extends AbstractAction {

	@At("/index")
	public View index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BasicDBObject qryfilter2 = new BasicDBObject();
		qryfilter2.put(DBC.dr, 0);

		List<Document> userdocs = MongoDAO.getInstance().find("user_popular",
				qryfilter2);
		request.setAttribute("userdocs", userdocs);

		return new JspView("/WEB-INF/jsp/index.jsp");
//		String role = (String) request.getSession().getAttribute(Constants.ROLECODE);
//
//		if (Constants.ROLECODE_ADMIN.equals(role) || Constants.ROLECODE_SUPER_ADMIN.equals(role)) {
//			// 管理员角色
//			// response.sendRedirect("/admin_index.action");
//			return new JspView("/WEB-INF/jsp/admin_index.jsp");
//		} else if (Constants.ROLECODE_USER.equals(role)) {
//			// 用户角色
//			return new JspView("/WEB-INF/jsp/user_index.jsp");
//			// } else if (Constants.ROLECODE_AGENT.equals(role)) {
//			// return new JspView("/WEB-INF/jsp/agent_index.jsp");
//		} else {
//			throw new Exception("该用户所分配的角色有错误");
//		}
	}
}
