<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sha1 test</title>
</head>
<body>

	<h1>sha1 test</h1>

	<div align="center">
		<%
			String str = "appkeyjdHj65pS4n9Odi8Iextno5786ed10d639fa0a745513bcpcodeCMCC_10phoneTc/KA2eRO2LNsOYwJdlllw==pscope中国pstandard7ptime1token3LHxPUzPxT1yR8Q9";
			if(request.getParameter("str")!=null){
				str=request.getParameter("str");
			}	
			String sign = com.mzboss.encrypt.CryptAES.SHA1(str);
			out.print(sign);
		%>

	</div>

</body>
</html>
