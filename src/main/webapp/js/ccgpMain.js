Ext.require(['Ext.Msg.*', 'Ext.example.*']);
function onReady() {
    $j(".flexme4").flexigrid({
        url: path + "/ccgp/getAllAjax.htm",
        dataType: 'json',
        colModel: [{
            display: '日期',
            name: 'rq',
            width: 100,
            sortable: true,
            align: 'center'
        }, {
            display: '股票代码',
            name: 'code',
            width: 100,
            sortable: true,
            align: 'center'
        }, {
            display: '股票名称',
            name: 'name',
            width: 100,
            sortable: true,
            align: 'center'
        }, {
            display: '持仓价',
            name: 'buyprice',
            width: 100,
            sortable: true,
            align: 'center'
        }, {
            display: '最新价',
            name: 'nowprice',
            width: 100,
            sortable: true,
            process:processFormat1,
            align: 'center'
        }, {
            display: '持仓数量',
            name: 'sl',
            width: 100,
            sortable: true,
            align: 'center'
        }, {
            display: '盈亏金额',
            name: 'yke',
            width: 100,
            sortable: true,
            process:processFormat,
            align: 'center'
        }, {
            display: '涨跌率',
            name: 'zdl',
            width: 100,
            sortable: true,
            process:processFormat,
            align: 'center'
        }, {
            display: '持仓天数',
            name: 'ccday',
            width: 100,
            sortable: true,
            align: 'center'
        }],
        searchitems: [{
            display: '日期',
            name: 'rq'
        }, {
            display: '股票代码',
            name: 'code',
            isdefault: true
        }, {
            display: '股票名称',
            name: 'name'
        }],
        sortname: "rq",
        sortorder: "desc",
        usepager: false,
        title: '持仓股票',
        procmsg: '正在处理,请稍候 ...',
        useRp: true,
        rp: 25,
        showTableToggleBtn: true,
        pagestat: '显示记录从{from}到{to}，总数 {total} 条',
        striped: true,
        width: "auto",
        height: 700
    });
}
function processFormat(dom,status){
    var tc=$(dom).textContent;
    tc = tc.replace("%","");
    if(tc>0){
        $j(dom).css({'color': 'red'});
    }else {
        $j(dom).css({'color': 'green'});
    }
}
function processFormat1(dom,status){
    var tc=$(dom).textContent;
    data = new Array();
    var nowprices = $j('[abbr="nowprice"]').children('div').prevObject;
    // console.log($j(dom));
    nowprices.each(function(i) {
        if(tc===nowprices[i].textContent) {
            var buyprice = $j('[abbr="buyprice"]').children('div')[i].textContent;

            if (Number(tc) > Number(buyprice)) {
                $j(dom).css({'color': 'red'});
            } else {
                $j(dom).css({'color': 'green'});
            }
        }
    });
}

function initWs() {
    ws.binaryType = 'arraybuffer';
    ws.onmessage = function (event) {
        console.log("----onmessage----");
        if(event.data instanceof ArrayBuffer){
            var arrayBuffer = event.data;
            var byteBuffer = ByteBuffer.wrap(arrayBuffer);
            console.log(byteBuffer);
            var str = byteBuffer.readUTF8String(byteBuffer.limit);
            console.log(str);
        } else {
            // console.log("--------文本------"+event.data);
            var resultText = JSON.parse(event.data);
            var code2 = resultText.code;
            var codes = $j('[abbr="code"]').children('div');
            var data = new Array();
            codes.each(function(i) {
                var code = codes[i].textContent;
                if(code ===code2){
                    $j('[name="name"]').val(resultText.name);
                    $j('[name="buyprice"]').val(resultText.buyprice);
                    $j('[name="nowprice"]').val(resultText.nowprice);
                    $j('[name="sl"]').val(resultText.sl);
                    $j('[name="yke"]').val(resultText.yke);
                    $j('[name="zdl"]').val(resultText.zdl);
                    $j(".flexme4").flexReload();
                }
            });
        }
    };
    ws.onclose = function (event) {
        console.log("--------websocket close-----");
    };
    ws.onopen = function (event) {
        console.log("--------websocket open-----");
        var bb = new ByteBuffer().writeUTF8String("connect tio server").flip();
        var ab = bb.toArrayBuffer();
        console.log(ab);
        ws.send(ab);
    };
    ws.onerror = function (event) {
        console.log("--------websocket onerror-----");
    };
}
function send() {
    ws.send("hello world");
}



