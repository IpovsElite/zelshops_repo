-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopid` int(11) NOT NULL,
  `userid` varchar(45) NOT NULL,
  `comment` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (25,58,'B4E31ABA5B02416FA0469A5558B8EF7F','Эта инфа капец как устарела'),(26,61,'B4E31ABA5B02416FA0469A5558B8EF7F','дурацкий магаз'),(27,47,'B1AA012DA502DB54A8BD17D0038D1132','Он не для детей'),(28,47,'2C48848423E06CD3777451DBB8B336FD','Фотография левая'),(29,53,'2FAAA08665F7218290C306DEB50824F5','Чтото не так'),(30,42,'2478B610D4C43F3C6F2B91E399C93F18','Слишком пафосное название'),(31,60,'AA09E49CD5297BCF39B1A6D5703F1458','Это не магазин'),(32,57,'2B3BCAE33C285DB6711417D780B183B4','wtf');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `address` varchar(120) NOT NULL,
  `site` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `spec` varchar(45) NOT NULL,
  `image` longblob,
  `status` int(1) NOT NULL,
  `description` varchar(200) NOT NULL DEFAULT '',
  `rating` float NOT NULL DEFAULT '0',
  `voters` int(10) NOT NULL DEFAULT '0',
  `lat` double NOT NULL DEFAULT '0',
  `lng` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (34,'Компьюсити','Зеленоград, микрорайон 3, Корпус 317','нет','+7(499)734-72-27','Электроника',NULL,3,'',0,0,0,0),(35,'Любимая одежда','Зеленоград, микрорайон 3, Корпус 317А, цокольный этаж, павильон 8','нет','нет','Одежда',NULL,3,'',0,0,0,0),(36,'Мясная лавка','Зеленоград, микрорайон 3, Корпус 317А','нет','+7(909)946-33-14','Еда',NULL,3,'',0,0,0,0),(37,'Овощи, фрукты','Зеленоград, микрорайон 3, Корпус 317А','нет','нет','Еда',NULL,3,'',0,0,0,0),(38,'Перекрёсток Экспресс','Зеленоград, микрорайон 3, Корпус 316','нет','нет','Еда',NULL,3,'',0,0,0,0),(39,'Продукты (корпус 339А)','Зеленоград, микрорайон 3, Корпус 339А','нет','нет','Еда',NULL,3,'',0,0,0,0),(40,'Продукты (корпус 349А)','Зеленоград, микрорайон 3, Корпус 349А','нет','нет','Еда',NULL,3,'',0,0,0,0),(41,'Продукты (корпус 361)','Зеленоград, микрорайон 3, корпус 361, строение 1, корпус 361, строение 1','нет','нет','Еда',NULL,3,'',0,0,0,0),(42,'Территория стиля','Зеленоград, микрорайон 3, Корпус 317А (3-й торговый центр), павильон 25','www.terristyle.ru ','нет','Одежда',NULL,2,'',0,0,0,0),(43,'Магазин мужской одежды \"Mensformat\"','Зеленоград, Савелкинский проезд, дом 4, павильон 118','www.mensformat.ru','+7(916)678-04-34','Одежда',NULL,3,'',0,0,0,0),(46,'Столовая Префектуры','Зеленоград, Центральная площадь, дом 1','norma-food.ru','+7(499)735-12-25','Еда',NULL,3,'',0,0,0,0),(47,'Магазин товаров для детей \"Dимапупс\"','Зеленоград, микрорайон 3, Корпус 357, помещение 1','Димапупс.рф','+7(903)186-92-11','Одежда',NULL,3,'',0,0,0,0),(50,'Английский паб \"The Bell Pub\"','Зеленоград, Яблоневая аллея, дом 2, дом 2а','vk.com/bellpub','+7(915)139-61-61','Еда',NULL,3,'',0,0,55.996295,37.217134),(51,'Аптека \"Ригла\" (корпус 317А)','Зеленоград, микрорайон 3, Корпус 317А','www.rigla.ru','+7(499)734-03-15','Здоровье',NULL,3,'',0,0,0,0),(52,'Аптека № 73','Зеленоград, микрорайон 3, Корпус 309','www.citypharm.ru','+7(499)735-73-43','Здоровье',NULL,3,'',0,0,0,0),(53,'Дежурная аптека','Зеленоград, микрорайон 3, Корпус 309','нет','+7(499)735-73-43','Здоровье',NULL,3,'',0,0,0,0),(54,'Супермаркет \"Дикси\"','Зеленоград, ул. Яблоневская аллея, д. 317 а','dixy.ru','+7(495) 535-45-69','Еда',NULL,3,'',0,0,0,0),(55,'Супермаркет \"АТАК\"','Зеленоград, ул. Яблоневая аллея, к. 313б','ataksupermarket.ru','+7(499)-685-13-42','Еда',NULL,3,'',0,0,0,0),(57,'Спортивный магаз','Спортивная','спорт.рф','828382838','Спорт',NULL,2,'',0,0,0,0),(58,'Дикси','Краснобогатырская','w','235324','Еда',NULL,3,'efkpowekf',0,0,55.807668,37.706537),(60,'Мосгорсуд','Москва','www.mosgorsud.com','+7(232)4445566','Еда',NULL,3,'цуацу',0,0,55.802541,37.706995);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tran`
--

DROP TABLE IF EXISTS `tran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tran` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `who` varchar(45) NOT NULL,
  `action` int(1) NOT NULL,
  `shopid` int(11) NOT NULL,
  `when` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tran`
--

LOCK TABLES `tran` WRITE;
/*!40000 ALTER TABLE `tran` DISABLE KEYS */;
/*!40000 ALTER TABLE `tran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `access` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (25,'admin','admin',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-29  5:56:55
