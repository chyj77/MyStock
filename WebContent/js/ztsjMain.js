Ext.require([ 'Ext.Msg.*','Ext.example.*']);
function onReady(){
	$j(".flexme4").flexigrid({
                url : path+"/ztsj/getAllAjax.htm",
                dataType : 'json',
                colModel : [ {
                    display : '日期',
                    name : 'rq',
                    width : 100,
                    sortable : true,
                    align : 'center'
                    }, {
                        display : '每日涨停数量',
                        name : 'mrztgs',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '非一字板个数',
                        name : 'fyzbgs',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '10点前涨停个数',
                        name : 'dqztgs',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '涨停最多概念',
                        name : 'ztzdgn',
                        width : 120,
                        sortable : true,
                        align : 'center'
                    } ,{
                        display : '涨停最多个数',
                        name : 'ztzdgs',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '打板次日高开率',
                        name : 'dbcrgkl',
                        width : 100,
                        sortable : true,
                        align : 'center',
                        process:processFormat
                    }, {
                        display : '收盘成功率',
                        name : 'spcgl',
                        width : 80,
                        sortable : true,
                        align : 'center',
                        process:processFormat
                    }, {
                        display : '10点前涨停高开率',
                        name : 'dqztgkl',
                        width : 100,
                        sortable : true,
                        align : 'center',
                        process:processFormat
                    }, {
                        display : '被砸数量',
                        name : 'bzsl',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '被砸率',
                        name : 'bzl',
                        width : 80,
                        sortable : true,
                        align : 'center',
                        process:processFormat
                    }, {
                        name : 'recid',
                        idProperty:'recid',
                        hide: true,
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }],
                buttons : [ {
                    name : '新增',
                    bclass : 'add',
                    onpress : Example4
                    }
                    ,
                    {
                        name : '编辑',
                        bclass : 'edit',
                        onpress : Example4
                    }
                    ,
                    {
                        name : '删除',
                        bclass : 'delete',
                        onpress : Example4
                    }
                    ,
                    {
                        separator : true
                    } 
                ],
                searchitems : [ {
                    display : '日期',
                    name : 'rq'
                    }, {
                        display : '涨停最多概念',
                        name : 'ztzdgn',
                        isdefault : true
                } ],
                sortname : "rq",
                sortorder : "desc",
                usepager : true,
                title : '涨停数据',
                useRp : true,
                rp : 25,
                showTableToggleBtn : true,
                width : "auto",
                pagestat: '显示记录从{from}到{to}，总数 {total} 条', 
                striped: true,
                height : 700
            });
}
            function Example4(com, grid) {
                if (com == '删除') {
                    var conf = confirm('确认删除 ' + $j('.trSelected', grid).length + ' 条记录?')
                    if(conf){
                    	var recids = new Array();
                        $j.each($j('.trSelected', grid),
                            function(key, value){                        		
                        		var recid = value.lastChild.innerText;
                        		recid = recid.replace("\n","");                        		
                        		var ztsj = JSON.stringify({"recid":recid});  
                        		recids.push(ztsj);
                        });    
                        $j.post(path+"/ztsj/doDel.htm", JSON.stringify(recids)
                        		, function(result){
                        	// when ajax returns (callback), update the grid to refresh the data
                        	var resultText = JSON.parse(result);
                        	Ext.example.msg("提示",resultText.message);
                        	$j(".flexme4").flexReload();
                        });
                    }
                }
                else if (com == '编辑') {
                	  if($j('.trSelected', grid).length>1 || ($j('.trSelected', grid).length==0)){
                		  alert("请选择一条记录！");
                		  return false;
                	  }
                    var conf = confirm('编辑 ' + $j('.trSelected', grid).length + ' 条记录?')
                    if(conf){
                        $j.each($j('.trSelected', grid),
                            function(key, value){
                                // collect the data
                        	var recid=value.lastChild.innerText;
                    		recid = recid.replace("\n","");    
//                                var OrgEmpID = value.children[0].innerText; // in case we're changing the key
//                                var EmpID = prompt("Please enter the New Employee ID",value.children[0].innerText);
//                                var Name = prompt("Please enter the Employee Name",value.children[1].innerText);
//                                var PrimaryLanguage = prompt("Please enter the Employee's Primary Language",value.children[2].innerText);
//                                var FavoriteColor = prompt("Please enter the Employee's Favorite Color",value.children[3].innerText);
//                                var FavoriteAnimal = prompt("Please enter the Employee's Favorite Animal",value.children[4].innerText);
                        	Modalbox.show(path+"/ztsj/modify.htm", 
                        			{title: "涨停数据编辑", 
                        			height: 300,
                        			width:700,
                        			afterLoad:callback
                        		}); 
                        	function callback(){
                        		var json = JSON.stringify({"recid":recid}); 
                        		$j.post(path+"/ztsj/toEdit.htm", json
                        				  , function(result){
                        				      // when ajax returns (callback), update the grid to refresh the data
                        						var resultText = JSON.parse(result);
                        						$j("#datepicker").val(resultText.rq);
                        						$j('[name="mrztgs"]').val(resultText.mrztgs);
                        						$j('[name="fyzbgs"]').val(resultText.fyzbgs);
                        						$j('[name="dqztgs"]').val(resultText.dqztgs);
                        						$j('[name="dqztgkl"]').val(resultText.dqztgkl);
                        						$j('[name="ztzdgn"]').val(resultText.ztzdgn);
                        						$j('[name="ztzdgs"]').val(resultText.ztzdgs);
                        						$j('[name="dbcrgkl"]').val(resultText.dbcrgkl);
                        						$j('[name="spcgl"]').val(resultText.spcgl);
                        						$j('[name="bzsl"]').val(resultText.bzsl);
                        						$j('[name="bzl"]').val(resultText.bzl);
                        						$j('[name="recid"]').val(resultText.recid);
                        				});
                        		}
//                                // call the ajax to save the data to the session
//                                $j.post('example4.php', 
//                                    { Edit: true
//                                        , OrgEmpID: OrgEmpID
//                                        , EmpID: EmpID
//                                        , Name: Name
//                                        , PrimaryLanguage: PrimaryLanguage
//                                        , FavoriteColor: FavoriteColor
//                                        , FavoritePet: FavoriteAnimal  }
//                                    , function(){
//                                        // when ajax returns (callback), update the grid to refresh the data
//                                        $j(".flexme4").flexReload();
//                               });
                        });    
                    }
                }
                else if (com == '新增') {
                	Modalbox.show(path+"/ztsj/modify.htm", 
                			{title: "涨停数据编辑", 
                			height: 300,
                			width:700
                		}); 
//                	$(document).on('click','.add', function(e) {
//                		e.preventDefault();
//                		var modalLocation = "myModal";
//                		$('#'+modalLocation).reveal($(this).data());
//                	});
//                    $j.get('example4.php', { Add: true, EmpID: EmpID, Name: Name, PrimaryLanguage: PrimaryLanguage, FavoriteColor: FavoriteColor, FavoritePet: FavoriteAnimal  }
//                        , function(){
//                            // when ajax returns (callback), update the grid to refresh the data
//                            $j(".flexme4").flexReload();
//                    });
                }
            }
function doClose(){
	$j("#ui-datepicker-div")["0"].style.display='none';
	Modalbox.hide();
}
function doSave(){
	var ztsj ={};
	ztsj.rq = $j("#datepicker").val();
	ztsj.mrztgs = $j('[name="mrztgs"]').val();
	ztsj.fyzbgs = $j('[name="fyzbgs"]').val();
	ztsj.dqztgs = $j('[name="dqztgs"]').val();
	ztsj.dqztgkl = $j('[name="dqztgkl"]').val();
	ztsj.ztzdgn = $j('[name="ztzdgn"]').val();
	ztsj.ztzdgs = $j('[name="ztzdgs"]').val();
	ztsj.dbcrgkl = $j('[name="dbcrgkl"]').val();
	ztsj.spcgl = $j('[name="spcgl"]').val();
	ztsj.bzsl = $j('[name="bzsl"]').val();
	ztsj.bzl = $j('[name="bzl"]').val();
	ztsj.recid = $j('[name="recid"]').val();
	var json = JSON.stringify(ztsj);  
	$j.post(path+"/ztsj/doSave.htm", json
  , function(result){
      // when ajax returns (callback), update the grid to refresh the data
		var resultText = JSON.parse(result);
		Ext.example.msg("提示",resultText.message);
		Modalbox.hide();
		$j("#ui-datepicker-div")["0"].style.display='none';
      $j(".flexme4").flexReload();
});
}
function parseDouble(val){
	if(val.value>1) 
	return val.value= val.value/100;
}
function processFormat(dom,status){
	var tc=$(dom).textContent; 
	$(dom).textContent=tc+"%";
}