<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>实盘买卖统计分析</title>
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

    <br /><br />

    <div id="detailsPlaceholder">
        <table id="jqGridDetails"></table>
        <div id="jqGridDetailsPager"></div>
    </div>

    <script type="text/javascript"> 
    
        $j(document).ready(function () {
			
			// master grid
            $j("#jqGrid").jqGrid({
                url: path + "/spcz/queryStat.htm",
                datatype: "json",
                colModel: [
                    { label: '股票代码', name: 'code', key: true, width: 100 },
                    { label: '股票名称', name: 'name', width: 150 },
                    { label: '持仓数量', name: 'sl', width: 150 },
                    { label: '盈亏金额', name: 'ykje', width: 150 },
                    { label: '持仓天数', name: 'ccts', width: 150 }
                ],
                width: 1000,
                height: 400,
                rowNum: 15,
				viewrecords: true,
				loadonce: true,
				caption: '实盘买卖统计分析',
                onSelectRow: function(rowid, selected) {
					if(rowid != null) {
						var detailUrl = path + "/spcz/queryDetail.htm";
						var rowData = $j("#jqGrid").jqGrid('getRowData',rowid);
						var rowCode=rowData.code;
						var param = {"code":rowCode};
						jQuery("#jqGridDetails").jqGrid('setGridParam',{url: detailUrl,datatype: 'json',postData:param}); // the last setting is for demo only
						jQuery("#jqGridDetails").jqGrid('setCaption', '股票明细::'+rowid);
						jQuery("#jqGridDetails").trigger("reloadGrid");
					}					
				}, // use the onSelectRow that is triggered on row click to show a details grid
				onSortCol : clearSelection,
				onPaging : clearSelection,
                pager: "#jqGridPager"
            });
        });
		// detail grid
        $j("#jqGridDetails").jqGrid({
			url: path + "/spcz/queryDetail.htm",
            mtype: "POST",
            datatype: "json",
            page: 1,
			colModel: [
                    { label: '日期', name: 'rq', key: true, width: 75 },
                    { label: '股票代码', name: 'code', width: 100 },
                    { label: '股票名称', name: 'name', width: 100 },
                    { label: '操作', name: 'caozuo', width: 100 },
                    { label: '价格', name: 'jiage', width: 75 },
                    { label: '数量', name: 'sl', width: 75 },
                    { label: '逻辑', name: 'luoji', width: 100 }
			],
			width: 1000,
			rowNum: 8,
			loadonce: true,
			height: '210',
			viewrecords: true,
			caption: '股票明细::',
			pager: "#jqGridDetailsPager"
		});

			function clearSelection() {
				var param = {"code":""};
				jQuery("#jqGridDetails").jqGrid('setGridParam',{url: path + "/spcz/queryDetail.htm", datatype: 'json',postData:param}); // the last setting is for demo purpose only
				jQuery("#jqGridDetails").jqGrid('setCaption', '股票明细:: none');
				jQuery("#jqGridDetails").trigger("reloadGrid");
				
			}
    </script>

</body>
</html>