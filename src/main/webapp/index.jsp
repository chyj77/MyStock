<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>

    <!-- GC -->
    <script type="text/javascript" src="<%=path %>/js/router.js"></script>
    <script type="text/javascript" src="<%=path %>/js/layout.js"></script>
</head>
<body onload="spaRouters.init();">
<div id="content">
</div>
<script type="text/javascript">
    console.log(location.hash);
    location.hash = "#/ccgp/getAllAjax.htm?p=" + Math.random();
    spaRouters.map('/ccgp/getAllAjax.htm', function (transition) {
        spaRouters.asyncFun('js/ccgpMain.js', transition);
    });
    spaRouters.map('/ztsj/getAllAjax.htm', function (transition) {
        spaRouters.asyncFun('js/ztsjMain.js', transition);
    });
    spaRouters.map('/cdcp2r/getAllAjax.htm', function (transition) {
        spaRouters.asyncFun('js/cdsp2rMain.js', transition);
    });
    spaRouters.map('/spcz/getAllAjax.htm', function (transition) {
        spaRouters.asyncFun('js/spczMain.js', transition);
    });
    spaRouters.map('/gfStock/getAllAjax.htm', function (transition) {
        spaRouters.asyncFun('js/gfStockMain.js', transition)
    });
    spaRouters.map('/cdcp2r/getWeekAnay.htm', function (transition) {
        spaRouters.asyncFun('js/cdsp2rWeek.js', transition)
    });
    spaRouters.map('/ztsj/analytics.htm', function (transition) {
        spaRouters.asyncFun('js/zdsjAnaly.js', transition)
    });
    spaRouters.map('/spcz/getStat.htm', function (transition) {
        spaRouters.asyncFun('js/spczStat.js', transition)
    });
    spaRouters.map('/gfStock/getGfstock.htm', function (transition) {
        spaRouters.asyncFun('js/gfStockAnaly.js', transition)
    });
    spaRouters.map('/shlhb/getAll.htm', function (transition) {
        spaRouters.asyncFun('js/zdsjAnaly.js', transition)
    });
    spaRouters.beforeEach(function (transition) {
        console.log('切换之前dosomething')
        setTimeout(function () {
            //模拟切换之前延迟，比如说做个异步登录信息验证
            transition.next()
        }, 100)
    });
    spaRouters.afterEach(function (transition) {
        console.log("切换之后dosomething");
        if (transition.path.indexOf("/ccgp/getAllAjax.htm") > -1) {
            try {
                console.log(transition.path);
                $j("#jqGrid").trigger("reloadGrid");
            }catch (e){
                console.log("切换之后dosomething 报错了 error");
            }
        }
    });
</script>
</body>
</html>