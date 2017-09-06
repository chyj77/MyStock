Ext.require([ 'Ext.Msg.*','Ext.example.*']);
function onReady(){
	$j(".flexme4").flexigrid({
                url : path+"/spcz/getAllAjax.htm",
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
                        display : '操作',
                        name : 'caozuo',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '买卖价格',
                        name : 'jiage',
                        width : 120,
                        sortable : true,
                        align : 'center'
                } ,{
                        display : '买卖数量',
                        name : 'sl',
                        width : 80,
                        sortable : true,
                        align : 'center'
                    }, {
                        display : '买卖逻辑',
                        name : 'luoji',
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
                title : '实盘操作',
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
            function Example4(com, grid) {
                if (com == '删除') {
                    var conf = confirm('确认删除 ' + $j('.trSelected', grid).length + ' 条记录?')
                    if(conf){
                    	var recids = new Array();
                        $j.each($j('.trSelected', grid),
                            function(key, value){                        		
                        		var recid = value.lastChild.innerText;
                        		recid = recid.replace("\n","");                        		
                        		var spcz = JSON.stringify({"recid":recid});  
                        		recids.push(spcz);
                        });    
                        $j.post(path+"/spcz/doDel.htm", JSON.stringify(recids)
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
                        	Modalbox.show(path+"/spcz/modify.htm", 
                        			{title: "实盘操作编辑", 
                        			height: 300,
                        			width:700,
                        			afterLoad:callback
                        		}); 
                        	function callback(){
                        		var json = JSON.stringify({"recid":recid}); 
                        		$j.post(path+"/spcz/toEdit.htm", json
                        				  , function(result){
                        				      // when ajax returns (callback), update the grid to refresh the data
                        						var resultText = JSON.parse(result);
                        						$j("#datepicker").val(resultText.rq);
                        						$j('[name="code"]').val(resultText.code);
                        						$j('[name="name"]').val(resultText.name);
                        						//$j('[name="caozuo"]').val(resultText.caozuo);
                        						$j("input:radio[value='"+resultText.caozuo+"']").attr('checked','true');
                        						$j('[name="jiage"]').val(resultText.jiage);
                        						$j('[name="sl"]').val(resultText.sl);
                        						$j('[name="luoji"]').val(resultText.luoji);
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
                	Modalbox.show(path+"/spcz/modify.htm", 
                			{title: "实盘操作编辑", 
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
	var cz = $j("input[name='caozuo']:checked").val();
	var jg = $j('[name="jiage"]').val();
	var sl = $j('[name="sl"]').val();
	var spcz ={};
	spcz.rq = $j("#datepicker").val();
	spcz.code = $j('[name="code"]').val();
	spcz.name = $j('[name="name"]').val();
	spcz.caozuo = cz;//$j('[name="caozuo"]').val();
	if(cz==1){
		jg=-jg;
		sl=-sl;
	}
	spcz.jiage = jg;
	spcz.sl = sl
	spcz.luoji = $j('[name="luoji"]').val();
	spcz.recid = $j('[name="recid"]').val();
	var json = JSON.stringify(spcz);  
	$j.post(path+"/spcz/doSave.htm", json
  , function(result){
      // when ajax returns (callback), update the grid to refresh the data
		var resultText = JSON.parse(result);
		Ext.example.msg("提示",resultText.message);
		Modalbox.hide();
		$j("#ui-datepicker-div")["0"].style.display='none';
      $j(".flexme4").flexReload();
});
}
