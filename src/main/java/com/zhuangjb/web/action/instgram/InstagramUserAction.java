package com.zhuangjb.web.action.instgram;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.system.Constants;
import com.zhuangjb.util.MD5;
import com.zhuangjb.web.action.AbstractAction;
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


	/**
	 * 登录校验(ajax)
	 */
	@At("/postDetail")
	public View postDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登陆校验
		String id = request.getParameter(DBC.id);
		BasicDBObject qryfilter2 = new BasicDBObject();
        qryfilter2.put(DBC.id, new ObjectId(id));

		List<Document> docs = MongoDAO.getInstance().find("posts_info",
				qryfilter2);
		if(docs ==null){
			throw new Exception("Data is Null");
		}
		request.setAttribute("postdoc", docs.get(0));
		return new JspView("/WEB-INF/jsp/postDetail.jsp");
	}


}
