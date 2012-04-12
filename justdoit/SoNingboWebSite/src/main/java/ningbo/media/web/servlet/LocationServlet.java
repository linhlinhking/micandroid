package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.web.api.LocationAPI;

import org.json.JSONObject;

public class LocationServlet extends HttpServlet {

	private static final long serialVersionUID = 6322079829003041504L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
//			response.setContentType("text/plain");
//			String name_en = request.getParameter("name_en");
//			String name_cn = request.getParameter("name_cn");
//			String address_en = request.getParameter("address_en");
//			String address_cn = request.getParameter("address_cn");
//			String telephone = request.getParameter("telephone");
//			String latitude = request.getParameter("latitude");
//			String longitude = request.getParameter("longitude");
//			String photo_path = request.getParameter("photo_path");
//			String[] category_id = request.getParameterValues("category_id");
//			String key = request.getParameter("key");
//			LocationAPI api = new LocationAPI();
//			String url = "http://localhost:8080/resource/location/add";
//			String input = "{'name_en':" + name_en + ", name_cn':" + name_cn
//					+ ", address_en':" + address_en + ", address_cn':" + address_cn
//					+ ", telephone':" + telephone + ", latitude':" + latitude
//					+ ", longitude':" + longitude + ", photo_path':" + photo_path
//					+ ", key':" + key + ", category_id':" + category_id + "}";
//			String res = api.jerseyClient(url, "method", input, null);
//			JSONObject json = new JSONObject(res);
//			String code = json.getString("code");
//			PrintWriter writer = response.getWriter();
//			writer.println(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
