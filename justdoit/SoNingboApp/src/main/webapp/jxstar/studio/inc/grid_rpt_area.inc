	
config.eventcfg = {

		showField: function(){
			var records = this.grid.getSelectionModel().getSelections();
			if (!JxUtil.selectone(records)) return;
			
			//过滤条件
			var where_sql = 'rpt_detail.area_id = ?';
			var where_type = 'string';
			var where_value = records[0].get('rpt_area__area_id');
			
			//加载数据
			var hdcall = function(grid) {
				grid.getBottomToolbar().hide();
				//显示数据
				JxUtil.delay(500, function(){
					//var grid = layout.getComponent(0).getComponent(0);
				
					//设置外键值
					grid.fkValue = where_value;
					Jxstar.loadData(grid, {where_sql:where_sql, where_value:where_value, where_type:where_type});
				});
			};

			//显示数据
			Jxstar.showData({
				filename: '/jxstar/studio/grid_rpt_detail.js',
				title: jx.rpt.ftitle,	//'报表字段定义'
				pagetype: 'subeditgrid',
				nodedefine: Jxstar.findNode('rpt_detail'),
				width: 400,
				height: 350,
				modal: false,
				callback: hdcall
			});
		}
	};
	
	config.initpage = function(gridNode){
		var grid = gridNode.page;
		grid.on('rowclick', function(g, rowindex, e) {
			var detGrid = Ext.getCmp('node_rpt_detail_subeditgrid');
			if (detGrid != null) {
				var record = g.getStore().getAt(rowindex);
				var areaId = record.get('rpt_area__area_id');
				
				detGrid.fkValue = areaId;
				var where_sql = 'rpt_detail.area_id = ?';
				var where_type = 'string';
				var where_value = areaId;
				Jxstar.loadData(detGrid, {where_sql:where_sql, where_value:where_value, where_type:where_type});
			}
		});
	};