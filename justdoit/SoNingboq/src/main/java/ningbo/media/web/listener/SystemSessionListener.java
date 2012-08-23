package ningbo.media.web.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SystemSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		//String sessionId = session.getId();
		System.out.println(getTime() + " sessionId:" + session.getId());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		String sessionId = session.getId();
		UserSessions.getInstance().removeUserSession(sessionId);
	}

	private String getTime() {
		return new Date(System.currentTimeMillis()).toString();
	}
}
