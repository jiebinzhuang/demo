<!DOCTYPE html>
<%@include file="_header.jsp"%>
<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-5cc0383de6815676"></script>
<body>

	<h2 class="text-center mt-4 mb-1 h3">Instagram Popular Users</h2>
	<!-- Marketing messaging and featurettes
    ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->
	<h2 class="text-center mt-4 mb-1 h3"></h2>
	<div class="container marketing">
		<%
			java.util.List<org.bson.Document> list=(java.util.List<org.bson.Document>)request.getAttribute("list");
			for(int i=0;i<list.size()/4;i++){
		%>
		<div class="row">
			<%
                int k=i*4;
				for(int m=0;m<4;m++){
					org.bson.Document doc=list.get(k+m);
			%>
			<div class="col-lg-3">
				<a href="userDetail.action?username=yao" class="pt-2">
					<img src="image/<%=getString(doc.get("username"))%>.jpg" style="width:140px ;height:140px; background:#777 ; color:#777" class="rounded-circle"/>
				</a>
				<div class="card-block text-left">
					<h4 class="card-title text-truncate mb-0">&nbsp;&nbsp;&nbsp;&nbsp;<a href="userDetail.action?username=yao" class="pt-2"><%=getString(doc.get("alias")) %></a></h4>
				</div>
			</div>
			<%
				}
			%>
		</div><!-- /.row -->
		<%
			}
		%>

	</div><!-- /.container -->

<%@include file="_footer.jsp"%>
</body>
</html>
