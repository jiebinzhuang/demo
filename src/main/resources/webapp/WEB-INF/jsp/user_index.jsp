<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%
	String username = (String) request.getSession().getAttribute(
			Constants.USERNAME);
    String modulename = (String) request.getParameter("modulename");
%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="_header.jsp"%>
<link href="<%=ctxPath%>/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<title><%=system_name %></title>
</head>
<body>
<header class="Hui-header cl"> <a class="Hui-logo l" title="H-ui.admin v2.3" href="/"><%=system_name %></a> <a class="Hui-logo-m l" href="/" title="H-ui.admin">H-ui</a> <span class="Hui-subtitle l">V1.0</span>
	<nav class="mainnav cl" id="Hui-nav">
		<ul>	   
			<li><a href="<%=ctxPath %>/index.action">首页</a></li>
		</ul>
	</nav>
	<ul class="Hui-userbar">
		<li>欢迎您，用户</li>
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${USERNAME}<i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:void(0)" onclick="user/customerCard.action">个人信息</a></li>
<!-- 				<li><a href="#">切换账户</a></li> -->
				<li><a href="logout">退出</a></li>
			</ul>
		</li>
<!-- 		<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
		<li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;" data-val="default" title="黑色">黑色</a></li>
				<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
				<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
				<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
				<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
				<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
			</ul>
		</li>
	</ul>
	<a href="javascript:;" class="Hui-nav-toggle Hui-iconfont" aria-hidden="false">&#xe667;</a> 
	</header>
	
 <aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div class="menu_dropdown bk_2">
			<dl id="menu-liuliang">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
					
				</dt>
				<dd>
					<ul>
						<li><a _href="<%=ctxPath%>/admin/device/list.action" data-title="设备管理" href="javascript:void(0)">设备管理</a></li>
							<li><a _href="<%=ctxPath%>/user/showmsg/show.action" data-title="显示消息" href="javascript:void(0)">显示消息</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-xiazhu">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i> 预测管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="<%=ctxPath%>/user/xiazhu/xiazhu.action" data-title="自动预测" href="javascript:void(0)">自动预测</a></li>
						<li><a _href="<%=ctxPath%>/user/xiazhu/list.action" data-title="我的预测情况" href="javascript:void(0)">我的预测情况</a></li>
						<li><a _href="<%=ctxPath%>/admin/xiazhurequest/list.action" data-title="预测记录" href="javascript:void(0)">预测记录</a></li>
						<li><a _href="<%=ctxPath%>/user/xiazhuresult/openresult.action" data-title="开奖结果" href="javascript:void(0)">开奖结果</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-pk10">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i>北京赛车<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="<%=ctxPath%>/pk10/scheme1/list.action" data-title="方案1" href="javascript:void(0)">方案1</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme2/list.action" data-title="方案2" href="javascript:void(0)">方案2</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme3/list.action" data-title="方案3" href="javascript:void(0)">方案3</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme4/list.action" data-title="方案4" href="javascript:void(0)">方案4</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme5/list.action" data-title="方案5" href="javascript:void(0)">方案5</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme6/list.action" data-title="方案6" href="javascript:void(0)">方案6</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme7/list.action" data-title="方案7" href="javascript:void(0)">方案7</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme8/list.action" data-title="方案8" href="javascript:void(0)">方案8</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme9/list.action" data-title="方案9(漂移)" href="javascript:void(0)">方案9(漂移)</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme10/list.action" data-title="方案10(漂移)" href="javascript:void(0)">方案10(漂移)</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme11/list.action" data-title="方案11(漂移)" href="javascript:void(0)">方案11(漂移)</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme12/list.action" data-title="方案12" href="javascript:void(0)">方案12</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme13/list.action" data-title="方案13" href="javascript:void(0)">方案13</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme14/list.action" data-title="方案14" href="javascript:void(0)">方案14</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme15/list.action" data-title="方案15" href="javascript:void(0)">方案15（对子）</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme18/list.action" data-title="方案18" href="javascript:void(0)">方案18</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme19/list.action" data-title="方案19" href="javascript:void(0)">方案19</a></li>
					    <li><a _href="<%=ctxPath%>/pk10/scheme21/list.action" data-title="方案21" href="javascript:void(0)">方案21</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme22/list.action" data-title="方案22" href="javascript:void(0)">方案22</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme23/list.action" data-title="方案23" href="javascript:void(0)">方案23</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme24/list.action" data-title="方案24" href="javascript:void(0)">方案24</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme25/list.action" data-title="方案25" href="javascript:void(0)">方案25</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme26/list.action" data-title="方案26" href="javascript:void(0)">方案26</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme27/list.action" data-title="方案27" href="javascript:void(0)">方案27</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme28/list.action" data-title="方案28" href="javascript:void(0)">方案28</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme29/list.action" data-title="方案29" href="javascript:void(0)">方案29</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme30/list.action" data-title="方案30" href="javascript:void(0)">方案30</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme31/list.action" data-title="方案31" href="javascript:void(0)">方案31</a></li>
						<li><a _href="<%=ctxPath%>/pk10/scheme32/list.action" data-title="方案32" href="javascript:void(0)">方案32</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme33/list.action" data-title="方案33" href="javascript:void(0)">方案33</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme34/list.action" data-title="方案34" href="javascript:void(0)">方案34</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme35/list.action" data-title="方案35" href="javascript:void(0)">方案35</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme36/list.action" data-title="方案36" href="javascript:void(0)">方案36</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme37/list.action" data-title="方案37" href="javascript:void(0)">方案37</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme38/list.action" data-title="方案38" href="javascript:void(0)">方案38</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme39/list.action" data-title="方案39" href="javascript:void(0)">方案39</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme40/list.action" data-title="方案40" href="javascript:void(0)">方案40</a></li>
                        <li><a _href="<%=ctxPath%>/pk10/scheme41/list.action" data-title="方案41" href="javascript:void(0)">方案41</a></li>
					</ul>
				</dd>
			</dl>
			
		</div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
	<div class="dislpayArrow">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的今日输赢情况" data-href="<%=ctxPath%>/user/xiazhuresult/mywinlose.action">我的今日输赢情况</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="<%=ctxPath%>/user/xiazhuresult/mywinlose.action"></iframe>
			</div>
		</div>
	</section>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
</body>
</html>