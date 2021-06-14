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

      out.write("\n");
      out.write("<!doctype html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <!-- Required meta tags -->\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("        <!-- Bootstrap CSS -->\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n");
      out.write("              integrity=\"sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x\" crossorigin=\"anonymous\">\n");
      out.write("\n");
      out.write("        <!-- My CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"styles/main.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"styles/home.css\">\n");
      out.write("\n");
      out.write("        <!-- Jquery -->\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("\n");
      out.write("        <title>Home</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-light bg-light sticky-top drop-shadow-0-4-10\">\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <!-- Navbar Toggler -->\n");
      out.write("                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarTogglerDemo02\"\n");
      out.write("                        aria-controls=\"navbarTogglerDemo02\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("                    <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("                </button>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <!-- Navbar  -->\n");
      out.write("                <a class=\"navbar-brand rubik-bold color-green\" href=\"#\">日々つ HIBITSU</a>\n");
      out.write("                <div class=\"collapse navbar-collapse justify-content-end\" id=\"navbarTogglerDemo02\">\n");
      out.write("                    <ul class=\"navbar-nav mb-2 mb-lg-0\">\n");
      out.write("                        <li class=\"nav-item me-3\">\n");
      out.write("                            <a class=\"nav-link active rubik-bold color-green\" aria-current=\"page\" href=\"index.jsp\">Home</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"nav-item me-3\">\n");
      out.write("                            <a class=\"nav-link rubik-normal\" href=\"book1.jsp\">Book Now</a>\n");
      out.write("                        </li>\n");
      out.write("                        ");
 if (session.getAttribute("idUser") == null) { 
      out.write("\n");
      out.write("                        <li class=\"nav-item me-3\">\n");
      out.write("                            <a class=\"nav-link rubik-normal\" href=\"login.jsp\">Login</a>\n");
      out.write("                        </li>\n");
      out.write("                        ");
 }
      out.write("\n");
      out.write("                        ");
 if (session.getAttribute("idUser") != null) { 
      out.write("\n");
      out.write("                        <li class=\"nav-item me-3\">\n");
      out.write("                            <a class=\"nav-link rubik-normal\" href=\"track-order.jsp\">Track Order</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"nav-item me-3\">\n");
      out.write("                            <form method='POST' action='login-handler.jsp'>\n");
      out.write("                                <input type='hidden' name='command' value='logout'>\n");
      out.write("                                <button type='submit' class=\"nav-link rubik-normal\" style='padding-right: .5rem; padding-left: .5rem; padding: .5rem 1rem; border: none; background: none;' href=\"login.jsp\">Logout</a>\n");
      out.write("                            </form>\n");
      out.write("                        </li>\n");
      out.write("                        ");
 }
      out.write("\n");
      out.write("                    </ul>\n");
      out.write("                    <form>\n");
      out.write("                        <button class=\"btn btn-success rubik-bold color-white background-green\" type=\"submit\">Download\n");
      out.write("                            App</button>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        ");
 if (session.getAttribute("printLogin") != null) { 
      out.write("\n");
      out.write("        <p class=\"rubik-bold color-green mt-5\" style='text-align: center;'>");
 out.println(String.valueOf(session.getAttribute("printLogin"))); 
      out.write("</p>\n");
      out.write("        ");
 }
            session.removeAttribute("printLogin");
        
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"grid-container grid-container-reverse box-model box-model1\">\n");
      out.write("            <div class=\"grid-left\">\n");
      out.write("                <p class=\"rubik-bold\" style=\"font-size: 2.2em; line-height: 44px;\">Explore Villas<br>On Bali</p>\n");
      out.write("                <p class=\"karla-normal\">We provide you wonderful experiences and excellent services especially our beloved\n");
      out.write("                    customers</p>\n");
      out.write("                <a href=\"book1.jsp\" class=\"btn btn-success rubik-bold color-white background-green\">Book Now</a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"grid-right\">\n");
      out.write("                <div id=\"carouselExampleFade\" class=\"carousel slide carousel-fade\" data-bs-ride=\"carousel\">\n");
      out.write("                    <div class=\"carousel-inner\">\n");
      out.write("                        <div class=\"carousel-item active\">\n");
      out.write("                            <img src=\"assets/images/1.png\" class=\"d-block w-100\" alt=\"...\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"carousel-item\">\n");
      out.write("                            <img src=\"assets/images/2.png\" class=\"d-block w-100\" alt=\"...\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"carousel-item\">\n");
      out.write("                            <img src=\"assets/images/3.png\" class=\"d-block w-100\" alt=\"...\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#carouselExampleFade\"\n");
      out.write("                            data-bs-slide=\"prev\">\n");
      out.write("                        <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                        <span class=\"visually-hidden\">Previous</span>\n");
      out.write("                    </button>\n");
      out.write("                    <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#carouselExampleFade\"\n");
      out.write("                            data-bs-slide=\"next\">\n");
      out.write("                        <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                        <span class=\"visually-hidden\">Next</span>\n");
      out.write("                    </button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"grid-container box-model box-model2\" style=\"padding: 0;\">\n");
      out.write("            <div class=\"grid-left\" style=\"padding: 0;\">\n");
      out.write("                <img src=\"assets/images/home2.png\" class=\"img-home2\" alt=\"\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"grid-right\">\n");
      out.write("                <p class=\"rubik-bold color-green\">About Us</p>\n");
      out.write("                <p class=\"karla-normal\">We came from a small company who is hoping to bring joy to people around the world\n");
      out.write("                    as they visit\n");
      out.write("                    Bali.<br><br>\n");
      out.write("                    Hibitsu is committed to providing a complete accommodation experience for the modern traveler.</p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"grid-container grid-container-reverse box-model box-model1\">\n");
      out.write("            <div class=\"grid-left\">\n");
      out.write("                <p class=\"rubik-bold color-green\">Our Services</p>\n");
      out.write("                <p class=\"karla-normal\">When you sign up with us, you will get a lot of benefits. For example :</p>\n");
      out.write("                <ul class=\"karla-normal\">\n");
      out.write("                    <li>Real-time consultation via chat, audio, or video call</li>\n");
      out.write("                    <li>Make reservation anytime and anywhere</li>\n");
      out.write("                    <li>Track your order status</li>\n");
      out.write("                </ul>\n");
      out.write("                ");
 if (session.getAttribute("idUser") == null) { 
      out.write("\n");
      out.write("                <a href=\"login.jsp\" class=\"btn btn-outline-success rubik-bold color-green border-green\">Sign Up</a>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div class=\"grid-right\">\n");
      out.write("                <img src=\"assets/images/home3.png\" class=\"img-home3\" alt=\"\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"box-model box-model2\" style=\"text-align: center;\">\n");
      out.write("            <p class=\"rubik-bold color-green\" style=\"font-size: 1.5em;\">Let's Save The<br>Environment Together</p>\n");
      out.write("            <p class=\"karla-normal\">HERE ARE THE RULE TO KEEP THE VILLA HEALTHY</p>\n");
      out.write("            <div class=\"env-grid\">\n");
      out.write("                <div class=\"env-rule\">\n");
      out.write("                    <img src=\"assets/images/env-straw.svg\" alt=\"\">\n");
      out.write("                    <p class=\"rubik-bold\">NO PLASTIC<br>STRAWS</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"env-rule\">\n");
      out.write("                    <img src=\"assets/images/env-bottle.svg\" alt=\"\">\n");
      out.write("                    <p class=\"rubik-bold\">NO PLASTIC<br>BOTTLES</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"env-rule\">\n");
      out.write("                    <img src=\"assets/images/env-recycle.svg\" alt=\"\">\n");
      out.write("                    <p class=\"rubik-bold\">RECYCLE</p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js\"\n");
      out.write("                integrity=\"sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4\" crossorigin=\"anonymous\">\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                $(window).scroll(function () {\n");
      out.write("                    var height = $('.grid-container').height();\n");
      out.write("                    var scrollTop = $(window).scrollTop();\n");
      out.write("\n");
      out.write("                    if (scrollTop >= 20) {\n");
      out.write("                        $('nav').addClass('solid-nav');\n");
      out.write("                    } else {\n");
      out.write("                        $('nav').removeClass('solid-nav');\n");
      out.write("                    }\n");
      out.write("\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("\n");
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
