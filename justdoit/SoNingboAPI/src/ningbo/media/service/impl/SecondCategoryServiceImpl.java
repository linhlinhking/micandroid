package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.Response.Status;

import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.SecondCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FirstCategoryDao;
import ningbo.media.dao.SecondCategoryDao;
import ningbo.media.rest.dto.SecondCategoryData;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.service.SecondCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("secondCategoryService")
public class SecondCategoryServiceImpl extends
		BaseServiceImpl<SecondCategory, Integer> implements
		SecondCategoryService {

	@Resource
	private FirstCategoryDao firstCategoryDao;

	@Autowired
	public SecondCategoryServiceImpl(@Qualifier("secondCategoryDao")
	SecondCategoryDao secondCategoryDao) {
		super(secondCategoryDao);
	}

	public List<SecondCategoryData> querySecondCategoryData(Integer id) {
		List<SecondCategory> tempList = null;
		if (null == id) {
			tempList = getAll();
		} else {
			FirstCategory firstCategory = firstCategoryDao.get(id);
			if (firstCategory == null) {
				throw Jerseys.buildException(Status.NOT_FOUND,
						JSONCode.THROW_MESSAGE);
			}
			tempList = firstCategory.getSecondCategorys();
		}

		List<SecondCategoryData> list = Lists.newArrayList();
		for (SecondCategory sc : tempList) {
			SecondCategoryData temp = new SecondCategoryData();
			temp.setId(sc.getId());
			temp.setName_cn(sc.getName_cn());
			temp.setName_en(sc.getName_en());
			list.add(temp);
		}

		return list;
	}

}
