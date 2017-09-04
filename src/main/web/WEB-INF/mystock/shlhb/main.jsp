<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上海龙虎榜</title>
<head>
<script type="text/javascript">
	
	
</script>
</head>
<body>
    

    <script type="text/javascript"> 
    function showajaxin(begindate){
		
		var tempData = {
			isPageing:true,
			url: 'http://query.sse.com.cn/infodisplay/showTradePublicFile.do',
			params:{
				"isPagination":false,//是否分页
				"dateTx":begindate
			}
		}
		
		jQuery.ajax({
			url: tempData.url,
			type:"post",
			dataType: "jsonp",
			jsonp:'jsonCallBack',
			jsonpCallback: "jsonpCallback" + Math.floor(Math.random() * (100000 + 1)),
			data:tempData.params,
			async:false,
			cache : false,
			success:function(dataJson){
				alert(dataJson.fileContents);
			},complete:function(){
					alert("complete");
				},
			error:function(e){}
		});
		
	}
    showajaxin("2017-03-06")
    </script>
</body>
</html>