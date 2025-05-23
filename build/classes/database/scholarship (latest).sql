-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 11, 2025 at 09:10 AM
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
  `pass` varchar(50) NOT NULL,
  `cpass` varchar(50) NOT NULL,
  `status` varchar(15) NOT NULL,
  `registration_date` timestamp(6) NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`u_id`, `username`, `f_name`, `l_name`, `email`, `contact`, `type`, `pass`, `cpass`, `status`, `registration_date`) VALUES
(1, 'jay', 'Allen Jay', 'Cabatuan', 'jaycabatuan@gmail.com', '09283919192', 'Admin', 'password1', 'pass', 'Active', '2025-02-25 08:23:56.828848'),
(10, 'ivan', 'Ivan', 'laluna', 'ivanlaluna@gmail.com', '09091212121', 'Applicant', 'password123', 'password123', 'Active', '2025-02-25 08:23:56.828848'),
(11, 'maxrey', 'Max Rey', 'Pantonial', 'maxrey@gmail.com', '09659725973', 'Scholarship Providers/Committee', 'maxrey2025', 'maxrey2025', 'Active', '2025-02-25 08:23:56.828848'),
(15, 'john', 'john', 'doe', 'johndoe@gmail.com', '12345678920', 'Applicant', 'jaycabatuan', 'jaycabatuan', 'Active', '2025-02-25 08:23:56.828848'),
(16, 'reg', 'register', 'ni', 'register@gmail.com', '09090909091', 'Applicant', 'ohahapassword', 'ohahapassword', 'Active', '2025-02-25 08:23:56.828848'),
(17, 'new', 'new', 'comers', 'comers@gmail.com', '09090909091', 'Applicant', 'applicant01', 'applicant01', 'Active', '2025-02-25 08:26:30.270356'),
(18, 'register', 'regs', 'resghaha', 'haha@gmail.com', '09091212121', 'Applicant', 'applicant', 'applicant', 'Active', '2025-02-25 08:29:33.487493'),
(19, 'loading', 'loading', 'loading', 'loading@gmail.com', '09120102401', 'Applicant', 'passwordnasad', 'passwordnasad', 'Active', '2025-02-25 08:39:03.410423');

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
  MODIFY `s_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `u_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
