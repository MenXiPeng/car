/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : car

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/03/2020 15:22:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` bigint(24) NOT NULL COMMENT '车辆id',
  `user_id` bigint(20) NOT NULL COMMENT '归属修理厂',
  `commission_id` bigint(24) NOT NULL COMMENT '委托id',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '姓名',
  `sex` tinyint(2) NOT NULL COMMENT '性别',
  `ethnic` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '民族',
  `birth_date` date NOT NULL COMMENT '出生日期',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  `id_car` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '身份证',
  `organ_auth` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '签发机关',
  `begin_validity_date` date NOT NULL COMMENT '有效期限',
  `end_validity_date` date NOT NULL COMMENT '有效期结束时间',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号码',
  `is_main_car` tinyint(2) NOT NULL COMMENT '是否是第三者1:主车，2:三者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '车辆信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for commission_info
-- ----------------------------
DROP TABLE IF EXISTS `commission_info`;
CREATE TABLE `commission_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commission_id` bigint(21) NOT NULL COMMENT '委托id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '归属修理厂',
  `principal` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '委托人',
  `contact` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '联系人',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '电话',
  `policy_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '保单号',
  `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '保险公司',
  `insured` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '被保险人',
  `risk_date` date NOT NULL COMMENT '出险时间',
  `report_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '报案号',
  `damage_amount` decimal(10, 2) NOT NULL COMMENT '定损金额',
  `discount` double(255, 0) NOT NULL COMMENT '折扣',
  `salesman` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '业务员',
  `payment_date` date NOT NULL COMMENT '回款时间',
  `checkout_date` date NOT NULL COMMENT '结账时间',
  `reminder_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '提醒时间',
  `reminder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '提醒内容',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `direct` tinyint(2) NULL DEFAULT NULL COMMENT '直赔',
  `money_back` decimal(15, 2) NOT NULL COMMENT '回款金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '委托信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for driver_info
-- ----------------------------
DROP TABLE IF EXISTS `driver_info`;
CREATE TABLE `driver_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `driver_id` bigint(50) NOT NULL COMMENT '驾驶证id',
  `commission_id` bigint(50) NOT NULL COMMENT '委托id',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '姓名',
  `sex` tinyint(2) NOT NULL COMMENT '性别',
  `country` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '国籍',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  `birth_date` date NOT NULL COMMENT '出生日期',
  `first_doc_date` date NOT NULL COMMENT '初次领证日期',
  `drive_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '准驾车型',
  `begin_validity_date` date NOT NULL COMMENT '有效期限',
  `end_validity_date` date NOT NULL COMMENT '有效期结束时间',
  `id_car` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '证件号',
  `file_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '档案编号',
  `record` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '记录',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `is_main_driver` tinyint(2) NOT NULL COMMENT '是否主车驾驶，1:主车2:三者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '驾驶证信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_id` bigint(50) NOT NULL COMMENT '图片id',
  `car_id` bigint(24) NULL DEFAULT NULL COMMENT '归宿车辆',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图片路径',
  `order_id` int(11) NOT NULL COMMENT '图片排序编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '上传图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for travel
-- ----------------------------
DROP TABLE IF EXISTS `travel`;
CREATE TABLE `travel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '车牌',
  `car_type` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '车辆类型',
  `owner` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '归属人',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '住址',
  `use_type` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '使用性质',
  `brand_type` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '品牌型号',
  `iden_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '车辆识别代号',
  `engine_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '发动机号码',
  `registered_date` date NOT NULL COMMENT '注册日期',
  `issue_date` date NOT NULL COMMENT '发证日期',
  `file_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '档案编号',
  `load_num` int(11) NOT NULL COMMENT '核定载人数',
  `total_quality` int(11) NOT NULL COMMENT '总质量',
  `curb_quality` int(11) NOT NULL COMMENT '整备质量',
  `load_quality` int(11) NOT NULL COMMENT '核定载质量',
  `profile_size` int(11) NOT NULL COMMENT '轮廓尺寸',
  `traction_quality` int(11) NOT NULL COMMENT '准牵引质量',
  `test_record` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '校验记录',
  `bar_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '条形号码',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `commission_id` bigint(50) NOT NULL COMMENT '委托id',
  `travel_id` bigint(50) NOT NULL COMMENT '行驶证id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_main_travel` tinyint(2) NOT NULL COMMENT '是否是主',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '行驶证表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `verification` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '验证id,用于单机验证',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公司',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '联系电话',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户id，用来绑定修理单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '修理厂用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
