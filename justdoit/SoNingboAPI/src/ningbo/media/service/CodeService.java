
package ningbo.media.service;

import ningbo.media.bean.Code;
import ningbo.media.core.service.BaseService;

public interface CodeService extends BaseService<Code, Integer> {

	public Code findCodeByCodeNum(Integer codeNum) ;
	
}
