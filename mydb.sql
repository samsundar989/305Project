-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Customer` (
  `CustomerID` int(11) NOT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (111111),(111222),(111333),(111444),(111555);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databaseuser`
--

DROP TABLE IF EXISTS `databaseuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `databaseuser` (
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `View` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databaseuser`
--

LOCK TABLES `databaseuser` WRITE;
/*!40000 ALTER TABLE `databaseuser` DISABLE KEYS */;
INSERT INTO `databaseuser` VALUES ('111111','123','customer'),('111222','123','customer'),('111333','123','customer'),('111444','123','customer'),('111555','123','customer'),('222111','123','seller'),('222222','123','seller'),('222333','123','seller'),('222444','123','seller'),('222555','123','seller'),('admin','123','admin');
/*!40000 ALTER TABLE `databaseuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Item` (
  `ISBN` int(11) NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `SellerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `sellerID_idx` (`SellerID`),
  CONSTRAINT `sellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (444000,'Poetry','9',222555),(444111,'Fiction','4',222111),(444222,'Action','5',222222),(444333,'Poetry','10',222333),(444444,'Children\'s','12',222444),(444555,'Horror','8',222555),(444666,'Science','16',222111),(444777,'Young Adult','11',222222),(444888,'Action','7',222333),(444999,'Fiction','5',222444);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Orders` (
  `CustomerID` int(11) DEFAULT NULL,
  `SellerID` int(11) DEFAULT NULL,
  `ConfirmationNum` int(11) NOT NULL,
  PRIMARY KEY (`ConfirmationNum`),
  KEY `orders_CustomerID_idx` (`CustomerID`),
  KEY `orders_SellerID_idx` (`SellerID`),
  CONSTRAINT `orders_CustomerID` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `orders_SellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (111111,222333,1),(111222,222444,2),(111111,222111,3),(111333,222444,4),(111444,222333,5);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `review` (
  `ISBN` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `SellerID` int(11) NOT NULL,
  `Text` varchar(45) DEFAULT NULL,
  `Rating` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ISBN`,`CustomerID`,`SellerID`),
  KEY `review_SellerID_idx` (`SellerID`),
  KEY `review_cusotmerID_idx` (`CustomerID`),
  CONSTRAINT `review_SellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`),
  CONSTRAINT `review_cusotmerID` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (444000,111111,222555,'Average book.','3'),(444000,111222,222555,'Poor writing.','1'),(444111,111333,222111,'Great quality writing.','5'),(444222,111444,222222,'Generic plot.','3'),(444333,111333,222333,'Beautiful poetry.','4'),(444444,111444,222444,'Below average illustration.','2'),(444555,111222,222555,'Not as scary as I would\'ve liked.','1'),(444666,111555,222111,'Solid information, hard read.','3'),(444777,111111,222222,'Good book.','2'),(444888,111222,222333,'Ok.','3'),(444999,111333,222444,'Beautifully written.','5');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Seller`
--

DROP TABLE IF EXISTS `Seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Seller` (
  `SellerID` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Rating` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seller`
--

LOCK TABLES `Seller` WRITE;
/*!40000 ALTER TABLE `Seller` DISABLE KEYS */;
INSERT INTO `Seller` VALUES (222111,'','1'),(222222,'','4'),(222333,'','5'),(222444,'','2'),(222555,'','3');
/*!40000 ALTER TABLE `Seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Shipment`
--

DROP TABLE IF EXISTS `Shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Shipment` (
  `TrackingNumber` int(11) NOT NULL,
  `DeliveryAddress` varchar(45) DEFAULT NULL,
  `TypeOfShipment` varchar(45) DEFAULT NULL,
  `ShipmentCharge` int(11) DEFAULT NULL,
  `SellerID` int(11) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TrackingNumber`),
  KEY `seller_idx` (`SellerID`),
  KEY `customer_idx` (`CustomerID`),
  CONSTRAINT `shipmentCustomer` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `shipmentSeller` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Shipment`
--

LOCK TABLES `Shipment` WRITE;
/*!40000 ALTER TABLE `Shipment` DISABLE KEYS */;
INSERT INTO `Shipment` VALUES (1,'129 Moison Rd','Express',32,222333,111222),(2,'246 Main St','Standard',45,222111,111333),(3,'300 Circle Rd','2-Day',14,222222,111444),(4,'52 Town Rd','Standard',20,222444,111111);
/*!40000 ALTER TABLE `Shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShoppingCart`
--

DROP TABLE IF EXISTS `ShoppingCart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ShoppingCart` (
  `ISBN` int(11) NOT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `CustomerID` int(11) NOT NULL,
  `SellerID` int(11) NOT NULL,
  PRIMARY KEY (`SellerID`,`CustomerID`,`ISBN`),
  KEY `CustomerID_idx` (`CustomerID`),
  KEY `shoppingcart_SellerID_idx` (`SellerID`),
  KEY `ISBN` (`ISBN`),
  CONSTRAINT `shoppingcart_customerID` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `shoppingcart_sellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShoppingCart`
--

LOCK TABLES `ShoppingCart` WRITE;
/*!40000 ALTER TABLE `ShoppingCart` DISABLE KEYS */;
INSERT INTO `ShoppingCart` VALUES (23142,'4',111111,111222),(65243,'2',111111,111333),(444111,'4',111222,222111),(444000,'9',111222,222555);
/*!40000 ALTER TABLE `ShoppingCart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-09 18:48:50
