package ningbo.media.core.dao;

import java.io.Serializable;

import ningbo.media.core.IdEntity;

public interface IBaseDao<T extends IdEntity<T>, PK extends Serializable> {

	public T get(PK id);

	public int delete(PK id);

	public int save(T entity);
}
