<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("idUser") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
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
        <link rel="stylesheet" href="styles/login.css">

        <!-- Jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <title>Login</title>
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
                            <a class="nav-link rubik-normal" href="book1.jsp">Book Now</a>
                        </li>
                        <li class="nav-item me-3">
                            <a class="nav-link active rubik-bold color-green" aria-current="page"
                               href="login.html">Login</a>
                        </li>
                    </ul>
                    <button class="btn btn-success background-green"><a href="https://drive.google.com/uc?export=download&id=1ILQiR2KmkBBEBSSA4bdQ198P8s_MnPPX" class="rubik-bold color-white" style="text-decoration: none;">Download App</a>
                    </button>
                </div>
            </div>
        </nav>
        
        <!-- Display Message After Login -->
        <% if (session.getAttribute("printLogin") != null) { %>
        <p class='rubik-bold' style='color: red; text-align: center;'><% out.println(String.valueOf(session.getAttribute("printLogin"))); %></p>
        <% }
            session.removeAttribute("printLogin");
        %>
        
        <!-- Display Message After Registration -->
        <% if (session.getAttribute("printRegister") != null) { %>
        <p class='rubik-bold <% out.print(String.valueOf(session.getAttribute("printRegisterColor"))); %>' style='text-align: center;'><% out.print(String.valueOf(session.getAttribute("printRegister"))); %></p>
        <% }
            session.removeAttribute("printRegister");
            session.removeAttribute("printRegisterColor");
        %>
        <div class="login-container">
            <div class="login-details mb-4">
                
                
                <!-- FORM LOGIN -->
                <form method='POST' action="login-handler.jsp">
                    <input type='hidden' name='command' value='login'>
                    <p class="rubik-bold color-green fs-4">LOGIN</p>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="email" required>
                        <label for="floatingInput">Email address</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name='password' required>
                        <label for="floatingPassword">Password</label>
                    </div>
                    <button class="btn btn-success rubik-bold color-white background-green mb-3" type="submit"
                            style="width: 100%;">Login</button>
                </form>
                
                
            </div>
            <p class="rubik-normal">Don't have an account? <span class="rubik-bold color-green">Register</span> now below
            </p>
            <div class="login-details mb-4">
                
                
                <!-- FORM REGISTER -->
                <form method='POST' action="login-handler.jsp">
                    <input type='hidden' name='command' value='register'>
                    <p class="rubik-bold color-green fs-4">REGISTER</p>
                    <div class="form-floating mb-3">
                        <input type="email" name="email" class="form-control" id="floatingEmail" placeholder="name@example.com" required>
                        <label for="floatingInput">Email address</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" required>
                        <label for="floatingPassword">Password</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" name="displayName" class="form-control" id="floatingDisplayName" placeholder="Display Name" maxlength="8" required>
                        <label for="floatingDisplayName">Display Name</label>
                        <p class="karla-normal" style="margin:0;padding:0;font-size:0.8em;color:grey;text-align: left;">MAXIMUM 8 CHARACTER</p>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" name="fullName" class="form-control" id="floatingFullName" placeholder="Full Name" required>
                        <label for="floatingFullName">Full Name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" name="phoneNumber" class="form-control" id="floatingPhoneNumber" placeholder="Phone Number" required>
                        <label for="floatingPhoneNumber">Phone Number</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" name="KTPNumber" class="form-control" id="floatingKTP" placeholder="KTP Number" required>
                        <label for="floatingKTP">KTP Number</label>
                    </div>
                    <button class="btn btn-success rubik-bold color-white background-green mb-3" type="submit"
                            style="width: 100%;">Register</button>
                </form>
                
                
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