<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="_header.jsp"%>
<link href="<%=ctxPath %>/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<title><%=system_name %></title>
</head>
<body onload="init()">
<input type="hidden" id="TenantId" name="TenantId" value="" />
<!-- <div class="header"></div> -->
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form id="loginform" name="loginForm" class="form form-horizontal" action="loginCheck.action" method="post" onsubmit="return doSubmit(this)">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
<!--       <div class="row cl"> -->
<!--         <div class="formControls col-8 col-offset-3"> -->
<!--           <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;"> -->
<!--           <img src="images/VerifyCode.aspx.png"> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div> -->
<!--       </div> -->
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <label for="online"><input type="checkbox" name="is_save_username" id="is_save_username" value="1">记住用户名</label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input id="submitBtn" name="submitBtn" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="resetBtn" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer"><%=system_copyright %></div>
<%@include file="_footer.jsp"%>
<script type="text/javascript">
//两个参数，一个是cookie的名子，一个是值
function setCookie(name,value){
	var Days = 30;//此 cookie 将被保存 30 天
// 	if(document.loginform.online.checked){
// 		Days = 14;//保存2星期
// 	}
	var exp  = new Date();//new Date("December 31, 9998");
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name){
	//取cookies函数
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr != null){
		return unescape(arr[2]);
	}
	return null;
}

function delCookie(name){
	//删除cookie
	try{
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}catch(ex){
	}
}

function doSubmit(oform){
	setCookie("username",oform.username.value);
	setCookie("is_save_username",oform.is_save_username.checked);
	return true;
}
function init(){
	if(top!=window){
		top.location.href=window.location.href
		return;
	}
	
	var ck_username=getCookie("username");
	var is_save_username=getCookie("is_save_username");
	if(is_save_username!=null&&is_save_username!=""&&is_save_username=="true"){
		document.loginForm.is_save_username.checked=true;
		if(ck_username!=null&&ck_username!=""){
			document.loginForm.username.value=ck_username;
		}
	}
	
	var username=document.loginForm.username.value;
	if(username!=null&&username!=""){
		document.loginForm.password.focus();
	}else{
		document.loginForm.username.focus();
	}
}
</script>
</body>
</html>