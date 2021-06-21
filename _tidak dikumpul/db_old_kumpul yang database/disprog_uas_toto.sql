-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2021 at 07:10 AM
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
  `message` text NOT NULL,
  `idsender` int(11) NOT NULL,
  `idreceiver` int(11) NOT NULL,
  PRIMARY KEY (`idchat`),
  KEY `fk_chats_users1_idx` (`idsender`),
  KEY `fk_chats_users2_idx` (`idreceiver`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=350 ;

--
-- Dumping data for table `chats`
--

INSERT INTO `chats` (`idchat`, `cht_timestamp`, `message`, `idsender`, `idreceiver`) VALUES
(76, '2021-06-11 13:15:07', 'Halo, apa yang bisa dibantu?', 3, 1),
(77, '2021-06-11 13:15:32', 'Saya mau bertanya', 1, 3),
(78, '2021-06-11 13:15:51', 'Apakah ada batas maximum untuk guest?', 1, 3),
(79, '2021-06-11 13:16:03', 'Tidak ada batasan maximum untuk guest', 3, 1),
(80, '2021-06-11 13:16:22', 'hal tersebut hanya digunakan untuk keperluan informasi', 3, 1),
(81, '2021-06-11 13:16:32', 'Baik, terimakasih', 1, 3),
(82, '2021-06-11 13:16:39', 'Sama-sama', 3, 1),
(83, '2021-06-11 13:16:52', 'Halo, apa yang bisa dibantu?', 3, 2),
(84, '2021-06-11 13:17:12', 'Halo', 2, 3),
(85, '2021-06-11 13:17:52', 'Apakah disediakan pembantu pada villa?', 2, 3),
(86, '2021-06-11 13:18:09', 'Tidak ada pembantu pada villa', 3, 2),
(87, '2021-06-11 13:18:18', 'Yang ada hanyalah satpam yang menjaga ', 3, 2),
(88, '2021-06-11 13:18:26', 'Ooh okee trims', 2, 3),
(89, '2021-06-11 13:18:33', 'Sama-sama', 3, 2),
(97, '2021-06-11 13:45:33', 'Halo, apa yang bisa dibantu?', 3, 1),
(98, '2021-06-11 13:45:57', 'Hai admin', 1, 3),
(99, '2021-06-11 13:45:59', 'hai', 3, 1),
(100, '2021-06-11 13:46:15', 'Halo, apa yang bisa dibantu?', 3, 2),
(101, '2021-06-11 13:46:44', 'ini jasti', 1, 3),
(102, '2021-06-11 13:47:18', 'Villa berhasil dibooking', 3, 1),
(104, '2021-06-14 07:02:57', 'haha', 3, 2),
(218, '2021-06-14 12:55:36', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(219, '2021-06-14 12:55:41', '1', 2, 3),
(220, '2021-06-14 12:55:41', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(221, '2021-06-14 12:55:44', '2', 2, 3),
(222, '2021-06-14 12:55:45', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(223, '2021-06-14 12:55:51', ' 2#2021-06-15#2021-06-20#5#Butuh kompor 2', 2, 3),
(224, '2021-06-14 12:55:51', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(225, '2021-06-14 12:58:39', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(226, '2021-06-14 12:58:42', '1', 2, 3),
(227, '2021-06-14 12:58:43', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(228, '2021-06-14 12:58:43', '2', 2, 3),
(229, '2021-06-14 12:58:44', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(230, '2021-06-14 12:58:48', '0', 2, 3),
(231, '2021-06-14 12:58:48', 'Anda memilih ''Kembali''.\n\nOpsi Menu :\n1. Reservasi\n2. Track Order Booking\n', 3, 2),
(232, '2021-06-14 12:58:51', '1', 2, 3),
(233, '2021-06-14 12:58:51', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(234, '2021-06-14 12:58:52', '2', 2, 3),
(235, '2021-06-14 12:58:53', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(236, '2021-06-14 12:59:21', '2#2021-06-11#2021-06-12#4#Butuhkompor', 2, 3),
(237, '2021-06-14 12:59:21', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(238, '2021-06-14 13:00:26', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(239, '2021-06-14 13:00:31', '1', 2, 3),
(240, '2021-06-14 13:00:31', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(241, '2021-06-14 13:00:32', '2', 2, 3),
(242, '2021-06-14 13:00:32', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(243, '2021-06-14 13:01:36', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(244, '2021-06-14 13:01:37', '2', 2, 3),
(245, '2021-06-14 13:01:38', 'Anda memilih ''Check Booking/Track order''.\nSilahkan masukkan order id : \n\nOpsi Menu : \n0) Kembali\n', 3, 2),
(246, '2021-06-14 13:01:43', '0', 2, 3),
(247, '2021-06-14 13:01:44', 'Anda memilih ''Kembali''.\n\nOpsi Menu :\n1. Reservasi\n2. Track Order Booking\n', 3, 2),
(248, '2021-06-14 13:01:45', '1', 2, 3),
(249, '2021-06-14 13:01:45', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(250, '2021-06-14 13:01:46', '2', 2, 3),
(251, '2021-06-14 13:01:46', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(252, '2021-06-14 13:01:55', '3#2021-06-15#2021-06-20#5#Butuh kompor 2', 2, 3),
(253, '2021-06-14 13:01:56', 'Villa tidka dapat dibooking.\nKarena ada jadwal reservasi bertabrakan dengan pesanan lain.\nOpsi Menu : \n0) Kembali\n1) Cek Ketersediaan Villa pada tanggal tertentu\n2) Booking Villa\n', 3, 2),
(254, '2021-06-14 13:02:08', '1#2021-06-15#2021-06-20#5#Butuh kompor 2', 2, 3),
(255, '2021-06-14 13:02:09', 'Harap memberi input dalam bentuk angka saja sesuai opsi.', 3, 2),
(256, '2021-06-14 13:02:18', '2', 2, 3),
(257, '2021-06-14 13:02:19', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(258, '2021-06-14 13:02:29', '0', 2, 3),
(259, '2021-06-14 13:02:29', 'Anda memilih ''Kembali''.\n\nOpsi Menu :\n1. Reservasi\n2. Track Order Booking\n', 3, 2),
(260, '2021-06-14 13:05:36', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(261, '2021-06-14 13:05:43', '1', 2, 3),
(262, '2021-06-14 13:05:43', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(263, '2021-06-14 13:05:46', '2', 2, 3),
(264, '2021-06-14 13:05:46', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(265, '2021-06-14 13:06:05', '2#2021-07-15#2021-07-20#5#Butuh kompor 2', 2, 3),
(266, '2021-06-14 13:06:06', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(267, '2021-06-14 13:07:11', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(268, '2021-06-14 13:07:16', '1', 2, 3),
(269, '2021-06-14 13:07:16', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(270, '2021-06-14 13:07:18', '2', 2, 3),
(271, '2021-06-14 13:07:18', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(272, '2021-06-14 13:07:51', '3#2021-07-05#2021-07-10#4#Haha', 2, 3),
(273, '2021-06-14 13:07:51', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(274, '2021-06-14 13:09:43', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(275, '2021-06-14 13:09:48', '1', 2, 3),
(276, '2021-06-14 13:09:48', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(277, '2021-06-14 13:09:49', '2', 2, 3),
(278, '2021-06-14 13:09:49', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(279, '2021-06-14 13:10:04', '3#2021-07-5#2021-07-10#4#Haha', 2, 3),
(280, '2021-06-14 13:10:04', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(281, '2021-06-14 13:10:21', '3#2021-10-5#2021-10-10#4#Haha', 2, 3),
(282, '2021-06-14 13:10:22', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(283, '2021-06-14 13:14:49', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(284, '2021-06-14 13:14:59', '1', 2, 3),
(285, '2021-06-14 13:14:59', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(286, '2021-06-14 13:15:00', '2', 2, 3),
(287, '2021-06-14 13:15:01', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(288, '2021-06-14 13:15:13', ' 2#2021-06-15#2021-06-20#5#Butuh kompor 2', 2, 3),
(289, '2021-06-14 13:15:13', 'Harap memberi input dalam bentuk angka sesuai format yang ada.', 3, 2),
(290, '2021-06-16 12:05:11', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(291, '2021-06-16 12:07:51', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(292, '2021-06-16 12:11:57', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(293, '2021-06-17 13:20:11', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(294, '2021-06-17 13:21:10', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(295, '2021-06-17 13:52:43', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(296, '2021-06-17 13:53:00', 'hallo', 2, 3),
(297, '2021-06-17 13:53:00', 'Harap memberi input dalam bentuk angka saja sesuai opsi.', 3, 2),
(298, '2021-06-17 13:53:52', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(299, '2021-06-17 13:54:12', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 1),
(300, '2021-06-17 13:54:38', 'hallo', 2, 3),
(301, '2021-06-17 13:54:44', 'mantab boi', 3, 2),
(302, '2021-06-17 13:54:49', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(303, '2021-06-17 13:55:12', 'haha', 1, 3),
(304, '2021-06-17 13:55:19', 'asd', 2, 3),
(305, '2021-06-17 14:02:38', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(306, '2021-06-17 14:03:42', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(307, '2021-06-17 14:07:09', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(308, '2021-06-17 14:07:54', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(309, '2021-06-17 14:08:04', '2', 2, 3),
(310, '2021-06-17 14:08:04', 'Anda memilih ''Check Booking/Track order''.\nSilahkan masukkan order id : \n\nOpsi Menu : \n0) Kembali\n', 3, 2),
(311, '2021-06-17 14:08:37', '111', 2, 3),
(312, '2021-06-17 14:08:38', 'Maaf tidak ada reservasi dengan order ID = 111\n\nOpsi Menu : \n0) Kembali', 3, 2),
(313, '2021-06-17 14:08:40', '222', 2, 3),
(314, '2021-06-17 14:08:41', 'Berikut informasi Reservasi dengan Order ID : 222\nName : Theophil Henry Soegianto\nVilla : The Beach Next Door\nDate : 2021-06-02-2021-06-03\nTotal Guest : 0\nNotes : \nStatus : PENDING\nBukti Pembayaran : Belum ada\n\nOpsi Menu : \n0) Kembali', 3, 2),
(315, '2021-06-17 14:08:43', '0', 2, 3),
(316, '2021-06-17 14:08:44', 'Anda memilih ''Kembali''.\n\nOpsi Menu :\n1. Reservasi\n2. Track Order Booking\n', 3, 2),
(317, '2021-06-17 14:08:46', '2', 2, 3),
(318, '2021-06-17 14:08:46', 'Anda memilih ''Check Booking/Track order''.\nSilahkan masukkan order id : \n\nOpsi Menu : \n0) Kembali\n', 3, 2),
(319, '2021-06-17 14:08:50', '0', 2, 3),
(320, '2021-06-17 14:08:50', 'Anda memilih ''Kembali''.\n\nOpsi Menu :\n1. Reservasi\n2. Track Order Booking\n', 3, 2),
(321, '2021-06-17 14:08:51', '1', 2, 3),
(322, '2021-06-17 14:08:51', 'Anda memilih ''Reservasi''.\nOpsi Menu : \n0) Kembali\n1) Cek ketersediaan villa pada tanggal tertentu\n2) Booking villa\n', 3, 2),
(323, '2021-06-17 14:08:54', '1', 2, 3),
(324, '2021-06-17 14:08:55', 'Anda memilih ''Cek Ketersediaan villa pada tnaggal tertentu''.\n----Keterangan----\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nContoh Pesan (NoVilla#check-in#check-out) : 1#2021-06-25#2021-06-30\n\nOpsi Menu : \n0) Kembali\n', 3, 2),
(325, '2021-06-17 14:08:59', '1#2021-06-25#2021-06-30', 2, 3),
(326, '2021-06-17 14:08:59', 'Villa tersedia pada tanggal 2021-06-25-2021-06-30\n\nOpsi Menu : \n0) Kembali\n1) Cek Ketersediaan Villa pada tanggal tertentu\n2) Booking Villa\n', 3, 2),
(327, '2021-06-17 14:09:04', '2', 2, 3),
(328, '2021-06-17 14:09:04', 'Anda memilih ''Booking Villa''.\n----Keterangan----\nDaftar Villa : \n1)The La Llorona\n2)The Beach Next Door\n3)The Wonderland\n\nTanggal check-in dan check-out menggunakan format ini : yyyy-MM-dd\nTotal Guest adalah berapa banyak yang akan menghuni villa\nNotes adalah pesan tambahan yang ingin disampaikan kepada admin\nFORMAT PESAN : \nidVilla#checkin#checkout#TotalGuest#Notes\nContoh : 2#2021-06-15#2021-06-20#5#Butuh kompor 2\n\nOpsi Menu : \n0) Kembali', 3, 2),
(329, '2021-06-17 14:09:10', '2#2021-06-15#2021-06-20#5#Butuh kompor 2', 2, 3),
(330, '2021-06-17 14:09:10', 'Villa sudah terbooking. Silahkan mengupload bukti pembayaran sebelum tanggal checkin\nOrder Id reservasi : 1001\nOpsi Menu : \n0) Kembali\n1) Cek Ketersediaan Villa pada tanggal tertentu\n2) Booking Villa\n', 3, 2),
(331, '2021-06-17 14:09:21', '0', 2, 3),
(332, '2021-06-17 14:09:21', 'Anda memilih ''Kembali''.\n\nOpsi Menu :\n1) Reservasi\n2) Track Order Booking\n', 3, 2),
(333, '2021-06-17 14:09:37', 'Hallo Admin ', 2, 3),
(334, '2021-06-17 14:09:43', 'hallo bambank ngepet', 3, 2),
(335, '2021-06-17 14:11:37', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(336, '2021-06-17 14:11:56', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 1),
(337, '2021-06-17 14:12:16', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(338, '2021-06-17 14:12:43', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(339, '2021-06-17 14:12:47', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 1),
(340, '2021-06-17 14:13:00', 'haha', 2, 3),
(341, '2021-06-17 14:13:04', 'asd', 2, 3),
(342, '2021-06-17 14:13:14', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 1),
(343, '2021-06-17 14:13:21', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(344, '2021-06-17 14:14:38', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(345, '2021-06-17 14:14:46', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(346, '2021-06-17 14:19:57', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(347, '2021-06-17 14:21:43', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(348, '2021-06-17 14:24:02', 'Selamat datang di hibitsu\nJika ingin konsultasi langsung dengan Admin,\nanda dapat menekan chat/audio/video call di atas,\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2),
(349, '2021-06-17 14:24:09', 'Selamat Datang Kembali!\nJika ingin berbicara dengan saya(BotChat),\nanda dapat melakukannya dengan memilih salah satu angka dari opsi di bawah :\n1) Reservasi\n2) Track Order Booking\nEndFromBot', 3, 2);

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
  `total_price` int(9) NOT NULL,
  `url_bukti_pembayaran` varchar(255) DEFAULT NULL,
  `iduser` int(11) NOT NULL,
  `idvilla` int(11) NOT NULL,
  PRIMARY KEY (`idreservation`),
  KEY `fk_reservations_users_idx` (`iduser`),
  KEY `fk_reservations_villas1_idx` (`idvilla`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1002 ;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`idreservation`, `res_timestamp`, `checkin_date`, `checkout_date`, `status`, `total_guest`, `notes`, `total_price`, `url_bukti_pembayaran`, `iduser`, `idvilla`) VALUES
(111, '2021-06-14 09:34:52', '2021-06-01 00:00:00', '2021-06-03 00:00:00', 'PENDING', 3, 'Kasik bunga', 4000000, NULL, 1, 2),
(222, '2021-06-14 09:34:56', '2021-06-02 00:00:00', '2021-06-03 00:00:00', 'PENDING', 0, '', 2000000, NULL, 2, 2),
(333, '2021-06-17 12:24:31', '2022-06-01 00:00:00', '2022-06-02 00:00:00', 'CANCELED', 10, 'GAK PAKEK GRILL ', 1800000, NULL, 1, 1),
(444, '2021-06-17 12:07:35', '2021-06-15 00:00:00', '2021-06-18 00:00:00', 'PENDING', 0, 'BAMBANK', 5400000, NULL, 1, 1),
(555, '2021-06-16 15:32:05', '2021-06-24 00:00:00', '2021-06-25 00:00:00', 'PENDING', 0, 'Butuh grill', 2000000, NULL, 1, 2),
(666, '2021-06-16 13:17:09', '2021-07-01 00:00:00', '2021-06-02 00:00:00', 'PENDING', 5, '', 500000, NULL, 2, 2),
(777, '2021-06-16 13:17:09', '2021-07-03 00:00:00', '2021-07-04 00:00:00', 'PENDING', 6, '', 8388607, NULL, 1, 1),
(888, '2021-06-16 13:19:44', '2021-06-29 00:00:00', '2021-06-30 00:00:00', 'PENDING', 2, 'Bawa sapi', 2500000, NULL, 2, 3),
(999, '2021-06-16 13:23:36', '2021-07-30 00:00:00', '2021-07-31 00:00:00', 'APPROVED', 5, '', 890000, NULL, 2, 3),
(1000, '2021-06-17 12:28:28', '2022-07-08 00:00:00', '2022-07-09 00:00:00', 'CANCELED', 0, 'BAMBANK', 2000000, NULL, 2, 2),
(1001, '2021-06-17 14:09:10', '2021-06-15 00:00:00', '2021-06-20 00:00:00', 'PENDING', 5, 'Butuh kompor 2', 10000000, NULL, 2, 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`iduser`, `fullname`, `display_name`, `phone_number`, `email`, `password`, `role`, `no_ktp`) VALUES
(1, 'Jasti Ohanna', 'jasti', '08123456789', 'jasti@gmail.com', 'jasti', 'CLIENT', '3315060711900001'),
(2, 'Theophil Henry Soegianto', 'theo', '08123456789', 'theo@gmail.com', 'theo', 'CLIENT', '3315143107800001'),
(3, 'Christopher Tri Anugrah', 'toto', '08123456789', 'toto@gmail.com', 'toto', 'ADMIN', '3315142512700001');

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
