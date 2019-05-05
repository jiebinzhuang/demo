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
<link rel="stylesheet" href="../css/品牌账号.css">
<link rel="icon" sizes="any" mask="" href="//ci.xiaohongshu.com/953e8240-67d1-4d6d-94f3-fa699ad27b9a">
<style>


	.aniE3 {
		animation-delay:0.3s;
	}

	.aniE5 {
		animation-delay: 0.5s;
	}


	.aniD1 {
		animation-duration: 1s;
	}

	.aniDe1 {
		animation-delay: 1s;
	}



	.aniDe3 {
		animation-delay: 3s;
	}

	.fadeInDown {
		animation-name: fadeInDown;
	}

	.fadeInLeft {
		animation-name: fadeInLeft;
	}


	.fadeIn {
		animation-name: fadeIn;
	}

	.animated {
		animation-duration: 1s;
		animation-fill-mode: both;
	}

	@keyframes fadeInDown {
		0% {
			opacity: 0;
			transform: translate3d(0, -100%, 0);
		}
		100% {
			opacity: 1;
			transform: none;
		}
	}

	@keyframes fadeInLeft {
		0% {
			opacity: 0;
			transform: translate3d(-100%, 0, 0);
		}
		100% {
			opacity: 1;
			transform: none;
		}
	}

	@keyframes rotateInDownLeft {
		0% {
			transform-origin: left bottom;
			transform: rotate3d(0, 0, 1, -45deg);
			opacity: 0;
		}
		100% {
			transform-origin: left bottom;
			transform: none;
			opacity: 1;
		}
	}

	.bounceInRight {
		animation-name: bounceInRight;
	}
	@keyframes bounceInRight {
		0% {
			opacity: 0;
			transform: translate3d(3000px, 0, 0);
		}
		60% {
			opacity: 1;
			transform: translate3d(-25px, 0, 0);
		}
		75% {
			transform: translate3d(10px, 0, 0);
		}
		90% {
			transform: translate3d(-5px, 0, 0);
		}
		100% {
			transform: none;
		}
	}
	@keyframes bounceInLeft {
		0% {
			opacity: 0;
			transform: translate3d(3000px, 0, 0);
		}
		60% {
			opacity: 1;
			transform: translate3d(25px, 0, 0);
		}
		75% {
			transform: translate3d(-10px, 0, 0);
		}
		90% {
			transform: translate3d(5px, 0, 0);
		}
		100% {
			transform: none;
		}
	}
	@keyframes fadeIn {
		0% {
			opacity: 0;
		}
		100% {
			opacity: 1;
		}
	}
	.zoomIn {
		animation-name: zoomIn;
	}
	@keyframes zoomIn {
		0% {
			opacity: 0;
			transform: scale3d(.3, .3, .3);
		}
		50% {
			opacity: 1;
		}
	}
</style>

<%@include file="_header.jsp" %>
<body>
<%

	java.util.List<org.bson.Document> list=(java.util.List<org.bson.Document>)request.getAttribute("list");


%>
<div class="business-page pc-container">
	<div class="container">
		<div class="inner-brief">
			<h2>品牌认证优势</h2>
			<p>官方品牌认证，多种特权专享</p>
			<div class="content">
				<div class="back"></div>
				<div class="all-list ">
					<div class="each-card fadeInLeft animated aniD1 aniDe1">
						<img src="../images/4e30f1cc-997d-4f09-a556-15738a145cc5.png" alt="">
						<h6>官方认证</h6>
						<p>统一标识</p>
						<p>多样展示</p>
					</div>
					<div class="each-card fadeInLeft animated aniD1 aniDe1">
						<img src="../images/ce501c02-3d4d-4c9d-bfa5-b00ffaca04ae.png" alt="">
						<h6>品牌合作</h6>
						<p>合作数据</p>
						<p>一目了然</p>
					</div>
					<div class="each-card fadeInLeft animated aniD1 aniDe1">
						<img src="../images/464eb07c-9c46-4982-b4ec-16ae448f1ac5.png" alt="">
						<h6>粉丝互动</h6>
						<p>多样营销</p>
						<p>更多互动</p>
					</div>
					<div class="each-card fadeInLeft animated aniD1 aniDe1">
						<img src="../images/29e5bbbd-fc09-4bef-af11-830d3babe4b6.png" alt="">
						<h6>数据分析</h6>
						<p>数据报告</p>
						<p>全域分析</p>
					</div>
					<div class="each-card fadeInLeft animated aniD1 aniDe1">
						<img src="../images/343af3b8-ef78-4b1d-b243-26482cdd694d.png" alt="">
						<h6>流量收拢</h6>
						<p>店铺绑定</p>
						<p>高效转化</p>
					</div>
				</div>
				<div class="bottom-back"></div>
				<div class="red-shadow"></div>
				<a href="社区推广平台.html" class="btn">立即申请</a>
			</div>
		</div>
		<div class="inner-enter">
			<h2>品牌认证流程</h2>
			<h5>通过品牌认证流程，成为官方品牌账号</h5>
			<div class="inner-flow">
				<div class="list">
					<img src="../images/946156ec-5ad1-4ad9-b0c3-770c7f54005e.png" alt="">
					<p class="title">1.点击立即申请</p>
					<div class="content">
						<p>使用小红书APP账号</p>
						<div>
							<p>登录ares.xiaohongshu.com</p>
						</div>
					</div>
				</div>
				<div class="list">
					<img src="../images/94599ec3-d790-4459-aab9-16d5d038ba38.png" alt="">
					<p class="title">2.提交认证材料</p>
					<div class="content">
						<p>根据提示要求，提交认证材料</p>
						<div>
							<a href="#mao">查看认证材料要求</a>
						</div>
					</div>
				</div>
				<div class="list">
					<img src="../images/cf214749-1e2f-4092-adb6-b8dd3dea3f58.png" alt="">
					<p class="title" >3.开启认证</p>
					<div class="content">
						<p>成为品牌账号</p>
					</div>
				</div>
			</div>
		</div>
		<div id="mao" class="inner-detail">
			<h4>品牌认证指南</h4>
			<div class="top-topic">
				<div class="inner select">国内品牌</div>
				<div class="inner">国外品牌</div>
			</div>
			<div class="middle-topic" >
				<div class="inner">
					<h4>基础信息</h4>
					<ul>
						<div class="main"><p class="info">账号名字</p></div>
						<div class="main"><p class="info">品牌简介</p></div>
						<div class="main"><p class="info">行业类别</p></div>
						<div class="main"><p class="info">所在地</p></div>
					</ul>
				</div>
				<div class="inner">
					<h4>头像/证明材料</h4>
					<ul>
						<div class="main">
							<p class="info">头像</p>
							<li>头像必须为品牌形象，如品牌LOGO或企业商标</li>
						</div>
						<div class="main">
							<p class="info">认证公函</p>
							<li>
								<span>公函需下载打印填写，并加盖</span>
								<span class="red">品牌方</span>
								<span>的实体公章后拍摄上传，公函中的“小红书账号名字”需与商标注册证一致 </span>
								<a href="../images/42cf420c-70b0-4a1c-b438-57b03e12370c.png" target="_blank">查看示例</a>
								<a href="../images/application.pdf" target="_blank">获取认证公函</a>
							</li>
						</div>
						<div class="main">
							<p class="info">企业营业执照</p>
							<li>
								<span>上传</span>
								<span class="red">品牌方</span>
								<span> 的中国大陆工商局或市场监督管理局的最新工商营业执照（三证合一） </span>
								<a href="../images/dae20c4e-c3c5-473a-84ad-547f57f6ad32.png" target="_blank">查看示例</a>
							</li>
						</div>
						<div class="main">
							<p class="info">商标注册证</p>
							<li>若品牌认证名称/信息包含品牌，需上传商标注册证</li>
						</div>
						<div class="main">
							<p class="info">网站ICP备案截图</p>
							<li>若品牌账号认证内容为网站，需上传网站在工信部网站备案查询的截图，要求内容真实完整、清晰可识别，需加盖网站主办单位公章</li>
						</div>
						<div class="main">
							<p class="info">软件著作证</p>
							<li>若品牌账号认证内容为软件/APP，需提供软件著作权登记证书原件彩色扫描件
							</li>
						</div>
					</ul>
				</div>
				<div class="inner">
					<h4>企业信息</h4>
					<ul>
						<div class="main"><p class="info">企业名称</p></div>
						<div class="main"><p class="info">统一社会信用代码</p></div>
						<div class="main"><p class="info">营业期限</p></div>
						<div class="main"><p class="info">运营者姓名</p></div>
						<div class="main"><p class="info">运营者手机号</p></div>
					</ul>
				</div>
			</div>
			<div class="middle-topic" style="display: none">
				<div class="inner">
					<h4>基础信息</h4>
					<ul>
						<div class="main"><p class="info">账号名字</p></div>
						<div class="main"><p class="info">品牌简介</p></div>
						<div class="main"><p class="info">行业类别</p></div>
						<div class="main"><p class="info">所在地</p></div>
					</ul>
				</div>
				<div class="inner">
					<h4>头像/证明材料</h4>
					<ul>
						<div class="main">
							<p class="info">头像</p>
							<li>头像必须为品牌形象，如品牌LOGO或企业商标</li>
						</div>
						<div class="main">
							<p class="info">认证公函</p>
							<li>
								<span>公函需下载打印填写，并加盖</span>
								<span class="red">品牌方</span>
								<span>的实体公章后拍摄上传，公函中的“小红书账号名字”需与商标注册证一致 </span>
							</li>
						</div>
						<div class="main">
							<p class="info">公司注册文件</p>
							<li>
								Business registration（简称“BR”）或Certificate of incorporation（简称“CR”）的原件彩色扫描件，若为香港公司，BR和CR均需上传。
							</li>
						</div>
						<div class="main">
							<p class="info">商标注册证</p>
							<li>若品牌认证名称/信息包含品牌，需上传商标注册证</li>
						</div>
						<div class="main">
							<p class="info">网站ICP备案截图</p>
							<li>若品牌账号认证内容为网站，需上传网站在工信部网站备案查询的截图，要求内容真实完整、清晰可识别，需加盖网站主办单位公章</li>
						</div>
					</ul>
				</div>
				<div class="inner">
					<h4>企业信息</h4>
					<ul>
						<div class="main"><p class="info">企业名称</p></div>
						<div class="main"><p class="info">统一社会信用代码</p></div>
						<div class="main"><p class="info">营业期限</p></div>
						<div class="main"><p class="info">运营者姓名</p></div>
						<div class="main"><p class="info">运营者手机号</p></div>
					</ul>
				</div>
			</div>
		</div>
		<div class="inner-issue">
			<h2>常见问题</h2>
			<div class="allissue">
				<div class="inner">
					<h4>Q：什么是品牌方？</h4>
					<p>A：一般情况下，品牌方指商标注册人。</p>
				</div>
				<div class="inner">
					<h4>Q：关于营业执照的要求</h4>
					<p>A：上传品牌方的中国大陆工商局或市场监督管理局的最新工商营业执照（三证合一）</p>
				</div>
				<div class="inner">
					<h4>Q：关于认证公函的要求</h4>
					<p>A：公函需下载打印填写，并加盖品牌方的实体公章后拍摄上传，公函中的“小红书账号名字”需与商标注册证一致</p>
				</div>
			</div>
		</div>
	</div>
</div>
<!--foot-bottom的制作-->
<div class="footer">
	<div class="footer_top">
		<div class="footer_inner">
			<div class="left fl">
				<img class="footer_logo" src="../images/881ae53d-2f84-4257-86c3-10e9805ad19f.png" title="" alt="">
				<div class="icon-btn">
					<a href="">
						<span id="wx"></span>
						<img id="wxe" src="../images/c6e68107-edae-41b2-85af-1644cf5988ad%20(1).jpg" alt="">
					</a>
					<a href="//weibo.com/xhsapp?refer_flag=1001030101_">
						<span></span>
					</a>
					<a href="https://itunes.apple.com/cn/app/%E5%B0%8F%E7%BA%A2%E4%B9%A6-%E6%A0%87%E8%AE%B0%E6%88%91%E7%9A%84%E7%94%9F%E6%B4%BB/id741292507?mt=8">
						<span></span>
						ios
					</a>
					<a href="https://www.wandoujia.com/apps/com.xingin.xhs">
						<span></span>
						安卓
					</a>
				</div>
			</div>
			<div class="right fr">
				<div class="beed fl">
					<span></span>
					<div class="tab">
						<span>公司相关</span>
						<a href="" title="小红书_加入我们">加入我们</a>
						<a href="招商合作.html" title="小红书_招商合作">招商合作</a>
						<a href="关于我们.html" title="小红书_关于我们">关于我们</a>
					</div>
				</div>
				<div class="beed fl">
					<span></span>
					<div class="tab">
						<span>内容相关</span>
						<a href="社区精选.html" title="小红书_社区精选">社区精选</a>
						<a href="" title="小红书_媒体报道">媒体报道</a>
					</div>
				</div>
				<div class="beed fl">
					<span></span>
					<div class="tab">
						<span>协议条款</span>
						<a href="" title="小红书_注册协议">注册协议</a>
						<a href="" title="小红书_隐私协议">隐私协议</a>
						<a href="" title="小红书_侵权投诉指引">侵权投诉指引</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="inner-bottom">
		<div class="inner">
			<div class="info">
				<a target="_blank" href="" title="小红书_沪ICP备">沪ICP备 13030189号 Copyright © 2014-<span>2018</span>行吟信息科技（上海）有限公司</a>
				<span>| 地址：上海市黄浦区马当路388号C座</span>
				<span>| 电话：021-64224530</span>
				| <a  target="_blank" href=""> 自营经营者营业执照</a>
				<a target="_blank" href="" title="小红书_沪公网安备"><span>2018</span>  沪公网安备 31010102002533号<i class="police"></i></a>
			</div>
			<div class="info">
				<span title="小红书_网文">沪网文〔2018〕4086-308号</span>
				| <a  target="_blank" href="" title="小红书_网络文化经营许可"> 网络文化经营许可证：沪网文[2018]4086-308号</a>
				| <a  target="_blank" href="" title="小红书_网文">增值电信业务经营许可证：沪B2-20150021</a>
				| <a  target="_blank" href="" title="小红书_食品经营">食品经营许可证：JY13101140093802</a>
				| <a  target="_blank" href="" title="小红书_出版物经营">出版物经营许可证：新出发沪零字第M7553号</a></div> <div data-v-3b6d624c="" class="info"><a target="_blank" href="//o3.xiaohongshu.com/d/5-xingyin-sh-yiliaoqixie.pdf" title="小红书_医疗器械经营">医疗器械经营许可证：沪嘉食药监械经营许20187006号</a>
			| <a  target="_blank" href="" title="小红书_互联网药品信息服务">互联网药品信息服务资格证书：(沪)-经营性-2018-0011 </a>
			| <span title="小红书_违法不良信息">违法不良信息举报电话：(027) 5931 3970</span>
			| <span>上海市互联网举报中心</span><i class="email"></i>
		</div>
		</div>
	</div>
	<script>
        $(function () {
            $wx=$("#wx");
            $wxe=$("#wxe");
            $wx.mouseover(function () {
                $wxe.css("display","block");
            }).mouseout(function () {
                $wxe.css("display","none");
            })
        })
	</script>

</div>
<!--回到顶部的制作-->
<div class="backtop" style="display:none;">

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