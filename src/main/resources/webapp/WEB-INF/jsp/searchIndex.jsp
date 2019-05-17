<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">
<%@include file="include.jsp" %>

<script src="../js/common.js"></script>
<script src="../js/jquery-1.8.2.js"></script>
<link rel="stylesheet" href="../css/base.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/normalize.css">
<link rel="stylesheet" href="../css/brand.css">
<link rel="stylesheet" href="../css/third.css">
<link rel="stylesheet" href="../css/search.css">


<%@include file="_header.jsp" %>
<body>
<div class="business-page pc-container">
	<div class="container">
			<div class="inner-brief">
				 <h2>Search Instagram</h2>
				<form name="searchForm" method="post">
				<div class="login-input">
					<p><input   type="text" name="content" id="txt" placeholder="Search Instagram User Or Tag">
						<button type="submit" class="btn" id="btn">Search</button></p>
				</div>
				</form>
				<div>
					<p>
					<button onclick="location.href='search.action?content=Russia'" class="btn" style="height: 20px;width: 100px" >#Russia</button>
						<button onclick="location.href='search.action?content=liverpool'" class="btn" style="height: 20px;width: 100px" >#liverpool</button>
						<button onclick="location.href='search.action?content=spur'" class="btn" style="height: 20px;width: 100px" >#spur</button>
						<button onclick="location.href='search.action?content=rocket'" class="btn" style="height: 20px;width: 100px" >#rocket</button>
						<button onclick="location.href='search.action?content=USA'" class="btn" style="height: 20px;width: 100px" >#USA</button>
						<button onclick="location.href='search.action?content=China'" class="btn" style="height: 20px;width: 100px" >#China</button>
						<button onclick="location.href='search.action?content=NBA'" class="btn" style="height: 20px;width: 100px" >#NBA</button>
						<button onclick="location.href='search.action?content=kimkardashian'" class="btn" style="height: 20px;width: 100px" >#kimkardashian</button>
						<button onclick="location.href='search.action?content=Messi'" class="btn" style="height: 20px;width: 100px" >#Messi</button>

				</div>

			</div>
		</div>

	</div>
</div>

<!--回到顶部的制作-->
<div class="backtop" style="display:none;">

</div>
<%--<!--二维码的制作-->--%>
<%--<div class="ewm">--%>
	<%--<div class="smallewm"></div>--%>
	<%--<div class="bigewm" style="display:none;">--%>
		<%--<img src="../images/936da4bf-1faa-4e6a-af2d-9b853c3e6e55.png" alt="" class="ewmimg">--%>
		<%--<p class="title">扫我下载APP</p>--%>
	<%--</div>--%>
<%--</div>--%>
<%--</body>--%>
<!--tab转换-->
<script>

    //tab转换
    var alldivs=document.querySelectorAll(".top-topic div");
    var allclass=document.querySelectorAll(".inner-detail .middle-topic")
    for(var i=0;i<alldivs.length;i++){
        alldivs[i].index=i;
        alldivs[i].onclick=function () {
            for(var j=0;j<alldivs.length;j++){
                alldivs[j].className="inner";
            }
            this.className="inner select";
            var index=this.index;
            for(var j=0;j<allclass.length;j++){
                allclass[j].style.display="none";
            }
            allclass[index].style.display="block";
        }
    }


    //鼠标移入显示二维码
    var smallewm=document.querySelectorAll(".smallewm")[0];
    var bigewm=document.querySelectorAll(".bigewm")[0];
    smallewm.onmouseover=function (){
        bigewm.style.display="block"
    }
    smallewm.onmouseleave=function () {
        bigewm.style.display="none"
    }



    //回到顶部
    var header=document.querySelectorAll(".header")[0];
    var backtop=document.querySelectorAll(".backtop")[0];
    header.className="header"
    window.onscroll=function (e) {
        var oev=e||window.event;
        var scrollTop=getScroll().scrollTop;
        if(scrollTop>10){
            header.className="header";
            backtop.style.display="block"
            backtop.onmouseover=function () {
                backtop.className="scrolltop"
            }
            backtop.onmouseleave=function (){
                backtop.className="backtop"
            }
        }
        else{
            header.className="header"
            backtop.style.display="none"
        }
    }
    backtop.onclick=function () {
        var timer=setInterval(function () {
            var currentScrollTop=getScroll().scrollTop;
            var targetScrollTop=0;
            var step=-20;
            if(Math.abs(currentScrollTop-targetScrollTop)<Math.abs(step)){
                document.body.scrollTop=targetScrollTop;
                document.documentElement.scrollTop=targetScrollTop;
                clearInterval(timer);
                return
            }
            currentScrollTop+=step;
            document.body.scrollTop=currentScrollTop;
            document.documentElement.scrollTop=currentScrollTop;
        },1)
    }


</script>

</html>