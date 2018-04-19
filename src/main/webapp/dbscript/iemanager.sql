/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.28-cdb2016-log : Database - iemanager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iemanager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `iemanager`;

/*Table structure for table `t_email` */

DROP TABLE IF EXISTS `t_email`;

CREATE TABLE `t_email` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `system_no` bit(2) NOT NULL COMMENT '系统标识',
  `email_no` varchar(100) NOT NULL COMMENT '邮件编号',
  `email_to` varchar(100) NOT NULL COMMENT '收件人地址',
  `email_touser` varchar(100) NOT NULL COMMENT '收件人',
  `email_carboncopy` varchar(100) DEFAULT NULL COMMENT '抄送',
  `email_title` varchar(100) NOT NULL COMMENT '主题',
  `email_content` varchar(100) NOT NULL COMMENT '内容',
  `email_from` varchar(100) NOT NULL COMMENT '发件人地址',
  `email_fromuser` varchar(100) NOT NULL COMMENT '收件人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `email_status` int(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_email` */

insert  into `t_email`(`id`,`system_no`,`email_no`,`email_to`,`email_touser`,`email_carboncopy`,`email_title`,`email_content`,`email_from`,`email_fromuser`,`create_time`,`send_time`,`email_status`) values (1,'','223132','3094759846@qq.com','华浩','','台风“天鸽”来袭，请做好预防准备','台风“天鸽”来袭，请做好预防准备，各单位、各部门停业。','hao707789@163.com','张三','2017-08-24 12:16:05','2017-08-24 12:16:11',1);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu` varchar(100) NOT NULL COMMENT '菜单',
  `parent_menu` varchar(100) DEFAULT NULL COMMENT '上级菜单',
  `menu_desc` varchar(100) DEFAULT NULL COMMENT '菜单描述',
  `menu_icon` varchar(100) NOT NULL COMMENT '菜单图标',
  `menu_type` int(1) NOT NULL COMMENT '菜单类型：1、菜单；2、按钮',
  `menu_scope` int(1) NOT NULL COMMENT '菜单范围：1、系统级；2、普通级',
  `status` int(1) NOT NULL COMMENT '状态：1、启用；2、禁用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`menu_name`,`menu`,`parent_menu`,`menu_desc`,`menu_icon`,`menu_type`,`menu_scope`,`status`,`id`) values ('系统管理','','','系统管理','&#xe631;',1,1,1,1),('用户管理','./system/userIndex.shtml','1','用户管理','&#xe612;',1,1,1,2),('消息管理','','','消息管理','&#xe611;',1,1,1,5),('邮件管理','./message/goEmailIndex.shtml','5','邮件管理','&#xe606;',1,1,1,6),('报表管理','','','报表管理','&#xe629;',1,1,1,7),('角色管理','./system/roleIndex.shtml','1','角色管理','&#xe64f;',1,1,1,20),('资源管理','./system/goResource.shtml','1','资源管理','&#xe60f;',1,1,1,21),('菜单管理','./system/goMenuIndex.shtml','1','菜单管理','&#xe649;',1,1,1,22),('组织管理','./system/goOrganizationIndex.shtml','1','组织管理','&#xe628;',1,1,1,23),('公告管理','./message/goNoticeIndex.shtml','5','公告管理','&#xe645;',1,1,1,24),('短信管理','./message/goSmsIndex.shtml','5','短信管理','&#xe63b;',1,1,1,25);

/*Table structure for table `t_notice` */

DROP TABLE IF EXISTS `t_notice`;

CREATE TABLE `t_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `notice_title` varchar(100) NOT NULL COMMENT '标题',
  `notice_content` varchar(100) NOT NULL COMMENT '内容',
  `notice_from` varchar(100) NOT NULL COMMENT '来源',
  `notice_to` varchar(100) NOT NULL COMMENT '发送目的',
  `status` int(11) NOT NULL COMMENT '状态：1、未发布；2、已发布',
  `noice_scope` int(1) NOT NULL COMMENT '公告范围：1、系统级；2、普通级',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(100) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_notice` */

insert  into `t_notice`(`id`,`notice_title`,`notice_content`,`notice_from`,`notice_to`,`status`,`noice_scope`,`create_time`,`create_user`) values (1,'123','12','123','123',2,1,'2017-08-23 17:30:52','admin'),(2,'66666666','666666666','666666666666','666666666666',2,1,'2017-08-23 18:44:29','admin'),(4,'ggggg','gggggggggggggg','gggggggggggg','ggggggggggg',2,1,'2017-08-23 18:44:59','admin'),(6,'2222','88888888888','88888888888','88888888888',2,1,'2017-08-23 18:49:30','admin');

/*Table structure for table `t_organization` */

DROP TABLE IF EXISTS `t_organization`;

CREATE TABLE `t_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `organization` varchar(100) NOT NULL COMMENT '组织编号',
  `organization_parent` varchar(100) DEFAULT NULL COMMENT '上级组织',
  `organization_name` varchar(100) NOT NULL COMMENT '组织名称',
  `organization_desc` varchar(500) NOT NULL COMMENT '组织描述',
  `organization_innerphone` bigint(11) DEFAULT NULL COMMENT '内部电话',
  `organization_outerphone` bigint(11) DEFAULT NULL COMMENT '外部电话',
  `organization_address` varchar(100) NOT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user` varchar(100) NOT NULL COMMENT '创建用户',
  `organization_scope` int(11) NOT NULL COMMENT '组织范围：1、系统级；2、普通级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `t_organization` */

insert  into `t_organization`(`id`,`organization`,`organization_parent`,`organization_name`,`organization_desc`,`organization_innerphone`,`organization_outerphone`,`organization_address`,`create_time`,`modify_time`,`create_user`,`organization_scope`) values (23,'mds','0','迈鼎盛信息技术有限公司','',NULL,NULL,' 广东省深圳市龙岗区龙岗中心城龙岗路9号','2017-08-22 10:12:23',NULL,'admin',1),(24,'development','mds','研发中心','',NULL,NULL,'研发中心','2017-08-22 10:13:40',NULL,'admin',1),(25,'Marketing','mds','市场部','打市场的',NULL,NULL,'市场部','2017-08-22 10:15:22',NULL,'admin',1);

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源 ID',
  `resource_parent` varchar(100) DEFAULT NULL COMMENT '上级资源，可有可无',
  `resource` varchar(100) NOT NULL COMMENT '资源，可以是地址，也可以是js方法',
  `resource_name` varchar(100) NOT NULL COMMENT '资源名称，给资源取个名字',
  `resource_desc` varchar(100) DEFAULT NULL COMMENT '名称不够表达的时候，用上我吧',
  `resource_type` int(1) NOT NULL COMMENT '资源类型：1、菜单地址；2、按钮',
  `resource_scope` int(1) NOT NULL COMMENT '作用域：1、系统级，表示是该后面管理系统的资源；2、普通级，表示是其它项目的资源',
  `status` int(1) NOT NULL COMMENT '状态：1、启用；2、禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `t_resource` */

insert  into `t_resource`(`id`,`resource_parent`,`resource`,`resource_name`,`resource_desc`,`resource_type`,`resource_scope`,`status`,`create_time`) values (22,'','/system/userIndex.sthml','用户管理','用户管理',1,1,1,'2017-08-17 20:51:55'),(23,'','/system/roleIndex.sthml','角色管理','角色管理',1,1,1,'2017-08-17 20:52:22');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色表 ID',
  `role` varchar(20) NOT NULL COMMENT '角色',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '状态：1、启用；2、禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `role_type` int(1) NOT NULL COMMENT '角色类型：0、普通角色；1、系统角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role`,`role_name`,`role_desc`,`status`,`create_time`,`role_type`) values (1,'admin','系统管理员','系统管理员',1,'2017-08-16 17:05:55',1),(17,'root','超级管理员','超级管理员',1,'2017-08-16 17:06:07',1),(18,'student','学生','学生',1,'2017-08-16 17:08:52',1);

/*Table structure for table `t_role_resource` */

DROP TABLE IF EXISTS `t_role_resource`;

CREATE TABLE `t_role_resource` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '角色资源关联 ID',
  `role_id` tinyint(4) NOT NULL COMMENT '角色 id',
  `resource_id` tinyint(4) NOT NULL COMMENT '资源 id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

/*Data for the table `t_role_resource` */

insert  into `t_role_resource`(`id`,`role_id`,`resource_id`) values (1,1,1),(2,2,3),(3,2,5),(4,2,6),(5,2,7),(6,2,9),(7,2,11),(8,2,12),(9,2,13),(10,2,15),(11,2,17),(12,2,18),(13,3,6),(14,3,7),(15,3,8),(16,3,14),(17,4,6),(18,4,7),(19,4,12),(20,4,18);

/*Table structure for table `t_sms` */

DROP TABLE IF EXISTS `t_sms`;

CREATE TABLE `t_sms` (
  `system_no` int(2) NOT NULL COMMENT '系统标识',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sms_id` varchar(100) NOT NULL COMMENT '短信标识',
  `sms_content` varchar(100) NOT NULL COMMENT '短信内容',
  `sms_address` varchar(100) NOT NULL COMMENT '收件人',
  `sms_from` varchar(100) NOT NULL COMMENT '发件人',
  `sms_type` int(1) NOT NULL COMMENT '短信类型：1、短信；2、彩信',
  `sms_file` varchar(100) DEFAULT NULL COMMENT '文件路径	语音/图片/视频等',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `sms_status` int(1) NOT NULL COMMENT '短信状态：1、未读；2、已读',
  `sms_service` varchar(100) NOT NULL COMMENT '运营商服务电话',
  `sms_fromuser` varchar(100) DEFAULT NULL COMMENT '发件人名称',
  `sms_addressuser` varchar(100) DEFAULT NULL COMMENT '收件人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_sms` */

insert  into `t_sms`(`system_no`,`id`,`sms_id`,`sms_content`,`sms_address`,`sms_from`,`sms_type`,`sms_file`,`create_time`,`send_time`,`sms_status`,`sms_service`,`sms_fromuser`,`sms_addressuser`) values (1,1,'msg01','台风“天鸽”来袭啦','17688561711','17688561711',1,NULL,'2017-08-24 07:01:33','2017-08-24 07:01:36',1,'000','张三','李四');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `nickname` varchar(30) NOT NULL COMMENT '昵称',
  `status` int(11) NOT NULL COMMENT '状态:1 启用,2 禁用',
  `userdesc` varchar(100) NOT NULL COMMENT '描述',
  `organization` varchar(30) DEFAULT NULL COMMENT '组织',
  `birth_date` varchar(30) DEFAULT NULL COMMENT '出生日期',
  `user_type` int(11) NOT NULL COMMENT '用户类型：0 普通用户，1 系统用户',
  `phone_number` varchar(13) DEFAULT NULL COMMENT '手机号',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`nickname`,`status`,`userdesc`,`organization`,`birth_date`,`user_type`,`phone_number`,`address`,`email`,`create_time`,`login_time`,`modify_time`) values (1,'admin','a66abb5684c45962d887564f08346e8d','超级管理员',1,'admin','0','2017-08-16',1,'17688561711','深圳龙岗','huahao@maidingsheng.net','1000-01-01 00:00:00',NULL,'2017-08-25 10:43:47');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联表 ID',
  `user_id` tinyint(4) NOT NULL,
  `role_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(2,2,2),(3,3,3),(4,4,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
