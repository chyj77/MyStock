<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>涨停数据分析</title>
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
                url: path + "/cdcp2r/getWeekAnay.htm",
                mtype: "POST",
                datatype: "json",
                colModel: [
                           { label: '月', name: 'month', width: 150
       					},
 					{ label: '周', name: 'week', width: 150
					},
                    { label: '盈亏额', name: 'ykje', width: 150,
						summaryTpl: "合计: {0}", // set the summary template to show the group summary
                        summaryType: "sum" ,// set the formula to calculate the summary type
                        formatter: 'number',
                        formatoptions :{ decimalSeparator: ".", decimalPlaces:3}
                        },
                    {
						label: '涨跌幅',
                        name: 'zdl',
                        width: 150,
						summaryTpl: "合计: {0}", // set the summary template to show the group summary
                        summaryType: "sum" ,// set the formula to calculate the summary type
                        formatter: 'number',
                        formatoptions :{ decimalSeparator: ".", decimalPlaces:3,suffix: ' %'}
                    },
                  
                    {
						label: '年',
                        name: 'year',
                        width: 150
                    }
                ],
				viewrecords: true,
                autowidth: true,
                //autoheight: true,
                height: '100%',
                rowNum: 20,
                scroll: true,
				sortname: 'year',
                pager: "#jqGridPager",
				footerrow: false, // set a footer row
				userDataOnFooter: false, // the calculated sums and/or strings from server are put at footer row.
                grouping: true,
                groupingView: {
                    groupField: ["year","month"],
                    groupColumnShow: [false,false],
                    groupText: ["<b>{0}</b> 年", "<b>{0}</b> 月"],
                    groupSummaryPos:  ['header', 'header'],
                    groupOrder: ["asc","asc"],
                    groupSummary: [true, false],
                    groupCollapse: false,
                    showSummaryOnHide:true
                    
                }
            });
			$j("#jqGrid").jqGrid("navGrid","#jqGridPager",{add:false, edit:false, del:false});
        });
    </script>
</body>
</html>