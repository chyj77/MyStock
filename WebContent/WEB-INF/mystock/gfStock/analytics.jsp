<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广发股票流水统计</title>
<head>
    <!-- This is the Javascript file of jqGrid -->   
    <script type="text/javascript" src="<%=path%>/jslib/jquery/jquery.jqGrid.js"></script>
    <script type="text/javascript" src="<%=path%>/jslib/jquery/grid.locale-cn.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="<%=path %>/resources/ui.jqgrid.css" />
<script type="text/javascript">
	
	
</script>
</head>
<body>
<table id="jqGrid"></table>
<div id="jqGridPager"></div>
    

    <script type="text/javascript"> 
    
        $j(document).ready(function () {
            $j("#jqGrid").jqGrid({
                url: path + "/gfStock/queryGfstock.htm",
                mtype: "POST",
                datatype: "json",
                colModel: [
                  
                    {
						label: '股票代码',
                        name: 'stockcode',
                        width: 150
                    },
 					{ label: '股票名称', name: 'stockname', width: 150 },
                    { label: '持仓数量', name: 'stocknum', width: 150
                        },
                    {
						label: '盈亏金额',
                        name: 'fsje',
                        width: 150,
                        formatter: 'number',
                        formatoptions :{ decimalSeparator: ".", decimalPlaces:3}
                    },
                    { label:'涨跌幅', name: 'zdf', width: 150,
                        formatter: 'number',
                        formatoptions :{ decimalSeparator: ".", decimalPlaces:3,suffix: ' %'}
                        }
                ],
				viewrecords: true,
                autowidth: true,
//                loadonce: true,
                //autoheight: true,
                height: '100%',
                rowNum: 20,
//                multiSort: true,
                pager: "#jqGridPager",
				footerrow: false, // set a footer row
				userDataOnFooter: true
				
            });
			$j("#jqGrid").jqGrid("navGrid","#jqGridPager",{add:false, edit:false, del:false});
        });
    </script>
</body>
</html>