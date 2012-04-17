package com.jshop.action.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.jshop.action.tools.Arith;
import com.jshop.action.tools.BaseTools;
import com.jshop.action.tools.Serial;
import com.jshop.action.tools.Validate;
import com.jshop.entity.CartT;
import com.jshop.entity.GoodsT;
import com.jshop.entity.UserT;
import com.jshop.service.impl.CartTServiceImpl;
import com.jshop.service.impl.GoodsTServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("jshop")
@Namespace("")
@InterceptorRefs({  
    @InterceptorRef("defaultStack")  
})
@Controller("cartAction")
public class CartAction extends ActionSupport {
	@Resource(name="goodsTServiceImpl")
	private GoodsTServiceImpl goodsTServiceImpl;
	@Resource(name="cartTServiceImpl")
	private CartTServiceImpl cartTServiceImpl;
	@Resource(name="serial")
	private Serial serial;
	private String hidurl;
	private String redirecturl;
	private String cartid;
	private String orderid;
	private String goodsid;
	private String goodsname;
	private String userid;
	private String username;
	private String needquantity;
	private String price;
	private String favorable;
	private String changeprice;
	private String points;
	private String subtotal;
	private String quantity;
	private String picture;
	private String id;
	private String sendstring;
	private Double totalweight = 0.0;
	private Double totalmemberprice = 0.0;
	private boolean sflag = false;
	private boolean slogin = false;
	@JSON(serialize = false)
	public GoodsTServiceImpl getGoodsTServiceImpl() {
		return goodsTServiceImpl;
	}

	public void setGoodsTServiceImpl(GoodsTServiceImpl goodsTServiceImpl) {
		this.goodsTServiceImpl = goodsTServiceImpl;
	}
	@JSON(serialize = false)
	public CartTServiceImpl getCartTServiceImpl() {
		return cartTServiceImpl;
	}

	public void setCartTServiceImpl(CartTServiceImpl cartTServiceImpl) {
		this.cartTServiceImpl = cartTServiceImpl;
	}
	@JSON(serialize = false)
	public Serial getSerial() {
		return serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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

	public String getNeedquantity() {
		return needquantity;
	}

	public void setNeedquantity(String needquantity) {
		this.needquantity = needquantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFavorable() {
		return favorable;
	}

	public void setFavorable(String favorable) {
		this.favorable = favorable;
	}

	public String getChangeprice() {
		return changeprice;
	}

	public void setChangeprice(String changeprice) {
		this.changeprice = changeprice;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public String getHidurl() {
		return hidurl;
	}

	public void setHidurl(String hidurl) {
		this.hidurl = hidurl;
	}

	public Double getTotalweight() {
		return totalweight;
	}

	public void setTotalweight(Double totalweight) {
		this.totalweight = totalweight;
	}

	public Double getTotalmemberprice() {
		return totalmemberprice;
	}

	public void setTotalmemberprice(Double totalmemberprice) {
		this.totalmemberprice = totalmemberprice;
	}

	public String getRedirecturl() {
		return redirecturl;
	}

	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendstring() {
		return sendstring;
	}

	public void setSendstring(String sendstring) {
		this.sendstring = sendstring;
	}

	/**
	 * 清理错误
	 */
	@Override
	public void validate() {
		this.clearErrorsAndMessages();

	}



	/**
	 * 在增加商品到购物车前查询商品信息
	 */
	public List<GoodsT> GetGoodsdetailByGoodsidForCart() {
		if (Validate.StrNotNull(this.getGoodsid())) {
			List<GoodsT> list1 = new ArrayList<GoodsT>();
			String[] tempgoodsid = this.getGoodsid().split(",");
			for (int i = 0; i < tempgoodsid.length; i++) {
				GoodsT list = this.getGoodsTServiceImpl().findGoodsById(tempgoodsid[i]);
				if (list != null) {

					list1.add(list);
				}
			}
			return list1;
		}
		return null;
	}

	/**
	 * 增加购物车商品
	 * 
	 * @return
	 */
	@Action(value = "addCart", results = { 
			@Result(name = "json",type="json")
	})
	public String addCart() {
		UserT user = (UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if (user != null) {
			this.setSlogin(false);
			List<GoodsT> gtlist1 = this.GetGoodsdetailByGoodsidForCart();
			for (int i = 0; i < gtlist1.size(); i++) {
				GoodsT gtlist = gtlist1.get(i);
				CartT cart = this.getCartTServiceImpl().findGoodsInCartOrNot(user.getUserid(), gtlist.getGoodsid(), "1");
				if (cart != null) {
					//同状态的商品只能在购物车出现一次
					//更新对应商品id的数量	//检测商品是否已经在购物车中，如果有责增加数量，没有责加入
					@SuppressWarnings("unused")
					int j = this.getCartTServiceImpl().updateCartNeedquantityByGoodsid(user.getUserid(), this.getGoodsid().trim(), Integer.parseInt(this.getNeedquantity()), "1");

					//state=1表示更新商品到新增状态，前台可在购物车读取。注意在同一个商品被删除后，通过更新状态到1做到可以再次购买这个商品
					//					if(cart.getState().equals("2")){
					//						@SuppressWarnings("unused")
					//						int k=this.getCartserviceimpl().UpdateCartState(user.getUserid(), this.getGoodsid().trim(), "1");
					//					
					//					}
					//更新商品价格小计subtotal
					//int j=this.getCartserviceimpl().UpdateCartSubtotal(user.getUserid(), this.getGoodsid(), Integer.parseInt(this.getNeedquantity())*cart.getFavorable());
					this.setSflag(true);
				} else {
					String[] picturelist = gtlist.getPictureurl().split(",");

					CartT t = new CartT();
					t.setId(this.getSerial().Serialid(Serial.CARTINFO));
					t.setCartid(null);
					t.setOrderid(null);
					t.setGoodsid(gtlist.getGoodsid());
					t.setGoodsname(gtlist.getGoodsname());
					t.setUserid(user.getUserid());
					t.setUsername(user.getUsername());
					t.setNeedquantity(Integer.parseInt(this.getNeedquantity()));
					t.setPrice(gtlist.getPrice());
					t.setFavorable(gtlist.getMemberprice());
					t.setChangeprice(null);
					t.setPoints(gtlist.getPoints());
					t.setSubtotal(Double.parseDouble(this.getNeedquantity()) * gtlist.getMemberprice());
					t.setAddtime(BaseTools.systemtime());
					t.setQuantity(gtlist.getQuantity());
					t.setPicture(picturelist[0]);
					t.setUsersetnum(gtlist.getUsersetnum());
					t.setWeight(gtlist.getWeight());
					t.setState("1");
					if (this.getCartTServiceImpl().addCart(t) > 0) {
						this.setSflag(true);
					}
				}
			}
			return "json";
		}
		this.setSlogin(true);
		return "json";

	}

	/**
	 * 获取推荐商品
	 * 
	 * @return
	 */
	public void GetRcommendedGoods(int limit, double minprice, double maxprice, String goodsid) {
		List<GoodsT> list = this.getGoodsTServiceImpl().findSamepriceGoods(limit, minprice, maxprice, goodsid);
		if (list != null) {
			List<String> list1 = new ArrayList<String>();
			String[] temp = null;
			for (Iterator it = list.iterator(); it.hasNext();) {
				GoodsT gt = (GoodsT) it.next();
				temp = gt.getPictureurl().split(",");
				gt.setPictureurl(temp[0]);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("recommended", list);
			ActionContext.getContext().put("recommendedgoods", map);
		}

	}

	/**
	 * 获取购物车数据给用户中心我的购物车
	 * 
	 * @return
	 */
//	@Action(value = "findAllCartByUserIdForUsercenter", results = { 
//			@Result(name = "success", type="redirect",location = "/jshop/admin/adminindex.jsp?session=${param}"),
//			@Result(name = "input", type="redirect",location = "/jshop/admin/adminlogin.jsp?msg=${param}")
//	})
//	public String findAllCartByUserIdForUsercenter() {
//		UserT user = (UserT) ActionContext.getContext().getSession().get("user");
//		if (user != null) {
//			List<CartT> list = this.getCartTServiceImpl().findAllCartByUserId(user.getUserid());
//			if (list != null && list.size() > 0) {
//				//删除购物车session
//				ActionContext.getContext().getSession().remove("usercart");
//				this.setTotalmemberprice(0.0);
//				this.setTotalweight(0.0);
//				for (Iterator it = list.iterator(); it.hasNext();) {
//					CartT ct = (CartT) it.next();
//					totalweight = Arith.add(totalweight, Arith.mul(Double.parseDouble(ct.getWeight()), Double.parseDouble(String.valueOf(ct.getNeedquantity()))));
//					totalmemberprice = Arith.add(totalmemberprice, Arith.mul(ct.getFavorable(), Double.parseDouble(String.valueOf(ct.getNeedquantity()))));
//
//				}
//				//获取推荐商品（暂时根据价位来获取）
//				GetRcommendedGoods(5, Arith.sub(list.get(0).getFavorable(), 20.0), Arith.add(list.get(0).getFavorable(), 20.0), list.get(0).getGoodsid());
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("cart", list);
//				map.put("totalweight", totalweight);
//				map.put("totalmemberprice", totalmemberprice);
//				map.put("count", list.size());
//				ActionContext.getContext().getSession().put("usercart", map);
//				return SUCCESS;
//			}
//			//删除购物车session
//			ActionContext.getContext().getSession().remove("usercart");
//			return SUCCESS;
//		}
//		ActionContext.getContext().getSession().remove("usercart");
//		return INPUT;
//	}

	/**
	 * 根据userid获取购物车信息
	 * 
	 * @return
	 */
	@Action(value = "findAllCartByUserId", results = { 
			@Result(name = "success",location = "/usercenter/cart/cart.jsp"),
			@Result(name = "input",type="redirect",location = "/html/default/shop/login.html?redirecturl=${redirecturl}")
	})
	public String findAllCartByUserId() {
		UserT user = (UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if (user != null) {
			List<CartT> list = this.getCartTServiceImpl().findAllCartByUserId(user.getUserid());
			if (list != null && list.size() > 0) {
				//删除购物车session
				ActionContext.getContext().getSession().remove("usercart");
				this.setTotalmemberprice(0.0);
				this.setTotalweight(0.0);
				for (Iterator it = list.iterator(); it.hasNext();) {
					CartT ct = (CartT) it.next();
					totalweight = Arith.add(totalweight, Arith.mul(Double.parseDouble(ct.getWeight()), Double.parseDouble(String.valueOf(ct.getNeedquantity()))));
					totalmemberprice = Arith.add(totalmemberprice, Arith.mul(ct.getFavorable(), Double.parseDouble(String.valueOf(ct.getNeedquantity()))));
				}
				//获取推荐商品（暂时根据价位来获取）
				GetRcommendedGoods(5, Arith.sub(list.get(0).getFavorable(), 20.0), Arith.add(list.get(0).getFavorable(), 20.0), list.get(0).getGoodsid());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cart", list);
				map.put("totalweight", totalweight);
				map.put("totalmemberprice", totalmemberprice);
				map.put("count", list.size());
				ActionContext.getContext().getSession().put("usercart", map);
				return SUCCESS;
			}
			//获取推荐商品（暂时根据价位来获取）
			GetRcommendedGoods(10, 0.0, 20.0, "0");
			//删除购物车session
			ActionContext.getContext().getSession().remove("usercart");
			return SUCCESS;
		}
		ActionContext.getContext().getSession().remove("usercart");
		return INPUT;
	}

	/**
	 * 获取用户购物车数据用于上方显示购物车数据
	 * 
	 * @return
	 */
	@Action(value = "findAllCartByUserIdFortopCart", results = { 
			@Result(name = "json",type="json")
	})
	public String findAllCartByUserIdFortopCart() {
		UserT user = (UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if (user != null) {
			List<CartT> list = this.getCartTServiceImpl().findAllCartByUserId(user.getUserid());
			if (list != null && list.size() > 0) {
				//删除购物车session
				ActionContext.getContext().getSession().remove("usercart");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cart", list);
				map.put("count", list.size());
				ActionContext.getContext().getSession().put("usercart", map);
				return "json";
			}
			//删除购物车session
			ActionContext.getContext().getSession().remove("usercart");
			return "json";
		}
		ActionContext.getContext().getSession().remove("usercart");
		return "json";
	}

	/**
	 * 更新商品数量，在提交订单时修改购物车商品数量的ajax
	 * 
	 * @return
	 */
	@Action(value = "PlusCartNeedquantityByGoodsid", results = { 
			@Result(name = "json",type="json")
	})
	public String PlusCartNeedquantityByGoodsid() {
		UserT user = (UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if (user != null) {
			//更新对应商品id的数量	//检测商品是否已经在购物车中，如果有责增加数量，没有责加入
			int j = 0;
			String[] tempstring = this.getSendstring().split(":");
			String temp = null;
			String[] ttemp = null;
			String cartid = this.getSerial().Serialid(Serial.CART);//获取购物车信息id是可重复的。一次提交只有一个购物车信息id
			for (int k = 0; k < tempstring.length; k++) {
				temp = tempstring[k];
				ttemp = temp.split(",");
				j = this.getCartTServiceImpl().updateCartNeedquantity(user.getUserid(), ttemp[0], Integer.parseInt(ttemp[1]));
				this.getCartTServiceImpl().updateCartId(cartid, user.getUserid(), ttemp[0], "1");
			}
			this.setSflag(true);
			return "json";
		} else {
			this.setSlogin(true);
			return "json";
		}
	}

	/**
	 * 删除购物车中的商品
	 * 
	 * @return
	 */
	@Action(value = "DelCartByGoodsId", results = { 
			@Result(name = "success",type="chain", location = "findAllCartByUserId"),
			@Result(name = "input",type="redirect",location = "/html/default/shop/login.html?redirecturl=${redirecturl}")
	})
	public String DelCartByGoodsId() {
		UserT user = (UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if (user != null) {
			@SuppressWarnings("unused")
			int i = this.getCartTServiceImpl().delCartByGoodsId(user.getUserid(), this.getGoodsid());
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	/**
	 * 暂时删除购物车中商品
	 * 
	 * @return
	 */
	@Action(value = "UpdateCartGoodsstate2", results = { 
			@Result(name = "success",type="chain", location = "findAllCartByUserId"),
			@Result(name = "input",type="redirect",location = "/html/default/shop/login.html?redirecturl=${redirecturl}")
	})
	public String UpdateCartGoodsstate2() {
		UserT user = (UserT) ActionContext.getContext().getSession().get(BaseTools.USER_SESSION_KEY);
		if (user != null) {
			@SuppressWarnings("unused")
			String[] goodslist = (this.getGoodsid() + ",").split(",");
			int i = this.getCartTServiceImpl().updateCartStateByGoodsidList(user.getUserid(), goodslist, "2");
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

}
