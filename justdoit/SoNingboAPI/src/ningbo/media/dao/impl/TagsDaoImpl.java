package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Tags;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.TagsDao;

@Repository("tagsDao")
public class TagsDaoImpl extends BaseDaoImpl<Tags, Integer> implements TagsDao {
	
	public TagsDaoImpl() {
		super(Tags.class);
	}
}
