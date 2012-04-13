	cols[cols.length] = {col:
		{header:'<div class="hd_button_add" title="添加记录">&#160;</div>', id:'col_button_add', width:30, xtype:'actioncolumn', menuDisabled:true, align:'center', items:[{
				icon: 'resources/images/icons/fam/clear.gif',
				tooltip: jx.base.del,	
				handler: function(grid, rowIndex, colIndex) {
					grid.getSelectionModel().selectRow(rowIndex);
					grid.gridNode.event.editDelete();
				}
			}]
		}
	};
	
	config.initpage = function(gridNode){
		var grid = gridNode.page;
		grid.on('render', function(){
			Ext.fly(grid.getView().innerHd).on('mousedown', function(e, t){
			//添加表头添加事件
				if(e.button === 0 && t.className == 'hd_button_add'){
					e.stopEvent();
					grid.gridNode.event.editCreate();
				}
			}, grid);
        }, grid);
		
	};
	
	