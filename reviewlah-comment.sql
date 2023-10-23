
--
-- Table structure for table `diningComment`
--

DROP TABLE IF EXISTS `diningComment`;
CREATE TABLE `diningComment` (
  `dining_com_id` bigint NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint NOT NULL,
  `customer_id` bigint NOT NULL,
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rate` int NOT NULL,
  `time_dc` datetime NOT NULL,
  `pic_dc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`dining_com_id`) USING BTREE,
  KEY `dining comments_ibfk_1_idx` (`customer_id`),
  KEY `dining comments_ibfk_2_idx` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
