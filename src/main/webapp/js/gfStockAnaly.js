SPA_RESOLVE_INIT = function(transition) {
    var jqGrid4 = $j('<table>', {'id': 'jqGrid4'});
    var jqGridPager4 = $j('<div>', {'id': 'jqGridPager4'});
    $j('#tabl8-innerCt').append(jqGrid4);
    $j('#tabl8-innerCt').append(jqGridPager4);

    $j(document).ready(function () {
        $j("#jqGrid4").jqGrid({
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
            pager: "#jqGridPager4",
            footerrow: false, // set a footer row
            userDataOnFooter: true

        });
        $j("#jqGrid4").jqGrid("navGrid","#jqGridPager4",{add:false, edit:false, del:false});
    });
}