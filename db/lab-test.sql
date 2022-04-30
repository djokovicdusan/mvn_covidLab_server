/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.27 : Database - lab-test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lab-test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `lab-test`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `korisnickoIme` varchar(255) NOT NULL,
  `lozinka` varchar(255) NOT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `admin` */

insert  into `admin`(`korisnickoIme`,`lozinka`,`ime`,`prezime`) values 
('admin','admin','Dusan','Djokovic');

/*Table structure for table `laborant` */

DROP TABLE IF EXISTS `laborant`;

CREATE TABLE `laborant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `brojOrdinacije` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;

/*Data for the table `laborant` */

insert  into `laborant`(`id`,`ime`,`prezime`,`brojOrdinacije`) values 
(1,'Micko','Ljubicic',1),
(53,'Zdravko','Colic',13);

/*Table structure for table `pacijent` */

DROP TABLE IF EXISTS `pacijent`;

CREATE TABLE `pacijent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `datumRodjenja` date DEFAULT NULL,
  `telefon` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `laborantId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `pacijent_ibfk_3` (`laborantId`),
  CONSTRAINT `pacijent_ibfk_3` FOREIGN KEY (`laborantId`) REFERENCES `laborant` (`id`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;

/*Data for the table `pacijent` */

insert  into `pacijent`(`id`,`ime`,`prezime`,`datumRodjenja`,`telefon`,`email`,`laborantId`) values 
(1,'Stevan','Markovic','1999-06-16','44321','mail@mail.com',1),
(20,'Zdravko','Colic','3900-11-10','123','test',1),
(23,'Zdravko','Colic','3900-11-10','123',NULL,1),
(24,'Zdravko','Colic','3900-11-10','123',NULL,1),
(25,'Zdravko','Colic','3900-11-10','123',NULL,1),
(26,'Zdravko','Colic','3900-11-10','123',NULL,1),
(27,'Zdravko','Colic','3900-11-10','123',NULL,1),
(28,'Zdravko','Colic','3900-11-10','123',NULL,1),
(29,'Zdravko','Colic','3900-11-10','123',NULL,1),
(30,'Zdravko','Colic','3900-11-10','123',NULL,1),
(32,'Zdravko','Sotra','1978-11-10',NULL,NULL,1),
(33,'Zdravko','Colic','3900-11-10','123',NULL,1);

/*Table structure for table `rezultat` */

DROP TABLE IF EXISTS `rezultat`;

CREATE TABLE `rezultat` (
  `testId` int NOT NULL,
  `terminTestiranjaId` int NOT NULL,
  `vrednost` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`testId`,`terminTestiranjaId`),
  KEY `rezultat_ibfk_2` (`terminTestiranjaId`),
  CONSTRAINT `rezultat_ibfk_2` FOREIGN KEY (`terminTestiranjaId`) REFERENCES `termintestiranja` (`id`),
  CONSTRAINT `rezultat_ibfk_3` FOREIGN KEY (`testId`) REFERENCES `test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `rezultat` */

insert  into `rezultat`(`testId`,`terminTestiranjaId`,`vrednost`) values 
(1,1,0),
(1,17,0),
(1,19,0),
(1,21,0),
(1,23,0),
(1,25,0),
(1,27,0),
(1,29,0),
(1,31,0);

/*Table structure for table `termintestiranja` */

DROP TABLE IF EXISTS `termintestiranja`;

CREATE TABLE `termintestiranja` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `napomena` varchar(255) DEFAULT NULL,
  `pacijentId` int DEFAULT NULL,
  `laborantId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pacijentId` (`pacijentId`),
  KEY `laborantId` (`laborantId`),
  CONSTRAINT `termintestiranja_ibfk_1` FOREIGN KEY (`pacijentId`) REFERENCES `pacijent` (`id`) ON UPDATE RESTRICT,
  CONSTRAINT `termintestiranja_ibfk_2` FOREIGN KEY (`laborantId`) REFERENCES `laborant` (`id`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;

/*Data for the table `termintestiranja` */

insert  into `termintestiranja`(`id`,`datum`,`napomena`,`pacijentId`,`laborantId`) values 
(1,'2022-04-05','napomena',NULL,NULL),
(14,'3899-12-01','test',1,1),
(15,'3899-12-01','test',1,1),
(16,'3899-12-01','test',1,1),
(17,'3899-12-01','test',1,1),
(18,'3899-12-01','test',1,1),
(19,'3899-12-01','test',1,1),
(20,'3899-12-01','test',1,1),
(21,'3899-12-01','test',1,1),
(22,'3899-12-01','test',1,1),
(23,'3899-12-01','test',1,1),
(24,'3899-12-01','test',1,1),
(25,'3899-12-01','test',1,1),
(26,'3899-12-01','test',1,1),
(27,'3899-12-01','test',1,1),
(28,'3899-12-01','test',1,1),
(29,'3899-12-01','test',1,1),
(30,'3899-12-01','test',1,1),
(31,'3899-12-01','test',1,1),
(32,'3899-12-01','test',1,1);

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int NOT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  `vrsta` varchar(255) DEFAULT NULL,
  `uputstvoZaUpotrebu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `test` */

insert  into `test`(`id`,`naziv`,`opis`,`vrsta`,`uputstvoZaUpotrebu`) values 
(1,'test','opis','vrsta','uputsvo');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
