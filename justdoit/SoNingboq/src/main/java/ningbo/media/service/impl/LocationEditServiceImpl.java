package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.bean.LocationEdit;
import ningbo.media.core.page.Finder;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationEditDao;
import ningbo.media.service.LocationEditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("locationEditService")
public class LocationEditServiceImpl extends
		BaseServiceImpl<LocationEdit, Integer> implements LocationEditService {

	@Resource
	private LocationEditDao locationEditDao;

	@Autowired
	public LocationEditServiceImpl(
			@Qualifier("locationEditDao") LocationEditDao locationEditDao) {
		super(locationEditDao);
	}

	public List<LocationEdit> getAllEditInfors(String locationMd5) {
		return locationEditDao.getAllEditInfors(locationMd5);
	}

	public Pagination<LocationEdit> getAllEditsByPage(int pageNo, int pageSize) {
		final Finder f = Finder.create("from LocationEdit as bean where 1=1 ");
		f.append(" order by bean.createDateTime desc,bean.id desc ");
		return findByPage(f, pageNo, pageSize);
	}

	public Pagination<LocationEdit> getLocationEditsByPage(
			String locationMd5Value, int pageNo, int pageSize) {

		final Finder f = Finder
				.create("from LocationEdit as bean where 1=1 and bean.locationMd5 =:locationMd5 ");
		f.append(" order by bean.createDateTime desc,bean.id desc ");
		f.setParam("locationMd5", locationMd5Value);
		return findByPage(f, pageNo, pageSize);
	}
}
