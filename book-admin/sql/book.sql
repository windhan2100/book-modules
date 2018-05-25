==============test ===================
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `book`;

/*****************/
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` VARCHAR(300) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `email` VARCHAR(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户email',
  `phone` VARCHAR(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户手机号',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
INSERT  INTO `user`(`id`,`name`,`password`,`email`,`phone`,`update_time`) VALUES (1,'admin','admin007','liweihan@sohu-inc.om','13581746914','2017-08-10 13:43:36');

/*****************/
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '城市表city的主键ID',
  `city_name` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '城市名称',
  `city_url` VARCHAR(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '城市URL,预约时需要',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
INSERT  INTO `city`(`id`,`city_name`,`city_url`) VALUES (3,'北京','beijing'),(4,'上海','shanghai');

/*****************/
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '套餐类型的专辑ID',
  `type_name` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '套餐的名称',
  `price` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '套餐价格',
  `city_id` INT(11) DEFAULT NULL COMMENT '所属的城市',
  PRIMARY KEY (`id`),
  KEY `FK_type` (`city_id`),
  CONSTRAINT `FK_type` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
INSERT  INTO `type`(`id`,`type_name`,`price`,`city_id`) VALUES (1,'398套餐','398.00',3),(2,'598套餐','598.00',3),(3,'上门服务','1000.00',3),(5,'498套餐','498.00',4),(6,'798双胎套餐','598.00',4);

/*****************/
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '店铺表的主键ID',
  `name` VARCHAR(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '店铺名称',
  `city_id` INT(11) NOT NULL COMMENT '所属城市的ID',
  PRIMARY KEY (`id`),
  KEY `FK_shop` (`city_id`),
  CONSTRAINT `FK_shop` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
INSERT  INTO `shop`(`id`,`name`,`city_id`) VALUES (1,'劲松店',3),(2,'军博店',3),(3,'西土城店',3),(4,'南京路店',4),(5,'外滩店',4);

/*****************/
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `series` VARCHAR(300) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户使用密码登录成功之后获取的一个UUID值，同时用户端保存的cookie记录就是：EncryptionUtil.base64Encode(用户名:此UUID值)',
  `token` VARCHAR(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '在拦截器中校验是否能够登录的密文，其加密方式是：EncryptionUtil.sha256Hex(用户名 + “_” + 密码 + “_” + 自动登录失效的时间点的字符串 + “_” +  自定义的salt)',
  `valid_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '自动登录失效的时间，即：这个时间点之后只能重新用用户名、密码登录，如果在重新登录时勾选了“30天内自动登录”则更新该用户在persistent_logins这个表中的自动登录记录',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*********************/
DROP TABLE IF EXISTS `pay_type`;
CREATE TABLE `pay_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '支付类型表的主键ID',
  `pay_name` VARCHAR(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付类型的ID',
  `city_id` INT(11) DEFAULT NULL COMMENT '所属的城市',
  PRIMARY KEY (`id`),
  KEY `FK_pay_type` (`city_id`),
  CONSTRAINT `FK_pay_type` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
INSERT  INTO `pay_type`(`id`,`pay_name`,`city_id`) VALUES (1,'现金',3),(2,'支付宝',3),(3,'微信',3),(4,'美团团购',3),(5,'大众团购',3),(6,'糯米团购',3),(7,'哪拍网',3),(8,'现金',4),(9,'支付宝',4),(10,'微信',4),(11,'美团网',4),(12,'大众点评',4),(13,'糯米网',4);

/*********************/
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单表的主键ID',
  `username` VARCHAR(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '客户姓名',
  `phone` VARCHAR(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '客户电话(手机号码或座机)',
  `city_id` INT(11) DEFAULT NULL COMMENT '所属城市的ID',
  `city_name` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所属城市的名称',
  `shop_id` INT(11) DEFAULT NULL COMMENT '店铺的ID',
  `shop_name` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '店铺的名称',
  `book_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '预约的时间',
  `address` VARCHAR(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '客户地址:邮寄时可能需要',
  `type_id` INT(11) DEFAULT NULL COMMENT '套餐类型ID',
  `type_name` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '套餐类型名称',
  `pay_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '用户实际支付的价格',
  `pay_id` INT(11) DEFAULT NULL COMMENT '用户的支付类型ID',
  `pay_name` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户支付类型的名称:比如美团、微信等',
  `pay_code` VARCHAR(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户团购支付的团购码',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单修改的时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建的时间',
  `update_user` VARCHAR(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改订单的管理员账号',
  `status` TINYINT(4) DEFAULT NULL COMMENT '0:完成，1:未处理 2:取消',
  `back_up` VARCHAR(1024) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注信息:比如用户取消的原因等',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `admin_right`;
CREATE TABLE `admin_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `name` varchar(20) DEFAULT '' COMMENT '用户名(和user表中的name一样)',
  `rights` varchar(255) DEFAULT '' COMMENT '用户拥有的权限列表，用,分割',
  `is_admin` tinyint(4) DEFAULT 0 COMMENT '是否超级管理员，0：不是；1：超管',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '记录添加时间',
  `creator_name` varchar(50) DEFAULT '' COMMENT '添加者',
  `chinese_name` varchar(20) DEFAULT '' COMMENT '中文名字,便于阅读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
INSERT  INTO `admin_right`(`id`,`name`,`rights`,`is_admin`,`create_time`,`creator_name`,`chinese_name`) VALUES (55,'admin','1,2,3,',1,'2017-08-24 14:26:14','admin','韩超');

/*************************/
DROP TABLE IF EXISTS `admin_functions`;
CREATE TABLE `admin_functions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `name` VARCHAR(100) DEFAULT '' COMMENT '菜单名称',
  `url` VARCHAR(255) DEFAULT '' COMMENT '菜单访问的url',
  `sort` TINYINT(11) DEFAULT '0' COMMENT '排序的序号',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '菜单添加时间',
  `state` TINYINT(11) DEFAULT '0',
  `creator_name` VARCHAR(20) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
INSERT  INTO `admin_functions`(`id`,`name`,`url`,`sort`,`create_time`,`state`,`creator_name`) VALUES (6,'预约客户(北京)','/book/beijing',0,'2017-08-28 15:18:23',0,'admin'),(7,'预约客户(上海)','/book/shanghai',1,'2017-08-28 15:18:46',0,'admin');
