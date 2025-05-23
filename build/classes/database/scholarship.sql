-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2025 at 01:59 PM
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

--
-- Dumping data for table `tbl_records`
--

INSERT INTO `tbl_records` (`r_id`, `user_id`, `s_id`, `GPA`, `requirements_pass`, `annual_income`, `date_submit`, `status`) VALUES
(1, 1, 1, 1, 'REPORT CARD', 10000, '2025-02-11', 'Pending');

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
  `annual_income` int(255) NOT NULL,
  `fund_amount` int(255) NOT NULL,
  `capacity` int(100) NOT NULL,
  `status` varchar(50) NOT NULL,
  `Deadline` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_scholarship`
--

INSERT INTO `tbl_scholarship` (`s_id`, `s_name`, `description`, `GPA`, `requirements`, `annual_income`, `fund_amount`, `capacity`, `status`, `Deadline`) VALUES
(1, 'UNIFAST', 'FAST AND SLOW', 2, 'REPORT CARD', 20000, 10000, 300, 'Not full', '');

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
  `type` varchar(20) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `cpass` varchar(50) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`u_id`, `username`, `f_name`, `l_name`, `email`, `contact`, `type`, `pass`, `cpass`, `status`) VALUES
(1, 'jay', 'allen jay', 'cabatuan', 'jay@gmail.com', '09099009', 'Admin', 'pass', 'pass', 'Pending'),
(2, 'jay', 'jayjay', 'cabatuan', 'allenjaycabatuan@gmail.com', '0909090909', 'Admin', 'pass', 'pass', 'Pending'),
(3, 'adaw', 'awdawd', 'adaw', 'awdad', 'adwaw', 'Admin', 'awdwad', 'awdawd', 'Pending'),
(4, 'jay', 'jay', 'jay', 'awdawd', '09090999', 'Admin', 'password', 'password', 'Pending'),
(6, 'jay', 'jayjay', 'jayjay', 'jay@gmail.com', '8080808', 'Admin', 'ww', 'ww', 'Pending'),
(7, 'ww', 'ww', 'ww', 'ww@gmail.com', '09090909', 'Admin', '111111111', '21', 'Pending'),
(8, 'ww', 'ww', 'ww', 'ww@gmail.com', '0909090', 'Admin', 'wwwwwwww', 'wwwwwwww', 'Pending');

--
-- Indexes for dumped tables
--

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
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_records`
--
ALTER TABLE `tbl_records`
  MODIFY `r_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_scholarship`
--
ALTER TABLE `tbl_scholarship`
  MODIFY `s_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `u_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

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
