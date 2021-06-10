<%@ page language="java" import="java.util.*, java.io.*, java.text.SimpleDateFormat" %>

<%
    int iduser = 1;
    int idvilla = 1;
    String checkIn = request.getParameter("checkIn");
    String checkOut = request.getParameter("checkOut");
    int totalGuest = Integer.parseInt(request.getParameter("totalGuest"));
    String notes = request.getParameter("notes");

    try {
        com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
        com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();
        // TODO initialize WS operation arguments here
        // TODO process result here
        java.lang.String result = port.insertReservation(checkIn, checkOut, totalGuest, notes, iduser, idvilla);
        
        out.println("Result = " + result);
    } catch (Exception ex) {
        // TODO handle custom exceptions here
    }

%>