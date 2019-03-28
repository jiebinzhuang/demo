<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String jsv = "20131024";
	String ctxPath = request.getContextPath();
	String system_name = "内部测试";
	String system_logoSrc = ctxPath + "/images/top-logo.png?v=" + jsv;
	String system_echiUrl = "http://www.zhuangjb.name/";
	String system_copyright="Copyright &copy; 2015 <a href=\""+ctxPath+"/about.html\" target=\"dialog\">zhuangjb</a>";
	String system_qq="http://wpa.qq.com/msgrd?v=3&uin=20025404&site=qq&menu=yes";
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
%>