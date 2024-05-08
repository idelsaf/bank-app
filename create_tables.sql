CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `card_number` varchar(16) NOT NULL,
  `pin_code` varchar(4) NOT NULL,
  `current_balance` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(45) NOT NULL,
  `transaction_date` datetime NOT NULL,
  `transaction_amount` decimal(10,2) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
