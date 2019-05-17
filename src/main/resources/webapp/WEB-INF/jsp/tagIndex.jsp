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



<link rel="stylesheet" href="../css/pick.css">
<script src="../js/shequjingxuan.js"></script>
<%@include file="_header.jsp" %>

<body>
<div class="box" id="box">
    <div class="tab" id="tab">
        <span class="current" style="width: 300px"><%=getString(request.getParameter("content"))%></span>
    </div>
    <div class="bd">
        <li class="show">
            <%
                java.util.List<org.bson.Document> list=(java.util.List<org.bson.Document>)request.getAttribute("list");
                for(int i=0;i<=list.size()/4;i++){
            %>
            <div class="td2 fl">
                <%
                    int start=4*i;
                    int end=4;
                    if(i==list.size()/4){
                        end=list.size()%4;
                    }

                    for(int k=0;k<end;k++){
                        org.bson.Document doc=list.get(k+start);
                %>

                <div class="td2-<%=k+1%>">
                    <a href="getPostByUrl.action?href=<%=getString(doc.get("href"))%>" >
                        <% if("true".equals(getString(doc.get("video")))){%>
                        <i class="fr fa fa-video-camera fa-5x fa-inverse" aria-hidden="true"></i>
                        <% }%>
                        <img src="<%=getString(doc.get("preview_img"))%>" class="lazy">

                        <%--&lt;%&ndash;<p>讲真，如果你不看到最后，你知道这个是什么吗？</p>&ndash;%&gt;--%>
                        <%--<div class="di2-<%=k+1%>">--%>
                            <%--<img src="../image/user/<%=getString(doc.get("username"))%>.jpg" class="lazy">--%>
                            <%--<p><%=getString(doc.get("username"))%></p>--%>
                        <%--</div>--%>
                    </a>
                </div>
                 <% }%>
            </div>
             <%}%>
        </li>

        <li> </li>
        <li> </li>
    </div>
</div>
</body>
</html>
