package com.zhuangjb.web.mvc;

import org.nutz.ioc.Ioc;
import org.nutz.json.JsonFormat;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewMaker;
import org.nutz.mvc.view.ForwardView;
import org.nutz.mvc.view.HttpStatusView;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.RawView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.VoidView;

public class MyViewMaker implements ViewMaker {

	public static final String VIEW_JSP = "jsp";
	public static final String VIEW_JSON = "json";
	public static final String VIEW_REDIRECT = "redirect";
	public static final String VIEW_REDIRECT2 = ">>";
	public static final String VIEW_VOID = "void";
	public static final String VIEW_IOC = "ioc";
	public static final String VIEW_HTTP = "http";
	public static final String VIEW_FORWARD = "forward";
	public static final String VIEW_FORWARD2 = "->";
	public static final String VIEW_RAW = "raw";

	public View make(Ioc ioc, String type, String value) {
		type = type.toLowerCase();
		if (VIEW_JSP.equals(type)) {
			return new JspView(value);
		} else if (VIEW_JSON.equals(type)) {
			return new com.zhuangjb.web.mvc.UTF8JsonView(JsonFormat.full());
		} else if (VIEW_REDIRECT.equals(type) || VIEW_REDIRECT2.equals(type)) {
			return new ServerRedirectView(value);
		} else if (VIEW_FORWARD.equals(type) || VIEW_FORWARD2.equals(type)) {
			return new ForwardView(value);
		} else if (VIEW_VOID.equals(type)) {
			return new VoidView();
		} else if (VIEW_IOC.equals(type)) {
			return ioc.get(View.class, value);
		} else if (VIEW_HTTP.equals(type)) {
			return new HttpStatusView(Integer.parseInt(value));
		} else if (VIEW_RAW.equals(type)) {
			return new RawView(value);
		}
		return null;
	}

}
