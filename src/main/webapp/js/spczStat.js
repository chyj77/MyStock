SPA_RESOLVE_INIT = function(transition) {
    var jqGrid3 = $j('<table>', {'id': 'jqGrid3'});
    var jqGridPager3 = $j('<div>', {'id': 'jqGridPager3'});
    $j('#tabl7-innerCt').append(jqGrid3);
    $j('#tabl7-innerCt').append(jqGridPager3);

    var detailsPlaceholder = $j('<div>', {'id': 'detailsPlaceholder'});

    var jqGridDetails = $j('<table>', {'id': 'jqGridDetails'});
    var jqGridDetailsPager = $j('<div>', {'id': 'jqGridDetailsPager'});

    $j('#detailsPlaceholder').append(jqGridDetails);
    $j('#detailsPlaceholder').append(jqGridDetailsPager);

    $j('#tabl7-innerCt').append(detailsPlaceholder);

    $j(document).ready(function () {

        // master grid
        $j("#jqGrid3").jqGrid({
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
                    var rowData = $j("#jqGrid3").jqGrid('getRowData',rowid);
                    var rowCode=rowData.code;
                    var param = {"code":rowCode};
                    jQuery("#jqGridDetails").jqGrid('setGridParam',{url: detailUrl,datatype: 'json',postData:param}); // the last setting is for demo only
                    jQuery("#jqGridDetails").jqGrid('setCaption', '股票明细::'+rowid);
                    jQuery("#jqGridDetails").trigger("reloadGrid");
                }
            }, // use the onSelectRow that is triggered on row click to show a details grid
            onSortCol : clearSelection,
            onPaging : clearSelection,
            pager: "#jqGridPager3"
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
}