package com.jshop.action;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import com.jshop.action.templates.CreateHtml;
import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.ContentTag;
import com.jshop.action.tools.Serial;
import com.jshop.action.tools.Validate;
import com.jshop.entity.ArticleCategoryT;
import com.jshop.entity.ArticleT;
import com.jshop.entity.GoodsT;
import com.jshop.entity.ImgT;
import com.jshop.entity.JshopbasicInfoT;
import com.jshop.entity.ProductT;
import com.jshop.entity.SiteNavigationT;
import com.jshop.service.impl.ArticleCategoryTServiceImpl;
import com.jshop.service.impl.ArticleTServiceImpl;
import com.jshop.service.impl.GoodsCommentTServiceImpl;
import com.jshop.service.impl.GoodsTServiceImpl;
import com.jshop.service.impl.JshopbasicInfoTServiceImpl;
import com.jshop.service.impl.ProductTServiceImpl;
import com.jshop.service.impl.SiteNavigationTServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import freemarker.template.TemplateException;
@ParentPackage("jshop")

@Controller("goodsTNAction")
public class GoodsTNAction extends ActionSupport {
	@Resource(name="goodsTServiceImpl")
	private GoodsTServiceImpl goodsTServiceImpl;
	@Resource(name="serial")
	private Serial serial;
	@Resource(name="productTServiceImpl")
	private ProductTServiceImpl productTServiceImpl;
	@Resource(name="imgTAction")
	private ImgTAction imgTAction;
	@Resource(name="articleTServiceImpl")
	private ArticleTServiceImpl articleTServiceImpl;
	@Resource(name="articleCategoryTServiceImpl")
	private ArticleCategoryTServiceImpl articleCategoryTServiceImpl;
	@Resource(name="jshopbasicInfoTServiceImpl")
	private JshopbasicInfoTServiceImpl jshopbasicInfoTServiceImpl;
	@Resource(name="siteNavigationTServiceImpl")
	private SiteNavigationTServiceImpl siteNavigationTServiceImpl;
	@Resource(name="goodsCommentTServiceImpl")
	private GoodsCommentTServiceImpl goodsCommentTServiceImpl;
	@Resource(name="createHtml")
	private CreateHtml createHtml;

	private String goodsid;
	private String goodsname;
	private String brandname;
	private String model;
	private String nname;
	private String lname;
	private String sname;
	private String fname;
	private String navid;
	private String ltypeid;
	private String stypeid;
	private String price;
	private String memberprice;
	private String points;
	private String pictureurl;
	private String quantity;
	private String salestate;
	private String detail;
	private String unitname;
	private String keywordname;
	private String weight;
	private String readcount;
	private String relatedproductid;
	private String recommended;
	private String hotsale;
	private String bargainprice;
	private String sort;
	private Date createtime;
	private String creatorid;
	private String replycount;
	private String brandid;
	private String placeStore;
	private String metaKeywords;
	private String metaDescription;
	private String cost;
	private String saleprice;
	private String isNew;
	private String htmlPath;
	private String productSn;
	private String goodsParameterValue;
	private String freezeStore;
	private String keywordid;
	private String productplaceid;
	private String unitnameid;
	private String goodsTypeId;
	private String goodsTypeName;
	private String goodsAttrVal1;
	private String goodsAttrVal2;
	private String goodsAttrVal3;
	private String goodsAttrVal4;
	private String goodsAttrVal5;
	private String goodsAttrVal6;
	private String goodsAttrVal7;
	private String goodsAttrVal8;
	private String goodsAttrVal9;
	private String goodsAttrVal10;
	private String goodsAttrVal11;
	private String goodsAttrVal12;
	private String goodsAttrVal13;
	private String goodsAttrVal14;
	private String goodsAttrVal15;
	private String goodsAttrVal16;
	private String goodsAttrVal17;
	private String goodsAttrVal18;
	private String goodsAttrVal19;
	private String goodsAttrVal20;
	private String goodsAttrVal21;
	private String goodsAttrVal22;
	private String goodsAttrVal23;
	private String goodsAttrVal24;
	private String goodsAttrVal25;
	private String goodsAttrVal26;
	private String goodsAttrVal27;
	private String goodsAttrVal28;
	private String goodsAttrVal29;
	private String goodsAttrVal0;
	private String usersetnum;
	private String isSpecificationsOpen;
	private String specificationsValue;
	private String star;
	private String staruser;
	private String totalcomment;
	private String ismobileplatformgoods;
	private String rejson;
	private String query;//text
	private String qtype;//select
	private String sortname;//排序字段
	private String sortorder;//排序方式
	private GoodsT bean=new GoodsT();
	private List rows=new ArrayList();
	private Map<String, Object> map=new HashMap<String,Object>();
	private int rp;
	private int page = 1;
	private int total = 0;
	private File fileupload;
	private String fileuploadFileName;
	private String allfilename;
	private String pcpath;
	private boolean delpcflag;
	private boolean slogin;
	private boolean sucflag;
	private String usession;
	
	

	@JSON(serialize = false)
	public GoodsTServiceImpl getGoodsTServiceImpl() {
		return goodsTServiceImpl;
	}

	public void setGoodsTServiceImpl(GoodsTServiceImpl goodsTServiceImpl) {
		this.goodsTServiceImpl = goodsTServiceImpl;
	}
	@JSON(serialize = false)
	public Serial getSerial() {
		return serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}
	@JSON(serialize = false)
	public ProductTServiceImpl getProductTServiceImpl() {
		return productTServiceImpl;
	}

	public void setProductTServiceImpl(ProductTServiceImpl productTServiceImpl) {
		this.productTServiceImpl = productTServiceImpl;
	}
	@JSON(serialize = false)
	public ImgTAction getImgTAction() {
		return imgTAction;
	}

	public void setImgTAction(ImgTAction imgTAction) {
		this.imgTAction = imgTAction;
	}
	@JSON(serialize = false)
	public ArticleTServiceImpl getArticleTServiceImpl() {
		return articleTServiceImpl;
	}

	public void setArticleTServiceImpl(ArticleTServiceImpl articleTServiceImpl) {
		this.articleTServiceImpl = articleTServiceImpl;
	}
	@JSON(serialize = false)
	public ArticleCategoryTServiceImpl getArticleCategoryTServiceImpl() {
		return articleCategoryTServiceImpl;
	}

	public void setArticleCategoryTServiceImpl(ArticleCategoryTServiceImpl articleCategoryTServiceImpl) {
		this.articleCategoryTServiceImpl = articleCategoryTServiceImpl;
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
	public GoodsCommentTServiceImpl getGoodsCommentTServiceImpl() {
		return goodsCommentTServiceImpl;
	}

	public void setGoodsCommentTServiceImpl(GoodsCommentTServiceImpl goodsCommentTServiceImpl) {
		this.goodsCommentTServiceImpl = goodsCommentTServiceImpl;
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

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
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

	public String getPictureurl() {
		return pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public String getSalestate() {
		return salestate;
	}

	public void setSalestate(String salestate) {
		this.salestate = salestate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getKeywordname() {
		return keywordname;
	}

	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRelatedproductid() {
		return relatedproductid;
	}

	public void setRelatedproductid(String relatedproductid) {
		this.relatedproductid = relatedproductid;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

	public String getHotsale() {
		return hotsale;
	}

	public void setHotsale(String hotsale) {
		this.hotsale = hotsale;
	}

	public String getBargainprice() {
		return bargainprice;
	}

	public void setBargainprice(String bargainprice) {
		this.bargainprice = bargainprice;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getPlaceStore() {
		return placeStore;
	}

	public void setPlaceStore(String placeStore) {
		this.placeStore = placeStore;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getGoodsParameterValue() {
		return goodsParameterValue;
	}

	public void setGoodsParameterValue(String goodsParameterValue) {
		this.goodsParameterValue = goodsParameterValue;
	}

	public String getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(String keywordid) {
		this.keywordid = keywordid;
	}

	public String getProductplaceid() {
		return productplaceid;
	}

	public void setProductplaceid(String productplaceid) {
		this.productplaceid = productplaceid;
	}

	public String getUnitnameid() {
		return unitnameid;
	}

	public void setUnitnameid(String unitnameid) {
		this.unitnameid = unitnameid;
	}

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public String getGoodsAttrVal1() {
		return goodsAttrVal1;
	}

	public void setGoodsAttrVal1(String goodsAttrVal1) {
		this.goodsAttrVal1 = goodsAttrVal1;
	}

	public String getGoodsAttrVal2() {
		return goodsAttrVal2;
	}

	public void setGoodsAttrVal2(String goodsAttrVal2) {
		this.goodsAttrVal2 = goodsAttrVal2;
	}

	public String getGoodsAttrVal3() {
		return goodsAttrVal3;
	}

	public void setGoodsAttrVal3(String goodsAttrVal3) {
		this.goodsAttrVal3 = goodsAttrVal3;
	}

	public String getGoodsAttrVal4() {
		return goodsAttrVal4;
	}

	public void setGoodsAttrVal4(String goodsAttrVal4) {
		this.goodsAttrVal4 = goodsAttrVal4;
	}

	public String getGoodsAttrVal5() {
		return goodsAttrVal5;
	}

	public void setGoodsAttrVal5(String goodsAttrVal5) {
		this.goodsAttrVal5 = goodsAttrVal5;
	}

	public String getGoodsAttrVal6() {
		return goodsAttrVal6;
	}

	public void setGoodsAttrVal6(String goodsAttrVal6) {
		this.goodsAttrVal6 = goodsAttrVal6;
	}

	public String getGoodsAttrVal7() {
		return goodsAttrVal7;
	}

	public void setGoodsAttrVal7(String goodsAttrVal7) {
		this.goodsAttrVal7 = goodsAttrVal7;
	}

	public String getGoodsAttrVal8() {
		return goodsAttrVal8;
	}

	public void setGoodsAttrVal8(String goodsAttrVal8) {
		this.goodsAttrVal8 = goodsAttrVal8;
	}

	public String getGoodsAttrVal9() {
		return goodsAttrVal9;
	}

	public void setGoodsAttrVal9(String goodsAttrVal9) {
		this.goodsAttrVal9 = goodsAttrVal9;
	}

	public String getGoodsAttrVal10() {
		return goodsAttrVal10;
	}

	public void setGoodsAttrVal10(String goodsAttrVal10) {
		this.goodsAttrVal10 = goodsAttrVal10;
	}

	public String getGoodsAttrVal11() {
		return goodsAttrVal11;
	}

	public void setGoodsAttrVal11(String goodsAttrVal11) {
		this.goodsAttrVal11 = goodsAttrVal11;
	}

	public String getGoodsAttrVal12() {
		return goodsAttrVal12;
	}

	public void setGoodsAttrVal12(String goodsAttrVal12) {
		this.goodsAttrVal12 = goodsAttrVal12;
	}

	public String getGoodsAttrVal13() {
		return goodsAttrVal13;
	}

	public void setGoodsAttrVal13(String goodsAttrVal13) {
		this.goodsAttrVal13 = goodsAttrVal13;
	}

	public String getGoodsAttrVal14() {
		return goodsAttrVal14;
	}

	public void setGoodsAttrVal14(String goodsAttrVal14) {
		this.goodsAttrVal14 = goodsAttrVal14;
	}

	public String getGoodsAttrVal15() {
		return goodsAttrVal15;
	}

	public void setGoodsAttrVal15(String goodsAttrVal15) {
		this.goodsAttrVal15 = goodsAttrVal15;
	}

	public String getGoodsAttrVal16() {
		return goodsAttrVal16;
	}

	public void setGoodsAttrVal16(String goodsAttrVal16) {
		this.goodsAttrVal16 = goodsAttrVal16;
	}

	public String getGoodsAttrVal17() {
		return goodsAttrVal17;
	}

	public void setGoodsAttrVal17(String goodsAttrVal17) {
		this.goodsAttrVal17 = goodsAttrVal17;
	}

	public String getGoodsAttrVal18() {
		return goodsAttrVal18;
	}

	public void setGoodsAttrVal18(String goodsAttrVal18) {
		this.goodsAttrVal18 = goodsAttrVal18;
	}

	public String getGoodsAttrVal19() {
		return goodsAttrVal19;
	}

	public void setGoodsAttrVal19(String goodsAttrVal19) {
		this.goodsAttrVal19 = goodsAttrVal19;
	}

	public String getGoodsAttrVal20() {
		return goodsAttrVal20;
	}

	public void setGoodsAttrVal20(String goodsAttrVal20) {
		this.goodsAttrVal20 = goodsAttrVal20;
	}

	public String getGoodsAttrVal21() {
		return goodsAttrVal21;
	}

	public void setGoodsAttrVal21(String goodsAttrVal21) {
		this.goodsAttrVal21 = goodsAttrVal21;
	}

	public String getGoodsAttrVal22() {
		return goodsAttrVal22;
	}

	public void setGoodsAttrVal22(String goodsAttrVal22) {
		this.goodsAttrVal22 = goodsAttrVal22;
	}

	public String getGoodsAttrVal23() {
		return goodsAttrVal23;
	}

	public void setGoodsAttrVal23(String goodsAttrVal23) {
		this.goodsAttrVal23 = goodsAttrVal23;
	}

	public String getGoodsAttrVal24() {
		return goodsAttrVal24;
	}

	public void setGoodsAttrVal24(String goodsAttrVal24) {
		this.goodsAttrVal24 = goodsAttrVal24;
	}

	public String getGoodsAttrVal25() {
		return goodsAttrVal25;
	}

	public void setGoodsAttrVal25(String goodsAttrVal25) {
		this.goodsAttrVal25 = goodsAttrVal25;
	}

	public String getGoodsAttrVal26() {
		return goodsAttrVal26;
	}

	public void setGoodsAttrVal26(String goodsAttrVal26) {
		this.goodsAttrVal26 = goodsAttrVal26;
	}

	public String getGoodsAttrVal27() {
		return goodsAttrVal27;
	}

	public void setGoodsAttrVal27(String goodsAttrVal27) {
		this.goodsAttrVal27 = goodsAttrVal27;
	}

	public String getGoodsAttrVal28() {
		return goodsAttrVal28;
	}

	public void setGoodsAttrVal28(String goodsAttrVal28) {
		this.goodsAttrVal28 = goodsAttrVal28;
	}

	public String getGoodsAttrVal29() {
		return goodsAttrVal29;
	}

	public void setGoodsAttrVal29(String goodsAttrVal29) {
		this.goodsAttrVal29 = goodsAttrVal29;
	}

	public String getGoodsAttrVal0() {
		return goodsAttrVal0;
	}

	public void setGoodsAttrVal0(String goodsAttrVal0) {
		this.goodsAttrVal0 = goodsAttrVal0;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public GoodsT getBean() {
		return bean;
	}

	public void setBean(GoodsT bean) {
		this.bean = bean;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public String getAllfilename() {
		return allfilename;
	}

	public void setAllfilename(String allfilename) {
		this.allfilename = allfilename;
	}

	public String getPcpath() {
		return pcpath;
	}

	public void setPcpath(String pcpath) {
		this.pcpath = pcpath;
	}

	public boolean isDelpcflag() {
		return delpcflag;
	}

	public void setDelpcflag(boolean delpcflag) {
		this.delpcflag = delpcflag;
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

	public String getUsersetnum() {
		return usersetnum;
	}

	public void setUsersetnum(String usersetnum) {
		this.usersetnum = usersetnum;
	}

	public String getIsSpecificationsOpen() {
		return isSpecificationsOpen;
	}

	public void setIsSpecificationsOpen(String isSpecificationsOpen) {
		this.isSpecificationsOpen = isSpecificationsOpen;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMemberprice() {
		return memberprice;
	}

	public void setMemberprice(String memberprice) {
		this.memberprice = memberprice;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getReadcount() {
		return readcount;
	}

	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getReplycount() {
		return replycount;
	}

	public void setReplycount(String replycount) {
		this.replycount = replycount;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}

	public String getFreezeStore() {
		return freezeStore;
	}

	public void setFreezeStore(String freezeStore) {
		this.freezeStore = freezeStore;
	}

	public String getRejson() {
		return rejson;
	}

	public void setRejson(String rejson) {
		this.rejson = rejson;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getStaruser() {
		return staruser;
	}

	public void setStaruser(String staruser) {
		this.staruser = staruser;
	}

	public String getTotalcomment() {
		return totalcomment;
	}

	public void setTotalcomment(String totalcomment) {
		this.totalcomment = totalcomment;
	}

	public String getIsmobileplatformgoods() {
		return ismobileplatformgoods;
	}

	public void setIsmobileplatformgoods(String ismobileplatformgoods) {
		this.ismobileplatformgoods = ismobileplatformgoods;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getSpecificationsValue() {
		return specificationsValue;
	}

	public void setSpecificationsValue(String specificationsValue) {
		this.specificationsValue = specificationsValue;
	}

	public String getUsession() {
		return usession;
	}

	public void setUsession(String usession) {
		this.usession = usession;
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
	public void createGoodsT() {
		findIndexSiteNavigation();
		findJshopbasicInfo();
		findFooterCateogyrT();
		findFooterArticle();
	}

	/**
	 * 获取导航
	 */
	public void findIndexSiteNavigation() {
	
		String isVisible = "1";
		List<SiteNavigationT> list = this.getSiteNavigationTServiceImpl().findSiteNavigationByisVisible(isVisible, BaseTools.adminCreateId());
		if (list != null) {
			map.put("siteNavigationList", list);
		}
	
	}

	/**
	 * 获取商城基本信息
	 */
	public void findJshopbasicInfo() {
		String state = "1";
		String openstate = "1";
		JshopbasicInfoT bean = this.getJshopbasicInfoTServiceImpl().findJshopbasicInfoBystateandopstate(BaseTools.adminCreateId(), state, openstate);
		if (bean != null) {
			map.put("jshopbasicinfo", bean);
		}

	}

	/**
	 * 获取页脚分类
	 */
	public void findFooterCateogyrT() {
		String grade = "1";
		String status = "1";

		List<ArticleCategoryT> list = this.getArticleCategoryTServiceImpl().findArticleCategoryByGrade(grade, status, BaseTools.adminCreateId());
		if (list != null) {
			map.put("footcategory", list);
		}
	}

	/**
	 * 获取页脚文章
	 */
	public void findFooterArticle() {
		List<ArticleT> list = this.getArticleTServiceImpl().findAllArticleBycreatorid(BaseTools.adminCreateId());
		if (list != null) {
			List<ArticleT> list1 = new ArrayList<ArticleT>();
			map.put("footerarticle", list1);
		}

	}

	/**
	 * 增加商品
	 * 
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@Action(value = "addGoods", results = { 
			@Result(name = "json",type="json")
	})
	public String addGoods() throws IOException, TemplateException {
		
			//规格和属性都没有开启
			if (this.getGoodsTypeId().equals("0") && this.getIsSpecificationsOpen().equals("0") || this.getIsSpecificationsOpen().equals("2")) {
				GoodsT gt = new GoodsT();
				gt.setGoodsid(this.getSerial().Serialid(Serial.GOODS));
				gt.setGoodsname(this.getGoodsname());
				gt.setBrandname(this.getBrandname().trim());
				gt.setModel(this.getModel());
				gt.setNname(this.getNname());
				gt.setLname(this.getLname());
				gt.setSname(this.getSname());
				gt.setFname(this.getFname());
				gt.setNavid(this.getNavid());
				gt.setLtypeid(this.getLtypeid());
				gt.setStypeid(this.getStypeid());
				gt.setPrice(Double.parseDouble(this.getPrice()));
				gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
				gt.setPoints(Double.parseDouble(this.getPoints()));
				gt.setPictureurl(this.getPictureurl());
				gt.setQuantity(Integer.parseInt(this.getQuantity()));
				gt.setSalestate(this.getSalestate());
				gt.setDetail(this.getDetail());
				gt.setUnitname(this.getUnitname());
				gt.setUnitnameid(this.getUnitnameid());
				gt.setKeywordname(this.getKeywordname());
				gt.setWeight(this.getWeight());
				gt.setReadcount(0);
				//gt.setRelatedproductid(this.getRelatedproductid());
				gt.setRecommended(this.getRecommended());
				gt.setHotsale(this.getHotsale());
				gt.setBargainprice(this.getBargainprice());
				gt.setSort(0);
				gt.setCreatetime(BaseTools.systemtime());
				gt.setCreatorid(BaseTools.adminCreateId());
				gt.setReplycount(0);
				gt.setBrandid(this.getBrandid());
				gt.setPlaceStore(this.getPlaceStore());
				gt.setMetaKeywords(this.getMetaKeywords());
				gt.setMetaDescription(this.getMetaDescription());
				gt.setCost(Double.parseDouble(this.getCost()));
				gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
				gt.setIsNew(this.getIsNew());
				gt.setHtmlPath("#");
				gt.setProductSn(this.getProductSn());
				gt.setGoodsParameterValue("");
				gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
				gt.setKeywordid(this.getKeywordid());
				gt.setGoodsTypeId(this.getGoodsTypeId());
				gt.setGoodsTypeName(this.getGoodsTypeName());
				gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
				gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
				gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
				gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
				gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
				gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
				gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
				gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
				gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
				gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
				gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
				gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
				gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
				gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
				gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
				gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
				gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
				gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
				gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
				gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
				gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
				gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
				gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
				gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
				gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
				gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
				gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
				gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
				gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
				gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
				gt.setUsersetnum(this.getUsersetnum());
				gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
				gt.setStar(1);
				gt.setStaruser(0);
				gt.setTotalcomment(0);
				gt.setVirtualsale(0);
				gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
				if (this.getGoodsTServiceImpl().addGoods(gt) > 0) {
					this.createGoodsT();
					map.put("goodsdetail", gt);
					String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
					this.updateHtmlPath(gt.getGoodsid(), htmlPath);
					this.setSucflag(true);
					return "json";
				} else {
					this.setSucflag(false);
					return "json";
				}
			} else {
				if (!this.getGoodsTypeId().equals("0") && this.getIsSpecificationsOpen().equals("0") || this.getIsSpecificationsOpen().equals("2")) {
					//增加属性不增加货品
					GoodsT gt = new GoodsT();
					gt.setGoodsid(this.getSerial().Serialid(Serial.GOODS));
					gt.setGoodsname(this.getGoodsname());
					gt.setBrandname(this.getBrandname().trim());
					gt.setModel(this.getModel());
					gt.setNname(this.getNname());
					gt.setLname(this.getLname());
					gt.setSname(this.getSname());
					gt.setFname(this.getFname());
					gt.setNavid(this.getNavid());
					gt.setLtypeid(this.getLtypeid());
					gt.setStypeid(this.getStypeid());
					gt.setPrice(Double.parseDouble(this.getPrice()));
					gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
					gt.setPoints(Double.parseDouble(this.getPoints()));
					gt.setPictureurl(this.getPictureurl());
					gt.setQuantity(Integer.parseInt(this.getQuantity()));
					gt.setSalestate(this.getSalestate());
					gt.setDetail(this.getDetail());
					gt.setUnitname(this.getUnitname());
					gt.setUnitnameid(this.getUnitnameid());
					gt.setKeywordname(this.getKeywordname());
					gt.setWeight(this.getWeight());
					gt.setReadcount(0);
					//gt.setRelatedproductid(this.getRelatedproductid());
					gt.setRecommended(this.getRecommended());
					gt.setHotsale(this.getHotsale());
					gt.setBargainprice(this.getBargainprice());
					gt.setSort(0);
					gt.setCreatetime(BaseTools.systemtime());
					gt.setCreatorid(BaseTools.adminCreateId());
					gt.setReplycount(0);
					gt.setBrandid(this.getBrandid());
					gt.setPlaceStore(this.getPlaceStore());
					gt.setMetaKeywords(this.getMetaKeywords());
					gt.setMetaDescription(this.getMetaDescription());
					gt.setCost(Double.parseDouble(this.getCost()));
					gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
					gt.setIsNew(this.getIsNew());
					gt.setHtmlPath("#");
					gt.setProductSn(this.getProductSn());
					gt.setGoodsParameterValue(this.getGoodsParameterValue());
					gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
					gt.setKeywordid(this.getKeywordid());
					gt.setGoodsTypeId(this.getGoodsTypeId());
					gt.setGoodsTypeName(this.getGoodsTypeName());
					gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
					gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
					gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
					gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
					gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
					gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
					gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
					gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
					gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
					gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
					gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
					gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
					gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
					gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
					gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
					gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
					gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
					gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
					gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
					gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
					gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
					gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
					gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
					gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
					gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
					gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
					gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
					gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
					gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
					gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
					gt.setUsersetnum(this.getUsersetnum());
					gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
					gt.setStar(1);
					gt.setStaruser(0);
					gt.setTotalcomment(0);
					gt.setVirtualsale(0);
					gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
					if (this.getGoodsTServiceImpl().addGoods(gt) > 0) {
						this.createGoodsT();
						map.put("goodsdetail", gt);
						String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
						this.updateHtmlPath(gt.getGoodsid(), htmlPath);
						this.setSucflag(true);
						return "json";
					} else {
						this.setSucflag(false);
						return "json";
					}
				}

			}
			//规格开启属性未开启
			if (this.getIsSpecificationsOpen().equals("1") && this.getGoodsTypeId().equals("0")) {
				GoodsT gt = new GoodsT();
				gt.setGoodsid(this.getSerial().Serialid(Serial.GOODS));
				gt.setGoodsname(this.getGoodsname());
				gt.setBrandname(this.getBrandname().trim());
				gt.setModel(this.getModel());
				gt.setNname(this.getNname());
				gt.setLname(this.getLname());
				gt.setSname(this.getSname());
				gt.setFname(this.getFname());
				gt.setNavid(this.getNavid());
				gt.setLtypeid(this.getLtypeid());
				gt.setStypeid(this.getStypeid());
				gt.setPrice(Double.parseDouble(this.getPrice()));
				gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
				gt.setPoints(Double.parseDouble(this.getPoints()));
				gt.setPictureurl(this.getPictureurl());
				gt.setQuantity(Integer.parseInt(this.getQuantity()));
				gt.setSalestate(this.getSalestate());
				gt.setDetail(this.getDetail());
				gt.setUnitname(this.getUnitname());
				gt.setUnitnameid(this.getUnitnameid());
				gt.setKeywordname(this.getKeywordname());
				gt.setWeight(this.getWeight());
				gt.setReadcount(0);
				//gt.setRelatedproductid(this.getRelatedproductid());
				gt.setRecommended(this.getRecommended());
				gt.setHotsale(this.getHotsale());
				gt.setBargainprice(this.getBargainprice());
				gt.setSort(0);
				gt.setCreatetime(BaseTools.systemtime());
				gt.setCreatorid(BaseTools.adminCreateId());
				gt.setReplycount(0);
				gt.setBrandid(this.getBrandid());
				gt.setPlaceStore(this.getPlaceStore());
				gt.setMetaKeywords(this.getMetaKeywords());
				gt.setMetaDescription(this.getMetaDescription());
				gt.setCost(Double.parseDouble(this.getCost()));
				gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
				gt.setIsNew(this.getIsNew());
				gt.setHtmlPath("#");
				gt.setProductSn(this.getProductSn());
				gt.setGoodsParameterValue("");
				gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
				gt.setKeywordid(this.getKeywordid());
				gt.setGoodsTypeId(this.getGoodsTypeId());
				gt.setGoodsTypeName(this.getGoodsTypeName());
				gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
				gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
				gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
				gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
				gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
				gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
				gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
				gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
				gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
				gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
				gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
				gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
				gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
				gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
				gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
				gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
				gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
				gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
				gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
				gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
				gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
				gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
				gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
				gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
				gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
				gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
				gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
				gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
				gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
				gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
				gt.setUsersetnum(this.getUsersetnum());
				gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
				gt.setStar(1);
				gt.setStaruser(0);
				gt.setTotalcomment(0);
				gt.setVirtualsale(0);
				gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
				if (this.getGoodsTServiceImpl().addGoods(gt) > 0) {
					//此处要增加商品货品到货品表
					ProductT pt = new ProductT();
					String str[] = this.getRejson().split("-");
					String strsv[] = this.getSpecificationsValue().split("-");
					int count = 0;
					for (int i = 0; i < str.length; i++) {
						pt.setProductid(this.getSerial().Serialid(Serial.PRODUCT));
						pt.setProductName(gt.getGoodsname());
						pt.setSpecificationsValue(strsv[i].toString());
						pt.setGoodsid(gt.getGoodsid());
						pt.setCreatorid(BaseTools.adminCreateId());
						pt.setCreatetime(BaseTools.systemtime());
						JSONObject jo = (JSONObject) JSONValue.parse(str[i].toString());
						Iterator it = jo.keySet().iterator();
						while (it.hasNext()) {
							String key = it.next().toString();
							if (key.equals("price")) {
								pt.setPrice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("memberprice")) {
								pt.setMemberprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("cost")) {
								pt.setCost(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("saleprice")) {
								pt.setSaleprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("freezeStore")) {
								pt.setFreezeStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("store")) {
								pt.setStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("isDefault")) {
								pt.setIsDefault(jo.get(key).toString());
							}
							if (key.equals("isSalestate")) {
								pt.setIsSalestate(jo.get(key).toString());
							}
							if (key.equals("productSn")) {
								pt.setProductSn(jo.get(key).toString());
							}
							if (key.equals("warehouseLocation")) {
								pt.setWarehouseLocation(jo.get(key).toString());
							}
							if (key.equals("placeStore")) {
								pt.setPlaceStore(jo.get(key).toString());
							}
							if (key.equals("weight")) {
								pt.setWeight(jo.get(key).toString());
							}
						}
						if (this.getProductTServiceImpl().addProductT(pt) > 0) {
							count++;
						}
					}
					if (count == str.length) {
						this.createGoodsT();
						map.put("goodsdetail", gt);
						String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
						this.updateHtmlPath(gt.getGoodsid(), htmlPath);
						this.setSucflag(true);
						return "json";
					} else {
						this.setSucflag(false);
						return "json";
					}

				} else {
					this.setSucflag(false);
					return "json";
				}
			}

			//规格和属性都开启
			if (this.getIsSpecificationsOpen().equals("1") && !this.getGoodsTypeId().equals("0")) {
				GoodsT gt = new GoodsT();
				gt.setGoodsid(this.getSerial().Serialid(Serial.GOODS));
				gt.setGoodsname(this.getGoodsname());
				gt.setBrandname(this.getBrandname().trim());
				gt.setModel(this.getModel());
				gt.setNname(this.getNname());
				gt.setLname(this.getLname());
				gt.setSname(this.getSname());
				gt.setFname(this.getFname());
				gt.setNavid(this.getNavid());
				gt.setLtypeid(this.getLtypeid());
				gt.setStypeid(this.getStypeid());
				gt.setPrice(Double.parseDouble(this.getPrice()));
				gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
				gt.setPoints(Double.parseDouble(this.getPoints()));
				gt.setPictureurl(this.getPictureurl());
				gt.setQuantity(Integer.parseInt(this.getQuantity()));
				gt.setSalestate(this.getSalestate());
				gt.setDetail(this.getDetail());
				gt.setUnitname(this.getUnitname());
				gt.setUnitnameid(this.getUnitnameid());
				gt.setKeywordname(this.getKeywordname());
				gt.setWeight(this.getWeight());
				gt.setReadcount(0);
				//gt.setRelatedproductid(this.getRelatedproductid());
				gt.setRecommended(this.getRecommended());
				gt.setHotsale(this.getHotsale());
				gt.setBargainprice(this.getBargainprice());
				gt.setSort(0);
				gt.setCreatetime(BaseTools.systemtime());
				gt.setCreatorid(BaseTools.adminCreateId());
				gt.setReplycount(0);
				gt.setBrandid(this.getBrandid());
				gt.setPlaceStore(this.getPlaceStore());
				gt.setMetaKeywords(this.getMetaKeywords());
				gt.setMetaDescription(this.getMetaDescription());
				gt.setCost(Double.parseDouble(this.getCost()));
				gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
				gt.setIsNew(this.getIsNew());
				gt.setHtmlPath("#");
				gt.setProductSn(this.getProductSn());
				gt.setGoodsParameterValue(this.getGoodsParameterValue());
				gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
				gt.setKeywordid(this.getKeywordid());
				gt.setGoodsTypeId(this.getGoodsTypeId());
				gt.setGoodsTypeName(this.getGoodsTypeName());
				gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
				gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
				gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
				gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
				gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
				gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
				gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
				gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
				gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
				gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
				gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
				gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
				gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
				gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
				gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
				gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
				gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
				gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
				gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
				gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
				gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
				gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
				gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
				gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
				gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
				gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
				gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
				gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
				gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
				gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
				gt.setUsersetnum(this.getUsersetnum());
				gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
				gt.setStar(1);
				gt.setStaruser(0);
				gt.setTotalcomment(0);
				gt.setVirtualsale(0);
				gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
				if (this.getGoodsTServiceImpl().addGoods(gt) > 0) {
					//此处要增加商品货品到货品表
					ProductT pt = new ProductT();
					String str[] = this.getRejson().split("-");
					String strsv[] = this.getSpecificationsValue().split("-");
					int count = 0;
					for (int i = 0; i < str.length; i++) {
						pt.setProductid(this.getSerial().Serialid(Serial.PRODUCT));
						pt.setProductName(gt.getGoodsname());
						pt.setSpecificationsValue(strsv[i].toString());
						pt.setGoodsid(gt.getGoodsid());
						pt.setCreatorid(BaseTools.adminCreateId());
						pt.setCreatetime(BaseTools.systemtime());
						JSONObject jo = (JSONObject) JSONValue.parse(str[i].toString());
						Iterator it = jo.keySet().iterator();
						while (it.hasNext()) {
							String key = it.next().toString();
							if (key.equals("price")) {
								pt.setPrice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("memberprice")) {
								pt.setMemberprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("cost")) {
								pt.setCost(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("saleprice")) {
								pt.setSaleprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("freezeStore")) {
								pt.setFreezeStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("store")) {
								pt.setStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("isDefault")) {
								pt.setIsDefault(jo.get(key).toString());
							}
							if (key.equals("isSalestate")) {
								pt.setIsSalestate(jo.get(key).toString());
							}
							if (key.equals("productSn")) {
								pt.setProductSn(jo.get(key).toString());
							}
							if (key.equals("warehouseLocation")) {
								pt.setWarehouseLocation(jo.get(key).toString());
							}
							if (key.equals("placeStore")) {
								pt.setPlaceStore(jo.get(key).toString());
							}
							if (key.equals("weight")) {
								pt.setWeight(jo.get(key).toString());
							}
						}
						if (this.getProductTServiceImpl().addProductT(pt) > 0) {
							count++;
						}
					}
					if (count == str.length) {
						this.createGoodsT();
						map.put("goodsdetail", gt);
						String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
						this.updateHtmlPath(gt.getGoodsid(), htmlPath);
						this.setSucflag(true);
						return "json";
					} else {
						this.setSucflag(false);
						return "json";
					}

				} else {
					this.setSucflag(false);
					return "json";
				}
			}
		this.setSucflag(false);
		return "json";
	}

	/**
	 * 根据用户id读取所有该用户增加的商品
	 * 
	 * @return
	 */
	@Action(value = "findAllGoods", results = { 
			@Result(name = "json",type="json",params={"excludeNullProperties","true"})
	})
	public String findAllGoods() {
			if ("sc".equals(this.getQtype())) {
				finddefaultAllGoods();

			} else {
				if (Validate.StrisNull(this.getQtype())) {
					return "json";
				} else {
					if (this.getQtype().equals("goodsname")) {
						//findGoodsByGoodsname();
					}
				}
			}
		return "json";

	}

	/**
	 * 获取用户的所有商品信息
	 */
	public void finddefaultAllGoods() {
		int currentPage = page;
		int lineSize = rp;
		if (Validate.StrNotNull(sortname) && Validate.StrNotNull(sortorder)) {
			String queryString = "from GoodsT as gt where gt.creatorid=:creatorid order by " +sortname+ " " + sortorder + "";
			List<GoodsT> list = this.getGoodsTServiceImpl().sortAllGoods(currentPage, lineSize, BaseTools.adminCreateId(), queryString);
			if (!list.isEmpty()) {
				ProcessGoodsList(list);
			}
		} 
	}

	/**
	 * 迭代处理供前台显示
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void ProcessGoodsList(List<GoodsT> list) {
		total = this.getGoodsTServiceImpl().countAllGoods(BaseTools.adminCreateId());
		rows.clear();
		for (Iterator it = list.iterator(); it.hasNext();) {
			GoodsT gt = (GoodsT) it.next();
			if (gt.getRecommended().equals("1")) {
				gt.setRecommended("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setRecommended("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getHotsale().equals("1")) {
				gt.setHotsale("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setHotsale("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getBargainprice().equals("1")) {
				gt.setBargainprice("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setBargainprice("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getIsNew().equals("1")) {
				gt.setIsNew("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setIsNew("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			if (gt.getSalestate().equals("1")) {
				gt.setSalestate("<span class='truestatue'><img src='../images/base_right_icon.gif'/></span>");
			} else {
				gt.setSalestate("<span class='falsestatue'><img src='../images/base_wrong_icon.gif'/></span>");
			}
			
			Map cellMap = new HashMap();
			cellMap.put("id", gt.getGoodsid());
			cellMap.put("cell", new Object[] {gt.getGoodsname(), gt.getUsersetnum(), gt.getNname(), gt.getMemberprice(), gt.getSalestate(), gt.getIsNew(), gt.getBargainprice(), gt.getHotsale(), gt.getRecommended(), gt.getQuantity(), "<a target='_blank' id='editgoods' href='editgoods.jsp?session="+this.getUsession()+"#goods&goodsid=" + gt.getGoodsid() + "' name='editgoods'>[编辑]</a>" + "<a target='_blank' id='browergoods' href='" + gt.getHtmlPath() + "' name='browergoods'>[预览]</a>" });
			rows.add(cellMap);
		}
	}

	/**
	 * 根据商品id获取商品数据
	 * 
	 * @return
	 */
	@Action(value = "findGoodsById", results = { 
			@Result(name = "json",type="json")
	})
	public String findGoodsById() {
		
			if (Validate.StrNotNull(this.getGoodsid())) {
				bean = this.getGoodsTServiceImpl().findGoodsById(this.getGoodsid().trim());
				if (bean != null) {
					this.setSucflag(true);
					return "json";
				}
			}
			this.setSucflag(false);
			return "json";

	}

	/**
	 * 更新文章静态路径
	 * 
	 * @param articleid
	 * @param htmlPath
	 */
	public void updateHtmlPath(String goodsid, String htmlPath) {
		this.getGoodsTServiceImpl().updateHtmlPath(goodsid, htmlPath);
	}

	/**
	 * 更新商品
	 * 
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@Action(value = "updateGoods", results = { 
			@Result(name = "json",type="json")
	})
	public String updateGoods() throws IOException, TemplateException {
			//规格和属性都没有开启
			if (this.getGoodsTypeId().equals("0") && this.getIsSpecificationsOpen().equals("0") || this.getIsSpecificationsOpen().equals("2")) {
				GoodsT gt = new GoodsT();
				gt.setGoodsid(this.getGoodsid());
				gt.setGoodsname(this.getGoodsname());
				gt.setBrandname(this.getBrandname().trim());
				gt.setModel(this.getModel());
				gt.setNname(this.getNname());
				gt.setLname(this.getLname());
				gt.setSname(this.getSname());
				gt.setFname(this.getFname());
				gt.setNavid(this.getNavid());
				gt.setLtypeid(this.getLtypeid());
				gt.setStypeid(this.getStypeid());
				gt.setPrice(Double.parseDouble(this.getPrice()));
				gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
				gt.setPoints(Double.parseDouble(this.getPoints()));
				gt.setPictureurl(this.getPictureurl());
				gt.setQuantity(Integer.parseInt(this.getQuantity()));
				gt.setSalestate(this.getSalestate());
				gt.setDetail(this.getDetail());
				gt.setUnitname(this.getUnitname());
				gt.setUnitnameid(this.getUnitnameid());
				gt.setKeywordname(this.getKeywordname());
				gt.setWeight(this.getWeight());
				gt.setReadcount(bean.getReadcount());
				gt.setRelatedproductid(bean.getRelatedproductid());
				gt.setRecommended(this.getRecommended());
				gt.setHotsale(this.getHotsale());
				gt.setBargainprice(this.getBargainprice());
				gt.setSort(bean.getSort());
				gt.setCreatetime(BaseTools.systemtime());
				gt.setCreatorid(BaseTools.adminCreateId());
				gt.setReplycount(bean.getReplycount());
				gt.setBrandid(this.getBrandid());
				gt.setPlaceStore(this.getPlaceStore());
				gt.setMetaKeywords(this.getMetaKeywords());
				gt.setMetaDescription(this.getMetaDescription());
				gt.setCost(Double.parseDouble(this.getCost()));
				gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
				gt.setIsNew(this.getIsNew());
				gt.setHtmlPath(bean.getHtmlPath());
				gt.setProductSn(this.getProductSn());
				gt.setGoodsParameterValue(bean.getGoodsParameterValue());
				gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
				gt.setKeywordid(this.getKeywordid());
				gt.setGoodsTypeId(this.getGoodsTypeId());
				gt.setGoodsTypeName(this.getGoodsTypeName());
				gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
				gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
				gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
				gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
				gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
				gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
				gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
				gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
				gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
				gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
				gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
				gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
				gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
				gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
				gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
				gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
				gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
				gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
				gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
				gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
				gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
				gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
				gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
				gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
				gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
				gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
				gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
				gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
				gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
				gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
				gt.setUsersetnum(this.getUsersetnum());
				gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
				gt.setStar(bean.getStar());
				gt.setStaruser(bean.getStaruser());
				gt.setTotalcomment(bean.getTotalcomment());
				gt.setVirtualsale(bean.getVirtualsale());
				gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
				if (this.getGoodsTServiceImpl().updateGoods(gt) > 0) {
					this.createGoodsT();
					map.put("goodsdetail", gt);
					String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
					this.updateHtmlPath(gt.getGoodsid(), htmlPath);
					this.setSucflag(true);
					return "json";
				} else {
					this.setSucflag(false);
					return "json";
				}
			} else {
				if (!this.getGoodsTypeId().equals("0") && this.getIsSpecificationsOpen().equals("0") || this.getIsSpecificationsOpen().equals("2")) {
					//增加属性不增加货品
					GoodsT gt = new GoodsT();
					gt.setGoodsid(this.getGoodsid());
					gt.setGoodsname(this.getGoodsname());
					gt.setBrandname(this.getBrandname().trim());
					gt.setModel(this.getModel());
					gt.setNname(this.getNname());
					gt.setLname(this.getLname());
					gt.setSname(this.getSname());
					gt.setFname(this.getFname());
					gt.setNavid(this.getNavid());
					gt.setLtypeid(this.getLtypeid());
					gt.setStypeid(this.getStypeid());
					gt.setPrice(Double.parseDouble(this.getPrice()));
					gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
					gt.setPoints(Double.parseDouble(this.getPoints()));
					gt.setPictureurl(this.getPictureurl());
					gt.setQuantity(Integer.parseInt(this.getQuantity()));
					gt.setSalestate(this.getSalestate());
					gt.setDetail(this.getDetail());
					gt.setUnitname(this.getUnitname());
					gt.setUnitnameid(this.getUnitnameid());
					gt.setKeywordname(this.getKeywordname());
					gt.setWeight(this.getWeight());
					gt.setReadcount(bean.getReadcount());
					gt.setRelatedproductid(bean.getRelatedproductid());
					gt.setRecommended(this.getRecommended());
					gt.setHotsale(this.getHotsale());
					gt.setBargainprice(this.getBargainprice());
					gt.setSort(bean.getSort());
					gt.setCreatetime(BaseTools.systemtime());
					gt.setCreatorid(BaseTools.adminCreateId());
					gt.setReplycount(bean.getReplycount());
					gt.setBrandid(this.getBrandid());
					gt.setPlaceStore(this.getPlaceStore());
					gt.setMetaKeywords(this.getMetaKeywords());
					gt.setMetaDescription(this.getMetaDescription());
					gt.setCost(Double.parseDouble(this.getCost()));
					gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
					gt.setIsNew(this.getIsNew());
					gt.setHtmlPath(bean.getHtmlPath());
					gt.setProductSn(this.getProductSn());
					gt.setGoodsParameterValue(this.getGoodsParameterValue());
					gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
					gt.setKeywordid(this.getKeywordid());
					gt.setGoodsTypeId(this.getGoodsTypeId());
					gt.setGoodsTypeName(this.getGoodsTypeName());
					gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
					gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
					gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
					gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
					gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
					gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
					gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
					gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
					gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
					gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
					gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
					gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
					gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
					gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
					gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
					gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
					gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
					gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
					gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
					gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
					gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
					gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
					gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
					gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
					gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
					gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
					gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
					gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
					gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
					gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
					gt.setUsersetnum(this.getUsersetnum());
					gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
					gt.setStar(bean.getStar());
					gt.setStaruser(bean.getStaruser());
					gt.setTotalcomment(bean.getTotalcomment());
					gt.setVirtualsale(bean.getVirtualsale());
					gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
					if (this.getGoodsTServiceImpl().updateGoods(gt) > 0) {
						this.setSucflag(true);
						this.createGoodsT();
						map.put("goodsdetail", gt);
						String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
						this.updateHtmlPath(gt.getGoodsid(), htmlPath);
						return "json";
					} else {
						this.setSucflag(false);
						return "json";
					}
				}
			}
			//规格开启属性未开启
			if (this.getIsSpecificationsOpen().equals("1") && this.getGoodsTypeId().equals("0")) {
				GoodsT gt = new GoodsT();
				gt.setGoodsid(this.getGoodsid());
				gt.setGoodsname(this.getGoodsname());
				gt.setBrandname(this.getBrandname().trim());
				gt.setModel(this.getModel());
				gt.setNname(this.getNname());
				gt.setLname(this.getLname());
				gt.setSname(this.getSname());
				gt.setFname(this.getFname());
				gt.setNavid(this.getNavid());
				gt.setLtypeid(this.getLtypeid());
				gt.setStypeid(this.getStypeid());
				gt.setPrice(Double.parseDouble(this.getPrice()));
				gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
				gt.setPoints(Double.parseDouble(this.getPoints()));
				gt.setPictureurl(this.getPictureurl());
				gt.setQuantity(Integer.parseInt(this.getQuantity()));
				gt.setSalestate(this.getSalestate());
				gt.setDetail(this.getDetail());
				gt.setUnitname(this.getUnitname());
				gt.setUnitnameid(this.getUnitnameid());
				gt.setKeywordname(this.getKeywordname());
				gt.setWeight(this.getWeight());
				gt.setReadcount(bean.getReadcount());
				gt.setRelatedproductid(bean.getRelatedproductid());
				gt.setRecommended(this.getRecommended());
				gt.setHotsale(this.getHotsale());
				gt.setBargainprice(this.getBargainprice());
				gt.setSort(bean.getSort());
				gt.setCreatetime(BaseTools.systemtime());
				gt.setCreatorid(BaseTools.adminCreateId());
				gt.setReplycount(bean.getReplycount());
				gt.setBrandid(this.getBrandid());
				gt.setPlaceStore(this.getPlaceStore());
				gt.setMetaKeywords(this.getMetaKeywords());
				gt.setMetaDescription(this.getMetaDescription());
				gt.setCost(Double.parseDouble(this.getCost()));
				gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
				gt.setIsNew(this.getIsNew());
				gt.setHtmlPath(bean.getHtmlPath());
				gt.setProductSn(this.getProductSn());
				gt.setGoodsParameterValue(bean.getGoodsParameterValue());
				gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
				gt.setKeywordid(this.getKeywordid());
				gt.setGoodsTypeId(this.getGoodsTypeId());
				gt.setGoodsTypeName(this.getGoodsTypeName());
				gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
				gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
				gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
				gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
				gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
				gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
				gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
				gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
				gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
				gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
				gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
				gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
				gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
				gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
				gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
				gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
				gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
				gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
				gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
				gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
				gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
				gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
				gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
				gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
				gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
				gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
				gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
				gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
				gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
				gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
				gt.setUsersetnum(this.getUsersetnum());
				gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
				gt.setStar(bean.getStar());
				gt.setStaruser(bean.getStaruser());
				gt.setTotalcomment(bean.getTotalcomment());
				gt.setVirtualsale(bean.getVirtualsale());
				gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
				if (this.getGoodsTServiceImpl().updateGoods(gt) > 0) {
					//此处要增加商品货品到货品表
					ProductT pt = new ProductT();
					String str[] = this.getRejson().split("-");
					String strsv[] = this.getSpecificationsValue().split("-");
					int count = 0;
					for (int i = 0; i < str.length; i++) {
						
						pt.setProductName(gt.getGoodsname());
						pt.setSpecificationsValue(strsv[i].toString());
						pt.setGoodsid(gt.getGoodsid());
						pt.setCreatorid(BaseTools.adminCreateId());
						pt.setCreatetime(BaseTools.systemtime());
						JSONObject jo = (JSONObject) JSONValue.parse(str[i].toString());
						Iterator it = jo.keySet().iterator();
						while (it.hasNext()) {
							String key = it.next().toString();
							if (key.equals("price")) {
								pt.setPrice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("memberprice")) {
								pt.setMemberprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("cost")) {
								pt.setCost(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("saleprice")) {
								pt.setSaleprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("freezeStore")) {
								pt.setFreezeStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("store")) {
								pt.setStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("isDefault")) {
								pt.setIsDefault(jo.get(key).toString());
							}
							if (key.equals("isSalestate")) {
								pt.setIsSalestate(jo.get(key).toString());
							}
							if (key.equals("productSn")) {
								pt.setProductSn(jo.get(key).toString());
							}
							if (key.equals("warehouseLocation")) {
								pt.setWarehouseLocation(jo.get(key).toString());
							}
							if (key.equals("placeStore")) {
								pt.setPlaceStore(jo.get(key).toString());
							}
							if (key.equals("weight")) {
								pt.setWeight(jo.get(key).toString());
							}
							if (key.equals("productid")) {
								pt.setProductid(jo.get(key).toString());
							}
						}
						List<ProductT> list = this.getProductTServiceImpl().findProductTByproductid(BaseTools.adminCreateId(), pt.getProductid());
						if (list != null) {
							if (this.getProductTServiceImpl().updateProductT(pt) > 0) {
								count++;
							}
						} else {
							pt.setProductid(this.getSerial().Serialid(Serial.PRODUCT));
							if (this.getProductTServiceImpl().addProductT(pt) > 0) {
								count++;
							}
						}
					}
					if (count == str.length) {
						this.createGoodsT();
						map.put("goodsdetail", gt);
						String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
						this.updateHtmlPath(gt.getGoodsid(), htmlPath);
						this.setSucflag(true);
						return "json";
					} else {
						this.setSucflag(false);
						return "json";
					}

				} else {
					this.setSucflag(false);
					return "json";
				}
			}

			//规格和属性都开启
			if (this.getIsSpecificationsOpen().equals("1") && !this.getGoodsTypeId().equals("0")) {
				GoodsT gt = new GoodsT();
				gt.setGoodsid(this.getGoodsid());
				gt.setGoodsname(this.getGoodsname());
				gt.setBrandname(this.getBrandname().trim());
				gt.setModel(this.getModel());
				gt.setNname(this.getNname());
				gt.setLname(this.getLname());
				gt.setSname(this.getSname());
				gt.setFname(this.getFname());
				gt.setNavid(this.getNavid());
				gt.setLtypeid(this.getLtypeid());
				gt.setStypeid(this.getStypeid());
				gt.setPrice(Double.parseDouble(this.getPrice()));
				gt.setMemberprice(Double.parseDouble(this.getMemberprice()));
				gt.setPoints(Double.parseDouble(this.getPoints()));
				gt.setPictureurl(this.getPictureurl());
				gt.setQuantity(Integer.parseInt(this.getQuantity()));
				gt.setSalestate(this.getSalestate());
				gt.setDetail(this.getDetail());
				gt.setUnitname(this.getUnitname());
				gt.setUnitnameid(this.getUnitnameid());
				gt.setKeywordname(this.getKeywordname());
				gt.setWeight(this.getWeight());
				gt.setReadcount(bean.getReadcount());
				gt.setRelatedproductid(bean.getRelatedproductid());
				gt.setRecommended(this.getRecommended());
				gt.setHotsale(this.getHotsale());
				gt.setBargainprice(this.getBargainprice());
				gt.setSort(bean.getSort());
				gt.setCreatetime(BaseTools.systemtime());
				gt.setCreatorid(BaseTools.adminCreateId());
				gt.setReplycount(bean.getReplycount());
				gt.setBrandid(this.getBrandid());
				gt.setPlaceStore(this.getPlaceStore());
				gt.setMetaKeywords(this.getMetaKeywords());
				gt.setMetaDescription(this.getMetaDescription());
				gt.setCost(Double.parseDouble(this.getCost()));
				gt.setSaleprice(Double.parseDouble(this.getSaleprice()));
				gt.setIsNew(this.getIsNew());
				gt.setHtmlPath(bean.getHtmlPath());
				gt.setProductSn(this.getProductSn());
				gt.setGoodsParameterValue(this.getGoodsParameterValue());
				gt.setFreezeStore(Integer.parseInt(this.getFreezeStore()));
				gt.setKeywordid(this.getKeywordid());
				gt.setGoodsTypeId(this.getGoodsTypeId());
				gt.setGoodsTypeName(this.getGoodsTypeName());
				gt.setGoodsAttrVal0(this.getGoodsAttrVal0());
				gt.setGoodsAttrVal1(this.getGoodsAttrVal1());
				gt.setGoodsAttrVal2(this.getGoodsAttrVal2());
				gt.setGoodsAttrVal3(this.getGoodsAttrVal3());
				gt.setGoodsAttrVal4(this.getGoodsAttrVal4());
				gt.setGoodsAttrVal5(this.getGoodsAttrVal5());
				gt.setGoodsAttrVal6(this.getGoodsAttrVal6());
				gt.setGoodsAttrVal7(this.getGoodsAttrVal7());
				gt.setGoodsAttrVal8(this.getGoodsAttrVal8());
				gt.setGoodsAttrVal9(this.getGoodsAttrVal9());
				gt.setGoodsAttrVal10(this.getGoodsAttrVal10());
				gt.setGoodsAttrVal11(this.getGoodsAttrVal11());
				gt.setGoodsAttrVal12(this.getGoodsAttrVal12());
				gt.setGoodsAttrVal13(this.getGoodsAttrVal13());
				gt.setGoodsAttrVal14(this.getGoodsAttrVal14());
				gt.setGoodsAttrVal15(this.getGoodsAttrVal15());
				gt.setGoodsAttrVal16(this.getGoodsAttrVal16());
				gt.setGoodsAttrVal17(this.getGoodsAttrVal17());
				gt.setGoodsAttrVal18(this.getGoodsAttrVal18());
				gt.setGoodsAttrVal19(this.getGoodsAttrVal19());
				gt.setGoodsAttrVal20(this.getGoodsAttrVal20());
				gt.setGoodsAttrVal21(this.getGoodsAttrVal21());
				gt.setGoodsAttrVal22(this.getGoodsAttrVal22());
				gt.setGoodsAttrVal23(this.getGoodsAttrVal23());
				gt.setGoodsAttrVal24(this.getGoodsAttrVal24());
				gt.setGoodsAttrVal25(this.getGoodsAttrVal25());
				gt.setGoodsAttrVal26(this.getGoodsAttrVal26());
				gt.setGoodsAttrVal27(this.getGoodsAttrVal27());
				gt.setGoodsAttrVal28(this.getGoodsAttrVal28());
				gt.setGoodsAttrVal29(this.getGoodsAttrVal29());
				gt.setUsersetnum(this.getUsersetnum());
				gt.setIsSpecificationsOpen(this.getIsSpecificationsOpen());
				gt.setStar(bean.getStar());
				gt.setStaruser(bean.getStaruser());
				gt.setTotalcomment(bean.getTotalcomment());
				gt.setVirtualsale(bean.getVirtualsale());
				gt.setIsmobileplatformgoods(this.getIsmobileplatformgoods());
				if (this.getGoodsTServiceImpl().updateGoods(gt) > 0) {
					//此处要增加商品货品到货品表
					ProductT pt = new ProductT();
					String str[] = this.getRejson().split("-");
					String strsv[] = this.getSpecificationsValue().split("-");
					int count = 0;
					for (int i = 0; i < str.length; i++) {
						pt.setProductName(gt.getGoodsname());
						pt.setSpecificationsValue(strsv[i].toString());
						pt.setGoodsid(gt.getGoodsid());
						pt.setCreatorid(BaseTools.adminCreateId());
						pt.setCreatetime(BaseTools.systemtime());
						JSONObject jo = (JSONObject) JSONValue.parse(str[i].toString());
						Iterator it = jo.keySet().iterator();
						while (it.hasNext()) {
							String key = it.next().toString();
							if (key.equals("price")) {
								pt.setPrice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("memberprice")) {
								pt.setMemberprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("cost")) {
								pt.setCost(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("saleprice")) {
								pt.setSaleprice(Double.parseDouble(jo.get(key).toString()));
							}
							if (key.equals("freezeStore")) {
								pt.setFreezeStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("store")) {
								pt.setStore(Integer.parseInt(jo.get(key).toString()));
							}
							if (key.equals("isDefault")) {
								pt.setIsDefault(jo.get(key).toString());
							}
							if (key.equals("isSalestate")) {
								pt.setIsSalestate(jo.get(key).toString());
							}
							if (key.equals("productSn")) {
								pt.setProductSn(jo.get(key).toString());
							}
							if (key.equals("warehouseLocation")) {
								pt.setWarehouseLocation(jo.get(key).toString());
							}
							if (key.equals("placeStore")) {
								pt.setPlaceStore(jo.get(key).toString());
							}
							if (key.equals("weight")) {
								pt.setWeight(jo.get(key).toString());
							}
							if (key.equals("productid")) {
								pt.setProductid(jo.get(key).toString());
							}
						}
						List<ProductT> list = this.getProductTServiceImpl().findProductTByproductid(BaseTools.adminCreateId(), pt.getProductid());
						if (list != null) {
							if (this.getProductTServiceImpl().updateProductT(pt) > 0) {
								count++;
							}
						} else {
							pt.setProductid(this.getSerial().Serialid(Serial.PRODUCT));
							if (this.getProductTServiceImpl().addProductT(pt) > 0) {
								count++;
							}
						}

					}
					if (count == str.length) {
						this.createGoodsT();
						map.put("goodsdetail", gt);
						String htmlPath = this.getCreateHtml().createGoodsT(BaseTools.getApplicationthemesig()+"_"+ContentTag.TEMPLATENAMEFORGOODSDETAIL, gt.getGoodsid(), map);
						this.updateHtmlPath(gt.getGoodsid(), htmlPath);
						this.setSucflag(true);
						return "json";
					} else {
						this.setSucflag(false);
						return "json";
					}

				} else {
					this.setSucflag(false);
					return "json";
				}
			}
		this.setSucflag(false);
		return "json";
	}

	/**
	 * 删除商品同时删除商品对应的货品
	 * 
	 * @return
	 */
	@Action(value = "delGoods", results = { 
			@Result(name = "json",type="json")
	})
	//判断是否上架
	public String delGoods() {
			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				for (int i = 0; i < strs.length; i++) {
					this.getProductTServiceImpl().delProductTBygoodsid(strs[i], BaseTools.adminCreateId());
				}
				int i = this.getGoodsTServiceImpl().delGoods(strs, BaseTools.adminCreateId());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}
	}

	/**
	 * 根据goodsid更新上下架状态
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsSaleState", results = { 
			@Result(name = "json",type="json")
	})
	public String updateGoodsSaleState() {

			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				int i = this.getGoodsTServiceImpl().updateGoodsSaleState(strs, this.getSalestate(), BaseTools.adminCreateId());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}
	}

	/**
	 * 根据goodsid更新特价商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsbargainprice", results = { 
			@Result(name = "json",type="json")
	})
	public String updateGoodsbargainprice() {
			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				int i = this.getGoodsTServiceImpl().updateGoodsbargainprice(strs, this.getBargainprice(), BaseTools.adminCreateId());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}

	}

	/**
	 * 根据goodsid更新热销商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodshotsale", results = { 
			@Result(name = "json",type="json")
	})
	public String updateGoodshotsale() {
			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				int i = this.getGoodsTServiceImpl().updateGoodshotsale(strs, this.getHotsale(), BaseTools.adminCreateId());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}
	}

	/**
	 * 根据goodsid更新推荐商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsrecommended", results = { 
			@Result(name = "json",type="json")
	})
	public String updateGoodsrecommended() {
			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				int i = this.getGoodsTServiceImpl().updateGoodsrecommended(strs, this.getRecommended(), BaseTools.adminCreateId());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}
	}

	/**
	 * 根据goodsid更新新品商品
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsisNew", results = { 
			@Result(name = "json",type="json")
	})
	public String updateGoodsisNew() {
			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				int i = this.getGoodsTServiceImpl().updateGoodsisNew(strs, this.getIsNew(), BaseTools.adminCreateId());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}
	}

	/**
	 * 根据商品id更新是否同步到移动平台
	 * 
	 * @return
	 */
	@Action(value = "updateGoodsismobileplatformgoods", results = { 
			@Result(name = "json",type="json")
	})
	public String updateGoodsismobileplatformgoods() {
			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				int i = this.getGoodsTServiceImpl().updateGoodsismobileplatformgoods(strs, this.getIsmobileplatformgoods(), BaseTools.adminCreateId());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}
	}

	/**
	 * 根据商品id更新5种商品状态
	 * 
	 * @return
	 */
	@Action(value = "updateFiveGoodsState", results = { 
			@Result(name = "json",type="json")
	})
	public String updateFiveGoodsState() {
	
			if (Validate.StrNotNull(this.getGoodsid())) {
				String[] strs = this.getGoodsid().split(",");
				int i = this.getGoodsTServiceImpl().updateFiveGoodsState(strs, this.getRecommended(), this.getHotsale(), this.getBargainprice(), this.getIsNew(), this.getIsmobileplatformgoods());
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(false);
				return "json";
			}
		
	}

//	/**
//	 * 一键同步商品图片
//	 * 需要重新修改逻辑
//	 * @return
//	 */
//	@Action(value = "OneKeyAddImgT", results = { 
//			@Result(name = "json",type="json")
//	})
//	public String OneKeyAddImgT() {
//			List<GoodsT> list = this.getGoodsTServiceImpl().findAllGoodsForImgT();
//			for (Iterator it = list.iterator(); it.hasNext();) {
//				GoodsT g = (GoodsT) it.next();
//				this.addImgValWhenEditGoods(g.getPictureurl(), g.getGoodsid(), g.getGoodsname());
//			}
//			this.setSucflag(true);
//			return "json";
//	}
//
//	public void addImgValWhenEditGoods(String Pictureurl, String goodsid, String goodsname) {
//		String[] pcarray = Pictureurl.split(",");
//		String newFilename = "";
//		String extName = "";
//		ImgT it = new ImgT();
//		for (int i = 0; i < pcarray.length; i++) {
//			if (pcarray[i].lastIndexOf(".") >= 0) {
//				extName = pcarray[i].substring(pcarray[i].lastIndexOf("."));
//				newFilename = pcarray[i];
//			}
//			it = this.getImgTAction().findImgTByImgName(newFilename);
//			if (it == null) {
//				//保存图片数据
//				Map<String, Object> imgVal = new HashMap<String, Object>();
//				imgVal.put("imgName", newFilename);
//				imgVal.put("imgType", extName);
//				imgVal.put("imgHref", "/Uploads/" + newFilename);
//				imgVal.put("usedGoodsid", goodsid);
//				imgVal.put("usedGoodsname", goodsname);
//				imgVal.put("state", "1");
//				this.getImgTAction().addImgTWhenEdit(imgVal);
//			}
//		}
//
//	}

	/**
	 * 增加星级评分
	 * 
	 * @return
	 */
	@Action(value = "updatestarsumBygoodsid", results = { 
			@Result(name = "json",type="json")
	})
	public String updatestarsumBygoodsid() {
			this.getGoodsTServiceImpl().updatestarsumBygoodsid(this.getGoodsid().trim(), Integer.parseInt(this.getStar()));
			this.setSucflag(true);
			return "json";
	}

	/**
	 * 更新商品总打分人数
	 * 
	 * @return
	 */
	@Action(value = "updatestarusersumBygoodsid", results = { 
			@Result(name = "json",type="json")
	})
	public String updatestarusersumBygoodsid() {
			this.getGoodsTServiceImpl().updatestarusersumBygoodsid(this.getGoodsid(), Integer.parseInt(this.getStaruser()));
			this.setSucflag(true);
			return "json";
	}

	/**
	 * 更新商品总评分人数
	 * 
	 * @return
	 */
	@Action(value = "updatecommentsumBygoodsid", results = { 
			@Result(name = "json",type="json")
	})
	public String updatecommentsumBygoodsid() {
			this.getGoodsTServiceImpl().updatecommentsumBygoodsid(this.getGoodsid(), Integer.parseInt(this.getTotalcomment()));
			this.setSucflag(true);
			return "json";
	}
}
