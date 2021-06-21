-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2021 at 11:26 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `disprog_uas`
--

-- --------------------------------------------------------

--
-- Table structure for table `chats`
--

CREATE TABLE `chats` (
  `idchat` int(11) NOT NULL,
  `cht_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `message` text NOT NULL,
  `idsender` int(11) NOT NULL,
  `idreceiver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `idreservation` int(11) NOT NULL,
  `res_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `checkin_date` datetime NOT NULL,
  `checkout_date` datetime NOT NULL,
  `status` enum('PENDING','APPROVED','DECLINED','CANCELED') NOT NULL DEFAULT 'PENDING',
  `total_guest` int(11) NOT NULL,
  `notes` varchar(255) NOT NULL,
  `total_price` int(9) NOT NULL,
  `url_bukti_pembayaran` varchar(255) DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  `idvilla` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`idreservation`, `res_timestamp`, `checkin_date`, `checkout_date`, `status`, `total_guest`, `notes`, `total_price`, `url_bukti_pembayaran`, `iduser`, `idvilla`) VALUES
(1000, '2021-06-21 09:24:04', '2022-07-08 00:00:00', '2022-07-09 00:00:00', 'CANCELED', 0, 'Tambahan nasi', 2000000, NULL, 2, 2),
(1004, '2021-06-20 11:17:34', '2021-08-13 00:00:00', '2021-08-27 00:00:00', 'PENDING', 4, 'Tolong bersihkan persiapkan grill 2 untuk barbekyu, dan saya pesan sound system 1 ya.', 28000000, 'https://drive.google.com/file/d/1j6fAAm5lBhvQEvrSsYcvvuoTl50d4mhK/view?usp=sharing', 2, 2),
(1005, '2021-06-20 17:43:17', '2021-07-01 00:00:00', '2021-07-02 00:00:00', 'PENDING', 5, 'Tambah Grill', 1800000, 'https://drive.google.com/drive/folders/1V2miIMbYRxSXKLtUNes9RHVwCfo2iPXU?usp=sharing', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `iduser` int(11) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `display_name` varchar(8) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('ADMIN','CLIENT') NOT NULL DEFAULT 'CLIENT',
  `no_ktp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`iduser`, `fullname`, `display_name`, `phone_number`, `email`, `password`, `role`, `no_ktp`) VALUES
(1, 'Jasti Ohanna', 'jasti', '08123456789', 'jasti@gmail.com', 'jasti', 'CLIENT', '3315060711900001'),
(2, 'Theophil Henry Soegianto', 'theo', '08123456789', 'theo@gmail.com', 'theo', 'CLIENT', '3315143107800001'),
(3, 'Christopher Tri Anugrah', 'toto', '08123456789', 'toto@gmail.com', 'toto', 'ADMIN', '3315142512700001'),
(4, 'Ultraman Cosmos', 'cosmos', '018293485910', 'cosmos@gmail.com', 'cosmos', 'CLIENT', '0000000000000000'),
(5, 'Ultraman Dyna', 'dyna', '081833929401', 'dyna@gmail.com', 'dyna', 'CLIENT', '0000000000000000');

-- --------------------------------------------------------

--
-- Table structure for table `villas`
--

CREATE TABLE `villas` (
  `idvilla` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `total_bedroom` int(11) NOT NULL,
  `total_bathroom` int(11) NOT NULL,
  `facilities` varchar(255) NOT NULL,
  `unit_size` varchar(45) NOT NULL,
  `url_photo` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `villas`
--

INSERT INTO `villas` (`idvilla`, `name`, `address`, `total_bedroom`, `total_bathroom`, `facilities`, `unit_size`, `url_photo`, `price`, `description`) VALUES
(1, 'The La Llorona', 'Jl. Hang Tuah No.86, Sanur Kaja, Kec. Denpasar Sel., Kota Denpasar, Bali 80227', 3, 2, 'Wifi, Pool, Breakfast, Grill, Rooftop Lounge, Multipurpose Room', '100 x 200', 'http://localhost:8080/WebServer/images/1.png', 1800000, 'A beautiful near beach villa, with a wonderful neighboorhood. Be amazed! Be Refreshed!\r\nOther activities to do here is surfing, skying, running, jumping and other stuff!'),
(2, 'The Beach Next Door', 'Jl. Bumbak Jl. Umalas No.22, Kerobokan, Kec. Kuta Utara, Kabupaten Badung, Bali 80361\r\n2', 2, 1, 'Wifi, Hot and cold water, Breakfast, Spa, Private pool, Mini bar', '250 x 100', 'http://localhost:8080/WebServer/images/2.png', 2000000, 'The beach next door got everything. Beautiful, Charming, Elegant, no wonder everyone want it. Don’t you? Look at it’s sexy build. Don’t you just want to lay down and sleep on it.'),
(3, 'The Wonderland', 'Pantai Lima, Pererenan, Jl. Babadan, Canggu, Kec. Mengwi, Kabupaten Badung, Bali 80351', 5, 3, 'Private pool, Wifi, Rooftop longue, karaoke', '250 x 250', 'http://localhost:8080/WebServer/images/3.png', 2500000, 'A near lake villa, where you will feel relaxed and refreshed. Feel the nature as you meditate in the cool breeze. Feel the wonderland wonder as you enter the realm of peace.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chats`
--
ALTER TABLE `chats`
  ADD PRIMARY KEY (`idchat`),
  ADD KEY `fk_chats_users1_idx` (`idsender`),
  ADD KEY `fk_chats_users2_idx` (`idreceiver`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`idreservation`),
  ADD KEY `fk_reservations_users_idx` (`iduser`),
  ADD KEY `fk_reservations_villas1_idx` (`idvilla`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`iduser`);

--
-- Indexes for table `villas`
--
ALTER TABLE `villas`
  ADD PRIMARY KEY (`idvilla`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chats`
--
ALTER TABLE `chats`
  MODIFY `idchat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=350;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `idreservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1006;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `villas`
--
ALTER TABLE `villas`
  MODIFY `idvilla` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chats`
--
ALTER TABLE `chats`
  ADD CONSTRAINT `fk_chats_users1` FOREIGN KEY (`idsender`) REFERENCES `users` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_chats_users2` FOREIGN KEY (`idreceiver`) REFERENCES `users` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `fk_reservations_users` FOREIGN KEY (`iduser`) REFERENCES `users` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservations_villas1` FOREIGN KEY (`idvilla`) REFERENCES `villas` (`idvilla`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
