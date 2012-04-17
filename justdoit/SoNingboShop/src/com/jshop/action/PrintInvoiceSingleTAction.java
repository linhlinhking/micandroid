package com.jshop.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.PrintInvoiceParam;
import com.jshop.action.tools.Validate;
import com.jshop.entity.CartT;
import com.jshop.entity.InvoicetempleteT;
import com.jshop.entity.OrderT;
import com.jshop.entity.ShippingAddressT;
import com.jshop.service.impl.CartTServiceImpl;
import com.jshop.service.impl.InvoicetempleteTServiceImpl;
import com.jshop.service.impl.OrderTServiceImpl;
import com.jshop.service.impl.ShippingAddressTServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("jshop")

@Controller("printInvoiceSingleTAction")
public class PrintInvoiceSingleTAction extends ActionSupport {
	@Resource(name="orderTServiceImpl")
	private OrderTServiceImpl orderTServiceImpl;
	@Resource(name="cartTServiceImpl")
	private CartTServiceImpl cartTServiceImpl;
	@Resource(name="shippingAddressTServiceImpl")
	private ShippingAddressTServiceImpl shippingAddressTServiceImpl;
	@Resource(name="invoicetempleteTServiceImpl")
	private InvoicetempleteTServiceImpl invoicetempleteTServiceImpl;
	private String orderid;
	private PrintInvoiceParam pi = new PrintInvoiceParam();
	private List<CartT> ct = new ArrayList<CartT>();
	private List invoicerows = new ArrayList();
	private boolean slogin = false;
	private boolean sprintinvoiceflag = false;

	
	@JSON(serialize = false)
	public OrderTServiceImpl getOrderTServiceImpl() {
		return orderTServiceImpl;
	}

	public void setOrderTServiceImpl(OrderTServiceImpl orderTServiceImpl) {
		this.orderTServiceImpl = orderTServiceImpl;
	}
	@JSON(serialize = false)
	public CartTServiceImpl getCartTServiceImpl() {
		return cartTServiceImpl;
	}

	public void setCartTServiceImpl(CartTServiceImpl cartTServiceImpl) {
		this.cartTServiceImpl = cartTServiceImpl;
	}
	@JSON(serialize = false)
	public ShippingAddressTServiceImpl getShippingAddressTServiceImpl() {
		return shippingAddressTServiceImpl;
	}

	public void setShippingAddressTServiceImpl(ShippingAddressTServiceImpl shippingAddressTServiceImpl) {
		this.shippingAddressTServiceImpl = shippingAddressTServiceImpl;
	}
	@JSON(serialize = false)
	public InvoicetempleteTServiceImpl getInvoicetempleteTServiceImpl() {
		return invoicetempleteTServiceImpl;
	}

	public void setInvoicetempleteTServiceImpl(InvoicetempleteTServiceImpl invoicetempleteTServiceImpl) {
		this.invoicetempleteTServiceImpl = invoicetempleteTServiceImpl;
	}

	public List<CartT> getCt() {
		return ct;
	}

	public void setCt(List<CartT> ct) {
		this.ct = ct;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public PrintInvoiceParam getPi() {
		return pi;
	}

	public void setPi(PrintInvoiceParam pi) {
		this.pi = pi;
	}

	public boolean isSlogin() {
		return slogin;
	}

	public void setSlogin(boolean slogin) {
		this.slogin = slogin;
	}

	public boolean isSprintinvoiceflag() {
		return sprintinvoiceflag;
	}

	public void setSprintinvoiceflag(boolean sprintinvoiceflag) {
		this.sprintinvoiceflag = sprintinvoiceflag;
	}

	public List getInvoicerows() {
		return invoicerows;
	}

	public void setInvoicerows(List invoicerows) {
		this.invoicerows = invoicerows;
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
			this.setSlogin(false);
		} else {
			this.setSlogin(true);
		}

	}
	/**
	 * 显示是否开具发票了
	 * 
	 * @return
	 */
	private String GetChangeHasprintfpinvoice(String hasprintfpinvoice) {
		String temp;
		if (hasprintfpinvoice.equals("1")) {
			temp = "是";
		} else {
			temp = "否";
		}
		return temp;
	}

	@SuppressWarnings("deprecation")
	private void GetChangeTime(Date t) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-yy-ss");
		String time = formatter.format(t.getTime()).toString();
		pi.setPurchasetime(time);
	}

	

	/**
	 * 获取订单发货地址
	 * 
	 * @param orderid
	 */
	public void GetOrderShippingAddress(String orderid) {
		ShippingAddressT st = this.getShippingAddressTServiceImpl().findShippingAddressByOrderid(orderid, "1");
		if (st != null) {
			pi.setShippingusername(st.getUsername());
			pi.setCountry(st.getCountry());
			pi.setProvince(st.getProvince());
			pi.setCity(st.getCity());
			pi.setDistrict(st.getDistrict());
			pi.setStreet(st.getStreet());
			pi.setPostcode(st.getPostcode());
		}
	}

	/**
	 * 根据id获取订单详细
	 * 
	 * @param orderid
	 */
	public void GetOrderDetailByOrderId(String orderid) {
		OrderT o = this.getOrderTServiceImpl().findOrderDetailByorderid(orderid);
		if (o != null) {
			GetChangeTime(Date.valueOf(o.getPurchasetime().toString()));
			pi.setOrderid(o.getOrderid());
			pi.setHasprintfpinvoice(GetChangeHasprintfpinvoice(o.getHasprintfpinvoice()));
			pi.setAmount(String.valueOf(o.getAmount()));
			pi.setShouldpay(String.valueOf(o.getShouldpay()));
			pi.setFreight(String.valueOf(o.getFreight()));
			pi.setInvoicenumber(o.getDeliverynumber());//发货单号
		}
	}

	/**
	 *根据订单id获取购物车中商品数据
	 */
	public void findCartGoodsByOrderid(String orderid) {
		List<CartT> ct = this.getCartTServiceImpl().findCartGoodsByOrderid(orderid);
		if (ct != null) {
			invoicerows.clear();
			for (Iterator it = ct.iterator(); it.hasNext();) {
				CartT c = (CartT) it.next();
				Map<String, Object> cellMap = new HashMap<String, Object>();
				cellMap.put("goodsname", c.getGoodsname());
				cellMap.put("usersetnum", c.getUsersetnum());
				cellMap.put("favorable", c.getFavorable());
				cellMap.put("needquantity", c.getNeedquantity());
				cellMap.put("subtotal", c.getSubtotal());
				invoicerows.add(cellMap);
			}

		}
	}

	/**
	 * 读取发货单模板
	 */
	public void GetPrintInvoiceValue() {
		InvoicetempleteT it = this.getInvoicetempleteTServiceImpl().findInvoicetempleteByState("1");
		pi.setKindeditorCode(it.getKindeditorCode());
	}

	/**
	 * 初始化发货单打印
	 * 
	 * @return
	 */
	@Action(value = "InitPrintInvoice", results = { 
			@Result(name = "json",type="json")
	})
	public String InitPrintInvoice() {
		this.CheckLogin();
		if(!this.isSlogin()){
			if (Validate.StrNotNull(this.getOrderid())) {
				String orderid = this.getOrderid().trim();
				GetOrderDetailByOrderId(orderid);
				GetOrderShippingAddress(orderid);
				findCartGoodsByOrderid(orderid);
				GetPrintInvoiceValue();
				this.setSprintinvoiceflag(true);
				return "json";
			}
			this.setSprintinvoiceflag(false);
			return "json";
		}else{
			this.setSprintinvoiceflag(false);
			return "json";
		}

	}
}
