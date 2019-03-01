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

 Date: 01/03/2019 21:57:09
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
  `created_date` timestamp NULL DEFAULT NULL,
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
  `district` varchar(255) NOT NULL,
  `ward` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `street` varchar(255) NOT NULL,
  `structure` varchar(255) NOT NULL,
  `number_of_basement` int(10) unsigned DEFAULT NULL,
  `building_area` int(10) unsigned NOT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `rental_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_description` text,
  `rental_cost` int(10) unsigned NOT NULL,
  `cost_description` varchar(255) DEFAULT NULL,
  `service_cost` int(10) unsigned NOT NULL,
  `car_cost` int(10) unsigned DEFAULT NULL,
  `motorbike_cost` int(10) unsigned DEFAULT NULL,
  `overtime_cost` int(10) unsigned DEFAULT NULL,
  `electricity_cost` int(10) unsigned DEFAULT NULL,
  `deposit_cost` int(10) unsigned NOT NULL,
  `payment_cost` int(10) unsigned NOT NULL,
  `time_contract` varchar(50) DEFAULT NULL,
  `time_decorator` varchar(50) DEFAULT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `manager_phone` varchar(20) DEFAULT NULL,
  `commission_cost` int(10) unsigned DEFAULT NULL,
  `note` text,
  `link` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `type_arrays` varchar(255) DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
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
  PRIMARY KEY (`id`),
  KEY `FK_USER_ROLE` (`role_id`),
  CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'thanhtai', '12345', 'Võ Thành Tài', 'MANAGER', NULL, NULL);
INSERT INTO `user` VALUES (2, 'haimy', '12345', 'Trần Hải My', 'USER', NULL, NULL);
INSERT INTO `user` VALUES (3, 'duyquang', '12345', 'Trần Duy Quang', 'MANAGER', NULL, NULL);
INSERT INTO `user` VALUES (4, 'duyquang', '12345', 'Trần Duy Quang', 'MANAGER', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
