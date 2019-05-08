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
-- Table structure for table `Card`
--

DROP TABLE IF EXISTS `Card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Card` (
  `NameOnCard` varchar(10) NOT NULL,
  `CardNumber` int(11) DEFAULT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `CVV` int(11) DEFAULT NULL,
  `ExpiryDate` datetime DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`NameOnCard`),
  KEY `customerID_idx` (`CustomerID`),
  CONSTRAINT `card_customerID` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Card`
--

LOCK TABLES `Card` WRITE;
/*!40000 ALTER TABLE `Card` DISABLE KEYS */;
/*!40000 ALTER TABLE `Card` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `Customer` VALUES (444444);
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
INSERT INTO `databaseuser` VALUES ('111222','123','seller'),('111333','123','seller'),('333333','123','seller'),('admin','123','admin'),('customer','123','customer'),('seller3','123','seller');
/*!40000 ALTER TABLE `databaseuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Employee` (
  `EmployeeID` int(11) NOT NULL,
  `Designation/Role` varchar(45) DEFAULT NULL,
  `DateJoined` varchar(45) DEFAULT NULL,
  `SupervisorID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `supervisorID_idx` (`SupervisorID`),
  CONSTRAINT `supervisorID` FOREIGN KEY (`SupervisorID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (111111,NULL,NULL,NULL),(111112,'Enter data','Enter data',111111);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Inventory` (
  `ItemID` int(11) NOT NULL,
  `ItemName` varchar(45) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `SellerID` int(11) NOT NULL,
  PRIMARY KEY (`SellerID`,`ItemID`),
  KEY `itemID_idx` (`ItemID`),
  CONSTRAINT `inventory_sellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`),
  CONSTRAINT `itemID` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ArticleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inventory`
--

LOCK TABLES `Inventory` WRITE;
/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Item` (
  `ArticleID` int(11) NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Price` decimal(10,0) DEFAULT NULL,
  `SellerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ArticleID`),
  KEY `sellerID_idx` (`SellerID`),
  CONSTRAINT `sellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (179462,'periodt',7,111222);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MakesA`
--

DROP TABLE IF EXISTS `MakesA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MakesA` (
  `TotalPrice` decimal(10,0) DEFAULT NULL,
  `ConfirmationNumber` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `SellerID` int(11) NOT NULL,
  PRIMARY KEY (`ConfirmationNumber`,`CustomerID`,`SellerID`),
  KEY `customerID_idx` (`CustomerID`),
  KEY `sellerID_idx` (`SellerID`),
  CONSTRAINT `PaymentConfirmationNumber` FOREIGN KEY (`ConfirmationNumber`) REFERENCES `payment` (`ConfirmationNumber`),
  CONSTRAINT `makesA_customerID` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `makesA_sellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MakesA`
--

LOCK TABLES `MakesA` WRITE;
/*!40000 ALTER TABLE `MakesA` DISABLE KEYS */;
/*!40000 ALTER TABLE `MakesA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Payment` (
  `ConfirmationNumber` int(11) NOT NULL,
  `AmountCharged` int(11) DEFAULT NULL,
  PRIMARY KEY (`ConfirmationNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Person` (
  `PhoneNumber` int(11) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PhoneNumber`,`FirstName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reviews`
--

DROP TABLE IF EXISTS `Reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Reviews` (
  `CustomerID` int(11) NOT NULL,
  `SellerID` int(11) NOT NULL,
  `ArticleID` int(11) NOT NULL,
  `Rating` int(11) DEFAULT NULL,
  `DetailedReview` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`,`SellerID`,`ArticleID`),
  KEY `sellerID_idx` (`SellerID`),
  KEY `articleID_idx` (`ArticleID`),
  CONSTRAINT `reviews_articleID` FOREIGN KEY (`ArticleID`) REFERENCES `item` (`ArticleID`),
  CONSTRAINT `reviews_customerID` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `reviews_sellerID` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reviews`
--

LOCK TABLES `Reviews` WRITE;
/*!40000 ALTER TABLE `Reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reviews` ENABLE KEYS */;
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
INSERT INTO `Seller` VALUES (111111,NULL,NULL),(111222,NULL,NULL);
/*!40000 ALTER TABLE `Seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SendsOut`
--

DROP TABLE IF EXISTS `SendsOut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SendsOut` (
  `ConfirmationNumber` int(11) NOT NULL,
  `SellerID` int(11) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ConfirmationNumber`),
  KEY `customer_idx` (`CustomerID`),
  KEY `seller_idx` (`SellerID`),
  CONSTRAINT `customer` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `seller` FOREIGN KEY (`SellerID`) REFERENCES `seller` (`SellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SendsOut`
--

LOCK TABLES `SendsOut` WRITE;
/*!40000 ALTER TABLE `SendsOut` DISABLE KEYS */;
INSERT INTO `SendsOut` VALUES (90879,111222,444444);
/*!40000 ALTER TABLE `SendsOut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Shipment`
--

DROP TABLE IF EXISTS `Shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Shipment` (
  `TrackingNumber` int(11) NOT NULL,
  `ShipmentDetails` varchar(45) DEFAULT NULL,
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
INSERT INTO `Shipment` VALUES (432142,'hello','129 moison rd N','express',33,111222,444444);
/*!40000 ALTER TABLE `Shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Shipment/Delivery`
--

DROP TABLE IF EXISTS `Shipment/Delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Shipment/Delivery` (
  `TrackingNumber` int(11) NOT NULL,
  `ShipmentDetails` varchar(45) DEFAULT NULL,
  `DeliveryAddress` varchar(45) DEFAULT NULL,
  `TypeOfShipment` varchar(45) DEFAULT NULL,
  `ShipmentCharge` int(11) DEFAULT NULL,
  PRIMARY KEY (`TrackingNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Shipment/Delivery`
--

LOCK TABLES `Shipment/Delivery` WRITE;
/*!40000 ALTER TABLE `Shipment/Delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `Shipment/Delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ShoppingCart`
--

DROP TABLE IF EXISTS `ShoppingCart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ShoppingCart` (
  `Quantity` int(11) DEFAULT NULL,
  `ItemsBought` varchar(45) DEFAULT NULL,
  `PricePerItem` varchar(45) DEFAULT NULL,
  `TotalPrice` int(11) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  KEY `CustomerID_idx` (`customerID`),
  CONSTRAINT `CustomerID` FOREIGN KEY (`customerID`) REFERENCES `customer` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ShoppingCart`
--

LOCK TABLES `ShoppingCart` WRITE;
/*!40000 ALTER TABLE `ShoppingCart` DISABLE KEYS */;
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

-- Dump completed on 2019-05-08 18:40:57
