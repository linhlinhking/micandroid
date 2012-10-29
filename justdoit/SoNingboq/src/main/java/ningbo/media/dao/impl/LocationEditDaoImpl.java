package ningbo.media.dao.impl;

import java.util.List;

import ningbo.media.bean.LocationEdit;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LocationEditDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("locationEditDao")
public class LocationEditDaoImpl extends BaseDaoImpl<LocationEdit, Integer>
		implements LocationEditDao {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public LocationEditDaoImpl() {
		super(LocationEdit.class);
	}

	@Transactional(readOnly = true)
	public List<LocationEdit> getAllEditInfors(String locationMd5) {
		try {
			String hql = "from LocationEdit as model where 1=1 and model.locationMd5 = ? order by model.createDateTime desc,model.id desc ";
			List<LocationEdit> lists = findByHql(hql, locationMd5);
			if (null != lists && lists.size() > 0) {
				return lists;
			} else {
				return null;
			}
		} catch (Exception ex) {
			logger.error("Query All Edited Location Information Error.", ex);
			return null;
		}

	}

}
