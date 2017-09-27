SPA_RESOLVE_INIT = function(transition) {
    var jqGrid1 = $j('<table>', {'id': 'jqGrid1'});
    var jqGridPager1 = $j('<div>', {'id': 'jqGridPager1'});
    $j('#tabl5-innerCt').append(jqGrid1);
    $j('#tabl5-innerCt').append(jqGridPager1);

    $j(document).ready(function () {
        $j("#jqGrid1").jqGrid({
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
            pager: "#jqGridPager1",
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
        $j("#jqGrid1").jqGrid("navGrid","#jqGridPager1",{add:false, edit:false, del:false});
    });
}