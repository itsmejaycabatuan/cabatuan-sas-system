-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2025 at 01:54 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scholarship`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_logs`
--

CREATE TABLE `tbl_logs` (
  `log_id` int(50) NOT NULL,
  `user_id` int(50) NOT NULL,
  `action` varchar(500) NOT NULL,
  `date_time` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_logs`
--

INSERT INTO `tbl_logs` (`log_id`, `user_id`, `action`, `date_time`) VALUES
(1, 24, 'Login', '2025-03-24 06:30:43.595585'),
(37, 24, 'Login', '2025-03-24 07:25:47.900992'),
(38, 24, 'Login', '2025-03-24 07:28:56.692339'),
(40, 24, 'Login', '2025-03-24 07:33:18.401027'),
(42, 24, 'Login', '2025-03-24 07:41:57.764994'),
(43, 24, 'Login', '2025-03-24 07:45:17.894167'),
(44, 24, 'Login', '2025-03-24 07:49:55.723428'),
(45, 24, 'Login', '2025-03-24 07:51:00.312041'),
(46, 24, 'Login', '2025-03-24 07:55:36.831605'),
(48, 24, 'Login', '2025-03-24 07:57:46.397053'),
(50, 24, 'Login', '2025-03-24 08:11:31.753772'),
(51, 24, 'Login', '2025-03-24 08:13:01.823494'),
(53, 24, 'Login', '2025-03-24 08:18:55.244128'),
(63, 24, 'Login', '2025-03-24 08:28:22.617803'),
(65, 24, 'Login', '2025-03-24 08:31:02.079361'),
(67, 24, 'Login', '2025-03-24 08:34:10.170126'),
(68, 24, 'Login', '2025-03-24 08:36:12.117018'),
(69, 24, 'Login', '2025-03-24 08:39:10.985896'),
(70, 24, 'Login', '2025-03-24 08:39:42.467190'),
(71, 24, 'Login', '2025-03-24 08:40:19.137613'),
(72, 24, 'Admin deleted user with ID: 31', '2025-03-24 08:40:25.000000'),
(73, 25, 'Admin updated user with ID: 25', '2025-03-24 08:40:59.296492'),
(75, 24, 'Login', '2025-03-25 05:06:10.367601'),
(77, 24, 'Login', '2025-03-25 05:16:09.237981'),
(78, 24, 'Admin deleted user with ID: 32', '2025-03-25 05:16:22.000000'),
(79, 33, 'Admin Added A User', '2025-03-25 05:17:22.889768'),
(80, 33, 'Admin updated user with ID: 33', '2025-03-25 05:17:39.836379'),
(81, 24, 'Login', '2025-03-25 05:29:06.530895'),
(82, 24, 'Admin updated contact number', '2025-03-25 05:29:48.000000'),
(83, 24, 'Login', '2025-03-25 05:31:43.577589'),
(84, 24, 'Login', '2025-03-25 05:33:31.981066'),
(85, 24, 'Login', '2025-03-25 05:35:15.195089'),
(86, 24, 'Login', '2025-03-25 05:39:02.976879'),
(87, 24, 'Login', '2025-03-25 05:41:15.952675'),
(88, 24, 'Admin updated contact number', '2025-03-25 05:41:43.000000'),
(89, 24, 'Login', '2025-03-25 05:44:15.535217'),
(90, 24, 'Login', '2025-03-25 05:47:41.746493'),
(91, 25, 'Login', '2025-03-25 05:50:42.614159'),
(92, 24, 'Login', '2025-03-25 05:53:20.701504'),
(93, 24, 'Login', '2025-03-25 05:54:45.701550'),
(94, 24, 'Login', '2025-03-25 05:56:16.485063'),
(95, 24, 'Login', '2025-03-25 05:57:16.562301'),
(96, 24, 'Login', '2025-03-25 05:58:53.604756'),
(97, 24, 'Logout', '2025-03-25 05:58:58.697825'),
(98, 24, 'Login', '2025-03-25 05:59:06.820655'),
(99, 24, 'Admin updated contact number', '2025-03-25 05:59:42.000000'),
(100, 24, 'Login', '2025-03-25 06:01:19.668605'),
(101, 25, 'Login', '2025-03-25 06:03:21.717169'),
(102, 25, 'Login', '2025-03-25 06:04:17.139726'),
(103, 25, 'Admin updated contact number', '2025-03-25 06:04:38.000000'),
(104, 24, 'Login', '2025-03-25 06:06:00.608325'),
(105, 24, 'Admin updated contact number', '2025-03-25 06:06:51.000000'),
(106, 25, 'Login', '2025-03-25 06:14:15.897579'),
(107, 25, 'Applicant updated contact number.', '2025-03-25 06:14:45.000000'),
(108, 25, 'Logout', '2025-03-25 06:15:09.059664'),
(109, 24, 'Login', '2025-03-25 06:15:13.862484'),
(110, 25, 'Login', '2025-03-25 06:16:26.003497'),
(111, 25, 'Applicant updated email address.', '2025-03-25 06:17:35.000000'),
(112, 24, 'Login', '2025-03-25 06:18:54.722235'),
(113, 24, 'Login', '2025-03-25 06:21:49.082463'),
(114, 24, 'Admin updated email', '2025-03-25 06:23:26.000000'),
(115, 24, 'Login', '2025-03-25 06:25:26.579184'),
(116, 24, 'Login', '2025-03-25 06:28:26.338124'),
(117, 24, 'Login', '2025-03-25 06:29:20.889770'),
(118, 24, 'Admin changed a password', '2025-03-25 06:29:44.000000'),
(119, 24, 'Login', '2025-03-30 03:25:46.617107'),
(120, 25, 'Login', '2025-03-30 03:31:20.691236'),
(121, 25, 'Admin changed a password', '2025-03-30 03:31:38.000000'),
(122, 25, 'Login', '2025-03-30 03:32:09.088186'),
(123, 25, 'Applicant changed a password', '2025-03-30 03:32:49.000000'),
(124, 25, 'Logout', '2025-03-30 03:32:54.706617'),
(125, 24, 'Login', '2025-03-30 03:33:05.708604'),
(126, 25, 'Login', '2025-03-30 03:37:05.991310'),
(127, 25, 'SPC updated contact number.', '2025-03-30 03:37:47.000000'),
(128, 25, 'Logout', '2025-03-30 03:37:54.998704'),
(129, 24, 'Login', '2025-03-30 03:38:09.211252'),
(130, 25, 'Login', '2025-03-30 03:40:28.730370'),
(131, 25, 'SPC updated email address', '2025-03-30 03:41:12.000000'),
(132, 25, 'Logout', '2025-03-30 03:41:18.043532'),
(133, 24, 'Login', '2025-03-30 03:41:25.741848'),
(134, 33, 'Login', '2025-03-30 03:47:24.442389'),
(135, 24, 'Login', '2025-03-30 04:07:32.498983'),
(136, 33, 'Login', '2025-03-30 04:08:13.751019'),
(137, 33, 'Login', '2025-03-30 04:08:58.302566'),
(138, 33, 'Login', '2025-03-30 04:21:55.088846'),
(139, 33, 'Login', '2025-03-30 04:27:57.600634'),
(140, 33, 'Login', '2025-03-30 04:49:24.577267'),
(141, 33, 'Login', '2025-03-30 04:50:19.641886'),
(142, 33, 'Login', '2025-03-30 04:53:22.295390'),
(143, 33, 'Login', '2025-03-30 05:10:27.888176'),
(144, 24, 'Login', '2025-03-30 05:28:22.327217'),
(145, 24, 'Login', '2025-03-30 05:35:20.440871'),
(146, 34, 'Admin Added A User', '2025-03-30 05:36:06.341147'),
(147, 24, 'Login', '2025-03-30 05:38:40.629613'),
(148, 35, 'Admin Added A User', '2025-03-30 05:40:30.268337'),
(149, 24, 'Login', '2025-03-30 05:55:02.944772'),
(150, 24, 'Login', '2025-03-30 05:55:38.999091'),
(151, 35, 'Admin updated user with ID: 35', '2025-03-30 05:55:54.692440'),
(152, 24, 'Login', '2025-03-30 06:04:41.680688'),
(153, 35, 'Admin updated user with ID: 35', '2025-03-30 06:05:00.483336'),
(154, 35, 'Admin updated user with ID: 35', '2025-03-30 06:05:47.898854'),
(155, 35, 'Admin updated user with ID: 35', '2025-03-30 06:06:03.211166'),
(156, 35, 'Login', '2025-03-30 06:12:48.724282'),
(157, 35, 'Login', '2025-03-30 06:24:53.690102'),
(158, 35, 'Login', '2025-03-30 06:25:22.766389'),
(159, 35, 'Login', '2025-03-30 06:26:20.091246'),
(160, 35, 'Login', '2025-03-30 06:32:17.117367'),
(161, 35, 'Login', '2025-03-30 06:35:30.583782'),
(162, 35, 'Login', '2025-03-30 06:36:57.195974'),
(163, 35, 'Login', '2025-03-30 06:38:25.737635'),
(164, 35, 'Login', '2025-03-30 06:44:58.454741'),
(165, 34, 'Login', '2025-03-30 06:46:45.466112'),
(166, 24, 'Login', '2025-03-30 06:47:15.716043'),
(167, 35, 'Admin updated user with ID: 35', '2025-03-30 06:47:59.519751'),
(168, 24, 'Logout', '2025-03-30 06:48:16.092115'),
(169, 35, 'Login', '2025-03-30 06:48:26.196491'),
(170, 24, 'Login', '2025-03-30 06:51:42.251895'),
(171, 35, 'Login', '2025-03-30 06:53:08.626871'),
(172, 35, 'Login', '2025-03-30 06:54:37.181030'),
(173, 35, 'Login', '2025-03-30 06:55:32.896942'),
(174, 24, 'Login', '2025-03-30 06:57:34.177454'),
(175, 34, 'Login', '2025-03-30 06:59:49.251539'),
(176, 34, 'Logout', '2025-03-30 07:00:27.367353'),
(177, 24, 'Login', '2025-03-30 07:00:35.032503'),
(178, 24, 'Login', '2025-03-30 07:02:04.028161'),
(179, 34, 'Admin updated user with ID: 34', '2025-03-30 07:02:58.888446'),
(180, 24, 'Logout', '2025-03-30 07:03:14.486602'),
(181, 34, 'Login', '2025-03-30 07:03:20.391675'),
(182, 24, 'Login', '2025-03-30 07:04:16.875289'),
(183, 24, 'Login', '2025-03-30 07:05:03.699161'),
(184, 24, 'Login', '2025-03-30 07:05:41.338788'),
(185, 24, 'Login', '2025-03-30 07:06:40.927467'),
(186, 34, 'Admin updated user with ID: 34', '2025-03-30 07:06:49.431407'),
(187, 34, 'Admin updated user with ID: 34', '2025-03-30 07:07:03.812625'),
(188, 35, 'Login', '2025-03-30 07:07:34.463578'),
(189, 24, 'Login', '2025-03-30 07:12:03.274192'),
(190, 24, 'Logout', '2025-03-30 07:12:51.858936'),
(191, 35, 'Login', '2025-03-30 07:13:00.487188'),
(192, 35, 'Login', '2025-03-30 07:17:50.676489'),
(193, 35, 'Login', '2025-03-30 07:19:16.497270'),
(194, 35, 'Login', '2025-03-30 07:20:53.506296'),
(195, 35, 'Login', '2025-03-30 07:22:56.786244'),
(196, 35, 'Login', '2025-03-30 07:26:49.475854'),
(197, 24, 'Login', '2025-03-30 07:37:45.895779'),
(198, 24, 'Login', '2025-03-30 07:38:47.187893'),
(199, 24, 'Login', '2025-03-30 07:39:44.945819'),
(200, 34, 'Login', '2025-03-30 07:40:57.904764'),
(201, 24, 'Login', '2025-03-30 07:41:29.700318'),
(202, 35, 'Login', '2025-03-30 07:42:12.992472'),
(203, 24, 'Login', '2025-03-30 07:45:47.025983'),
(204, 35, 'Login', '2025-03-30 07:46:44.192485'),
(205, 34, 'Logout', '2025-03-30 07:46:58.179550'),
(206, 34, 'Login', '2025-03-30 07:47:10.255208'),
(207, 24, 'Login', '2025-03-30 07:48:23.284805'),
(208, 25, 'Login', '2025-03-30 07:56:00.253994'),
(209, 35, 'Login', '2025-03-30 08:00:03.641472'),
(210, 35, 'Login', '2025-03-30 08:01:19.168087'),
(211, 35, 'Login', '2025-03-30 08:06:20.474596'),
(212, 25, 'Login', '2025-03-30 08:50:24.889569'),
(213, 25, 'Login', '2025-03-30 08:51:52.029899'),
(214, 25, 'Login', '2025-03-30 08:52:23.493867'),
(215, 25, 'Login', '2025-03-30 08:52:51.904556'),
(216, 25, 'Login', '2025-03-30 09:05:02.350736'),
(217, 25, 'Login', '2025-03-30 09:06:34.734823'),
(218, 25, 'Login', '2025-03-30 09:08:51.131918'),
(219, 25, 'Login', '2025-03-30 09:09:59.142339'),
(220, 25, 'Login', '2025-03-30 09:10:41.383916'),
(221, 25, 'Login', '2025-03-30 09:11:55.284511'),
(222, 25, 'Login', '2025-03-30 09:15:09.218773'),
(223, 25, 'Login', '2025-03-30 09:18:34.389690'),
(224, 25, 'Login', '2025-03-30 09:19:27.648541'),
(225, 25, 'Login', '2025-03-30 09:21:08.715914'),
(226, 25, 'Login', '2025-03-30 09:24:48.582370'),
(227, 25, 'Login', '2025-03-30 09:27:26.291414'),
(228, 35, 'Login', '2025-03-30 09:28:06.029080'),
(229, 25, 'Login', '2025-03-30 09:29:43.648574'),
(230, 25, 'Login', '2025-03-30 09:30:41.305863'),
(231, 25, 'Login', '2025-03-30 09:32:09.904230'),
(232, 25, 'Login', '2025-03-30 09:35:07.226922'),
(233, 25, 'Login', '2025-03-30 09:37:00.041751'),
(234, 25, 'Login', '2025-03-30 09:38:17.830120'),
(235, 25, 'Logout', '2025-03-30 09:38:51.916326'),
(236, 35, 'Login', '2025-03-30 09:39:03.336372'),
(237, 35, 'Login', '2025-03-30 09:39:55.060444'),
(238, 35, 'Login', '2025-03-30 09:41:42.596739');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_records`
--

CREATE TABLE `tbl_records` (
  `r_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `u_email` int(50) NOT NULL,
  `contact` int(11) NOT NULL,
  `s_id` int(10) NOT NULL,
  `GPA` int(10) NOT NULL,
  `requirements_pass` varchar(1000) NOT NULL,
  `annual_income` int(255) NOT NULL,
  `date_submit` date NOT NULL,
  `status` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_scholarship`
--

CREATE TABLE `tbl_scholarship` (
  `s_id` int(10) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  `GPA` varchar(10) NOT NULL,
  `requirements` varchar(100) NOT NULL,
  `annual_income` varchar(255) NOT NULL,
  `fund_amount` varchar(255) NOT NULL,
  `capacity` mediumint(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `Deadline` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_scholarship`
--

INSERT INTO `tbl_scholarship` (`s_id`, `s_name`, `description`, `GPA`, `requirements`, `annual_income`, `fund_amount`, `capacity`, `status`, `Deadline`) VALUES
(2, 'Tes', 'Test', '3.0', 'Test', '20000', '20000', 500, 'Available', '2025-03-10'),
(3, 'Unifast', 'Test Grantees (2024-2025)', 'Above 2.5', 'REPORT CARD, form132', '300000 yearly', '1500 monthly', 100, 'Not Available', '2026-05-15');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_security`
--

CREATE TABLE `tbl_security` (
  `u_id` int(50) NOT NULL,
  `security_question` varchar(100) NOT NULL,
  `security_answer` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `u_id` int(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `f_name` varchar(30) NOT NULL,
  `l_name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `type` varchar(500) NOT NULL,
  `pass` varchar(150) NOT NULL,
  `status` varchar(15) NOT NULL,
  `registration_date` timestamp(6) NULL DEFAULT current_timestamp(6),
  `sec_ques1` varchar(500) NOT NULL,
  `answer_1` varchar(50) NOT NULL,
  `sec_ques2` varchar(500) NOT NULL,
  `answer_2` varchar(50) NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`u_id`, `username`, `f_name`, `l_name`, `email`, `contact`, `type`, `pass`, `status`, `registration_date`, `sec_ques1`, `answer_1`, `sec_ques2`, `answer_2`, `image`) VALUES
(24, 'current', 'test', 'Test1', 'current2@gmail.com', '12345678911', 'Admin', 'XxqSkx8Ucu9jA+5rbzIN1MezvPLMPD6DlTcv8UU4uqI=', 'Active', '2025-03-12 05:27:02.000000', 'In what city you are born?', 'Cebu City', 'What is your father name?', 'Antonio', 'src/userimages/482242823_1810150549837747_3526077695476022225_n.jpg'),
(25, 'user', 'Add', 'User', 'user@gmail.com', '09091919191', 'Scholarship Providers/Committee', 'sdcvH6WthR8jlmFbROwDExTe24VSmTYToT8MIUngftY=', 'Active', '2025-03-12 06:34:11.000000', 'What was the name of your first pet?', 'karl', 'What is the name of your favorite childhood friend?', 'campoy', 'src/userimages/2.png'),
(33, 'mike', 'jordan', 'hah', 'mike@gmail.com', '09291919191', 'Applicant', 'sdcvH6WthR8jlmFbROwDExTe24VSmTYToT8MIUngftY=', 'Active', '2025-03-25 05:17:21.000000', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A'),
(34, 'jay', 'jayjay', 'cabatuan', 'jaycabatuan@gmail.com', '09091919191', 'Admin', 'sdcvH6WthR8jlmFbROwDExTe24VSmTYToT8MIUngftY=', 'Active', '2025-03-30 05:36:05.000000', 'N/A', 'N/A', 'N/A', 'N/A', 'src/userimages/2.png'),
(35, 'testing', 'jayjay', 'cabatuan', 'testing@gmail.com', '09091919191', 'Applicant', 'sdcvH6WthR8jlmFbROwDExTe24VSmTYToT8MIUngftY=', 'Active', '2025-03-30 05:40:27.000000', 'N/A', 'N/A', 'N/A', 'N/A', 'src/userimages/3image.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `tbl_records`
--
ALTER TABLE `tbl_records`
  ADD PRIMARY KEY (`r_id`),
  ADD KEY `u_id` (`user_id`),
  ADD KEY `s_id` (`s_id`);

--
-- Indexes for table `tbl_scholarship`
--
ALTER TABLE `tbl_scholarship`
  ADD PRIMARY KEY (`s_id`);

--
-- Indexes for table `tbl_security`
--
ALTER TABLE `tbl_security`
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  MODIFY `log_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=239;

--
-- AUTO_INCREMENT for table `tbl_records`
--
ALTER TABLE `tbl_records`
  MODIFY `r_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_scholarship`
--
ALTER TABLE `tbl_scholarship`
  MODIFY `s_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `u_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_logs`
--
ALTER TABLE `tbl_logs`
  ADD CONSTRAINT `tbl_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`u_id`);

--
-- Constraints for table `tbl_records`
--
ALTER TABLE `tbl_records`
  ADD CONSTRAINT `s_id` FOREIGN KEY (`s_id`) REFERENCES `tbl_scholarship` (`s_id`),
  ADD CONSTRAINT `u_id` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`u_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
