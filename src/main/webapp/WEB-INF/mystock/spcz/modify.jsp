<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/resources/flexigrid.pack.css" />
<script type="text/javascript" src="<%=path %>/js/spczMain.js"></script>
<link rel="stylesheet" href="<%=path %>/resources/jquery-ui.css" type="text/css" />
<link rel="stylesheet" href="<%=path %>/resources/jquery-ui.theme.css" type="text/css" />
<title>涨停数据编辑</title>
</head>
<body>
<script type="text/javascript">
    $j("#datepicker").datepicker({//添加日期选择功能  
    numberOfMonths:1,//显示几个月  
    showButtonPanel:true,//是否显示按钮面板  
    dateFormat: 'yy-mm-dd',//日期格式  
    clearText:"清除",//清除日期的按钮名称  
    closeText:"关闭",//关闭选择框的按钮名称  
    yearSuffix: '年', //年的后缀  
    showMonthAfterYear:true,//是否把月放在年的后面  
    defaultDate:new Date(),//默认日期  
//    minDate:'2011-03-05',//最小日期  
//    maxDate:'2011-03-20',//最大日期  
    //monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
    //dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
    //dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
    //dayNamesMin: ['日','一','二','三','四','五','六'],  
    onSelect: function(datepicker) {//选择日期后执行的操作  
        
    }  
    });  
    $j("#datepicker").datepicker('setDate', new Date());
    
</script>
<div class="flexigrid" style="width:'100%';" id="ztsj">  
            <form id="doSave" method="post">  
                <table width="100%" border="0" align="center"  class="flexigrid"
                    cellpadding="0" cellspacing="0">  
                    <tr>  
                        <td>日期：</td>  
                        <td><input type="text" id="datepicker" name="rq" readonly="readonly"></td>  
                        <td><input type="hidden" name="recid"></td>
                        <td>&nbsp;</td>
                    </tr>  
                    <tr>  
                        <td>股票代码：</td>  
                        <td><input type="text" name="code"></td> 
                        <td>股票名称：</td>  
                        <td><input type="text" name="name"></td>  
                    </tr>  
                    <tr>  
                        <td>操作：</td>  
                        <td><input type="radio" name="caozuo" id="caozuo1" checked="checked" value="1" >
                        买</input>
                        <input type="radio" name="caozuo" id="caozuo2"  value="0">
                        <label>卖</label></input>
						</td>  
                        <td>买卖逻辑：</td>  
                        <td><input type="text" name="luoji"></td>
                    </tr>  
                    <tr>  
                        <td>买卖价格：</td>  
                        <td><input type="text" name="jiage"></td> 
                        <td>买卖数量：</td>  
                        <td><input type="text" name="sl"></td>  
                    </tr> 
                </table>  
                </BR>
                <div align="center">  
                    <input type="button" id="submit" class="input-button" value="保存" onclick="doSave()"/>  
                    &nbsp;
                    <input type="button" class="input-button" value="关闭"  onclick="doClose()"/>  
                </div>  
            </form>  
        </div>  
</body>
</html>