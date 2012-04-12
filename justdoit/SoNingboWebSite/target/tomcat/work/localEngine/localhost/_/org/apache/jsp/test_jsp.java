package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("<title>搜索宁波 - So Ningbo</title>\n");
      out.write("<script src=\"scripts/jquery-1.7.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"http://192.168.1.105:8080/js/soningbo.api.v1.js\"></script>\n");
      out.write("<link href=\"global.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("$(document).ready(function(){\n");
      out.write("\t$('#sign_in_btn').click(function(){\n");
      out.write("\t\talert(1) ;\n");
      out.write("\t\t//var log_data = function(data) { console.log(data); };\n");
      out.write("\t\t//$.searchningbo_api.api({method:'resource/user/showAll',callback:log_data}) ;\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("  <div class=\"header\" style=\"height:90px\">\n");
      out.write("    \n");
      out.write("    <div class=\"logo\"> \n");
      out.write("    <a href=\"http://www.soningbo.com\"> \n");
      out.write("    <img src=\"images/home_logo.png\" alt=\"搜索宁波 - So Ningbo\" name=\"logo\" width=\"180\" height=\"46\" id=\"logo\" style=\"display:block;\" /> \n");
      out.write("    </a> \n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"sign_in\">\n");
      out.write("    <table width=\"300\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  <tr>\n");
      out.write("    <td>电子邮件 </td>\n");
      out.write("    <td>密码</td>\n");
      out.write("    <td>保持登录</td>\n");
      out.write("    <td width=\"78\">&nbsp;</td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td><input type=\"text\" name=\"email\" id=\"email\" tabindex=\"1\" /></td>\n");
      out.write("    <td><input type=\"password\" name=\"password\" id=\"password\" tabindex=\"1\" /></td>\n");
      out.write("    <td>\n");
      out.write("    \n");
      out.write("    <div class=\"keep_me\">\n");
      out.write("    <div class=\"keep_me_cover\" id=\"keeptrying\"><img src=\"images/keep_me_cover.png\" width=\"36\" height=\"30\" /></div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    </td>\n");
      out.write("    <td width=\"78\">\n");
      out.write("    <div >\n");
      out.write("   \n");
      out.write("    \n");
      out.write("    <div class=\"btn\" id=\"sign_in_btn\" style=\"-moz-box-shadow: 0px 0px 0px 0 #CCC;\n");
      out.write("\t-webkit-box-shadow: 0px 0px 0px 0 #CCC;\n");
      out.write("\tbox-shadow: 0px 0px 0px 0 #CCC;\" >登录 </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td>&nbsp;</td>\n");
      out.write("    <td><div id=\"forgot_password\" style=\"cursor:hand; cursor:pointer\">忘记密码?</div></td>\n");
      out.write("    <td>&nbsp;</td>\n");
      out.write("    <td width=\"78\">&nbsp;</td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("    </div> \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  <div class=\"content\">\n");
      out.write("  \n");
      out.write("  <div class=\"home_video\">\n");
      out.write("  <div style=\"padding-top:50px; font-size:24px; font-weight:bold; color:#333\">您的城市宁波</div>\n");
      out.write("  <div style=\"color:#333333\"> 让搜索成为您生活的一部分！</div>\n");
      out.write("  <div style=\"padding-top:15px\"><img src=\"images/video.jpg\" width=\"430\" height=\"262\" /></div>\n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write("  <div class=\"home_login\">\n");
      out.write("    <div style=\"width:90%; margin: 0 auto; padding-top:50px; font-size:24px; color:#333333\">\n");
      out.write("    注册<br />\n");
      out.write("    <div style=\"font-size:16px\"> 与您的城市自由连接</div>\n");
      out.write("        <div class=\"greybar\" style=\"margin-top:10px\"></div>\n");
      out.write("    <div class=\"whitebar\"></div>\n");
      out.write("    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"padding-top:15px\">\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">昵称:</td>\n");
      out.write("    <td><input type=\"text\" name=\"username\" id=\"username\" tabindex=\"1\" class=\"text_fld\" /></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">真实姓名:</td>\n");
      out.write("    <td><input type=\"text\" name=\"name_cn\" id=\"name_cn\" tabindex=\"1\" class=\"text_fld\" /></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">电子邮件:</td>\n");
      out.write("    <td><input type=\"text\" name=\"remail\" id=\"remail\" tabindex=\"1\" class=\"text_fld\" /></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">密码:</td>\n");
      out.write("    <td><input type=\"password\" name=\"rpassword\" id=\"rpassword\" tabindex=\"1\" class=\"text_fld\" /></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">确认密码:</td>\n");
      out.write("    <td><input type=\"password\" name=\"confirm_password\" id=\"confirm_password\" tabindex=\"1\" class=\"text_fld\" /></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">性别:</td>\n");
      out.write("    <td style=\"font-size:14px;\">\n");
      out.write("    <input type=\"radio\" name=\"gender\" value=\"1\" checked=\"checked\" />男&nbsp;&nbsp;&nbsp;&nbsp;\n");
      out.write("    <input type=\"radio\" name=\"gender\" value=\"0\" />女\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">出生日期:</td>\n");
      out.write("    <td>\n");
      out.write("    <input name=\"birthday\" id=\"birthday\" class=\"Wdate text_fld\" type=\"text\" onfocus=\"WdatePicker({skin:'blueFresh',maxDate:'%y-%M-%d',lang:'auto'})\" value=\"1990-01-01\"/>\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td style=\"text-align:right; font-size:14px; padding-right:15px\">&nbsp;</td>\n");
      out.write("    <td>\n");
      out.write("    \n");
      out.write("    <div id=\"reg_signup_btn\"><div style=\"margin-left:90px;\">注册</div></div>\n");
      out.write("    \n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write(" <div class=\"greybar\" style=\"margin-top:10px\"></div>\n");
      out.write("    <div class=\"whitebar\"></div>\n");
      out.write("    \n");
      out.write("   <div style=\"text-align:center; font-size:12px; padding-top:10px\"> 为您的企业创建页面，点击这里</div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("  \n");
      out.write("  </div>\n");
      out.write("  \n");
      out.write(" ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jsp", out, false);
      out.write("\n");
      out.write(" </div>\n");
      out.write("</div>\n");
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
