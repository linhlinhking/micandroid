/*!
 * Copyright 2008-2011 the original author or authors.
 * Licensed under the Apache License, Version 2.0
 */
 
/**
 * 缺省值函数对象。
 * 
 * @author TonyTan
 * @version 1.0, 2010-01-01
 */

JxDefault = {};
(function(){

	Ext.apply(JxDefault, {
		/**
		* 取当前日期, 格式：yyyy-mm-dd
		*/
		getToday: function(){
			return new Date();
		},
		
		/**
		* 取当前登录人ID
		*/
		getUserId: function(){
			return Jxstar.session['user_id'];
		},
		
		/**
		* 取当前登录人编码
		*/
		getUserCode: function(){
			return Jxstar.session['user_code'];
		},
		
		/**
		* 取当前登录人名
		*/
		getUserName: function(){
			return Jxstar.session['user_name'];
		},
		
		/**
		* 取当前登录人所在部门ID
		*/
		getDeptId: function(){
			return Jxstar.session['dept_id'];
		},
		
		/**
		* 取当前登录人所在部门编码
		*/
		getDeptCode: function(){
			return Jxstar.session['dept_code'];
		},
		
		/**
		* 取当前登录人所在部门名称
		*/
		getDeptName: function(){
			return Jxstar.session['dept_name'];
		},
		
		/**
		* 取当前登录人的角色ID
		*/
		getRoleId: function(){
			return Jxstar.session['role_id'];
		}
	});//Ext.apply

})();
