package com.zhuangjb.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.util.HttpUtils;
import com.zhuangjb.util.JsonUtil;
import com.zhuangjb.web.filter.ContextHolder;
import org.bson.Document;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;

import com.zhuangjb.system.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexAction extends AbstractAction {

	@At("/index")
	public View index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BasicDBObject qryfilter2 = new BasicDBObject();

		List<Document> list= new ArrayList();
		String type = request.getParameter("type");
        if(type==null||"".equals(type)||"hot".equals(type)){
			  list = MongoDAO.getInstance().find("posts_info",
					qryfilter2);
			ContextHolder.getRequest().setAttribute("list", list);
			return new JspView("/WEB-INF/jsp/index.jsp");
		}else {

			list = MongoDAO.getInstance().find("posts_"+type,
					qryfilter2);
			ContextHolder.getRequest().setAttribute("list", list);
			return new JspView("/WEB-INF/jsp/"+type+".jsp");
		}

//
//		List<Document> fashionlist = MongoDAO.getInstance().find("posts_fashion",
//				qryfilter2);
//
//		List<Document> beautylist = MongoDAO.getInstance().find("posts_beauty",
//				qryfilter2);
//		List<Document> foodslist = MongoDAO.getInstance().find("posts_foods",
//				qryfilter2);
//		List<Document> sportslist = MongoDAO.getInstance().find("posts_sports",
//				qryfilter2);
//		List<Document> movielist = MongoDAO.getInstance().find("posts_movie",
//				qryfilter2);
//		List<Document> travellist = MongoDAO.getInstance().find("posts_travel",
//				qryfilter2);
//		List<Document> digitallist = MongoDAO.getInstance().find("posts_digital",
//				qryfilter2);
//		List<Document> booklist = MongoDAO.getInstance().find("posts_book",
//				qryfilter2);
//		List<Document> jokelist = MongoDAO.getInstance().find("posts_joke",
//				qryfilter2);




//		ContextHolder.getRequest().setAttribute("fashionlist", fashionlist);
//
//		ContextHolder.getRequest().setAttribute("beautylist", beautylist);
//		ContextHolder.getRequest().setAttribute("foodslist", foodslist);
//		ContextHolder.getRequest().setAttribute("sportslist", sportslist);
//
//		ContextHolder.getRequest().setAttribute("movielist", movielist);
//		ContextHolder.getRequest().setAttribute("travellist", travellist);
//
//		ContextHolder.getRequest().setAttribute("digitallist", digitallist);
//
//		ContextHolder.getRequest().setAttribute("booklist", booklist);
//		ContextHolder.getRequest().setAttribute("jokelist", jokelist);



	}
}
