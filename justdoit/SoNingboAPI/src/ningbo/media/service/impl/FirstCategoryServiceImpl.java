package ningbo.media.service.impl;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.FirstCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FirstCategoryDao;
import ningbo.media.rest.dto.FirstCategoryData;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.service.FirstCategoryService;

@Service("firstCategoryService")
public class FirstCategoryServiceImpl extends
		BaseServiceImpl<FirstCategory, Integer> implements FirstCategoryService {

	@Autowired
	public FirstCategoryServiceImpl(@Qualifier("firstCategoryDao")
	FirstCategoryDao firstCategoryDao) {
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
			return findAllObject(hql);
		}
		return null;
	}

	public FirstCategoryData getFirstCategoryById(Integer id) {
		FirstCategoryData data = new FirstCategoryData() ;
		try {
			FirstCategory first = get(id);
			if (null == first) {
				throw Jerseys.buildException(Status.NOT_FOUND,
						JSONCode.THROW_MESSAGE);
			}
			data.setId(first.getId()) ;
			data.setName_cn(first.getName_cn()) ;
			data.setName_en(first.getName_en()) ;
			data.setDescription(first.getDescription()) ;
			data.setSecondCategorys(first.getSecondCategorys()) ;
			return data ;
		} catch (Exception ex) {
			ex.printStackTrace() ;
			throw Jerseys.buildException(Status.NOT_FOUND,
					JSONCode.SERVER_EXCEPTION);
		}
	}

}
