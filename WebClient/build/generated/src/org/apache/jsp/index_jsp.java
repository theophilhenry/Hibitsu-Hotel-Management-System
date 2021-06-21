package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!doctype html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("    <head>\r\n");
      out.write("        <!-- Required meta tags -->\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Bootstrap CSS -->\r\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n");
      out.write("              integrity=\"sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x\" crossorigin=\"anonymous\">\r\n");
      out.write("\r\n");
      out.write("        <!-- My CSS -->\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"styles/main.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"styles/home.css\">\r\n");
      out.write("\r\n");
      out.write("        <!-- Jquery -->\r\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("        <title>Home</title>\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-light bg-light sticky-top drop-shadow-0-4-10 mb-5\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <!-- Navbar Toggler -->\r\n");
      out.write("                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarTogglerDemo02\"\r\n");
      out.write("                        aria-controls=\"navbarTogglerDemo02\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("                    <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("                </button>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <!-- Navbar  -->\r\n");
      out.write("                <a class=\"navbar-brand rubik-bold color-green\" href=\"#\">日々つ HIBITSU</a>\r\n");
      out.write("                <div class=\"collapse navbar-collapse justify-content-end\" id=\"navbarTogglerDemo02\">\r\n");
      out.write("                    <ul class=\"navbar-nav mb-2 mb-lg-0\">\r\n");
      out.write("                        <li class=\"nav-item me-3\">\r\n");
      out.write("                            <a class=\"nav-link active rubik-bold color-green\" aria-current=\"page\" href=\"index.jsp\">Home</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item me-3\">\r\n");
      out.write("                            <a class=\"nav-link rubik-normal\" href=\"book1.jsp\">Book Now</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        ");
 if (session.getAttribute("idUser") == null) { 
      out.write("\r\n");
      out.write("                        <li class=\"nav-item me-3\">\r\n");
      out.write("                            <a class=\"nav-link rubik-normal\" href=\"login.jsp\">Login</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        ");
 }
      out.write("\r\n");
      out.write("                        ");
 if (session.getAttribute("idUser") != null) { 
      out.write("\r\n");
      out.write("                        <li class=\"nav-item me-3\">\r\n");
      out.write("                            <a class=\"nav-link rubik-normal\" href=\"track-order.jsp\">Track Order</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item me-3\">\r\n");
      out.write("                            <form method='POST' action='login-handler.jsp'>\r\n");
      out.write("                                <input type='hidden' name='command' value='logout'>\r\n");
      out.write("                                <button type='submit' class=\"nav-link rubik-normal\" style='padding-right: .5rem; border: none; background: none;' href=\"login.jsp\">Logout</a>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        ");
 }
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                    <button class=\"btn btn-success background-green\"><a href=\"https://drive.google.com/uc?export=download&id=14c8fMAalEadnSOyytsD2iEqYGuiJCNrG\" \r\n");
      out.write("                                                                        class=\"rubik-bold color-white\" style=\"text-decoration: none;\">Download App</a>\r\n");
      out.write("                    </button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("        ");
 if (session.getAttribute("printLogin") != null) { 
      out.write("\r\n");
      out.write("        <p class=\"rubik-bold color-green mt-5\" style='text-align: center;'>");
 out.println(String.valueOf(session.getAttribute("printLogin"))); 
      out.write("</p>\r\n");
      out.write("        ");
 }
            session.removeAttribute("printLogin");
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div class=\"grid-container grid-container-reverse box-model box-model1\">\r\n");
      out.write("            <div class=\"grid-left\">\r\n");
      out.write("                <p class=\"rubik-bold\" style=\"font-size: 2.2em; line-height: 44px;\">Explore Villas<br>On Bali</p>\r\n");
      out.write("                <p class=\"karla-normal\">We provide you wonderful experiences and excellent services especially our beloved\r\n");
      out.write("                    customers</p>\r\n");
      out.write("                <a href=\"book1.jsp\" class=\"btn btn-success rubik-bold color-white background-green\">Book Now</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"grid-right\">\r\n");
      out.write("                <div id=\"carouselExampleFade\" class=\"carousel slide carousel-fade\" data-bs-ride=\"carousel\">\r\n");
      out.write("                    <div class=\"carousel-inner\">\r\n");
      out.write("                        <div class=\"carousel-item active\">\r\n");
      out.write("                            <img src=\"assets/images/1.png\" class=\"d-block w-100\" alt=\"...\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"carousel-item\">\r\n");
      out.write("                            <img src=\"assets/images/2.png\" class=\"d-block w-100\" alt=\"...\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"carousel-item\">\r\n");
      out.write("                            <img src=\"assets/images/3.png\" class=\"d-block w-100\" alt=\"...\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExampleFade\"\r\n");
      out.write("                            data-bs-slide=\"prev\">\r\n");
      out.write("                        <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\r\n");
      out.write("                        <span class=\"visually-hidden\">Previous</span>\r\n");
      out.write("                    </button>\r\n");
      out.write("                    <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExampleFade\"\r\n");
      out.write("                            data-bs-slide=\"next\">\r\n");
      out.write("                        <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\r\n");
      out.write("                        <span class=\"visually-hidden\">Next</span>\r\n");
      out.write("                    </button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"grid-container box-model box-model2\" style=\"padding: 0;\">\r\n");
      out.write("            <div class=\"grid-left\" style=\"padding: 0;\">\r\n");
      out.write("                <img src=\"assets/images/home2.png\" class=\"img-home2\" alt=\"\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"grid-right\">\r\n");
      out.write("                <p class=\"rubik-bold color-green\">About Us</p>\r\n");
      out.write("                <p class=\"karla-normal\">We came from a small company who is hoping to bring joy to people around the world\r\n");
      out.write("                    as they visit\r\n");
      out.write("                    Bali.<br><br>\r\n");
      out.write("                    Hibitsu is committed to providing a complete accommodation experience for the modern traveler.</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"grid-container grid-container-reverse box-model box-model1\">\r\n");
      out.write("            <div class=\"grid-left\">\r\n");
      out.write("                <p class=\"rubik-bold color-green\">Our Services</p>\r\n");
      out.write("                <p class=\"karla-normal\">When you sign up with us, you will get a lot of benefits. For example :</p>\r\n");
      out.write("                <ul class=\"karla-normal\">\r\n");
      out.write("                    <li>Real-time consultation via chat, audio, or video call</li>\r\n");
      out.write("                    <li>Make reservation anytime and anywhere</li>\r\n");
      out.write("                    <li>Track your order status</li>\r\n");
      out.write("                </ul>\r\n");
      out.write("                ");
 if (session.getAttribute("idUser") == null) { 
      out.write("\r\n");
      out.write("                <a href=\"login.jsp\" class=\"btn btn-outline-success rubik-bold color-green border-green\">Sign Up</a>\r\n");
      out.write("                ");
 }
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"grid-right\">\r\n");
      out.write("                <img src=\"assets/images/home3.png\" class=\"img-home3\" alt=\"\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div class=\"box-model box-model2\" style=\"text-align: center;\">\r\n");
      out.write("            <p class=\"rubik-bold color-green\" style=\"font-size: 1.5em;\">Let's Save The<br>Environment Together</p>\r\n");
      out.write("            <p class=\"karla-normal\">HERE ARE THE RULE TO KEEP THE VILLA HEALTHY</p>\r\n");
      out.write("            <div class=\"env-grid\">\r\n");
      out.write("                <div class=\"env-rule\">\r\n");
      out.write("                    <img src=\"assets/images/env-straw.svg\" alt=\"\">\r\n");
      out.write("                    <p class=\"rubik-bold\">NO PLASTIC<br>STRAWS</p>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"env-rule\">\r\n");
      out.write("                    <img src=\"assets/images/env-bottle.svg\" alt=\"\">\r\n");
      out.write("                    <p class=\"rubik-bold\">NO PLASTIC<br>BOTTLES</p>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"env-rule\">\r\n");
      out.write("                    <img src=\"assets/images/env-recycle.svg\" alt=\"\">\r\n");
      out.write("                    <p class=\"rubik-bold\">RECYCLE</p>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js\"\r\n");
      out.write("                integrity=\"sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4\" crossorigin=\"anonymous\">\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
      out.write("        <script>\r\n");
      out.write("            $(document).ready(function () {\r\n");
      out.write("                $(window).scroll(function () {\r\n");
      out.write("                    var height = $('.grid-container').height();\r\n");
      out.write("                    var scrollTop = $(window).scrollTop();\r\n");
      out.write("\r\n");
      out.write("                    if (scrollTop >= 20) {\r\n");
      out.write("                        $('nav').addClass('solid-nav');\r\n");
      out.write("                    } else {\r\n");
      out.write("                        $('nav').removeClass('solid-nav');\r\n");
      out.write("                    }\r\n");
      out.write("\r\n");
      out.write("                });\r\n");
      out.write("            });\r\n");
      out.write("        </script>\r\n");
      out.write("    </body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
