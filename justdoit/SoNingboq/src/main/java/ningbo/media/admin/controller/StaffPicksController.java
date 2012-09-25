package ningbo.media.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.admin.util.Result;
import ningbo.media.bean.Location;
import ningbo.media.bean.StaffPicks;
import ningbo.media.bean.SystemUser;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.LocationService;
import ningbo.media.service.StaffPicksService;
import ningbo.media.service.SystemUserService;
import ningbo.media.web.util.SessionHandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/staff")
public class StaffPicksController {

	private static final String STAFFPICKS_LIST = "staffPicks-list";


	@Resource
	private StaffPicksService staffPicksService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private LocationService locationService;

	@RequestMapping
	public String getStaffPicksHome(HttpServletRequest request,
			HttpServletResponse response) {
		return STAFFPICKS_LIST;
	}

	public String doSave() {

		return STAFFPICKS_LIST;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toSave(HttpServletRequest request) {
		return SessionHandler.verifySession(request, "admin/staff/add");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String doSave(@RequestParam("locationId") String locationId,
			@RequestParam("userId") String userId, Model model) {
		StaffPicks staff = null;
		if (null == locationId || null == userId) {
			return "";
		}

		Location location = locationService.get(Constant.MD5_FIELD, locationId);
		Integer locId = null, uId = null;
		if (null != location) {
			locId = location.getId();
		}
		SystemUser u = systemUserService.get(Constant.MD5_FIELD, userId);
		if (null != u) {
			uId = u.getId();
		}

		staff = staffPicksService.queryUniqueStaffPicks(locId, uId);

		if (null != staff) {
			model.addAttribute("msg", "This location already be shared");
		} else {
			staff = new StaffPicks();
			staff.setLocation(location);
		}
		Result<Integer> rs;

		return null;
	}
}
