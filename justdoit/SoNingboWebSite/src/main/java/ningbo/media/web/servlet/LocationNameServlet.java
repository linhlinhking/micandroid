package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.web.api.LocationAPI;

public class LocationNameServlet extends HttpServlet {

	private static final long serialVersionUID = 6322079829003041504L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			String name_cn = request.getParameter("name_cn");
			LocationAPI api = new LocationAPI();
			String res = api.showLocations(name_cn);
			PrintWriter writer = response.getWriter();
			writer.println(res);
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
