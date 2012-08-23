package ningbo.media.rest.ws.impl;

import javax.annotation.Resource;
import javax.jws.WebService;

import ningbo.media.bean.SystemUser;
import ningbo.media.mapper.BeanMapper;
import ningbo.media.rest.WsConstants;
import ningbo.media.rest.base.IdResult;
import ningbo.media.rest.base.WSResult;
import ningbo.media.rest.dto.SystemUserData;
import ningbo.media.rest.ws.SystemUseWebService;
import ningbo.media.rest.ws.result.UserResult;
import ningbo.media.service.SystemUserService;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(serviceName = "SystemUseWebService", portName = "SystemUseWebServicePort", endpointInterface = "ningbo.media.rest.ws.SystemUserWebService", targetNamespace = WsConstants.NS)
public class SystemUserWebServiceImpl implements SystemUseWebService {

	private static Logger logger = LoggerFactory
			.getLogger(SystemUserWebServiceImpl.class);

	@Resource
	private SystemUserService systemUserService;

	public IdResult createUser(SystemUserData user) {
		return null;
	}

	public UserResult getUserDetail(Integer id) {
		try {
			SystemUser u = systemUserService.get(id);
			Validate.notNull(u, "部门不存在(id:" + id + ")");
			SystemUserData dto = BeanMapper.map(u, SystemUserData.class);
			return new UserResult(dto);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return new UserResult().setError(WSResult.PARAMETER_ERROR, e
					.getMessage());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			return new UserResult().setDefaultError();
		}
	}

}
