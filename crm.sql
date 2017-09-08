/*
Navicat MySQL Data Transfer

Source Server         : 127
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2017-01-09 14:54:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `sid` varchar(255) NOT NULL,
  `iname` varchar(255) DEFAULT NULL,
  `imessages` varchar(255) DEFAULT NULL,
  `iprice` double(20,0) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('21', '鲜花玫瑰 - 星座经典系列', '摩羯座  11支', '1520', 'img/1.png');
INSERT INTO `item` VALUES ('31', '鲜花玫瑰 - 恒久真爱系列', '小恒久朱砂  19支', '2980', 'http://www.roseonly.com.cn/upload/products/14818833350439890.png');
INSERT INTO `item` VALUES ('33', '黑暗玫瑰 - 测试用例', '黑暗玫瑰  200支', '1999', 'img/2.png');
INSERT INTO `item` VALUES ('42', '鲜花玫瑰 - 经典永续系列', '经典约定  19枝', '1564', 'http://www.roseonly.com.cn/upload/products/14823111709791472.png');
INSERT INTO `item` VALUES ('45', '鲜花玫瑰 - 经典永续系列', '经典许愿  11支', '1314', 'http://www.roseonly.com.cn/upload/products/14798703424216731.png');
INSERT INTO `item` VALUES ('51', '鲜花玫瑰 - 经典永续系列', '经典惊艳  19支', '7878', 'http://www.roseonly.com.cn/upload/products/14797278698933869.png');
INSERT INTO `item` VALUES ('56', '鲜花玫瑰 - 经典永续系列', '经典初心  11支', '6341', 'http://www.roseonly.com.cn/upload/products/14351399518161012.png');
INSERT INTO `item` VALUES ('78', '鲜花玫瑰 - 星座经典系列', '摩羯座  19支', '4399', 'http://www.roseonly.com.cn/upload/products/14823875466099670.png');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` varchar(255) NOT NULL,
  `uid` varchar(255) NOT NULL,
  `items` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1233211234567', 'eeb822d01a8c4c29b792efc67e711083', '33~1@21~1@');
INSERT INTO `orders` VALUES ('1233211852748', 'eeb822d01a8c4c29b792efc67e711083', '33~9@21~4@');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(255) NOT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `psd` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `cart_item` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('01aeca97e79d497791b7ee88d71c2e48', 's', 's', null, null, 'beijing');
INSERT INTO `user` VALUES ('1', 'shange', 'shange', null, null, 'beijing');
INSERT INTO `user` VALUES ('3d5fc8556f0f4abfbb89e8ce85ade842', 'ko', 'kok', null, null, 'beijing');
INSERT INTO `user` VALUES ('49d4a449105549b4b6b932ba9427e483', '123', '123', null, null, '长春大学');
INSERT INTO `user` VALUES ('6cb95fb9d7254e79aa1015d00b47f62f', 'shuaiqide', '1', null, null, 'beijing');
INSERT INTO `user` VALUES ('937ea8445b3d44fd884c5a5ac27c4e3d', 'o', 'o', null, null, 'beijing');
INSERT INTO `user` VALUES ('a8adb31d43754ec296c88f3a60693cca', 'haha', '2', null, null, 'beijing');
INSERT INTO `user` VALUES ('cd6a846598f140b38d0ba528659b5d8b', 'aaaaaa', 'aaaaa', null, null, 'beijing');
INSERT INTO `user` VALUES ('dda2921b078046ba968fe1d6996a5864', 'shange1', 'shange', null, null, 'beijing');
INSERT INTO `user` VALUES ('e176183d1da2499381a188f4a3f78e79', 'jojo', 'jojo', null, null, 'beijing');
INSERT INTO `user` VALUES ('eeb822d01a8c4c29b792efc67e711083', 'jiji', 'kk', null, '33~1@56~1@', 'guangzhou');
