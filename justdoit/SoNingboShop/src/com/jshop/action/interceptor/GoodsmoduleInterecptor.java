package com.jshop.action.interceptor;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import com.jshop.dao.impl.GoodsAttributeTDaoImpl;
import com.jshop.entity.GoodsTypeTN;
import com.jshop.service.impl.GoodsAttributeTServiceImpl;
import com.jshop.service.impl.GoodsTServiceImpl;
import com.jshop.service.impl.GoodsTypeBrandTServiceImpl;
/**
 * 对商品模块中需要做验证后才能执行的方法做统一的拦截器处理
 * @author cd
 *
 */
@ParentPackage("jshop")

@Controller("goodsmoduleInterecptor")
public class GoodsmoduleInterecptor {
	
	
	@Resource(name = "goodsTypeBrandTServiceImpl")
	private GoodsTypeBrandTServiceImpl goodsTypeBrandTServiceImpl;
	@Resource(name = "goodsAttributeTServiceImpl")
	private GoodsAttributeTServiceImpl goodsAttributeTServiceImpl;
	@Resource(name="goodsTServiceImpl")
	private GoodsTServiceImpl goodsTServiceImpl;
	
	public GoodsTypeBrandTServiceImpl getGoodsTypeBrandTServiceImpl() {
		return goodsTypeBrandTServiceImpl;
	}

	public void setGoodsTypeBrandTServiceImpl(GoodsTypeBrandTServiceImpl goodsTypeBrandTServiceImpl) {
		this.goodsTypeBrandTServiceImpl = goodsTypeBrandTServiceImpl;
	}
	
	public GoodsAttributeTServiceImpl getGoodsAttributeTServiceImpl() {
		return goodsAttributeTServiceImpl;
	}

	public void setGoodsAttributeTServiceImpl(GoodsAttributeTServiceImpl goodsAttributeTServiceImpl) {
		this.goodsAttributeTServiceImpl = goodsAttributeTServiceImpl;
	}

	public GoodsTServiceImpl getGoodsTServiceImpl() {
		return goodsTServiceImpl;
	}

	public void setGoodsTServiceImpl(GoodsTServiceImpl goodsTServiceImpl) {
		this.goodsTServiceImpl = goodsTServiceImpl;
	}

	/**
	 * 更新商品品牌类型中的商品类型，当商品类型被修改以后
	 * @param gtn
	 * @return
	 */
	public void updateGoodsTypeBrandTname(String name,String goodsTypeId){
		this.getGoodsTypeBrandTServiceImpl().updateGoodsTypeBrandTname(name, goodsTypeId);
		
	}
	/**
	 * 更新商品属性中的商品类型
	 * @param goodsTypeName
	 * @param goodsTypeId
	 */
	public void updateGoodsAttributeTgoodsTypeName(String goodsTypeName,String goodsTypeId){
		this.getGoodsAttributeTServiceImpl().updateGoodsAttributeTgoodsTypeName(goodsTypeName, goodsTypeId);
	}
	/**
	 * 更新商品表中的商品类型
	 * @param goodsTypeName
	 * @param goodsTypeId
	 */
	public void updateGoodsTypeNameBygoodsTypeId(String goodsTypeName,String goodsTypeId){
		this.getGoodsTServiceImpl().updateGoodsTypeNameBygoodsTypeId(goodsTypeName, goodsTypeId);
	}
	
}
