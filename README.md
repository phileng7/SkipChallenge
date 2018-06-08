# SkipChallenge
This is a REST API that uses Spring Boot/Web/Security with JWT, H2, MySQL<BR>

After running the application the tables below will be created:<BR>
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(95) DEFAULT NULL,
  `address` varchar(105) DEFAULT NULL,
  `creation` varchar(45) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `address_UNIQUE` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(85) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `delivery_address` varchar(105) DEFAULT NULL,
  `contact` varchar(65) DEFAULT NULL,
  `store_id` int(11) NOT NULL DEFAULT '0',
  `total` double NOT NULL DEFAULT '0',
  `status` varchar(30) DEFAULT NULL,
  `last_update` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customerId_fk_idx` (`customer_id`),
  CONSTRAINT `customerId_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `price` double NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL DEFAULT '0',
  `total` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `productId_fk_idx` (`product_id`),
  KEY `orderId_fk_idx` (`order_id`),
  CONSTRAINT `orderId_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `productId_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
