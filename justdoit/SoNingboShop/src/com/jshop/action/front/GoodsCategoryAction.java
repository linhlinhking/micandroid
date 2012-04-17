package com.jshop.action.front;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;
import com.jshop.entity.ArticleCategoryT;
import com.jshop.entity.GoodsCategoryT;
import com.jshop.entity.GoodsT;
import com.jshop.entity.JshopbasicInfoT;
import com.jshop.entity.SiteNavigationT;
import com.jshop.service.impl.ArticleCategoryTServiceImpl;
import com.jshop.service.impl.ArticleTServiceImpl;
import com.jshop.service.impl.GoodsCategoryTServiceImpl;
import com.jshop.service.impl.GoodsTServiceImpl;
import com.jshop.service.impl.JshopbasicInfoTServiceImpl;
import com.jshop.service.impl.SiteNavigationTServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("jshop")
@Namespace("")
@InterceptorRefs({  
    @InterceptorRef("defaultStack")  
})
@Controller("goodsCategoryAction")
public class GoodsCategoryAction extends ActionSupport{
	@Resource(name="goodsTServiceImpl")
	private GoodsTServiceImpl goodsTServiceImpl;
	@Resource(name="siteNavigationTServiceImpl")
	private SiteNavigationTServiceImpl siteNavigationTServiceImpl;
	@Resource(name="goodsCategoryTServiceImpl")
	private GoodsCategoryTServiceImpl goodsCategoryTServiceImpl;
	@Resource(name="articleCategoryTServiceImpl")
	private ArticleCategoryTServiceImpl articleCategoryTServiceImpl;
	@Resource(name="articleTServiceImpl")
	private ArticleTServiceImpl articleTServiceImpl;
	@Resource(name="jshopbasicInfoTServiceImpl")
	private JshopbasicInfoTServiceImpl jshopbasicInfoTServiceImpl;
	private Map<String,Object>map=new HashMap<String,Object>();
	private List<GoodsT>goodsList;
	private String navid;
	private String ltypeid;
	private String stypeid;
	private String cp;
    private String ls;
   
    @JSON(serialize = false)
	public GoodsTServiceImpl getGoodsTServiceImpl() {
		return goodsTServiceImpl;
	}
	public void setGoodsTServiceImpl(GoodsTServiceImpl goodsTServiceImpl) {
		this.goodsTServiceImpl = goodsTServiceImpl;
	}
	@JSON(serialize = false)
	public SiteNavigationTServiceImpl getSiteNavigationTServiceImpl() {
		return siteNavigationTServiceImpl;
	}
	public void setSiteNavigationTServiceImpl(SiteNavigationTServiceImpl siteNavigationTServiceImpl) {
		this.siteNavigationTServiceImpl = siteNavigationTServiceImpl;
	}
	@JSON(serialize = false)
	public GoodsCategoryTServiceImpl getGoodsCategoryTServiceImpl() {
		return goodsCategoryTServiceImpl;
	}
	public void setGoodsCategoryTServiceImpl(GoodsCategoryTServiceImpl goodsCategoryTServiceImpl) {
		this.goodsCategoryTServiceImpl = goodsCategoryTServiceImpl;
	}
	@JSON(serialize = false)
	public ArticleCategoryTServiceImpl getArticleCategoryTServiceImpl() {
		return articleCategoryTServiceImpl;
	}
	public void setArticleCategoryTServiceImpl(ArticleCategoryTServiceImpl articleCategoryTServiceImpl) {
		this.articleCategoryTServiceImpl = articleCategoryTServiceImpl;
	}
	@JSON(serialize = false)
	public ArticleTServiceImpl getArticleTServiceImpl() {
		return articleTServiceImpl;
	}
	public void setArticleTServiceImpl(ArticleTServiceImpl articleTServiceImpl) {
		this.articleTServiceImpl = articleTServiceImpl;
	}
	@JSON(serialize = false)
	public JshopbasicInfoTServiceImpl getJshopbasicInfoTServiceImpl() {
		return jshopbasicInfoTServiceImpl;
	}
	public void setJshopbasicInfoTServiceImpl(JshopbasicInfoTServiceImpl jshopbasicInfoTServiceImpl) {
		this.jshopbasicInfoTServiceImpl = jshopbasicInfoTServiceImpl;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List<GoodsT> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<GoodsT> goodsList) {
		this.goodsList = goodsList;
	}
	public String getNavid() {
		return navid;
	}
	public void setNavid(String navid) {
		this.navid = navid;
	}
	public String getLtypeid() {
		return ltypeid;
	}
	public void setLtypeid(String ltypeid) {
		this.ltypeid = ltypeid;
	}
	public String getStypeid() {
		return stypeid;
	}
	public void setStypeid(String stypeid) {
		this.stypeid = stypeid;
	}

	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public String getLs() {
		return ls;
	}
	public void setLs(String ls) {
		this.ls = ls;
	}
	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages(); 
	
	}

	/**
	 * 收集生成静态页所需数据
	 */
	public void createGoodsCategory(){
		this.findIndexSiteNavigation();
		this.findGoodsCategoryT();
		this.findJshopbasicInfo();
		findFooterCateogyrT();

		
		
	}
	/**
	 * 获取商城基本信息
	 */
	public void findJshopbasicInfo(){
		String state="1";
		String openstate="1";
		JshopbasicInfoT bean=this.getJshopbasicInfoTServiceImpl().findJshopbasicInfoBystateandopstate(state, openstate);
		if(bean!=null){
			map.put("jshopbasicinfo", bean);
		}
		
	}
	/**
	 * 获取导航
	 */
	public void findIndexSiteNavigation(){
		String isVisible="1";
		List<SiteNavigationT>list=this.getSiteNavigationTServiceImpl().findSiteNavigationByisVisible(isVisible);
		if(list!=null){
			map.put("siteNavigationList", list);
		}
	}
	/**
	 * 读取左侧主导航
	 * @return
	 */
	public void findGoodsCategoryT(){
		String state="1";
		List<GoodsCategoryT>list=this.getGoodsCategoryTServiceImpl().findAllGoodsCategoryBycreatorid(state);
		if(list!=null){
			int gradecount=0;
			for(Iterator it=list.iterator();it.hasNext();){
				GoodsCategoryT gt=(GoodsCategoryT)it.next();
				if(gt.getGrade().equals("0")){
					gradecount++;
				}
			}
			map.put("goodsCategoryTree", list);
			map.put("goodsCategoryTreeFirstCount", gradecount);
		}
	}
	/**
	 * 获取页脚分类
	 */
	public void findFooterCateogyrT(){
		String position="1";
		String status="1";
		int lineSize=5;
		List<ArticleCategoryT>list=this.getArticleCategoryTServiceImpl().findArticleCategoryByposition(lineSize, status, position);
		if(list!=null){
			map.put("footcategory", list);
		}
	}


	
	

}
