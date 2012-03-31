/**
 * @author 侯非
 * @date 2009年3月11日
 * @class MenuFormPanel
 * @extends Ext.form.FormPanel
 * @description 数据字典表单
 */
MenuFormPanel = Ext.extend(Ext.form.FormPanel, {
			// 构造方法
			constructor : function(_config) {
				if (_config == null) {
					_config = {};
				}
				// 将自定义构造参数附加到当前对象
				Ext.apply(this, _config);
				// 拷贝父类的构造
				MenuFormPanel.superclass.constructor.call(this, {
							layout : "form",
							baseCls : "x-plain",
							labelWidth : 35,
							bodyStyle : "padding:10px;", // css填充10个像素
							items : [{
										xtype : "textfield",
										fieldLabel : "标题",
										anchor : "80%",
										allowBlank : false, // 不允许为空
										name : "title"
									}, {
										xtype : "textfield",
										fieldLabel : "菜单图标",
										name : "iconClass",
										allowBlank : true, // 允许为空
										anchor : "70%"
									}, {
										xtype : "textfield",
										fieldLabel : "链接",
										name : "url",
										allowBlank : false, // 不允许为空
										anchor : "99%"
									}, {
										xtype : "textfield",
										fieldLabel : "上级菜单",
										name : "parent",
										allowBlank : true, // 允许为空
										anchor : "90%"
									}, {
										xtype : "textfield",
										fieldLabel : "顺序",
										name : "order",
										anchor : "90%"
									}, {
										xtype : "textarea",
										fieldLabel : "描述",
										name : "description",
										allowBlank : true, // 允许为空
										anchor : "90%"
									}]
						})
			},

			/**
			 * 提交事件
			 * 
			 * @param {}
			 *            _url
			 */
			mySubmit : function(_url) {
				this.getForm().submit({
					url : _url,
					waitTitle : "数据传输",
					waitMsg : "数据传输中,请稍等...",
					success : this.onSuccess, // 成功回调函数
					failure : this.onFailure  // 失败回调函数
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
			onSuccess : function(_form, _action) {
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
			onFailure : function(_form, _action) {
				Ext.Msg.alert("系统消息", _action.result["errorMsg"]);
			}

		});