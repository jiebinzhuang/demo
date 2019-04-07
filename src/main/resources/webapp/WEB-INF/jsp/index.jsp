<!DOCTYPE html>
<html lang="en">
<%@include file="_header.jsp"%>
<main role="main">

	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="image/yao.jpg" style="width:80% ;height:100%; background:#777 ; color:#777" />
				<div class="container">
					<div class="carousel-caption text-right">
						<h1>Yao Ming</h1>
						<p>Yao Ming (Chinese: 姚明; born September 12, 1980) is a Chinese basketball executive and retired professional basketball player who played for the Shanghai Sharks of the Chinese Basketball Association (CBA) and the Houston Rockets of the National Basketball Association (NBA).</p>
						<p><a class="btn btn-lg btn-primary" href="https://www.instagram.com/yao" role="button">Follow</a></p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<img src="image/jaychou.jpg" style="width:80% ;height:100%; background:#777 ; color:#777" />
				<div class="container">
					<div class="carousel-caption text-left">
						<h1>jaychou</h1>
						<p>Jay Chou (traditional Chinese: 周杰倫; simplified Chinese: 周杰伦; pinyin: Zhōu Jiélún; Wade–Giles: Chou Chieh-lun; born 18 January 1979)[3] is a Chinese musician, singer, songwriter, record producer, film producer, actor, and director.</p>
						<p><a class="btn btn-lg btn-primary" href="https://www.instagram.com/jaychou/" role="button">Follow</a></p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<img src="image/chen.jpg" style="width:80% ;height:100%; background:#777 ; color:#777" />
				<div class="container">
					<div class="carousel-caption text-right">
						<h1>Jackie Chan</h1>
						<p>Datuk Chan Kong-sang SBS[1] MBE[2] PMW[3] (Chinese: 陳港生; born 7 April 1954),[4] known professionally as Jackie Chan, is a Hong Kong martial artist, actor, film director, producer, stuntman, and singer.</p>
						<p><a class="btn btn-lg btn-primary" href="https://www.instagram.com/jackiechan.official/" role="button">Follow</a></p>
					</div>
				</div>
			</div>

		</div>
		<a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a>
		<a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<h2 class="text-center mt-4 mb-1 h3">Instagram Popular Chinese Users</h2>
	<!-- Marketing messaging and featurettes
    ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->
	<h2 class="text-center mt-4 mb-1 h3"></h2>
	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-lg-3">
				<a href="userDetail.action?username=yao" class="pt-2">
					<img src="https://scontent-lax3-2.cdninstagram.com/vp/c06f7c2f99d71a45b5404213891b460b/5D2F88A3/t51.2885-19/s150x150/14350463_663207703854473_784235459_a.jpg?_nc_ht=scontent-lax3-2.cdninstagram.com" style="width:140px ;height:140px; background:#777 ; color:#777" class="rounded-circle"/>
				</a>
				<div class="card-block text-left">
					<h4 class="card-title text-truncate mb-0">&nbsp;&nbsp;&nbsp;&nbsp;<a href="userDetail.action?username=yao" class="pt-2">Yao  Ming</a></h4>
				</div>
			</div>
			<div class="col-lg-3">
				<a href="userDetail.action?username=jaychou" class="pt-2">
					<img src="https://scontent-lax3-2.cdninstagram.com/vp/64202f1ab5248066081ad272b6b15359/5D2ADE9F/t51.2885-19/s150x150/21147825_124638651514445_4540910313213526016_a.jpg?_nc_ht=scontent-lax3-2.cdninstagram.com" style="width:140px ;height:140px; background:#777 ; color:#777" class="rounded-circle"/>
				</a>
				<div class="card-block text-left">
					<h4 class="card-title text-truncate mb-0">&nbsp;&nbsp;&nbsp;&nbsp;<a href="userDetail.action?username=jaychou" class="pt-2">Jay Chou</a></h4>
				</div>
			</div>
			<div class="col-lg-3">
				<a href="userDetail.action?username=jackiechan" class="pt-2">
					<img src="https://scontent-iad3-1.cdninstagram.com/vp/090593c9c3d32b0863112d3ea5a6f1d7/5D2C4523/t51.2885-19/s150x150/20635455_1622953957716779_3883829670394724352_a.jpg?_nc_ht=scontent-iad3-1.cdninstagram.com" style="width:140px ;height:140px; background:#777 ; color:#777" class="rounded-circle"/>
				</a>
				<div class="card-block text-left">
					<h4 class="card-title text-truncate mb-0">&nbsp;&nbsp;&nbsp;<a href="userDetail.action?username=jackiechan" class="pt-2">Jackie Chan</a></h4>
				</div>
			</div>
			<div class="col-lg-3">
				<a href="userDetail.action?username=kimkardashian" class="pt-2">
					<img src="https://web.stagram.com/assets/img/icons/kimkardashian.jpg" style="width:140px ;height:140px; background:#777 ; color:#777" class="rounded-circle"/>
					<h4>kimkardashian</h4>
				</a>
			</div>
			<%--<div class="col-lg-3">--%>
				<%--<a href="userDetail.action?username=leomessi" class="pt-2">--%>
					<%--<img src="https://web.stagram.com/assets/img/icons/leomessi.jpg" style="width:140px ;height:140px; background:#777 ; color:#777" class="rounded-circle"/>--%>
					<%--<h4>&nbsp;&nbsp;Leo&nbsp;Messi</h4>--%>
				<%--</a>--%>
			<%--</div>--%>

		</div><!-- /.row -->




		<hr class="featurette-divider">

		<!-- /END THE FEATURETTES -->

	</div><!-- /.container -->



</main>
<%@include file="_footer.jsp"%>

</html>
