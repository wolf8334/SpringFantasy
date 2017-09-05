/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50719
Source Host           : 
Source Database       : dev

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-09-04 18:48:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dev_user
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_user`;
CREATE TABLE `t_dev_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `realname` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_dev_user
-- ----------------------------
INSERT INTO `t_dev_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员');
INSERT INTO `t_dev_user` VALUES ('2', 'finch', 'c041f0a44b95c6d5c7c3ff47e8ea9733', '宅叔');
INSERT INTO `t_dev_user` VALUES ('3', 'Reese', 'fd3295e0d701cf0fb1a7c0cd7aaca998', '四叔');
INSERT INTO `t_dev_user` VALUES ('4', 'sameen', '8093e7cf96a67d0efb294f72dfb25e2f', '肖妹');
INSERT INTO `t_dev_user` VALUES ('5', 'root', '63a9f0ea7bb98050796b649e85481845', '根妹');
