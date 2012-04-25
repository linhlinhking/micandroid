package ningbo.media.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ningbo.media.web.api.RequestAPI;
import ningbo.media.web.util.UrlUtil;

public class ProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 6322079829003041504L;
	private static final String TWODEEPURL = "http://localhost:8000/resource/category/first/" ;
	private RequestAPI api = null;

	public void init() {
		api = new RequestAPI();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("UTF-8");
			String url = request.getParameter("url");
			
			String method = request.getParameter("method") ;
			
			// PrintWriter writer = response.getWriter();
			// String json = "" ;
			// if(method.equalsIgnoreCase("GET")){
			// json = api.getResource(url,null) ;
			// }else if(method.equalsIgnoreCase("POST")){
			// json = api.postContent(url, null) ;
			// }else{
			// writer.println("Request Error.") ;
			// }
			// writer.println(json);

			//
			PrintWriter writer = response.getWriter();
			String json = "";

			String[] strs = UrlUtil.getUrlElement(url);
			int deep = 0 ;
			if(null == method){
				deep = strs.length ;
			}
			
			StringBuffer buffer = new StringBuffer() ;
			switch (deep) {
			case 0:
				json = api.getResource(url, null) ;
				break ;
			case 2:
				buffer.append(TWODEEPURL).append(strs[1]) ;
				json = api.getResource(buffer.toString(), null) ;
				break;
			case 3:
				break;
			case 5:
				break;
			case -1:
				json = "System Error." ;
				break;
			default:
				break;
			}
			writer.println(json) ;
			
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
