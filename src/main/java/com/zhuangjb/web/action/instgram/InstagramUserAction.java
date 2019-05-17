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
import java.util.Map;

public class InstagramUserAction extends AbstractAction {


	@At("/searchIndex")
	public View searchIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登陆校验
		BasicDBObject qryfilter2 = new BasicDBObject();
		String content = request.getParameter("content");
		if ("get".equalsIgnoreCase(request.getMethod())||"".equals(content)||content==null) {
			List<Document> list = MongoDAO.getInstance().find("user_popular",
					qryfilter2);
			request.setAttribute("list", list);

			return new JspView("/WEB-INF/jsp/searchIndex.jsp");

		}else{

			Object[] list=new InstagramUtils().search(content);

			ArrayList result=new ArrayList();
			if(list!=null){
			for(Object obj:list){
				if (obj==null){
					continue;
				}
				result.add(obj);
			}}

			request.setAttribute("list", result);

			return new JspView("/WEB-INF/jsp/searchList.jsp");

		}
	}

	@At("/download")
	public View download(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if ("get".equalsIgnoreCase(request.getMethod())){
			return new JspView("/WEB-INF/jsp/download.jsp");
		}

		String href = request.getParameter("href");
		if(!href.contains("instagram.com/p/")){
//			throw new Exception("Input Url invaild");
			return new JspView("/WEB-INF/jsp/download.jsp");
		}

		Document doc= new InstagramUtils().getPostByUrl(href);

		if (doc == null) {
			throw new Exception("Data is Null");
		}
		request.setAttribute("postdoc", doc );
		if (doc.get("imgs") != null) {
			ArrayList xx = (ArrayList) doc.get("imgs");
			if (xx != null && xx.size() > 0) {
				return new JspView("/WEB-INF/jsp/imageDetail.jsp");

			}
		}

		if (doc.get("video_url") != null) {
			return new JspView("/WEB-INF/jsp/postDetail.jsp");
		}else{
			throw new Exception("Server Error");
		}
	}


	@At("/search")
	public View search(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登陆校验

		String content = request.getParameter("content");
		if (!"".equals(content)&&content!=null) {
			Object[] list=new InstagramUtils().search(content);
			ArrayList result=new ArrayList();
			if(list!=null){
				for(Object obj:list){
					if (obj==null){
						continue;
					}
					result.add(obj);
				}}

			request.setAttribute("list", result);

		}
		return new JspView("/WEB-INF/jsp/searchList.jsp");
	}
	@At("/tagIndex")
	public View tagIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String tag = request.getParameter("tag");

		List<Document> list= new InstagramUtils().getPostPreList(tag,12);
		ContextHolder.getRequest().setAttribute("list", list);
		return new JspView("/WEB-INF/jsp/tagIndex.jsp");
	}

	@At("/userPostIndex")
	public View userPostIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String username = request.getParameter("username");

		List<Document> list= new InstagramUtils().getUserPostIndex(username,12);
		ContextHolder.getRequest().setAttribute("list", list);
		return new JspView("/WEB-INF/jsp/tagIndex.jsp");
	}



	@At("/userIndex")
	public View userIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 登陆校验
		BasicDBObject qryfilter2 = new BasicDBObject();
		String content = request.getParameter("content");

			List<Document> list = MongoDAO.getInstance().find("user_popular",
					qryfilter2);
			request.setAttribute("list", list);

			return new JspView("/WEB-INF/jsp/userIndex.jsp");


	}
	@At("/getPostByUrl")
	public View getPostByUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String href = request.getParameter("href");
		 Document doc= new InstagramUtils().getPostByUrl(href);

		if (doc == null) {
			throw new Exception("Data is Null");
		}
		request.setAttribute("postdoc", doc );
		if (doc.get("imgs") != null) {
			ArrayList xx = (ArrayList) doc.get("imgs");
			if (xx != null && xx.size() > 0) {
				return new JspView("/WEB-INF/jsp/imageDetail.jsp");

			}
		}

		if (doc.get("video_url") != null) {
			return new JspView("/WEB-INF/jsp/postDetail.jsp");
		}else{
			throw new Exception("Server Error");
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