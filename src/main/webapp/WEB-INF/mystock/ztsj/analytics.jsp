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
                url: path + "/ztsj/queryAnalytics.htm",
                mtype: "POST",
                datatype: "json",
                colModel: [
                  
                    {
						label: '涨停最多概念',
                        name: 'ztzdgn',
                        width: 150
                    },
 					{ label: '日期', name: 'rq', width: 150 ,
                        summaryTpl: "合计: {0}", // set the summary template to show the group summary
                        summaryType: "count" },
                    { label: '每日涨停数量', name: 'mrztgs', width: 150,
                        	summaryTpl: "平均: {0}", // set the summary template to show the group summary
                            summaryType: "avg" ,// set the formula to calculate the summary type
                            formatter: 'number',
                            formatoptions :{ decimalSeparator: ".", decimalPlaces:3}
                        },
                    {
						label: '非一字板个数',
                        name: 'fyzbgs',
                        width: 150,
                        summaryTpl: "平均: {0}", // set the summary template to show the group summary
                        summaryType: "avg" ,// set the formula to calculate the summary type
                        formatter: 'number',
                        formatoptions :{ decimalSeparator: ".", decimalPlaces:3}
                    },
                    { label:'10点前涨停个数', name: 'dqztgs', width: 150,
                    	summaryTpl: "平均: {0}", // set the summary template to show the group summary
                        summaryType: "avg" ,// set the formula to calculate the summary type
                        formatter: 'number',
                        formatoptions :{ decimalSeparator: ".", decimalPlaces:3}
                        },
                        {
    						label: '涨停最多个数',
                            name: 'ztzdgs',
                            width: 150,
                            summaryTpl: "平均: {0}", // set the summary template to show the group summary
                            summaryType: "avg" ,// set the formula to calculate the summary type
                            formatter: 'number',
                            formatoptions :{ decimalSeparator: ".", decimalPlaces:3}
                        },
                        {
    						label: '打板次日高开率',
                            name: 'dbcrgkl',
                            width: 150,
                            summaryTpl: "平均: {0}", // set the summary template to show the group summary
                            summaryType: "avg" ,// set the formula to calculate the summary type
                            formatter: 'number',
                            formatoptions :{ decimalSeparator: ".", decimalPlaces:3,suffix: ' %'}
                        },
                        {
    						label: '收盘成功率',
                            name: 'spcgl',
                            width: 150,
                            summaryTpl: "平均: {0}", // set the summary template to show the group summary
                            summaryType: "avg" ,// set the formula to calculate the summary type
                            formatter: 'number',
                            formatoptions :{ decimalSeparator: ".", decimalPlaces:3,suffix: ' %'}
                        },
                        {
    						label: '10点前涨停高开率',
                            name: 'dqztgkl',
                            width: 150,
                            summaryTpl: "平均: {0}", // set the summary template to show the group summary
                            summaryType: "avg", // set the formula to calculate the summary type
                            formatter: 'number',
                            formatoptions :{ decimalSeparator: ".", decimalPlaces:3,suffix: ' %'}
                        },
                        {
    						label: '被砸数量',
                            name: 'bzsl',
                            width: 150,
                            summaryTpl: "平均: {0}", // set the summary template to show the group summary
                            summaryType: "avg" ,// set the formula to calculate the summary type
                            formatter: 'number',
                            formatoptions :{ decimalSeparator: ".", decimalPlaces:3}
                        },
                        {
    						label: '被砸率',
                            name: 'bzl',
                            width: 150,
                            summaryTpl: "平均: {0}", // set the summary template to show the group summary
                            summaryType: "avg" ,// set the formula to calculate the summary type
                            formatter: 'number',
                            formatoptions :{ decimalSeparator: ".", decimalPlaces:3,suffix: ' %'}
                        }
                ],
				viewrecords: true,
                autowidth: true,
                //autoheight: true,
                height: '100%',
                rowNum: -1,
                scroll: true,
				sortname: 'ztzdgn',
                pager: "#jqGridPager",
				footerrow: true, // set a footer row
				userDataOnFooter: true, // the calculated sums and/or strings from server are put at footer row.
                grouping: true,
                groupingView: {
                    groupField: ["ztzdgn"],
                    groupColumnShow: [true],
                    groupText: ["<b>{0}</b>"],
                    groupSummaryPos: ['header'],
                    groupOrder: ["asc"],
                    groupSummary: [true],
                    groupCollapse: true,
                    showSummaryOnHide:false
                    
                }
            });
			$j("#jqGrid").jqGrid("navGrid","#jqGridPager",{add:false, edit:false, del:false});
        });
    </script>
</body>
</html>