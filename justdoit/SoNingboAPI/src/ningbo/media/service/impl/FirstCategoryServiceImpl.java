package ningbo.media.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.FirstCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FirstCategoryDao;
import ningbo.media.service.FirstCategoryService;

@Service("firstCategoryService")
public class FirstCategoryServiceImpl extends
		BaseServiceImpl<FirstCategory, Integer> implements FirstCategoryService {

	@Autowired
	public FirstCategoryServiceImpl(
			@Qualifier("firstCategoryDao") FirstCategoryDao firstCategoryDao) {
		super(firstCategoryDao);
	}

	public List<String> getAllCagegoryName(String local) {
		String queryName = null;
		if (local != null) {
			if ("zh".equalsIgnoreCase(local)) {
				queryName = "name_cn";
			} else if ("en".equalsIgnoreCase(local)) {
				queryName = "name_en";
			}
			String hql = "select " + queryName + " from FirstCategory model ";
			return findAllObject(hql) ;
		}
		return null;
	}

}
