package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("<title>搜索宁波 - So Ningbo</title>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script src=\"scripts/jquery-1.7.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"scripts/so_ningbo.js\" type=\"text/javascript\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"http://api.map.baidu.com/api?v=1.2\"></script>\n");
      out.write("<link href=\"global.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body style=\"background-image:url(images/bg.png); background-repeat:repeat;\">\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/check_email.jsp", out, false);
      out.write("\n");
      out.write("  \n");
      out.write("  <div class=\"whitebar\"></div>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write(" \n");
      out.write(" \n");
      out.write("  <div class=\"header\" style=\"height:90px\">\n");
      out.write("  \n");
      out.write("  <div style=\"padding-top:22px; padding-bottom:22px; float:left\"> <a href=\"http://www.soningbo.com/home.jsp\"><img src=\"images/logo.png\" alt=\"搜索宁波 - So Ningbo\" name=\"logo\" width=\"180\" height=\"46\" id=\"logo\" style=\"display:block;\" /></a>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  <div style=\"float:left; width:360px; padding-left:60px; padding-top:22px; display:block; position:relative\">\n");
      out.write("  <input type=\"text\" class=\"search_box\" value=\"搜索\"/>\n");
      out.write("    \n");
      out.write("  </div>\n");
      out.write("   <div style=\"float:left; width:60px; padding-top:27px; display:block; position:relative; left:-77px\">\n");
      out.write("  <input type=\"image\" name=\"search_btn\" id=\"search_btn\" src=\"images/search_btn.png\" />\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  \n");
      out.write("\n");
      out.write("  </div>\n");
      out.write(" \n");
      out.write("  \n");
      out.write("  <div class=\"content\">\n");
      out.write("  \n");
      out.write("  <div class=\"navigation\">\n");
      out.write("  <div class=\"nav_corner\"></div>\n");
      out.write("  <div class=\"photo\"><a href=\"profile.html\"><img src=\"images/no_photo.png\" width=\"90\" height=\"90\" /></a></div>\n");
      out.write("  \n");
      out.write("  <div class=\"nav_short\">\n");
      out.write("    <div class=\"nav_short_txt\">地点</div></div>\n");
      out.write("  \n");
      out.write("  <div class=\"nav_off\">\n");
      out.write("  <div class=\"icon\">\n");
      out.write("  <img src=\"images/search_icon.png\" width=\"21\" height=\"22\" />\n");
      out.write("  </div>\n");
      out.write("  <div class=\"nav_txt\">\n");
      out.write(" 搜索\n");
      out.write("  </div>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  \n");
      out.write("  <div class=\"nav_off\">\n");
      out.write("  <div class=\"icon\">\n");
      out.write("  <img src=\"images/find_icon.png\" width=\"22\" height=\"21\" />\n");
      out.write("  </div>\n");
      out.write("  <div class=\"nav_txt\">\n");
      out.write("查找\n");
      out.write("  </div>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("  <div class=\"nav_off\">\n");
      out.write("  <div class=\"icon\">\n");
      out.write("  <img src=\"images/favorites_icon.png\" width=\"22\" height=\"22\" />\n");
      out.write("  </div>\n");
      out.write("  <div class=\"nav_txt\">\n");
      out.write("收藏夹  </div>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("    <div class=\"nav_off\">\n");
      out.write("  <div class=\"icon\">\n");
      out.write("  <img src=\"images/favorites_icon.png\" width=\"22\" height=\"22\" />\n");
      out.write("  </div>\n");
      out.write("  <div class=\"nav_txt\">\n");
      out.write("<a href=\"manage/location/addLocation.html\">添加地区</a>  </div>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  <div class=\"nav_short\"><div class=\"nav_short_txt\">社交</div></div>\n");
      out.write("  \n");
      out.write("   <div class=\"nav_off\">\n");
      out.write("  <div class=\"icon\">\n");
      out.write("  <img src=\"images/friends_icon.png\" width=\"22\" height=\"22\" />\n");
      out.write("  </div>\n");
      out.write("  <div class=\"nav_txt\">\n");
      out.write(" 好友  </div>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  \n");
      out.write("   <div class=\"nav_off\">\n");
      out.write("  <div class=\"icon\">\n");
      out.write("  <img src=\"images/events_icon.png\" width=\"22\" height=\"22\" />\n");
      out.write("  </div>\n");
      out.write("  <div class=\"nav_txt\">\n");
      out.write(" 事件  </div>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  \n");
      out.write("   <div class=\"nav_off\">\n");
      out.write("  <div class=\"icon\">\n");
      out.write("  <img src=\"images/where_icon.png\" width=\"22\" height=\"22\" />\n");
      out.write("  </div>\n");
      out.write("  <div class=\"nav_txt\">\n");
      out.write(" 你在哪里 </div>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("   <div class=\"nav_short_bot\"></div>\n");
      out.write("  \n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  <div class=\"display\">\n");
      out.write("  \n");
      out.write("  <div id=\"map_container\"></div>\n");
      out.write("  <div id=\"map_bar\"></div>\n");
      out.write("  <div id=\"list_container\"></div>\n");
      out.write("  \n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("\n");
      out.write(" \n");
      out.write(" </div>\n");
      out.write(" ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jsp", out, false);
      out.write(" </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("var map = new BMap.Map(\"map_container\");            // 创建Map实例\n");
      out.write("var point = new BMap.Point(121.5426935,29.8091635);    // 创建点坐标\n");
      out.write("map.centerAndZoom(point,13);                     // 初始化地图,设置中心点坐标和地图级别。\n");
      out.write("var opts = {anchor: BMAP_ANCHOR_TOP_RIGHT, offset: new BMap.Size(10, 10)};\n");
      out.write("map.addControl(new BMap.NavigationControl(opts));\n");
      out.write("\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
