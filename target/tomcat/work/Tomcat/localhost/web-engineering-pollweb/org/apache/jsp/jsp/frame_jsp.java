/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-12-03 18:59:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class frame_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html dir=\"ltr\" lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <!-- Tell the browser to be responsive to screen width -->\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"description\" content=\"\">\r\n");
      out.write("    <meta name=\"author\" content=\"\">\r\n");
      out.write("    <!-- Favicon icon -->\r\n");
      out.write("    <link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"../resources/assets/images/favicon.png\">\r\n");
      out.write("    <title>PollWeb - Sondaggi</title>\r\n");
      out.write("    <!-- Custom CSS -->\r\n");
      out.write("    <link href=\"../resources/dist/css/style.min.css\" rel=\"stylesheet\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div id=\"main-wrapper\" data-navbarbg=\"skin6\" data-theme=\"light\" data-layout=\"vertical\" data-sidebartype=\"full\" data-boxed-layout=\"full\">\r\n");
      out.write("\r\n");
      out.write("    <header class=\"topbar\" data-navbarbg=\"skin6\">\r\n");
      out.write("        <nav class=\"navbar top-navbar navbar-expand-md navbar-light\">\r\n");
      out.write("            <div class=\"navbar-header\" data-logobg=\"skin5\">\r\n");
      out.write("                <!-- This is for the sidebar toggle which is visible on mobile only -->\r\n");
      out.write("                <a class=\"nav-toggler waves-effect waves-light d-block d-md-none\" href=\"javascript:void(0)\">\r\n");
      out.write("                    <i class=\"ti-menu ti-close\"></i>\r\n");
      out.write("                </a>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"navbar-brand\">\r\n");
      out.write("                    <a href=\"#\" class=\"logo\">\r\n");
      out.write("                        <!-- Logo icon -->\r\n");
      out.write("                        <b class=\"logo-icon\">\r\n");
      out.write("\r\n");
      out.write("                            <img src=\"../resources/assets/images/logo-icon.png\" alt=\"homepage\" class=\"dark-logo\" />\r\n");
      out.write("                            <!-- Light Logo icon -->\r\n");
      out.write("                            <img src=\"../resources/assets/images/logo-light-icon.png\" alt=\"homepage\" class=\"light-logo\" />\r\n");
      out.write("                        </b>\r\n");
      out.write("                        <!--End Logo icon -->\r\n");
      out.write("                        <!-- Logo text -->\r\n");
      out.write("                        <span class=\"logo-text\">\r\n");
      out.write("                                <!-- dark Logo text -->\r\n");
      out.write("                                <img src=\"../resources/assets/images/logo-text.png\" alt=\"homepage\" class=\"dark-logo\" />\r\n");
      out.write("                            <!-- Light Logo text -->\r\n");
      out.write("                                <img src=\"../resources/assets/images/logo-light-text.png\" class=\"light-logo\" alt=\"homepage\" />\r\n");
      out.write("                            </span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <a class=\"topbartoggler d-block d-md-none waves-effect waves-light\" href=\"javascript:void(0)\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\"\r\n");
      out.write("                   aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("                    <i class=\"ti-more\"></i>\r\n");
      out.write("                </a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"navbar-collapse collapse\" id=\"navbarSupportedContent\" data-navbarbg=\"skin6\">\r\n");
      out.write("\r\n");
      out.write("                <ul class=\"navbar-nav float-left mr-auto\">\r\n");
      out.write("\r\n");
      out.write("                    <li class=\"nav-item search-box\">\r\n");
      out.write("                        <a class=\"nav-link waves-effect waves-dark\" href=\"javascript:void(0)\">\r\n");
      out.write("                            <div class=\"d-flex align-items-center\">\r\n");
      out.write("                                <i class=\"mdi mdi-magnify font-20 mr-1\"></i>\r\n");
      out.write("                                <div class=\"ml-1 d-none d-sm-block\">\r\n");
      out.write("                                    <span>Search</span>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </a>\r\n");
      out.write("                        <form class=\"app-search position-absolute\">\r\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Search &amp; enter\">\r\n");
      out.write("                            <a class=\"srh-btn\">\r\n");
      out.write("                                <i class=\"ti-close\"></i>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <ul class=\"navbar-nav float-right\">\r\n");
      out.write("\r\n");
      out.write("                    <li class=\"nav-item dropdown\">\r\n");
      out.write("                        <a class=\"nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic\" href=\"\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("                            <span>Your_Username</span>\r\n");
      out.write("                            <i class=\"m-r-0 mdi mdi-menu-down\"></i>\r\n");
      out.write("                        </a>\r\n");
      out.write("                        <div class=\"dropdown-menu dropdown-menu-right user-dd animated\">\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"javascript:void(0)\"><i class=\"ti-user m-r-5 m-l-5\"></i> My Profile</a>\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"javascript:void(0)\"><i class=\"ti-wallet m-r-5 m-l-5\"></i> My Balance</a>\r\n");
      out.write("                            <a class=\"dropdown-item\" href=\"javascript:void(0)\"><i class=\"ti-email m-r-5 m-l-5\"></i> Inbox</a>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </li>\r\n");
      out.write("\r\n");
      out.write("                </ul>\r\n");
      out.write("\r\n");
      out.write("                <ul class=\"navbar-nav float-right\">\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <img src=\"\" alt=\"user\" class=\"rounded-circle\" width=\"31\">\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("    </header>\r\n");
      out.write("\r\n");
      out.write("    <aside class=\"left-sidebar\" data-sidebarbg=\"skin5\">\r\n");
      out.write("        <!-- Sidebar scroll-->\r\n");
      out.write("        <div class=\"scroll-sidebar\">\r\n");
      out.write("            <!-- Sidebar navigation-->\r\n");
      out.write("            <nav class=\"sidebar-nav\">\r\n");
      out.write("                <ul id=\"sidebarnav\">\r\n");
      out.write("                    <li class=\"sidebar-item\">\r\n");
      out.write("                        <a class=\"sidebar-link waves-effect waves-dark sidebar-link\" href=\"../index.jsp\" aria-expanded=\"false\">\r\n");
      out.write("                            <i class=\"mdi mdi-av-timer\"></i>\r\n");
      out.write("                            <span class=\"hide-menu\">Dashboard</span>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"sidebar-item\">\r\n");
      out.write("                        <a class=\"sidebar-link waves-effect waves-dark sidebar-link\" href=\"pages-profile.html\" aria-expanded=\"false\">\r\n");
      out.write("                            <i class=\"mdi mdi-account-network\"></i>\r\n");
      out.write("                            <span class=\"hide-menu\">Profile</span>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"sidebar-item\">\r\n");
      out.write("                        <a class=\"sidebar-link waves-effect waves-dark sidebar-link\" href=\"table-basic.html\" aria-expanded=\"false\">\r\n");
      out.write("                            <i class=\"mdi mdi-border-none\"></i>\r\n");
      out.write("                            <span class=\"hide-menu\">Table</span>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"sidebar-item\">\r\n");
      out.write("                        <a class=\"sidebar-link waves-effect waves-dark sidebar-link\" href=\"icon-material.html\" aria-expanded=\"false\">\r\n");
      out.write("                            <i class=\"mdi mdi-face\"></i>\r\n");
      out.write("                            <span class=\"hide-menu\">Icon</span>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"sidebar-item\">\r\n");
      out.write("                        <a class=\"sidebar-link waves-effect waves-dark sidebar-link\" href=\"starter-kit.html\" aria-expanded=\"false\">\r\n");
      out.write("                            <i class=\"mdi mdi-file\"></i>\r\n");
      out.write("                            <span class=\"hide-menu\">Blank</span>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li class=\"sidebar-item\">\r\n");
      out.write("                        <a class=\"sidebar-link waves-effect waves-dark sidebar-link\" href=\"error-404.html\" aria-expanded=\"false\">\r\n");
      out.write("                            <i class=\"mdi mdi-alert-outline\"></i>\r\n");
      out.write("                            <span class=\"hide-menu\">404</span>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </nav>\r\n");
      out.write("            <!-- End Sidebar navigation -->\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- End Sidebar scroll-->\r\n");
      out.write("    </aside>\r\n");
      out.write("\r\n");
      out.write("<!-- TAG DIV, BODY E HTML PRESENTE NEL FOOTER-->");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
