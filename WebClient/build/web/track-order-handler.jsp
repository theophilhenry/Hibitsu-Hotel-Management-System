<%@ page language="java" import="java.util.*, java.io.*, java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (request.getParameter("command") == null) {
        response.sendRedirect("track-order.jsp");
        return;
    }

    String command = request.getParameter("command");

    if (command.equals("uploadBuktiPembayaran")) {
        String url_bukti_pembayaran = request.getParameter("urlBuktiPembayaran");
        String getIdReservation = request.getParameter("idReservation");
        int idReservation = Integer.parseInt(getIdReservation);

        try {

            com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
            com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();

            java.lang.String result = port.uploadPayment(url_bukti_pembayaran, idReservation);
            System.out.println(result);
            String[] resultSplitted = result.split(";;");
            String keteranganSuksesGagal = resultSplitted[1];

            if (keteranganSuksesGagal.equals("true")) {
                session.setAttribute("printTrack", "Succesfully Upload URL");
                response.sendRedirect("track-order.jsp");
            } else if (keteranganSuksesGagal.equals("false")) {
                session.setAttribute("printTrack", "Failed Upload URL");
                response.sendRedirect("track-order.jsp");
            }
        } catch (Exception ex) {
            System.out.println("Book2 handler error : " + ex);
        }

    }
%>
