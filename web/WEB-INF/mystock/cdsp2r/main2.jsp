<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
List list = (List)request.getAttribute("list");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=path %>/jslib//jquery-3.1.1.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h6>
		<a href="">超短2日试盘</a>
	</h6>
	<table border="1">
		<tbody>
			<tr>
				<th>日期</th>
				<th>股票代码</th>
				<th>股票名称</th>
				<th>买入价格</th>
				<th>卖出价格</th>
				<th>盈亏金额</th>
				<th>涨跌率</th>
			</tr>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="cdcp2r">
					<tr>
						<td>${cdcp2r.rq}</td>
						<td>${cdcp2r.gpdm}</td>
						<td>${cdcp2r.gpmc}</td>
						<td>${cdcp2r.mrjg}</td>
						<td>${cdcp2r.mcjg}</td>
						<td>${cdcp2r.ykje}</td>
						<td>${cdcp2r.zdl}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>