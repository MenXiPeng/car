/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : car

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 02/12/2019 10:51:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` bigint(24) NOT NULL COMMENT '车辆id',
  `user_id` bigint(20) NOT NULL COMMENT '归属修理厂',
  `commission_id` bigint(24) NOT NULL COMMENT '委托id',
  `name` varchar(24) COLLATE utf8mb4_bin NOT NULL COMMENT '姓名',
  `sex` tinyint(2) NOT NULL COMMENT '性别',
  `ethnic` varchar(12) COLLATE utf8mb4_bin NOT NULL COMMENT '民族',
  `birth` date NOT NULL COMMENT '出生日期',
  `address` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  `id_car` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '身份证',
  `organ_auth` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '签发机关',
  `validity` date NOT NULL COMMENT '有效期限',
  `phone` varchar(13) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `is_main_car` tinyint(2) NOT NULL COMMENT '是否是第三者1:主车，2:三者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='车辆信息表';

-- ----------------------------
-- Records of car_info
-- ----------------------------
BEGIN;
INSERT INTO `car_info` VALUES (29, 1912021043571649, 1, 1912021043575945, '法拉力', 0, '汉族', '1999-09-09', '地球', '001002003', '公安局', '2020-01-01', 'http://sss', 1, '2019-12-02 10:43:58');
INSERT INTO `car_info` VALUES (30, 1912021043573252, 1, 1912021043575945, '小蹦蹦', 0, '回族', '2001-01-01', '火星', '200101010001', '银河系', '9999-01-01', 'http:/www', 2, '2019-12-02 10:43:58');
COMMIT;

-- ----------------------------
-- Table structure for commission_info
-- ----------------------------
DROP TABLE IF EXISTS `commission_info`;
CREATE TABLE `commission_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commission_id` bigint(21) NOT NULL COMMENT '委托id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '归属修理厂',
  `principal` varchar(24) COLLATE utf8mb4_bin NOT NULL COMMENT '委托人',
  `contact` varchar(24) COLLATE utf8mb4_bin NOT NULL COMMENT '联系人',
  `phone` varchar(13) COLLATE utf8mb4_bin NOT NULL COMMENT '电话',
  `policy_num` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '保单号',
  `company` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '保险公司',
  `insured` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '被保险人',
  `risk_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '出险时间',
  `report_num` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '报案号',
  `damage_amount` decimal(10,2) NOT NULL COMMENT '定损金额',
  `discount` double(255,0) NOT NULL COMMENT '折扣',
  `salesman` varchar(24) COLLATE utf8mb4_bin NOT NULL COMMENT '业务员',
  `payment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回款时间',
  `checkout_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结账时间',
  `reminder_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提醒时间',
  `reminder` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '提醒内容',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='委托信息';

-- ----------------------------
-- Records of commission_info
-- ----------------------------
BEGIN;
INSERT INTO `commission_info` VALUES (18, 1912021043575945, 1, '王洪亮', 'whl', '13843888888', 'BD0010001', '测试公司', '梁勇', '2019-12-01 11:11:11', 'BA001001', 1.12, 0, '王鑫', '2019-12-01 12:11:11', '2019-12-30 12:11:11', '2019-12-21 12:11:11', '该起床了', '吃喝拉撒睡', '2019-12-02 10:43:58');
COMMIT;

-- ----------------------------
-- Table structure for driver_info
-- ----------------------------
DROP TABLE IF EXISTS `driver_info`;
CREATE TABLE `driver_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `driver_id` bigint(50) NOT NULL COMMENT '驾驶证id',
  `commission_id` bigint(50) NOT NULL COMMENT '委托id',
  `name` varchar(24) COLLATE utf8mb4_bin NOT NULL COMMENT '姓名',
  `sex` tinyint(2) NOT NULL COMMENT '性别',
  `country` varchar(24) COLLATE utf8mb4_bin NOT NULL COMMENT '国籍',
  `address` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  `birth` date NOT NULL COMMENT '出生日期',
  `first_doc_time` date NOT NULL COMMENT '初次领证日期',
  `drive_model` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '准驾车型',
  `validity` date NOT NULL COMMENT '有效期限',
  `id_car` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '证件号',
  `file_num` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '档案编号',
  `record` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '记录',
  `remarks` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '备注',
  `is_main_driver` tinyint(2) NOT NULL COMMENT '是否主车驾驶，1:主车2:三者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='驾驶证信息';

-- ----------------------------
-- Records of driver_info
-- ----------------------------
BEGIN;
INSERT INTO `driver_info` VALUES (7, 1912021043573876, 1912021043575945, '乌啦啦', 0, '中国', '小山沟子', '1999-01-01', '2001-02-09', '法拉力', '2002-01-02', '2222009', '33333333', '深深的三口阿斯顿接口爱上', '时代大厦撒撒的', 1, '2019-12-02 10:43:58');
INSERT INTO `driver_info` VALUES (8, 1912021043579497, 1912021043575945, '库期', 0, '美国', '大山沟子', '1882-09-18', '1999-01-01', '小蹦蹦', '2032-01-01', '888888', '22222', '反反复复', '反反复复是我', 2, '2019-12-02 10:43:58');
COMMIT;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_id` bigint(50) NOT NULL COMMENT '图片id',
  `car_id` bigint(24) NOT NULL COMMENT '归宿车辆',
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '图片名称',
  `url` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '图片路径',
  `order_id` int(11) NOT NULL COMMENT '图片排序编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='上传图片表';

-- ----------------------------
-- Table structure for travel
-- ----------------------------
DROP TABLE IF EXISTS `travel`;
CREATE TABLE `travel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_num` varchar(12) COLLATE utf8mb4_bin NOT NULL COMMENT '车牌',
  `car_type` varchar(12) COLLATE utf8mb4_bin NOT NULL COMMENT '车辆类型',
  `owner` varchar(24) COLLATE utf8mb4_bin NOT NULL COMMENT '归属人',
  `address` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  `use_type` varchar(12) COLLATE utf8mb4_bin NOT NULL COMMENT '使用性质',
  `brand_type` varchar(12) COLLATE utf8mb4_bin NOT NULL COMMENT '品牌型号',
  `iden_code` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '车辆识别代号',
  `engine_num` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '发动机号码',
  `registered_date` date NOT NULL COMMENT '注册日期',
  `issue_date` date NOT NULL COMMENT '发证日期',
  `file_num` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '档案编号',
  `load_num` int(11) NOT NULL COMMENT '核定载人数',
  `total_quality` int(11) NOT NULL COMMENT '总质量',
  `curb_quality` int(11) NOT NULL COMMENT '整备质量',
  `load_quality` int(11) NOT NULL COMMENT '核定载质量',
  `profile_size` int(11) NOT NULL COMMENT '轮廓尺寸',
  `traction_quality` int(11) NOT NULL COMMENT '准牵引质量',
  `test_record` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '校验记录',
  `bar_num` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '条形号码',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `commission_id` bigint(50) NOT NULL COMMENT '委托id',
  `travel_id` bigint(50) NOT NULL COMMENT '行驶证id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_main_travel` tinyint(2) NOT NULL COMMENT '是否是主',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='行驶证表';

-- ----------------------------
-- Records of travel
-- ----------------------------
BEGIN;
INSERT INTO `travel` VALUES (1, '22222', '不知', '对的', '多多岛', '问问我', '嗯嗯', '222222', '333333', '2001-01-01', '2001-01-02', '5555', 7777, 22, 33, 55, 111, 222, '33', '55555', '团团圆圆', 1912021043575945, 1912021043573720, '2019-12-02 10:43:58', 1);
INSERT INTO `travel` VALUES (2, '33333', '不对的知', '对多多岛的', '多多多岛多岛', '问问多多岛我', '多多岛嗯嗯', '2222334422', '333333', '2001-03-01', '2001-04-02', '55555', 77767, 262, 353, 545, 5111, 2622, '3343', '555355', '团团4圆圆', 1912021043575945, 1912021043576567, '2019-12-02 10:43:58', 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `verification` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '验证id,用于单机验证',
  `username` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `company` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '公司',
  `phone` varchar(13) COLLATE utf8mb4_bin NOT NULL COMMENT '联系电话',
  `address` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户id，用来绑定修理单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='修理厂用户表';

SET FOREIGN_KEY_CHECKS = 1;
