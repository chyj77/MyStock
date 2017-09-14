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
            caption: "持仓股票",
//                loadonce: true,
            //autoheight: true,
            height: '100%',
            rowNum: -1,
            loadui: 'disable',
//                multiSort: true,
//             pager: "#jqGridPager",
            footerrow: false, // set a footer row
            userDataOnFooter: false,
            loadComplete:minchart,
            jsonReader: {
                repeatitems: false,
                cell: "",
                id: "0"
            }
        });
        $j("#jqGrid").jqGrid("navGrid", "#jqGridPager", {add: false, edit: false, del: false});
    });
    $j("#now").html(new Date().Format("yyyy年MM月dd日 hh:mm:ss"));
}

function initWs() {
    ws.binaryType = 'arraybuffer';
    ws.onmessage = function (event) {
        console.log("----onmessage----");
        $j("#now").html(new Date().Format("yyyy年MM月dd日 hh:mm:ss"));
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
            var ids = $j("#jqGrid").jqGrid('getDataIDs');
            var i=0;
            var data = new Array();
            // console.log(code2);
            codes.each(function (code1) {
                if (code1['code'] === code2) {
                    // code1["name"] = (resultText.name);
                    // code1["buyprice"] = (resultText.buyprice);
                    // code1["nowprice"] = (resultText.nowprice);
                    // code1["sl"] = (resultText.sl);
                    // code1["yke"] = (resultText.yke);
                    // code1["zdl"] = (resultText.zdl);
                    var datarow = {code:code2,name:resultText.name,buyprice:resultText.buyprice,nowprice:resultText.nowprice,sl:resultText.sl,tax:"30.00",yke:resultText.yke,zdl:resultText.zdl};
                    // console.log(ids[i]);
                    $j("#jqGrid").jqGrid('setRowData',ids[i],datarow);
                }
                i++;
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
function minchart(par1) {
    var total = par1.total;
    var rows = par1.rows;
    for(var i=0;i<total;i++){
        var chartUrl = "http://image.sinajs.cn/newchart/min/n/$code$.gif";
        var rowdata = rows[i];
        var code = '';
        if (rowdata['code'].startsWith("00") || rowdata['code'].startsWith("30")) {
            code = "sz"+rowdata['code'];
        } else {
            code = "sh"+rowdata['code'];
        }
        chartUrl = chartUrl.replace("$code$",code);
        var objNewDiv = $j('<div>',{'id':'div_'+code,'style':'float:left;padding: 0px;margin: 0px; width: auto'});
        var image=$j("<image id='chart_"+code+"' class='imgMinChart' src="+chartUrl+"/>");
        objNewDiv.html(image);
        console.log(objNewDiv);
        $j("#jqGridChart").append(objNewDiv);
    }
}
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
window.setInterval(updateChart,1000*60*2);
function  updateChart() {
    var imgMinCharts = $j(".imgMinChart");
    for(var i=0;i<imgMinCharts.length;i++){
        var imgMinChart = imgMinCharts[i];
        var path = imgMinChart.src+"?p="+Math.random();
        $j(imgMinChart).attr('src',path);
    }
}

