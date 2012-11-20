package ningbo.media.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ningbo.media.admin.exception.ServiceException;
import ningbo.media.bean.ImageInformation;
import ningbo.media.bean.ModuleFile;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.ImageInformationDao;
import ningbo.media.dao.ModuleFileDao;
import ningbo.media.rest.dto.ModuleFileData;
import ningbo.media.service.ModuleFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("moduleFileService")
public class ModuleFileServiceImpl extends BaseServiceImpl<ModuleFile, Integer>
		implements ModuleFileService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ModuleFileDao moduleFileDao;

	@Resource
	private ImageInformationDao imageInformationDao;

	@Autowired
	public ModuleFileServiceImpl(
			@Qualifier("moduleFileDao") ModuleFileDao moduleFileDao) {
		super(moduleFileDao);
	}

	public List<ModuleFileData> queryModuleFileByUserHeader(Integer userId) {
		String hql = "select m from ModuleFile as m join m.systemUsers as u where 1=1 and u.id = ? ";

		List<ModuleFile> list = moduleFileDao.findByHql(hql, userId);
		List<ModuleFileData> data = new ArrayList<ModuleFileData>();
		if (null != list && list.size() > 0) {
			for (ModuleFile file : list) {
				ModuleFileData temp = new ModuleFileData();
				temp.setId(file.getId());
				temp.setFileName(file.getFileName());
				temp.setFilePath(file.getFileHash());
				temp.setWidth(file.getImageInfo().getWidth());
				temp.setHeight(file.getImageInfo().getHeight());

				data.add(temp);
			}
			return data;
		}
		return null;
	}

	public List<ModuleFile> queryModuleFileByType(Integer userId,
			Integer toolId, Integer typeId) {
		final String hql = "from ModuleFile as m where 1=1 and m.tools.id in (select id from Tools as t inner join SystemUser as u where 1=1 and u.id = ? and t.id = ?) and m.moduleType.id = ? ";
		List<ModuleFile> list = moduleFileDao.findByHql(hql, userId, toolId,
				typeId);
		if (null != list && list.size() > 0) {
			return list;
		}
		return null;
	}

	public List<ModuleFileData> queryAllFile() {
		List<ModuleFile> listFiles = moduleFileDao.getAll();
		List<ModuleFileData> data = new ArrayList<ModuleFileData>();
		if (null == listFiles || listFiles.size() < 0) {
			return null;
		}
		for (ModuleFile file : listFiles) {
			ModuleFileData temp = new ModuleFileData();
			temp.setId(file.getId());
			temp.setFileName(file.getFileName());
			temp.setFilePath(file.getFileHash());

			ImageInformation infor = file.getImageInfo();
			if (null != infor) {
				temp.setWidth(infor.getWidth());
				temp.setHeight(infor.getHeight());
				temp.setSize(infor.getSize());
				temp.setLongitude(infor.getLongitude());
				temp.setLatitude(infor.getLatitude());
			}
			data.add(temp);
		}
		return data;
	}

	public List<ModuleFileData> queryModuleFileByLocation(String md5_id) {
		String hql = "select m from ModuleFile as m join m.locations as u where 1=1 and u.md5Value = ? ";
		List<ModuleFile> list = moduleFileDao.findByHql(hql, md5_id);
		List<ModuleFileData> data = new ArrayList<ModuleFileData>();
		if (null != list && list.size() > 0) {
			for (ModuleFile file : list) {
				ModuleFileData temp = new ModuleFileData();
				temp.setId(file.getId());
				temp.setFileName(file.getFileName());
				temp.setFilePath(file.getFileHash());
				temp.setWidth(file.getImageInfo().getWidth());
				temp.setHeight(file.getImageInfo().getHeight());

				data.add(temp);
			}
			return data;
		}
		return null;
	}

	public ModuleFileData getModuleFileById(Integer fileId) {
		try {
			ModuleFile tempFile = moduleFileDao.get(fileId);

			ModuleFileData data = new ModuleFileData();
			if (null != tempFile) {
				data.setFileName(tempFile.getFileName());
				data.setFilePath(tempFile.getFileHash());
				data.setWidth(tempFile.getImageInfo().getWidth());
				data.setHeight(tempFile.getImageInfo().getHeight());

				return data;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean deleteModuleFileByUserId(Integer userId, String locationMd5) {
		// boolean flag = false ;

		return false;
	}

	public boolean deleteModuleFile(Integer id) {
		boolean flag = false;
		try {
			ModuleFile moduleFile = moduleFileDao.get(id);
			ImageInformation info = moduleFile.getImageInfo();
			imageInformationDao.delete(info);
			moduleFileDao.delete(moduleFile);
			flag = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	public List<ModuleFile> queryFilesByEventId(Integer eventId)
			throws ServiceException {
		try {
			String hql = "select m from ModuleFile as m ,NEventFile as n where 1=1 and m.id = n.fileId and n.eventId = ? ";
			List<ModuleFile> files = moduleFileDao.findByHql(hql, eventId);

			return files;
		} catch (Exception ex) {
			logger.error(
					"Query Event files Error.The event's id is " + eventId, ex);
			return null;
		}

	}

}
