package ningbo.media.dao;

import ningbo.media.bean.Code;
import ningbo.media.core.dao.BaseDao;

public interface CodeDao extends BaseDao<Code, Integer> {

	/**
	 * 通过自定义codeNum得到code
	 * @param codeNum
	 * @return
	 */
	public Code findCodeByCodeNum(Integer codeNum);
}
