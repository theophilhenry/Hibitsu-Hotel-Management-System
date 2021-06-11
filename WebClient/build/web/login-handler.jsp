<%@ page language="java" import="java.util.*, java.io.*, java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (request.getParameter("command") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    String command = request.getParameter("command");

    if (command.equals("login")) {
        String ket = "[1]hasilLoginClient,[2]iduser,[3]fullname,[4]display_name,[5]email,[6]role;;";

        try {

            com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
            com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();

            java.lang.String result = port.loginClient(email, password);
            System.out.println(result);
            String[] resultSplitted = result.split(";;");
            String keteranganSuksesGagal = resultSplitted[1];

            if (keteranganSuksesGagal.equals("true")) {
                session.setAttribute("idUser", resultSplitted[2]);
                session.setAttribute("printLogin", "Welcome, " + resultSplitted[4]);
                response.sendRedirect("index.jsp");
            } else if (keteranganSuksesGagal.equals("false")) {
                session.setAttribute("printLogin", "Login failed. Wrong email or password");
                response.sendRedirect("login.jsp");
            }
        } catch (Exception ex) {
            System.out.println("Book2 handler error : " + ex);
        }

    } else if (command.equals("register")) {
        return;
    } else if (command.equals("logout")) {
        session.removeAttribute("idUser");
        session.setAttribute("printLogin", "You have Logged Out");
        response.sendRedirect("index.jsp");
    }
%>
