package ningbo.media.core.security;

import javax.servlet.http.HttpSessionEvent;

import ningbo.media.core.security.cache.OnlineSystemUserCache;
import ningbo.media.core.security.resource.SecurityUtils;
import ningbo.media.entity.SystemUser;

import org.springframework.security.web.session.HttpSessionEventPublisher;


public class OnlineSystemUserEventPublisher extends HttpSessionEventPublisher {

	public static final String LOGIN_USER_NAME = "LOGIN_USERNAME" ;
	
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		SystemUser user = SecurityUtils.getCurrentUser() ;
		if(user != null){
			OnlineSystemUserCache.put(user) ;
			event.getSession().setAttribute(LOGIN_USER_NAME, user.getUsername()) ;
		}
		super.sessionCreated(event);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		SystemUser user = SecurityUtils.getCurrentUser() ;
		if(user != null){
			OnlineSystemUserCache.remove(user.getUsername()) ;
		}else{
			String username = (String)event.getSession().getAttribute(LOGIN_USER_NAME) ;
			if(username != null){
				OnlineSystemUserCache.remove(username) ;
			}
		}
		super.sessionDestroyed(event);
	}

	
}
