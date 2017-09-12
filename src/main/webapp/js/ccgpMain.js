function onReady() {
    $j(document).ready(function () {
        $j("#jqGrid").jqGrid({
            url: path + "/ccgp/getAllAjax.htm",
            mtype: "POST",
            datatype: "json",
            colModel: [
                {
                    label: '日期',
                    name: 'rq',
                    width: 150,
                    align: "center"
                },
                {
                    label: '股票代码',
                    name: 'code',
                    width: 150,
                    align: "center"
                },
                {
                    label: '股票名称', name: 'name', width: 150,
                    align: "center"
                },
                {
                    label: '持仓价', name: 'buyprice', width: 150,
                    align: "center"
                },
                {
                    label: '最新价', name: 'nowprice', width: 150,
                    align: "center",
                    formatter: function (value, options, rData) {
                        var reValue;
                        if (Number(value) > rData['buyprice']) {
                            reValue = "<span style='color:red'>" + value + "</span>";
                        } else {
                            reValue = "<span style='color:#006400'>" + value + "</span>";
                        }
                        return reValue;
                    }
                },
                {
                    label: '持仓数量', name: 'sl', width: 150,
                    align: "center"
                },
                {
                    label: '盈亏金额',
                    name: 'yke',
                    width: 150,
                    align: "center",
                    formatter: function (value, options, rData) {
                        var reValue;
                        if (value > 0) {
                            reValue = "<span style='color:red'>" + value + "</span>";
                        } else {
                            reValue = "<span style='color:#006400'>" + value + "</span>";
                        }
                        return reValue;
                    }
                },
                {
                    label: '涨跌幅', name: 'zdl', width: 150,
                    align: "center",
                    formatter: function (value, options, rData) {
                        var reValue;
                        if (value > 0) {
                            reValue = "<span style='color:red'>" + value + "%</span>";
                        } else {
                            reValue = "<span style='color:#006400'>" + value + "%</span>";
                        }
                        return reValue;
                    }
                }
            ],
            viewrecords: false,
            autowidth: true,
            caption:"持仓股票",
//                loadonce: true,
            //autoheight: true,
            height: '100%',
            rowNum: -1,
            loadtext:"",
//                multiSort: true,
//             pager: "#jqGridPager",
            footerrow: false, // set a footer row
            userDataOnFooter: false,
            jsonReader: {
                repeatitems: false,
                cell: "",
                id: "0"
            }
        });
        $j("#jqGrid").jqGrid("navGrid", "#jqGridPager", {add: false, edit: false, del: false});
    });
}
function initWs() {
    ws.binaryType = 'arraybuffer';
    ws.onmessage = function (event) {
        console.log("----onmessage----");
        if (event.data instanceof ArrayBuffer) {
            var arrayBuffer = event.data;
            var byteBuffer = ByteBuffer.wrap(arrayBuffer);
            console.log(byteBuffer);
            var str = byteBuffer.readUTF8String(byteBuffer.limit);
            console.log(str);
        } else {
            // console.log("--------文本------"+event.data);
            var resultText = JSON.parse(event.data);
            var code2 = resultText.code;
            var codes = $j("#jqGrid").jqGrid('getRowData');
            var data = new Array();
            console.log(codes);
            codes.each(function (code1) {
                if (code1['code'] === code2) {
                    code1["name"]=(resultText.name);
                    code1["buyprice"]=(resultText.buyprice);
                    code1["nowprice"]=(resultText.nowprice);
                    code1["sl"]=(resultText.sl);
                    code1["yke"]=(resultText.yke);
                    code1["zdl"]=(resultText.zdl);
                    $j("#jqGrid").jqGrid('setGridParam',code1).trigger("reloadGrid");
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



