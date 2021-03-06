package com.jshop.action;

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
import org.springframework.stereotype.Controller;
import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.Serial;
import com.jshop.action.tools.Validate;
import com.jshop.entity.GoodsunitT;
import com.jshop.service.impl.GoodsunitTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("jshop")
@Controller("goodsunitTAction")
public class GoodsunitTAction extends ActionSupport {
	@Resource(name = "goodsunitTServiceImpl")
	private GoodsunitTServiceImpl goodsunitTServiceImpl;
	@Resource(name = "serial")
	private Serial serial;
	private String unitid;
	private String unitname;
	private String creatorid;
	private String unitjson;
	private List<GoodsunitT> unit = new ArrayList<GoodsunitT>();
	private GoodsunitT beanlist = new GoodsunitT();
	private List rows = new ArrayList();
	private int rp;
	private int page = 1;
	private int total = 0;
	private String sortname;
	private String sortorder;
	private boolean slogin;
	private String param;
	private String usession;

	

	@JSON(serialize = false)
	public GoodsunitTServiceImpl getGoodsunitTServiceImpl() {
		return goodsunitTServiceImpl;
	}

	public void setGoodsunitTServiceImpl(GoodsunitTServiceImpl goodsunitTServiceImpl) {
		this.goodsunitTServiceImpl = goodsunitTServiceImpl;
	}

	@JSON(serialize = false)
	public Serial getSerial() {
		return serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public GoodsunitT getBeanlist() {
		return beanlist;
	}

	public void setBeanlist(GoodsunitT beanlist) {
		this.beanlist = beanlist;
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

	public String getUnitjson() {
		return unitjson;
	}

	public void setUnitjson(String unitjson) {
		this.unitjson = unitjson;
	}

	public List<GoodsunitT> getUnit() {
		return unit;
	}

	public void setUnit(List<GoodsunitT> unit) {
		this.unit = unit;
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

	public String getUsession() {
		return usession;
	}

	public void setUsession(String usession) {
		this.usession = usession;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public boolean isSlogin() {
		return slogin;
	}

	public void setSlogin(boolean slogin) {
		this.slogin = slogin;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}

	/**
	 * 增加商品单位
	 * 
	 * @return
	 */
	@Action(value = "addGoodsunit", results = { @Result(name = "success", type = "redirect", location = "/jshop/admin/goods/goodsunitmanagement.jsp?session=${usession}"), @Result(name = "input", type = "redirect", location = "/jshop/admin/goods/goodsunitmanagement.jsp?session=${usession}"), @Result(name = "error", type = "redirect", location = "/jshop/admin/goods/goodsunitmanagement.jsp?session=${usession}") })
	public String addGoodsunit() {
		GoodsunitT u = new GoodsunitT();
		u.setUnitid(this.getSerial().Serialid(Serial.UNIT));
		u.setUnitname(this.getUnitname().trim());
		u.setCreatorid(BaseTools.adminCreateId());
		u.setCreatetime(BaseTools.systemtime());
		if (this.getGoodsunitTServiceImpl().addGoodsunit(u) > 0) {
			return SUCCESS;
		}
		return INPUT;
	}

	/**
	 * 获取所有单位数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "findAllGoodsunit", results = { @Result(name = "json", type = "json") })
	public String findAllGoodsunit() {
		int currentPage = page;
		int lineSize = rp;
		String queryString = "from GoodsunitT order by " + sortname + " " + sortorder + "";
		if (Validate.StrNotNull(sortname) && Validate.StrNotNull(sortorder)) {
			List<GoodsunitT> gt = this.getGoodsunitTServiceImpl().sortAllGoodsunit(currentPage, lineSize, queryString);
			if (gt != null) {
				total = this.getGoodsunitTServiceImpl().countfindAllGoodsunit();
				rows.clear();
				for (Iterator it = gt.iterator(); it.hasNext();) {
					GoodsunitT u = (GoodsunitT) it.next();
					Map cellMap = new HashMap();
					cellMap.put("id", u.getUnitid());
					cellMap.put("cell", new Object[] { u.getUnitname(), BaseTools.formateDbDate(u.getCreatetime()), u.getCreatorid() });
					rows.add(cellMap);
				}
				return "json";
			}
		}
		this.setTotal(0);
		rows.clear();
		return "json";
	}

	/**
	 *更新单位
	 * 
	 * @return
	 */
	@Action(value = "UpdateGoodsunit", results = { @Result(name = "json", type = "json") })
	public String UpdateGoodsunit() {
		if (Validate.StrNotNull(this.getUnitname())) {
			GoodsunitT u = new GoodsunitT();
			u.setUnitid(this.getUnitid().trim());
			u.setUnitname(this.getUnitname().trim());
			u.setCreatorid(BaseTools.adminCreateId());
			u.setCreatetime(BaseTools.systemtime());
			this.getGoodsunitTServiceImpl().updateGoodsunit(u);
			return "json";
		}
		return "json";
	}

	/**
	 * 根据id获取单位
	 * 
	 * @return
	 */
	@Action(value = "findGoodsunitById", results = { @Result(name = "json", type = "json") })
	public String findGoodsunitById() {
		if (Validate.StrNotNull(this.getUnitid())) {
			beanlist = this.getGoodsunitTServiceImpl().findGoodsunitById(this.getUnitid().trim());
			if (beanlist != null) {
				return "json";
			}
		}
		return "json";
	}

	/**
	 * 删除单位
	 * 
	 * @return
	 */
	@Action(value = "DelGoodsunit", results = { @Result(name = "json", type = "json") })
	public String DelGoodsunit() {
		if (Validate.StrNotNull(this.getUnitid())) {
			String[] array = this.getUnitid().trim().split(",");
			if (this.getGoodsunitTServiceImpl().delGoodsunit(array) > 0) {
				return "json";
			}
			return "json";
		}
		return "json";
	}

	/**
	 * 获取所有单位数据json
	 * 
	 * @return
	 */
	@Action(value = "findAllGoodsunitjson", results = { @Result(name = "json", type = "json") })
	public String findAllGoodsunitjson() {
		this.setUnitjson("");
		this.unit = this.getGoodsunitTServiceImpl().findAllGoodsunitjson();
		if (unit != null) {
			for (Iterator it = this.unit.iterator(); it.hasNext();) {
				GoodsunitT gt = (GoodsunitT) it.next();
				this.unitjson += "<option value='" + gt.getUnitid() + "'>" + gt.getUnitname() + "</option>";
			}
			this.setUnitjson(unitjson);
			return "json";
		}
		return "json";
	}
}
