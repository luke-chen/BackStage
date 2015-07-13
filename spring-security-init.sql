CREATE DATABASE  IF NOT EXISTS `backstage` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `backstage`;

--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` integer AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `create_date` timestamp DEFAULT NOW(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create unique index ix_username on users (username);

--
-- Table structure for table `authorities`
--
DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
create unique index ix_auth_username on authorities (username,authority);

--
-- Dumping data for table `users`
--
LOCK TABLES `users` WRITE;
INSERT INTO `users` (username, password, enabled) VALUES ('admin','96e79218965eb72c92a549dd5a330112',1),('user1','96e79218965eb72c92a549dd5a330112',1);
UNLOCK TABLES;

--
-- Dumping data for table `authorities`
--
LOCK TABLES `authorities` WRITE;
INSERT INTO `authorities` VALUES ('admin','ROLE_ADMIN'),('user1','ROLE_USER');
UNLOCK TABLES;