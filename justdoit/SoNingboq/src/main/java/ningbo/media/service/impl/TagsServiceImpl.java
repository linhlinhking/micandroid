package ningbo.media.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ningbo.media.bean.Tags;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.TagsDao;
import ningbo.media.data.api.TagsXml;
import ningbo.media.data.entity.TagsEntity;
import ningbo.media.exception.DataFormatException;
import ningbo.media.exception.ServerErrorException;
import ningbo.media.service.TagsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("tagsService")
public class TagsServiceImpl extends BaseServiceImpl<Tags, Integer> implements TagsService{

	@Autowired
	public TagsServiceImpl(@Qualifier("tagsDao")TagsDao tagsDao){
		super(tagsDao) ;
	}

	public List<TagsXml> getAllTags() {
		List<Tags> listTags = super.getAll() ;
		Iterator<Tags> its = listTags.iterator() ;
		List<TagsXml> result = new ArrayList<TagsXml>() ;
		if(its.hasNext()){
			Tags t = its.next() ;
			try {
				
				result.add(new TagsXml(new TagsEntity(t.getId(),t.getTagName()))) ;
				//result.add(new TagsEntity(t.getId(),t.getTagName())) ;
			} catch (DataFormatException e) {
				throw new ServerErrorException(e.getMessage()) ;
			}
		}
		return result ;
	}
	
	
	
}
