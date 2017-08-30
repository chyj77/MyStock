Ext.require([ 'Ext.Msg.*','Ext.example.*']);
function onReady(){
	$j(".flexme4").flexigrid({
                url : path+"/ccgp/getAllAjax.htm",
                dataType : 'json',
                colModel : [ {
                    display : '日期',
                    name : 'rq',
                    width : 100,
                    sortable : true,
                    align : 'center'
                    }, {
                        display : '股票代码',
                        name : 'code',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '股票名称',
                        name : 'name',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '买入价',
                        name : 'buyprice',
                        width : 100,
                        sortable : true,
                        align : 'center'
                    } ,{
                        display : '数量',
                        name : 'sl',
                        width : 100,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '当前价格',
                        name : 'nowprice',
                        width : 100,
                        sortable : true,
                        align : 'center'
                    },  {
                        name : 'recid',
                        idProperty:'recid',
                        hide: true,
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }],                
                searchitems : [ {
                    display : '日期',
                    name : 'rq'
                    }, {
                        display : '股票代码',
                        name : 'code',
                        isdefault : true
                } , {
                    display : '股票名称',
                    name : 'name'
            } ],
                sortname : "rq",
                sortorder : "desc",
                usepager : true,
                title : '持仓股票',
                procmsg : '正在处理,请稍候 ...',
                useRp : true,
                rp : 25,
                showTableToggleBtn : true,
                pagestat: '显示记录从{from}到{to}，总数 {total} 条', 
                striped: true,
                width : "auto",
                height : 700
            });
}


