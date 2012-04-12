package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class information_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
 String path = request.getContextPath(); 
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<meta name=\"description\" content=\"soningbo搜索宁波--是一个专门为广大用户服务，用于方便的搜索到用户自己想要找的或者去的任何地址，如车站、餐厅、旅游场景、娱乐场所、公司、旅馆等地方。\">\n");
      out.write("<title>搜索宁波 - So Ningbo</title>\n");
      out.write("<link rel=\"stylesheet\"  type=\"text/css\" href=\"");
      out.print(path );
      out.write("/css/global_test.css\" />\n");
      out.write("<link rel=\"stylesheet\" id=\"cssStyle\" type=\"text/css\" href=\"");
      out.print(path );
      out.write("/css/genral_test.css\" />\n");
      out.write("<script src=\"");
      out.print(path );
      out.write("/scripts/jquery-1.7.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"");
      out.print(path );
      out.write("/scripts/so_ningbo_showcateg.js\" type=\"text/javascript\"></script>s\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("function userAgent(){\n");
      out.write("    var ua = navigator.userAgent;\n");
      out.write("    ua = ua.toLowerCase();\n");
      out.write("    var match = /(webkit)[ \\/]([\\w.]+)/.exec(ua) ||\n");
      out.write("    /(opera)(?:.*version)?[ \\/]([\\w.]+)/.exec(ua) ||\n");
      out.write("    /(msie) ([\\w.]+)/.exec(ua) ||\n");
      out.write("    !/compatible/.test(ua) && /(mozilla)(?:.*? rv:([\\w.]+))?/.exec(ua) ||\n");
      out.write("    [];\n");
      out.write("    //如果需要获取浏览器版本号：match[2]\n");
      out.write("    switch(match[1]){\n");
      out.write("     case \"msie\":      //ie\n");
      out.write("\n");
      out.write("      document.getElementById(\"cssStyle\").href=\"");
      out.print(path );
      out.write("/css/ie_test.css\";\n");
      out.write("      break;\n");
      out.write("     case \"webkit\":     //safari or chrome\n");
      out.write("\t case \"opera\":      //opera\n");
      out.write("\t case \"mozilla\":    //Firefox\n");
      out.write("     document.getElementById(\"cssStyle\").href=\"");
      out.print(path );
      out.write("/css/genral_test.css\";\n");
      out.write("      break;\n");
      out.write("     default:    \n");
      out.write("\t document.getElementById(\"cssStyle\").href=\"");
      out.print(path );
      out.write("/css/ie_test.css\";\n");
      out.write("      break;\n");
      out.write("    }\n");
      out.write("}\n");
      out.write("userAgent();\n");
      out.write("        </script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body><div class=\"container\">\n");
      out.write("<header><div id=\"header\">\n");
      out.write("<div class=\"logo\"><span>English</span></div></div></header>\n");
      out.write("<div class=\"content\">\n");
      out.write("<section>\n");
      out.write("<div class=\"section_st\" id=\"section_st\">\n");
      out.write("</div></section>\n");
      out.write("<section>\n");
      out.write("<div class=\"section_nd\" id=\"section_nd\">\n");
      out.write("</div></section></div>\n");
      out.write("<footer><div id=\"footer\">\n");
      out.write("<div class=\"copyright\"><img src=\"");
      out.print(path );
      out.write("/images/nm_logo.png\" alt=\"宁波商外文化传媒\"/><br/>© 2012 宁波商外文化传媒</div>\n");
      out.write("<div class=\"message\">客服电话：0574-87200625（个人） 0574-83860743（企业） （按当地市话标准计费）<br/><span>宁波ICP证000000号</span></div>\n");
      out.write("<div class=\"report\"><a href=\"http://net.china.cn/\" target=\"_blank\"><img src=\"");
      out.print(path );
      out.write("/images/ciirc.png\" alt=\"不良信息举报\"/><br/>不良信息举报中心</a></div></div></footer></div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
