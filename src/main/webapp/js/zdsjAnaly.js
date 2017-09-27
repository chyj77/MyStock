SPA_RESOLVE_INIT = function(transition) {
    var jqGrid2 = $j('<table>', {'id': 'jqGrid2'});
    var jqGridPager2 = $j('<div>', {'id': 'jqGridPager2'});
    $j('#tabl6-innerCt').append(jqGrid2);
    $j('#tabl6-innerCt').append(jqGridPager2);

    $j(document).ready(function () {
        $j("#jqGrid2").jqGrid({
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
            pager: "#jqGridPager2",
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
        $j("#jqGrid2").jqGrid("navGrid","#jqGridPager2",{add:false, edit:false, del:false});
    });
}