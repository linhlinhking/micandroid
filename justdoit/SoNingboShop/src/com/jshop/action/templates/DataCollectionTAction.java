package com.jshop.action.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;
import com.jshop.action.tools.BaseTools;
import com.jshop.entity.ArticleCategoryT;
import com.jshop.entity.ArticleT;
import com.jshop.entity.GoodsCategoryT;
import com.jshop.entity.GoodsT;
import com.jshop.entity.JshopbasicInfoT;
import com.jshop.entity.PageEditareaT;
import com.jshop.entity.SiteNavigationT;
import com.jshop.service.impl.ArticleCategoryTServiceImpl;
import com.jshop.service.impl.ArticleTServiceImpl;
import com.jshop.service.impl.GoodsCategoryTServiceImpl;
import com.jshop.service.impl.GoodsTServiceImpl;
import com.jshop.service.impl.JshopbasicInfoTServiceImpl;
import com.jshop.service.impl.PageEditareaTServiceImpl;
import com.jshop.service.impl.SiteNavigationTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.emory.mathcs.backport.java.util.Collections;
/**
 * 数据收集
 * 收集所有模板页面需要的数据
 * @author cd
 *
 */

@ParentPackage("json-default")
@Namespace("")
@Controller("dataCollectionTAction")
public class DataCollectionTAction extends ActionSupport {
	
	@Resource(name="jshopbasicInfoTServiceImpl")
	private JshopbasicInfoTServiceImpl jshopbasicInfoTServiceImpl;
	@Resource(name="siteNavigationTServiceImpl")
	private SiteNavigationTServiceImpl siteNavigationTServiceImpl;
	@Resource(name="pageEditareaTServiceImpl")
	private PageEditareaTServiceImpl pageEditareaTServiceImpl;
	@Resource(name="goodsCategoryTServiceImpl")
	private GoodsCategoryTServiceImpl goodsCategoryTServiceImpl;
	private int gradecount;
	@Resource(name="goodsTServiceImpl")
	private GoodsTServiceImpl goodsTServiceImpl;
	@Resource(name="articleTServiceImpl")
	private ArticleTServiceImpl articleTServiceImpl;
	@Resource(name="articleCategoryTServiceImpl")
	private ArticleCategoryTServiceImpl articleCategoryTServiceImpl;
	
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
	public GoodsTServiceImpl getGoodsTServiceImpl() {
		return goodsTServiceImpl;
	}

	public void setGoodsTServiceImpl(GoodsTServiceImpl goodsTServiceImpl) {
		this.goodsTServiceImpl = goodsTServiceImpl;
	}

	@JSON(serialize = false)
	public GoodsCategoryTServiceImpl getGoodsCategoryTServiceImpl() {
		return goodsCategoryTServiceImpl;
	}

	public void setGoodsCategoryTServiceImpl(GoodsCategoryTServiceImpl goodsCategoryTServiceImpl) {
		this.goodsCategoryTServiceImpl = goodsCategoryTServiceImpl;
	}
	@JSON(serialize = false)
	public JshopbasicInfoTServiceImpl getJshopbasicInfoTServiceImpl() {
		return jshopbasicInfoTServiceImpl;
	}

	public void setJshopbasicInfoTServiceImpl(JshopbasicInfoTServiceImpl jshopbasicInfoTServiceImpl) {
		this.jshopbasicInfoTServiceImpl = jshopbasicInfoTServiceImpl;
	}

	@JSON(serialize = false)
	public SiteNavigationTServiceImpl getSiteNavigationTServiceImpl() {
		return siteNavigationTServiceImpl;
	}

	public void setSiteNavigationTServiceImpl(SiteNavigationTServiceImpl siteNavigationTServiceImpl) {
		this.siteNavigationTServiceImpl = siteNavigationTServiceImpl;
	}
	@JSON(serialize = false)
	public PageEditareaTServiceImpl getPageEditareaTServiceImpl() {
		return pageEditareaTServiceImpl;
	}

	public void setPageEditareaTServiceImpl(PageEditareaTServiceImpl pageEditareaTServiceImpl) {
		this.pageEditareaTServiceImpl = pageEditareaTServiceImpl;
	}
	public int getGradecount() {
		return gradecount;
	}

	public void setGradecount(int gradecount) {
		this.gradecount = gradecount;
	}

	/**
	 * 获取网站根目录
	 * @return
	 */
	public String getBasePath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath =  path + "/";
		return basePath;
	}
	
	/**
	 * 获取session数据
	 * @return
	 */
	public Map<String,Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	
	/**
	 * 获取商城基本信息
	 */
	public JshopbasicInfoT findJshopbasicInfo() {
		String state = "1";//商城状态标记
		String openstate = "1";//商城开启运作标记
		JshopbasicInfoT bean = this.getJshopbasicInfoTServiceImpl().findJshopbasicInfoBystateandopstate(state, openstate);
		if (bean != null) {
			return bean;
		} else {
			return (JshopbasicInfoT) Collections.emptyList().get(0);
		}
	}
	
	/**
	 * 获取导航
	 */
	@SuppressWarnings("unchecked")
	public List<SiteNavigationT> findSiteNavigation() {
		String isVisible = "1";//显示标记
		List<SiteNavigationT> list = this.getSiteNavigationTServiceImpl().findSiteNavigationByisVisible(isVisible);
		if (!list.isEmpty()) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * 获取自定义区域
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public Map<String,Object> findEditarea(String sign,String state,Map<String,Object>map) {
		List<PageEditareaT> list = this.getPageEditareaTServiceImpl().findPageEditareaTBySign(sign, state, BaseTools.adminCreateId());
		if(!list.isEmpty()){
			for (Iterator it = list.iterator(); it.hasNext();) {
				PageEditareaT pea = (PageEditareaT) it.next();
				if (pea.getEditflag() == 1) {
					map.put("p1", pea);
				}
				if (pea.getEditflag() == 2) {
					map.put("p2", pea);
				}
				if (pea.getEditflag() == 3) {
					map.put("p3", pea);
				}
				if (pea.getEditflag() == 4) {
					map.put("p4", pea);
				}
				if (pea.getEditflag() == 5) {
					map.put("p5", pea);
				}
				if (pea.getEditflag() == 6) {
					map.put("p6", pea);
				}
				if (pea.getEditflag() == 7) {
					map.put("p7", pea);
				}
				if (pea.getEditflag() == 8) {
					map.put("p8", pea);
				}
				if (pea.getEditflag() == 9) {
					map.put("p9", pea);
				}
				if (pea.getEditflag() == 10) {
					map.put("p10", pea);
				}
				if (pea.getEditflag() == 11) {
					map.put("p11", pea);
				}
				if (pea.getEditflag() == 12) {
					map.put("p12", pea);
				}
				if (pea.getEditflag() == 13) {
					map.put("p13", pea);
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}
	
	/**
	 * 读取左侧主导航(商品分类)
	 * 
	 * @return
	 */
	public List<GoodsCategoryT> findGoodsCategoryT() {
		String state="1";//标示激活的商品分类
		List<GoodsCategoryT> list = this.getGoodsCategoryTServiceImpl().findAllGoodsCategoryT(state);
		if (!list.isEmpty()) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				GoodsCategoryT gt = (GoodsCategoryT) it.next();
				if (gt.getGrade().equals("0")) {
					gradecount++;
				}
			}
			return list;
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * 统计顶级分类个数
	 * 
	 * @return
	 */
	public int getGoodsCategoryTreeFirstCount() {
		return this.getGradecount();
	}

	/**
	 * 根据状态获取商品分类,用于生成静态分页商品列表
	 * 
	 * @return
	 */
	public List<GoodsCategoryT> findAllGoodsCategoryTByState() {
		String state = "1";//启用的商品状态
		List<GoodsCategoryT> list = this.getGoodsCategoryTServiceImpl().findAllGoodsCategoryT(state);
		if (!list.isEmpty()) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * 查询所有上架商品数据
	 * @return
	 */
	public List<GoodsT> findAllGoodsT() {
		String salestate = "1";//标示上架
		List<GoodsT> list = this.getGoodsTServiceImpl().finaAllGoodsT(salestate);
		if (!list.isEmpty()) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * 获取所有文章数据
	 * 
	 * @return
	 */
	public List<ArticleT> findAllArticleT() {
		String status = "1";//标示显示
		List<ArticleT> list = this.getArticleTServiceImpl().findAllArticleT(status);
		if (!list.isEmpty()) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * 获取页脚文章一级分类
	 */
	public List<ArticleCategoryT> findFooterCateogyrT() {
		String grade = "1";//标示文章是一级分类
		String status = "1";//标示显示
		List<ArticleCategoryT> list = this.getArticleCategoryTServiceImpl().findArticleCategoryByGrade(grade, status);
		if (!list.isEmpty()) {
			return list;
		}
		return Collections.emptyList();
	}
	
	/**
	 * 获取页脚文章一级分类下得文章
	 */
	@SuppressWarnings( { "unused", "unchecked" })
	public List<ArticleT> findFooterArticle() {
		String status="1";//显示的文章
		List<ArticleT> list = this.getArticleTServiceImpl().findAllArticleT(status);
		if (!list.isEmpty()) {
			List<ArticleT> list1 = new ArrayList<ArticleT>();
			for (Iterator it = list.iterator(); it.hasNext();) {
				ArticleT at = (ArticleT) it.next();
				if (at.getPosition() != null && at.getPosition().equals("1")) {
					list1.add(at);
				}
			}
			return list1;
		}
		return Collections.emptyList();
	}

	
}
