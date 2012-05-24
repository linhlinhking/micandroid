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
import ningbo.media.util.Pinyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("firstCategoryService")
public class FirstCategoryServiceImpl extends
		BaseServiceImpl<FirstCategory, Integer> implements FirstCategoryService {

	@Resource
	private FirstCategoryDao firstCategoryDao;

	@Autowired
	public FirstCategoryServiceImpl(@Qualifier("firstCategoryDao")
	FirstCategoryDao firstCategoryDao) {
		super(firstCategoryDao);
	}

	public List<FirstCategoryData> getAllFirstCategory() {
		try {
			List<FirstCategory> list = firstCategoryDao.getAll();
			if (null == list && list.size() < 0) {
				return null;
			}
			List<FirstCategoryData> data = Lists.newArrayList();
			for (FirstCategory first : list) {
				FirstCategoryData d = new FirstCategoryData();
				d.setId(first.getId());
				d.setName_cn(first.getName_cn());
				d.setName_en(first.getName_en());
				d.setDescription_cn(first.getDescription_cn());
				d.setDescription_en(first.getDescription_en());
				data.add(d);
			}

			return data;
		} catch (Exception ex) {
			throw Jerseys.buildException(Status.NOT_FOUND,
					JSONCode.SERVER_EXCEPTION);
		}

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
			data.setDescription_cn(first.getDescription_cn());
			data.setDescription_en(first.getDescription_en());
			return data;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw Jerseys.buildException(Status.NOT_FOUND,
					JSONCode.SERVER_EXCEPTION);
		}
	}

	public List<SecondCategoryData> getFristCategoryByName(String name) {
		StringBuffer hql = new StringBuffer();
		hql.append("from FirstCategory as model where 1=1") ;
		if(null == name){
			return null ;
		}
		if(Pinyin.isChinese(name)){
			hql.append(" and model.name_cn = ? ") ;
		}else{
			hql.append(" and model.name_en = ? ") ;
		}
		
		List<SecondCategoryData> datas = Lists.newArrayList();
		try {
			FirstCategory first = (FirstCategory) firstCategoryDao.findUnique(
					hql.toString(), name);
			if (null == first) {
				throw Jerseys.buildException(Status.NOT_FOUND,
						JSONCode.THROW_MESSAGE);
			}
			List<SecondCategory> list = first.getSecondCategorys();
			if (null == list && list.size() < 0) {
				throw Jerseys.buildException(Status.NOT_FOUND,
						JSONCode.THROW_MESSAGE);
			}
			for (SecondCategory s : list) {
				SecondCategoryData data = new SecondCategoryData();
				data.setId(s.getId());
				data.setName_cn(s.getName_cn());
				data.setName_en(s.getName_en());

				datas.add(data);
			}
			return datas;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw Jerseys.buildException(Status.NOT_FOUND,
					JSONCode.SERVER_EXCEPTION);
		}
	}

}
