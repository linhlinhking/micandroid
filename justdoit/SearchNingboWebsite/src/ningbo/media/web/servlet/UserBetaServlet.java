package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.web.util.SqlLiteJDBC;

public class UserBetaServlet extends HttpServlet {

	private static final long serialVersionUID = -1947572836604058574L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		try {
			SqlLiteJDBC slj = new SqlLiteJDBC();
			slj.saveUser(username, email);
			writer.println("success");
		} catch (Exception e) {
			writer.println("failure");
			e.printStackTrace();
		}
	}

}
