/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-12-16 15:47:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class viewSurveys_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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

      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "frame.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"page-wrapper\">\r\n");
      out.write("\r\n");
      out.write("    <div class=\"page-breadcrumb\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-5 align-self-center\">\r\n");
      out.write("                <h4 class=\"page-title\">I tuoi sondaggi.</h4>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container-fluid\">\r\n");
      out.write("\r\n");
      out.write("        <div class=\"addSurvey-button\">\r\n");
      out.write("            <a href=\"createSurvey\">\r\n");
      out.write("                <img class=\"new-survey-img\" src=\"./resources/images/icons/addSurvey.png\"/>\r\n");
      out.write("            </a>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-12\">\r\n");
      out.write("                <div class=\"card\">\r\n");
      out.write("                    <div class=\"card-body\">\r\n");
      out.write("                        <h4 class=\"card-title\">Aiuto:</h4>\r\n");
      out.write("                        <h5 class=\"card-subtitle\">Verde: pubblico-aperto | Viola: privato-aperto | Rosso: chiuso | Grigio chiaro: salvato | Blu: ?</h5>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"table-responsive table-hover\">\r\n");
      out.write("                        <table class=\"table\">\r\n");
      out.write("                            <thead>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <th scope=\"col\">Titolo</th>\r\n");
      out.write("                                <th scope=\"col\">Stato</th>\r\n");
      out.write("                                <th scope=\"col\">Descrizione</th>\r\n");
      out.write("                                <th scope=\"col\">Modifica<i class=\"m-r-0 mdi mdi-lead-pencil\"></i></th>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            </thead>\r\n");
      out.write("\r\n");
      out.write("                            <tbody>\r\n");
      out.write("                            ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                            </tbody>\r\n");
      out.write("                        </table>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div><!--END CARD-->\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
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

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /jsp/viewSurveys.jsp(42,28) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/jsp/viewSurveys.jsp(42,28) '${list}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${list}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /jsp/viewSurveys.jsp(42,28) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("item");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                            <tr class=\"table-");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.statusCss}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\">\r\n");
          out.write("                                <th scope=\"row\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</th>\r\n");
          out.write("                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.status}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write(' ');
          out.write('-');
          out.write(' ');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.privacy}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.opening}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("                                <td class=\"edit-td\">\r\n");
          out.write("                                    <ul class=\"edit-nav\">\r\n");
          out.write("                                        <li class=\"nav-item dropdown\">\r\n");
          out.write("                                            <a class=\"nav-link dropdown-toggle text-muted waves-effect waves-dark edit-survey\" href=\"\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
          out.write("                                                <span style=\"display:flex;align-self:center;\">Scegli</span>\r\n");
          out.write("                                                <i class=\"m-r-0 mdi mdi-menu-down\"></i>\r\n");
          out.write("                                            </a>\r\n");
          out.write("                                            <div class=\"dropdown-menu dropdown-menu-right user-dd animated\">\r\n");
          out.write("                                                <a class=\"dropdown-item\" href=\"javascript:void(0)\"><i class=\"ti-agenda m-r-5 m-l-5\"></i> Modifica Partecipanti</a>\r\n");
          out.write("                                                <a class=\"dropdown-item\" href=\"surveyEditor?survey=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\"><i class=\"ti-write m-r-5 m-l-5\"></i> Modifica Contenuto</a>\r\n");
          out.write("                                                <a class=\"dropdown-item\" href=\"javascript:void(0)\"><i class=\"ti-eye m-r-5 m-l-5\"></i> Preview Sondaggio</a>\r\n");
          out.write("                                                <a class=\"dropdown-item\" href=\"javascript:void(0)\"><i class=\"ti-search m-r-5 m-l-5\"></i> Vedi Risultato</a>\r\n");
          out.write("                                                <a class=\"dropdown-item\" href=\"javascript:void(0)\"><i class=\"ti-close m-r-5 m-l-5\"></i> Chiudi Sondaggio</a>\r\n");
          out.write("                                            </div>\r\n");
          out.write("                                        </li>\r\n");
          out.write("                                    </ul>\r\n");
          out.write("                                </td>\r\n");
          out.write("                            </tr>\r\n");
          out.write("                            ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
