package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Tags;
import ningbo.media.core.service.BaseService;
import ningbo.media.data.api.TagsXml;

public interface TagsService extends BaseService<Tags, Integer>{
	public List<TagsXml> getAllTags() ;
}
