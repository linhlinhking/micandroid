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

public class SystemUserServlet extends HttpServlet {

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
			response.setContentType("text/plain");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String key = request.getParameter("key");
			String deviceId = request.getParameter("device_id");
			SystemUserAPI api = new SystemUserAPI();
			String res = api.login(username, password, key, deviceId);
			JSONObject json = new JSONObject(res) ;
			String code = json.getString("code") ;
			if(!code.endsWith("5")){
				String userid = json.getString("userId");
				//将获得的用户ID加密
				//String base64uid = new String(Base64.encode(userid));
//				String strOut2 = new String(Base64.decode(strOut.getBytes()));
//				System.out.println(strOut2);
				
				Cookie[] cookies = request.getCookies();
				if(null != cookies && cookies.length > 0){
					
				}
				
				if("0".equals(code) && null != userid){
				    //Cookie uid = new Cookie("uid", base64uid); 
//				    uid.setDomain("soningbo.com");
	                //response.addCookie(uid);
				}
			}
			
			PrintWriter writer = response.getWriter();
	        writer.println(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
