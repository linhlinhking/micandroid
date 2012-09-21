/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : pmonitor

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2012-03-29 16:15:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pm_department`
-- ----------------------------
DROP TABLE IF EXISTS `pm_department`;
CREATE TABLE `pm_department` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_department
-- ----------------------------
INSERT INTO `pm_department` VALUES ('1', 'CABU', 'CABU');
INSERT INTO `pm_department` VALUES ('2', 'NMTG', 'NMTG');
INSERT INTO `pm_department` VALUES ('4', 'MEBU', 'MEBU');

-- ----------------------------
-- Table structure for `pm_equipment`
-- ----------------------------
DROP TABLE IF EXISTS `pm_equipment`;
CREATE TABLE `pm_equipment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `OWNER` varchar(50) DEFAULT NULL,
  `LOCATION` varchar(50) DEFAULT NULL,
  `DESCRIPTION` text,
  `STATUS` tinyint(4) DEFAULT NULL,
  `ROOM_ID` int(11) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `DEPARTMENT_ID` int(11) DEFAULT NULL,
  `GROSSPOWER` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_equipment
-- ----------------------------
INSERT INTO `pm_equipment` VALUES ('1', '10K', 'shuaizha', 'c13', 'It just is a 10k...', '1', '1', '1', '1', '2500');
INSERT INTO `pm_equipment` VALUES ('2', 'D3-EPC3000-1', 'shuaizha', 'c13', 'D3-EPC3000-1', '1', '1', '1', '1', '3000');
INSERT INTO `pm_equipment` VALUES ('3', 'D3-DPC3000-1', 'shuzchen', 'c13', 'D3-DPC3000-1', '0', '1', '1', '1', '2500');
INSERT INTO `pm_equipment` VALUES ('4', 'D3-FPC3000-1', 'shuzchen', 'C16', 'D3-FPC3000-1', '0', '2', '2', '2', '2400');
INSERT INTO `pm_equipment` VALUES ('5', 'D3-GPC3000-1', 'shuaizha', 'C13', 'D3-GPC3000-1', '1', '1', '1', '1', '3600');
INSERT INTO `pm_equipment` VALUES ('6', 'D3-HPC3000-1', 'shuaizha', 'C12', 'D3-HPC3000-1', '0', '1', '1', '1', '3800');

-- ----------------------------
-- Table structure for `pm_group`
-- ----------------------------
DROP TABLE IF EXISTS `pm_group`;
CREATE TABLE `pm_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `ROOM_ID` int(11) DEFAULT NULL,
  `DESCRIPTION` text,
  `OWNER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_group
-- ----------------------------
INSERT INTO `pm_group` VALUES ('1', 'SH-CABLE-DMR', '1', 'SH-CABLE-DMR', 'shuai');
INSERT INTO `pm_group` VALUES ('2', 'SH-NMTG-GROUP', '2', 'SH-NMTG-GROUP', 'shuaizha');

-- ----------------------------
-- Table structure for `pm_powercycler`
-- ----------------------------
DROP TABLE IF EXISTS `pm_powercycler`;
CREATE TABLE `pm_powercycler` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `TYPE` tinyint(4) NOT NULL,
  `HOST` varchar(50) DEFAULT NULL,
  `OUTLET_FROM` tinyint(4) DEFAULT NULL,
  `OUTLET_TO` tinyint(4) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `LOGIN_USER` varchar(50) DEFAULT NULL,
  `LOGIN_PWD` varchar(50) DEFAULT NULL,
  `ENABLE_PWD` varchar(50) DEFAULT NULL,
  `PROTOCOL` tinyint(4) DEFAULT NULL,
  `POWER_ON` tinyint(4) DEFAULT NULL,
  `POWER_OFF` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_powercycler
-- ----------------------------
INSERT INTO `pm_powercycler` VALUES ('1', 'PowerCycler', '1', '192.168.0.1', '1', '8', '1', 'shuai', 'tkz', 'tkz', '1', '1', '0');

-- ----------------------------
-- Table structure for `pm_powercycler_equipment`
-- ----------------------------
DROP TABLE IF EXISTS `pm_powercycler_equipment`;
CREATE TABLE `pm_powercycler_equipment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POWERCYCLER_ID` int(11) DEFAULT NULL,
  `EQUIPMENT_ID` int(11) DEFAULT NULL,
  `OUTLET` tinyint(4) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_powercycler_equipment
-- ----------------------------
INSERT INTO `pm_powercycler_equipment` VALUES ('1', '1', '1', '1', '0');
INSERT INTO `pm_powercycler_equipment` VALUES ('3', '1', '1', '2', '1');
INSERT INTO `pm_powercycler_equipment` VALUES ('4', '1', '1', '3', '0');
INSERT INTO `pm_powercycler_equipment` VALUES ('5', '1', '1', '4', '0');
INSERT INTO `pm_powercycler_equipment` VALUES ('6', '1', '1', '5', '1');
INSERT INTO `pm_powercycler_equipment` VALUES ('7', '1', '1', '6', '0');
INSERT INTO `pm_powercycler_equipment` VALUES ('8', '1', '1', '7', '1');
INSERT INTO `pm_powercycler_equipment` VALUES ('9', '1', '1', '8', '0');

-- ----------------------------
-- Table structure for `pm_reserve`
-- ----------------------------
DROP TABLE IF EXISTS `pm_reserve`;
CREATE TABLE `pm_reserve` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RESERVER` varchar(50) DEFAULT NULL,
  `EQUIPMENT_ID` int(11) DEFAULT NULL,
  `START_TIME` varchar(50) DEFAULT NULL,
  `END_TIME` varchar(50) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_reserve
-- ----------------------------
INSERT INTO `pm_reserve` VALUES ('1', 'shuai', '1', '2012-02-29 17:29:59', '2012-03-08 17:30:04', '-1', 'testing...');
INSERT INTO `pm_reserve` VALUES ('2', 'shuaizha', '1', '2012-03-09 15:08:26', '2012-04-06 15:08:46', '1', 'testing');
INSERT INTO `pm_reserve` VALUES ('3', 'shuai', '2', '2012-03-14 22:25:48', '2012-04-07 22:25:52', '1', 'I wanna to reserve this device...');
INSERT INTO `pm_reserve` VALUES ('4', 'shuai', '5', '2012-03-13 22:27:15', '2012-05-03 22:27:19', '1', 'thank you....');
INSERT INTO `pm_reserve` VALUES ('5', 'shuai', '2', '2012-05-10 10:47:55', '2012-06-07 10:48:04', '0', 'testing...');
INSERT INTO `pm_reserve` VALUES ('6', 'shuai', '1', '2012-04-07 21:43:19', '2012-05-11 21:43:23', '0', 'Reserve equipment..');
INSERT INTO `pm_reserve` VALUES ('7', 'shuai', '1', '2012-05-17 22:07:54', '2012-05-25 22:07:59', '0', 'test');

-- ----------------------------
-- Table structure for `pm_room`
-- ----------------------------
DROP TABLE IF EXISTS `pm_room`;
CREATE TABLE `pm_room` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `LOCATION` varchar(100) DEFAULT NULL,
  `DESCRIPTION` text,
  `OWNER` varchar(50) DEFAULT NULL,
  `DEPARTMENT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_room
-- ----------------------------
INSERT INTO `pm_room` VALUES ('1', 'SH-CABLE-ROOM', 'C13', 'SH-CABLE-ROOM', 'shuai', '1');
INSERT INTO `pm_room` VALUES ('2', 'SH-NMTG-ROOM', 'C16', 'SH-NMTG-ROOM', 'shuaizha', '2');
INSERT INTO `pm_room` VALUES ('4', 'SH-PMONITOR-ROOM', 'C13', 'SH-PMONITOR-ROOM', 'shuzchen', '1');

-- ----------------------------
-- Table structure for `pm_user`
-- ----------------------------
DROP TABLE IF EXISTS `pm_user`;
CREATE TABLE `pm_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `FULLNAME` varchar(50) DEFAULT NULL,
  `ROLE` tinyint(4) DEFAULT NULL,
  `GMT_CREATE` varchar(50) DEFAULT NULL,
  `GMT_MODIFIED` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_user
-- ----------------------------
INSERT INTO `pm_user` VALUES ('3', 'shuai', 'tkz', 'shuaizha@cisco.com', 'Shuai Zhang', '1', '2012-02-21 17:07:35', '2012-02-21 17:07:35');
INSERT INTO `pm_user` VALUES ('4', 'shuaizha', '', 'shuaizha@cisco.com', 'Shuai Zhang', '0', '2012-02-21 23:12:00', '2012-02-21 23:12:00');

-- ----------------------------
-- Table structure for `pm_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `pm_user_group`;
CREATE TABLE `pm_user_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `ROLE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_user_group
-- ----------------------------
INSERT INTO `pm_user_group` VALUES ('1', 'shuai', '1', '1');
INSERT INTO `pm_user_group` VALUES ('2', 'shuai', '2', '1');

-- ----------------------------
-- Table structure for `pm_use_ratio`
-- ----------------------------
DROP TABLE IF EXISTS `pm_use_ratio`;
CREATE TABLE `pm_use_ratio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EQUIPMENT_ID` int(11) DEFAULT NULL,
  `USE_TIME` int(11) DEFAULT NULL,
  `IDLE_TIME` int(11) DEFAULT NULL,
  `USE_RATIO` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pm_use_ratio
-- ----------------------------
INSERT INTO `pm_use_ratio` VALUES ('1', '6', '0', '0', '0');
