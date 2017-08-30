Ext.require([ 'Ext.tab.*', 'Ext.window.*', 'Ext.tip.*', 'Ext.tree.*',
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
		width : 300,
		height : 800,
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
					id:'l1',
					text : '涨停数据',
					canDropOnFirst : true,
					canDropOnSecond : true,
					leaf : true
				}, {
					id:'l2',
					text : '2日试盘',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/cdcp2r/getAll.htm marginheight='0' marginwidth='0'width='100%' height='1000'  frameborder='0'></iframe>",
					leaf : true
				} , {
					id:'l3',
					text : '实盘操作',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/spcz/getAll.htm marginheight='0' marginwidth='0'width='100%' height='1000'  frameborder='0'></iframe>",
					leaf : true
				} , , {
					id:'l10',
					text : '持仓股票',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/ccgp/getAll.htm marginheight='0' marginwidth='0'width='100%' height='1000'  frameborder='0'></iframe>",
					leaf : true
				} ,{
					id:'l4',
					text : '广发股票流水',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/gfStock/getAll.htm marginheight='0' marginwidth='0'width='100%' height='1000'  frameborder='0'></iframe>",
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
					html:"<iframe src="+path+"/cdcp2r/getWeek.htm marginheight='0' marginwidth='0' width='100%' height='100%' scrolling='auto'  frameborder='0'></iframe>",
					leaf : true
				}, {
					id:'l6',
					text : '涨停数据分析',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/ztsj/analytics.htm id='ztsjfxfrm' marginheight='0' marginwidth='0' width='100%' height='100%' scrolling='auto'  frameborder='0'></iframe>",
					leaf : true
				} , {
					id:'l7',
					text : '实盘股票分析',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/spcz/getStat.htm  marginheight='0' marginwidth='0' width='100%' height='100%' scrolling='auto'  frameborder='0'></iframe>",
					leaf : true
				} , {
					id:'l8',
					text : '广发流水统计分析',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/gfStock/getGfstock.htm  marginheight='0' marginwidth='0' width='100%' height='100%' scrolling='auto'  frameborder='0'></iframe>",
					leaf : true
				} , {
					id:'l9',
					text : '上海龙虎榜数据',
					canDropOnFirst : true,
					canDropOnSecond : true,
					html:"<iframe src="+path+"/shlhb/getAll.htm  marginheight='0' marginwidth='0' width='100%' height='100%' scrolling='auto'  frameborder='0'></iframe>",
					leaf : true
				}],
				expanded : false
			} ]
		}
	});

	var mainTab = Ext.create('Ext.TabPanel', {
	    fullscreen: true,
	    id: "mainTab",  
//	    renderTo:Ext.getBody(),
	    activeTab: 0,  
	    maximizable : false,
//	    width:1000,
	    height:900,
	    frame:true,  	   
	    tabPosition: 'top',
	    defaults: {  
	        autoScroll: true,  
	        autoHeight:true,  
	        style: "padding:5"  
	    },  
	    items:[  
	        {id:'tabl1',title:"涨停数据", tabTip:"mormal", 
	        	html:"<iframe src="+path+"/ztsj/getAll.htm marginheight='0' marginwidth='0'width='100%' height='1000'  frameborder='0'></iframe>",
	        	autoWidth:true,autoHeight:true,fullscreen: true }
	    ],  
	    enableTabScroll: true  
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
		height : 400,
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
	                html:e.data.html,
	                autoWidth:true,
	                autoHeight:true,
	                closable: true  
	            }).show(); 
        	}
        }
    }
    
});