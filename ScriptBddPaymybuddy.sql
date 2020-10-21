
-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: paymybuddyDB
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP DATABASE IF EXISTS `paymybuddyDB`;
CREATE DATABASE `paymybuddyDB` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` bigint NOT NULL,
  `account_balance` double DEFAULT '0',
  `account_email` varchar(50) NOT NULL,
  `account_name` varchar(20) NOT NULL,
  `account_password` varchar(20) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_o72icrlyfe9jcc61hbgsnmkgr` (`account_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `bank_account_id` bigint NOT NULL,
  `bank_account_description` varchar(20) DEFAULT NULL,
  `bank_account_holder_name` varchar(30) NOT NULL,
  `bank_account_iban` varchar(35) NOT NULL,
  `account_holder_id` bigint DEFAULT NULL,
  PRIMARY KEY (`bank_account_id`),
  UNIQUE KEY `UK_t5qbwtxe2cftc53vx37w0qyyy` (`bank_account_iban`),
  KEY `FK93dpqga6qvqlwheq19n1r8oaf` (`account_holder_id`),
  CONSTRAINT `FK93dpqga6qvqlwheq19n1r8oaf` FOREIGN KEY (`account_holder_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendship`
--

DROP TABLE IF EXISTS `friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friendship` (
  `friendship_account_id` bigint NOT NULL,
  `friendship_friend_account_id` bigint NOT NULL,
  PRIMARY KEY (`friendship_account_id`,`friendship_friend_account_id`),
  KEY `FK51pu2x5e8ku1mii6lyl1x7w1c` (`friendship_friend_account_id`),
  CONSTRAINT `FK51pu2x5e8ku1mii6lyl1x7w1c` FOREIGN KEY (`friendship_friend_account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `FKjrcita1x5gjowrojkmop56c95` FOREIGN KEY (`friendship_account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendship`
--

LOCK TABLES `friendship` WRITE;
/*!40000 ALTER TABLE `friendship` DISABLE KEYS */;
/*!40000 ALTER TABLE `friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
  `operation_id` bigint NOT NULL,
  `operation_amount` double NOT NULL,
  `operation_date` datetime NOT NULL,
  `operation_fee` double NOT NULL,
  `operation_tax_id` int NOT NULL,
  PRIMARY KEY (`operation_id`),
  KEY `FKqpp7ujd4qsvrf7vgfsj754ub7` (`operation_tax_id`),
  CONSTRAINT `FKqpp7ujd4qsvrf7vgfsj754ub7` FOREIGN KEY (`operation_tax_id`) REFERENCES `tax` (`tax_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `providing`
--

DROP TABLE IF EXISTS `providing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `providing` (
  `bank_account_id` bigint NOT NULL,
  `account_id` bigint NOT NULL,
  `providing_operation_id` bigint NOT NULL,
  `providing_type` varchar(20) NOT NULL,
  PRIMARY KEY (`bank_account_id`,`account_id`,`providing_operation_id`),
  UNIQUE KEY `UK_vniif4qg7l7eprxuvj2jbb7v` (`providing_operation_id`),
  KEY `FKrtxl9gh9basdkkpviitqyamd7` (`account_id`),
  CONSTRAINT `FKjhclklgtht84vwyhivbdcsh9l` FOREIGN KEY (`providing_operation_id`) REFERENCES `operation` (`operation_id`),
  CONSTRAINT `FKk3tjk50vb0o0q73dnmvk7ow8g` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`bank_account_id`),
  CONSTRAINT `FKrtxl9gh9basdkkpviitqyamd7` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `providing`
--

LOCK TABLES `providing` WRITE;
/*!40000 ALTER TABLE `providing` DISABLE KEYS */;
/*!40000 ALTER TABLE `providing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax` (
  `tax_id` int NOT NULL,
  `tax_description` varchar(25) NOT NULL,
  `tax_rate` double NOT NULL,
  PRIMARY KEY (`tax_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `transfer_account_from` bigint NOT NULL,
  `transfer_account_to` bigint NOT NULL,
  `transfer_operation_id` bigint NOT NULL,
  `transfer_description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`transfer_account_from`,`transfer_account_to`,`transfer_operation_id`),
  UNIQUE KEY `UK_5f22f85uif7tc34he4f806j2` (`transfer_operation_id`),
  KEY `FK19slcr1f490xogv558c3179hl` (`transfer_account_to`),
  CONSTRAINT `FK19slcr1f490xogv558c3179hl` FOREIGN KEY (`transfer_account_to`) REFERENCES `account` (`account_id`),
  CONSTRAINT `FKadin6a0lvqjeoxhdgx306992a` FOREIGN KEY (`transfer_account_from`) REFERENCES `account` (`account_id`),
  CONSTRAINT `FKssertdmh8vykith1863np6hsj` FOREIGN KEY (`transfer_operation_id`) REFERENCES `operation` (`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'paymybuddyDB'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-19 12:45:15
