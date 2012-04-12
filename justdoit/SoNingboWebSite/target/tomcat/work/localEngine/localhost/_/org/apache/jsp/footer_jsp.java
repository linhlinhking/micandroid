package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class footer_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write(" <div class=\"footer\" style=\"margin-top:15px\">\n");
      out.write("   \n");
      out.write("    <div class=\"footer_display\">\n");
      out.write("      <div class=\"copyright\"> <br />\n");
      out.write("        <img src=\"images/nm_logo.png\" width=\"180\" height=\"41\" alt=\"宁波商外文化传媒\" /><br />\n");
      out.write("       \n");
      out.write("       <div style=\"margin-left:20px; margin-top:5px\"> © 2012 宁波商外文化传媒 </div>\n");
      out.write("      </div>\n");
      out.write("      \n");
      out.write("      <a href=\"http://net.china.cn/\" target=\"_blank\" style=\"text-decoration:none; cursor:hand; cursor:pointer;\" >\n");
      out.write("      <div style=\"float:right; width:120px; margin-top:25px; display:block\">\n");
      out.write("      \n");
      out.write("      <div style=\"margin-left:15px\">\n");
      out.write("      <img src=\"images/ciirc.png\" width=\"50\" height=\"40\" />\n");
      out.write("      </div>\n");
      out.write("      \n");
      out.write("      <div style=\"text-decoration:none\">\n");
      out.write("       不良信息举报中心\n");
      out.write("       </div>\n");
      out.write("      </div>\n");
      out.write("      </a>\n");
      out.write("      \n");
      out.write("      <div style=\"float:right; margin-top:25px;margin-left:10; margin-right:130px; display:block\">客服电话：0574-87200625（个人） 0574-83860743（企业） （按当地市话标准计费）<br />\n");
      out.write(" 宁波ICP证000000号</div>\n");
      out.write("      \n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("  \n");
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
