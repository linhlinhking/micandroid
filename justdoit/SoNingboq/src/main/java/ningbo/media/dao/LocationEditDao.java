package ningbo.media.dao;

import java.util.List;

import ningbo.media.bean.LocationEdit;
import ningbo.media.core.dao.BaseDao;

public interface LocationEditDao extends BaseDao<LocationEdit, Integer>{

	public List<LocationEdit> getAllEditInfors(String locationMd5);
}
