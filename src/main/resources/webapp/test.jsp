<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JNDI Test</title>
</head>
<body>

	<h1>JNDI Test</h1>

	<div align="center">
		<%
			String jndiName = request.getParameter("jndi");
			if (jndiName == null) {
				jndiName = "jdbc/ROEEE";
			}
			String[] jndiPrefix = { "java:comp/env/", "" };
			javax.naming.InitialContext ctx = new javax.naming.InitialContext();
			for (int i = 0; i < jndiPrefix.length; i++) {
				String prefix = jndiPrefix[i];
				out.print("<div>");
				String jndiStr = prefix + jndiName;
				out.print(jndiStr);
				try {
					javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(jndiStr);
					try {
						java.sql.Connection conn = ds.getConnection();
						conn.close();
						out.print("<span style='color:blue'> 获取连接成功.</span>");
					} catch (java.lang.Exception e1) {
						out.print("<span style='color:red'> 获取连接失败!</span>");
					}
					// 					out.print("<span style='color:blue'> ok.</span>");
				} catch (java.lang.Exception e2) {
					out.print("<span style='color:red'> fail:"+(e2.getMessage()==null?"":e2.getMessage())+"</span>");
				}
				out.print("</div>");
			}
		%>

	</div>

</body>
</html>
