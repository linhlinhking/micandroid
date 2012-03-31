package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ningbo.media.web.api.CategoryAPI;
import ningbo.media.web.bean.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SecondCategoryServlet extends HttpServlet {

	private static final long serialVersionUID = -3168481919592988082L;

	private Configuration conf;

	private CategoryAPI api;

	public void init() {
		conf = new Configuration();
		conf.setServletContextForTemplateLoading(getServletContext(),
				"template/html/");
		conf.setEncoding(Locale.getDefault(), "UTF-8");
		api = new CategoryAPI();

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("utf-8");
			String id = request.getParameter("secondId");
			PrintWriter writer = response.getWriter();
			List<Location> list = getLocationBySecondId(id) ;
			Map<String, Object> map = new HashMap<String, Object>();
			String ctx = request.getContextPath() ;
			map.put("ctx", ctx) ;
			map.put("secondId", id);
			if(list!=null){
				map.put("locationList", list) ;
			}
			Template template = conf.getTemplate("locationlist.ftl");
			response.setContentType("text/html; charset="
					+ template.getEncoding());

			template.process(map, writer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	private List<Location> getLocationBySecondId(String id) {
		List<Location> list = new ArrayList<Location>() ;
		try {
			String response = api.getLocationsBySecondCategoryId(id);
			JSONObject json = new JSONObject(response);
			
			JSONArray array = json.getJSONArray("location");
			for (int i = 0, j = array.length(); i < j; i++) {
				Location location = new Location() ;
				JSONObject temp = array.getJSONObject(i) ;
				location.setId(Integer.valueOf(temp.getString("id"))) ;
				location.setName_cn(temp.getString("name_cn")) ;
				location.setName_en(temp.getString("name_en")) ;
				
				list.add(location) ;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		return list;
	}
}
