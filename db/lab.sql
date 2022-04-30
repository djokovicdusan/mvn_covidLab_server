/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.27 : Database - lab
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lab` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `lab`;

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
('djokovicdusan','mojasifra','Dušan','Đoković'),
('djokovicveljko','mojasifra','Veljko','Đoković');

/*Table structure for table `laborant` */

DROP TABLE IF EXISTS `laborant`;

CREATE TABLE `laborant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `brojOrdinacije` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

/*Data for the table `laborant` */

insert  into `laborant`(`id`,`ime`,`prezime`,`brojOrdinacije`) values 
(7,'Dusan ','Djokovic',1),
(8,'Magdalena','Savic',2),
(9,'Jovan','Savic',3),
(10,'Milena','Ilic',3),
(11,'Igor','Tisma',4),
(12,'Emilija','Djokovic',2),
(13,'Ena','Markovic',4),
(14,'Una','Kostic',2),
(16,'Andjela','Mircetic',12),
(18,'Petar','Njegos',12);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

/*Data for the table `pacijent` */

insert  into `pacijent`(`id`,`ime`,`prezime`,`datumRodjenja`,`telefon`,`email`,`laborantId`) values 
(1,'Domagoj','Tisma','1999-06-14','+386514874','tisma@gmail.com',9),
(2,'Aca','Zoric','1993-06-02','+38164254961','zorkela@gmail.com',13),
(3,'Andjela','Dragojevic','1994-03-19','+381542114','andja@gmail.com',7),
(4,'Arsen','Dedic','1999-02-14','+387888442','arsen@gmail.com',11),
(5,'Veljko','Kon','2000-12-30','+381622977554','velja17@gmail.com',12),
(6,'Marcus','Hanston','2022-01-01','062297969','marcus@gmail.com',11),
(8,'Bernulioo','Tomson','2001-01-15','+38160254565','bernulio@gmail.com',7),
(9,'Nitchell','Mayhem','1995-01-16','+38163548775','nitchell@gmail.com',7),
(10,'Nemanja','Matic','1920-01-19','09911220','nemanjamatic@gmail.com',14);

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
(1,1,1),
(1,2,0),
(1,3,0),
(2,3,1),
(2,6,1),
(2,8,1),
(2,10,0),
(3,2,1),
(3,10,1),
(3,11,1),
(4,1,1),
(4,2,1),
(4,4,1),
(4,5,0),
(4,8,0),
(4,10,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

/*Data for the table `termintestiranja` */

insert  into `termintestiranja`(`id`,`datum`,`napomena`,`pacijentId`,`laborantId`) values 
(1,'2022-01-13','probni termin',1,7),
(2,'2022-01-11','proba2',5,9),
(3,'2022-01-01','prva proba zakazivanja termina iz aplikacije',6,14),
(4,'2022-01-20','pacijent je retardiran',8,16),
(5,'2022-01-22','Nemanja je oboleo',10,14),
(6,'2022-01-22','pacijent ne govori srpski jezik',8,16),
(7,'2012-12-12','',2,7),
(8,'2022-01-23','tests',2,7),
(9,'2022-01-23','',9,10),
(10,'2022-01-25','maloletni pacijent',1,11),
(11,'2022-01-26','Aca i pEKI',2,18);

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
(1,'Antitela','opis1','vrsta1','u1'),
(2,'Bris','opis2','vrsta2','u2'),
(3,'Seroloski test','opis3','vrsta3','u3'),
(4,'PCR test','opis4','vrsta4','u4');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
