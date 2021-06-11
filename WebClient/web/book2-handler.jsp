<%@ page language="java" import="java.util.*, java.io.*, java.text.SimpleDateFormat" %>

<%
    if (request.getParameter("command") == null) {
        response.sendRedirect("book2.jsp");
        return;
    }

    int iduser = 1;

    String command = request.getParameter("command");
    String checkIn = request.getParameter("checkIn");
    String checkOut = request.getParameter("checkOut");

    int totalGuest;
    String notes;

    if (command.equals("checkAvailability")) {

        String result = "Service not available yet";
        session.setAttribute("printAvailability", result);
        response.sendRedirect("book2.jsp");
        return;

    } else if (command.equals("book")) {
        int idvilla = Integer.parseInt(request.getParameter("idVilla"));
        try {
            totalGuest = Integer.parseInt(request.getParameter("totalGuest"));
            notes = request.getParameter("notes");

            com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
            com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();

            java.lang.String result = port.insertReservation(checkIn, checkOut, totalGuest, notes, iduser, idvilla);
            String[] resultSplitted = result.split(";;");
            String keteranganSuksesGagal = resultSplitted[1];

            if (keteranganSuksesGagal.equals("true")) {
                session.setAttribute("idReservation", resultSplitted[2]);
//                    out.println(resultSplitted[2]);
                response.sendRedirect("book3.jsp");
            } else if (keteranganSuksesGagal.equals("false")) {
                session.setAttribute("bookPrintAvailability", "Reservation Failed. Please Check Villa's Availability during the date.");
                response.sendRedirect("book2.jsp");
            }

            out.println("Result = " + result);
        } catch (Exception ex) {
            System.out.println("Book2 handler error : " + ex);
        }

    }


%>