CREATE DATABASE  IF NOT EXISTS `lecture` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lecture`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lecture
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course_mst`
--

DROP TABLE IF EXISTS `course_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_mst` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lecture_id` int NOT NULL,
  `student_id` int NOT NULL,
  `register_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_mst`
--

LOCK TABLES `course_mst` WRITE;
/*!40000 ALTER TABLE `course_mst` DISABLE KEYS */;
INSERT INTO `course_mst` VALUES (1,1,1,'2023-04-01'),(2,2,1,'2023-04-01'),(3,3,1,'2023-04-01'),(4,4,1,'2023-04-01'),(5,1,2,'2023-04-01'),(6,3,2,'2023-04-01'),(7,5,2,'2023-04-01'),(8,7,2,'2023-04-01'),(9,4,3,'2023-04-01'),(10,5,3,'2023-04-01'),(11,6,3,'2023-04-01'),(12,8,3,'2023-04-01'),(13,1,4,'2023-04-01'),(14,5,4,'2023-04-01'),(15,6,4,'2023-04-01'),(16,7,4,'2023-04-01'),(17,8,4,'2023-04-01'),(18,5,5,'2023-04-01'),(19,7,5,'2023-04-01'),(20,8,5,'2023-04-01'),(21,10,5,'2023-04-01'),(22,9,6,'2023-04-01'),(23,10,6,'2023-04-01'),(24,11,6,'2023-04-01'),(25,4,7,'2023-04-01'),(26,5,7,'2023-04-01'),(27,6,7,'2023-04-01'),(28,1,8,'2023-04-01'),(29,5,8,'2023-04-01'),(30,8,8,'2023-04-01'),(31,9,8,'2023-04-01'),(32,10,8,'2023-04-01'),(33,11,8,'2023-04-01');
/*!40000 ALTER TABLE `course_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_mst`
--

DROP TABLE IF EXISTS `lecture_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_mst` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lecture_name` varchar(45) NOT NULL,
  `lecture_price` int NOT NULL,
  `lecturer_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_mst`
--

LOCK TABLES `lecture_mst` WRITE;
/*!40000 ALTER TABLE `lecture_mst` DISABLE KEYS */;
INSERT INTO `lecture_mst` VALUES (1,'스프링 부트 - 핵심 원리와 활용',99000,1),(2,'스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술',99000,1),(3,'실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발',88000,1),(4,'스프링 DB 2편 - 데이터 접근 활용 기술',99000,1),(5,'스프링 DB 1편 - 데이터 접근 핵심 원리',77000,1),(6,'스프링 핵심 원리 - 고급편',121000,1),(7,'토비의 스프링 부트 - 이해와 원리',99000,2),(8,'만들고 비교하며 학습하는 리액트 (React)',55000,3),(9,'[리뉴얼] 타입스크립트 올인원 : Part1.기본문법편',36300,4),(10,'[리뉴얼] React로 NodeBird SNS 만들기',88000,4),(11,'[개정3판] Node.js 교과서 - 기본부터 프로젝트 실습까지',49500,4);
/*!40000 ALTER TABLE `lecture_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer_mst`
--

DROP TABLE IF EXISTS `lecturer_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer_mst` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer_mst`
--

LOCK TABLES `lecturer_mst` WRITE;
/*!40000 ALTER TABLE `lecturer_mst` DISABLE KEYS */;
INSERT INTO `lecturer_mst` VALUES (1,'김영한','2023-01-10'),(2,'토비','2023-02-10'),(3,'김정환','2023-03-10'),(4,'조현영','2023-04-10');
/*!40000 ALTER TABLE `lecturer_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_mst`
--

DROP TABLE IF EXISTS `student_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_mst` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `birth_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_mst`
--

LOCK TABLES `student_mst` WRITE;
/*!40000 ALTER TABLE `student_mst` DISABLE KEYS */;
INSERT INTO `student_mst` VALUES (1,'김종환','2023-04-10'),(2,'고병수','2023-04-10'),(3,'손민재','2023-04-10'),(4,'황창욱','2023-04-10'),(5,'김상현','2023-04-10'),(6,'강대협','2023-04-10'),(7,'손지호','2023-04-10'),(8,'최해혁','2023-04-10');
/*!40000 ALTER TABLE `student_mst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'lecture'
--

--
-- Dumping routines for database 'lecture'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-10 17:48:08
