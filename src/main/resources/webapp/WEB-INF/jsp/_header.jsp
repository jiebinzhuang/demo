<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String jsv = "20160125";
	String ctxPath = request.getContextPath();
	String system_name = "Insgraph";
%>

<meta charset="utf-8">
 <header>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="Zhuangjb">
	<meta name="generator" content="Insgraph v1.0.1">
	<title>Insgraph</title>

	<%--<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/carousel/">--%>

	<!-- Bootstrap core CSS -->
	<link href="bootstrap-4.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="css/carousel.css" rel="stylesheet">
	 <link href="css/style.css" rel="stylesheet">


	 <%--<link href="<%=ctxPath %>/css/font-awesome.min.css" rel="stylesheet">--%>
	 <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

	 <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="http://www.insgraph.com">Insgraph<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link disabled" href="#">Online Instagram Image and Video  Viewer and Downloader</a>
				</li>
			</ul>
			<form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search Instagram Hash" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search </button>
			</form>
		</div>
	</nav>
</header>
