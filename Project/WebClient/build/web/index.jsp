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
        <link rel="stylesheet" href="styles/home.css">

        <!-- Jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <title>Home</title>
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
                            <a class="nav-link active rubik-bold color-green" aria-current="page" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item me-3">
                            <a class="nav-link rubik-normal" href="book1.jsp">Book Now</a>
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
                    <button class="btn btn-success background-green"><a href="https://drive.google.com/uc?export=download&id=14c8fMAalEadnSOyytsD2iEqYGuiJCNrG" 
                                                                        class="rubik-bold color-white" style="text-decoration: none;">Download App</a>
                    </button>
                </div>
            </div>
        </nav>

        <% if (session.getAttribute("printLogin") != null) { %>
        <p class="rubik-bold color-green mt-5" style='text-align: center;'><% out.println(String.valueOf(session.getAttribute("printLogin"))); %></p>
        <% }
            session.removeAttribute("printLogin");
        %>

        <div class="grid-container grid-container-reverse box-model box-model1">
            <div class="grid-left">
                <p class="rubik-bold" style="font-size: 2.2em; line-height: 44px;">Explore Villas<br>On Bali</p>
                <p class="karla-normal">We provide you wonderful experiences and excellent services especially our beloved
                    customers</p>
                <a href="book1.jsp" class="btn btn-success rubik-bold color-white background-green">Book Now</a>
            </div>
            <div class="grid-right">
                <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="assets/images/1.png" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="assets/images/2.png" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="assets/images/3.png" class="d-block w-100" alt="...">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="grid-container box-model box-model2" style="padding: 0;">
            <div class="grid-left" style="padding: 0;">
                <img src="assets/images/home2.png" class="img-home2" alt="">
            </div>
            <div class="grid-right">
                <p class="rubik-bold color-green">About Us</p>
                <p class="karla-normal">We came from a small company who is hoping to bring joy to people around the world
                    as they visit
                    Bali.<br><br>
                    Hibitsu is committed to providing a complete accommodation experience for the modern traveler.</p>
            </div>
        </div>
        <div class="grid-container grid-container-reverse box-model box-model1">
            <div class="grid-left">
                <p class="rubik-bold color-green">Our Services</p>
                <p class="karla-normal">When you sign up with us, you will get a lot of benefits. For example :</p>
                <ul class="karla-normal">
                    <li>Real-time consultation via chat, audio, or video call</li>
                    <li>Make reservation anytime and anywhere</li>
                    <li>Track your order status</li>
                </ul>
                <% if (session.getAttribute("idUser") == null) { %>
                <a href="login.jsp" class="btn btn-outline-success rubik-bold color-green border-green">Sign Up</a>
                <% }%>
            </div>
            <div class="grid-right">
                <img src="assets/images/home3.png" class="img-home3" alt="">
            </div>
        </div>


        <div class="box-model box-model2" style="text-align: center;">
            <p class="rubik-bold color-green" style="font-size: 1.5em;">Let's Save The<br>Environment Together</p>
            <p class="karla-normal">HERE ARE THE RULE TO KEEP THE VILLA HEALTHY</p>
            <div class="env-grid">
                <div class="env-rule">
                    <img src="assets/images/env-straw.svg" alt="">
                    <p class="rubik-bold">NO PLASTIC<br>STRAWS</p>
                </div>
                <div class="env-rule">
                    <img src="assets/images/env-bottle.svg" alt="">
                    <p class="rubik-bold">NO PLASTIC<br>BOTTLES</p>
                </div>
                <div class="env-rule">
                    <img src="assets/images/env-recycle.svg" alt="">
                    <p class="rubik-bold">RECYCLE</p>
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