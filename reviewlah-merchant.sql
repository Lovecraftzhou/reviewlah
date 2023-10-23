--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `merchant_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `avator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avg_rate` double NOT NULL,
  PRIMARY KEY (`merchant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `merchant_id` bigint NOT NULL,
  `address_detail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `unitnum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `addres_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`addres_id`) USING BTREE,
  UNIQUE KEY `merchant_id_UNIQUE` (`merchant_id`),
  KEY `address_ibfk_1_idx` (`merchant_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`merchant_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `announcement_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time_anc` datetime NOT NULL,
  `merchant_id` bigint NOT NULL,
  PRIMARY KEY (`announcement_id`) USING BTREE,
  KEY `announcements_ibfk_1_idx` (`merchant_id`),
  CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`merchant_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (3,'BBQ'),(1,'ChineseFood'),(4,'FrenchFood'),(2,'HotPot');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint NOT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE KEY `merchant_id_UNIQUE` (`merchant_id`),
  KEY `menu_ibfk_1_idx` (`merchant_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`merchant_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;


--
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `dish_id` bigint NOT NULL AUTO_INCREMENT,
  `dish_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` double NOT NULL,
  `pic_dish` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `menu_id` bigint NOT NULL,
  PRIMARY KEY (`dish_id`) USING BTREE,
  KEY `disher_ibfk_1_idx` (`menu_id`),
  CONSTRAINT `disher_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Table structure for table `merchantsCategory`
--

DROP TABLE IF EXISTS `merchantsCategory`;
CREATE TABLE `merchantsCategory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `merchant_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `merchants category_ibfk_2_idx` (`merchant_id`),
  KEY `merchants category_ibfk_1_idx` (`category_id`),
  CONSTRAINT `merchantsCategory_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `merchantsCategory_ibfk_2` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`merchant_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

