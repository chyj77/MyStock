<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="<%=path %>/js/ccgpMain.js"></script>
    <script type="text/javascript" src="<%=path %>/jslib/jquery/modalbox.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/flexigrid.pack.css"/>
    <link rel="stylesheet" href="<%=path %>/resources/modalbox.css" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/jslib/jquery/jquery.jqGrid.js"></script>
    <script type="text/javascript" src="<%=path%>/jslib/jquery/grid.locale-cn.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/resources/ui.jqgrid.css" />
    <title>持仓股票</title>
</head>
<body onload="onReady();initWs();">
<div><span id="now" style="color: #0066ff;"></span></div>
<table id="jqGrid"></table>
<div id="jqGridPager"></div>
<div id="jqGridChart" style="float:left;padding-top:15px;padding-left:0px;margin: 0px; width: auto;position: relative; overflow: hidden;"></div>
</body>
</html>
