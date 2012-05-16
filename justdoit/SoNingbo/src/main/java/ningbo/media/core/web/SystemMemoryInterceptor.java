package ningbo.media.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SystemMemoryInterceptor extends HandlerInterceptorAdapter {

	protected static Logger logger = LoggerFactory
			.getLogger(SystemMemoryInterceptor.class);
	public static boolean enabled = false;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (enabled) {
			int a = 1024 * 1024;
			String maxMem = Runtime.getRuntime().maxMemory() / a + "MB";
			String currentMem = Runtime.getRuntime().totalMemory() / a + "MB";
			String residualMem = Runtime.getRuntime().freeMemory() / a + "MB";
			request.setAttribute("maxMem", maxMem);
			request.setAttribute("currentMem", currentMem);
			request.setAttribute("residualMem", residualMem);
			float b = (float) (a);
			logger.info("JVM list-> Rated:"
					+ (float) ((float) Runtime.getRuntime().maxMemory() / b)
					+ "MB real:"
					+ (float) (Runtime.getRuntime().totalMemory() / b)
					+ "MB rest:" + (float) Runtime.getRuntime().freeMemory() / b
					+ "MB");
		}
		return super.preHandle(request, response, handler);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled2) {
		enabled = enabled2;
	}
}
