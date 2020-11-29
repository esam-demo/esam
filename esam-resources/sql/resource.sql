DROP TABLE IF EXISTS resources;

CREATE TABLE `resources` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `version` int NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `source` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_resources_name` (`name`),
  KEY `idx_resources_created_by` (`created_by`),
  KEY `idx_resources_source` (`source`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
