package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.web.api.SystemUserAPI;

import org.json.JSONObject;

public class SystemUserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -1947572836604058574L;

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
			String name_cn = request.getParameter("name_cn");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			String key = request.getParameter("key");
			SystemUserAPI api = new SystemUserAPI();
			String res = api.register(username, name_cn, email, password, gender, birthday, key);
			JSONObject json = new JSONObject(res) ;
			String code = json.getString("code") ;
			PrintWriter writer = response.getWriter();
	        writer.println(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
