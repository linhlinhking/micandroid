package com.jshop.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;
import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.Serial;
import com.jshop.action.tools.Validate;
import com.jshop.entity.PageT;
import com.jshop.service.impl.PageTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("jshop")

@Controller("pageTAction")
public class PageTAction extends ActionSupport {
	@Resource(name="pageTServiceImpl")
	private PageTServiceImpl pageTServiceImpl;
	@Resource(name="serial")
	private Serial serial;
	
	private String pageid;
	private String pagename;
	private String pagenameen;
	private String url;
	private String canedit;
	private String rangearea;
	private String creatorid;
	private Date createtime;
	private PageT beanlist = new PageT();
	private List<PageT> paget = new ArrayList<PageT>();
	private List rows = new ArrayList();
	private int rp;
	private int page = 1;
	private int total = 0;
	private boolean slogin;
	@JSON(serialize = false)
	public PageTServiceImpl getPageTServiceImpl() {
		return pageTServiceImpl;
	}

	public void setPageTServiceImpl(PageTServiceImpl pageTServiceImpl) {
		this.pageTServiceImpl = pageTServiceImpl;
	}
	@JSON(serialize = false)
	public Serial getSerial() {
		return serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}

	public String getPageid() {
		return pageid;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getPagenameen() {
		return pagenameen;
	}

	public void setPagenameen(String pagenameen) {
		this.pagenameen = pagenameen;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRangearea() {
		return rangearea;
	}

	public void setRangearea(String rangearea) {
		this.rangearea = rangearea;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public PageT getBeanlist() {
		return beanlist;
	}

	public void setBeanlist(PageT beanlist) {
		this.beanlist = beanlist;
	}

	public List<PageT> getPaget() {
		return paget;
	}

	public void setPaget(List<PageT> paget) {
		this.paget = paget;
	}

	@JSON(name = "rows")
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

	public String getCanedit() {
		return canedit;
	}

	public void setCanedit(String canedit) {
		this.canedit = canedit;
	}

	public boolean isSlogin() {
		return slogin;
	}

	public void setSlogin(boolean slogin) {
		this.slogin = slogin;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}
	/**
	 * 验证登陆
	 * 
	 * @return
	 */
	public void CheckLogin() {
		String adminid = (String) ActionContext.getContext().getSession().get(BaseTools.BACK_USER_SESSION_KEY);
		if (adminid != null) {
			this.setCreatorid(adminid);
			this.setSlogin(false);
		} else {
			this.setSlogin(true);
		}
	}
	

	/**
	 * 增加页面
	 * 
	 * @return
	 */
	//	public String addPaget(){
	//		String adminid=(String)ActionContext.getContext().getSession().get("adminid");
	//		if(adminid!=null){
	//			String serialid=null;
	//			SerialT list=this.getSerialtserviceimpl().findBybaseid(biz);
	//			serialid=this.tagdate()+list.getHead()+list.getLastid();
	//			PageT pt=new PageT();
	//			pt.setPageid(serialid);
	//			pt.setPagename(this.getPagename().trim());
	//			pt.setPagenameen(this.getPagenameen().trim());
	//			pt.setRangearea(this.getRangearea());
	//			pt.setUrl(this.getUrl().trim());
	//			pt.setCanedit(this.getCanedit());
	//			pt.setCreatetime(this.systemtime());
	//			pt.setCreatorid(adminid);
	//			if(this.getPagetserviceimpl().addPaget(pt)>0){
	//				return Action.SUCCESS;
	//			}
	//			return Action.INPUT;
	//		}
	//		return Action.INPUT;
	//	}
	/**
	 *查询所有页面
	 * 
	 * @return
	 */
	//	public String findAllPaget(){
	//		int currentPage=page;
	//		int lineSize=rp;
	//		List<PageT>list=this.getPagetserviceimpl().findAllPaget(currentPage, lineSize);
	//		if(list!=null){
	//			total=this.getPagetserviceimpl().countfindAllPaget();
	//			rows.clear();
	//			for(Iterator it=list.iterator();it.hasNext();){
	//				PageT pt=(PageT)it.next();
	//				if("1".equals(pt.getRangearea())){
	//					pt.setRangearea("前台");
	//				}
	//				if("2".equals(pt.getRangearea())){
	//					pt.setRangearea("后台");
	//				}
	//				if("1".equals(pt.getCanedit())){
	//					pt.setCanedit("可以");
	//				}
	//				if("2".equals(pt.getCanedit())){
	//					pt.setCanedit("不可以");
	//				}
	//				Map cellMap=new HashMap();
	//				cellMap.put("id", pt.getPageid());
	//				cellMap.put("cell",new Object[]{
	//						"<input id='id' name='firstcol' class='firstpageid' type='checkbox' value='"+pt.getPageid()+"'></input>",
	//						pt.getPagename(),
	//						pt.getPagenameen(),
	//						pt.getUrl(),
	//						pt.getRangearea(),
	//						pt.getCanedit(),
	//						pt.getCreatetime(),
	//						pt.getCreatorid()
	//				});
	//				rows.add(cellMap);
	//			}
	//			return "json";
	//		}
	//		this.setTotal(0);
	//		rows.clear();
	//		return "json";
	//	}
	//	/**
	//	 * 更新页面
	//	 * @return
	//	 */
	//	public String UpdatePaget(){
	//		String adminid=(String)ActionContext.getContext().getSession().get("adminid");
	//		if(adminid!=null){
	//			PageT pt=new PageT();
	//			pt.setPageid(this.getPageid().trim());
	//			pt.setPagename(this.getPagename().trim());
	//			pt.setPagenameen(this.getPagenameen().trim());
	//			pt.setUrl(this.getUrl());
	//			pt.setCreatetime(this.systemtime());
	//			pt.setRangearea(this.getRangearea().trim());
	//			pt.setCanedit(this.getCanedit());
	//			pt.setCreatorid(adminid);
	//			if(this.getPagetserviceimpl().UpdatePaget(pt)>0){
	//				return "json";
	//			}
	//			return "json";
	//		}
	//		return "json";
	//		
	//	}
	/**
	 * 删除页面
	 * 
	 * @return
	 */
	@Action(value="DelPaget",results={
			@Result(name="json",type="json")
	})
	public String DelPaget() {
		this.CheckLogin();
		if(!this.isSlogin()){
			if (Validate.StrNotNull(this.getPageid())) {
				String[] strs = this.getPageid().trim().split(",");
				if (this.getPageTServiceImpl().delPaget(strs) > 0) {
					return "json";
				}
				return "json";
			}
			return "json";
		}else{
			return "json";
		}
		
	}

	/**
	 * 根据id获取页面
	 * 
	 * @return
	 */
	@Action(value="findPagetById",results={
			@Result(name="json",type="json")
	})
	public String findPagetById() {
		this.CheckLogin();
		if(!this.isSlogin()){
			if (Validate.StrNotNull(this.getPageid())) {
				beanlist = this.getPageTServiceImpl().findPagetById(this.getPageid().trim());
				if (beanlist != null) {
					return "json";
				}
			}
			return "json";
		}else{
			return "json";
		}
	
	}

	/**
	 * 查询所有页面信息用于页面编辑区域
	 * 
	 * @return
	 */
	@Action(value = "findAllPagetforPageEdit", results = { 
			@Result(name="json",type="json")
	})
	public String findAllPagetforPageEdit() {
		this.CheckLogin();
		if(!this.isSlogin()){
			paget = this.getPageTServiceImpl().findAllPagetforPageEdit();
			if (paget != null) {
				return "json";
			}
			return "json";
		}
		return "json";
	}
}
