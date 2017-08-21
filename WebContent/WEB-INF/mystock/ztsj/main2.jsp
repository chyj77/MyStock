<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=path %>/jslib/jquery/jquery-3.1.1.js"></script>
<script type="text/javascript" src="<%=path %>/jslib/jquery/jquery.reveal.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/reveal.css"/>
<title>涨停数据</title>
</head>
<style type="text/css">
			body { font-family: "HelveticaNeue","Helvetica-Neue", "Helvetica", "Arial", sans-serif; }
			.big-link { display:block; margin-top: 100px; text-align: center; font-size: 70px; color: #06f; }
</style>
<body>
<a href="#" class="big-link" data-reveal-id="myModal">
			Fade and Pop
		</a>	
		
		<a href="#" class="big-link" data-reveal-id="myModal" data-animation="fade">
			Fade
		</a>
		
		<a href="#" class="big-link" data-reveal-id="myModal" data-animation="none">
			None
		</a>

		<div id="myModal" class="reveal-modal">
			<h1>Reveal Modal Goodness</h1>
			<p>This is a default modal in all its glory, but any of the styles here can easily be changed in the CSS.</p>
			<a class="close-reveal-modal">&#215;</a>
		</div>
</body>  
</html>
