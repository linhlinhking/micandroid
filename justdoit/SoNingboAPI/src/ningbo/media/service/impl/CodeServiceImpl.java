package ningbo.media.service.impl;

import ningbo.media.bean.Code;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.CodeDao;
import ningbo.media.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("codeService")
public class CodeServiceImpl extends BaseServiceImpl<Code, Integer>
		implements CodeService {

	private CodeDao codeDao ;
	
	@Autowired
	public CodeServiceImpl(@Qualifier("codeDao")CodeDao codeDao) {
		super(codeDao);
		this.codeDao = codeDao ;
	}

	public Code findCodeByCodeNum(Integer codeNum) {
		try {
			return codeDao.findCodeByCodeNum(codeNum);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
