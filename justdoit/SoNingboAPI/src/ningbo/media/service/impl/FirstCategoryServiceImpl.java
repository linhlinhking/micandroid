package ningbo.media.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.ws.rs.core.Response.Status;
import ningbo.media.bean.FirstCategory;
import ningbo.media.bean.SecondCategory;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FirstCategoryDao;
import ningbo.media.rest.dto.FirstCategoryData;
import ningbo.media.rest.dto.SecondCategoryData;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.Jerseys;
import ningbo.media.service.FirstCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("firstCategoryService")
public class FirstCategoryServiceImpl extends
		BaseServiceImpl<FirstCategory, Integer> implements FirstCategoryService {

	@Resource
	private FirstCategoryDao firstCategoryDao ;
	
	@Autowired
	public FirstCategoryServiceImpl(@Qualifier("firstCategoryDao")
	FirstCategoryDao firstCategoryDao) {
		super(firstCategoryDao);
	}

	public FirstCategoryData getFirstCategoryById(Integer id) {
		FirstCategoryData data = new FirstCategoryData();
		
		try {
			FirstCategory first = firstCategoryDao.get(id);
			if (null == first) {
				throw Jerseys.buildException(Status.NOT_FOUND,
						JSONCode.THROW_MESSAGE);
			}
			data.setId(first.getId());
			data.setName_cn(first.getName_cn());
			data.setName_en(first.getName_en());
			data.setDescription(first.getDescription());
			return data;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw Jerseys.buildException(Status.NOT_FOUND,
					JSONCode.SERVER_EXCEPTION);
		}
	}

	public List<SecondCategoryData> getFristCategoryByName(String name) {
		final String hql = "from FirstCategory as model where 1=1 and model.name_en = ? " ;
		List<SecondCategoryData> datas = Lists.newArrayList() ;
		try{
			FirstCategory first = (FirstCategory)firstCategoryDao.findUnique(hql, name) ;
			if (null == first) {
				throw Jerseys.buildException(Status.NOT_FOUND,
						JSONCode.THROW_MESSAGE);
			}
			List<SecondCategory> list = first.getSecondCategorys() ;
			if(null == list && list.size() < 0){
				throw Jerseys.buildException(Status.NOT_FOUND,
						JSONCode.THROW_MESSAGE);
			}
			for(SecondCategory s : list){
				SecondCategoryData data = new SecondCategoryData() ;
				data.setId(s.getId()) ;
				data.setName_cn(s.getName_cn()) ;
				data.setName_en(s.getName_en()) ;
				
				datas.add(data) ;
			}
			return datas ;
		}catch(Exception ex){
			ex.printStackTrace();
			throw Jerseys.buildException(Status.NOT_FOUND,
					JSONCode.SERVER_EXCEPTION);
		}
	}

}
