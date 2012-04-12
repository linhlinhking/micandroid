package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.web.api.CategoryAPI;

import org.json.JSONObject;

public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = -3168481919592988082L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			CategoryAPI api = new CategoryAPI();
			String res = api.showAllCategory();
			JSONObject json = new JSONObject(res) ;
			PrintWriter writer = response.getWriter();
	        writer.println(json);
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
