package ningbo.media.core.service.impl;

import java.io.Serializable;

import ningbo.media.core.IdEntity;
import ningbo.media.core.dao.IBaseDao;
import ningbo.media.core.service.IBaseService;



public class BaseService<T extends IdEntity<T>, PK extends Serializable> implements IBaseService<T, PK> {
	
	protected IBaseDao<T,PK> dao;
	
	public void setBaseDao(IBaseDao<T, PK> dao) {
		this.dao = dao;
	}

	public T getObjectByPK(PK pk) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteObjectByPk(PK pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	public T saveObject(T object) {
		// TODO Auto-generated method stub
		return null;
	}

}
