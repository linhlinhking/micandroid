package ningbo.media.core.service;

import java.io.Serializable;

import ningbo.media.core.IdEntity;

public interface IBaseService<T extends IdEntity<T>, PK extends Serializable> {

	public T getObjectByPK(PK pk);

	public int deleteObjectByPk(PK pk);

	public T saveObject(T object);
}
