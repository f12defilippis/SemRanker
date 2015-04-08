CREATE DATABASE  IF NOT EXISTS `semranker` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `semranker`;
-- MySQL dump 10.13  Distrib 5.5.24, for osx10.5 (i386)
--
-- Host: 127.0.0.1    Database: semranker
-- ------------------------------------------------------
-- Server version	5.5.17

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_domain`
--

DROP TABLE IF EXISTS `account_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_domain` (
  `id` int(11) NOT NULL,
  `account` int(11) NOT NULL,
  `domain` int(11) NOT NULL,
  `account_domain_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_has_domain_domain1_idx` (`domain`),
  KEY `fk_account_has_domain_account1_idx` (`account`),
  CONSTRAINT `fk_account_has_domain_account1` FOREIGN KEY (`account`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_has_domain_domain1` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_domain_competitor`
--

DROP TABLE IF EXISTS `account_domain_competitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_domain_competitor` (
  `id` int(11) NOT NULL,
  `account_domain` int(11) NOT NULL,
  `domain_competitor` int(11) NOT NULL,
  `account_domain_competitor_status` int(11) NOT NULL,
  PRIMARY KEY (`id`,`account_domain_competitor_status`),
  KEY `fk_account_domain_has_domain_domain1_idx` (`domain_competitor`),
  KEY `fk_account_domain_has_domain_account_domain1_idx` (`account_domain`),
  KEY `fk_account_domain_competitor_account_domain_competitor_stat_idx` (`account_domain_competitor_status`),
  CONSTRAINT `fk_account_domain_has_domain_account_domain1` FOREIGN KEY (`account_domain`) REFERENCES `account_domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_domain_has_domain_domain1` FOREIGN KEY (`domain_competitor`) REFERENCES `domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_domain_competitor_account_domain_competitor_status1` FOREIGN KEY (`account_domain_competitor_status`) REFERENCES `account_domain_competitor_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_domain_competitor_status`
--

DROP TABLE IF EXISTS `account_domain_competitor_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_domain_competitor_status` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL COMMENT '1 - Suggested\n2 - Active',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `domain`
--

DROP TABLE IF EXISTS `domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `datecreate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12927 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `geographical_targeting`
--

DROP TABLE IF EXISTS `geographical_targeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `geographical_targeting` (
  `id` int(11) NOT NULL,
  `name` varchar(125) NOT NULL,
  `canonical_name` varchar(200) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `country_code` varchar(10) NOT NULL,
  `target_type` varchar(50) NOT NULL,
  `uule` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(400) NOT NULL,
  `datecreate` datetime DEFAULT NULL,
  `wordcount` int(11) DEFAULT NULL,
  `lastscan` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23736 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword_history_data`
--

DROP TABLE IF EXISTS `keyword_history_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_history_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` int(11) NOT NULL,
  `period` int(11) NOT NULL,
  `searchengine_country` int(11) NOT NULL,
  `avgmonthlysearches` int(11) NOT NULL,
  `competition` double DEFAULT NULL,
  `suggestedbid` double DEFAULT NULL,
  `scandate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `KW_Keyworddata_FK_idx` (`keyword`),
  KEY `period_keyworddata_fk_idx` (`period`),
  KEY `fk_keyword_history_data_searchengine_country1_idx` (`searchengine_country`),
  CONSTRAINT `KW_Keyworddata_FK` FOREIGN KEY (`keyword`) REFERENCES `keyword` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `period_keyworddata_fk` FOREIGN KEY (`period`) REFERENCES `period` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_keyword_history_data_searchengine_country1` FOREIGN KEY (`searchengine_country`) REFERENCES `searchengine_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21631 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword_position_visit`
--

DROP TABLE IF EXISTS `keyword_position_visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_position_visit` (
  `id` int(11) NOT NULL,
  `visit_factor` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword_scan_summary`
--

DROP TABLE IF EXISTS `keyword_scan_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_scan_summary` (
  `id` int(11) NOT NULL,
  `keyword_search_engine_account_domain` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `proxy` int(11) NOT NULL,
  `keyword_scan_summary_status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `keysearaccou_fk_idx` (`keyword_search_engine_account_domain`),
  KEY `keyscansumsatus_fk_idx` (`keyword_scan_summary_status`),
  CONSTRAINT `keyscansumsatus_fk` FOREIGN KEY (`keyword_scan_summary_status`) REFERENCES `keyword_scan_summary_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `keysearaccou_fk` FOREIGN KEY (`keyword_search_engine_account_domain`) REFERENCES `keyword_searchengine_account_domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword_scan_summary_status`
--

DROP TABLE IF EXISTS `keyword_scan_summary_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_scan_summary_status` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL COMMENT '0 - Running\n1 - Completed\n2 - Failed',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword_searchengine`
--

DROP TABLE IF EXISTS `keyword_searchengine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_searchengine` (
  `id` int(11) NOT NULL,
  `searchengine` int(11) NOT NULL,
  `keyword` int(11) NOT NULL,
  `searchengine_country` int(11) NOT NULL,
  `geographical_targeting` int(11) DEFAULT NULL,
  `mobile` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_searchengine_has_keyword_keyword1_idx` (`keyword`),
  KEY `fk_searchengine_has_keyword_searchengine1_idx` (`searchengine`),
  KEY `fk_keyword_searchengine_searchengine_country1_idx` (`searchengine_country`),
  KEY `fk_keyword_searchengine_geographical_targeting1_idx` (`geographical_targeting`),
  CONSTRAINT `fk_keyword_searchengine_geographical_targeting1` FOREIGN KEY (`geographical_targeting`) REFERENCES `geographical_targeting` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_keyword_searchengine_searchengine_country1` FOREIGN KEY (`searchengine_country`) REFERENCES `searchengine_country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_searchengine_has_keyword_keyword1` FOREIGN KEY (`keyword`) REFERENCES `keyword` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_searchengine_has_keyword_searchengine1` FOREIGN KEY (`searchengine`) REFERENCES `searchengine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `keyword_searchengine_account_domain`
--

DROP TABLE IF EXISTS `keyword_searchengine_account_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_searchengine_account_domain` (
  `id` int(11) NOT NULL,
  `keyword_searchengine` int(11) NOT NULL,
  `account_domain` int(11) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_closed` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_keyword_searchengine_has_account_domain_account_domain1_idx` (`account_domain`),
  KEY `fk_keyword_searchengine_has_account_domain_keyword_searchen_idx` (`keyword_searchengine`),
  CONSTRAINT `fk_keyword_searchengine_has_account_domain_keyword_searchengi1` FOREIGN KEY (`keyword_searchengine`) REFERENCES `keyword_searchengine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_keyword_searchengine_has_account_domain_account_domain1` FOREIGN KEY (`account_domain`) REFERENCES `account_domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period` (
  `id` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proxy`
--

DROP TABLE IF EXISTS `proxy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proxy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) NOT NULL,
  `port` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `datecreate` datetime DEFAULT NULL,
  `date_lastsuccess` datetime DEFAULT NULL,
  `date_lastfail` datetime DEFAULT NULL,
  `errors` int(11) DEFAULT '0',
  `date_lastscan` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1564 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `search_report`
--

DROP TABLE IF EXISTS `search_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` int(11) NOT NULL,
  `keyword_searchengine` int(11) NOT NULL,
  `url` int(11) NOT NULL,
  `dateFirstSeen` datetime DEFAULT NULL,
  `dateLastSeen` datetime DEFAULT NULL,
  `dateClosed` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_search_report_keyword_searchengine1_idx` (`keyword_searchengine`),
  KEY `fk_search_report_url1_idx` (`url`),
  CONSTRAINT `fk_search_report_keyword_searchengine1` FOREIGN KEY (`keyword_searchengine`) REFERENCES `keyword_searchengine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_search_report_url1` FOREIGN KEY (`url`) REFERENCES `url` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=95160 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `search_report_account`
--

DROP TABLE IF EXISTS `search_report_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_report_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` int(11) NOT NULL,
  `url` int(11) NOT NULL,
  `keyword_searchengine_account_domain` int(11) NOT NULL,
  `dateFirstSeen` datetime DEFAULT NULL,
  `dateLastSeen` datetime DEFAULT NULL,
  `dateClosed` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_search_report_url1_idx` (`url`),
  KEY `fk_search_report_account_keyword_searchengine_account_domai_idx` (`keyword_searchengine_account_domain`),
  CONSTRAINT `fk_search_report_url10` FOREIGN KEY (`url`) REFERENCES `url` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_search_report_account_keyword_searchengine_account_domain1` FOREIGN KEY (`keyword_searchengine_account_domain`) REFERENCES `keyword_searchengine_account_domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=95160 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `searchengine`
--

DROP TABLE IF EXISTS `searchengine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchengine` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `searchengine_country`
--

DROP TABLE IF EXISTS `searchengine_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchengine_country` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `tld` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `searchengine_parameter`
--

DROP TABLE IF EXISTS `searchengine_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchengine_parameter` (
  `id` varchar(30) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `url`
--

DROP TABLE IF EXISTS `url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(400) NOT NULL,
  `domain` int(11) NOT NULL,
  `datecreate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`),
  KEY `DOMAIN_FK_URL_idx` (`domain`),
  CONSTRAINT `DOMAIN_FK_URL` FOREIGN KEY (`domain`) REFERENCES `domain` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44949 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-08 13:01:19
