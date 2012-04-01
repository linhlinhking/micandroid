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
import ningbo.media.web.bean.FirstCategory;
import ningbo.media.web.bean.SecondCategory;

import org.json.JSONArray;
import org.json.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FirstCategoryServlet extends HttpServlet {

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
			String id = request.getParameter("id");
			PrintWriter writer = response.getWriter();
			List<SecondCategory> list = getSecondCategoryById(id) ;
			FirstCategory firstCategory = getFirstCategoryById(id) ;
			
			Map<String, Object> map = new HashMap<String, Object>();
			String ctx = request.getContextPath() ;
			map.put("ctx", ctx) ;
			map.put("categroyId", id);
			map.put("firstCategory", firstCategory) ;
			map.put("secondCategorys", list) ;
			
			Template template = conf.getTemplate("secondcategory.ftl");
			response.setContentType("text/html; charset="
					+ template.getEncoding());

			template.process(map, writer);
			//request.getRequestDispatcher("/category/meirongmeifa").forward(request, response) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	private List<SecondCategory> getSecondCategoryById(String id) {
		List<SecondCategory> list = new ArrayList<SecondCategory>() ;
		try {
			String response = api.showCategory(id);
			JSONObject json = new JSONObject(response);
			
			JSONArray array = json.getJSONArray("secondCategory");
			for (int i = 0, j = array.length(); i < j; i++) {
				SecondCategory sc = new SecondCategory() ;
				JSONObject temp = array.getJSONObject(i) ;
				sc.setId(Integer.valueOf(temp.getString("id"))) ;
				sc.setName_cn(temp.getString("name_cn")) ;
				sc.setName_en(temp.getString("name_en")) ;
				
				list.add(sc) ;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		return list;
	}
	
	
	private FirstCategory getFirstCategoryById(String id){
		FirstCategory first = new FirstCategory() ;
		try {
			String response = api.getFirstCategoryById(id) ;
			if(null == response || response.length() < 0){
				return null ;
			}
			JSONObject json = new JSONObject(response) ;
			first.setId(Integer.valueOf(json.getString("id"))) ;
			first.setName_cn(json.getString("name_cn")) ;
			first.setName_en(json.getString("name_en")) ;
			first.setDescription(json.getString("description")) ;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		
		return first ;
	}
}
