CREATE DATABASE  IF NOT EXISTS `qltieccuoi` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `qltieccuoi`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: qltieccuoi
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `booking_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `customter_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `concept_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `booking_date` date DEFAULT NULL,
  `shift` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `fk_customer_idx` (`customter_id`),
  KEY `fk_user_idx` (`user_id`),
  KEY `fk_room_idx` (`room_id`),
  KEY `fk_concept_idx` (`concept_id`),
  CONSTRAINT `fk_concept` FOREIGN KEY (`concept_id`) REFERENCES `concept` (`concept_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer` FOREIGN KEY (`customter_id`) REFERENCES `customer` (`customter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES ('12','KH4','4','US2','2',NULL,'2017-05-11','18'),('242a3c25-7a33-45fb-b0a1-f841676abec2','3a6397e7-d704-43dc-b09f-5155c3412a4d','3','US22','1',NULL,'2017-04-28','18'),('40a89252-c399-42c8-858b-ddb79c17e9d3','13ff4764-ee69-432a-88cc-e61c033d5772','3','US1','1',NULL,'2018-11-18','15'),('589c84f1-58b1-4bc2-ba65-4a01397c17a1','7270b027-093a-449f-adff-2fe9acd3c015','4','US22','2',NULL,'2018-12-17','18'),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','9ee2bea0-fcfe-4902-a9e1-36ebc279d62b','1','US22','1',NULL,'2018-05-12','18'),('94e32d51-4b2a-4dd1-a746-a14965b195f2','77fc477d-a490-43dd-a105-301c66a2674f','4','US22','2',NULL,'2018-06-20','15'),('a8b647be-8b4f-41f8-9779-68d5110926c7','72b35e91-2c97-4dae-9685-83073d7d33a4','1','US22','1',NULL,'2018-07-26','8'),('ad3e4b0c-567b-4903-9d76-ea4f77ab59ea','2e8f4853-e50f-44f0-ac9c-6e1f74aefa16','4','US22','2',NULL,'2018-05-30','15'),('ae5402de-58da-4288-8350-936f511b7fe0','dd92d584-fa3e-4c99-9de7-a84b03184ec7','1','US22','2',NULL,'2018-04-17','11'),('B1','KH1','1','US1','1',NULL,'2018-07-11','8'),('B10','KH2','2','US2','1',NULL,'2017-04-12','11'),('B11','KH3','3','US1','2',NULL,'2018-12-11','15'),('B12','KH1','1','US1','1',NULL,'2016-07-11','8'),('B13','KH2','2','US2','1',NULL,'2016-02-15','11'),('B14','KH3','3','US1','2',NULL,'2016-04-11','15'),('B15','KH2','2','US2','1',NULL,'2016-07-11','11'),('B16','KH3','3','US1','2',NULL,'2014-11-11','15'),('B17','KH4','4','US2','2',NULL,'2015-07-11','18'),('B18','KH1','1','US1','1',NULL,'2014-10-11','8'),('B19','KH2','2','US2','1',NULL,'2017-07-16','11'),('B2','KH2','2','US2','1',NULL,'2017-07-11','11'),('B20','KH3','3','US1','2',NULL,'2018-09-15','15'),('B21','KH4','4','US2','2',NULL,'2017-07-11','18'),('B22','KH1','1','US1','1',NULL,'2018-01-19','8'),('B23','KH3','1','US1','2',NULL,'2018-04-17','8'),('B24','KH4','2','US2','2',NULL,'2018-08-17','18'),('B25','KH1','3','US1','1',NULL,'2018-03-17','8'),('B26','KH2','4','US2','1',NULL,'2018-10-17','11'),('B27','KH3','1','US1','2',NULL,'2018-04-17','15'),('B28','KH4','2','US2','2',NULL,'2018-05-17','15'),('B29','KH1','3','US1','1',NULL,'2018-03-17','18'),('B3','KH3','3','US1','2',NULL,'2018-11-11','15'),('B4','KH4','4','US2','2',NULL,'2017-07-11','18'),('B5','KH1','1','US1','1',NULL,'2018-10-11','8'),('B6','KH2','2','US2','1',NULL,'2017-02-16','11'),('B7','KH3','3','US1','2',NULL,'2018-01-15','15'),('B8','KH4','4','US2','2',NULL,'2017-07-11','18'),('B9','KH1','1','US1','1',NULL,'2018-06-19','8'),('f4ea0fc0-69cf-4f50-b0e8-897ed0f5fd04','00e37aed-ca3a-449b-86b2-75206aa4c4db','2','US1','2',NULL,'2018-04-19','15');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_detail`
--

DROP TABLE IF EXISTS `booking_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_detail` (
  `booking_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `menu_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `discount` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`booking_id`,`menu_id`),
  KEY `fk_food_idx` (`menu_id`),
  CONSTRAINT `fk_booking` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_detail`
--

LOCK TABLES `booking_detail` WRITE;
/*!40000 ALTER TABLE `booking_detail` DISABLE KEYS */;
INSERT INTO `booking_detail` VALUES ('242a3c25-7a33-45fb-b0a1-f841676abec2','4',NULL),('242a3c25-7a33-45fb-b0a1-f841676abec2','5',NULL),('242a3c25-7a33-45fb-b0a1-f841676abec2','6',NULL),('242a3c25-7a33-45fb-b0a1-f841676abec2','7',NULL),('40a89252-c399-42c8-858b-ddb79c17e9d3','1',NULL),('40a89252-c399-42c8-858b-ddb79c17e9d3','13',NULL),('40a89252-c399-42c8-858b-ddb79c17e9d3','2',NULL),('589c84f1-58b1-4bc2-ba65-4a01397c17a1','12',NULL),('589c84f1-58b1-4bc2-ba65-4a01397c17a1','13',NULL),('589c84f1-58b1-4bc2-ba65-4a01397c17a1','2',NULL),('589c84f1-58b1-4bc2-ba65-4a01397c17a1','3',NULL),('589c84f1-58b1-4bc2-ba65-4a01397c17a1','4',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','1',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','10',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','11',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','12',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','14',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','3',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','4',NULL),('753e10a9-06b9-4cae-af49-4cfb47fd0b21','7',NULL),('94e32d51-4b2a-4dd1-a746-a14965b195f2','10',NULL),('94e32d51-4b2a-4dd1-a746-a14965b195f2','12',NULL),('a8b647be-8b4f-41f8-9779-68d5110926c7','1',NULL),('ad3e4b0c-567b-4903-9d76-ea4f77ab59ea','13',NULL),('ad3e4b0c-567b-4903-9d76-ea4f77ab59ea','2',NULL),('ad3e4b0c-567b-4903-9d76-ea4f77ab59ea','3',NULL),('ae5402de-58da-4288-8350-936f511b7fe0','10',NULL),('ae5402de-58da-4288-8350-936f511b7fe0','13',NULL),('ae5402de-58da-4288-8350-936f511b7fe0','4',NULL),('ae5402de-58da-4288-8350-936f511b7fe0','5',NULL),('ae5402de-58da-4288-8350-936f511b7fe0','6',NULL),('B1','1',NULL),('B1','2',NULL),('B1','3',NULL),('B1','4',NULL),('B2','1',NULL),('B3','1',NULL),('B4','1',NULL),('f4ea0fc0-69cf-4f50-b0e8-897ed0f5fd04','1',NULL),('f4ea0fc0-69cf-4f50-b0e8-897ed0f5fd04','13',NULL),('f4ea0fc0-69cf-4f50-b0e8-897ed0f5fd04','2',NULL);
/*!40000 ALTER TABLE `booking_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept`
--

DROP TABLE IF EXISTS `concept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept` (
  `concept_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `color` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `table` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `background` text COLLATE utf8_unicode_ci,
  `folower` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` varchar(13) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`concept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept`
--

LOCK TABLES `concept` WRITE;
/*!40000 ALTER TABLE `concept` DISABLE KEYS */;
INSERT INTO `concept` VALUES ('1','Tông màu đỏ','Khăn trải bàn màu sắc rực rỡ','Sân khấu hiện đại','Vòng hoa hồng lớn','2000000'),('2','Tông màu trắng','Khăn trải bàn khẻ sọc trắng kem','Sân khấu cổ điển','Vòng hoa Lan','1000000');
/*!40000 ALTER TABLE `concept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customter_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` text CHARACTER SET utf8,
  `gender` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`customter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('00e37aed-ca3a-449b-86b2-75206aa4c4db','Nguyễn Văn Hồng','Sơn','0939272333','Đà Lạt','Nam'),('13ff4764-ee69-432a-88cc-e61c033d5772','Danh Ngọc','Oanh','0939727333','12/21 Lý Thường Kiệt','Nam'),('2e8f4853-e50f-44f0-ac9c-6e1f74aefa16','Lê Văn','Thọ','016768612','12/43 lê văn sỹ','Nam'),('3a6397e7-d704-43dc-b09f-5155c3412a4d','Dương Tuấn ','Hiệp','017826781','45/12/2 CMT8','Nam'),('7270b027-093a-449f-adff-2fe9acd3c015','Lê Văn','Long','017878992','123/8 Kỳ Long','Nam'),('72b35e91-2c97-4dae-9685-83073d7d33a4','Lê Thành','Lộc','01676727268','123 Lê Quý Đôn','Nam'),('77fc477d-a490-43dd-a105-301c66a2674f','Nguyễn Thị','Cúc','017892012','12/9 Lê Trung Trực','Nam'),('9ee2bea0-fcfe-4902-a9e1-36ebc279d62b','Lâm Kim ','Tình','016787902','12/9 Lê Thị Chung','Nam'),('dd92d584-fa3e-4c99-9de7-a84b03184ec7','Lê Tấn','Long','0167872123','23/9 Quang Trung','Nam'),('KH1','Nguyễn','Tình',NULL,NULL,NULL),('KH2','Phạm','Tài',NULL,NULL,NULL),('KH3','Nguyễn','Long',NULL,NULL,NULL),('KH4','Quang','Vinh',NULL,NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menu_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `menu_name` text CHARACTER SET utf8,
  `price` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `title_menu` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `image` text COLLATE utf8_unicode_ci,
  `status` bit(1) DEFAULT NULL,
  `is_select` bit(1) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `fk_title_idx` (`title_menu`),
  CONSTRAINT `fk_title` FOREIGN KEY (`title_menu`) REFERENCES `title_menu` (`title_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('1','Súp cua','20000','KV',NULL,'Images/SupCua.jpg','','\0'),('10','Thịt Thỏ','150000','MC',NULL,'Images/tho.jpg','','\0'),('11','nước ngọt','150000','N',NULL,'Images/nuocngot.png','','\0'),('12','Gà quay','200000','MC',NULL,'Images/Gaquay.png','','\0'),('13','xôi gà','50000','KV',NULL,'Images/xoi.jpg','','\0'),('14','Rau câu','30000','TM',NULL,'Images/raucau.png','','\0'),('2','Gỏi lỗ tai bò','100000','KV',NULL,'Images/goi.jpg','',''),('3','Heo Rừng Nướng','300000','MC',NULL,'Images/heonuong.jpg','','\0'),('4','Bò Xào Lăng','300000','MC',NULL,'Images/boxao.jpg','','\0'),('5','Tôm Hấp Bia','300000','MC',NULL,'Images/tom.jpg','','\0'),('6','Lẩu Cá Hồi','300000','MC',NULL,'Images/cahoi.png','','\0'),('7','bia','200000','N',NULL,'Images/bia.png','','\0'),('8','nho mỹ','50000','TM',NULL,'Images/Nho.jpg','','\0'),('9','dưa hấu','20000','TM',NULL,'Images/duahau.jpg','','\0');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `room_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `location` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `price` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `status` bit(1) DEFAULT NULL,
  `quantityTable` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('1','A','Lầu 1','2000000','',NULL,'10'),('2','B','Lầu 2 ','3000000','',NULL,'30'),('3','C','Lầu 3','3000000','',NULL,'20'),('4','D','Sân Thương','5000000','',NULL,'30');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title_menu`
--

DROP TABLE IF EXISTS `title_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `title_menu` (
  `title_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `title_name` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title_menu`
--

LOCK TABLES `title_menu` WRITE;
/*!40000 ALTER TABLE `title_menu` DISABLE KEYS */;
INSERT INTO `title_menu` VALUES ('KV','Món khai vị'),('MC','Món chính'),('N','Nước uống'),('TM','Món tráng miệng');
/*!40000 ALTER TABLE `title_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `role` varchar(15) CHARACTER SET utf8 NOT NULL,
  `active` varchar(15) CHARACTER SET utf8 NOT NULL,
  `address` text COLLATE utf8_unicode_ci,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `image` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('bd2fc887-5a36-470b-bee1-649b3720a1bb','tinhtinh','03fa788970a884b2833fe14a6c4b5776441bc163','Long','Hoàng','Member','Active','','','Male',NULL),('US1','Tình','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Nguyễn','Tình','Admin','Active',NULL,NULL,'Male',NULL),('US10','longlong123','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Hoàng','Hoàng','Member','Disable',NULL,NULL,'Female',NULL),('US11','vinhvinh123','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Vinh','Vinh','Member','Disable',NULL,NULL,'Female',NULL),('US12','taipham','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Phạm','Tài','Admin','Active',NULL,NULL,'Male',NULL),('US13','vinhquang','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Hữu','Tình','Member','Disable',NULL,NULL,'Female',NULL),('US14','long_nguyen','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Hoàng','Long','Member','Disable',NULL,NULL,'Female',NULL),('US16','username2','password','Kim','Toàn','Member','Disable','','','Male',''),('US17','tinh15','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Nguyễn','Tài','Admin','Disable',NULL,NULL,'Female',NULL),('US18','bi_234','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Phạm','Tài','Member','Disable',NULL,NULL,'Female',NULL),('US19','an_123','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Nguyễn','Tình','Member','Disable',NULL,NULL,'Female',NULL),('US2','anchoibibi','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Phạm','Tài','Admin','Active',NULL,NULL,'Male',NULL),('US20','longhoang','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Hoàng','Hoàng','Member','Disable',NULL,NULL,'Female',NULL),('US21','hoanglong','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Vinh','Vinh','Member','Disable',NULL,NULL,'Female',NULL),('US22','Tình','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Nguyễn','Tình','Admin','Active',NULL,NULL,'Male',NULL),('US3','anchoibi','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Hữu','Tình','Member','Disable',NULL,NULL,'Female',NULL),('US4','longlong','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Hoàng','Long','Member','Disable',NULL,NULL,'Female',NULL),('US5','vinhvinh','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Quang','Vinh','Member','Disable',NULL,NULL,'Female',NULL),('US55','vinh_456','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Quang','Vinh','Member','Disable',NULL,NULL,'Female',NULL),('US6','username','password','Kim','Toàn','Admin','Disable','','','Male',''),('US7','tinh123','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Nguyễn','Tài','Admin','Active',NULL,NULL,'Male',NULL),('US8','anchoibibi123','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Phạm','Tài','Admin','Active',NULL,NULL,'Male',NULL),('US9','anchoibi123','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','Nguyễn','Tình','Member','Disable',NULL,NULL,'Female',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-16 21:56:11
