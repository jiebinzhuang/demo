package com.zhuangjb.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import com.zhuangjb.busy.DBC;
import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.system.Constants;
import com.zhuangjb.util.DateUtils;
import com.zhuangjb.web.filter.ContextHolder;
import com.mongodb.BasicDBObject;

abstract public class AbstractAction {
	protected Log log = LogFactory.getLog(this.getClass());
	protected MongoDAO dao = MongoDAO.getInstance();

	public Map<String, Object> genAjaxResponse(boolean result, Throwable e) {
		String msg = null;
		if (e.getCause() == null) {
			msg = e.getMessage();
		} else {
			msg = e.getCause().getMessage();
		}
		return genAjaxResponse(result, msg, null, null);
	}

	/**
	 * 页面操作后的返回信息，该信息会被页面捕获，用于操作提示
	 * 
	 * @param result
	 *            操作成功与否
	 * @param msg
	 *            返回操作后的信息，默认操作成功，一般操作失败会抛出异常信息
	 * @return
	 */
	public Map<String, Object> genAjaxResponse(boolean result, String msg) {
		return genAjaxResponse(result, msg, null, null);
	}

	/**
	 * 页面操作后的返回信息，该信息会被页面捕获，用于操作提示
	 * 
	 * @param result
	 *            操作成功与否
	 * @param msg
	 *            返回操作后的信息，默认操作成功，一般操作失败会抛出异常信息
	 * @param obj
	 *            操作返回的对象，一般是ajax操作后返回的数据对象
	 * @return
	 */
	public Map<String, Object> genAjaxResponse(boolean result, String msg, Object obj) {
		return genAjaxResponse(result, msg, obj, null);
	}

	/**
	 * 页面操作后的返回信息，该信息会被页面捕获，用于操作提示
	 * 
	 * @param result
	 *            操作成功与否
	 * @param msg
	 *            返回操作后的信息，默认操作成功，一般操作失败会抛出异常信息
	 * @param obj
	 *            操作返回的对象，一般是ajax操作后返回的数据对象
	 * @param other
	 *            此字段特定场合用作业务的查询条件 页面可能需要其他的提示信息，如批量操作中,有些记录没有执行成功，此时需要提示用户
	 * @return
	 */
	public Map<String, Object> genAjaxResponse(boolean result, String msg, Object obj, Object other) {
		Map<String, Object> viewMap = new HashMap<String, Object>();
		viewMap.put("success", result);
		viewMap.put("msg", msg == null ? "操作成功！" : msg);
		// viewMap.put("status", result ? "y" : "n");
		// viewMap.put("info", msg == null ? "操作成功！" : msg);
		if (obj != null) {
			// if (obj instanceof List || obj instanceof Object[]) { // List或者数组
			// viewMap.put("datas", obj);
			// }
			viewMap.put("data", obj);
		}
		if (other != null) {
			viewMap.put("other", other);
		}
		return viewMap;
	}

	/**
	 * 获取At注解的value，既url
	 * 
	 * @return 如果没有则返回空串，以便拼接url时不会出现null
	 */
	public String getRequestMappingValue() {
		Object[] objArr = this.getClass().getDeclaredAnnotations();
		for (Object o : objArr) {
			if (o instanceof At) {
				At at = (At) o;
				String[] arr = at.value();
				if (arr.length == 1) {
					return arr[0];
				}
			}
		}
		return "";
	}

	public boolean isExistParameter(String name) {
		HttpServletRequest request = ContextHolder.getRequest();
		if (request.getParameter(name) != null && request.getParameter(name).length() > 0) {
			return true;
		}
		return false;
	}

	public boolean isExistParameter(String name, String value) {
		HttpServletRequest request = ContextHolder.getRequest();
		if (request.getParameter(name) != null && request.getParameter(name).length() > 0) {
			if (request.getParameter(name).equals(value)) {
				return true;
			}
		}
		return false;
	}

	public String getParameter(String name) {
		return getParameter(name, null);
	}

	public String getParameter(String name, String defaultValue) {
		if (isExistParameter(name)) {
			String str = ContextHolder.getRequest().getParameter(name);
			ContextHolder.getRequest().setAttribute(name, str);
			if (str != null) {
				return str.trim();
			} else {
				return str;
			}
		} else {
			return defaultValue;
		}
	}

	public String[] getParameters(String name) {
		return ContextHolder.getRequest().getParameterValues(name);
	}

	public String getUserName() {
		return ContextHolder.getRequest().getSession().getAttribute(Constants.USERNAME).toString();
	}

	public String getCollectionName() {
		throw new RuntimeException("未指定collectionName!");
	}

//	@At("/delete.action")
//	@Ok("json")
//	@Fail("json")
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isExistParameter(DBC.id)) {
			BasicDBObject filter = new BasicDBObject();
			filter.put(DBC.id, new ObjectId(getParameter(DBC.id)));
			// dao.deleteOne(getCollectionName(), filter);

			BasicDBObject update = new BasicDBObject();
			update.put(DBC.dr, 1);
			update.put(DBC.updateTime, DateUtils.getNowTime().getTime());
			update.put(DBC.ts, DateUtils.getTS());

			dao.updateOne(getCollectionName(), filter, new BasicDBObject("$set", update));
		} else if (isExistParameter("_id[]")) {
			for (String id : request.getParameterValues("_id[]")) {
				BasicDBObject filter = new BasicDBObject();
				filter.put(DBC.id, new ObjectId(id));
				// dao.deleteOne(getCollectionName(), filter);

				BasicDBObject update = new BasicDBObject();
				update.put(DBC.dr, 1);
				update.put(DBC.updateTime, DateUtils.getNowTime().getTime());
				update.put(DBC.ts, DateUtils.getTS());

				dao.updateOne(getCollectionName(), filter, new BasicDBObject("$set", update));
			}
		}

		return genAjaxResponse(true, "操作成功！");
	}

//	@At("/disable.action")
//	@Ok("json")
//	@Fail("json")
	public Map<String, Object> disable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isExistParameter(DBC.id)) {
			BasicDBObject filter = new BasicDBObject();
			filter.put(DBC.id, new ObjectId(getParameter(DBC.id)));

			BasicDBObject update = new BasicDBObject();
			update.put(DBC.enable, false);
			update.put(DBC.updateTime, DateUtils.getNowTime().getTime());
			update.put(DBC.ts, DateUtils.getTS());

			dao.findOneAndUpdate(getCollectionName(), filter, new BasicDBObject("$set", update));
		}

		return genAjaxResponse(true, "操作成功！");
	}

//	@At("/enable.action")
//	@Ok("json")
//	@Fail("json")
	public Map<String, Object> enable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (isExistParameter(DBC.id)) {
			BasicDBObject filter = new BasicDBObject();
			filter.put(DBC.id, new ObjectId(getParameter(DBC.id)));

			BasicDBObject update = new BasicDBObject();
			update.put(DBC.enable, true);
			update.put(DBC.updateTime, DateUtils.getNowTime().getTime());
			update.put(DBC.ts, DateUtils.getTS());

			dao.findOneAndUpdate(getCollectionName(), filter, new BasicDBObject("$set", update));
		}

		return genAjaxResponse(true, "操作成功！");
	}

	public List<Document> doList(BasicDBObject filter) {
		if (ContextHolder.getRequest().getParameter("exportBtn") != null) {
			// 导出不分页
			List<Document> list = dao.find(getCollectionName(), filter);
			return list;
		}

		long time1 = System.currentTimeMillis();
		long count = dao.count(getCollectionName(), filter);
		long time2 = System.currentTimeMillis();
		if ((time2 - time1) > 500) {
			log.warn("count查询缓慢:" + (time2 - time1) + "\n" + this.getCollectionName() + "\n" + filter.toJson());
		}
		ContextHolder.getRequest().setAttribute("size", count);// 获取总记录数

		/**
		 * 分页
		 */
		int pageNum = 1, pageSize = DBC.DEFAULT_PAGE_SIZE;
		if (isExistParameter("pageNum")) {
			pageNum = Integer.parseInt(getParameter("pageNum"));
		}
		if (isExistParameter("pageSize")) {
			pageSize = Integer.parseInt(getParameter("pageSize"));
		}
		ContextHolder.getRequest().setAttribute("pageNum", pageNum);
		ContextHolder.getRequest().setAttribute("pageSize", pageSize);
		int pageNumTotal = (int) Math.ceil((double) count / pageSize);
		ContextHolder.getRequest().setAttribute("pageNumTotal", pageNumTotal);

		BasicDBObject sort = doSort();

		/**
		 * 查询
		 */
		time1 = System.currentTimeMillis();
		List<Document> list = null;
		if (sort != null) {
			list = dao.find(getCollectionName(), filter, sort, pageSize, pageSize * (pageNum - 1));
		} else {
			list = dao.find(getCollectionName(), filter, pageSize, pageSize * (pageNum - 1));
		}
		time2 = System.currentTimeMillis();
		if ((time2 - time1) > 500) {
			log.warn("find查询缓慢:" + (time2 - time1) + "\n" + this.getCollectionName() + "\n" + filter.toJson() + "\n"
					+ +pageSize + "\n" + pageSize * (pageNum - 1));
		}
		if (list.size() == 0 && pageNum > 1) {
			// 如果切换页码后，当前页为空，则返回第一页
			pageNum = 1;
			ContextHolder.getRequest().setAttribute("pageNum", pageNum);
			// list = dao.find(getCollectionName(), filter, sort, pageSize,
			// pageSize * (pageNum - 1));
			list = dao.find(getCollectionName(), filter, pageSize, pageSize * (pageNum - 1));
		}
		return list;
	}

	public BasicDBObject doSort() {
		/**
		 * 排序
		 */
		BasicDBObject sort = null;
		if (isExistParameter("sort1")) {
			sort = new BasicDBObject(getParameter("sort1"), 1);
		} else if (isExistParameter("sort2")) {
			sort = new BasicDBObject(getParameter("sort2"), -1);
		} else {
			// 不排序
		}
		ContextHolder.getRequest().setAttribute("sort1", getParameter("sort1"));
		ContextHolder.getRequest().setAttribute("sort2", getParameter("sort2"));
		return sort;
	}

}
