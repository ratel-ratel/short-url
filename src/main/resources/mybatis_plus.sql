/*
Navicat MySQL Data Transfer

Source Server         : localMySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mybatis_plus

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-16 17:05:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `short_link`
-- ----------------------------
DROP TABLE IF EXISTS `short_link`;
CREATE TABLE `short_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) DEFAULT NULL COMMENT '平台编号',
  `link` varchar(1000) NOT NULL COMMENT '原链接',
  `short_link` varchar(100) NOT NULL COMMENT '短链接',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `created_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `updated_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '1' COMMENT '删除标识(1:正常  2:删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `short_link_index` (`short_link`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=974569842013188098 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of short_link
-- ----------------------------
INSERT INTO `short_link` VALUES ('1', '100000054', 'http://localhost:8089/report/post?request%3D1326%26token%3D123', 'http://localhost:8089/ArmUNz', '906486871133863936', '1521188914716', '906486871133863936', '1521188914716', '1');
INSERT INTO `short_link` VALUES ('974569065718804482', '100000054', 'http://localhost:8089/report/post?request%3D%E4%BD%A0%E5%A5%BD%E4%B8%96%E7%95%8C', 'http://localhost:8089/mqYziq', '906486871133863936', '1521190346157', '906486871133863936', '1521190346157', '1');
INSERT INTO `short_link` VALUES ('974569385635147777', '100000054', 'http://localhost:8089/report/post?request%3Dhello', 'http://localhost:8089/vieqQj', '906486871133863936', '1521190422816', '906486871133863936', '1521190422816', '1');
INSERT INTO `short_link` VALUES ('974569842013188097', '100000054', 'http://localhost:8089/sendRedirect/hello', 'http://localhost:8089/AnYvmm', '906486871133863936', '1521190531253', '906486871133863936', '1521190531253', '1');

-- ----------------------------
-- Table structure for `short_url`
-- ----------------------------
DROP TABLE IF EXISTS `short_url`;
CREATE TABLE `short_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `long_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '长链接',
  `short_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '短链编码',
  `short_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '短链',
  `clicks` int(11) DEFAULT NULL COMMENT '点击数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of short_url
-- ----------------------------
INSERT INTO `short_url` VALUES ('7', 'localhost:8080/report/post?request=1326&token=123', 'http://localhost:8089/rIZrMb', 'http://localhost:8089/rIZrMb', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(20) NOT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `deleted` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'sky', 'sky', '1504168031798', '678', '1504168031798', '0', '0');
INSERT INTO `user` VALUES ('2', 'nice', 'nice', '1504169134487', '678', '1504169134487', '0', '0');
INSERT INTO `user` VALUES ('3', 'nice', 'nice', '1504169148827', '678', '1504169148827', '0', '0');
INSERT INTO `user` VALUES ('4', 'sky', 'sky', '1513824945911', '678', '1513824945911', '678', '1');
INSERT INTO `user` VALUES ('5', 'boots', 'root', '1513825141719', '6789', '1513825141719', '6789', '1');
