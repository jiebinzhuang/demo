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
				 <h2>Download Instagram</h2>
				<form   method="post">
				<div class="login-input">
					<p><input  type="text" name="href" id="txt" placeholder="https://instagram.com/p/41SW_pmmq4/" text="<%=request.getParameter("href")%>">
						<button type="submit" class="btn" id="btn">Download</button></p>
				</div>
				</form>

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
</body>
<!--tab转换-->
<script>
    //    download('https://scontent-hkg3-1.cdninstagram.com/vp/afacc5bde4eed94a37e661aa823dcbc4/5CC68760/t50.2886-16/58673830_135571454251749_8238907259084646332_n.mp4?_nc_ht=scontent-hkg3-1.cdninstagram.com','beyonce.mp4','video/mp4');
    <%--function downl() {--%>
        <%--download('<%=doc.get("video_url")%>','<%=doc.get("username")%>.mp4','video/mp4')--%>
    <%--}--%>

</script>

</html>