<%@ page language="java" import="java.util.*, java.io.*, java.text.SimpleDateFormat" %>

<%
    if (request.getParameter("command") == null) {
        response.sendRedirect("book1.jsp");
        return;
    }

    // Untuk Booking ataupun Check Availability
    String command = request.getParameter("command");
    int idvilla = Integer.parseInt(request.getParameter("idVilla"));
    String checkIn = request.getParameter("checkIn");
    String checkOut = request.getParameter("checkOut");

    // Untuk Booking
    int totalGuest;
    String notes;

    if (command.equals("checkAvailability")) {
        try {
            com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
            com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();

            java.lang.String result = port.checkAvailability(idvilla, checkIn, checkOut);

            String[] resultSplitted = result.split(";;");
            String keteranganSuksesGagal = resultSplitted[1];
            String pesanBalik = "";
            String warnaPesan = "";

            // Melihat jika available atau tidak
            if (keteranganSuksesGagal.equals("true")) {
                pesanBalik = "Villa is available on <br>" + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(checkIn)) + " - " + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(checkOut));
                warnaPesan = "color-green";
            } else if (keteranganSuksesGagal.equals("false")) {
                pesanBalik = "Villa is unavailable on <br>" + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(checkIn)) + " - " + new SimpleDateFormat("dd MMMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(checkOut));
                warnaPesan = "color-red";
            } else {
                pesanBalik = resultSplitted[1];
                warnaPesan = "color-red";
            }

            session.setAttribute("printAvailability", pesanBalik);
            session.setAttribute("printAvailabilityColor", warnaPesan);
            response.sendRedirect("book2.jsp?idVilla=" + idvilla);

        } catch (Exception ex) {
            System.out.println("Check Availibility Error : " + ex);
        }

    } else if (command.equals("book")) {
        // Hanya book yang membutuhkan id user
        int iduser = Integer.parseInt(String.valueOf(session.getAttribute("idUser")));
        
        try {
            totalGuest = Integer.parseInt(request.getParameter("totalGuest"));
            notes = request.getParameter("notes");

            com.ubaya.disprog.WebServiceServer_Service service = new com.ubaya.disprog.WebServiceServer_Service();
            com.ubaya.disprog.WebServiceServer port = service.getWebServiceServerPort();

            // Mendapatkan keterangan true/false, dan idReservation jika true
            java.lang.String result = port.insertReservation(checkIn, checkOut, totalGuest, notes, iduser, idvilla);

            String[] resultSplitted = result.split(";;");
            String keteranganSuksesGagal = resultSplitted[1];

            if (keteranganSuksesGagal.equals("true")) { // Forward ke Book3 untuk menampilkan hasil reservasi
                session.setAttribute("idReservation", resultSplitted[2]);
                response.sendRedirect("book3.jsp");
                return;

            } else if (keteranganSuksesGagal.equals("false")) { // Lakukan pengembalian ke Book2.jsp
                session.setAttribute("bookPrintAvailability", "Reservation Failed. Please Check Villa's Availability during the date.");
                session.setAttribute("bookPrintAvailabilityColor", "color-red");
                response.sendRedirect("book2.jsp?idVilla=" + idvilla);
                return;

            }
        } catch (Exception ex) {
            System.out.println("Booking Error : " + ex);
        }

    }


%>