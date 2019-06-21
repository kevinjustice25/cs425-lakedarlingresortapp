-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: lakedarlingdb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adresses`
--

DROP TABLE IF EXISTS `adresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `adresses` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `house_number` int(11) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `street_line_two` varchar(255) DEFAULT NULL,
  `zip` int(11) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresses`
--

LOCK TABLES `adresses` WRITE;
/*!40000 ALTER TABLE `adresses` DISABLE KEYS */;
INSERT INTO `adresses` VALUES (1,'Alexandria',2916,'MINNESOTA','Crestwood Dr','',56308),(2,'Fairfield',301,'IA','E Burlington Ave','Apt 3',52556),(3,'Billings',111,'SD','Franklin Ave','',33256),(4,'Des Moines',442,'IA','Prarie St','Apt 12',52333),(5,'Hollywood',887,'CA','Easy St','',90210),(6,'Imagination',78,'RI','Fuzzy Ave','',44356),(7,'Fergus Falls',131,'MN','Happy Blvd','Apt 108',56709),(8,'Gotham City',56,'NY','Kennel Ln','Apt B',77677),(9,'Lincoln',882,'NB','Chamber Ave','',47441);
/*!40000 ALTER TABLE `adresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_roles`
--

DROP TABLE IF EXISTS `customer_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer_roles` (
  `customer_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`role_id`),
  KEY `FKnjtvg9npn1etke1ldeffa0ym6` (`role_id`),
  CONSTRAINT `FKgee5mori29s8ae8hwcl23qxf0` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `FKnjtvg9npn1etke1ldeffa0ym6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_roles`
--

LOCK TABLES `customer_roles` WRITE;
/*!40000 ALTER TABLE `customer_roles` DISABLE KEYS */;
INSERT INTO `customer_roles` VALUES (1,1),(1,2),(2,2),(3,2);
/*!40000 ALTER TABLE `customer_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customers` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `FK3o86hnbd7vr1orjnlkbiqpktt` (`address_address_id`),
  CONSTRAINT `FK3o86hnbd7vr1orjnlkbiqpktt` FOREIGN KEY (`address_address_id`) REFERENCES `adresses` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'2019-05-26','jprettyman@abc.com','Jerry Prettyman','$2a$10$Ea4hWhryLeuKGz4n6UD1M.CWG/PTxlpypg9gHEGUGjTwviZWg9ukK','3207630633',1),(2,'1985-09-24','kevinjustice25@gmail.com','Kevin Justice','$2a$10$wF8i/Hdvq3AZC2OhFFdFmel8SnNMf/zjfIuSWhyyXKgF3Lf2UgHEm','3202190726',2),(3,'1942-04-20','myogi@fun.com','Mr Yogi','$2a$10$vnjY.EPCi.hZj6CTsEQWTuxJp4cQi.fUKspDDDt9h7atH8Ew4x0hW','(641)472-1111',9);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owners`
--

DROP TABLE IF EXISTS `owners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `owners` (
  `owner_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`owner_id`),
  KEY `FKqwl3ynycm857s74en8ok6jhkm` (`address_address_id`),
  CONSTRAINT `FKqwl3ynycm857s74en8ok6jhkm` FOREIGN KEY (`address_address_id`) REFERENCES `adresses` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owners`
--

LOCK TABLES `owners` WRITE;
/*!40000 ALTER TABLE `owners` DISABLE KEYS */;
INSERT INTO `owners` VALUES (1,'pstansky@gmail.com','Pete Stansky','(123)456-7890',3),(2,'BMcStuffins@google.com','Betty McStuffins','(444)425-9988',4),(3,'cdiaz@abc.com','Cameron Diaz','(641)472-9923',5),(4,'bear@yahoo.com','Stuffy Bear','(218)567-3321',6),(5,'tina@xyz.com','Tina Anderson','(942)242-6657',7),(6,'mdog@apple.com','Monty Dog','(226)764-8665',8);
/*!40000 ALTER TABLE `owners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservations` (
  `reservation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `customer_customer_id` bigint(20) DEFAULT NULL,
  `villa_villa_id` bigint(20) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `FK53q5wmkqd7mvo2wvmin6n4ejf` (`customer_customer_id`),
  KEY `FK4i2shkjmy4jlf3xncyo1er5ov` (`villa_villa_id`),
  CONSTRAINT `FK4i2shkjmy4jlf3xncyo1er5ov` FOREIGN KEY (`villa_villa_id`) REFERENCES `villas` (`villa_id`),
  CONSTRAINT `FK53q5wmkqd7mvo2wvmin6n4ejf` FOREIGN KEY (`customer_customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,'2019-06-26','2019-06-19',2,1,2800),(2,'2019-06-22','2019-06-20',2,7,1100),(3,'2019-06-24','2019-06-20',3,4,1600),(4,'2019-07-30','2019-07-21',2,3,4050),(5,'2019-10-13','2019-10-08',2,4,2000),(6,'2019-06-23','2019-06-21',2,5,800);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'CUSTOMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `villas`
--

DROP TABLE IF EXISTS `villas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `villas` (
  `villa_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number_beds` int(11) NOT NULL,
  `price` float NOT NULL,
  `villa_number` int(11) NOT NULL,
  `owner_owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`villa_id`),
  KEY `FKrgq47lh1jv4jq8s71jfahpbu1` (`owner_owner_id`),
  CONSTRAINT `FKrgq47lh1jv4jq8s71jfahpbu1` FOREIGN KEY (`owner_owner_id`) REFERENCES `owners` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `villas`
--

LOCK TABLES `villas` WRITE;
/*!40000 ALTER TABLE `villas` DISABLE KEYS */;
INSERT INTO `villas` VALUES (1,8,400,1,1),(2,10,450,2,2),(3,10,450,3,3),(4,8,400,4,4),(5,8,400,5,5),(6,8,400,6,6),(7,12,550,7,6),(8,8,400,8,3);
/*!40000 ALTER TABLE `villas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-21 16:48:17
