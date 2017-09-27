SPA_RESOLVE_INIT = function(transition) {
    var flexme6 = $j('<table>',{'id':'flexme6','style':'display: none;','class':'flexme4'});
    $j('#tabl4-innerCt').append(flexme6);

	$j("#flexme6").flexigrid({
                url : path+"/gfStock/getAllAjax.htm",
                dataType : 'json',
                colModel : [ {
                    display : '成交日期',
                    name : 'stockdate',
                    width : 100,
                    sortable : true,
                    align : 'center'
                    }, {
                        display : '股票代码',
                        name : 'stockcode',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '股票名称',
                        name : 'stockname',
                        width : 100,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '操作',
                        name : 'oper',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '成交数量',
                        name : 'stocknum',
                        width : 80,
                        sortable : true,
                        align : 'center'
                } ,{
                        display : '成交均价',
                        name : 'stockprice',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '成交金额',
                        name : 'stockallprice',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    },  {
                        display : '股票数量',
                        name : 'stocksl',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '可用数量',
                        name : 'stockkysl',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '发生金额',
                        name : 'fsje',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '印花税',
                        name : 'yhs',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '合同编号',
                        name : 'htbh',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '可用余额',
                        name : 'kyje',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '股东账户',
                        name : 'gdzh',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '过户费',
                        name : 'ghf',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '经手费',
                        name : 'jsf',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '证管费',
                        name : 'zgf',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '佣金',
                        name : 'yj',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        name : 'recid',
                        idProperty:'recid',
                        hide: true,
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }],
                
                searchitems : [ {
                    display : '日期',
                    name : 'stockdate'
                    }, {
                        display : '股票代码',
                        name : 'stockcode',
                        isdefault : true
                } , {
                    display : '股票名称',
                    name : 'stockname'
            } ],
                sortname : "stockdate",
                sortorder : "desc",
                usepager : true,
                title : '广发股票流水',
                procmsg : '正在处理,请稍候 ...',
                useRp : true,
                rp : 25,
                showTableToggleBtn : true,
                nomsg: '没有符合条件的记录存在', 
                pagestat: '显示记录从{from}到{to}，总数 {total} 条', 
                striped: true,
                width : "auto",
                height : 700
            });
}
            
