<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

 <!-- GC -->
    <script type="text/javascript" src="<%=path %>/jslib/prototype/prototype.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/prototype//scriptaculous.js?load=effects" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/ext/include-ext.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/ext/examples.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/jquery/jquery-3.1.1.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/jquery/flexigrid.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/jquery/jquery-ui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/jquery/jquery.ui.datepicker-zh-CN.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=path %>/jslib/websocket/bytebuffer.js"></script>
    <script type="text/javascript" src="<%=path %>/jslib/websocket/long.js"></script>
    <!--<script type="text/javascript" src="<%=path %>/jslib/ext/options-toolbar.js"></script> -->

    <!-- Common Styles for the examples -->
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/example.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/jquery-ui.theme.css"/>

    <style type="text/css">
        .x-panel-body p {
            margin: 10px;
            font-size: 12px;
        }
    </style>
    <script type="text/javascript">
    	var basePath = "<%=basePath %>";
    	var path = "<%=path %>";
    	var $j=jQuery.noConflict();
        var ByteBuffer = window.dcodeIO.ByteBuffer;
        var ws = new WebSocket("ws://localhost:9321");
//        var ws = new WebSocket("ws://47.94.93.65:9321");
    </script>
</head>
<body>
</body>
</html>