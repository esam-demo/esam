drop table if exists requests;
select * from requests;
--update requests set source = 'ABS SYSTEM' where creator like '%1'; commit;

CREATE TABLE `requests` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'request id if submit into system',
  `created_by` varchar(255) NOT NULL COMMENT 'the people who submit request',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'the date when submit request',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'the people who update request',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'the date when update request',
  `version` int NOT NULL DEFAULT '1' COMMENT 'Optimistic lock',
  `requested_for` varchar(255) NOT NULL COMMENT 'the people who submit request for',
  `type` varchar(45) DEFAULT NULL COMMENT 'request type, such as CREATE, DISABLE, REVALIDATE',
  `status` varchar(45) DEFAULT NULL COMMENT 'What the current request status',
  `justification` varchar(1000) DEFAULT NULL COMMENT 'the reason of requested_by request',
  `resource_id` varchar(45) DEFAULT NULL COMMENT 'The ID of the resource requested by the user',
  `resource_name` varchar(500) DEFAULT NULL COMMENT 'The name of the resource requested by the user',
  `resource_type` varchar(45) DEFAULT NULL COMMENT 'The type of the resource requested by the user',
  `source` varchar(45) DEFAULT NULL COMMENT 'where the resource is from',
  `comment` varchar(1000) DEFAULT NULL COMMENT 'the comment from anywhere',
  PRIMARY KEY (`id`),
  KEY `idx_requests_resource_id` (`resource_id`),
  KEY `idx_requests_requested_for` (`requested_for`),
  KEY `idx_requests_created_by` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='esam request table'
;


commit;

-- templates of creating index in mysql
-- CREATE INDEX indexName ON table_name (column_name)
-- ALTER table tableName ADD INDEX indexName(columnName)

