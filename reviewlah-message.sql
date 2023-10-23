
--
-- Table structure for table `postCommentMessage`
--

DROP TABLE IF EXISTS `postCommentMessage`;
CREATE TABLE `postCommentMessage` (
  `customer_id` bigint NOT NULL,
  `post_id` bigint NOT NULL,
  `post_com_id` bigint NOT NULL,
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `time` datetime NOT NULL,
  `read` boolean DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;