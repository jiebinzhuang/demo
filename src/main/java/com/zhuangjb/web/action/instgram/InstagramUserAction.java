package com.zhuangjb.web.action.instgram;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.client.InstagramUtils;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.system.Constants;
import com.zhuangjb.util.MD5;
import com.zhuangjb.web.action.AbstractAction;
import com.zhuangjb.web.filter.ContextHolder;
import com.zhuangjb.web.server.WebServerConfigUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstagramUserAction extends AbstractAction {


	@At("/userIndex")
	public View userIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登陆校验
		BasicDBObject qryfilter2 = new BasicDBObject();
		String content = request.getParameter("content");
		if ("get".equalsIgnoreCase(request.getMethod())||"".equals(content)||content==null) {
			List<Document> list = MongoDAO.getInstance().find("user_popular",
					qryfilter2);
			request.setAttribute("userList", list);

			return new JspView("/WEB-INF/jsp/userIndex.jsp");

		}else{

			  Object[] list=new InstagramUtils().search(content);
			 request.setAttribute("list", list);

			return new JspView("/WEB-INF/jsp/searchList.jsp");

		}
	}

	@At("/postDetail")
	public View postDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登陆校验
		String id = request.getParameter(DBC.id);
		BasicDBObject qryfilter2 = new BasicDBObject();
		qryfilter2.put(DBC.id, new ObjectId(id));

		String type = request.getParameter("type");

		String tableName;
		if(type==null||"".equals(type)||"hot".equals(type)){
			 tableName="posts_info";
		}else {
			tableName="posts_"+type;
		}

		List<Document> docs = MongoDAO.getInstance().find(tableName,
				qryfilter2);
		if (docs == null) {
			throw new Exception("Data is Null");
		}
		request.setAttribute("postdoc", docs.get(0));
		if (docs.get(0).get("imgs") != null) {
			ArrayList xx = (ArrayList) docs.get(0).get("imgs");
			if (xx != null && xx.size() > 0) {
				return new JspView("/WEB-INF/jsp/imageDetail.jsp");

			}
		}

		if (docs.get(0).get("video_url") != null) {
			return new JspView("/WEB-INF/jsp/postDetail.jsp");
		}else{
			throw new Exception("Server Error");
		}

	}
}