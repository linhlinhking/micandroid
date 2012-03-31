/**
 * @author 侯非
 * @date 2009年3月12日
 * @class EditMenuWindow
 * @extends Ext.Window
 * @description 数据字典表单
 */
EditMenuWindow = Ext.extend(Ext.Window, {
			myForm : null,
			myEditPkId : "default",
			// 构造方法
			constructor : function() {
				this.myForm = new MenuFormPanel();
				// 拷贝父类的构造
				EditMenuWindow.superclass.constructor.call(this, {
							title : "修改菜单信息", // 标题
							iconCls : "silk-edit", // 添加字典信息
							width : 280, // 宽度
							closeAction : "hide", // 关闭模式为隐藏
							modal : true, // 模态窗体
							collapsible : true, // 伸缩按钮
							resizable : false, // 不允许改变窗体的大小
							plain : true, // 强制背景色
							items : [this.myForm],
							buttons : [{
										text : "确定修改",
										handler : this.onEdit, // 添加事件
										scope : this
									}, {
										text : "取消",
										handler : this.onCancel, // 取消事件
										scope : this
									}]
						});
			},

			/**
			 * 添加事件
			 */
			onEdit : function() {
				// 如果验证通过则提交
				if (this.myForm.getForm().isValid() == true) {
					var url = "sysmgr/MenuMgr/save&id="
							+ this.myEditPkId + "";
					// 提交
					this.myForm.mySubmit(url);
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