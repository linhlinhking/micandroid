package ningbo.media.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -3168481919592988082L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			response.setContentType("text/plain");
			//得到所有的cookie
			Cookie[] cookies = request.getCookies();
			if(null != cookies && cookies.length > 0){
				for (Cookie cookie : cookies) {
					if("uid".equals(cookie.getName())){
						//解码
						//String userid = new String(Base64.decode(cookie.getValue().getBytes()));
						//SystemUserAPI api = new SystemUserAPI();
						//String res = api.show(userid);
						//JSONObject json = new JSONObject(res) ;
						//PrintWriter writer = response.getWriter();
				        //writer.println(json);
					}
				}
			}else{
				response.sendRedirect("../index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

}
