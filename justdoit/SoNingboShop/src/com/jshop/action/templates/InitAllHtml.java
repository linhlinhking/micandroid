package com.jshop.action.templates;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

import com.jshop.action.InitTAction;
import com.jshop.action.TemplateTAction;
import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.ContentTag;
import com.jshop.entity.TemplateT;
import com.jshop.service.impl.TemplateTServiceImpl;
import com.jshop.service.impl.TemplatesetTServiceImpl;
import com.jshop.service.impl.TemplatethemeTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.TemplateException;
@ParentPackage("json-default")
@Namespace("")
@Controller("initAllHtml")
public class InitAllHtml extends ActionSupport {
	@Resource(name="createHtml")
	private CreateHtml createHtml;
	@Resource(name="dataCollectionTAction")
	private DataCollectionTAction dataCollectionTAction;
	@Resource(name="initTAction")
	private InitTAction initTAction;
	private Map<String, Object> map;
	private String status;
	
	public InitAllHtml() {
		map = new HashMap<String, Object>();
	}

	@JSON(serialize = false)
	public InitTAction getInitTAction() {
		return initTAction;
	}
	public void setInitTAction(InitTAction initTAction) {
		this.initTAction = initTAction;
	}
	@JSON(serialize = false)
	public DataCollectionTAction getDataCollectionTAction() {
		return dataCollectionTAction;
	}
	public void setDataCollectionTAction(DataCollectionTAction dataCollectionTAction) {
		this.dataCollectionTAction = dataCollectionTAction;
	}
	
	@JSON(serialize = false)
	public CreateHtml getCreateHtml() {
		return createHtml;
	}

	public void setCreateHtml(CreateHtml createHtml) {
		this.createHtml = createHtml;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}

	/**
	 * 生成所有静态数据
	 * 
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
	@Action(value = "buildAllHtml", results = { 
			@Result(name = "json",type="json")
	})
	public String buildAllHtml() throws IOException, TemplateException, IllegalAccessException {
		//获取默认主题
		this.getInitTAction().InitDefaultThemeT();
		//创建ftl模板数据
		this.getCreateHtml().recreateTemplate();
		//获取根目录
		map.put("basePath",this.getDataCollectionTAction().getBasePath());
		//获取session
		map.put("Session", this.getDataCollectionTAction().getSession());
		//获取导航数据
		map.put("siteNavigationList", this.getDataCollectionTAction().findSiteNavigation());
		//获取商城基本数据
		map.put("jshopbasicinfo", this.getDataCollectionTAction().findJshopbasicInfo());
		//获取商品分类左侧主导航
		map.put("goodsCategoryTree", this.getDataCollectionTAction().findGoodsCategoryT());
		map.put("goodsCategoryTreeFirstCount", this.getDataCollectionTAction().getGoodsCategoryTreeFirstCount());
		//获取页脚分类数据
		map.put("footcategory", this.getDataCollectionTAction().findFooterCateogyrT());
		//获取页脚文章数据
		map.put("footerarticle",this.getDataCollectionTAction().findFooterArticle());
		//获取首页自定义区域
		this.getDataCollectionTAction().findEditarea(ContentTag.TEMPLATENAMEFORINDEX, "1", map);
		//生成邮件回调模板页面
		this.getCreateHtml().buildUseractivatescallback(map);
		//生成商品数据页面
		this.getCreateHtml().buildGoodsdetailsPage(map);
		//生成文章页面
		this.getCreateHtml().buildArticlesPage(map);
		//生成商品分类页面静态页
		this.getCreateHtml().buildGoodsCategoryPage(map);
		//生成登录页面
		this.getCreateHtml().buildLogin(map);
		//生成注册页面
		this.getCreateHtml().buildRegister(map);
		//生成首页
		this.getCreateHtml().buildIndex(map);
		this.setStatus("success");
		return "json";
	}
}
