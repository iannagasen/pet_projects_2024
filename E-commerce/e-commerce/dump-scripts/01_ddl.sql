-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `image_data`
--

DROP TABLE IF EXISTS `image_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_data` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `image_data` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_data`
--


--
-- Table structure for table `image_data_seq`
--

DROP TABLE IF EXISTS `image_data_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_data_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_data_seq`
--

INSERT INTO `image_data_seq` VALUES (1);

--
-- Table structure for table `image_paths`
--

DROP TABLE IF EXISTS `image_paths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_paths` (
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_paths`
--

INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.559761',1,'2024-04-09 20:24:55.560768',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.568137',2,'2024-04-09 20:24:55.568137',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.574327',3,'2024-04-09 20:24:55.574327',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.635723',4,'2024-04-09 20:24:55.635723',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.641153',5,'2024-04-09 20:24:55.641153',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.645697',6,'2024-04-09 20:24:55.646205',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.681051',7,'2024-04-09 20:24:55.681051',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.686625',8,'2024-04-09 20:24:55.686625',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.691565',9,'2024-04-09 20:24:55.691565',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.715848',10,'2024-04-09 20:24:55.715848',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.719815',11,'2024-04-09 20:24:55.719815',NULL,'somePath',NULL);
INSERT INTO `image_paths` VALUES ('2024-04-09 20:24:55.725057',12,'2024-04-09 20:24:55.725057',NULL,'somePath',NULL);

--
-- Table structure for table `image_paths_seq`
--

DROP TABLE IF EXISTS `image_paths_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_paths_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_paths_seq`
--

INSERT INTO `image_paths_seq` VALUES (101);

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

INSERT INTO `product` VALUES ('2024-04-09 20:24:55.544421',1,'2024-04-09 20:24:55.544421',NULL,'description of Product1','Product1',NULL);
INSERT INTO `product` VALUES ('2024-04-09 20:24:55.564756',2,'2024-04-09 20:24:55.564756',NULL,'description of Product2','Product2',NULL);
INSERT INTO `product` VALUES ('2024-04-09 20:24:55.570737',3,'2024-04-09 20:24:55.571047',NULL,'description of Product3','Product3',NULL);

--
-- Table structure for table `product_image_paths`
--

DROP TABLE IF EXISTS `product_image_paths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image_paths` (
  `image_paths_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  UNIQUE KEY `UK_gb376s1v1kdck5834mq32csn4` (`image_paths_id`),
  KEY `FKk5th7jkitvqpj3buceetxuviw` (`product_id`),
  CONSTRAINT `FKhk2w5ykcq6lmwwih2ho050xjn` FOREIGN KEY (`image_paths_id`) REFERENCES `image_paths` (`id`),
  CONSTRAINT `FKk5th7jkitvqpj3buceetxuviw` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image_paths`
--

INSERT INTO `product_image_paths` VALUES (1,1);
INSERT INTO `product_image_paths` VALUES (2,2);
INSERT INTO `product_image_paths` VALUES (3,3);

--
-- Table structure for table `product_reviews`
--

DROP TABLE IF EXISTS `product_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_reviews` (
  `id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_reviews`
--

INSERT INTO `product_reviews` VALUES (1,1);
INSERT INTO `product_reviews` VALUES (2,2);
INSERT INTO `product_reviews` VALUES (3,3);

--
-- Table structure for table `product_reviews_reviews`
--

DROP TABLE IF EXISTS `product_reviews_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_reviews_reviews` (
  `product_reviews_id` bigint NOT NULL,
  `reviews_id` bigint NOT NULL,
  UNIQUE KEY `UK_l9hqbfouy20ff5193fukwub3o` (`reviews_id`),
  KEY `FKhhjb6viljh1def98vciediyvv` (`product_reviews_id`),
  CONSTRAINT `FK5rqahe2dt0ig13cu70uplo6nt` FOREIGN KEY (`reviews_id`) REFERENCES `reviews` (`id`),
  CONSTRAINT `FKhhjb6viljh1def98vciediyvv` FOREIGN KEY (`product_reviews_id`) REFERENCES `product_reviews` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_reviews_reviews`
--

INSERT INTO `product_reviews_reviews` VALUES (1,1);
INSERT INTO `product_reviews_reviews` VALUES (1,2);
INSERT INTO `product_reviews_reviews` VALUES (1,3);
INSERT INTO `product_reviews_reviews` VALUES (2,4);
INSERT INTO `product_reviews_reviews` VALUES (2,5);
INSERT INTO `product_reviews_reviews` VALUES (2,6);
INSERT INTO `product_reviews_reviews` VALUES (3,7);
INSERT INTO `product_reviews_reviews` VALUES (3,8);
INSERT INTO `product_reviews_reviews` VALUES (3,9);

--
-- Table structure for table `product_reviews_seq`
--

DROP TABLE IF EXISTS `product_reviews_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_reviews_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_reviews_seq`
--

INSERT INTO `product_reviews_seq` VALUES (101);

--
-- Table structure for table `product_seq`
--

DROP TABLE IF EXISTS `product_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_seq`
--

INSERT INTO `product_seq` VALUES (101);

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `rate` double NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.632586',1,'2024-04-09 20:24:55.632586',NULL,'description 0','tag 0',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.638505',2,'2024-04-09 20:24:55.639037',NULL,'description 1','tag 1',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.643346',3,'2024-04-09 20:24:55.643346',NULL,'description 2','tag 2',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.678345',4,'2024-04-09 20:24:55.678345',NULL,'description 3','tag 3',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.684625',5,'2024-04-09 20:24:55.684625',NULL,'description 4','tag 4',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.689709',6,'2024-04-09 20:24:55.689709',NULL,'description 5','tag 5',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.713774',7,'2024-04-09 20:24:55.713774',NULL,'description 6','tag 6',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.717946',8,'2024-04-09 20:24:55.717946',NULL,'description 7','tag 7',NULL);
INSERT INTO `reviews` VALUES (4.5,'2024-04-09 20:24:55.722511',9,'2024-04-09 20:24:55.722511',NULL,'description 8','tag 8',NULL);

--
-- Table structure for table `reviews_image_paths`
--

DROP TABLE IF EXISTS `reviews_image_paths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews_image_paths` (
  `image_paths_id` bigint NOT NULL,
  `reviews_id` bigint NOT NULL,
  UNIQUE KEY `UK_d7divpx04ss9odpibnwypf5kp` (`image_paths_id`),
  KEY `FK5bmowjl8mj8noex8wbg8bvev2` (`reviews_id`),
  CONSTRAINT `FK5bmowjl8mj8noex8wbg8bvev2` FOREIGN KEY (`reviews_id`) REFERENCES `reviews` (`id`),
  CONSTRAINT `FKe177q45kwnf7f0afkkhe6r7x6` FOREIGN KEY (`image_paths_id`) REFERENCES `image_paths` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews_image_paths`
--

INSERT INTO `reviews_image_paths` VALUES (4,1);
INSERT INTO `reviews_image_paths` VALUES (5,2);
INSERT INTO `reviews_image_paths` VALUES (6,3);
INSERT INTO `reviews_image_paths` VALUES (7,4);
INSERT INTO `reviews_image_paths` VALUES (8,5);
INSERT INTO `reviews_image_paths` VALUES (9,6);
INSERT INTO `reviews_image_paths` VALUES (10,7);
INSERT INTO `reviews_image_paths` VALUES (11,8);
INSERT INTO `reviews_image_paths` VALUES (12,9);

--
-- Table structure for table `reviews_seq`
--

DROP TABLE IF EXISTS `reviews_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews_seq`
--

INSERT INTO `reviews_seq` VALUES (101);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
