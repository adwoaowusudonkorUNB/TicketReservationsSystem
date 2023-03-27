-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2023 at 09:09 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reservations`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
                          `Admin_User_ID` varchar(12) NOT NULL,
                          `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`Admin_User_ID`, `Password`) VALUES
    ('Rahul_Rascal', 'Rascal_No1');

-- --------------------------------------------------------

--
-- Table structure for table `airlines`
--

CREATE TABLE `airlines` (
                            `Airlines` varchar(25) NOT NULL,
                            `Airline_ID` varchar(2) NOT NULL,
                            `hourly_fare_business_class` decimal(16,0) NOT NULL,
                            `hourly_fare_economy_class` decimal(16,0) NOT NULL,
                            `hourly_fare_first_class` decimal(16,0) NOT NULL,
                            `hourly_fare_prem_economy` decimal(16,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `airlines`
--

INSERT INTO `airlines` (`Airlines`, `Airline_ID`, `hourly_fare_business_class`, `hourly_fare_economy_class`, `hourly_fare_first_class`, `hourly_fare_prem_economy`) VALUES
                                                                                                                                                                        ('Biman Bangladesh Airlines', 'BG', '0', '0', '0', '0'),
                                                                                                                                                                        ('Air India', 'IA', '0', '0', '0', '0'),
                                                                                                                                                                        ('Air Peace', 'P4', '0', '0', '0', '0'),
                                                                                                                                                                        ('Qatar Airways', 'QR', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Table structure for table `airports`
--

CREATE TABLE `airports` (
                            `Airport_ID` varchar(3) NOT NULL,
                            `Airport` varchar(10) NOT NULL,
                            `Country` varchar(20) NOT NULL,
                            `Time_Zone` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `airports`
--

INSERT INTO `airports` (`Airport_ID`, `Airport`, `Country`, `Time_Zone`) VALUES
                                                                             ('DAC', 'Dhaka', 'Bangladesh', 600),
                                                                             ('DEL', 'Delhi', 'India', 530),
                                                                             ('DOH', 'Doha', 'Qatar', 300),
                                                                             ('DXB', 'Dubai', 'United Arab Emirates', 400),
                                                                             ('LOS', 'Lagos', 'Nigeria', 100);

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
                            `Ticket_Number` int(8) NOT NULL,
                            `Booking_Date` int(8) NOT NULL,
                            `Booking_Time` int(4) NOT NULL,
                            `Flight_Number` varchar(6) NOT NULL,
                            `Flight_Departure_Date` int(8) NOT NULL,
                            `Booking_ID` varchar(6) NOT NULL,
                            `User_ID` varchar(20) NOT NULL,
                            `Customer_ID` int(5) NOT NULL,
                            `Customer_First_Name` varchar(20) NOT NULL,
                            `Customer_Last_Name` varchar(20) NOT NULL,
                            `Amount` int(6) NOT NULL,
                            `Status` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`Ticket_Number`, `Booking_Date`, `Booking_Time`, `Flight_Number`, `Flight_Departure_Date`, `Booking_ID`, `User_ID`, `Customer_ID`, `Customer_First_Name`, `Customer_Last_Name`, `Amount`, `Status`) VALUES
                                                                                                                                                                                                                                (10000001, 4032023, 1212, 'AI971', 4032023, 'A1A1A1', 'Rahul_Rascal', 1, 'Neel', 'Prajapati', 1000, 'Booked'),
                                                                                                                                                                                                                                (10000002, 4032023, 1212, 'AI971', 4032023, 'A1A1A1', 'Rahul_Rascal', 99999, 'Rahul', 'Mittal', 1000, 'Booked');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
                             `Customer_ID` int(5) NOT NULL,
                             `First_Name` varchar(20) NOT NULL,
                             `Last_Name` varchar(20) NOT NULL,
                             `Date_of_Birth` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Customer_ID`, `First_Name`, `Last_Name`, `Date_of_Birth`) VALUES
                                                                                        (1, 'Neel', 'Prajapati', 1011999),
                                                                                        (99999, 'Rahul', 'Mittal', 31121997);

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
                           `Flight_Number` varchar(6) NOT NULL,
                           `Origin_Airport` varchar(3) NOT NULL,
                           `Destination_Airport` varchar(3) NOT NULL,
                           `Departure_Time` int(4) NOT NULL,
                           `Arrival_Time` int(4) NOT NULL,
                           `Duration` smallint(4) NOT NULL,
                           `Day` tinyint(4) NOT NULL,
                           `Economy_Seats` smallint(6) DEFAULT NULL,
                           `Premium_Economy_Seats` smallint(11) DEFAULT NULL,
                           `Business_Seats` smallint(11) DEFAULT NULL,
                           `FirstClass_Seats` smallint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`Flight_Number`, `Origin_Airport`, `Destination_Airport`, `Departure_Time`, `Arrival_Time`, `Duration`, `Day`, `Economy_Seats`, `Premium_Economy_Seats`, `Business_Seats`, `FirstClass_Seats`) VALUES
                                                                                                                                                                                                                          ('2345', 'XYZ', 'YZW', 1230, 1730, 6, 1, 100, 50, 40, 10),
                                                                                                                                                                                                                          ('AI971', 'DEL', 'DOH', 1925, 2135, 330, 1, 100, 30, 16, 6),
                                                                                                                                                                                                                          ('AI971', 'DEL', 'DOH', 1925, 2135, 330, 2, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('AI971', 'DEL', 'DOH', 1925, 2135, 330, 3, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('AI971', 'DAC', 'DEL', 1750, 2250, 10, 4, 100, 50, 30, 10),
                                                                                                                                                                                                                          ('AI972', 'DOH', 'DEL', 2235, 445, 0, 1, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('AI972', 'DOH', 'DEL', 2235, 445, 0, 2, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('AI972', 'DOH', 'DEL', 2235, 445, 0, 3, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('AI973', 'DEL', 'DOH', 1920, 2135, 330, 1, 200, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('BG397', 'DAC', 'DEL', 1200, 1415, 0, 0, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('BG398', 'DEL', 'DAC', 1515, 1815, 0, 0, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('QR1405', 'DOH', 'LOS', 800, 1405, 0, 0, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('QR1406', 'LOS', 'DOH', 1535, 15, 0, 0, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('QR634', 'DOH', 'DAC', 800, 1405, 0, 0, NULL, NULL, NULL, NULL),
                                                                                                                                                                                                                          ('QR635', 'DAC', 'DOH', 1935, 2235, 0, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                         `User_ID` varchar(20) NOT NULL,
                         `Password` varchar(10) NOT NULL,
                         `First_Name` varchar(5) NOT NULL,
                         `Last_Name` varchar(6) NOT NULL,
                         `Email` varchar(19) NOT NULL,
                         `Contact_No` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_ID`, `Password`, `First_Name`, `Last_Name`, `Email`, `Contact_No`) VALUES
    ('Rahul_Rascal', 'Rascal_No1', 'Rahul', 'Mittal', 'rahul.mittal@unb.ca', 5064409999);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
    ADD PRIMARY KEY (`Admin_User_ID`);

--
-- Indexes for table `airlines`
--
ALTER TABLE `airlines`
    ADD PRIMARY KEY (`Airline_ID`);

--
-- Indexes for table `airports`
--
ALTER TABLE `airports`
    ADD PRIMARY KEY (`Airport_ID`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
    ADD PRIMARY KEY (`Ticket_Number`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
    ADD PRIMARY KEY (`Customer_ID`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
    ADD PRIMARY KEY (`Flight_Number`,`Day`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`User_ID`),
  ADD UNIQUE KEY `Email` (`Email`,`Contact_No`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
