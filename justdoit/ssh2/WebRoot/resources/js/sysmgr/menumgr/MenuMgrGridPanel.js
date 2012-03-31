/**
 * @author 侯非
 * @date 2009年3月11日
 * @class MenuMgrGridPanel
 * @extends Ext.grid.GridPanel
 * @description 菜单管理表格面板
 */
MenuMgrGridPanel = Ext.extend(Ext.grid.GridPanel, {

	myid : "defaultId",
	myAddWin : null,
	myEditWIn : null,

	// 构造方法
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}
		// 将_config附加到当前对象
		Ext.apply(this, _config);
		// 实例化添加数据字典信息窗体
		this.myAddWin = new AddMenuWindow();
		// 实例化编辑数据字典信息窗体
		

		// 数据存贮器
		this["store"] = new Ext.data.JsonStore({
					url : "sysmgr/MenuMgr/get?json=true",
					root : "rows",
					totalProperty : "totalCount",
					fields : ["id", "title", "icon", "url", "description", "parent", "order"]
				});

		// 将父类的构造拷贝到当前对象
		MenuMgrGridPanel.superclass.constructor.call(this, {
			id : this.myid, // 唯一id
			layout : "fit",
			title:"字典维护",
			stripeRows : true, // 交替行效果
			iconCls:"houfei-treeNodeLeafIcon",
			viewConfig : {
				forceFit : true
			},
			columns : [ // 复选框列
					new Ext.grid.CheckboxSelectionModel({
								// 头
								header : ""
							}), { // 列模板
						header : "编号",
						sortable : true, // 允许排序
						dataIndex : "id"
					}, {
						header : "标题",
						sortable : true, // 允许排序
						dataIndex : "title"
					}, {
						header : "菜单图标",
						sortable : false, // 允许排序
						dataIndex : "icon"
					}, {
						header : "链接",
						sortable : false, // 允许排序
						dataIndex : "url"
					}, {
						header : "描述",
						sortable : false, // 允许排序
						dataIndex : "description"
					}, {
						header : "上级菜单",
						sortable : true, // 允许排序
						dataIndex : "parent"
					}, {
						header : "顺序",
						sortable : false, // 允许排序
						dataIndex : "order"
					}],
			selModel : new Ext.grid.RowSelectionModel({
						// 单行选择模式
						singleSelect : true
					}),
			// 填充加载时间
			loadMask : {
				msg : "正在加载数据,请稍候......"
			},
			tbar : [{
						iconCls : "silk-add", // 图标样式
						text : "添加",
						tooltip : "添加一条新菜单",
						handler : this.onAddDD,
						scope : this
					}, "-", {
						text : "修改",
						iconCls : "silk-edit", // 图标样式
						tooltip : "选择一条菜单进行修改",
						handler : this.onEditDD,
						scope : this
					}, "-", {
						text : "删除",
						iconCls : "silk-delete", // 图标样式
						tooltip : "选择一条菜单进行删除",
						handler : this.onDeleteDD,
						scope : this
					}],
			bbar : new Ext.PagingToolbar({
				store : this["store"], // 数据存储器
				pageSize : 25, // 页大小
				displayMsg : "共<font color=\"green\"> {2} </font>条记录,当前页记录索引<font color=\"green\"> {0} - {1}</font>&nbsp;&nbsp;&nbsp;",
				displayInfo : true
			})

		});

		this.getStore().load({
					params : {
						start : 0,
						limit : 25
					}
				});
	},

	/**
	 * 添加事件
	 */
	onAddDD : function() {
		// 重置表单
		this.myAddWin.onReset();
		// 显示窗体
		this.myAddWin.show();
	},

	/**
	 * 修改事件
	 */
	onEditDD : function() {
		// 获取当前表格的行选择模板
		var _sm = this.getSelectionModel();
		// 如果用户选择了一条记录则进行修改
		if (_sm.getCount() > 0) {
			// 获取当前选择行的记录
			var _values = _sm.getSelected();
			// 新建一个记录
			var _newRecord = new Ext.data.Record({
						"hrmConfig.cfgItem" : _values.get("cfgItem"),
						"hrmConfig.cfgValue" : _values.get("cfgValue"),
						"hrmConfig.cfgMemo" : _values.get("cfgMemo")
					});
			// 显示窗体
			this.myEditWin.show();
			// 加载_newRecord到编辑窗体
			this.myEditWin.myForm.getForm().loadRecord(_newRecord);
			//将当前行的id传递给EditWindow
			this.myEditWin.myEditPkId=_values.get("cfgId");
		} else {
			Ext.Msg.show({
						title : "系统提示",
						msg : "请您选择一条您要修改的信息",
						buttons : Ext.Msg.OK,
						icon : Ext.Msg.INFO
					});
		}
	},
	/**
	 * 删除事件
	 */
	onDeleteDD : function() {
		this.submit({
			url : _url,
			waitTitle : "数据传输",
			waitMsg : "数据删除中,请稍等...",
			success : this.onSuccess, // 成功回调函数
			failure : this.onFailure, // 失败回调函数
			scope : this
		});
	},
	/**
	 * 成功回调函数
	 * 
	 * @param {}
	 *            _form
	 * @param {}
	 *            _action
	 */
	onDeleteSuccess : function(_form, _action) {
		// 调用当前组建的父容器的onCloseWinClick()事件
		this.ownerCt.onCancel();
		// 重新加载表格数据
		MenuMgrGridPanel.getStore().reload();
	},

	/**
	 * 失败回调函数
	 * 
	 * @param {}
	 *            _form
	 * @param {}
	 *            _action
	 */
	onDeleteFailure : function(_form, _action) {
		Ext.Msg.alert("系统消息", _action.result["errorMsg"]);
	}

});