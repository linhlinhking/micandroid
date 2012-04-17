package com.jshop.action.front;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;
import com.jshop.action.templates.DataCollectionTAction;
import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.FreeMarkerController;
import com.jshop.action.tools.Serial;
import com.jshop.action.tools.Validate;
import com.jshop.entity.DeliverAddressT;
import com.jshop.entity.UserT;
import com.jshop.service.impl.DeliverAddressTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import freemarker.template.TemplateException;
@ParentPackage("jshop")
@Namespace("")
@InterceptorRefs({  
    @InterceptorRef("defaultStack")  
})
@Controller("deliverAddressAction")
public class DeliverAddressAction extends ActionSupport {
	@Resource(name="serial")
	private Serial serial;
	@Resource(name="deliverAddressTServiceImpl")
	private DeliverAddressTServiceImpl deliverAddressTServiceImpl;
	@Resource(name="dataCollectionTAction")
	private DataCollectionTAction dataCollectionTAction;
	
	private String addressid;
	private String userid;
	private String username;
	private String province;
	private String city;
	private String district;
	private String street;
	private String postcode;
	private String telno;
	private String mobile;
	private String email;
	private String state;
	private String country;

	private boolean sflag=false;
	private boolean slogin=false;
	
	private Map<String,Object>amap=new HashMap<String,Object>();
	
	@JSON(serialize=false) 
	public DataCollectionTAction getDataCollectionTAction() {
		return dataCollectionTAction;
	}

	public void setDataCollectionTAction(DataCollectionTAction dataCollectionTAction) {
		this.dataCollectionTAction = dataCollectionTAction;
	}

	@JSON(serialize=false) 
	public Serial getSerial() {
		return serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}
	@JSON(serialize=false) 
	public DeliverAddressTServiceImpl getDeliverAddressTServiceImpl() {
		return deliverAddressTServiceImpl;
	}

	public void setDeliverAddressTServiceImpl(DeliverAddressTServiceImpl deliverAddressTServiceImpl) {
		this.deliverAddressTServiceImpl = deliverAddressTServiceImpl;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isSflag() {
		return sflag;
	}

	public void setSflag(boolean sflag) {
		this.sflag = sflag;
	}
	
	public boolean isSlogin() {
		return slogin;
	}

	public void setSlogin(boolean slogin) {
		this.slogin = slogin;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Map<String, Object> getAmap() {
		return amap;
	}

	public void setAmap(Map<String, Object> amap) {
		this.amap = amap;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages(); 
	
	}
	

	
	/**
	 * 增加用户收获地址
	 * @return
	 */
	@Action(value = "addDeliveraddress", results = { 
			@Result(name = "json",type="json")
	})
	public String addDeliveraddress(){
		UserT user=(UserT)ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if(user!=null){
			DeliverAddressT d=new DeliverAddressT();
			d.setAddressid(this.getSerial().Serialid(Serial.DELIVERADDRESS));
			d.setUserid(user.getUserid());
			d.setUsername(this.getUsername().trim());
			d.setCountry(this.getCountry().trim());
			d.setProvince(this.getProvince().trim());
			d.setCity(this.getCity().trim());
			d.setDistrict(this.getDistrict().trim());
			d.setStreet(this.getStreet().trim());
			d.setPostcode(this.getPostcode().trim());
			d.setTelno(this.getTelno());
			d.setMobile(this.getMobile());
			d.setEmail(this.getEmail().trim());
			d.setCreatetime(BaseTools.systemtime());
			d.setState("0");
			if(this.getDeliverAddressTServiceImpl().addDeliverAddress(d)>0){
				this.setSflag(true);
				return "json";
			}else{
				this.setSflag(false);
				return "json";
			}
		}
		this.setSflag(true);
		return "json";
	}
	
	/**
	 * 获取用户收获地址
	 * @return
	 */
	@Action(value = "GetUserDeliverAddress", results = { 
			@Result(name = "success",type="chain",location = "InitOrder"),
			@Result(name = "input",type="redirect",location = "/html/default/shop/login.html")
	})
	public String GetUserDeliverAddress(){
		UserT user=(UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if(user!=null){
			List<DeliverAddressT> list=this.getDeliverAddressTServiceImpl().findDeliverAddressByuserid(user.getUserid());
			if(list!=null){
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("address", list);
				
				ActionContext.getContext().put("deliveraddress", map);
				return SUCCESS;
			}else{
				return SUCCESS;
			}
		}else{
			return INPUT;
		}
		
	}
	
	/**
	 *用户中心获取用户收货地址
	 * @return
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	@Action(value = "GetUserDeliverAddressForUserCenter", results = { 
			@Result(name = "success",type="freemarker",location = "WEB-INF/theme/default/shop/deliveraddress.ftl"),
			@Result(name = "input",type="redirect",location = "/html/default/shop/login.html")
	})
	public String GetUserDeliverAddressForUserCenter() throws TemplateException, IOException{
		UserT user=(UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if(user!=null){
			List<DeliverAddressT> list=this.getDeliverAddressTServiceImpl().findDeliverAddressByuserid(user.getUserid());
			if(!list.isEmpty()){
				//路径获取
				ActionContext.getContext().put("basePath", this.getDataCollectionTAction().getBasePath());
				//获取收货地址
				ActionContext.getContext().put("deliveraddress", list);
				//获取导航数据
				ActionContext.getContext().put("siteNavigationList", this.getDataCollectionTAction().findSiteNavigation());
				//获取商城基本数据
				ActionContext.getContext().put("jshopbasicinfo", this.getDataCollectionTAction().findJshopbasicInfo());
				//获取页脚分类数据
				ActionContext.getContext().put("footcategory", this.getDataCollectionTAction().findFooterCateogyrT());
				//获取页脚文章数据
				ActionContext.getContext().put("footerarticle", this.getDataCollectionTAction().findFooterArticle());
				return SUCCESS;
			}else{
				//路径获取
				ActionContext.getContext().put("basePath", this.getDataCollectionTAction().getBasePath());
				//获取收货地址
				ActionContext.getContext().put("deliveraddress", list);
				//获取导航数据
				ActionContext.getContext().put("siteNavigationList", this.getDataCollectionTAction().findSiteNavigation());
				//获取商城基本数据
				ActionContext.getContext().put("jshopbasicinfo", this.getDataCollectionTAction().findJshopbasicInfo());
				//获取页脚分类数据
				ActionContext.getContext().put("footcategory", this.getDataCollectionTAction().findFooterCateogyrT());
				//获取页脚文章数据
				ActionContext.getContext().put("footerarticle", this.getDataCollectionTAction().findFooterArticle());
				return SUCCESS;
			}
		}else{
			return INPUT;
		}
	}
	
	/**
	 *用户在用户中心删除收获地址
	 * @return
	 */
	@Action(value = "UserDelDeliverAddress", results = { 
			@Result(name = "success",type="chain",location = "GetUserDeliverAddressForUserCenter"),
			@Result(name = "input",type="redirect",location = "/html/default/shop/login.html")
	})
	public String UserDelDeliverAddress(){
		UserT user=(UserT)ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if(user!=null){
			if(Validate.StrNotNull(this.getAddressid())){
				String temp=this.getAddressid().trim()+",";
				String []tempid=temp.split(",");
				this.getDeliverAddressTServiceImpl().delDeliverAddress(tempid);
				return SUCCESS;
			}else{
				return INPUT;
			}
		}else{
			return INPUT;
		}
	
	}
	
}
