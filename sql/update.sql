ALTER TABLE `car`.`user`
MODIFY COLUMN `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码' AFTER `username`;

INSERT INTO `car`.`user`(`id`, `name`, `verification`, `username`, `password`, `company`, `phone`, `address`, `user_id`) VALUES (1, '汽车修理厂', 'BFEBFBFF000906E9', 'root', '$2a$10$ODW7KD.Ck6WkcRO0CAuhTucv4/r5osG8vRmzmjL.EdIER5tSqZf4u', '汽车修理厂', '12345678901', '北京', 1);
