CREATE DATABASE  IF NOT EXISTS `ProyectoFinal` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ProyectoFinal`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: ProyectoFinal
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.17.10.1

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (90),(90);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'lider','ROLE_LIDER'),(2,'desarrollador','ROLE_DESARROLLADOR');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `creation_date` varchar(255) DEFAULT NULL,
  `estimated_time` double NOT NULL,
  `modificaction_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `task_list_id` int(11) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FKnoop17o52c1kg73bskj5o4rdx` (`task_list_id`),
  CONSTRAINT `FKnoop17o52c1kg73bskj5o4rdx` FOREIGN KEY (`task_list_id`) REFERENCES `task_list` (`task_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (11,'2018-11-13 14:14:30',50,'2019-02-03 19:09:06','TT','High',25),(12,'2018-11-13 14:14:52',50,'2019-02-07 11:45:51','EE','High',30),(14,'2018-11-13 14:15:05',50,'2019-02-07 15:38:26','PPPPP','High',10),(18,'2018-11-13 14:41:33',50,'2019-02-07 15:48:34','carlos IV','Low',10),(19,'2018-11-13 14:41:40',50,'2019-02-07 16:07:56','carlos2','Low',10),(20,'2018-11-13 14:41:49',50,'2019-02-07 16:08:57','lio','High',10),(21,'2018-11-13 16:30:51',50,'2019-02-07 17:03:20','Test Task','Medium',10),(22,'2018-11-13 16:30:57',50,'2019-02-07 16:10:59','Test Task','Medium',2),(23,'2018-11-13 16:32:33',50,'2018-11-13 16:32:33','Test Task','Medium',1),(24,'2018-11-13 16:44:49',50,'2018-11-13 16:44:49','Test Task','Medium',1),(26,'2018-11-13 16:46:29',50,'2018-11-13 16:46:29','Test Task','Medium',1),(27,'2018-11-13 16:47:12',50,'2018-11-13 16:47:12','latarea','High',1),(28,'2018-11-13 16:47:51',50,'2018-11-13 16:47:51','Test Task','Medium',1),(29,'2018-11-13 17:05:11',50,'2019-02-03 19:11:49','Test Task','Medium',25),(31,'2018-11-13 17:07:37',50,'2019-02-07 11:41:32','TestTask','High',25),(33,'2018-11-13 17:19:55',50,'2018-11-13 17:19:55','Test Task','Medium',1),(36,'2018-12-15 18:05:40',50,'2018-12-15 18:05:40','TestTask2','High',1),(37,'2018-12-15 18:05:46',50,'2018-12-15 18:05:46','TestTask2','High',1),(38,'2018-12-15 18:09:29',50,'2018-12-15 18:09:29','Test task','Medium',1),(39,'2018-12-15 18:11:00',55,'2018-12-15 18:11:00','Test task','Medium',1),(40,'2018-12-15 18:13:36',55,'2018-12-15 18:13:36','Test','Medium',1),(41,'2018-12-16 12:36:52',25,'2018-12-16 12:36:52','Testeando','High',1),(43,'2018-12-16 12:47:33',50,'2018-12-16 12:47:33','TestTask2','High',1),(44,'2018-12-16 13:25:22',30,'2018-12-16 13:25:22','ingweb3','Low',1),(50,'2019-02-03 20:11:17',20,'2019-02-03 20:11:17','Probando atr','Medium',1),(56,'2019-02-05 19:09:23',21,'2019-02-05 19:09:23','ahora si','Low',1),(57,'2019-02-05 19:15:52',34,'2019-02-05 19:15:52','prueba final','Low',1),(60,'2019-02-07 01:17:46',50,'2019-02-07 01:17:46','prueba de backlog nuevo','Medium',58),(62,'2019-02-07 11:03:09',0,'2019-02-07 11:03:09','prueba pasar sin tiempo estimado','Medium',1),(71,'2019-02-07 13:04:59',0,'2019-02-07 13:04:59','prueba134','Medium',1),(72,'2019-02-07 13:05:21',25,'2019-02-07 13:05:21','prueba sprint2','Medium',59),(73,'2019-02-07 13:10:25',123,'2019-02-07 13:10:25','pruena prueba','Medium',1),(74,'2019-02-07 15:38:50',0,'2019-02-07 15:38:50','prueba tttt','Low',1),(75,'2019-02-07 15:39:21',0,'2019-02-07 15:39:21','pasar a sprint','Low',58),(77,'2019-02-07 16:08:26',0,'2019-02-07 16:08:26','probandoooo','Low',1),(78,'2019-02-07 16:08:36',0,'2019-02-07 16:08:36','probandoooo','Low',1),(79,'2019-02-07 16:09:08',0,'2019-02-07 16:09:08','prueba de ssss','Low',1),(80,'2019-02-07 16:10:52',0,'2019-02-07 16:10:52','asdas','Low',1),(87,'2019-02-07 17:08:38',11234,'2019-02-07 17:08:44','test3','Medium',83);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_list`
--

DROP TABLE IF EXISTS `task_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_list` (
  `task_list_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sprint_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_list`
--

LOCK TABLES `task_list` WRITE;
/*!40000 ALTER TABLE `task_list` DISABLE KEYS */;
INSERT INTO `task_list` VALUES (1,'Backlog','Sprint1'),(2,'TODO','Sprint1'),(10,'In Progress','Sprint1'),(25,'Done','Sprint1'),(30,'Waiting','Sprint1'),(45,'Waiting','Sprint2'),(46,'TODO','Sprint3'),(58,'Backlog','SprintDePrueba'),(59,'Backlog','Sprint2'),(63,'TODO','SprintDePrueba'),(64,'Waiting','SprintDePrueba'),(65,'In Progress','SprintDePrueba'),(66,'Done','SprintDePrueba'),(82,'Backlog','SprintFinal'),(83,'TODO','SprintFinal'),(84,'Done','SprintFinal'),(85,'In Progress','SprintFinal'),(86,'Waiting','SprintFinal'),(88,'Backlog','test'),(89,'TODO','test'),(100,'Backlog','Backlog');
/*!40000 ALTER TABLE `task_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userroles`
--

DROP TABLE IF EXISTS `userroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userroles` (
  `id_user` bigint(20) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `FKgfipxowvmyms6t9cl1k0x357d` (`id_role`),
  CONSTRAINT `FKf1nhsbw21gsbf6qo3juo6nmke` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  CONSTRAINT `FKgfipxowvmyms6t9cl1k0x357d` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userroles`
--

LOCK TABLES `userroles` WRITE;
/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` VALUES (2,1),(1,2);
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_user` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','','','desarrollo@desarrollo.com','','desarrollo','$2a$10$xguHxVXLe.Lasbdl2uKdIuNfFHxPvK8xnO1NKOmZOLBKjMLUHYJ06','desarrollo'),(2,'','','','admin@admin.com','','admin','$2a$10$xguHxVXLe.Lasbdl2uKdIuNfFHxPvK8xnO1NKOmZOLBKjMLUHYJ06','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-07 17:19:55
