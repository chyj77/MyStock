Ext.require([ 'Ext.tab.*', 'Ext.window.*', 'Ext.tip.*', 'Ext.tree.*','Ext.Msg.*','Ext.example.*',
		'Ext.data.*', 'Ext.layout.container.HBox', 'Ext.window.MessageBox',
		'Ext.layout.container.Border' ]);


Ext.define('Item', {
    extend: 'Ext.data.Model',
    fields: ['text', 'canDropOnFirst', 'canDropOnSecond']
});

Ext.onReady(function() {

	var treeMenu = Ext.create('Ext.tree.TreePanel',{
		id:'treeMenu',
		model : 'Item',
		title:'导航',
		width : '100%',
		height : '100%',
		frame: true,
        autoScroll: true,
        enableDD: false,
        containerScroll: true,
        draggable: false,
		rootVisible: false,
        collapsible: true,
        animate: true, 
		root : {
			text : 'Root ',
			expanded : true,
			children : [ {
				text : '我的股票',
				children : [ {
                    id:'20',
                    text : '持仓股票',
                    canDropOnFirst : true,
                    canDropOnSecond : true,
                    myhref:"#/ccgp/getAllAjax.htm" ,
                    leaf : true
                } ,{
					id:'l1',
					text : '涨停数据',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/ztsj/getAllAjax.htm" ,
					leaf : true
				}, {
					id:'l2',
					text : '2日试盘',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/cdcp2r/getAllAjax.htm" ,
					leaf : true
				} , {
					id:'l3',
					text : '实盘操作',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/spcz/getAllAjax.htm",
					leaf : true
				} , {
					id:'l4',
					text : '广发股票流水',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/gfStock/getAllAjax.htm" ,
					leaf : true
				}  ],
				expanded : true
			}, {
				text : '统计分析',
				children : [{
					id:'l5',
					text : '2日试盘统计分析',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/cdcp2r/getWeekAnay.htm",
					leaf : true
				}, {
					id:'l6',
					text : '涨停数据分析',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/ztsj/analytics.htm",
					leaf : true
				} , {
					id:'l7',
					text : '实盘股票分析',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/spcz/getStat.htm",
					leaf : true
				} , {
					id:'l8',
					text : '广发流水统计分析',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/gfStock/getGfstock.htm" ,
					leaf : true
				} , {
					id:'l9',
					text : '上海龙虎榜数据',
					canDropOnFirst : true,
					canDropOnSecond : true,
                    myhref:"#/shlhb/getAll.htm" ,
					leaf : true
				}],
				expanded : false
			} ]
		}
	});

	var mainTab = Ext.create('Ext.TabPanel', {
	    fullscreen: true,
	    id: "mainTab",  
	    renderTo:Ext.getBody(),
	    activeTab:0,  
	    maximizable:true,
//	    width:1000,
height:1500,
	    frame:true,  	   
	    tabPosition: 'top',
	    defaults: {  
	        autoScroll: true,  
	        style: "padding:0px"
	    },  
	    items:[  
	        {id:'tab20',title:"持仓股票", tabTip:"mormal",
	        	myhref:"#/ccgp/getAllAjax.htm" ,
	        	autoWidth:true,autoScroll:true, fullscreen: true }
	    ],  
	    enableTabScroll: true,
        listeners: {
            'tabchange': function (tab1, tab2,tab3) {
                location.hash= tab2.myhref+"?p="+Math.random();
            }
        }
    });
	
	var win = Ext.create('widget.window', {
		title : '我的股票',
		header : {
			titlePosition : 2,
			titleAlign : 'center'
		},
		closable : false,
		closeAction : 'hide',
		maximizable : false,
		maximized : true,
		// animateTarget: button,
		width : 800,
		minWidth : 350,
		height : 2000,
		tools : [ {
			type : 'pin'
		} ],
		layout : {
			type : 'border',
			padding : 5
		},
		items : [ {
			region : 'west',
			title : '',
			header : {
				titlePosition : 2,
				titleAlign : 'left',
				height:0
			},
			width : 200,
			split : true,
			collapsible : true,
			floatable : true,
			items:[treeMenu]
		}, {
			region: 'center',
autoScroll:true,
         //   xtype: 'tabpanel',
			items :[mainTab]
			
		} ]
	});
	win.show(this);
	treeMenu.addListener('itemclick', showTab);
    function showTab(node,e) {
    	var tabs = Ext.getCmp("mainTab");  
    	var subtab = Ext.getCmp("tab"+e.id);  
        if(subtab){
        	tabs.setActiveTab(subtab.getId());
        }else{
        	if(e.data.leaf){
	        	tabs.add({  
	                id: "tab" + e.id,  
	                fullscreen: true,
	                title:e.data.text ,
                    myhref:e.data.myhref,
	                autoWidth:true,
			autoScroll:true,
	                closable: true
                }).show();
                // location.hash=e.data.myhref+"?p="+Math.random() ;
        	}
        }
    }

});