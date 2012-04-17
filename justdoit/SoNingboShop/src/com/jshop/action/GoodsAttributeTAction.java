package com.jshop.action;

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
import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.Serial;
import com.jshop.action.tools.Validate;
import com.jshop.entity.GoodsAttributeT;
import com.jshop.service.impl.GoodsAttributeTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("jshop")
@Controller("goodsAttributeTAction")
public class GoodsAttributeTAction extends ActionSupport {
	@Resource(name = "goodsAttributeTServiceImpl")
	private GoodsAttributeTServiceImpl goodsAttributeTServiceImpl;
	@Resource(name = "serial")
	private Serial serial;
	private String goodsattributeid;
	private String goodsattributename;
	private String goodsTypeId;
	private String goodsTypeName;
	private Date createtime;
	private String state;
	private String creatorid;
	private String attributeType;
	private String attributelist;
	private String sort;
	private String attributeIndex;
	private String rjson;
	private GoodsAttributeT bean;
	private List<GoodsAttributeT> gatbeanlist;
	private List beanlist;
	private String query;
	private String qtype;
	private List rows;
	private int rp;
	private int page = 1;
	private int total = 0;
	private boolean slogin;
	private boolean sucflag;

	public GoodsAttributeTAction() {
		bean = new GoodsAttributeT();
		gatbeanlist = new ArrayList<GoodsAttributeT>();
		beanlist = new ArrayList();
		rows = new ArrayList();
	}

	@JSON(serialize = false)
	public GoodsAttributeTServiceImpl getGoodsAttributeTServiceImpl() {
		return goodsAttributeTServiceImpl;
	}

	public void setGoodsAttributeTServiceImpl(GoodsAttributeTServiceImpl goodsAttributeTServiceImpl) {
		this.goodsAttributeTServiceImpl = goodsAttributeTServiceImpl;
	}

	@JSON(serialize = false)
	public Serial getSerial() {
		return serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}

	public String getGoodsattributeid() {
		return goodsattributeid;
	}

	public void setGoodsattributeid(String goodsattributeid) {
		this.goodsattributeid = goodsattributeid;
	}

	public String getGoodsattributename() {
		return goodsattributename;
	}

	public void setGoodsattributename(String goodsattributename) {
		this.goodsattributename = goodsattributename;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	public String getAttributelist() {
		return attributelist;
	}

	public void setAttributelist(String attributelist) {
		this.attributelist = attributelist;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAttributeIndex() {
		return attributeIndex;
	}

	public void setAttributeIndex(String attributeIndex) {
		this.attributeIndex = attributeIndex;
	}

	public GoodsAttributeT getBean() {
		return bean;
	}

	public void setBean(GoodsAttributeT bean) {
		this.bean = bean;
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

	public String getRjson() {
		return rjson;
	}

	public void setRjson(String rjson) {
		this.rjson = rjson;
	}

	public List getBeanlist() {
		return beanlist;
	}

	public void setBeanlist(List beanlist) {
		this.beanlist = beanlist;
	}

	public List<GoodsAttributeT> getGatbeanlist() {
		return gatbeanlist;
	}

	public void setGatbeanlist(List<GoodsAttributeT> gatbeanlist) {
		this.gatbeanlist = gatbeanlist;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}

	/**
	 * 增加商品参数
	 */
	@Action(value = "addGoodsAttributeT", results = { @Result(name = "json", type = "json") })
	public String addGoodsAttributeT() {
		String a[] = this.getRjson().split("-");
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			GoodsAttributeT gat = new GoodsAttributeT();
			gat.setGoodsattributeid(this.getSerial().Serialid(Serial.GOODSATTRIBUTE));
			gat.setCreatetime(BaseTools.systemtime());
			gat.setState("1");
			gat.setCreatorid(BaseTools.adminCreateId());
			gat.setGoodsTypeId(this.getGoodsTypeId());
			gat.setGoodsTypeName(this.getGoodsTypeName());
			gat.setAttributeIndex(this.getAttributeIndex());
			JSONObject jo = (JSONObject) JSONValue.parse(a[i].toString());
			Iterator iter = jo.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next().toString();
				if (key.equals("goodsattributename")) {
					gat.setGoodsattributename(jo.get(key).toString());
				}
				if (key.equals("attributeType")) {
					gat.setAttributeType(jo.get(key).toString());
				}
				if (key.equals("attributelist")) {
					gat.setAttributelist(jo.get(key).toString());
				}
				if (key.equals("sort")) {
					gat.setSort(jo.get(key).toString());
				}
			}
			if (this.getGoodsAttributeTServiceImpl().addGoodsAttributeT(gat) > 0) {
				count++;
			}
		}
		if (count == a.length) {
			this.setSucflag(true);
			return "json";
		}
		this.setSucflag(false);
		return "json";
	}

	/**
	 * 更新商品参数
	 * 
	 * @return
	 */
	@Action(value = "UpdateGoodsAttributeT", results = { @Result(name = "json", type = "json") })
	public String UpdateGoodsAttributeT() {
		String a[] = this.getRjson().split("-");
		int count = 0;
		GoodsAttributeT gat = new GoodsAttributeT();
		for (int i = 0; i < a.length; i++) {
			gat.setCreatetime(BaseTools.systemtime());
			gat.setState("1");
			gat.setCreatorid(BaseTools.adminCreateId());
			gat.setGoodsTypeId(this.getGoodsTypeId());
			gat.setGoodsTypeName(this.getGoodsTypeName());
			gat.setAttributeIndex(this.getAttributeIndex());
			JSONObject jo = (JSONObject) JSONValue.parse(a[i].toString());
			Iterator iter = jo.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next().toString();
				if (key.equals("goodsattributename")) {
					gat.setGoodsattributename(jo.get(key).toString());
				}
				if (key.equals("attributeType")) {
					gat.setAttributeType(jo.get(key).toString());
				}
				if (key.equals("attributelist")) {
					gat.setAttributelist(jo.get(key).toString());
				}
				if (key.equals("sort")) {
					gat.setSort(jo.get(key).toString());
				}
				if (key.equals("goodsattributeid")) {
					gat.setGoodsattributeid(jo.get(key).toString());
				}
			}
			int j = this.getGoodsAttributeTServiceImpl().updateGoodsAttributeT(gat);
			count++;

		}
		if (count == a.length) {
			this.setSucflag(true);
			return "json";
		}
		this.setSucflag(false);
		return "json";
	}

	/**
	 * 获取所有商品参数
	 */
	@Action(value = "findAllGoodsAttributeT", results = { @Result(name = "json", type = "json") })
	public String findAllGoodsAttributeT() {

		if (this.getQtype().equals("sc")) {
			this.setTotal(0);
			rows.clear();
			this.findDefaultAllGoodsAttributeT();
		} else {
			if (Validate.StrisNull(this.getQuery())) {
				return "json";
			} else {
				return "json";
			}
		}
		return "json";

	}

	public void findDefaultAllGoodsAttributeT() {
		int currentPage = page;
		int lineSize = rp;
		total = this.getGoodsAttributeTServiceImpl().countfindAllGoodsAttributeT();
		List<GoodsAttributeT> list = this.getGoodsAttributeTServiceImpl().findAllGoodsAttributeT(currentPage, lineSize);
		if (list != null) {
			this.ProcessGoodsAttributeTList(list);
		}
	}

	public void ProcessGoodsAttributeTList(List<GoodsAttributeT> list) {
		rows.clear();
		for (Iterator it = list.iterator(); it.hasNext();) {
			GoodsAttributeT gat = (GoodsAttributeT) it.next();
			Map<String, Object> cellMap = new HashMap<String, Object>();
			cellMap.put("id", gat.getGoodsattributeid());
			cellMap.put("cell", new Object[] {gat.getGoodsattributename(), gat.getAttributeType(), gat.getAttributelist(), gat.getSort(), gat.getGoodsTypeName(), BaseTools.formateDbDate(gat.getCreatetime()) });
			rows.add(cellMap);
		}
	}

	/**
	 * 删除商品参数
	 * 
	 * @return
	 */
	@Action(value = "DelGoodsAttributeT", results = { @Result(name = "json", type = "json") })
	public String DelGoodsAttributeT() {

		if (Validate.StrNotNull(this.getGoodsattributeid())) {
			String[] list = this.getGoodsattributeid().split(",");
			int i = this.getGoodsAttributeTServiceImpl().delGoodsAttributeT(list);
			this.setSucflag(true);
			return "json";
		}
		this.setSucflag(false);
		return "json";
	}

	/**
	 * 根据商品类型来获取对应的商品参数
	 * 
	 * @return
	 */
	@Action(value = "findGoodsAttributeTByGoodsTypeName", results = { @Result(name = "json", type = "json") })
	public String findGoodsAttributeTByGoodsTypeName() {

		if (Validate.StrNotNull(this.getGoodsTypeName())) {
			List<GoodsAttributeT> list = this.getGoodsAttributeTServiceImpl().findGoodsAttributeTByGoodsTypeName(this.getGoodsTypeName().trim());
			if (list != null) {
				beanlist = list;
				this.setSucflag(true);
				return "json";
			}
		}
		this.setSucflag(false);
		return "json";
	}

	/**
	 * 根据商品类型id获取商品属性参数
	 * 
	 * @return
	 */
	@Action(value = "findGoodsAttributeTBygoodsTypeId", results = { @Result(name = "json", type = "json") })
	public String findGoodsAttributeTBygoodsTypeId() {

		if (Validate.StrNotNull(this.getGoodsTypeId())) {
			List<GoodsAttributeT> list = this.getGoodsAttributeTServiceImpl().findGoodsAttributeTBygoodsTypeId(this.getGoodsTypeId());
			if (list != null) {
				gatbeanlist = list;
				this.setSucflag(true);
				return "json";
			} else {
				this.setSucflag(true);
				return "json";
			}
		}
		this.setSucflag(true);
		return "json";

	}

}
