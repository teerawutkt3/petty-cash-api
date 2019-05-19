-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.35-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for pettycash
DROP DATABASE IF EXISTS `pettycash`;
CREATE DATABASE IF NOT EXISTS `pettycash` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pettycash`;

-- Dumping structure for table pettycash.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifier ของตาราง employee',
  `employee_code` varchar(8) DEFAULT NULL COMMENT 'รหัสพนักงาน เช่น DF-021',
  `firshname` varchar(100) DEFAULT NULL COMMENT 'ชื่อของพนักงาน',
  `surname` varchar(100) DEFAULT NULL COMMENT 'นามสกุลของพนักงาน',
  `telephone` varchar(11) DEFAULT NULL COMMENT 'หมายเลขโทรศัพท์',
  `job_level` char(1) DEFAULT NULL COMMENT 'ระดับพนักงาน มีรายละเอียดดังนี้ 1 – Junior, 2 – Senior, 3 – Manager',
  `department` varchar(100) DEFAULT NULL COMMENT 'แผนกของพนักงาน',
  `status` char(1) DEFAULT NULL COMMENT 'สถานะของพนักงาน 1 – ทำงานอยู่, 2 – ลาออกแล้ว',
  `manager_id` char(10) DEFAULT NULL COMMENT 'รหัสพนักงานของผู้จัดการ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table pettycash.employee: ~7 rows (approximately)
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `employee_code`, `firshname`, `surname`, `telephone`, `job_level`, `department`, `status`, `manager_id`) VALUES
	(1, 'emp-0001', 'นิพนธ์', 'บุญมี', '0979856904', '3', 'ผู้จัดการ ', '1', NULL),
	(2, 'emp-0002', 'ธีรวุฒิ', 'กุลฤทธิชัย', '0984751365', '1', 'พนักงานทั่วไป ', '1', '1'),
	(3, 'emp-0003', 'ชาญชัย', 'แก้วคำ', '0877458136', '2', 'พนักงานทั่วไป ', '1', '1'),
	(4, 'emp-0004', 'กรรณิกา', 'ยศยิ่ง', '0874565451', NULL, 'ฝ่ายการเงิน ', '1', NULL),
	(5, 'emp-0005', 'เกรียงไกร', 'สารยศ', '0989465515', '2', 'พนักงานทั่วไป ', '2', '1'),
	(6, 'emp-0006', 'ธนพล', 'แสงจันทร์', '0949494464', '3', 'ผู้จัดการ ', '1', NULL),
	(7, 'emp-0007', 'จันทร์เพ็ญ', 'สว่าง', '0664515154', '1', 'พนักงานทั่วไป ', '1', '6');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table pettycash.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pettycash.hibernate_sequence: 1 rows
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table pettycash.param_group
DROP TABLE IF EXISTS `param_group`;
CREATE TABLE IF NOT EXISTS `param_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `value` varchar(2) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table pettycash.param_group: ~10 rows (approximately)
DELETE FROM `param_group`;
/*!40000 ALTER TABLE `param_group` DISABLE KEYS */;
INSERT INTO `param_group` (`id`, `type`, `value`, `description`) VALUES
	(1, 'job_level', '1', 'Junior'),
	(2, 'job_level', '2', 'Senior'),
	(3, 'job_level', '3', 'Manager'),
	(4, 'petty_cash_status', '1', 'ขออนุมัติ'),
	(5, 'petty_cash_status', '2', 'อนุมัติแล้ว'),
	(6, 'petty_cash_status', '3', 'ไม่อนุมัติ'),
	(7, 'petty_cash_status', '4', 'ยกเลิก'),
	(8, 'petty_cash_status', '5', 'สำเร็จแล้ว'),
	(9, 'employee_status', '1', 'ทำงานอยู่'),
	(10, 'employee_status', '2', 'ลาออกแล้ว');
/*!40000 ALTER TABLE `param_group` ENABLE KEYS */;

-- Dumping structure for table pettycash.petty_cash
DROP TABLE IF EXISTS `petty_cash`;
CREATE TABLE IF NOT EXISTS `petty_cash` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifier ของตาราง Petty Cash',
  `code` varchar(12) DEFAULT NULL COMMENT 'รหัสสำหรับอ้างอิงการเบิกจ่าย เช่น PC-2018-0001',
  `emp_id` int(11) DEFAULT NULL COMMENT 'รหัส identifier อ้างอิงไปยังรหัสพนักงานที่ขอเบิก',
  `description` text COMMENT 'ข้อความรายละเอียดการเบิกจ่าย',
  `amount` decimal(10,2) DEFAULT NULL COMMENT 'จำนวนเงินที่ขอเบิกจ่ายสามารถบันทึกเป็นหน่วยสตางค์ได้',
  `status` char(1) DEFAULT NULL COMMENT 'สถานะของการเบิกจ่าย มี 5 สถานะคือ 1 – ขออนุมัติ, 2 – อนุมัติแล้ว, 3 – ไม่อนุมัติ, 4 – ยกเลิก, 5 – สำเร็จแล้ว ',
  `craete_datetime` datetime DEFAULT NULL COMMENT 'timestamp ขณะสร้าง record',
  `update_datetime` datetime DEFAULT NULL COMMENT 'timestamp ขณะแก้ไข record',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- Dumping data for table pettycash.petty_cash: ~7 rows (approximately)
DELETE FROM `petty_cash`;
/*!40000 ALTER TABLE `petty_cash` DISABLE KEYS */;
INSERT INTO `petty_cash` (`id`, `code`, `emp_id`, `description`, `amount`, `status`, `craete_datetime`, `update_datetime`) VALUES
	(28, 'PC-2019-0000', 2, 'ค่าอาหารกลางวัน', 400.00, '3', '2019-05-18 18:59:15', '2019-05-19 15:18:50'),
	(29, 'PC-2019-0001', 2, 'ค่าที่พัก', 4000.00, '4', '2019-05-18 18:59:33', '2019-05-19 15:27:32'),
	(30, 'PC-2019-0005', 3, 'ค่าที่พัก2', 4000.00, '2', '2019-05-18 19:31:23', '2019-05-19 15:18:46'),
	(31, 'PC-2019-0017', 3, 'อาหารเช้า', 150.00, '2', '2019-05-19 07:09:59', '2019-05-19 15:18:45'),
	(32, 'PC-2019-0017', 2, 'อาหารเช้า1', 150.00, '2', '2019-05-19 09:10:54', '2019-05-19 15:18:44'),
	(33, 'PC-2019-0017', 7, 'อาหารเช้า', 1500.00, '5', '2019-05-19 09:11:08', '2019-05-19 14:57:46'),
	(34, 'PC-2019-0018', 7, 'ค่าอาหารกลางวัน', 40.00, '1', '2019-05-19 09:19:40', NULL);
/*!40000 ALTER TABLE `petty_cash` ENABLE KEYS */;

-- Dumping structure for table pettycash.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `description` varchar(150) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pettycash.role: ~3 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role_name`, `description`) VALUES
	(3, 'ROLE_NORMAL', 'ROLE_NORMAL'),
	(4, 'ROLE_MANAMENT', 'ROLE_MANAMENT'),
	(5, 'ROLE_PETTY_CASH', 'ROLE_PETTY_CASH');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table pettycash.role_assignment
DROP TABLE IF EXISTS `role_assignment`;
CREATE TABLE IF NOT EXISTS `role_assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pettycash.role_assignment: ~7 rows (approximately)
DELETE FROM `role_assignment`;
/*!40000 ALTER TABLE `role_assignment` DISABLE KEYS */;
INSERT INTO `role_assignment` (`id`, `role_id`, `user_id`) VALUES
	(2, 4, 2),
	(3, 3, 4),
	(4, 5, 3),
	(5, 3, 5),
	(6, 3, 8),
	(7, 3, 10),
	(8, 4, 9);
/*!40000 ALTER TABLE `role_assignment` ENABLE KEYS */;

-- Dumping structure for table pettycash.running_number
DROP TABLE IF EXISTS `running_number`;
CREATE TABLE IF NOT EXISTS `running_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(4) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Dumping data for table pettycash.running_number: ~1 rows (approximately)
DELETE FROM `running_number`;
/*!40000 ALTER TABLE `running_number` DISABLE KEYS */;
INSERT INTO `running_number` (`id`, `number`, `year`) VALUES
	(27, '0023', '2019');
/*!40000 ALTER TABLE `running_number` ENABLE KEYS */;

-- Dumping structure for table pettycash.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table pettycash.user: ~7 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `employee_id`) VALUES
	(2, 'manager1', '$2a$10$04w3vX6O.LEZzNwHYYZefONopYeL2TgGj30QikHGKrkoeHQ.Wrtga', 1),
	(3, 'finance', '$2a$10$04w3vX6O.LEZzNwHYYZefONopYeL2TgGj30QikHGKrkoeHQ.Wrtga', 4),
	(4, 'normal1', '$2a$10$04w3vX6O.LEZzNwHYYZefONopYeL2TgGj30QikHGKrkoeHQ.Wrtga', 2),
	(5, 'normal2', '$2a$10$04w3vX6O.LEZzNwHYYZefONopYeL2TgGj30QikHGKrkoeHQ.Wrtga', 3),
	(8, 'normal3', '$2a$10$04w3vX6O.LEZzNwHYYZefONopYeL2TgGj30QikHGKrkoeHQ.Wrtga', 5),
	(9, 'manager2', '$2a$10$04w3vX6O.LEZzNwHYYZefONopYeL2TgGj30QikHGKrkoeHQ.Wrtga', 6),
	(10, 'normal4', '$2a$10$04w3vX6O.LEZzNwHYYZefONopYeL2TgGj30QikHGKrkoeHQ.Wrtga', 7);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
