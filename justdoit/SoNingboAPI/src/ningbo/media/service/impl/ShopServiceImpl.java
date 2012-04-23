package ningbo.media.service.impl;

import ningbo.media.bean.tuan.Shop;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.ShopDao;
import ningbo.media.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("shopService")
public class ShopServiceImpl extends BaseServiceImpl<Shop, Integer> implements
		ShopService {

	@Autowired
	public ShopServiceImpl(@Qualifier("shopDao")
	ShopDao shopDao) {
		super(shopDao);
	}
}
