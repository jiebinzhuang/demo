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
<head>
	<meta charset="UTF-8">
	<title>Insgraph</title>
	<link rel="stylesheet" href="../css/header.css">
	<link rel="stylesheet" href="../css/base.css">
	<link rel="stylesheet" href="../css/社区精选.css">
	<link rel="stylesheet" href="../css/normalize.css">
	<link rel="stylesheet" href="../css/footer.css">
	<script src="../js/common.js"></script>
	<script src="../js/jquery-1.8.2.js"></script>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/jquery.lazyload.js"></script>
	<script src="../js/shequjingxuan.js"></script>
 </head>

<div class="header">
	<div class="header_logo">
		<img src="../image/logo.png" alt="">
		<p style="color:#FF0000">Instagram Viewer/Downloader</p>
	</div>
	<div class="header_middle">
		<a href="index" title="Instagram Posts"><strong>InstagramPost</strong></a>
		<a href="" title="Popular Users"><strong>InsUsers</strong></a>
		<a href="" title="About Us"><strong>AboutUs</strong></a>
		<a href="" title="Posts Download"><strong>PostsDownload</strong></a>
		<a href="" title="Cooperation"><strong>Cooperation</strong></a>
	</div>
</div>
