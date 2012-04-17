package com.jshop.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

import com.jshop.action.tools.BaseTools;
import com.jshop.entity.TemplatethemeT;
import com.jshop.service.impl.TemplatethemeTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("jshop")

@Controller("initTAction")
public class InitTAction extends ActionSupport {
	@Resource(name="templatethemeTServiceImpl")
	private TemplatethemeTServiceImpl templatethemeTServiceImpl;
	private TemplatethemeT tt;
	private boolean slogin;
	private boolean sucflag;
	
	@JSON(serialize = false)
	public TemplatethemeTServiceImpl getTemplatethemeTServiceImpl() {
		return templatethemeTServiceImpl;
	}
	public void setTemplatethemeTServiceImpl(TemplatethemeTServiceImpl templatethemeTServiceImpl) {
		this.templatethemeTServiceImpl = templatethemeTServiceImpl;
	}
	public boolean isSlogin() {
		return slogin;
	}
	public void setSlogin(boolean slogin) {
		this.slogin = slogin;
	}
	public boolean isSucflag() {
		return sucflag;
	}
	public void setSucflag(boolean sucflag) {
		this.sucflag = sucflag;
	}
	
	
	public InitTAction() {
		tt=new TemplatethemeT();
	}
	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}

	/**
	 * 初始化后台所需要的数据
	 * @return
	 */
	public void InitDefaultThemeT(){
		String status="1";//标示默认主题
		tt=this.getTemplatethemeTServiceImpl().findTemplatethemeBystatus(status);
		if(tt!=null){
			//将启用的模板主题标示加入到服务器内存中
			if(!tt.getSign().isEmpty()){
				ActionContext.getContext().getApplication().put(BaseTools.DEFAULTTHEMESIGN, tt.getSign());
			}else{
				//如果没有默认的模板，那么启用默认主题模板
				tt.setSign("default");
				ActionContext.getContext().getApplication().put(BaseTools.DEFAULTTHEMESIGN, tt.getSign());
			}
			
		}
	}
	
	
	
	
}
