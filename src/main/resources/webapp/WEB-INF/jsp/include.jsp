<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String jsv = "20160125";
	String ctxPath = request.getContextPath();
	String system_name = "Insgraph";
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
<meta charset="UTF-8">
<title><%=system_name%></title>
