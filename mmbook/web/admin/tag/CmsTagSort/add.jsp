<%@ page contentType="text/html; charset=UTF-8" errorPage="../../../inc/errorpage.jsp"%>
<%@include file="../inc/param.jsp"%>
<%
String templetId_temp="";
 
String index_u_id = ("1".equals(v_id)) ? "1011106" : "1012106" ;
 %>
<html>
<head>
<title>新增类别标签页面</title>
<script type="text/javascript">
Ext.namespace("Ext.ux.cmstagsort");
Ext.ux.cmstagsort.add<%=u_id%>=Ext.extend(function(){
var panel<%=u_id%> = Ext.getCmp('center-tab-panel');

fun_settest=function(textfield,value){
		frm_add_cmstagsort<%=u_id%>.form.findField(textfield).setValue(value);
}
 
var getId_ = function(id){
     frm_add_cmstagsort<%=u_id%>.form.findField("categoryId").setValue(id);
};
/**模板ID*/
var templetId_temp = 'test';
/**模板下拉框 qiongguo **/
var cmsTempletStore = new Ext.data.SimpleStore({
			fields: ['id', 'mc'],
			proxy: new Ext.data.HttpProxy({
			    url: 'tag/CmsTemplet/getCmsTempletCombo.do?versionId=<%=v_id%>&webTempletType=20'
			})
});

fun_update_cms_templet=function(){
		frm_add_cmstagsort<%=u_id%>.form.findField(textfield).setValue(value);
}

var field_add_base<%=u_id%> = new Ext.form.FieldSet({
	title : '基础数据',
	id : 'field_add_base<%=u_id%>',
	autoHeight : true,
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [ 
	                         {
		                    	xtype:'textfield',
		                    	
		                        fieldLabel: '标签名称',
		                        name:'tagNameNew',
								invalidText : '标签名已经被注册!', 
		                        allowBlank : false,
		                        minLength: 2,
								regex:/^[\w\u4E00-\u9FFF]+$/,
		                        regexText:'输入中文、数字和英文',
								maxLength: 20,
		                        validationEvent : 'blur',
								validator : function(thisText) {
							     var isok =false;
	                       	     Ext.Ajax.request({
	                                    url : 'tag/CmsTagSort/repetitionText.do',   
	                                    method : 'post',   
	                                    sync : true,
	                                    params : {   
	                                        textvalue : thisText   
	                                    },
	                                     success : function(response,options){
							                  var res = Ext.util.JSON.decode(response.responseText); 
							                  if(res.success == true){  
							                     isok=true;
							                  }
							             }
	                                });
	                        		return true;
	                           }
		                        }
	                         
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [
		                    {
	                    	xtype:'textfield',
	                        fieldLabel: '标签说明',
	                        maxLength:66,
							maxLengthText:'长度应该小于66个字符',
	                        name:'tagExp'
	                        }
	                         
                    ]
	           }]
});

var comboBoxCheckTree = new Ext.ux.ComboBoxCheckTree({
		fieldLabel: '类别选择',
		width : 300,
		height : 150,
		hiddenName: 'cmstagsortid',
		tree : {
			xtype:'treepanel',
			height:100,
			checkModel: 'multiple',    //多选: 'multiple'(默认)单选: 'single'级联多选: 'cascade'(同时选父和子);'parentCascade'(选父);'childCascade'(选子)
			onlyLeafCheckable: false, 
			animate: true,             //设置为true以启用展开 收缩时的动画效果
			rootVisible: true,
			autoScroll:true,  
			loader: new Ext.tree.TreeLoader({dataUrl:'manage/SiteContentSort/getTreeCombox.do',   
	        baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
			root : new Ext.tree.AsyncTreeNode({id:'1',text:'类别列表'})
			},
		selectValueModel:'all' //all 多选, folder选父节点  leaf选子节点
				
});

comboBoxCheckTree.on('change', function(box, newvar, oldvar){
    frm_add_cmstagsort<%=u_id%>.form.findField('categoryId').setValue(comboBoxCheckTree.getValue()); 
});  

var field_add_dataTransfer<%=u_id%> = new Ext.form.FieldSet({
	title : '类别配置',
	autoHeight : true,
	id : 'field_add_dataTransfer<%=u_id%>',
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [
			                 {
	                    	xtype:'textfield',
	                        fieldLabel: '类别选择<br/>常用变量表示<a href=\'javascript:fun_settest(\"categoryId\",\"$catid\")\'><span style=\'vertical-align: bottom\'>$catid</span></a>',
	                        name:'categoryId',
	                        allowBlank : false,
	                        readOnly: true 
	                        ,selectOnFocus:false
							 
	                        }
	                     
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '88%'
					},
                	items: [
	                       comboBoxCheckTree  
                    ]
	           }
	           ]
});

var field_add_datatemplet<%=u_id%> = new Ext.form.FieldSet({
	title : '模板配置',
	autoHeight : true,
	id : 'field_add_datatemplet<%=u_id%>',
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [
			                 
						  {
								xtype:'combo',
							    fieldLabel : '<font color="red">*</font>模板选择',
							    hiddenName :'templetId',
								triggerAction : 'all',
								editable :false,
								allowBlank : false,
								valueField : 'id',
								displayField : 'mc',
								forceSelection : true,
								store : cmsTempletStore,
								loadingText: '正在加载...',
								emptyText : '请选择...',
								mode: 'remote',
								listeners:{"select":function(){//根据业务添加监听事件
								      templetId_temp = this.value;
								    }
						  }}
	                    ]
            	},{
	                columnWidth:.5,
	                layout: 'form',
	                defaults : {
						anchor : '60%'
					},
          buttonAlign : 'center',
          buttons: [{
                id : 'mybuttons',
  	            text: '修改选中的模板',
  	            handler:function(){
  	               fun_opnetab("update_CmsTemplet"+templetId_temp,"修改模板","tag/CmsTemplet/updateBaset.do?id="+templetId_temp,"edit");
  	             }
  	     }]
	   }]
});


var field_add_dataDisplay<%=u_id%> = new Ext.form.FieldSet({
	title : '数据显示方式',
	autoHeight : true,
	id : 'field_add_dataDisplay<%=u_id%>',
	layout: 'fit',
            layout:'column',
            items:[{
                     columnWidth:.5,
                     layout: 'form',
                     defaults : {
		   			 	anchor : '88%'
			         },
			         items: [{
	                    	xtype:'numberfield',
	                        fieldLabel: '行数',
	                        name:'rowNum',
	                        value : '10'
	                        } ,{
				        	    xtype:'hidden',
				        	    fieldLabel:'方案ID',
				        	    name:'versionId',
				        	    value:'<%=v_id%>'
			                },{
				        	    xtype:'hidden',
				        	    fieldLabel:'类别ID',
				        	    name:'cids' 
			                },{
				                xtype:'hidden',
					        	fieldLabel:'标签分类',
					        	name:'tagSort',
					        	value:'sort'
				        	}
	                    ]
            	} ]
});

var frm_add_cmstagsort<%=u_id%> = new Ext.FormPanel({
        layout: 'fit',
        labelAlign: 'left',
        frame:true,
        title: '新增',
        border : 'true', 
        margins : '8',
        region:'center',
        labelWidth : 120,
        autoScroll : true,
        items:[field_add_base<%=u_id%>,field_add_dataDisplay<%=u_id%>,field_add_dataTransfer<%=u_id%>,field_add_datatemplet<%=u_id%>],
        buttonAlign : 'center',
        buttons: [{
	            text:'保存',
	            xtype : 'easyButton',
	            handler:function(){// 保存操作
				if (frm_add_cmstagsort<%=u_id%>.form.isValid() == false){
	    		    return;
	  		    }else{
	  		       frm_add_cmstagsort<%=u_id%>.form.findField('cids').setValue(comboBoxCheckTree.getValue());
				}
				Ext.MessageBox.confirm('确认操作','确定保存类别标签页面?',function(btn){
  	            if (btn == 'yes'){
	  		    frm_add_cmstagsort<%=u_id%>.form.submit({
	   		    url:'tag/CmsTagCategory/save.do',
	   		    success:function(form,action){
	   		         Ext.Msg.show({title: '提示', msg: action.result.msg,icon: Ext.Msg.INFO,minWidth: 200,buttons: Ext.Msg.OK,fn:function(){
			          if (!(typeof(pag_cmstagsort<%=index_u_id%>) == "undefined")) {
			            pag_cmstagsort<%=index_u_id%>.doLoad(0);
			          }
			          //fun_copnetab('<%//=index_u_id%>','管理类别标签','admin/tag/CmsTagCategory/index.jsp?u=<%=index_u_id%>&v=<%=v_id%>','icon-nav-p1');
	   		          }});

	 		       },
	 		     scope:this,
	 		     failure:function(form,action){
	 		          Ext.Msg.show({title: '错误', msg: action.result.msg,icon: Ext.Msg.ERROR,minWidth: 200,buttons: Ext.Msg.OK});
	   		       }
	  		     })}
  	  		     },this);   
	   		    }
	     },{
  	            text:'关闭',
  	            style : 'margin-left: 8px',
  	            handler:function(){
  	              Ext.MessageBox.confirm('确认操作','确定关闭类别标签页面?',function(btn){
  	                if (btn == 'yes'){
  				       panel<%=u_id%>.remove(panel<%=u_id%>.getActiveTab());
  	   		        }
  	  		       },this);
  	  		    }
  	     }] 
});

function fun_field_fit() {
	Ext.getCmp("field_add_base<%=u_id%>").setWidth(Ext.get("field_add_base<%=u_id%>").getWidth() - 22);
    Ext.getCmp("field_add_dataTransfer<%=u_id%>").setWidth(Ext.get("field_add_base<%=u_id%>").getWidth());
	Ext.getCmp("field_add_datatemplet<%=u_id%>").setWidth(Ext.get("field_add_base<%=u_id%>").getWidth());
	Ext.getCmp("field_add_dataDisplay<%=u_id%>").setWidth(Ext.get("field_add_base<%=u_id%>").getWidth());
};

var panel_add_cmstagsort<%=u_id%> = new Ext.Panel({
		renderTo:'add_cmstagsortdiv<%=u_id%>',
		width:Ext.get("add_cmstagsortdiv<%=u_id%>").getWidth(),
		height:Ext.get("add_cmstagsortdiv<%=u_id%>").getHeight(),
		border : false,
		layout : 'border',
        items:[frm_add_cmstagsort<%=u_id%>]
});
fun_field_fit();
panel<%=u_id%>.on('resize',function(){
    if(Ext.get("add_cmstagsortdiv<%=u_id%>")){
        var p =panel<%=u_id%>.getActiveTab().getId();
        if(p!='add<%=u_id%>'){
             panel<%=u_id%>.setActiveTab('add<%=u_id%>');
             panel_add_cmstagsort<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
             panel_add_cmstagsort<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
             fun_field_fit();
             panel<%=u_id%>.setActiveTab(p);
        }else{
            panel_add_cmstagsort<%=u_id%>.setWidth(Ext.getCmp('center-tab-panel').getInnerWidth()-2);
            panel_add_cmstagsort<%=u_id%>.setHeight(Ext.getCmp('center-tab-panel').getInnerHeight()-2);
            fun_field_fit();
        }
    }
});

},Ext.util.Observable);
new Ext.ux.cmstagsort.add<%=u_id%>();
</script>
</head>
<body>
<div id="add_cmstagsortdiv<%=u_id%>" style="width:100%;height:100%;"></div>
</body>
</html>
