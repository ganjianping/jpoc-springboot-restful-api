-- Create users table
CREATE TABLE IF NOT EXISTS`am_user` (
  `id` CHAR(36) NOT NULL,
  `user_name` VARCHAR(255) DEFAULT NULL,
  `nick_name` VARCHAR(255) DEFAULT NULL,
  `mobile_country_code` VARCHAR(10) DEFAULT NULL,
  `mobile_number` VARCHAR(20) DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,
  `status` VARCHAR(20) NOT NULL COMMENT 'ACTIVE, DISABLED, FROZEN, CANCELLED, BLOCKED',
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` VARCHAR(255) DEFAULT NULL,
  `updated_by` VARCHAR(255) DEFAULT NULL,
  `is_deleted` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `mobile_country_code_mobile_number_UNIQUE` (`mobile_country_code`, `mobile_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
