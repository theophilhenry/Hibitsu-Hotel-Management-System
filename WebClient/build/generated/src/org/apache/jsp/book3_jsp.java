package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class book3_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link rel=\"stylesheet\" href=\"styles/book.css\">\r\n");
      out.write("\r\n");
      out.write("        <title>Template</title>\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-light bg-light drop-shadow-0-4-10 mb-5\">\r\n");
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
      out.write("                            <a class=\"nav-link rubik-normal\" href=\"index.jsp\">Home</a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"nav-item me-3\">\r\n");
      out.write("                            <a class=\"nav-link active rubik-bold color-green\" aria-current=\"page\" href=\"book1.jsp\">Home</a>\r\n");
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
      out.write("                                <button type='submit' class=\"nav-link rubik-normal\" style='padding-right: .5rem; padding-left: .5rem; padding: .5rem 1rem; border: none; background: none;' href=\"login.jsp\">Logout</a>\r\n");
      out.write("                            </form>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        ");
 }
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                    <form>\r\n");
      out.write("                        <button class=\"btn btn-success rubik-bold color-white background-green\" type=\"submit\">Download\r\n");
      out.write("                            App</button>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"client-order-container mb-5\">\r\n");
      out.write("            <div class=\"grid-container grid-container-more-left\" style=\"padding: 20px;\">\r\n");
      out.write("                <div class=\"grid-left\">\r\n");
      out.write("                    <p class=\"rubik-bold fs-2 color-green\">Your Reservation<br>has been placed!</p>\r\n");
      out.write("                    <p>Your reservation order has been sent to your email.\r\n");
      out.write("                        <br>Please Check out your email.\r\n");
      out.write("                        <br>You can track your order status in the Track Order menu.\r\n");
      out.write("                        <br>If there's a problem, feel free to contact us.</p>\r\n");
      out.write("                    <a href=\"index.jsp\" class=\"btn btn-success rubik-bold color-white background-green\">Great</a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"grid-right\" style=\"align-items: center;\">\r\n");
      out.write("                    ");

                        if (session.getAttribute("idReservation") == null) {
                            response.sendRedirect("book2.jsp");
                            return;
                        }
                        
                        String getIdReservation = String.valueOf(session.getAttribute("idReservation"));
                        int idReservation = Integer.parseInt(getIdReservation);
                        
                        session.removeAttribute("idReservation");
                        try {
                            com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
                            com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();

                            java.lang.String result = port.trackOrder2(idReservation);
                            out.println(result);

                        } catch (Exception ex) {
                            System.out.println("Error Book3.jsp : " + ex);
                        }
                    
      out.write("\r\n");
      out.write("                    <!-- Reservation Info -->\r\n");
      out.write("                    <!--\r\n");
      out.write("                    <p>ORDER ID : 20200701001</p>\r\n");
      out.write("                    <div class=\"client-order-card\">\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">YOUR NAME</p>\r\n");
      out.write("                            <p>Theophil Henry Soegianto</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">VILLAS</p>\r\n");
      out.write("                            <p>The La'llorona</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">DATE</p>\r\n");
      out.write("                            <p>09 June 2021 - 21 June 2021</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">TOTAL GUEST</p>\r\n");
      out.write("                            <p>4 People</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">DATE OF ORDER</p>\r\n");
      out.write("                            <p>Rp. 1.000.000</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">TOTAL PRICE</p>\r\n");
      out.write("                            <p>Rp. 1.000.000</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">NOTES</p>\r\n");
      out.write("                            <p>4 Extra Grill</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"information\">\r\n");
      out.write("                            <p class=\"rubik-bold\">STATUS</p>\r\n");
      out.write("                            <p class=\"rubik-bold status-pending\">PENDING</p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    -->\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js\"\r\n");
      out.write("                integrity=\"sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4\" crossorigin=\"anonymous\">\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
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
