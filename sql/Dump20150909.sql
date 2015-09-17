CREATE database thankyou_db;
USE thankyou_db;


-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: thankyou_db
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Samara-department'),(2,'NY-department'),(3,'Java'),(4,'JavaKey'),(5,'JavaKey');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liketbl`
--

DROP TABLE IF EXISTS `liketbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liketbl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender_id` bigint(20) DEFAULT NULL,
  `reciever_id` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `typeBage` int(11) DEFAULT NULL,
  `isShow` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_27ia34aniesahkqs7m82n4epg` (`sender_id`),
  KEY `FK_j9kqs1ame68i5y10jhkevao0e` (`reciever_id`),
  CONSTRAINT `FK_27ia34aniesahkqs7m82n4epg` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_j9kqs1ame68i5y10jhkevao0e` FOREIGN KEY (`reciever_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liketbl`
--

LOCK TABLES `liketbl` WRITE;
/*!40000 ALTER TABLE `liketbl` DISABLE KEYS */;
INSERT INTO `liketbl` VALUES (1,1,2,'2015-08-28','good man Year!!',1,''),(4,2,1,'2015-08-29','new task is interesting work',6,'\0'),(5,2,1,'2015-09-01','ololo',3,'\0'),(6,2,1,'2015-09-01','bububu',2,'\0'),(7,1,6,'2015-09-05','text text super text YAHOO ',1,''),(8,3,6,'2015-09-05','this is my next coment good.',3,''),(9,2,6,'2015-09-05','HI, I think that I wow when you are busy! What about new story of iland? Maybe we will drink? I think that web-developer is interesting work. This is so think what you neeed, i mean...And this message yes or no i font know/ But simple wotk test for 253',2,''),(10,1,6,'2015-09-06','HI, I thins intr no i font know/ But simple wo',4,''),(11,2,6,'2015-09-06','text ut simple wo',3,''),(13,6,1,'2015-09-07','from me comment o',5,'\0'),(18,6,2,'2015-09-07','mimiki mimichki',2,'\0'),(19,6,5,'2015-09-08','',4,'\0'),(20,6,1,'2015-09-08','text tex text text text my text',6,'\0'),(23,5,6,'2015-09-08','comment for me',2,''),(24,6,5,'2015-09-09','mytext mytext mytext',1,'\0');
/*!40000 ALTER TABLE `liketbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Java developer'),(2,'UI developer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ldap` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `hashPass` varchar(255) DEFAULT NULL,
  `dep_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5yujjrq6qqt5rl7frm2b2sgmr` (`dep_id`),
  KEY `FK_qleu8ddawkdltal07p8e6hgva` (`role_id`),
  CONSTRAINT `FK_5yujjrq6qqt5rl7frm2b2sgmr` FOREIGN KEY (`dep_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FK_qleu8ddawkdltal07p8e6hgva` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'123456qwert','Igor','Ivanov','1@qwe.ru','1234567890',1,1),(2,'ooppeeww@qwert','Petr','Petrov','21@qwe.ru','9484874664646',2,2),(3,'777','Petr','Perviu','1@1.ru','123',1,1),(4,'888','Ivan','Abramov','1@1.com','48690',1,1),(5,'888','Ivan','Borisov','2@2.com','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y',1,1),(6,'888','Konstantin','Cherkasov','kos@cher.com','$2a$10$pohdVp7mjLkda6WqHX6g8uA3FXHtv69YwefJPdMO7ywjaGxtYQbca',1,1);
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

-- Dump completed on 2015-09-09 21:39:24
