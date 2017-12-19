-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for stickylist
DROP DATABASE IF EXISTS `stickylist`;
CREATE DATABASE IF NOT EXISTS `stickylist` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `stickylist`;

-- Dumping structure for table stickylist.activity
DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `x` int(11) NOT NULL DEFAULT '0',
  `y` int(11) NOT NULL DEFAULT '0',
  `list_id` int(11) NOT NULL DEFAULT '0',
  `index` int(11) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table stickylist.activity: ~10 rows (approximately)
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` (`id`, `x`, `y`, `list_id`, `index`, `title`, `description`, `created_at`) VALUES
	(1, 10, 60, 1, NULL, '', NULL, '2017-12-18 20:57:10'),
	(2, 10, 85, 1, NULL, '', NULL, '2017-12-18 20:57:10'),
	(3, 10, 60, 2, NULL, '', NULL, '2017-12-18 20:57:11'),
	(4, 10, 60, 1, NULL, '', NULL, '2017-12-18 20:58:31'),
	(5, 10, 85, 1, NULL, '', NULL, '2017-12-18 20:58:31'),
	(6, 10, 110, 1, NULL, '', NULL, '2017-12-18 20:58:31'),
	(7, 10, 60, 1, NULL, '', NULL, '2017-12-18 21:00:03'),
	(8, 10, 85, 1, NULL, '', NULL, '2017-12-18 21:00:04'),
	(9, 10, 60, 1, NULL, '', NULL, '2017-12-18 21:04:12'),
	(10, 10, 60, 1, NULL, '', NULL, '2017-12-18 21:05:55');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;

-- Dumping structure for table stickylist.list
DROP TABLE IF EXISTS `list`;
CREATE TABLE IF NOT EXISTS `list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `color` varchar(20) DEFAULT NULL,
  `x` varchar(10) NOT NULL,
  `y` varchar(10) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table stickylist.list: ~22 rows (approximately)
/*!40000 ALTER TABLE `list` DISABLE KEYS */;
INSERT INTO `list` (`id`, `title`, `color`, `x`, `y`, `created_at`) VALUES
	(1, 'test', 'default', '30', '64', '2017-12-19 10:11:11'),
	(2, 'dua', 'default', '250', '64', '2017-12-19 10:11:11');
/*!40000 ALTER TABLE `list` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
