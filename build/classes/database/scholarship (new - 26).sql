-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2025 at 04:01 AM
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
(118, 24, 'Admin changed a password', '2025-03-25 06:29:44.000000');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_records`
--

CREATE TABLE `tbl_records` (
  `r_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
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
  `GPA` decimal(10,0) NOT NULL,
  `requirements` varchar(100) NOT NULL,
  `annual_income` bigint(255) NOT NULL,
  `fund_amount` bigint(255) NOT NULL,
  `capacity` mediumint(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `Deadline` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_scholarship`
--

INSERT INTO `tbl_scholarship` (`s_id`, `s_name`, `description`, `GPA`, `requirements`, `annual_income`, `fund_amount`, `capacity`, `status`, `Deadline`) VALUES
(2, 'Tes', 'Test', 3, 'Test', 20000, 20000, 500, 'Available', '2025-03-10'),
(3, 'Unifast', 'Test Grantees (2024-2025)', 2, 'REPORT CARD', 30000, 1500, 50, 'Available', '2025-05-15');

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
  `answer_2` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`u_id`, `username`, `f_name`, `l_name`, `email`, `contact`, `type`, `pass`, `status`, `registration_date`, `sec_ques1`, `answer_1`, `sec_ques2`, `answer_2`) VALUES
(24, 'current', 'test', 'Test1', 'current2@gmail.com', '12345678911', 'Admin', 'XxqSkx8Ucu9jA+5rbzIN1MezvPLMPD6DlTcv8UU4uqI=', 'Active', '2025-03-12 05:27:02.000000', 'In what city you are born?', 'Cebu City', 'What is your father name?', 'Antonio'),
(25, 'user', 'Add', 'User', 'user1@gmail.com', '12345678111', 'Applicant', 'sdcvH6WthR8jlmFbROwDExTe24VSmTYToT8MIUngftY=', 'Active', '2025-03-12 06:34:11.000000', 'What was the name of your first pet?', 'karl', 'What is the name of your favorite childhood friend?', 'campoy'),
(33, 'mike', 'jordan', 'hah', 'mike@gmail.com', '09291919191', 'Applicant', 'sdcvH6WthR8jlmFbROwDExTe24VSmTYToT8MIUngftY=', 'Active', '2025-03-25 05:17:21.000000', 'N/A', 'N/A', 'N/A', 'N/A');

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
  MODIFY `log_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- AUTO_INCREMENT for table `tbl_records`
--
ALTER TABLE `tbl_records`
  MODIFY `r_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_scholarship`
--
ALTER TABLE `tbl_scholarship`
  MODIFY `s_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `u_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

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
