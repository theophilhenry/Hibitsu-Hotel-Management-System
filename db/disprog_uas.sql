-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2021 at 02:05 PM
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
-- Indexes for table `villas`
--
ALTER TABLE `villas`
  ADD PRIMARY KEY (`idvilla`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `villas`
--
ALTER TABLE `villas`
  MODIFY `idvilla` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
