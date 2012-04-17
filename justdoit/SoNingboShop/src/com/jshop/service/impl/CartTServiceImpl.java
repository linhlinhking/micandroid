package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.CartTDaoImpl;
import com.jshop.entity.CartT;
import com.jshop.service.CartTService;

@Service("cartTServiceImpl")
@Scope("prototype")
public class CartTServiceImpl implements CartTService {
	@Resource(name="cartTDaoImpl")
	private CartTDaoImpl cartTDaoImpl;

	public CartTDaoImpl getCartTDaoImpl() {
		return cartTDaoImpl;
	}

	public void setCartTDaoImpl(CartTDaoImpl cartTDaoImpl) {
		this.cartTDaoImpl = cartTDaoImpl;
	}

	public int addCart(CartT c) {
		return this.getCartTDaoImpl().addCart(c);
	}

	public int countfindAllCart() {
		return this.getCartTDaoImpl().countfindAllCart();
	}

	public List<CartT> findAllCart(int currentPage, int lineSize) {
		return this.getCartTDaoImpl().findAllCart(currentPage, lineSize);
	}

	public List<CartT> findAllCartByUserId(String userid) {
		return this.getCartTDaoImpl().findAllCartByUserId(userid);
	}

	public CartT findGoodsInCartOrNot(String userid, String goodsid, String state) {
		return this.getCartTDaoImpl().findGoodsInCartOrNot(userid, goodsid, state);
	}

	public int updateCartNeedquantityByGoodsid(String userid, String goodsid, int needquantity, String state) {
		return this.getCartTDaoImpl().updateCartNeedquantityByGoodsid(userid, goodsid, needquantity, state);
	}

	public int reduceCartNeddquantityByGoodsid(String userid, String goodsid, int needquantity) {
		return this.getCartTDaoImpl().reduceCartNeddquantityByGoodsid(userid, goodsid, needquantity);
	}

	public int delCartByGoodsId(String userid, String goodsid) {
		return this.getCartTDaoImpl().delCartByGoodsId(userid, goodsid);
	}

	public int updateCartNeedquantity(String userid, String goodsid, int needquantity) {
		return this.getCartTDaoImpl().updateCartNeedquantity(userid, goodsid, needquantity);
	}

	public int updateCartSubtotal(String userid, String goodsid, double subtotal) {
		return this.getCartTDaoImpl().updateCartSubtotal(userid, goodsid, subtotal);
	}

	public int updateCartState(String userid, String goodsid, String state) {
		return this.getCartTDaoImpl().updateCartState(userid, goodsid, state);
	}

	public int updateCartStateandOrderidByGoodsidList(String cartid, String orderid, String userid, String state) {
		return this.getCartTDaoImpl().updateCartStateandOrderidByGoodsidList(cartid, orderid, userid, state);
	}

	public List<CartT> findCartGoodsByOrderid(String orderid) {
		return this.getCartTDaoImpl().findCartGoodsByOrderid(orderid);
	}

	public int updateCartStateByGoodsidList(String userid, String[] goodsid, String state) {
		return this.getCartTDaoImpl().updateCartStateByGoodsidList(userid, goodsid, state);
	}

	public int updateCartId(String cartid, String userid, String goodsid, String state) {
		return this.getCartTDaoImpl().updateCartId(cartid, userid, goodsid, state);
	}
}
