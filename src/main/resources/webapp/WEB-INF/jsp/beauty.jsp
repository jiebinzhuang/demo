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
        <span  style="width: 100px"><a href="index?type=hot" title="Instagram Posts">Hot</a></span>
        <span style="width: 100px"><a href="index?type=fashion" title="Instagram Posts">Fashion</a></span>
        <span class="current" style="width: 100px"><a href="index?type=beauty" title="Instagram Beauty">Beauty</a></span>
        <span style="width: 100px"><a href="index?type=foods" title="Instagram Foods">Foods</a></span>
        <span style="width: 100px"><a href="index?type=sports" title="Sports">Sports</a></span>
        <span style="width: 100px"><a href="index?type=movie" title="Instagram Movie">Movie</a></span>
        <span style="width: 100px"><a href="index?type=travel" title="Instagram Travel">Travel</a></span>
        <span style="width: 100px"><a href="index?type=digital" title="Instagram Digital">Digital</a></span>
        <span style="width: 100px"><a href="index?type=book" title="Instagram Book">Book</a></span>
        <span style="width: 100px"><a href="index?type=joke" title="Instagram Joke">Joke</a></span>
    </div>
    <div class="bd">
        <li></li>
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
                    <a href="postDetail.action?type=<%=request.getParameter("type")%>&_id=<%=getString(doc.get("_id"))%>" >
                        <% if(!"".equals(getString(doc.get("video_url")))){%>
                        <i class="fr fa fa-video-camera fa-5x fa-inverse" aria-hidden="true"></i>
                        <% }%>
                        <img src="<%=getString(doc.get("preview_img"))%>" class="lazy">

                        <div class="di2-<%=k+1%>">
                            <img src="../image/tag.png" class="lazy">
                            <p>#<%=request.getParameter("type")%></p>
                        </div>
                    </a>

                </div>
                <% }%>
            </div>
            <%}%>

        </li>
        <li> </li>
    </div>
</div>
</body>
</html>

