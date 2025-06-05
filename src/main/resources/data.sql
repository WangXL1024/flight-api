-- 插入机场信息表数据
INSERT INTO airport (code, name, city) VALUES
('PEK', '北京首都国际机场', '北京'),
('SHA', '上海虹桥国际机场', '上海'),
('PVG', '上海浦东国际机场', '上海'),
('CAN', '广州白云国际机场', '广州'),
('SZX', '深圳宝安国际机场', '深圳'),
('CTU', '成都双流国际机场', '成都'),
('CKG', '重庆江北国际机场', '重庆'),
('HGH', '杭州萧山国际机场', '杭州'),
('XIY', '西安咸阳国际机场', '西安'),
('WUH', '武汉天河国际机场', '武汉');

-- 插入航班信息表数据
INSERT INTO flight (flight_number, departure_airport_id, destination_airport_id, departure_date, departure_time, price) VALUES
('CA1833', 1, 3, '2025-06-01', '08:30:00', 1280.00),
('MU5102', 2, 1, '2025-06-01', '09:15:00', 1180.00),
('CZ3595', 4, 2, '2025-06-01', '10:45:00', 1380.00),
('ZH9831', 5, 1, '2025-06-01', '11:30:00', 1780.00),
('CA4507', 6, 4, '2025-06-01', '13:15:00', 1480.00),
('3U8897', 6, 7, '2025-06-01', '14:40:00', 780.00),
('MU2505', 3, 8, '2025-06-01', '15:25:00', 980.00),
('HU7603', 9, 5, '2025-06-01', '16:30:00', 1580.00),
('MF8039', 8, 10, '2025-06-01', '17:45:00', 880.00),
('SC4875', 10, 6, '2025-06-01', '19:10:00', 1080.00);

INSERT INTO user (email, password, first_name, last_name, country, phone) VALUES
('john.doe@example.com', '$2y$10$RkMhQVjB1BzqJZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1J', 'John', 'Doe', 'United States', '+1234567890'),
('jane.smith@example.com', '$2y$10$RkMhQVjB1BzqJZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1J', 'Jane', 'Smith', 'Canada', '+1987654321'),
('robert.brown@example.com', '$2y$10$RkMhQVjB1BzqJZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1J', 'Robert', 'Brown', 'United Kingdom', '+442071234567'),
('william.wilson@example.com', '$2y$10$RkMhQVjB1BzqJZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1J', 'William', 'Wilson', 'Australia', '+61298765432'),
('james.moore@example.com', '$2y$10$RkMhQVjB1BzqJZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1J', 'James', 'Moore', 'New Zealand', '+64987654321'),
('mary.taylor@example.com', '$2y$10$RkMhQVjB1BzqJZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1JZ1J', 'Mary', 'Taylor', 'Germany', '+493012345678');
