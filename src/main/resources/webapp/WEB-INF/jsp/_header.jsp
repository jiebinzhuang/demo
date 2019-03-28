<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.zhuangjb.system.Constants"%>
<%
	String jsv = "20160125";
	String ctxPath = request.getContextPath();
	String system_name = "快乐联盟平台";
	String system_logo = ctxPath + "/images/logo.png?v=" + jsv;
// 	String system_url = "http://www.fangwei.name/";
// 	String system_copyright="Copyright &copy; 2016 <a href=\""+system_url+"\" target=\"_blank\">zhuangjb</a>";
	String system_url = "#";
	String system_copyright="Copyright &copy; 2016 <a href=\""+system_url+"\" target=\"_blank\"></a>";
// 	String system_qq="http://wpa.qq.com/msgrd?v=3&uin=20025404&site=qq&menu=yes";
%>
<%!
public static String getString(Object value){
	return getString(value,"");
}
public static String getString(Object value,String defaultValue){
	if(value==null){
		return defaultValue;
	}else{
		return value.toString();
	}
}
public static Integer getInteger(Object value){
	return getInteger(value,null);
}
public static Integer getInteger(Object value,Integer defaultValue){
	if(value==null){
		return defaultValue;
	}else{
		if(value instanceof String){
			return Integer.valueOf(value.toString());
		}else{
			return (Integer)value;
		}
	}
}
public static Double getDouble(Object value){
	return getDouble(value,null);
}
public static Double getDouble(Object value,Double defaultValue){
	if(value==null){
		return defaultValue;
	}else{
		if(value instanceof String){
			return Double.valueOf(value.toString());
		}else{
			return (Double)value;
		}
	}
}
public static Double getMoney(Object value){
	if(value==null){
		return 0d;
	}
	
	Double d=null;
	if(value instanceof Double){
		d= (Double)value;
	}else if(value instanceof String){
		d= Double.valueOf(value.toString());
	}else{
		d= Double.valueOf(value.toString());
	}
	if(d==null){
		return 0d;
	}else{
		return com.zhuangjb.util.DoubleUtils.format(d);
	}
}
public static String convertLongTime(Object value){
	if(value==null){
		return "";
	}else{
		java.util.Date date=new java.util.Date((Long)value);
		return com.zhuangjb.util.DateUtils.formatDate(date, com.zhuangjb.util.DateUtils.DATEFORMAT_HORIZONTAL);
	}
}
%>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="<%=ctxPath %>/favicon.ico" >
<LINK rel="Shortcut Icon" href="<%=ctxPath %>/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=ctxPath %>/lib/html5.js"></script>
<script type="text/javascript" src="<%=ctxPath %>/lib/respond.min.js"></script>
<script type="text/javascript" src="<%=ctxPath %>/lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="<%=ctxPath %>/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=ctxPath %>/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=ctxPath %>/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="<%=ctxPath %>/skin/blue/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="<%=ctxPath %>/lib/Hui-iconfont/1.0.6/iconfont.css" rel="stylesheet" type="text/css" />
<link href="<%=ctxPath %>/css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=ctxPath %>/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<meta name="keywords" content="<%=system_name%>">
<meta name="description" content="<%=system_name%>">
