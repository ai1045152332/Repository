/*
Navicat MySQL Data Transfer

Source Server         : localhostZJY
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : cloud_note

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-05-16 08:45:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cn_group
-- ----------------------------
DROP TABLE IF EXISTS `cn_group`;
CREATE TABLE `cn_group` (
  `group_id` bigint(20) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `school_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_group
-- ----------------------------
INSERT INTO `cn_group` VALUES ('1001', '计算机科学与技术', '1');

-- ----------------------------
-- Table structure for cn_user
-- ----------------------------
DROP TABLE IF EXISTS `cn_user`;
CREATE TABLE `cn_user` (
  `user_id` int(11) NOT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gen_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `count` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_name_unique` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_user
-- ----------------------------
INSERT INTO `cn_user` VALUES ('1001', null, 'zhaojianyu', '123456', null, null, null, null, null, null);
INSERT INTO `cn_user` VALUES ('1002', null, 'admin', '123456', null, null, null, null, null, null);
