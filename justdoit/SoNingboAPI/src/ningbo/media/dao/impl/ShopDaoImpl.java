package ningbo.media.dao.impl;

import ningbo.media.bean.tuan.Shop;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.ShopDao;

import org.springframework.stereotype.Repository;

@Repository("shopDao")
public class ShopDaoImpl extends BaseDaoImpl<Shop, Integer> implements ShopDao {

	public ShopDaoImpl(){
		super(Shop.class) ;
	}
}
