-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2020 at 07:20 PM
-- Server version: 10.3.15-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `indoor_positioning`
--

-- --------------------------------------------------------

--
-- Table structure for table `beacon`
--

CREATE TABLE `beacon` (
  `id_bk` int(11) NOT NULL,
  `major` int(11) NOT NULL,
  `minor` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `beacon`
--

INSERT INTO `beacon` (`id_bk`, `major`, `minor`, `name`) VALUES
(2, 258, 2, 'Beacon2'),
(5, 258, 5, 'Beacon5'),
(6, 258, 6, 'Beacon6'),
(9, 258, 9, 'Beacon9'),
(4, 258, 4, 'Beacon4'),
(7, 258, 7, 'Beacon7'),
(1, 258, 1, 'Beacon1'),
(3, 258, 3, 'Beacon3'),
(8, 258, 8, 'Beacon8');

-- --------------------------------------------------------

--
-- Table structure for table `component`
--

CREATE TABLE `component` (
  `id_cmp` int(11) NOT NULL,
  `e_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `s_date` datetime DEFAULT NULL,
  `id_bk` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `component`
--

INSERT INTO `component` (`id_cmp`, `e_date`, `name`, `s_date`, `id_bk`) VALUES
(1, NULL, 'Equipo de sonido.', '2020-04-13 18:17:33', 1),
(2, NULL, 'Iluminación.', '2020-04-13 18:20:34', 2),
(3, NULL, 'Estructuras.', '2020-04-13 18:33:38', 3),
(4, NULL, 'Ordenadores.', '2020-04-13 18:33:50', 4),
(5, NULL, 'Pantallas.', '2020-04-13 18:33:57', 5),
(6, NULL, 'Cableado.', '2020-04-13 18:34:19', 6),
(7, NULL, 'Máquinas de humo.', '2020-04-13 18:34:42', 7),
(8, NULL, 'Plataforma.', '2020-04-13 18:36:16', 8),
(11, '2020-04-13 18:37:51', 'Carro.', '2020-04-13 18:36:46', 9),
(9, NULL, 'Motor.', '2020-04-13 18:37:57', 9);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id_history` int(11) NOT NULL,
  `battery` float DEFAULT NULL,
  `date` datetime NOT NULL,
  `rssi` int(11) NOT NULL,
  `id_bk` int(11) NOT NULL,
  `id_rcv` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id_history`, `battery`, `date`, `rssi`, `id_bk`, `id_rcv`) VALUES
(2, 100, '2020-04-11 17:39:10', -44, 1, 1),
(1, 100, '2020-04-11 17:39:10', -55, 1, 0),
(3, 100, '2020-04-11 17:39:10', -66, 1, 2),
(4, 99, '2020-04-11 18:55:15', -12, 2, 0),
(5, 99, '2020-04-11 18:55:15', -22, 2, 1),
(6, 99, '2020-04-11 18:55:15', -33, 2, 2),
(7, 98, '2020-04-11 19:05:22', -60, 3, 0),
(8, 98, '2020-04-11 19:05:22', -34, 3, 1),
(9, 98, '2020-04-11 19:05:22', -8, 3, 2),
(10, 97, '2020-04-11 19:25:22', -63, 4, 0),
(11, 97, '2020-04-11 19:25:22', -9, 4, 1),
(12, 97, '2020-04-11 19:25:22', -70, 4, 2),
(13, 96, '2020-04-11 19:33:22', -6, 5, 0),
(14, 96, '2020-04-11 19:33:22', -77, 5, 1),
(15, 96, '2020-04-11 19:33:22', -56, 5, 2),
(16, 95, '2020-04-11 19:37:22', -14, 6, 0),
(17, 95, '2020-04-11 19:37:22', -66, 6, 1),
(18, 95, '2020-04-11 19:37:22', -31, 6, 2),
(19, 95, '2020-04-11 19:41:22', -38, 7, 0),
(20, 95, '2020-04-11 19:41:22', -47, 7, 1),
(21, 95, '2020-04-11 19:41:22', -16, 7, 2),
(22, 94, '2020-04-11 19:46:36', -29, 8, 0),
(23, 94, '2020-04-11 19:46:36', -7, 8, 1),
(24, 94, '2020-04-11 19:46:36', -45, 8, 2),
(25, 93, '2020-04-11 19:52:36', -69, 9, 0),
(26, 93, '2020-04-11 19:52:36', -23, 9, 1),
(27, 93, '2020-04-11 19:52:36', -7, 9, 2),
(31, 91, '2020-04-11 20:03:36', -69, 1, 0),
(32, 91, '2020-04-11 20:03:36', -23, 1, 1),
(33, 91, '2020-04-11 20:03:36', -7, 1, 2),
(34, 91, '2020-04-11 20:03:36', -69, 1, 0),
(35, 91, '2020-04-11 20:03:36', -23, 1, 1),
(36, 91, '2020-04-11 20:03:36', -7, 1, 2),
(37, 91, '2020-04-11 20:03:36', -55, 2, 0),
(38, 91, '2020-04-11 20:03:36', -44, 2, 1),
(39, 91, '2020-04-11 20:03:36', -66, 2, 2),
(40, 91, '2020-04-11 20:03:36', -55, 2, 0),
(41, 91, '2020-04-11 20:03:36', -44, 2, 1),
(42, 91, '2020-04-11 20:03:36', -66, 2, 2),
(43, 91, '2020-04-11 20:03:36', -12, 3, 0),
(44, 91, '2020-04-11 20:03:36', -22, 3, 1),
(45, 91, '2020-04-11 20:03:36', -33, 3, 2),
(46, 91, '2020-04-11 20:03:36', -12, 3, 0),
(47, 91, '2020-04-11 20:03:36', -22, 3, 1),
(48, 91, '2020-04-11 20:03:36', -33, 3, 2),
(49, 91, '2020-04-11 20:03:36', -60, 4, 0),
(50, 91, '2020-04-11 20:03:36', -34, 4, 1),
(51, 91, '2020-04-11 20:03:36', -8, 4, 2),
(52, 91, '2020-04-11 20:03:36', -60, 4, 0),
(53, 91, '2020-04-11 20:03:36', -34, 4, 1),
(54, 91, '2020-04-11 20:03:36', -8, 4, 2),
(55, 91, '2020-04-11 20:03:36', -63, 5, 0),
(56, 91, '2020-04-11 20:03:36', -9, 5, 1),
(57, 91, '2020-04-11 20:03:36', -70, 5, 2),
(58, 91, '2020-04-11 20:03:36', -63, 5, 0),
(59, 91, '2020-04-11 20:03:36', -9, 5, 1),
(60, 91, '2020-04-11 20:03:36', -70, 5, 2),
(61, 91, '2020-04-11 20:03:36', -6, 6, 0),
(62, 91, '2020-04-11 20:03:36', -77, 6, 1),
(63, 91, '2020-04-11 20:03:36', -56, 6, 2),
(64, 91, '2020-04-11 20:03:36', -6, 6, 0),
(65, 91, '2020-04-11 20:03:36', -77, 6, 1),
(66, 91, '2020-04-11 20:03:36', -56, 6, 2),
(67, 91, '2020-04-11 20:03:36', -14, 7, 0),
(68, 91, '2020-04-11 20:03:36', -66, 7, 1),
(69, 91, '2020-04-11 20:03:36', -31, 7, 2),
(70, 91, '2020-04-11 20:03:36', -14, 7, 0),
(71, 91, '2020-04-11 20:03:36', -66, 7, 1),
(72, 91, '2020-04-11 20:03:36', -31, 7, 2),
(73, 91, '2020-04-11 20:03:36', -38, 8, 0),
(74, 91, '2020-04-11 20:03:36', -47, 8, 1),
(75, 91, '2020-04-11 20:03:36', -16, 8, 2),
(76, 91, '2020-04-11 20:03:36', -38, 8, 0),
(77, 91, '2020-04-11 20:03:36', -47, 8, 1),
(78, 91, '2020-04-11 20:03:36', -16, 8, 2),
(79, 91, '2020-04-11 20:03:36', -29, 9, 0),
(80, 91, '2020-04-11 20:03:36', -7, 9, 1),
(81, 91, '2020-04-11 20:03:36', -45, 9, 2),
(82, 91, '2020-04-11 20:03:36', -29, 9, 0),
(83, 91, '2020-04-11 20:03:36', -7, 9, 1),
(84, 91, '2020-04-11 20:03:36', -45, 9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `receiver`
--

CREATE TABLE `receiver` (
  `id_rcv` int(11) NOT NULL,
  `x_pos` int(11) NOT NULL,
  `y_pos` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receiver`
--

INSERT INTO `receiver` (`id_rcv`, `x_pos`, `y_pos`) VALUES
(0, 80, 98),
(1, 0, 51),
(2, 80, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password_hash`) VALUES
(9, 'user', '$2a$10$qSY69AyYi4LTKiTzlDb3G.o3DIrLPeCkQm3hg3FpsWrF76pyePNUy'),
(10, 'admin', '$2a$10$ti/AeHAH.JFnkJVOPrhozuu2KC/LBEQdSxZQ2Y3lh.tPfOgTLgqO6');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
(9, 'ROLE_USER'),
(10, 'ROLE_USER'),
(10, 'ROLE_ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `beacon`
--
ALTER TABLE `beacon`
  ADD PRIMARY KEY (`id_bk`),
  ADD UNIQUE KEY `UKeq02famlgmikrt83vmsuuf5o5` (`major`,`minor`);

--
-- Indexes for table `component`
--
ALTER TABLE `component`
  ADD PRIMARY KEY (`id_cmp`),
  ADD UNIQUE KEY `UK_qkg4faf5v8maop4avw1y44cxp` (`name`),
  ADD KEY `FKs92o52aeld53t4uvh55xuhxod` (`id_bk`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id_history`),
  ADD KEY `FKit58lsv8wjt97nk23a0huwqse` (`id_bk`),
  ADD KEY `FKsw5u2c70dhxtoa2s1ha7ei39y` (`id_rcv`);

--
-- Indexes for table `receiver`
--
ALTER TABLE `receiver`
  ADD PRIMARY KEY (`id_rcv`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `beacon`
--
ALTER TABLE `beacon`
  MODIFY `id_bk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `component`
--
ALTER TABLE `component`
  MODIFY `id_cmp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id_history` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=196726;

--
-- AUTO_INCREMENT for table `receiver`
--
ALTER TABLE `receiver`
  MODIFY `id_rcv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
