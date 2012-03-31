/**
 * @author 侯非
 * @date 2009年3月11日
 * @class AddMenuWindow
 * @extends Ext.Window
 * @description 数据字典表单
 */
AddMenuWindow = Ext.extend(Ext.Window, {
			myForm : null,
			// 构造方法
			constructor : function() {
				this.myForm = new MenuFormPanel();
				// 拷贝父类的构造
				AddMenuWindow.superclass.constructor.call(this, {
							title : "添加菜单", // 标题
							iconCls : "silk-add", // 添加字典信息
							width : 320, // 宽度
							closeAction : "hide", // 关闭模式为隐藏
							modal : true, // 模态窗体
							collapsible : true, // 伸缩按钮
							resizable : false, // 不允许改变窗体的大小
							plain : true, // 强制背景色
							items : [this.myForm],
							buttons : [{
										text : "添加",
										handler : this.onAdd, // 添加事件
										scope : this
									},{
										text : "取消",
										handler : this.onCancel, // 取消事件
										scope : this
									}]
						});
			},

			/**
			 * 添加事件
			 */
			onAdd : function() {
				// 如果验证通过则提交
				if (this.myForm.getForm().isValid() == true) {
					this.myForm
							.mySubmit(MenuMgrGridPanel.ADD_FORM_SUBMIT_URL);
				}
			},
			/**
			 * 取消按钮事件
			 */
			onCancel : function() {
				this.onReset; // 重置表单
				this.hide(); // 隐藏窗体
			},

			/**
			 * 重置事件
			 */
			onReset : function() {
				this.myForm.getForm().reset(); // 重置表单
			}
		});

/** 添加数据字典信息url */
AddMenuWindow.ADD_FORM_SUBMIT_URL = "sysmgr/MenuMgr/save";