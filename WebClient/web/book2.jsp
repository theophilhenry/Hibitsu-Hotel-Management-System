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

        <title>Book Now</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light drop-shadow-0-4-10 mb-5">
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
                        <li class="nav-item">
                            <a class="nav-link rubik-normal" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active rubik-bold color-green" aria-current="page" href="book1.jsp">Book
                                Now</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link rubik-normal" href="track-order.jsp">Track Order</a>
                        </li>
                        <li class="nav-item me-3">
                            <a class="nav-link rubik-normal" href="login.jsp">Login</a>
                        </li>
                    </ul>
                    <form>
                        <button class="btn btn-success rubik-bold color-white background-green" type="submit">Download
                            App</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="book-container">
            <img src="assets/images/hotel1.png" class="img-hotel" alt="">
            <div class="villa-description">
                <p class="rubik-bold">Hotel La Llorona</p>
                <p>A beautiful near beach villa, with a wonderful neighboorhood. Be amazed! Be Refreshed!
                    Other activities to do here is surfing, skying, running, jumping and other stuff!</p>
            </div>

            <div class="villa-details mb-4">
                <div class="villa-description">
                    <div class="information">
                        <p class="rubik-bold">Located at</p>
                        <p>Jalan Hangtuah 86, Sanur Kaja, Denpasar</p>
                    </div>
                    <div class="grid-container">
                        <div class="grid-left">
                            <div class="information">
                                <p class="rubik-bold">Total Bedroom</p>
                                <p>4</p>
                            </div>
                        </div>
                        <div class="grid-right">
                            <div class="information">
                                <p class="rubik-bold">Total Bathroom</p>
                                <p>6</p>
                            </div>
                        </div>
                    </div>
                    <div class="information">
                        <p class="rubik-bold">Facilities</p>
                        <p>Wifi, Pool, Breakfast, Grill, Rooftop Lounge, Multipurpose Room</p>
                    </div>
                    <div class="information">
                        <p class="rubik-bold">Unit Size</p>
                        <p>200 x 400 m</p>
                    </div>
                </div>
            </div>

            <div class="villa-details mb-4" style="box-shadow: none;">
                <div class="grid-container grid-container-more-left grid-container-reverse">
                    <div class="grid-left book-property" style="align-items: center;">
                        <div style="width: 300px;">
                            <form method="POST" action="book2-handler.jsp">
                                <p class="rubik-bold color-green">BOOK PROPERTY</p>
                                <div class="form-floating mb-3" style="width: 100%;">
                                    <input type="date" class="form-control" name="checkIn" required>
                                    <label for="floatingCheckIn">Check in Date</label>
                                </div>
                                <div class="form-floating mb-3" style="width: 100%;">
                                    <input type="date" class="form-control" name="checkOut" required>
                                    <label for="floatingCheckOut">Check out Date</label>
                                </div>
                                <div class="form-floating mb-3" style="width: 100%;">
                                    <input type="number" class="form-control" name="totalGuest" required>
                                    <label for="floatingCheckOut">Total Guest</label>
                                </div>
                                <div class="form-floating mb-3" style="width: 100%;">
                                    <textarea class="form-control" name="notes" placeholder="Add notes here"
                                              style="min-height: 180px;"></textarea>
                                    <label for="floatingNotes">Notes</label>
                                </div>
                                <button class="btn btn-success rubik-bold color-white background-green mb-3" type="submit"
                                        style="width: 100%;">Book</button>
                            </form>
                        </div>
                    </div>
                    <div class="grid-right book-check" style="align-items: center; height: fit-content">
                        <div style="width: 300px;">
                            <p class="rubik-bold color-green">CHECK FOR AVAILABILITY</p>
                            <div class="form-floating mb-3" style="width: 100%;">
                                <input type="date" class="form-control" required>
                                <label for="floatingCheckIn">Check in Date</label>
                            </div>
                            <div class="form-floating mb-3" style="width: 100%;">
                                <input type="date" class="form-control" required>
                                <label for="floatingCheckOut">Check out Date</label>
                            </div>
                            <button class="btn btn-success rubik-bold color-white background-green mb-3" type="submit"
                                    style="width: 100%;">Check Availability</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous">
        </script>

    </body>

</html>