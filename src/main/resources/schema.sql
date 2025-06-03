-- flight_db.`user` definition
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(100) NOT NULL COMMENT '电子邮箱',
  `password` varchar(255) NOT NULL COMMENT '加密密码',
  `first_name` varchar(50) NOT NULL COMMENT '名字',
  `last_name` varchar(50) NOT NULL COMMENT '姓氏',
  `country` varchar(100) NOT NULL COMMENT '国家',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户信息表';

-- flight_db.airport definition
CREATE TABLE `airport` (
  `airport_id` bigint NOT NULL AUTO_INCREMENT COMMENT '机场ID',
  `code` varchar(10) NOT NULL COMMENT '机场三字码',
  `name` varchar(100) NOT NULL COMMENT '机场名称',
  `city` varchar(100) NOT NULL COMMENT '所在城市',
  PRIMARY KEY (`airport_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机场信息表';

-- flight_db.flight definition
CREATE TABLE `flight` (
  `flight_id` bigint NOT NULL AUTO_INCREMENT COMMENT '航班ID',
  `flight_number` varchar(10) NOT NULL COMMENT '航班号',
  `departure_airport_id` bigint NOT NULL COMMENT '出发机场ID',
  `destination_airport_id` bigint NOT NULL COMMENT '目的机场ID',
  `departure_date` date NOT NULL COMMENT '出发日期',
  `departure_time` time NOT NULL COMMENT '出发时间',
  `price` decimal(10,2) NOT NULL COMMENT '基础票价',
  PRIMARY KEY (`flight_id`),
  KEY `departure_airport_id` (`departure_airport_id`),
  KEY `destination_airport_id` (`destination_airport_id`),
  CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`departure_airport_id`) REFERENCES `airport` (`airport_id`),
  CONSTRAINT `flight_ibfk_2` FOREIGN KEY (`destination_airport_id`) REFERENCES `airport` (`airport_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='航班信息表';

-- flight_db.booking definition
CREATE TABLE `booking` (
  `booking_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint NOT NULL COMMENT '创建订单的用户ID',
  `reference` varchar(20) NOT NULL COMMENT '订单参考号',
  `status` varchar(20) NOT NULL COMMENT '订单状态',
  `booking_time` timestamp NOT NULL COMMENT '订单创建时间',
  `total_price` decimal(10,2) NOT NULL COMMENT '订单总价',
  PRIMARY KEY (`booking_id`),
  KEY `user_id` (`user_id`),
  KEY `flight_id` (`flight_id`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='航班订单信息表';

CREATE TABLE `booking_flight` (
  `booking_flight_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单-航班关联ID',
  `booking_id` bigint NOT NULL COMMENT '订单ID',
  `flight_id` bigint NOT NULL COMMENT '航班ID',
  `flight_type` varchar(20) NOT NULL COMMENT '航班类型（OUTBOUND去程/RETURN返程）',
  `price` decimal(10,2) NOT NULL COMMENT '该航班对应的票价',
  PRIMARY KEY (`booking_flight_id`),
  KEY `booking_id` (`booking_id`),
  KEY `flight_id` (`flight_id`),
  CONSTRAINT `booking_flight_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`),
  CONSTRAINT `booking_flight_ibfk_2` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单与航班关联表';

-- flight_db.passenger definition
CREATE TABLE `passenger` (
  `passenger_id` bigint NOT NULL AUTO_INCREMENT COMMENT '乘客ID',
  `booking_id` bigint NOT NULL COMMENT '关联的订单ID',
  `first_name` varchar(50) NOT NULL COMMENT '乘客名字',
  `last_name` varchar(50) NOT NULL COMMENT '乘客姓氏',
  `email` varchar(100) NOT NULL COMMENT '乘客邮箱',
  PRIMARY KEY (`passenger_id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单关联的乘客信息表';
