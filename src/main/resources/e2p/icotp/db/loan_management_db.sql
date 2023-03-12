-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 01, 2023 at 02:30 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `loan_management_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `collateral`
--

CREATE TABLE `collateral` (
  `loaner_id` bigint(20) NOT NULL,
  `loan_id` int(11) NOT NULL,
  `collateral_id` int(11) NOT NULL,
  `collateral` varchar(500) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `status` enum('Seized','Safe','Warning','Neutral') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loaners`
--

CREATE TABLE `loaners` (
  `loaner_id` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `phone` bigint(20) NOT NULL,
  `email` varchar(250) NOT NULL,
  `birthdate` date NOT NULL,
  `social_security` bigint(20) NOT NULL,
  `civil_status` varchar(50) NOT NULL,
  `citizenship` varchar(50) NOT NULL,
  `place_of_birth` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loaners`
--

INSERT INTO `loaners` (`loaner_id`, `name`, `address`, `phone`, `email`, `birthdate`, `social_security`, `civil_status`, `citizenship`, `place_of_birth`) VALUES
(1, 'Zephyr Spade', 'Middle-Earth, Clover Kingdom, 1st Class District', 777666999, 'qwrqw@gmail.com', '2003-06-30', 777777, 'Single', 'Filipino', 'Baybay'),
(2, 'Crowley', 'Hell', 666666, 'CoreyTaylor@yahoo.com', '1915-01-07', 6666666, 'Baybay', 'married', 'Filipino'),
(3, 'Azkaziel', 'Baybay', 9612354813, 'Gideon@gmail.com', '2014-01-31', 3, 'Baybay', 'married', 'Filipino'),
(7, 'Lucatiel', 'Leyte', 9215364841, 'Andrew@hotmail.com', '2015-01-01', 5, 'Baybay', 'married', 'Filipino'),
(8, 'Gadreel', 'U.S.A', 65184698, 'DonaldTrump@gmail.com', '2019-10-10', 1, 'Baybay', 'married', 'Filipino'),
(9, 'Ezekiel', 'South Korean', 48138434, 'LeeDongWok@yahoo.com', '2013-07-06', 6, 'Baybay', 'married', 'Filipino'),
(14, 'Lucifer', 'North pole', 46565656, 'Kimjongon@zohomail.com', '2017-01-12', 0, 'Baybay', 'married', 'Filipino'),
(30, 'Michael', 'Kalubian', 9725681345, 'StephenCurry@yandexmail.com', '2013-06-10', 2, 'Baybay', 'married', 'Filipino'),
(34, 'Gabriel', 'Kansunka', 6464651, 'Cjaymoreno@yahoo.com', '2023-05-12', 999, 'Baybay', 'married', 'Filipino'),
(53, 'Mael', 'Sanfausto', 9687438921, 'Klaythompson@gmail.com', '2013-05-01', 1, 'Baybay', 'married', 'Filipino'),
(61, 'Zachariah', 'san francisco, leyte', 9633255541, 'dodongdurant@hotmail.com', '2013-01-01', 4, 'american', 'U.S.A', 'single'),
(75, 'Raphael', 'gubang', 113655468, 'felix@yahoo.com', '2013-05-01', 6, 'Filipino', 'sa balay', 'signle'),
(81, 'Hyphtareil', 'New york nickes', 8666648843, 'doctorkakaiba@gmail.com', '2015-05-15', 2, 'sa snow', 'unstable', 'american'),
(99, 'Xamiel', 'Japauki', 3212316546, 'kobebrian@yahoo.com', '2013-06-01', 9, 'sa kilid sa basketballan', 'married', 'Filipino'),
(123, 'Metatron', 'Heaven', 21345, 'theoneandonlyfgod@gmail.com', '2023-01-09', 6134, 'Assbutt', 'Angel', 'Heaven'),
(3815448, 'Klaus Hargreeves', 'Umbrella Academy', 23112311, 'immortalKlaus@gmail.com', '1989-10-01', 23415, 'Single', 'Amish', 'Pensylvania'),
(6052370, 'Ben Hargreeves', 'Umbrella Academy', 125214, 'tentacleboi@yahoo.com', '1989-10-01', 25156, 'Single', 'Korean', 'Korea'),
(7880135, 'Uriel', 'Heaven', 426, 'uryyyTheAngel@heavenmail.com', '2011-01-05', 2742, 'Complicated', 'Angel', 'Heaven'),
(8304456, 'Abaddon', 'Hell', 666125412, 'hotChickAbaddon@hellmail.com', '2023-01-30', 666214, 'Widowed', 'Knight of Hell', 'Hell'),
(8522327, 'Five Hargreeves', 'Umbrella Academy', 2412412, 'numberfive@gmail.com', '1989-10-01', 6124312, 'Single', 'American', 'Nowhere');

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

CREATE TABLE `loans` (
  `loan_id` int(11) NOT NULL,
  `loaner_id` bigint(20) NOT NULL,
  `type_id` int(11) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `release_date` date NOT NULL,
  `term` bigint(20) NOT NULL,
  `maturity_date` date NOT NULL,
  `principal` double NOT NULL,
  `interest` double NOT NULL,
  `penalty` double NOT NULL,
  `payment_frequency` varchar(200) NOT NULL,
  `due` int(11) NOT NULL,
  `paid` double NOT NULL,
  `balance` double NOT NULL,
  `status` enum('Application','Open','Closed','Paid') NOT NULL,
  `next_payment` double NOT NULL,
  `next_due_date` date NOT NULL,
  `total_unpaid` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loan_plans`
--

CREATE TABLE `loan_plans` (
  `plan_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `term` bigint(20) NOT NULL,
  `interest` double NOT NULL,
  `penalty` double NOT NULL,
  `payment_frequency` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loan_types`
--

CREATE TABLE `loan_types` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(250) NOT NULL,
  `type_desc` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan_types`
--

INSERT INTO `loan_types` (`type_id`, `type_name`, `type_desc`) VALUES
(1, 'Personal Loans', 'Personal loans can typically be used for anything, unlike vehicle and mortgage loans, which have specific uses in mind. For example, some people utilize them for home renovation projects, weddings, or unexpected bills. Most personal loans are unsecured, which means they don\'t call for any kind of security. They could have a few months to several years of repayment time and fixed or variable interest rates.'),
(2, 'Auto Loan', 'An auto loan allows you to borrow the balance of the car\'s purchase price, less any down payment. When the borrower stops paying payments, the vehicle is used as collateral and may be repossessed. Although lengthier loan durations are increasingly prevalent as auto costs rise, auto loan terms typically range from 36 to 72 months.'),
(3, 'Student Loan', 'College and graduate school costs may be covered via student loans. Both the federal government and private lenders have them available. Because they include deferment, forbearance, forgiveness, and income-based repayment choices, federal student loans are more favorable. United States-funded They frequently don\'t require a credit check and are provided by the Department of Education as financial help through schools. Every borrower receiving the same sort of loan will be subject to the same loan terms, which include fees, repayment schedules, and interest rates.\r\n\r\nContrarily, private lenders for student loans typically demand a credit check and set their own loan conditions, interest rates, and fees. These loans lack advantages like loan forgiveness or income-based repayment schedules, in contrast to federal student loans.'),
(4, 'Mortgage Loan', 'The purchase price of a home, less any down payment, is covered by a mortgage loan. If mortgage payments are not made, the lender may foreclose on the property, which serves as security. Typically, mortgages are repaid over 10, 15, 20, or 30 years. Government organizations do not insure conventional mortgages. Mortgages backed by governmental organizations like the Federal Housing Administration (FHA) or Veterans Administration may be available to some borrowers (VA). Mortgage interest rates can be fixed and remain the same for the duration of the loan or adjustable and subject to annual adjustment by the lender.'),
(5, 'Home Equity Loan', 'You can borrow up to a percentage of the equity in your house with a home equity loan or home equity line of credit (HELOC), which you can use for any reason. Installment loans are those made using your home equity; you receive a lump sum and pay it back over time (often five to thirty years) in consistent monthly installments. Revolving credit is what a HELOC is. Like with a credit card, you can use the credit line whenever you need during a \"draw period\" and only incur interest up until the end of the draw period. Following that, you typically have 20 years to repay the debt. Home equity loans typically have fixed interest rates, whereas HELOCs typically have variable interest rates.'),
(6, 'Credit Builder Loan', 'You can borrow up to a percentage of the equity in your house with a home equity loan or home equity line of credit (HELOC), which you can use for any reason. Installment loans are those made using your home equity; you receive a lump sum and pay it back over time (often five to thirty years) in consistent monthly installments. Revolving credit is what a HELOC is. Like with a credit card, you can use the credit line whenever you need during a \"draw period\" and only incur interest up until the end of the draw period. Following that, you typically have 20 years to repay the debt. Home equity loans typically have fixed interest rates, whereas HELOCs typically have variable interest rates.'),
(7, 'Dept Consolidation Loan', 'A personal loan called a debt consolidation loan is intended to be used to pay off high-interest debt, including credit card debt. If the interest rate on these loans is lower than the interest rate on your current debt, you could save money. Due to the fact that there is only one lender to pay instead of multiple, consolidating debt also makes repayment simpler. Your credit score can increase if you pay off credit card debt with a loan because it lowers your credit use ratio. Loans for debt consolidation might have a variety of payback lengths and fixed or variable interest rates.'),
(8, 'Payday Loan', 'Payday loans are one kind of loan to stay away from. These payday loans are usually subject to fees with annual percentage rates (APRs) of 400% or higher, and they must be fully returned by your next paycheck. These loans, which are available from online and physical payday lenders, typically range in size from $50 to $1,000 and don\'t involve a credit check. Payday loans are simple to obtain but difficult to pay back on time, so consumers renew them, incurring extra penalties and charges and spiraling further into debt. If you need money for an emergency, credit cards or personal loans are preferable possibilities.'),
(9, '323212', '32132132');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` bigint(20) NOT NULL,
  `loaner_id` bigint(20) NOT NULL,
  `loan_id` int(11) NOT NULL,
  `payment_date` date NOT NULL,
  `payment_amount` double NOT NULL,
  `date_pay` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `collateral`
--
ALTER TABLE `collateral`
  ADD PRIMARY KEY (`collateral_id`),
  ADD KEY `loaner_id` (`loaner_id`),
  ADD KEY `loan_id` (`loan_id`),
  ADD KEY `plan_id` (`plan_id`);

--
-- Indexes for table `loaners`
--
ALTER TABLE `loaners`
  ADD PRIMARY KEY (`loaner_id`);

--
-- Indexes for table `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`loan_id`),
  ADD KEY `loaner_id` (`loaner_id`),
  ADD KEY `type_id` (`type_id`),
  ADD KEY `plan_id` (`plan_id`);

--
-- Indexes for table `loan_plans`
--
ALTER TABLE `loan_plans`
  ADD PRIMARY KEY (`plan_id`),
  ADD KEY `type_id` (`type_id`);

--
-- Indexes for table `loan_types`
--
ALTER TABLE `loan_types`
  ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `loaner_id` (`loaner_id`),
  ADD KEY `loan_id` (`loan_id`),
  ADD KEY `payment_amount` (`payment_amount`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `collateral`
--
ALTER TABLE `collateral`
  ADD CONSTRAINT `collateral_ibfk_5` FOREIGN KEY (`loan_id`) REFERENCES `loans` (`loan_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `collateral_ibfk_6` FOREIGN KEY (`loaner_id`) REFERENCES `loans` (`loaner_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `collateral_ibfk_7` FOREIGN KEY (`plan_id`) REFERENCES `loans` (`plan_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`loaner_id`) REFERENCES `loaners` (`loaner_id`),
  ADD CONSTRAINT `loans_ibfk_8` FOREIGN KEY (`type_id`) REFERENCES `loan_plans` (`type_id`),
  ADD CONSTRAINT `loans_ibfk_9` FOREIGN KEY (`plan_id`) REFERENCES `loan_plans` (`plan_id`);

--
-- Constraints for table `loan_plans`
--
ALTER TABLE `loan_plans`
  ADD CONSTRAINT `loan_plans_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `loan_types` (`type_id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_3` FOREIGN KEY (`loan_id`) REFERENCES `loans` (`loan_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `payments_ibfk_4` FOREIGN KEY (`loaner_id`) REFERENCES `loans` (`loaner_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
