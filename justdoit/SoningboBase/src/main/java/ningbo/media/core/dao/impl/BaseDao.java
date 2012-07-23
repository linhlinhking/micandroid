package ningbo.media.core.dao.impl;

import java.io.Serializable;

import ningbo.media.core.IdEntity;
import ningbo.media.core.dao.IBaseDao;

import org.mybatis.spring.support.SqlSessionDaoSupport;



public class BaseDao <T extends IdEntity<T>, PK extends Serializable>  
extends SqlSessionDaoSupport implements IBaseDao<T, PK> {

	public T get(PK id) {
		return null;
	}

	public int delete(PK id) {
		return 0;
	}

	public int save(T entity) {
		return 0;
	}

}
