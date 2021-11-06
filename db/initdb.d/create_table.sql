USE `db`
CREATE TABLE `db`.`users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdAt` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `encPassword` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `modifiedAt` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;