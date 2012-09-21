package com.cisco.pmonitor.web.schedule;

import com.cisco.pmonitor.web.util.SessionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: shuaizha
 * Date: 12-3-26
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @RequestMapping(value = "/schedule_view", method = RequestMethod.GET)
    public String toView(HttpServletRequest request) {
        return SessionHandler.verifySession(request, "schedule/schedule_view");
    }
}
