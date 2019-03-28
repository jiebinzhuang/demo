package com.zhuangjb.web.action.api;

import com.mongodb.BasicDBObject;
import com.zhuangjb.busy.DBC;
import com.zhuangjb.util.DateUtils;
import org.bson.Document;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@At("/api/shishi")
public class ApiShiShicaiAction extends AbstractApiAction {

	@At("/checkdevice.do")
	@Ok("raw")
	public String checkdevice(HttpServletRequest request,
			HttpServletResponse response) {

		String deviceid = getParameter("deviceid");
		
		deviceid=deviceid.trim();
		// 从数据库获取
		BasicDBObject filter = new BasicDBObject();
		filter.put(DBC.dr, 0);
		filter.put("deviceid",deviceid);
  

		List<Document> list = dao.find("device", filter);
		if (list.size() > 0) {

			String effectdate = list.get(0).getString("effectdate");
			if (effectdate.compareTo(DateUtils.getCurrentDate()) < 0) {
				return "您的账号已经过期，请联系管理员重新开";
			}
			 return "success";
		}else{
			Document newdoc = new Document();
			newdoc.put(DBC.dr, 0);
			newdoc.put("deviceid",deviceid);
			newdoc.put("effectdate", "1999");
			newdoc.put("ts", DateUtils.getTS());
			dao.insert("device", newdoc);
		}
		return "设备授权失败";
		
	}

}
