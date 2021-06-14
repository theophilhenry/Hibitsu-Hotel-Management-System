<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <!-- My CSS -->
        <link rel="stylesheet" href="styles/main.css">
        <link rel="stylesheet" href="styles/book.css">

        <!-- Jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <title>Template</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top drop-shadow-0-4-10 mb-5">
            <div class="container-fluid">
                <!-- Navbar Toggler -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
                        aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>


                <!-- Navbar  -->
                <a class="navbar-brand rubik-bold color-green" href="#">日々つ HIBITSU</a>
                <div class="collapse navbar-collapse justify-content-end" id="navbarTogglerDemo02">
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item me-3">
                            <a class="nav-link rubik-normal" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item me-3">
                            <a class="nav-link active rubik-bold color-green" aria-current="page" href="book1.jsp">Book Now</a>
                        </li>
                        <% if (session.getAttribute("idUser") == null) { %>
                        <li class="nav-item me-3">
                            <a class="nav-link rubik-normal" href="login.jsp">Login</a>
                        </li>
                        <% }%>
                        <% if (session.getAttribute("idUser") != null) { %>
                        <li class="nav-item me-3">
                            <a class="nav-link rubik-normal" href="track-order.jsp">Track Order</a>
                        </li>
                        <li class="nav-item me-3">
                            <form method='POST' action='login-handler.jsp'>
                                <input type='hidden' name='command' value='logout'>
                                <button type='submit' class="nav-link rubik-normal" style='padding-right: .5rem; border: none; background: none;' href="login.jsp">Logout</a>
                            </form>
                        </li>
                        <% }%>
                    </ul>
                    <form>
                        <button class="btn btn-success rubik-bold color-white background-green" type="submit">Download
                            App</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="client-order-container mb-5">
            <div class="grid-container grid-container-more-left" style="padding: 20px;">
                <div class="grid-left">
                    <p class="rubik-bold fs-2 color-green">Your Reservation<br>has been placed!</p>
                    <p>Your reservation order has been sent to your email.
                        <br>Please Check out your email.
                        <br>You can track your order status in the Track Order menu.
                        <br>If there's a problem, feel free to contact us.</p>
                    <a href="index.jsp" class="btn btn-success rubik-bold color-white background-green">Great</a>
                </div>
                <div class="grid-right" style="align-items: center;">
                    <%
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
                    %>
                    <!-- Reservation Info -->
                    <!--
                    <p>ORDER ID : 20200701001</p>
                    <div class="client-order-card">
                        <div class="information">
                            <p class="rubik-bold">YOUR NAME</p>
                            <p>Theophil Henry Soegianto</p>
                        </div>


                        <div class="information">
                            <p class="rubik-bold">VILLAS</p>
                            <p>The La'llorona</p>
                        </div>


                        <div class="information">
                            <p class="rubik-bold">DATE</p>
                            <p>09 June 2021 - 21 June 2021</p>
                        </div>


                        <div class="information">
                            <p class="rubik-bold">TOTAL GUEST</p>
                            <p>4 People</p>
                        </div>

                        <div class="information">
                            <p class="rubik-bold">DATE OF ORDER</p>
                            <p>Rp. 1.000.000</p>
                        </div>

                        <div class="information">
                            <p class="rubik-bold">TOTAL PRICE</p>
                            <p>Rp. 1.000.000</p>
                        </div>


                        <div class="information">
                            <p class="rubik-bold">NOTES</p>
                            <p>4 Extra Grill</p>
                        </div>


                        <div class="information">
                            <p class="rubik-bold">STATUS</p>
                            <p class="rubik-bold status-pending">PENDING</p>
                        </div>
                    </div>
                    -->
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous">
        </script>

        <script>
            $(document).ready(function () {
                $(window).scroll(function () {
                    var height = $('.grid-container').height();
                    var scrollTop = $(window).scrollTop();

                    if (scrollTop >= 20) {
                        $('nav').addClass('solid-nav');
                    } else {
                        $('nav').removeClass('solid-nav');
                    }

                });
            });
        </script>

    </body>

</html>