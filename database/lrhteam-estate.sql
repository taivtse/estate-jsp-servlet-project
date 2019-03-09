/*
 Navicat Premium Data Transfer

 Source Server         : MySQL Server
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : lrhteam-estate

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 09/03/2019 16:28:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assignment
-- ----------------------------
DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `building_id` int(10) unsigned DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ASSIGNMENT_BUILDING` (`building_id`),
  KEY `FK_ASSIGNMENT_USER` (`user_id`),
  CONSTRAINT `FK_ASSIGNMENT_BUILDING` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`),
  CONSTRAINT `FK_ASSIGNMENT_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `districtId` varchar(255) NOT NULL,
  `wardId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `street` varchar(255) NOT NULL,
  `structure` varchar(255) NOT NULL,
  `number_of_basement` int(10) unsigned DEFAULT NULL,
  `building_area` float unsigned NOT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `rental_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_description` text,
  `rental_cost` int(10) unsigned NOT NULL,
  `cost_description` varchar(255) DEFAULT NULL,
  `service_cost` varchar(255) NOT NULL,
  `car_cost` varchar(255) DEFAULT NULL,
  `motorbike_cost` varchar(255) DEFAULT NULL,
  `overtime_cost` varchar(255) DEFAULT NULL,
  `electricity_cost` varchar(255) DEFAULT NULL,
  `deposit_cost` varchar(255) NOT NULL,
  `payment_cost` varchar(255) NOT NULL,
  `time_contract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `time_decorator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `manager_phone` varchar(20) DEFAULT NULL,
  `commission_cost` varchar(255) DEFAULT NULL,
  `note` text,
  `link` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of building
-- ----------------------------
BEGIN;
INSERT INTO `building` VALUES (1, 'New Sky', 'Quận Tân Phú', 'Tân Sơn Nhì', '52 Trường Chinh', 'AAAA', NULL, 900, NULL, NULL, '300', NULL, 50000000, NULL, '10 triệu', NULL, NULL, NULL, NULL, '25000000', '25000000', NULL, NULL, 'Võ Thành Tài', '0961523716', NULL, NULL, NULL, NULL, NULL, NULL, '2019-02-21', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for districtId
-- ----------------------------
DROP TABLE IF EXISTS `districtId`;
CREATE TABLE `districtId` (
  `id` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('MANAGER', 'Manager');
INSERT INTO `role` VALUES ('USER', 'User');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(20) NOT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_ROLE` (`role_id`),
  CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'thanhtai', '12345', 'Võ Thành Tài', 'MANAGER', '2019-01-15', NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'haimy', '12345', 'Trần Hải My', 'USER', '2019-01-25', NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, 'duyquang', '12345', 'Trần Duy Quang', 'MANAGER', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, 'duytan', '12345', 'Trần Duy Tân', 'USER', NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
