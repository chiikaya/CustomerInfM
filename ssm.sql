/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-05 18:38:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for contact_record
-- ----------------------------
DROP TABLE IF EXISTS `contact_record`;
CREATE TABLE `contact_record` (
  `record_id` int(5) NOT NULL AUTO_INCREMENT,
  `client_id` int(5) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reply_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `record_type` varchar(20) NOT NULL,
  `record_who` int(5) DEFAULT NULL,
  `record_text` varchar(30) NOT NULL,
  `record_desc` varchar(30) DEFAULT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact_record
-- ----------------------------
INSERT INTO `contact_record` VALUES ('1', '1', '2018-01-04 09:13:45', '2018-01-25 09:13:48', '送礼品', '1', '成年礼', '好想长大', '0');
INSERT INTO `contact_record` VALUES ('4', '2', '2018-01-04 11:10:34', '2018-01-13 09:39:52', '聚会', '3', '同学聚会', '玩嗨', '0');

-- ----------------------------
-- Table structure for contcat_person
-- ----------------------------
DROP TABLE IF EXISTS `contcat_person`;
CREATE TABLE `contcat_person` (
  `person_id` int(5) NOT NULL AUTO_INCREMENT,
  `person_customer` int(5) NOT NULL,
  `person_name` varchar(30) NOT NULL,
  `person_sex` int(10) DEFAULT '0' COMMENT '性别  0：男   1：女',
  `person_age` int(10) DEFAULT NULL,
  `person_post` varchar(20) DEFAULT NULL,
  `person_tel` varchar(11) DEFAULT NULL,
  `customer_nexus` varchar(30) DEFAULT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contcat_person
-- ----------------------------
INSERT INTO `contcat_person` VALUES ('1', '1', '华纳', '0', '35', '项目经理', '123456', '上下属', '0');
INSERT INTO `contcat_person` VALUES ('2', '4', '赵六', '1', '20', '普通员工', '111', '上下属', '0');
INSERT INTO `contcat_person` VALUES ('3', '2', '22', '1', '22', '降妖', '119', '上下属', '0');

-- ----------------------------
-- Table structure for house_info
-- ----------------------------
DROP TABLE IF EXISTS `house_info`;
CREATE TABLE `house_info` (
  `info_id` int(5) NOT NULL AUTO_INCREMENT,
  `type_id` int(10) DEFAULT NULL,
  `staff_id` int(5) DEFAULT NULL,
  `info_adress` varchar(10) DEFAULT NULL,
  `info_price` double(10,0) DEFAULT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house_info
-- ----------------------------
INSERT INTO `house_info` VALUES ('1', '1', '1', '上海', '10000', '0');
INSERT INTO `house_info` VALUES ('2', '2', '2', '济宁', '500', '0');

-- ----------------------------
-- Table structure for house_type
-- ----------------------------
DROP TABLE IF EXISTS `house_type`;
CREATE TABLE `house_type` (
  `type_id` int(5) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(30) NOT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house_type
-- ----------------------------
INSERT INTO `house_type` VALUES ('1', '四室两厅', '0');
INSERT INTO `house_type` VALUES ('2', '三室一厅2', '0');
INSERT INTO `house_type` VALUES ('3', '一室一厅一厨一卫', '0');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(5) NOT NULL AUTO_INCREMENT,
  `notice_person` int(10) DEFAULT NULL,
  `notice_text` varchar(30) DEFAULT NULL,
  `notice_content` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reply_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '1', '长生不老', '吃唐僧肉', '2018-01-05 11:01:27', '2018-02-01 09:15:59', '0');
INSERT INTO `notice` VALUES ('2', '2', '开大会', '表彰大会', '2018-01-05 11:01:31', '2018-02-01 09:15:59', '0');
INSERT INTO `notice` VALUES ('3', '3', '1', '1', '2018-01-29 11:02:18', '2018-01-02 11:02:23', '0');
INSERT INTO `notice` VALUES ('5', '2', '2', '2', '2018-01-05 11:04:09', '2018-01-07 11:04:01', '1');

-- ----------------------------
-- Table structure for staff_desc
-- ----------------------------
DROP TABLE IF EXISTS `staff_desc`;
CREATE TABLE `staff_desc` (
  `staff_id` int(10) NOT NULL AUTO_INCREMENT,
  `staff_name` varchar(50) NOT NULL,
  `staff_age` varchar(10) NOT NULL,
  `staff_sex` int(10) DEFAULT '0' COMMENT '性别 0：男   1：女',
  `staff_nation` varchar(10) NOT NULL,
  `staff_dept` int(10) DEFAULT NULL,
  `staff_role` int(10) DEFAULT NULL,
  `staff_degree` varchar(10) DEFAULT NULL,
  `staff_marital` int(10) DEFAULT '0' COMMENT '婚姻   0：未婚    1：已婚',
  `staff_address` varchar(10) DEFAULT NULL,
  `staff_phone` varchar(20) DEFAULT NULL,
  `staff_tel` varchar(10) DEFAULT NULL,
  `staff_email` varchar(50) DEFAULT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff_desc
-- ----------------------------
INSERT INTO `staff_desc` VALUES ('1', '赵六', '35', '0', '汉', '1', '4', '群众', '0', '济宁', '111', '111', '111@qq.com', '0');
INSERT INTO `staff_desc` VALUES ('2', '小七', '17', '1', '大汉', '3', '8', '本科', '0', '山东', '123321', '123', '123321@qq.com', '0');
INSERT INTO `staff_desc` VALUES ('3', '聂八', '18', '0', '朝鲜', '5', '4', '硕士', '1', '台湾', '120', '110', '119@qq.com', '0');

-- ----------------------------
-- Table structure for sys_allot
-- ----------------------------
DROP TABLE IF EXISTS `sys_allot`;
CREATE TABLE `sys_allot` (
  `allot_id` int(5) NOT NULL AUTO_INCREMENT,
  `allot_name` varchar(30) NOT NULL,
  `allot_inuse` int(10) DEFAULT '1' COMMENT '客户状态 1：潜在客户   2：意向客户  3：交易客户',
  `allot_source` int(10) DEFAULT '1' COMMENT '客户来源  1：自己上门   2：朋友推荐 ',
  `allot_type` int(10) DEFAULT '1' COMMENT '客户类型  1：合作伙伴   2：供应商',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `company` varchar(30) NOT NULL,
  PRIMARY KEY (`allot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_allot
-- ----------------------------
INSERT INTO `sys_allot` VALUES ('1', '	\r\n庆心', '1', '1', '1', '2017-12-26 09:29:19', '海尔');
INSERT INTO `sys_allot` VALUES ('2', '阿瑟斯', '2', '2', '2', '2016-01-01 01:01:00', '山东');
INSERT INTO `sys_allot` VALUES ('3', '奥利奥', '1', '2', '1', '2018-01-01 12:54:30', '澳洲');

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth` (
  `auth_id` int(5) NOT NULL AUTO_INCREMENT,
  `auth_name` varchar(20) NOT NULL,
  `auth_desc` varchar(50) DEFAULT NULL,
  `auth_layer` int(1) NOT NULL,
  `auth_url` varchar(30) DEFAULT NULL,
  `auth_order` int(3) NOT NULL,
  `parent_id` int(5) DEFAULT NULL,
  `auth_type` int(1) DEFAULT '0' COMMENT '权限类型  0：模块    1: 资源',
  `inuse` int(1) DEFAULT '0' COMMENT '是否再用  0：可用    1：禁用',
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES ('1', '客户相关', '客户相关信息', '1', '', '10', '-1', '0', '0');
INSERT INTO `sys_auth` VALUES ('2', '员工相关', '员工相关信息', '1', '', '20', '-1', '0', '0');
INSERT INTO `sys_auth` VALUES ('3', '管理员', '部门、用户、角色、权限管理', '1', '', '30', '-1', '0', '0');
INSERT INTO `sys_auth` VALUES ('4', '用户管理', '管理用户', '2', 'user/toMain', '10', '3', '0', '0');
INSERT INTO `sys_auth` VALUES ('5', '角色管理', '管理角色', '2', 'role/toMain', '20', '3', '0', '0');
INSERT INTO `sys_auth` VALUES ('6', '权限管理', '管理权限', '2', 'auth/toMain', '30', '3', '0', '0');
INSERT INTO `sys_auth` VALUES ('7', '部门管理', '管理部门', '3', 'dept/toMain', '50', '3', '0', '0');
INSERT INTO `sys_auth` VALUES ('8', '右键单击添加权限', '添加权限', '3', 'addAuth', '10', '6', '1', '0');
INSERT INTO `sys_auth` VALUES ('9', '右键单击编辑权限', '修改权限', '3', 'editAuth', '20', '6', '1', '0');
INSERT INTO `sys_auth` VALUES ('10', '右键单击刷新权限', '刷新权限', '3', 'refreshAuth', '30', '6', '1', '0');
INSERT INTO `sys_auth` VALUES ('11', '添加角色', '添加角色', '3', 'addRole', '10', '5', '1', '0');
INSERT INTO `sys_auth` VALUES ('12', '授权', '授权', '3', 'empowerButton', '20', '5', '1', '0');
INSERT INTO `sys_auth` VALUES ('13', '客户信息', '客户信息', '2', 'client/toMain', '10', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('14', '客户分配', '客户分配', '2', 'allot/toMain', '20', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('15', '客户关怀', '客户关怀', '2', 'thought/toMain', '30', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('16', '客户类型', '客户类型', '2', 'mold/toMain', '40', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('17', '客户状态', '客户状态', '2', 'status/toMain', '50', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('18', '客户来源', '客户来源', '2', 'from/toMain', '60', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('19', '联系记录', '联系记录', '2', 'record/toMain', '70', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('20', '联系人', '联系人', '2', 'person/toMain', '80', '1', '0', '0');
INSERT INTO `sys_auth` VALUES ('21', '员工信息', '员工信息', '2', 'desc/toMain', '10', '2', '0', '0');
INSERT INTO `sys_auth` VALUES ('22', '房屋信息', '房屋信息', '2', 'info/toMain', '20', '2', '0', '0');
INSERT INTO `sys_auth` VALUES ('23', '房屋类型', '房屋类型', '2', 'type/toMain', '30', '2', '0', '0');
INSERT INTO `sys_auth` VALUES ('24', '公     告', '公告', '2', 'notice/toMain', '40', '2', '0', '0');

-- ----------------------------
-- Table structure for sys_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client` (
  `client_id` int(5) NOT NULL AUTO_INCREMENT,
  `clientname` varchar(30) NOT NULL,
  `status_id` int(5) DEFAULT NULL,
  `from_id` int(5) DEFAULT NULL,
  `staff_id` int(5) DEFAULT NULL,
  `mold_id` int(5) DEFAULT NULL,
  `sex` int(10) DEFAULT '0' COMMENT '性别   0：男   1：女',
  `tel` varchar(11) NOT NULL,
  `QQ` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `post` varchar(20) NOT NULL,
  `company` varchar(30) NOT NULL,
  `inuse` int(1) DEFAULT '0',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_client
-- ----------------------------
INSERT INTO `sys_client` VALUES ('1', '张三', '1', '2', '1', '2', '0', '26', '55', '11@qq.com', '上下属', '创意', '0');
INSERT INTO `sys_client` VALUES ('2', '妖榴', '2', '2', '2', '1', '1', '123', '3215', '123', '经理', '海天酒楼', '0');
INSERT INTO `sys_client` VALUES ('3', '王八', '2', '1', '2', '2', '0', '123', '11', '1', '1', '小虎', '0');
INSERT INTO `sys_client` VALUES ('4', '李四', '3', '3', '1', '333', '0', '1', '1', '1', '1', '天街', '0');
INSERT INTO `sys_client` VALUES ('5', '1', '2', '1', '1', '1', '0', '1', '1', '1', '1', '海天', '0');
INSERT INTO `sys_client` VALUES ('6', '2', '3', '3', '3', '1', '1', '2', '2', '2', '2', '2', '0');
INSERT INTO `sys_client` VALUES ('7', '3', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0');
INSERT INTO `sys_client` VALUES ('8', '4', '3', '2', '1', '1', '1', '3', '3', '3', '3', '3', '0');
INSERT INTO `sys_client` VALUES ('9', '5', '1', '3', '1', '2', '1', '1', '1', '1', '1', '1', '0');
INSERT INTO `sys_client` VALUES ('10', '6', '1', '1', '1', '1', '0', '5', '5', '5', '5', '5', '0');
INSERT INTO `sys_client` VALUES ('11', '7', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '0');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(5) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(30) NOT NULL,
  `dept_desc` varchar(20) DEFAULT NULL,
  `dept_location` varchar(50) NOT NULL,
  `inuse` int(1) DEFAULT '0',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('0', '无', '无', '无', '1');
INSERT INTO `sys_dept` VALUES ('1', '软件开发', '活学活用', '济宁高新区', '0');
INSERT INTO `sys_dept` VALUES ('2', '软件开发', '活学活用', '济宁高新区', '1');
INSERT INTO `sys_dept` VALUES ('3', '财务部', '财务管理', '北京', '0');
INSERT INTO `sys_dept` VALUES ('4', '销售部', '能力极强', '天津', '0');
INSERT INTO `sys_dept` VALUES ('5', '市场部', '市场调查', '济宁', '0');
INSERT INTO `sys_dept` VALUES ('6', '测试部', '1', '1', '0');
INSERT INTO `sys_dept` VALUES ('9', '测试部', '测试部', '1', '1');

-- ----------------------------
-- Table structure for sys_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_user`;
CREATE TABLE `sys_dept_user` (
  `dbid` int(5) NOT NULL AUTO_INCREMENT,
  `dept_id` int(5) DEFAULT NULL,
  `user_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_from
-- ----------------------------
DROP TABLE IF EXISTS `sys_from`;
CREATE TABLE `sys_from` (
  `from_id` int(5) NOT NULL AUTO_INCREMENT,
  `from_name` varchar(30) NOT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`from_id`)
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_from
-- ----------------------------
INSERT INTO `sys_from` VALUES ('1', '自己上门', '0');
INSERT INTO `sys_from` VALUES ('2', '朋友推荐', '0');
INSERT INTO `sys_from` VALUES ('3', '22', '0');

-- ----------------------------
-- Table structure for sys_mold
-- ----------------------------
DROP TABLE IF EXISTS `sys_mold`;
CREATE TABLE `sys_mold` (
  `mold_id` int(5) NOT NULL AUTO_INCREMENT,
  `mold_name` varchar(30) NOT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`mold_id`)
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_mold
-- ----------------------------
INSERT INTO `sys_mold` VALUES ('1', '合作伙伴', '0');
INSERT INTO `sys_mold` VALUES ('2', '供应商', '0');
INSERT INTO `sys_mold` VALUES ('3', '22', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  `role_desc` varchar(20) DEFAULT NULL,
  `role_order` int(3) NOT NULL,
  `inuse` int(1) DEFAULT '0' COMMENT '是否再用  0：可用    1：禁用',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '系统内部维护,具有最高权限', '10', '0');
INSERT INTO `sys_role` VALUES ('3', '财务经理', '财务经理,复制财务,具有部分权限', '30', '0');
INSERT INTO `sys_role` VALUES ('4', '普通员工', '具有少部分权限', '40', '0');
INSERT INTO `sys_role` VALUES ('5', '普通员工1', '普通员工1', '50', '0');
INSERT INTO `sys_role` VALUES ('6', '普通员工11', '普通员工11', '60', '0');
INSERT INTO `sys_role` VALUES ('7', '普通员工111', '普通员工111', '110', '0');
INSERT INTO `sys_role` VALUES ('8', '普通员工2', '普通员工20', '80', '0');

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth` (
  `dbid` int(5) NOT NULL AUTO_INCREMENT,
  `role_id` int(5) DEFAULT NULL,
  `auth_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES ('12', '5', '1');
INSERT INTO `sys_role_auth` VALUES ('50', '4', '1');
INSERT INTO `sys_role_auth` VALUES ('51', '4', '9');
INSERT INTO `sys_role_auth` VALUES ('70', '3', '1');
INSERT INTO `sys_role_auth` VALUES ('71', '3', '2');
INSERT INTO `sys_role_auth` VALUES ('72', '3', '4');
INSERT INTO `sys_role_auth` VALUES ('73', '3', '11');
INSERT INTO `sys_role_auth` VALUES ('74', '3', '6');
INSERT INTO `sys_role_auth` VALUES ('75', '3', '8');
INSERT INTO `sys_role_auth` VALUES ('76', '3', '9');
INSERT INTO `sys_role_auth` VALUES ('80', '4', '13');
INSERT INTO `sys_role_auth` VALUES ('81', '4', '14');
INSERT INTO `sys_role_auth` VALUES ('82', '4', '15');
INSERT INTO `sys_role_auth` VALUES ('83', '4', '16');
INSERT INTO `sys_role_auth` VALUES ('84', '4', '17');
INSERT INTO `sys_role_auth` VALUES ('85', '4', '18');
INSERT INTO `sys_role_auth` VALUES ('86', '4', '19');
INSERT INTO `sys_role_auth` VALUES ('87', '4', '20');
INSERT INTO `sys_role_auth` VALUES ('92', '1', '1');
INSERT INTO `sys_role_auth` VALUES ('93', '1', '13');
INSERT INTO `sys_role_auth` VALUES ('94', '1', '14');
INSERT INTO `sys_role_auth` VALUES ('95', '1', '15');
INSERT INTO `sys_role_auth` VALUES ('96', '1', '16');
INSERT INTO `sys_role_auth` VALUES ('97', '1', '17');
INSERT INTO `sys_role_auth` VALUES ('98', '1', '18');
INSERT INTO `sys_role_auth` VALUES ('99', '1', '19');
INSERT INTO `sys_role_auth` VALUES ('100', '1', '20');
INSERT INTO `sys_role_auth` VALUES ('101', '1', '2');
INSERT INTO `sys_role_auth` VALUES ('102', '1', '21');
INSERT INTO `sys_role_auth` VALUES ('103', '1', '22');
INSERT INTO `sys_role_auth` VALUES ('104', '1', '23');
INSERT INTO `sys_role_auth` VALUES ('105', '1', '4');
INSERT INTO `sys_role_auth` VALUES ('106', '1', '5');
INSERT INTO `sys_role_auth` VALUES ('107', '1', '11');
INSERT INTO `sys_role_auth` VALUES ('108', '1', '12');
INSERT INTO `sys_role_auth` VALUES ('109', '1', '8');
INSERT INTO `sys_role_auth` VALUES ('110', '1', '7');
INSERT INTO `sys_role_auth` VALUES ('111', '1', '3');
INSERT INTO `sys_role_auth` VALUES ('112', '1', '6');
INSERT INTO `sys_role_auth` VALUES ('113', '1', '24');

-- ----------------------------
-- Table structure for sys_status
-- ----------------------------
DROP TABLE IF EXISTS `sys_status`;
CREATE TABLE `sys_status` (
  `status_id` int(5) NOT NULL AUTO_INCREMENT,
  `status_inuse` varchar(30) NOT NULL,
  `status_desc` varchar(30) DEFAULT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=334 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_status
-- ----------------------------
INSERT INTO `sys_status` VALUES ('1', '潜在客户', '可能成为客户的人', '0');
INSERT INTO `sys_status` VALUES ('2', '意向客户', '有意愿车成为客户的人', '0');
INSERT INTO `sys_status` VALUES ('3', '交易客户', '正在交易的客户', '0');
INSERT INTO `sys_status` VALUES ('333', '55', '2', '1');

-- ----------------------------
-- Table structure for sys_thought
-- ----------------------------
DROP TABLE IF EXISTS `sys_thought`;
CREATE TABLE `sys_thought` (
  `thought_id` int(5) NOT NULL AUTO_INCREMENT,
  `client_id` int(5) DEFAULT NULL,
  `thought_text` varchar(30) NOT NULL,
  `thought_way` varchar(30) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reply_time` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `thought_desc` varchar(30) DEFAULT NULL,
  `person` varchar(30) DEFAULT NULL,
  `inuse` int(5) DEFAULT '0',
  PRIMARY KEY (`thought_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_thought
-- ----------------------------
INSERT INTO `sys_thought` VALUES ('1', '2', '1', '2', '2017-12-29 10:58:27', '2018-01-02 10:58:31', '放假', '阿西', '0');
INSERT INTO `sys_thought` VALUES ('3', '1', '研发', '组团', '2018-01-03 14:12:41', '2018-01-10 14:12:44', '灌快', '八戒', '0');
INSERT INTO `sys_thought` VALUES ('4', '1', '昔年', '送礼', '2018-01-02 02:59:25', '2018-01-30 02:59:28', '1', '悟空', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `email` varchar(20) NOT NULL,
  `inuse` int(1) DEFAULT '0' COMMENT '是否再用  0：可用    1：禁用',
  `dept_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `dept_id` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '15763782603', '2476810979@qq.com', '0', '0');
INSERT INTO `sys_user` VALUES ('2', 'boss', 'boss', '15053795859', '1476810979@qq.com', '0', '2');
INSERT INTO `sys_user` VALUES ('3', 'manager', 'manager', '17863782603', '2345610979@qq.com', '0', '3');
INSERT INTO `sys_user` VALUES ('4', 'low', 'low', '15763786666', '2222684385@qq.com', '0', '4');
INSERT INTO `sys_user` VALUES ('5', '1', '1', '1', '111@gr.com', '0', '1');
INSERT INTO `sys_user` VALUES ('6', '2', '2', '2', '222@qq.com', '0', '2');
INSERT INTO `sys_user` VALUES ('7', 'gao', 'gao', '123456', '123456@163.com', '0', '5');
INSERT INTO `sys_user` VALUES ('8', '8', '8', '8', '8', '0', '4');
INSERT INTO `sys_user` VALUES ('9', '9', '9', '9', '9', '0', '4');
INSERT INTO `sys_user` VALUES ('10', '10', '10', '10', '10', '0', '0');
INSERT INTO `sys_user` VALUES ('11', '11', '11', '11', '11', '0', '1');
INSERT INTO `sys_user` VALUES ('19', '6', '6', '6', '6', '0', '3');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `dbid` int(5) NOT NULL AUTO_INCREMENT,
  `user_id` int(5) DEFAULT NULL,
  `role_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`dbid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1', '3');
INSERT INTO `sys_user_role` VALUES ('4', '1', '4');
INSERT INTO `sys_user_role` VALUES ('5', '2', '1');
INSERT INTO `sys_user_role` VALUES ('8', '2', '2');
INSERT INTO `sys_user_role` VALUES ('9', '3', '2');
INSERT INTO `sys_user_role` VALUES ('10', '3', '4');
INSERT INTO `sys_user_role` VALUES ('11', '3', '5');
