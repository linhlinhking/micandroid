package com.jeecms.cms.manager.assist;

import com.jeecms.cms.entity.assist.CmsVoteRecord;
import com.jeecms.common.page.Pagination;

public interface CmsVoteRecordMng {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsVoteRecord findById(Integer id);

	public CmsVoteRecord save(CmsVoteRecord bean);

	public CmsVoteRecord update(CmsVoteRecord bean);

	public int deleteByTopic(Integer topicId);

	public CmsVoteRecord deleteById(Integer id);

	public CmsVoteRecord[] deleteByIds(Integer[] ids);
}