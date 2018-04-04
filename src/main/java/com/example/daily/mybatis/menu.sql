CREATE DATABASE `kld_alert` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `NAME` varchar(30) DEFAULT NULL COMMENT '菜单名称',
  `CODE` varchar(10) DEFAULT NULL COMMENT '菜单code',
  `REQUIRED` tinyint(1) DEFAULT NULL COMMENT '是否是必须菜单',
  `DISPLAY` tinyint(1) DEFAULT NULL COMMENT '是否是需要显示菜单',
  `PARENTMENU` varchar(10) DEFAULT NULL COMMENT '父菜单code',
  `DESCRIPTION` varchar(50) DEFAULT NULL COMMENT '描述',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '认证完成状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='菜单信息';