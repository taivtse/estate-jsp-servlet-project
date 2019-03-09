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

 Date: 10/03/2019 00:37:14
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
  `ward_id` int(10) unsigned NOT NULL,
  `street` varchar(255) NOT NULL,
  `structure` varchar(255) NOT NULL,
  `number_of_basement` int(10) unsigned DEFAULT NULL,
  `building_area` float unsigned NOT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  KEY `FK_BUILDING_WARD_WARD` (`ward_id`),
  CONSTRAINT `FK_BUILDING_WARD_WARD` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of building
-- ----------------------------
BEGIN;
INSERT INTO `building` VALUES (1, 'New Sky', 26737, '52 Nguyễn Huệ', 'AAAA', NULL, 900, NULL, NULL, NULL, 50000000, NULL, '10 triệu', NULL, NULL, NULL, NULL, '25000000', '25000000', NULL, NULL, 'Võ Thành Tài', '0961523716', '5 triệu', NULL, NULL, NULL, NULL, NULL, '2019-02-21', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=974 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of district
-- ----------------------------
BEGIN;
INSERT INTO `district` VALUES (760, 'Quận 1');
INSERT INTO `district` VALUES (761, 'Quận 12');
INSERT INTO `district` VALUES (762, 'Quận Thủ Đức');
INSERT INTO `district` VALUES (763, 'Quận 9');
INSERT INTO `district` VALUES (764, 'Quận Gò Vấp');
INSERT INTO `district` VALUES (765, 'Quận Bình Thạnh');
INSERT INTO `district` VALUES (766, 'Quận Tân Bình');
INSERT INTO `district` VALUES (767, 'Quận Tân Phú');
INSERT INTO `district` VALUES (768, 'Quận Phú Nhuận');
INSERT INTO `district` VALUES (769, 'Quận 2');
INSERT INTO `district` VALUES (770, 'Quận 3');
INSERT INTO `district` VALUES (771, 'Quận 10');
INSERT INTO `district` VALUES (772, 'Quận 11');
INSERT INTO `district` VALUES (773, 'Quận 4');
INSERT INTO `district` VALUES (774, 'Quận 5');
INSERT INTO `district` VALUES (775, 'Quận 6');
INSERT INTO `district` VALUES (776, 'Quận 8');
INSERT INTO `district` VALUES (777, 'Quận Bình Tân');
INSERT INTO `district` VALUES (778, 'Quận 7');
INSERT INTO `district` VALUES (783, 'Huyện Củ Chi');
INSERT INTO `district` VALUES (784, 'Huyện Hóc Môn');
INSERT INTO `district` VALUES (785, 'Huyện Bình Chánh');
INSERT INTO `district` VALUES (786, 'Huyện Nhà Bè');
INSERT INTO `district` VALUES (787, 'Huyện Cần Giờ');
COMMIT;

-- ----------------------------
-- Table structure for rent_area
-- ----------------------------
DROP TABLE IF EXISTS `rent_area`;
CREATE TABLE `rent_area` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `area` float DEFAULT NULL,
  `building_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_RENT_AREA_BUILDING` (`building_id`),
  CONSTRAINT `FK_RENT_AREA_BUILDING` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'MANAGER', 'Quản lý');
INSERT INTO `role` VALUES (2, 'STAFF', 'Nhân viên');
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
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_date` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_ROLE` (`role_id`),
  CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'thanhtai', '12345', 'Võ Thành Tài', b'1', '2019-01-15', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (2, 'haimy', '12345', 'Trần Hải My', b'1', '2019-01-25', NULL, NULL, NULL, 2);
INSERT INTO `user` VALUES (3, 'duyquang', '12345', 'Trần Duy Quang', b'1', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (4, 'duytan', '12345', 'Trần Duy Tân', b'1', NULL, NULL, NULL, NULL, 2);
COMMIT;

-- ----------------------------
-- Table structure for ward
-- ----------------------------
DROP TABLE IF EXISTS `ward`;
CREATE TABLE `ward` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `district_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_WARD_DISTRICT` (`district_id`),
  CONSTRAINT `FK_WARD_DISTRICT` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27683 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ward
-- ----------------------------
BEGIN;
INSERT INTO `ward` VALUES (26734, 'Phường Tân Định', 760);
INSERT INTO `ward` VALUES (26737, 'Phường Đa Kao', 760);
INSERT INTO `ward` VALUES (26740, 'Phường Bến Nghé', 760);
INSERT INTO `ward` VALUES (26743, 'Phường Bến Thành', 760);
INSERT INTO `ward` VALUES (26746, 'Phường Nguyễn Thái Bình', 760);
INSERT INTO `ward` VALUES (26749, 'Phường Phạm Ngũ Lão', 760);
INSERT INTO `ward` VALUES (26752, 'Phường Cầu Ông Lãnh', 760);
INSERT INTO `ward` VALUES (26755, 'Phường Cô Giang', 760);
INSERT INTO `ward` VALUES (26758, 'Phường Nguyễn Cư Trinh', 760);
INSERT INTO `ward` VALUES (26761, 'Phường Cầu Kho', 760);
INSERT INTO `ward` VALUES (26764, 'Phường Thạnh Xuân', 761);
INSERT INTO `ward` VALUES (26767, 'Phường Thạnh Lộc', 761);
INSERT INTO `ward` VALUES (26770, 'Phường Hiệp Thành', 761);
INSERT INTO `ward` VALUES (26773, 'Phường Thới An', 761);
INSERT INTO `ward` VALUES (26776, 'Phường Tân Chánh Hiệp', 761);
INSERT INTO `ward` VALUES (26779, 'Phường An Phú Đông', 761);
INSERT INTO `ward` VALUES (26782, 'Phường Tân Thới Hiệp', 761);
INSERT INTO `ward` VALUES (26785, 'Phường Trung Mỹ Tây', 761);
INSERT INTO `ward` VALUES (26787, 'Phường Tân Hưng Thuận', 761);
INSERT INTO `ward` VALUES (26788, 'Phường Đông Hưng Thuận', 761);
INSERT INTO `ward` VALUES (26791, 'Phường Tân Thới Nhất', 761);
INSERT INTO `ward` VALUES (26794, 'Phường Linh Xuân', 762);
INSERT INTO `ward` VALUES (26797, 'Phường Bình Chiểu', 762);
INSERT INTO `ward` VALUES (26800, 'Phường Linh Trung', 762);
INSERT INTO `ward` VALUES (26803, 'Phường Tam Bình', 762);
INSERT INTO `ward` VALUES (26806, 'Phường Tam Phú', 762);
INSERT INTO `ward` VALUES (26809, 'Phường Hiệp Bình Phước', 762);
INSERT INTO `ward` VALUES (26812, 'Phường Hiệp Bình Chánh', 762);
INSERT INTO `ward` VALUES (26815, 'Phường Linh Chiểu', 762);
INSERT INTO `ward` VALUES (26818, 'Phường Linh Tây', 762);
INSERT INTO `ward` VALUES (26821, 'Phường Linh Đông', 762);
INSERT INTO `ward` VALUES (26824, 'Phường Bình Thọ', 762);
INSERT INTO `ward` VALUES (26827, 'Phường Trường Thọ', 762);
INSERT INTO `ward` VALUES (26830, 'Phường Long Bình', 763);
INSERT INTO `ward` VALUES (26833, 'Phường Long Thạnh Mỹ', 763);
INSERT INTO `ward` VALUES (26836, 'Phường Tân Phú', 763);
INSERT INTO `ward` VALUES (26839, 'Phường Hiệp Phú', 763);
INSERT INTO `ward` VALUES (26842, 'Phường Tăng Nhơn Phú A', 763);
INSERT INTO `ward` VALUES (26845, 'Phường Tăng Nhơn Phú B', 763);
INSERT INTO `ward` VALUES (26848, 'Phường Phước Long B', 763);
INSERT INTO `ward` VALUES (26851, 'Phường Phước Long A', 763);
INSERT INTO `ward` VALUES (26854, 'Phường Trường Thạnh', 763);
INSERT INTO `ward` VALUES (26857, 'Phường Long Phước', 763);
INSERT INTO `ward` VALUES (26860, 'Phường Long Trường', 763);
INSERT INTO `ward` VALUES (26863, 'Phường Phước Bình', 763);
INSERT INTO `ward` VALUES (26866, 'Phường Phú Hữu', 763);
INSERT INTO `ward` VALUES (26869, 'Phường 15', 764);
INSERT INTO `ward` VALUES (26872, 'Phường 13', 764);
INSERT INTO `ward` VALUES (26875, 'Phường 17', 764);
INSERT INTO `ward` VALUES (26876, 'Phường 6', 764);
INSERT INTO `ward` VALUES (26878, 'Phường 16', 764);
INSERT INTO `ward` VALUES (26881, 'Phường 12', 764);
INSERT INTO `ward` VALUES (26882, 'Phường 14', 764);
INSERT INTO `ward` VALUES (26884, 'Phường 10', 764);
INSERT INTO `ward` VALUES (26887, 'Phường 05', 764);
INSERT INTO `ward` VALUES (26890, 'Phường 07', 764);
INSERT INTO `ward` VALUES (26893, 'Phường 04', 764);
INSERT INTO `ward` VALUES (26896, 'Phường 01', 764);
INSERT INTO `ward` VALUES (26897, 'Phường 9', 764);
INSERT INTO `ward` VALUES (26898, 'Phường 8', 764);
INSERT INTO `ward` VALUES (26899, 'Phường 11', 764);
INSERT INTO `ward` VALUES (26902, 'Phường 03', 764);
INSERT INTO `ward` VALUES (26905, 'Phường 13', 765);
INSERT INTO `ward` VALUES (26908, 'Phường 11', 765);
INSERT INTO `ward` VALUES (26911, 'Phường 27', 765);
INSERT INTO `ward` VALUES (26914, 'Phường 26', 765);
INSERT INTO `ward` VALUES (26917, 'Phường 12', 765);
INSERT INTO `ward` VALUES (26920, 'Phường 25', 765);
INSERT INTO `ward` VALUES (26923, 'Phường 05', 765);
INSERT INTO `ward` VALUES (26926, 'Phường 07', 765);
INSERT INTO `ward` VALUES (26929, 'Phường 24', 765);
INSERT INTO `ward` VALUES (26932, 'Phường 06', 765);
INSERT INTO `ward` VALUES (26935, 'Phường 14', 765);
INSERT INTO `ward` VALUES (26938, 'Phường 15', 765);
INSERT INTO `ward` VALUES (26941, 'Phường 02', 765);
INSERT INTO `ward` VALUES (26944, 'Phường 01', 765);
INSERT INTO `ward` VALUES (26947, 'Phường 03', 765);
INSERT INTO `ward` VALUES (26950, 'Phường 17', 765);
INSERT INTO `ward` VALUES (26953, 'Phường 21', 765);
INSERT INTO `ward` VALUES (26956, 'Phường 22', 765);
INSERT INTO `ward` VALUES (26959, 'Phường 19', 765);
INSERT INTO `ward` VALUES (26962, 'Phường 28', 765);
INSERT INTO `ward` VALUES (26965, 'Phường 02', 766);
INSERT INTO `ward` VALUES (26968, 'Phường 04', 766);
INSERT INTO `ward` VALUES (26971, 'Phường 12', 766);
INSERT INTO `ward` VALUES (26974, 'Phường 13', 766);
INSERT INTO `ward` VALUES (26977, 'Phường 01', 766);
INSERT INTO `ward` VALUES (26980, 'Phường 03', 766);
INSERT INTO `ward` VALUES (26983, 'Phường 11', 766);
INSERT INTO `ward` VALUES (26986, 'Phường 07', 766);
INSERT INTO `ward` VALUES (26989, 'Phường 05', 766);
INSERT INTO `ward` VALUES (26992, 'Phường 10', 766);
INSERT INTO `ward` VALUES (26995, 'Phường 06', 766);
INSERT INTO `ward` VALUES (26998, 'Phường 08', 766);
INSERT INTO `ward` VALUES (27001, 'Phường 09', 766);
INSERT INTO `ward` VALUES (27004, 'Phường 14', 766);
INSERT INTO `ward` VALUES (27007, 'Phường 15', 766);
INSERT INTO `ward` VALUES (27010, 'Phường Tân Sơn Nhì', 767);
INSERT INTO `ward` VALUES (27013, 'Phường Tây Thạnh', 767);
INSERT INTO `ward` VALUES (27016, 'Phường Sơn Kỳ', 767);
INSERT INTO `ward` VALUES (27019, 'Phường Tân Quý', 767);
INSERT INTO `ward` VALUES (27022, 'Phường Tân Thành', 767);
INSERT INTO `ward` VALUES (27025, 'Phường Phú Thọ Hòa', 767);
INSERT INTO `ward` VALUES (27028, 'Phường Phú Thạnh', 767);
INSERT INTO `ward` VALUES (27031, 'Phường Phú Trung', 767);
INSERT INTO `ward` VALUES (27034, 'Phường Hòa Thạnh', 767);
INSERT INTO `ward` VALUES (27037, 'Phường Hiệp Tân', 767);
INSERT INTO `ward` VALUES (27040, 'Phường Tân Thới Hòa', 767);
INSERT INTO `ward` VALUES (27043, 'Phường 04', 768);
INSERT INTO `ward` VALUES (27046, 'Phường 05', 768);
INSERT INTO `ward` VALUES (27049, 'Phường 09', 768);
INSERT INTO `ward` VALUES (27052, 'Phường 07', 768);
INSERT INTO `ward` VALUES (27055, 'Phường 03', 768);
INSERT INTO `ward` VALUES (27058, 'Phường 01', 768);
INSERT INTO `ward` VALUES (27061, 'Phường 02', 768);
INSERT INTO `ward` VALUES (27064, 'Phường 08', 768);
INSERT INTO `ward` VALUES (27067, 'Phường 15', 768);
INSERT INTO `ward` VALUES (27070, 'Phường 10', 768);
INSERT INTO `ward` VALUES (27073, 'Phường 11', 768);
INSERT INTO `ward` VALUES (27076, 'Phường 17', 768);
INSERT INTO `ward` VALUES (27079, 'Phường 14', 768);
INSERT INTO `ward` VALUES (27082, 'Phường 12', 768);
INSERT INTO `ward` VALUES (27085, 'Phường 13', 768);
INSERT INTO `ward` VALUES (27088, 'Phường Thảo Điền', 769);
INSERT INTO `ward` VALUES (27091, 'Phường An Phú', 769);
INSERT INTO `ward` VALUES (27094, 'Phường Bình An', 769);
INSERT INTO `ward` VALUES (27097, 'Phường Bình Trưng Đông', 769);
INSERT INTO `ward` VALUES (27100, 'Phường Bình Trưng Tây', 769);
INSERT INTO `ward` VALUES (27103, 'Phường Bình Khánh', 769);
INSERT INTO `ward` VALUES (27106, 'Phường An Khánh', 769);
INSERT INTO `ward` VALUES (27109, 'Phường Cát Lái', 769);
INSERT INTO `ward` VALUES (27112, 'Phường Thạnh Mỹ Lợi', 769);
INSERT INTO `ward` VALUES (27115, 'Phường An Lợi Đông', 769);
INSERT INTO `ward` VALUES (27118, 'Phường Thủ Thiêm', 769);
INSERT INTO `ward` VALUES (27121, 'Phường 08', 770);
INSERT INTO `ward` VALUES (27124, 'Phường 07', 770);
INSERT INTO `ward` VALUES (27127, 'Phường 14', 770);
INSERT INTO `ward` VALUES (27130, 'Phường 12', 770);
INSERT INTO `ward` VALUES (27133, 'Phường 11', 770);
INSERT INTO `ward` VALUES (27136, 'Phường 13', 770);
INSERT INTO `ward` VALUES (27139, 'Phường 06', 770);
INSERT INTO `ward` VALUES (27142, 'Phường 09', 770);
INSERT INTO `ward` VALUES (27145, 'Phường 10', 770);
INSERT INTO `ward` VALUES (27148, 'Phường 04', 770);
INSERT INTO `ward` VALUES (27151, 'Phường 05', 770);
INSERT INTO `ward` VALUES (27154, 'Phường 03', 770);
INSERT INTO `ward` VALUES (27157, 'Phường 02', 770);
INSERT INTO `ward` VALUES (27160, 'Phường 01', 770);
INSERT INTO `ward` VALUES (27163, 'Phường 15', 771);
INSERT INTO `ward` VALUES (27166, 'Phường 13', 771);
INSERT INTO `ward` VALUES (27169, 'Phường 14', 771);
INSERT INTO `ward` VALUES (27172, 'Phường 12', 771);
INSERT INTO `ward` VALUES (27175, 'Phường 11', 771);
INSERT INTO `ward` VALUES (27178, 'Phường 10', 771);
INSERT INTO `ward` VALUES (27181, 'Phường 09', 771);
INSERT INTO `ward` VALUES (27184, 'Phường 01', 771);
INSERT INTO `ward` VALUES (27187, 'Phường 08', 771);
INSERT INTO `ward` VALUES (27190, 'Phường 02', 771);
INSERT INTO `ward` VALUES (27193, 'Phường 04', 771);
INSERT INTO `ward` VALUES (27196, 'Phường 07', 771);
INSERT INTO `ward` VALUES (27199, 'Phường 05', 771);
INSERT INTO `ward` VALUES (27202, 'Phường 06', 771);
INSERT INTO `ward` VALUES (27205, 'Phường 03', 771);
INSERT INTO `ward` VALUES (27208, 'Phường 15', 772);
INSERT INTO `ward` VALUES (27211, 'Phường 05', 772);
INSERT INTO `ward` VALUES (27214, 'Phường 14', 772);
INSERT INTO `ward` VALUES (27217, 'Phường 11', 772);
INSERT INTO `ward` VALUES (27220, 'Phường 03', 772);
INSERT INTO `ward` VALUES (27223, 'Phường 10', 772);
INSERT INTO `ward` VALUES (27226, 'Phường 13', 772);
INSERT INTO `ward` VALUES (27229, 'Phường 08', 772);
INSERT INTO `ward` VALUES (27232, 'Phường 09', 772);
INSERT INTO `ward` VALUES (27235, 'Phường 12', 772);
INSERT INTO `ward` VALUES (27238, 'Phường 07', 772);
INSERT INTO `ward` VALUES (27241, 'Phường 06', 772);
INSERT INTO `ward` VALUES (27244, 'Phường 04', 772);
INSERT INTO `ward` VALUES (27247, 'Phường 01', 772);
INSERT INTO `ward` VALUES (27250, 'Phường 02', 772);
INSERT INTO `ward` VALUES (27253, 'Phường 16', 772);
INSERT INTO `ward` VALUES (27256, 'Phường 12', 773);
INSERT INTO `ward` VALUES (27259, 'Phường 13', 773);
INSERT INTO `ward` VALUES (27262, 'Phường 09', 773);
INSERT INTO `ward` VALUES (27265, 'Phường 06', 773);
INSERT INTO `ward` VALUES (27268, 'Phường 08', 773);
INSERT INTO `ward` VALUES (27271, 'Phường 10', 773);
INSERT INTO `ward` VALUES (27274, 'Phường 05', 773);
INSERT INTO `ward` VALUES (27277, 'Phường 18', 773);
INSERT INTO `ward` VALUES (27280, 'Phường 14', 773);
INSERT INTO `ward` VALUES (27283, 'Phường 04', 773);
INSERT INTO `ward` VALUES (27286, 'Phường 03', 773);
INSERT INTO `ward` VALUES (27289, 'Phường 16', 773);
INSERT INTO `ward` VALUES (27292, 'Phường 02', 773);
INSERT INTO `ward` VALUES (27295, 'Phường 15', 773);
INSERT INTO `ward` VALUES (27298, 'Phường 01', 773);
INSERT INTO `ward` VALUES (27301, 'Phường 04', 774);
INSERT INTO `ward` VALUES (27304, 'Phường 09', 774);
INSERT INTO `ward` VALUES (27307, 'Phường 03', 774);
INSERT INTO `ward` VALUES (27310, 'Phường 12', 774);
INSERT INTO `ward` VALUES (27313, 'Phường 02', 774);
INSERT INTO `ward` VALUES (27316, 'Phường 08', 774);
INSERT INTO `ward` VALUES (27319, 'Phường 15', 774);
INSERT INTO `ward` VALUES (27322, 'Phường 07', 774);
INSERT INTO `ward` VALUES (27325, 'Phường 01', 774);
INSERT INTO `ward` VALUES (27328, 'Phường 11', 774);
INSERT INTO `ward` VALUES (27331, 'Phường 14', 774);
INSERT INTO `ward` VALUES (27334, 'Phường 05', 774);
INSERT INTO `ward` VALUES (27337, 'Phường 06', 774);
INSERT INTO `ward` VALUES (27340, 'Phường 10', 774);
INSERT INTO `ward` VALUES (27343, 'Phường 13', 774);
INSERT INTO `ward` VALUES (27346, 'Phường 14', 775);
INSERT INTO `ward` VALUES (27349, 'Phường 13', 775);
INSERT INTO `ward` VALUES (27352, 'Phường 09', 775);
INSERT INTO `ward` VALUES (27355, 'Phường 06', 775);
INSERT INTO `ward` VALUES (27358, 'Phường 12', 775);
INSERT INTO `ward` VALUES (27361, 'Phường 05', 775);
INSERT INTO `ward` VALUES (27364, 'Phường 11', 775);
INSERT INTO `ward` VALUES (27367, 'Phường 02', 775);
INSERT INTO `ward` VALUES (27370, 'Phường 01', 775);
INSERT INTO `ward` VALUES (27373, 'Phường 04', 775);
INSERT INTO `ward` VALUES (27376, 'Phường 08', 775);
INSERT INTO `ward` VALUES (27379, 'Phường 03', 775);
INSERT INTO `ward` VALUES (27382, 'Phường 07', 775);
INSERT INTO `ward` VALUES (27385, 'Phường 10', 775);
INSERT INTO `ward` VALUES (27388, 'Phường 08', 776);
INSERT INTO `ward` VALUES (27391, 'Phường 02', 776);
INSERT INTO `ward` VALUES (27394, 'Phường 01', 776);
INSERT INTO `ward` VALUES (27397, 'Phường 03', 776);
INSERT INTO `ward` VALUES (27400, 'Phường 11', 776);
INSERT INTO `ward` VALUES (27403, 'Phường 09', 776);
INSERT INTO `ward` VALUES (27406, 'Phường 10', 776);
INSERT INTO `ward` VALUES (27409, 'Phường 04', 776);
INSERT INTO `ward` VALUES (27412, 'Phường 13', 776);
INSERT INTO `ward` VALUES (27415, 'Phường 12', 776);
INSERT INTO `ward` VALUES (27418, 'Phường 05', 776);
INSERT INTO `ward` VALUES (27421, 'Phường 14', 776);
INSERT INTO `ward` VALUES (27424, 'Phường 06', 776);
INSERT INTO `ward` VALUES (27427, 'Phường 15', 776);
INSERT INTO `ward` VALUES (27430, 'Phường 16', 776);
INSERT INTO `ward` VALUES (27433, 'Phường 07', 776);
INSERT INTO `ward` VALUES (27436, 'Phường Bình Hưng Hòa', 777);
INSERT INTO `ward` VALUES (27439, 'Phường Bình Hưng Hoà A', 777);
INSERT INTO `ward` VALUES (27442, 'Phường Bình Hưng Hoà B', 777);
INSERT INTO `ward` VALUES (27445, 'Phường Bình Trị Đông', 777);
INSERT INTO `ward` VALUES (27448, 'Phường Bình Trị Đông A', 777);
INSERT INTO `ward` VALUES (27451, 'Phường Bình Trị Đông B', 777);
INSERT INTO `ward` VALUES (27454, 'Phường Tân Tạo', 777);
INSERT INTO `ward` VALUES (27457, 'Phường Tân Tạo A', 777);
INSERT INTO `ward` VALUES (27460, 'Phường  An Lạc', 777);
INSERT INTO `ward` VALUES (27463, 'Phường An Lạc A', 777);
INSERT INTO `ward` VALUES (27466, 'Phường Tân Thuận Đông', 778);
INSERT INTO `ward` VALUES (27469, 'Phường Tân Thuận Tây', 778);
INSERT INTO `ward` VALUES (27472, 'Phường Tân Kiểng', 778);
INSERT INTO `ward` VALUES (27475, 'Phường Tân Hưng', 778);
INSERT INTO `ward` VALUES (27478, 'Phường Bình Thuận', 778);
INSERT INTO `ward` VALUES (27481, 'Phường Tân Quy', 778);
INSERT INTO `ward` VALUES (27484, 'Phường Phú Thuận', 778);
INSERT INTO `ward` VALUES (27487, 'Phường Tân Phú', 778);
INSERT INTO `ward` VALUES (27490, 'Phường Tân Phong', 778);
INSERT INTO `ward` VALUES (27493, 'Phường Phú Mỹ', 778);
INSERT INTO `ward` VALUES (27496, 'Thị trấn Củ Chi', 783);
INSERT INTO `ward` VALUES (27499, 'Xã Phú Mỹ Hưng', 783);
INSERT INTO `ward` VALUES (27502, 'Xã An Phú', 783);
INSERT INTO `ward` VALUES (27505, 'Xã Trung Lập Thượng', 783);
INSERT INTO `ward` VALUES (27508, 'Xã An Nhơn Tây', 783);
INSERT INTO `ward` VALUES (27511, 'Xã Nhuận Đức', 783);
INSERT INTO `ward` VALUES (27514, 'Xã Phạm Văn Cội', 783);
INSERT INTO `ward` VALUES (27517, 'Xã Phú Hòa Đông', 783);
INSERT INTO `ward` VALUES (27520, 'Xã Trung Lập Hạ', 783);
INSERT INTO `ward` VALUES (27523, 'Xã Trung An', 783);
INSERT INTO `ward` VALUES (27526, 'Xã Phước Thạnh', 783);
INSERT INTO `ward` VALUES (27529, 'Xã Phước Hiệp', 783);
INSERT INTO `ward` VALUES (27532, 'Xã Tân An Hội', 783);
INSERT INTO `ward` VALUES (27535, 'Xã Phước Vĩnh An', 783);
INSERT INTO `ward` VALUES (27538, 'Xã Thái Mỹ', 783);
INSERT INTO `ward` VALUES (27541, 'Xã Tân Thạnh Tây', 783);
INSERT INTO `ward` VALUES (27544, 'Xã Hòa Phú', 783);
INSERT INTO `ward` VALUES (27547, 'Xã Tân Thạnh Đông', 783);
INSERT INTO `ward` VALUES (27550, 'Xã Bình Mỹ', 783);
INSERT INTO `ward` VALUES (27553, 'Xã Tân Phú Trung', 783);
INSERT INTO `ward` VALUES (27556, 'Xã Tân Thông Hội', 783);
INSERT INTO `ward` VALUES (27559, 'Thị trấn Hóc Môn', 784);
INSERT INTO `ward` VALUES (27562, 'Xã Tân Hiệp', 784);
INSERT INTO `ward` VALUES (27565, 'Xã Nhị Bình', 784);
INSERT INTO `ward` VALUES (27568, 'Xã Đông Thạnh', 784);
INSERT INTO `ward` VALUES (27571, 'Xã Tân Thới Nhì', 784);
INSERT INTO `ward` VALUES (27574, 'Xã Thới Tam Thôn', 784);
INSERT INTO `ward` VALUES (27577, 'Xã Xuân Thới Sơn', 784);
INSERT INTO `ward` VALUES (27580, 'Xã Tân Xuân', 784);
INSERT INTO `ward` VALUES (27583, 'Xã Xuân Thới Đông', 784);
INSERT INTO `ward` VALUES (27586, 'Xã Trung Chánh', 784);
INSERT INTO `ward` VALUES (27589, 'Xã Xuân Thới Thượng', 784);
INSERT INTO `ward` VALUES (27592, 'Xã Bà Điểm', 784);
INSERT INTO `ward` VALUES (27595, 'Thị trấn Tân Túc', 785);
INSERT INTO `ward` VALUES (27598, 'Xã Phạm Văn Hai', 785);
INSERT INTO `ward` VALUES (27601, 'Xã Vĩnh Lộc A', 785);
INSERT INTO `ward` VALUES (27604, 'Xã Vĩnh Lộc B', 785);
INSERT INTO `ward` VALUES (27607, 'Xã Bình Lợi', 785);
INSERT INTO `ward` VALUES (27610, 'Xã Lê Minh Xuân', 785);
INSERT INTO `ward` VALUES (27613, 'Xã Tân Nhựt', 785);
INSERT INTO `ward` VALUES (27616, 'Xã Tân Kiên', 785);
INSERT INTO `ward` VALUES (27619, 'Xã Bình Hưng', 785);
INSERT INTO `ward` VALUES (27622, 'Xã Phong Phú', 785);
INSERT INTO `ward` VALUES (27625, 'Xã An Phú Tây', 785);
INSERT INTO `ward` VALUES (27628, 'Xã Hưng Long', 785);
INSERT INTO `ward` VALUES (27631, 'Xã Đa Phước', 785);
INSERT INTO `ward` VALUES (27634, 'Xã Tân Quý Tây', 785);
INSERT INTO `ward` VALUES (27637, 'Xã Bình Chánh', 785);
INSERT INTO `ward` VALUES (27640, 'Xã Quy Đức', 785);
INSERT INTO `ward` VALUES (27643, 'Thị trấn Nhà Bè', 786);
INSERT INTO `ward` VALUES (27646, 'Xã Phước Kiển', 786);
INSERT INTO `ward` VALUES (27649, 'Xã Phước Lộc', 786);
INSERT INTO `ward` VALUES (27652, 'Xã Nhơn Đức', 786);
INSERT INTO `ward` VALUES (27655, 'Xã Phú Xuân', 786);
INSERT INTO `ward` VALUES (27658, 'Xã Long Thới', 786);
INSERT INTO `ward` VALUES (27661, 'Xã Hiệp Phước', 786);
INSERT INTO `ward` VALUES (27664, 'Thị trấn Cần Thạnh', 787);
INSERT INTO `ward` VALUES (27667, 'Xã Bình Khánh', 787);
INSERT INTO `ward` VALUES (27670, 'Xã Tam Thôn Hiệp', 787);
INSERT INTO `ward` VALUES (27673, 'Xã An Thới Đông', 787);
INSERT INTO `ward` VALUES (27676, 'Xã Thạnh An', 787);
INSERT INTO `ward` VALUES (27679, 'Xã Long Hòa', 787);
INSERT INTO `ward` VALUES (27682, 'Xã Lý Nhơn', 787);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
