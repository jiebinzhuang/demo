<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">

<%@include file="include.jsp" %>


<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/base.css">

<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/style.css">

<script src="../js/common.js"></script>
<script src="../js/jquery-1.8.2.js"></script>
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="../js/jquery.lazyload.js"></script>



<link rel="stylesheet" href="../css/search.css">
<script src="../js/shequjingxuan.js"></script>
<%@include file="_header.jsp" %>

<body>
<div class="box" id="box">
	<div class="tab" id="tab">
		<span class="current" style="width: 1120px"> <h2>Most Popular Users</h2> </span>
	</div>
	<div class="bd">
		<li></li>
		<li class="show">
			<%
				java.util.List<org.bson.Document> results=(java.util.List<org.bson.Document>)request.getAttribute("list");
				for(int i=0;i<=results.size()/4;i++){
			%>
			<div class="td2 fl">
				<%
					int start=4*i;
					int end=4;
					if(i==results.size()/4){
						end=results.size()%4;
					}

					for(int k=0;k<end;k++){
						org.bson.Document doc=results.get(k+start);
				%>


				<div class="td2-<%=k+1%>">
					<a href="userPostIndex.action?username=<%=getString(doc.get("username"))%>" >
						<div class="di2-<%=k+1%>">
							<img src="../image/user/<%=getString(doc.get("username"))%>.jpg" class="lazy">
							<p><%=getString(doc.get("username"))%></p>
						</div>
					</a>

				</div>

				<%
				}%>
			</div>
			<%}%>

		</li>
		<li> </li>
	</div>
</div>
</body>
</html>

