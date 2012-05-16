package ningbo.media.core.security.resource;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import ningbo.media.entity.SystemUser;
import ningbo.media.entity.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * Description:
 * @author Devon.Ning
 * @2012-4-23下午02:53:25
 * @version 1.0
 * <p>Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.</p>
 */
public class SecurityUtils {

	/**
	 * @description:取得当前用户, 返回值为SpringSecurity的SystemUser类或其子类,
	 *                      如果当前用户未登录则返回null.
	 * @param <T>
	 * @return
	 */
	public static <T extends SystemUser> T getCurrentUser() {
		try {
			Authentication auth = getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if (principal instanceof UserDetailsImpl
						|| principal instanceof SystemUser) {
					return extracted(principal);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static <T extends UserDetails> T getCurrentUserDetails() {
		try {
			Authentication authentication = getAuthentication();
			if (authentication != null) {
				Object principal = authentication.getPrincipal();
				if (principal instanceof UserDetails) {
					return extracted(principal);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description:取得当前用户的登录名, 如果当前用户未登录则返回空字符串.
	 * @return CurrentUserName
	 * 
	 */
	public static String getCurrentUserName() {
		try {
			Authentication authentication = getAuthentication();
			if (authentication != null && authentication.getPrincipal() != null) {
				return authentication.getName();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * @Description:取得当前用户登录IP, 如果当前用户未登录则返回空字符串.
	 * @return Login User IP Address
	 */
	public static String getCurrentUserIPAddress() {
		try {
			Authentication auth = getAuthentication();
			if (auth != null) {
				Object details = auth.getDetails();
				if (details instanceof WebAuthenticationDetails) {
					WebAuthenticationDetails temp = (WebAuthenticationDetails) details;
					return temp.getRemoteAddress();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * @Description:判断用户是否拥有角色, 如果用户拥有参数中的任意一个角色则返回true.
	 * @param roles
	 * @return true or false
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasAnyRole(String[] roles) {
		try {
			Authentication auth = getAuthentication();
			
			Collection authorities = auth.getAuthorities();
			Collection<GrantedAuthority> granteds = authorities;
			//Collection<GrantedAuthority> granteds = auth.getAuthorities();
			for (String role : roles) {
				for (GrantedAuthority authority : granteds) {
					if (role.equals(authority.getAuthority())) {
						return true;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * @Description:将UserDetails保存到Security Context.
	 * 
	 * @param userDetails
	 *            已初始化好的用户信息.
	 * @param request
	 *            用于获取用户IP地址信息.
	 */
	public static void saveUserDetailsToContext(UserDetails userDetails,
			HttpServletRequest request) {
		try {
			PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
					userDetails, userDetails.getPassword(), userDetails
							.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetails(request));
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} catch (Exception ex) {
			ex.printStackTrace() ;
		}
	}

	private static Authentication getAuthentication() {
		try {
			SecurityContext context = SecurityContextHolder.getContext();
			if (context != null) {
				return context.getAuthentication();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings( { "unchecked" })
	private static <T> T extracted(Object principal) {
		return (T) principal;
	}
}
