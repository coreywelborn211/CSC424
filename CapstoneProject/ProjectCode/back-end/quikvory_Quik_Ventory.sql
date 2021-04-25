-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 15, 2021 at 08:41 PM
-- Server version: 5.6.41-84.1
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quikvory_Quik_Ventory`
--

DELIMITER $$
--
-- Procedures
--
$$

$$

$$

$$

$$

--
-- Functions
--
$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `user_ID` int(11) NOT NULL,
  `item_ID` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `item_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `qty` int(11) NOT NULL,
  `price` decimal(7,2) DEFAULT NULL,
  `dept` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`user_ID`, `item_ID`, `item_name`, `qty`, `price`, `dept`) VALUES
(1, '111', 'C-3P0', 1, 0.99, 'Rebel Alliance'),
(1, '12345', 'Speeder', 78, 0.02, 'Naboo'),
(1, '2323', 'Millennium Falcon', 1, 2.59, 'Rebel Alliance'),
(1, '3782', 'R2-D2', 1, 2300.00, 'Jedi'),
(1, '4654', 'SaberTrainer', 2, 8.99, 'Jedi'),
(1, '501', 'lightsaber', 1, 100.99, 'jedi'),
(1, '7789', 'Vader\'s Helmet', 1, 0.00, 'Empire'),
(1, '78768', 'Flux Capacitor', 4, 0.02, 'Doc'),
(3, '48783', 'Monster Energy Drink', 3, 4.99, 'Soda');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `email` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `user_ID` int(11) NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`email`, `username`, `user_ID`, `password`) VALUES
('zachary.light@usm.edu', 'zl_test', 1, 'csc424h001'),
('Corey', 'Corey', 2, 'c'),
('Matthew', 'Matthew', 3, 'Matthew');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`user_ID`,`item_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_ID`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`user_ID`) REFERENCES `users` (`user_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
