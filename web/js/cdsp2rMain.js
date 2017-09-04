Ext.require([ 'Ext.data.*', 'Ext.grid.*','Ext.Msg.*','Ext.example.*','Ext.Date.*','Ext.window.*' ]);

Ext.define('Cdcp2r', {
	extend : 'Ext.data.Model',
	fields : [ 'rq', 'gpdm', 'gpmc', 'mrjg', 'mcjg', 'ykje', 'zdl','recid','success' ]
});

Ext.onReady(function() {
	var store = Ext.create('Ext.data.Store', {
// autoLoad : true,
		autoSync : true,
		model : 'Cdcp2r',
		proxy : {
			type : 'rest',
			url : path + '/cdcp2r/getAllAjax.htm',
			reader : {
				type : 'json',
				rootProperty : 'data'
			},
			writer : {
				type : 'json'
			}
		},
		listeners : {
			write : function(store, operation) {
				var record = operation.getRecords()[0], name = Ext.String
						.capitalize(operation.action), verb;
				if (name == 'Destroy') {
					verb = 'Destroyed';
				} else {
					verb = name + 'd';
				}
				Ext.example.msg(name, Ext.String.format("{0} 股票: {0}", verb,
						record.getId()));

			}
		}
	});
store.load();
	var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		autoCancel: false,
        saveBtnText: '保存',
        cancelBtnText: '取消',
        errorsText: '错误',
        dirtyText: "你要确认或取消更改",
		listeners : {
			cancelEdit : function(rowEditing, context) {
				// Canceling editing of a locally added, unsaved record: remove
				// it
				if (!context.record.phantom) {
					store.remove(context.record);
				}
			},
			afterEdit : function(rowEditing, context) {
				// Canceling editing of a locally added, unsaved record: remove
				// it
				var r = context.record;
				r.set('rq',dateFormat(r.get('rq')));
				var recid=r.get('recid');
				
				var cdcp2r = Ext.create('Ext.data.Store', {
					autoLoad : false,
					autoSync : false,
					model : 'Cdcp2r',
					data:[{'recid':recid,'rq':r.get('rq'),
						'gpdm':r.get('gpdm'),
						'gpmc':r.get('gpmc'),
						'mrjg':r.get('mrjg'),
						'mcjg':r.get('mcjg')}],
					proxy : {
						type : 'rest',
						headers: {'Content-Type': "text/plain; charset=utf-8" },
						method:"POST",
						url : path + '/cdcp2r/doSave.htm',
						reader : {
							type : 'json',
							rootProperty : 'data',
							successProperty: 'success',
						    messageProperty: 'message'
						},
						writer : {
							type : 'json'
						},
		                listeners: {
		                    exception: function (own, request, operation, eOpts) {// 异常处理
		                    }
		                },
						successProperty: 'success' // 后台传输的标识。必须 }
				       }
					
				});			
				cdcp2r.sync({
					callback:function(bath){
						var resultText = Ext.decode(bath.operations["0"].request._callback.arguments[2].responseText);
						var flag = resultText["success"];
						var msg = resultText["message"];
						Ext.example.msg("提示",msg);
					}
				});
				
			}
		}
	});
	var grid = Ext.create('Ext.grid.Panel', {
		renderTo : document.body,
		plugins : [ rowEditing ],
		maximized : true,
		frame : true,
		title : '超短2日试盘',
		header : {
			titlePosition : 2,
			titleAlign : 'center'
		},
		store : store,
		iconCls : 'icon-user',
		columns : [ {
			text : 'id',
			width : 120,
			hidden:true,
			sortable : true,
			dataIndex : 'recid',
			field : {
				xtype : 'textfield'
			}
		}, {
			text : '日期',
			width : 120,
			sortable : true,
			dataIndex : 'rq',
			xtype : 'datecolumn',
			field : {
				xtype : 'datefield',
				format : '20' + 'y-m-d',
				maxValue : new Date()
			},
			renderer : function(v, meta, rec) {
				return rec.phantom ? '' : dateFormat(v);
			}
		}, {
			text : '股票代码',
			width : 120,
			sortable : true,
			dataIndex : 'gpdm',
			field : {
				maxLength:6,
				xtype : 'textfield'
			}
		}, {
			header : '股票名称',
			width : 120,
			sortable : true,
			dataIndex : 'gpmc',
			field : {
				xtype : 'textfield'
			}
		}, {
			text : '买入价格',
			width : 120,
			sortable : true,
			dataIndex : 'mrjg',
			field : {
				xtype : 'numberfield'
			}
		}, {
			text : '卖出价格',
			width : 120,
			sortable : true,
			dataIndex : 'mcjg',
			field : {
				xtype : 'numberfield'
			}
		}, {
			text : '盈亏金额',
			width : 120,
			sortable : true,
			dataIndex : 'ykje'
		}, {
			text : '涨跌率',
			width : 120,
			sortable : true,
			dataIndex : 'zdl'
		} 
		 ],
		dockedItems : [ {
			xtype : 'toolbar',
			items : [
					{
						itemId : 'add',
						text : '增加',
						iconCls : 'icon-add',
						handler : function() {
							// empty record
							var cdcp2r = new Cdcp2r();
							cdcp2r.set('recid','');
							cdcp2r.set('rq','');
							cdcp2r.set('gpdm','');
							cdcp2r.set('gpmc','');
							cdcp2r.set('mrjg','');
							cdcp2r.set('mcjg','');
							cdcp2r.set('ykje','');
							cdcp2r.set('zdl','');
							store.insert(0, cdcp2r);
							rowEditing.startEdit(0, 0);
						}
					},
					'-',
					{
						itemId : 'delete',
						text : '删除',
						iconCls : 'icon-delete',
						disabled : true,
						handler : function() {
							Ext.MessageBox.confirm("提示","确认删除一条记录？",function(fn){
								if(fn=="no") return;
								else{
									var selection = grid.getView().getSelectionModel()
									.getSelection()[0];
							if (selection) {
								var cdcp2r = Ext.create('Ext.data.Store', {
									// autoLoad : true,
									// autoSync : true,
										model : 'Cdcp2r',
										data:[{'recid':selection.get('recid')}],
										proxy : {
											type : 'rest',
											headers: {'Content-Type': "text/plain; charset=utf-8" },
											url : path + '/cdcp2r/doDel.htm',
											reader : {
												type : 'json',
												rootProperty : 'data'
											},
											writer : {
												type : 'json'
											}
										}
									});			
								cdcp2r.sync({
									callback:function(bath){
										var resultText = Ext.decode(bath.operations["0"].request._callback.arguments[2].responseText);
										var flag = resultText["success"];
										var msg = resultText["message"];
										Ext.example.msg("提示",msg);
									}
								});
								store.remove(selection);
							}
								}
							});
							
						}
					},
					'-',
					{
						itemId : 'refresh',
						text : '刷新',
						iconCls : 'icon-refresh',
						handler : function() {
							store.load();
						}
					}
					]
		} ]
	});
	grid.getSelectionModel().on('selectionchange',
			function(selModel, selections) {
				grid.down('#delete').setDisabled(selections.length === 0);
			});
	// 格式化日期格式
	function dateFormat(value) {
		if (null != value) {
			return Ext.Date.format(new Date(value), 'Y-m-d');
		} else {
			return null;
		}
	}
});