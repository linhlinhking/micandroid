package com.jeecms.cms.dao.assist;

import com.jeecms.cms.entity.assist.CmsVoteRecord;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface CmsVoteRecordDao {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsVoteRecord findById(Integer id);

	public CmsVoteRecord save(CmsVoteRecord bean);

	public CmsVoteRecord updateByUpdater(Updater<CmsVoteRecord> updater);

	public int deleteByTopic(Integer topicId);

	public CmsVoteRecord deleteById(Integer id);
}