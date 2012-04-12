package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ningbo.media.web.api.LocationAPI;

public class LocationCountServlet extends HttpServlet {

	private static final long serialVersionUID = 6322079829003041504L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/plain");
			LocationAPI api = new LocationAPI();
			String res = api.getLocationCount();
			PrintWriter writer = response.getWriter();
			JSONObject json = new JSONObject(res) ;
			String count = json.getString("count") ;
			writer.println(count);
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
