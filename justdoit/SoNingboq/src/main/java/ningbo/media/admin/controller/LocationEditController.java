package ningbo.media.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ningbo.media.admin.response.ResponseObject;
import ningbo.media.admin.vo.LocationEditByPositionVO;
import ningbo.media.admin.vo.LocationEditListVO;
import ningbo.media.admin.vo.LocationEditVO;
import ningbo.media.bean.Location;
import ningbo.media.bean.LocationEdit;
import ningbo.media.bean.SystemUser;
import ningbo.media.core.page.Pagination;
import ningbo.media.core.web.BaseController;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.LocationEditService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SystemUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/locationEdit")
public class LocationEditController extends BaseController<LocationEdit> {

	@Resource
	private LocationEditService locationEditService;

	@Resource
	private LocationService locationService;

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long total = locationEditService.getTotalCount();
		ModelAndView model = new ModelAndView();
		model.addObject("total", total);
		model.setViewName("location-edit-list");
		return model;
	}

	@RequestMapping(value = "/page/{pageNumber}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseObject<LocationEditListVO> getDatasByPage(
			@PathVariable("pageNumber") String pageNumber,
			@PathVariable("pageSize") String pageSize) {

		if (pageNumber == null || pageSize == null) {
			return null;
		}
		Pagination<LocationEdit> p = locationEditService.getAllEditsByPage(
				Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
		List<LocationEdit> lists = p.getList();
		LocationEditListVO listVO = setLocationEditData(lists,
				Integer.valueOf(pageNumber), Integer.valueOf(pageSize));

		ResponseObject<LocationEditListVO> res = new ResponseObject<LocationEditListVO>();
		if (null != listVO) {
			res.setSuccess(true);
			res.setData(listVO);
		} else {
			res.setSuccess(false);
		}
		return res;
	}

	@RequestMapping(value = "/location/{md5Value}/{pageNumber}/{pageSize}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseObject<LocationEditByPositionVO> getDatasByLocation(
			@PathVariable("md5Value") String md5Value,
			@PathVariable("pageNumber") String pageNumber,
			@PathVariable("pageSize") String pageSize) {

		if (pageNumber == null || pageSize == null) {
			return null;
		}
		Pagination<LocationEdit> p = locationEditService
				.getLocationEditsByPage(md5Value, Integer.valueOf(pageNumber),
						Integer.valueOf(pageSize));
		List<LocationEdit> lists = p.getList();

		LocationEditByPositionVO listVO = setLocationEditPositionData(lists,
				md5Value);

		ResponseObject<LocationEditByPositionVO> res = new ResponseObject<LocationEditByPositionVO>();

		if (null != listVO) {
			res.setSuccess(true);
			res.setData(listVO);
		} else {
			res.setSuccess(false);
		}
		return res;
	}

	private LocationEditByPositionVO setLocationEditPositionData(
			List<LocationEdit> lists, String md5Value) {
		LocationEditByPositionVO listVO = new LocationEditByPositionVO();
		if (null == md5Value || md5Value == "") {
			return null;
		}
		Location loc = locationService.get(Constant.MD5_FIELD, md5Value);
		if (null == loc) {
			return null;
		}
		listVO.setLocation(loc);
		if (null != lists && lists.size() > 0) {
			List<LocationEditVO> voList = new ArrayList<LocationEditVO>();
			for (int i = 0, j = lists.size(); i < j; i++) {
				LocationEditVO temp = new LocationEditVO();
				LocationEdit ed = lists.get(i);

				temp.setFieldName(ed.getFieldName());
				temp.setNewValue(ed.getNewValue());
				temp.setOldValue(ed.getOldValue());
				voList.add(temp);
			}
			listVO.setLocationEdits(voList);

		}

		return listVO;
	}

	private LocationEditListVO setLocationEditData(List<LocationEdit> lists,
			Integer pageNumber, Integer pageSize) {

		if (null != lists && lists.size() > 0) {
			LocationEditListVO listVO = new LocationEditListVO();
			List<LocationEditVO> voList = new ArrayList<LocationEditVO>();
			String locMd5Value = "";
			String uMd5Value = "";
			Location loc = null;
			SystemUser u = null;

			for (int i = 0, j = lists.size(); i < j; i++) {
				LocationEditVO temp = new LocationEditVO();
				LocationEdit ed = lists.get(i);
				locMd5Value = ed.getLocationMd5();
				if ("" != locMd5Value) {
					loc = locationService.get(Constant.MD5_FIELD, locMd5Value);
					if (null != loc) {
						temp.setLocation(loc);
					}
				}

				uMd5Value = ed.getUserMd5();
				if ("" != uMd5Value) {
					u = systemUserService.get(Constant.MD5_FIELD, uMd5Value);
					if (null != u) {
						temp.setSystemUser(u);
					}
				}

				temp.setFieldName(ed.getFieldName());
				temp.setNewValue(ed.getNewValue());
				temp.setOldValue(ed.getOldValue());

				voList.add(temp);
			}
			listVO.setLocationEdits(voList);
			listVO.setPageNumber(pageNumber);
			listVO.setPageSize(pageSize);

			return listVO;
		}
		return null;
	}
	
	

}
