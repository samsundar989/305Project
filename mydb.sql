-- MySQL Script generated by MySQL Workbench
-- Wed May  1 00:16:49 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Seller`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Seller` (
  `SellerID` INT NOT NULL,
  `Name` VARCHAR(45) NULL,
  `Rating` VARCHAR(45) NULL,
  PRIMARY KEY (`SellerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Item` (
  `ArticleID` INT NOT NULL,
  `Type` VARCHAR(45) NULL,
  `Price` DECIMAL NULL,
  `SellerID` INT NULL,
  PRIMARY KEY (`ArticleID`),
  INDEX `sellerID_idx` (`SellerID` ASC) VISIBLE,
  CONSTRAINT `sellerID`
    FOREIGN KEY (`SellerID`)
    REFERENCES `mydb`.`Seller` (`SellerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Customer` (
  `CustomerID` INT NOT NULL,
  PRIMARY KEY (`CustomerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Payment` (
  `ConfirmationNumber` INT NOT NULL,
  `AmountCharged` INT NULL,
  PRIMARY KEY (`ConfirmationNumber`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shipment/Delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Shipment/Delivery` (
  `TrackingNumber` INT NOT NULL,
  `ShipmentDetails` VARCHAR(45) NULL,
  `DeliveryAddress` VARCHAR(45) NULL,
  `TypeOfShipment` VARCHAR(45) NULL,
  `ShipmentCharge` INT NULL,
  PRIMARY KEY (`TrackingNumber`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Employee` (
  `EmployeeID` INT NOT NULL,
  `Designation/Role` VARCHAR(45) NULL,
  `DateJoined` VARCHAR(45) NULL,
  `SupervisorID` INT NULL,
  PRIMARY KEY (`EmployeeID`),
  INDEX `supervisorID_idx` (`SupervisorID` ASC) VISIBLE,
  CONSTRAINT `supervisorID`
    FOREIGN KEY (`SupervisorID`)
    REFERENCES `mydb`.`Employee` (`EmployeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ShoppingCart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ShoppingCart` (
  `Quantity` INT NULL,
  `ItemsBought` VARCHAR(45) NULL,
  `PricePerItem` VARCHAR(45) NULL,
  `TotalPrice` INT NULL,
  `customerID` INT NULL,
  INDEX `CustomerID_idx` (`customerID` ASC) VISIBLE,
  CONSTRAINT `CustomerID`
    FOREIGN KEY (`customerID`)
    REFERENCES `mydb`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reviews`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Reviews` (
  `CustomerID` INT NOT NULL,
  `SellerID` INT NOT NULL,
  `ArticleID` INT NOT NULL,
  `Rating` INT NULL,
  `DetailedReview` VARCHAR(45) NULL,
  PRIMARY KEY (`CustomerID`, `SellerID`, `ArticleID`),
  INDEX `sellerID_idx` (`SellerID` ASC) VISIBLE,
  INDEX `articleID_idx` (`ArticleID` ASC) VISIBLE,
  CONSTRAINT `reviews_customerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `mydb`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `reviews_sellerID`
    FOREIGN KEY (`SellerID`)
    REFERENCES `mydb`.`Seller` (`SellerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `reviews_articleID`
    FOREIGN KEY (`ArticleID`)
    REFERENCES `mydb`.`Item` (`ArticleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Inventory`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Inventory` (
  `ItemID` INT NOT NULL,
  `ItemName` VARCHAR(45) NULL,
  `Price` INT NULL,
  `Quantity` INT NULL,
  `SellerID` INT NOT NULL,
  PRIMARY KEY (`SellerID`, `ItemID`),
  INDEX `itemID_idx` (`ItemID` ASC) VISIBLE,
  CONSTRAINT `itemID`
    FOREIGN KEY (`itemID`)
    REFERENCES `mydb`.`Item` (`ArticleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `inventory_sellerID`
    FOREIGN KEY (`SellerID`)
    REFERENCES `mydb`.`Seller` (`SellerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Person` (
  `PhoneNumber` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `LastName` VARCHAR(45) NULL,
  PRIMARY KEY (`PhoneNumber`, `FirstName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Card`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Card` (
  `NameOnCard` VARCHAR(10) NOT NULL,
  `CardNumber` INT NULL,
  `Type` VARCHAR(45) NULL,
  `CVV` INT NULL,
  `ExpiryDate` DATETIME NULL,
  `CustomerID` INT NULL,
  PRIMARY KEY (`NameOnCard`),
  INDEX `customerID_idx` (`CustomerID` ASC) VISIBLE,
  CONSTRAINT `card_customerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `mydb`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SendsOut`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SendsOut` (
  `ConfirmationNumber` INT NOT NULL,
  `SellerID` INT NULL,
  `CustomerID` INT NULL,
  PRIMARY KEY (`ConfirmationNumber`),
  INDEX `seller_idx` (`SellerID` ASC) VISIBLE,
  INDEX `customer_idx` (`CustomerID` ASC) VISIBLE,
  CONSTRAINT `seller`
    FOREIGN KEY (`SellerID`)
    REFERENCES `mydb`.`Seller` (`SellerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `mydb`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`MakesA`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`MakesA` (
  `TotalPrice` DECIMAL NULL,
  `ConfirmationNumber` INT NOT NULL,
  `CustomerID` INT NOT NULL,
  `SellerID` INT NOT NULL,
  PRIMARY KEY (`ConfirmationNumber`, `CustomerID`, `SellerID`),
  INDEX `customerID_idx` (`CustomerID` ASC) VISIBLE,
  INDEX `sellerID_idx` (`SellerID` ASC) VISIBLE,
  CONSTRAINT `makesA_customerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `mydb`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `makesA_sellerID`
    FOREIGN KEY (`SellerID`)
    REFERENCES `mydb`.`Seller` (`SellerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `PaymentConfirmationNumber`
    FOREIGN KEY (`ConfirmationNumber`)
    REFERENCES `mydb`.`Payment` (`ConfirmationNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
