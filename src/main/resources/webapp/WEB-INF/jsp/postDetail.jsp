<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">
<%@include file="include.jsp" %>

<script src="../js/jquery-1.8.2.js"></script>
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="../js/zoom-slideshow.js"></script>
<script src="../js/postdetail.js"></script>
<script src="../js/download.js"></script>

<%--<script src='//js.zapjs.com/js/download.js'></script>--%>

<link rel="stylesheet" href="../css/base.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/postdetail.css">

<%@include file="_header.jsp" %>
<body>
<%
    org.bson.Document doc=(org.bson.Document)request.getAttribute("postdoc");

    ArrayList list= (ArrayList) doc.get("imgs");

%>
<div class="card-note" id="card-note">
    <div>
        <div class="bottom-info">
            <a href="" class="author-info">
                <div class="left">
                    <div class="left-img">
                        <img src="../image/user/<%=doc.get("username")%>.jpg" style="width: 30px;height: 30px;"  alt="">
                    </div>
                </div>
                <div class="right" style="width: 200px">
                    <h6 class="name-bottom" style="font-size: 14px;line-height: 25px"><%=doc.get("username")%></h6>

                </div>
            </a>
            <div class="share-item">
                <p class="title">Share it with friends：</p>
                <div class="all-icon">
                    <a href="" class="wx"></a>
                    <a href="" class="wb"></a>
                    <a href="" class="qq"></a>
                </div>
            </div>
            <div class="codeshow" style="display: none">
                <img src="../images/wxewm.png" alt="">
                <p>分享至微信</p>
            </div>
        </div>
        <div>
            <div>
                <video  id="wrapper_video" autoplay="autoplay" width="800" height="600" src="<%=doc.get("video_url")%>" controls="controls"  preload="auto" ></video>
            </div>

        </div>

        <div class="tags">
            <div class="left-tag">
                <span class="title">Posted on </span>
                <span class="time"><%=doc.get("date")%></span>
            </div>
            <div class="right-tag">
                <a href="javascript:void(0);" onclick="dl()"><i class="fa fa-download d-inline-block mr-1"></i>Download</a>
                <span class="like">
                    <i></i>
                    <span>353</span>
                </span>
                <span class="comment">
                    <i></i>
                    <span>200</span>
                </span>
                <span class="star">
                    <i></i>
                    <span>293</span>
                </span>
            </div>
        </div>

    </div>

    <div class="load" style="display: none">
        <img src="../images/loading.7277f6d.gif" alt="">
    </div>
</div>


<!--二维码的制作-->
<div class="ewm">
    <div class="smallewm"></div>
    <div class="bigewm" style="display:none;">
        <img src="../images/936da4bf-1faa-4e6a-af2d-9b853c3e6e55.png" alt="" class="ewmimg">
        <p class="title">扫我下载APP</p>
    </div>
</div>
</body>
<!--//鼠标移入显示二维码-->
<!--回到顶部-->
<script>
//    download('https://scontent-hkg3-1.cdninstagram.com/vp/afacc5bde4eed94a37e661aa823dcbc4/5CC68760/t50.2886-16/58673830_135571454251749_8238907259084646332_n.mp4?_nc_ht=scontent-hkg3-1.cdninstagram.com','beyonce.mp4','video/mp4');
    function dl() {
        download('<%=doc.get("video_url")%>','<%=doc.get("username")%>.mp4','video/mp4')
    }
    var smallewm=document.querySelectorAll(".smallewm")[0];
    var bigewm=document.querySelectorAll(".bigewm")[0];
    smallewm.onmouseenter=function (){
        bigewm.style.display="block"
    }
    smallewm.onmouseleave=function () {
        bigewm.style.display="none"
    }
</script>
</html>