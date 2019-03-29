<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="_header.jsp"%>
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>Insgraph</title>
</head>
<body ontouchstart>
<div class="containBox">
	<div class="containBox-bg"></div>
	<header class="navbar-wrapper">
		<div class="navbar navbar-black navbar-fixed-top">
			<div class="container cl">
				<a class="logo navbar-logo hidden-xs" >Insgraph</a>
				<span class="logo navbar-slogan hidden-xs">简单 &middot; 免费 &middot; 分享你的Graph</span>
				<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs JS-nav-toggle" href="javascript:;">&#xe667;</a>
				<nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
					<ul class="cl">
						<li class="current">
							<a href="http://www.insgraph.com/" target="_blank">首页</a>
						</li>
						<li>
							<a href="http://www.insgraph.com" target="_blank">核心</a>
						</li>
						<li>
							<a href="http://www.insgraph.com" target="_blank">联系我们</a>
						</li>
					</ul>
				</nav>
				<nav class="navbar-userbar hidden-xs"></nav>
			</div>
		</div>
	</header>
	<div class="wap-container">


		<div class="container ui-sortable">
			<div class="panel panel-default mt-20">
				<div class="panel-header">图片</div>
				<div class="panel-body">
					<p><img width="140" height="140" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTEwYmJhZjQzYSB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1MTBiYmFmNDNhIj48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9Ijc0LjUiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" alt="..." class="radius">
						<img width="140" height="140" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTEwYmJhZjQzYSB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1MTBiYmFmNDNhIj48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9Ijc0LjUiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" alt="..." class="round">
						<img style="width:140px; height:140px" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTEwYmJhZjQzYSB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1MTBiYmFmNDNhIj48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9Ijc0LjUiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" alt="..." class="thumbnail"></p>
					<div class="album-item" style="width:200px">
						<div class="album-img">
							<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTEwYmJhZjQzYSB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1MTBiYmFmNDNhIj48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9Ijc0LjUiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=">
						</div>
						<div class="album-title">《仙剑奇侠传》赵灵儿
							<span class="c-999">(20张)</span>
						</div>
						<div class="album-bg">
							<div class="album-bg-Fir"></div>
							<div class="album-bg-Sec"></div>
						</div>
					</div>
					<div>
						<img src="static/h-ui/images/ucnter/avatar-default.jpg" class="avatar radius size-MINI">
						<img src="static/h-ui/images/ucnter/avatar-default.jpg" class="avatar radius size-S">
						<img src="static/h-ui/images/ucnter/avatar-default.jpg" class="avatar radius size-M">
						<img src="static/h-ui/images/ucnter/avatar-default.jpg" class="avatar radius size-L">
						<img src="static/h-ui/images/ucnter/avatar-default.jpg" class="avatar radius size-XL">
					</div>
					<div class="maskWraper" style="width:300px; height:250px; border:solid 1px #ddd;">
						<img src="http://images.h-ui.net/www/AD-300x250.gif" width="300" height="250">
						<div class="maskBar text-c">遮罩条</div>
					</div>
				</div>
			</div>

			<div class="panel panel-default mt-20">
				<div class="panel-header">分享到</div>
				<div class="panel-body">
					<section class="share cl">
						<div class="bdsharebuttonbox Hui-share">
							<span class="share-text Hui-iconfont">&#xe715;</span>
							<a href="#" class="bds_weixin Hui-iconfont" data-cmd="weixin" title="分享到微信">&#xe694;</a>
							<a href="#" class="bds_qzone Hui-iconfont" data-cmd="qzone" title="分享到QQ空间">&#xe6c8;</a>
							<a href="#" class="bds_sqq Hui-iconfont" data-cmd="sqq" title="分享到QQ好友">&#xe67b;</a>
							<a href="#" class="bds_tsina Hui-iconfont" data-cmd="tsina" title="分享到新浪微博">&#xe6da;</a>
							<a href="#" class="bds_tqq Hui-iconfont" data-cmd="tqq" title="分享到腾讯微博">&#xe6d9;</a>
							<a href="#" class="bds_douban Hui-iconfont" data-cmd="douban" title="分享到豆瓣网">&#xe67c;</a>
						</div>
						<script type="text/javascript">
                            window._bd_share_config={
                                "common":{
                                    "bdSnsKey":{},
                                    "bdText":"H-ui前端框架，架起设计与后端的桥梁轻量级前端框架，简单免费，兼容性好，服务中国网站。",
                                    'bdDes':'国内免费轻量级前端框架',
                                    'bdPopTitle':'H-ui前端框架',
                                    "bdMini":"2",
                                    "bdMiniList":false,
                                    "bdPic":"http://static.h-ui.net/h-ui/images/logo-big.jpeg",
                                    "bdStyle":"0",
                                    "bdSize":"24"
                                },
                                "selectShare":{
                                    "bdContainerClass":null,
                                    "bdSelectMiniList":["weixin","qzone","sqq","tsina","tqq","douban"]
                                }
                            };
                            with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
						</script>
					</section>
				</div>
			</div>


		<footer class="footer mt-20">
			<div class="container">
				<nav class="footer-nav">
					<a target="_blank" >关于我们</a>
					<span class="pipe">|</span>
					<a target="_blank" >软件著作权</a>
					<span class="pipe">|</span>
					<a target="_blank" >感谢捐赠</a>
				</nav>
				<p>Copyright &copy;2013-2017 H-ui.net All Rights Reserved. <br>
					<a rel="nofollow" target="_blank" href="http://www.miitbeian.gov.cn/">京ICP备3422223223号-1</a>
			</div>
		</footer>
	</div>
</div>
</body>
</html>