-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2021 at 02:46 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `disprog_uas`
--
CREATE DATABASE IF NOT EXISTS `disprog_uas` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `disprog_uas`;

-- --------------------------------------------------------

--
-- Table structure for table `chats`
--

CREATE TABLE IF NOT EXISTS `chats` (
  `idchat` int(11) NOT NULL AUTO_INCREMENT,
  `cht_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `messages` text NOT NULL,
  `idsender` int(11) NOT NULL,
  `idreceiver` int(11) NOT NULL,
  PRIMARY KEY (`idchat`),
  KEY `fk_chats_users1_idx` (`idsender`),
  KEY `fk_chats_users2_idx` (`idreceiver`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=41 ;

--
-- Dumping data for table `chats`
--

INSERT INTO `chats` (`idchat`, `cht_timestamp`, `messages`, `idsender`, `idreceiver`) VALUES
(11, '2021-06-21 12:22:09', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\n', 3, 2),
(12, '2021-06-21 12:22:21', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\n', 3, 12),
(13, '2021-06-21 12:22:55', 'Hallo ', 2, 3),
(14, '2021-06-21 12:23:09', 'Tidak jadi min, maaf', 2, 3),
(15, '2021-06-21 12:23:12', 'Okee', 3, 2),
(16, '2021-06-21 12:23:42', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(17, '2021-06-21 12:23:51', '2', 12, 3),
(18, '2021-06-21 12:23:51', 'Anda memilih ''Check Booking/Track order''.\nSilahkan masukkan order id : \nOpsi Menu : \n0) Kembali\n', 3, 12),
(19, '2021-06-21 12:24:39', '10', 12, 3),
(20, '2021-06-21 12:24:39', 'Maaf tidak ada reservasi dengan order ID = 10\nOpsi Menu : \n0) Kembali', 3, 12),
(21, '2021-06-21 12:24:44', '13', 12, 3),
(22, '2021-06-21 12:24:44', 'Berikut informasi Reservasi dengan Order ID : 13\nName : Ananda\nVilla : The Wonderland\nDate : 2021-07-13-2021-07-16\nTotal Guest : 5\nNotes : Tidak ada\nStatus : PENDING\nBukti Pembayaran : Sudah Terkirim\nOpsi Menu : \n0) Kembali', 3, 12),
(23, '2021-06-21 12:25:01', '1', 2, 3),
(24, '2021-06-21 12:25:02', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(25, '2021-06-21 12:25:04', 'asdasds', 2, 3),
(26, '2021-06-21 12:25:04', 'Harap memberi input dalam bentuk angka saja sesuai opsi.', 3, 2),
(27, '2021-06-21 12:25:09', '2', 2, 3),
(28, '2021-06-21 12:25:09', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\nOpsi Menu : \n0) Kembali', 3, 2),
(29, '2021-06-21 12:25:40', '3#2021-07-10#2021-07-20#5#Butuh kompor 2', 2, 3),
(30, '2021-06-21 12:25:40', 'Villa tidak dapat dibooking.\nKarena ada jadwal reservasi bertabrakan dengan pesanan lain.\nOpsi Menu : \n0) Kembali\n1) Cek Ketersediaan Villa pada tanggal tertentu\n2) Booking Villa\n', 3, 2),
(31, '2021-06-21 12:25:57', '3#2021-07-10#2021-07-13#5#Butuh kompor 2', 2, 3),
(32, '2021-06-21 12:25:57', 'Harap memberi input dalam bentuk angka saja sesuai opsi.', 3, 2),
(33, '2021-06-21 12:26:07', '3#2021-07-10#2021-07-13#5#Butuh kompor 2', 2, 3),
(34, '2021-06-21 12:26:08', 'Harap memberi input dalam bentuk angka saja sesuai opsi.', 3, 2),
(35, '2021-06-21 12:26:17', '2', 2, 3),
(36, '2021-06-21 12:26:17', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\nOpsi Menu : \n0) Kembali', 3, 2),
(37, '2021-06-21 12:26:21', '3#2021-07-10#2021-07-13#5#Butuh kompor 2', 2, 3),
(38, '2021-06-21 12:26:22', 'Villa sudah terbooking. Silahkan mengupload bukti pembayaran sebelum tanggal checkin\nOrder Id reservasi : 14\nOpsi Menu : \n0) Kembali\n1) Cek Ketersediaan Villa pada tanggal tertentu\n2) Booking Villa\n', 3, 2),
(39, '2021-06-21 12:29:46', 'Hallo min', 12, 3),
(40, '2021-06-21 12:29:47', 'Hallo ana ', 3, 12);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `idreservation` int(11) NOT NULL AUTO_INCREMENT,
  `res_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `checkin_date` datetime NOT NULL,
  `checkout_date` datetime NOT NULL,
  `status` enum('PENDING','APPROVED','DECLINED','CANCELED') NOT NULL DEFAULT 'PENDING',
  `total_guest` int(11) NOT NULL,
  `notes` varchar(255) NOT NULL,
  `total_price` bigint(20) NOT NULL,
  `url_bukti_pembayaran` varchar(255) DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  `idvilla` int(11) NOT NULL,
  PRIMARY KEY (`idreservation`),
  KEY `fk_reservations_users_idx` (`iduser`),
  KEY `fk_reservations_villas1_idx` (`idvilla`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`idreservation`, `res_timestamp`, `checkin_date`, `checkout_date`, `status`, `total_guest`, `notes`, `total_price`, `url_bukti_pembayaran`, `iduser`, `idvilla`) VALUES
(10, '2021-06-21 12:27:57', '2021-06-24 00:00:00', '2021-06-30 00:00:00', 'APPROVED', 2, '-', 3600000, 'https://drive.google.com/file/d/1SQTI-B2jdo81m_lky3Y1TEl9omc6d6-d/view?usp=sharing', 1, 1),
(11, '2021-06-21 12:24:06', '2021-06-15 00:00:00', '2021-06-17 00:00:00', 'DECLINED', 7, '-', 4000000, 'https://drive.google.com/file/d/1SQTI-B2jdo81m_lky3Y1TEl9omc6d6-d/view?usp=sharing', 2, 2),
(12, '2021-06-21 12:27:04', '2021-07-06 00:00:00', '2021-07-08 00:00:00', 'PENDING', 3, 'no gril', 3600000, NULL, 1, 1),
(13, '2021-06-21 12:21:22', '2021-07-13 00:00:00', '2021-07-16 00:00:00', 'PENDING', 5, 'Tidak ada', 7500000, 'https://drive.google.com/file/d/1SQTI-B2jdo81m_lky3Y1TEl9omc6d6-d/view?usp=sharing', 12, 3),
(14, '2021-06-21 12:28:26', '2021-07-10 00:00:00', '2021-07-13 00:00:00', 'PENDING', 5, 'Tidak butuh kompor', 7500000, NULL, 2, 3),
(15, '2021-06-21 12:33:14', '2021-07-10 00:00:00', '2021-07-20 00:00:00', 'PENDING', 6, '', 18000000, NULL, 12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `display_name` varchar(8) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('ADMIN','CLIENT') NOT NULL DEFAULT 'CLIENT',
  `no_ktp` varchar(255) NOT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`iduser`, `fullname`, `display_name`, `phone_number`, `email`, `password`, `role`, `no_ktp`) VALUES
(1, 'Jasti Ohanna', 'jasti', '08123456789', 'jasti@gmail.com', 'jasti', 'CLIENT', '3315060711900001'),
(2, 'Theophil Henry Soegianto', 'theo', '08123456789', 'theo@gmail.com', 'theo', 'CLIENT', '3315143107800001'),
(3, 'Christopher Tri Anugrah', 'toto', '08123456789', 'toto@gmail.com', 'toto', 'ADMIN', '3315142512700001'),
(12, 'Ananda', 'Ana', '123467890', 'ana@gmail.com', 'ana', 'CLIENT', '12345680');

-- --------------------------------------------------------

--
-- Table structure for table `villas`
--

CREATE TABLE IF NOT EXISTS `villas` (
  `idvilla` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `total_bedroom` int(11) NOT NULL,
  `total_bathroom` int(11) NOT NULL,
  `facilities` varchar(255) NOT NULL,
  `unit_size` varchar(45) NOT NULL,
  `url_photo` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`idvilla`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `villas`
--

INSERT INTO `villas` (`idvilla`, `name`, `address`, `total_bedroom`, `total_bathroom`, `facilities`, `unit_size`, `url_photo`, `price`, `description`) VALUES
(1, 'The La Llorona', 'Jl. Hang Tuah No.86, Sanur Kaja, Kec. Denpasar Sel., Kota Denpasar, Bali 80227', 3, 2, 'Wifi, Pool, Breakfast, Grill, Rooftop Lounge, Multipurpose Room', '100 x 200', 'http://localhost:8080/WebServer/images/1.png', 1800000, 'A beautiful near beach villa, with a wonderful neighboorhood. Be amazed! Be Refreshed!\r\nOther activities to do here is surfing, skying, running, jumping and other stuff!'),
(2, 'The Beach Next Door', 'Jl. Bumbak Jl. Umalas No.22, Kerobokan, Kec. Kuta Utara, Kabupaten Badung, Bali 80361\r\n2', 2, 1, 'Wifi, Hot and cold water, Breakfast, Spa, Private pool, Mini bar', '250 x 100', 'http://localhost:8080/WebServer/images/2.png', 2000000, 'The beach next door got everything. Beautiful, Charming, Elegant, no wonder everyone want it. Don’t you? Look at it’s sexy build. Don’t you just want to lay down and sleep on it.'),
(3, 'The Wonderland', 'Pantai Lima, Pererenan, Jl. Babadan, Canggu, Kec. Mengwi, Kabupaten Badung, Bali 80351', 5, 3, 'Private pool, Wifi, Rooftop longue, karaoke', '250 x 250', 'http://localhost:8080/WebServer/images/3.png', 2500000, 'A near lake villa, where you will feel relaxed and refreshed. Feel the nature as you meditate in the cool breeze. Feel the wonderland wonder as you enter the realm of peace.');

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
