package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.web.api.SystemUserAPI;

import org.json.JSONObject;

public class ShowSystemUserServlet extends HttpServlet {

	private static final long serialVersionUID = -3168481919592988082L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter writer = response.getWriter();
			response.setContentType("text/plain");
			String userid = "";
			SystemUserAPI api = new SystemUserAPI();
			String res = api.show(userid);
			JSONObject json = new JSONObject(res) ;
			String isManager = json.getString("isManager") ;
			Cookie[] cookie = request.getCookies();
			if(null == cookie[0].getName()){
				 writer.println("user not login");
			}else{
				if(cookie[0].getName().equals("userid")){
					userid = cookie[0].getValue();
				}
			}
			
//			if("0".equals(code) && null != json_userid){
//			    Cookie cookie = new Cookie("userid", userid); 
//			    cookie.setDomain("soningbo");
//                response.addCookie(cookie);
//			}
			
	        writer.println(isManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
