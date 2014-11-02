CREATE DATABASE  IF NOT EXISTS `SI_Basic` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `SI_Basic`;

--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--
LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('admin','111111',1),('user1','222222',1);
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--
DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `authorities`
--
LOCK TABLES `authorities` WRITE;
INSERT INTO `authorities` VALUES ('admin','ROLE_ADMIN'),('admin','ROLE_USER'),('user1','ROLE_USER');
UNLOCK TABLES;