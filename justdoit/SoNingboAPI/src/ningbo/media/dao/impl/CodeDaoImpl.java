package ningbo.media.dao.impl;

import ningbo.media.bean.Code;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.CodeDao;

import org.springframework.stereotype.Repository;

@Repository("codeDao")
public class CodeDaoImpl extends BaseDaoImpl<Code, Integer> implements
		CodeDao {

	public CodeDaoImpl() {
		super(Code.class);
	}

	/**
	 * 通过自定义codeNum得到code
	 */
	public Code findCodeByCodeNum(Integer codeNum) {
		final String hql = "from Code model where model.codeNum = ? ";
		if(null == codeNum) {
			return null;
		}
		Code code = (Code) findUnique(hql, Integer.valueOf(codeNum));
		return code;
	}


}
