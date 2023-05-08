/*
Navicat MySQL Data Transfer

Source Server         : mySqlConnect
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : summer

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2023-05-08 16:04:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_address`
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `is_default` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES ('3', '63', 'test', '18782059038', '四川省成都市青羊区百花中心站对面', '1');
INSERT INTO `tb_address` VALUES ('5', '63', 'admin', '18782059038', '上海青浦区汇联路', '0');
INSERT INTO `tb_address` VALUES ('6', '62', '1', '1', '1', '0');

-- ----------------------------
-- Table structure for `tb_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type` enum('reduction','discount') NOT NULL COMMENT '优惠卷类型 red立减 dis打折',,
  `limit_num` int NOT NULL DEFAULT '-1' COMMENT '限制条件 -1 默认没有限制金额',,
  `value` int DEFAULT NULL COMMENT '折扣力度 立减和打折都是整数',
  `total` int NOT NULL COMMENT '总卷数',
  `active_time` int NOT NULL COMMENT '有效时间 整数 以天为单位',
  `updated` datetime NOT NULL COMMENT '修改时间',
  `created` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tb_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_item`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '鍟嗗搧id锛屽悓鏃朵篃鏄晢鍝佺紪鍙?',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(100) DEFAULT NULL COMMENT '商品卖点',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
  `num` int DEFAULT NULL COMMENT '库存数量',
  `limit_num` int DEFAULT NULL COMMENT '售卖数量限制',
  `image` varchar(2000) DEFAULT NULL COMMENT '商品图片',
  `cid` bigint DEFAULT NULL COMMENT '所属分类',
  `status` int DEFAULT '1' COMMENT '商品状态 1正常 0下架',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `status` (`status`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=150642571432853 DEFAULT CHARSET=utf8mb3 COMMENT='商品表';

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('562379', '坚果 Pro 软胶保护套', 'TPU 环保材质、耐磨、耐油、耐久性强', '49.00', '999', '100', 'http://image.smartisanos.cn/resource/902befd5f32a8caf4ca79b55d39ee25a.jpg', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('605616', '坚果 Pro 钢化玻璃保护膜（前屏用）', '高达 92% 的超强透光率、耐刮花、防指纹', '49.00', '999', '100', 'http://image.smartisanos.cn/resource/30cacf4088f7105d16452c661afd9299.jpg', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('679532', 'Smartisan 原装快充充电器 18W', '18W 安全快充、支持主流 QC3.0, MTK PE+2.0 快充协议', '59.00', '999', '100', 'http://image.smartisanos.cn/resource/dc53bd870ee64d2053ecc51750ece43a.jpg', '560', '1', '2017-09-05 21:27:54', '2017-11-09 22:31:04');
INSERT INTO `tb_item` VALUES ('679533', 'Smartisan 坚果自拍杆', '通用 3.5 mm 接口、航空铝合金伸缩杆、防过敏硅胶手柄', '69.00', '999', '100', 'http://image.smartisanos.cn/resource/afe5728faa22f4b078b84d9c725c136d.jpg', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('691300', 'Smartisan 快充移动电源 10000mAh', '10000mAh 双向快充、轻盈便携、高标准安全保护', '199.00', '999', '100', 'http://image.smartisanos.cn/resource/0540778097a009364f2dcbb8c5286451.jpg', '560', '1', '2017-09-05 21:27:54', '2017-11-09 22:22:53');
INSERT INTO `tb_item` VALUES ('738388', 'Smartisan 原装 Type-C 数据线', 'PTC 过温保护、凹形设计、TPE 环保材质', '39.00', '999', '100', 'http://image.smartisanos.cn/resource/c79a73ffc6f8e782160d978f49f543dc.jpg', '560', '1', '2017-09-05 21:27:54', '2017-10-22 22:15:02');
INSERT INTO `tb_item` VALUES ('741524', 'Smartisan S-100 半入耳式线控耳机', '卓越的 14.2mm 发音单元、三按键式线控', '99.00', '999', '100', 'http://image.smartisanos.cn/resource/c98abe89b5a5502ef04c30e751b2bfef.png', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('816448', '极米无屏电视 CC', '720P 高清分辨率、JBL 音响、两万毫安续航力', '2799.00', '999', '100', 'http://image.smartisanos.cn/resource/41cb562a47d4831e199ed7e2057f3b61.jpg', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('816753', '《索尼设计，塑造现代》', '索尼全盛时期工业设计作品首次集结成书并引进中国', '259.00', '999', '100', 'http://image.smartisanos.cn/resource/f950d9c27ef21e17374fa212b40d66a9.jpg', '76', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('830972', '《深泽直人》', '首次面向中国读者介绍其作品', '199.00', '999', '100', 'http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('832739', 'FIIL CARAT 蓝牙运动耳机', '智能计步、磁吸项链式、佩戴舒适不易掉', '499.00', '999', '100', 'http://image.smartisanos.cn/resource/61b4f3de01f00e57a664e648d6ea4721.jpg', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('844022', 'FIIL CARAT LITE 蓝牙运动耳机', '线控带麦、IP65 防水、磁吸项链式', '299.00', '999', '100', 'http://image.smartisanos.cn/resource/62c1a6988de8071beef4203bedde5144.jpg', '560', '1', '2017-09-05 21:27:54', '2017-09-05 21:29:54');
INSERT INTO `tb_item` VALUES ('847276', 'FIIL Diva Pro 全场景无线降噪耳机', '智能语音交互、高清无损本地存储播放', '1499.00', '999', '100', 'http://image.smartisanos.cn/resource/804b82e4c05516e822667a23ee311f7c.jpg', '560', '1', '2017-09-05 21:27:54', '2018-04-20 22:18:11');
INSERT INTO `tb_item` VALUES ('856645', '三星 Galaxy S4 (I9500) 16G版 星空黑 联通3G手机', '年货特价来袭！三星经典旗舰机！', '1888.00', '999', '100', 'https://i.loli.net/2018/07/13/5b48b58b511dc.jpg', '560', '0', '2017-09-05 21:27:54', '2017-11-06 23:44:32');
INSERT INTO `tb_item` VALUES ('150635087972564', '支付测试商品 IPhone X 全面屏 全面绽放', '此仅为支付测试商品 拍下不会发货', '1.00', '999', '100', 'https://i.loli.net/2018/07/13/5b48ac7766d98.png,https://i.loli.net/2018/07/13/5b48ac9135c5f.png,https://i.loli.net/2018/07/13/5b48ac9c2be6c.png,https://i.loli.net/2018/07/13/5b48aca99c8b6.png,https://i.loli.net/2018/07/13/5b48a7f468bf2.png', '560', '1', '2017-09-05 21:27:54', '2018-07-13 21:59:05');
INSERT INTO `tb_item` VALUES ('150642571432835', '捐赠商品', '您的捐赠将用于本站维护 给您带来更好的体验', '1.00', '999', '100', 'https://i.loli.net/2018/07/13/5b48a7f46be51.png,https://i.loli.net/2018/07/13/5b48a952de430.png', '560', '1', '2017-09-05 21:27:54', '2018-07-13 21:38:52');
INSERT INTO `tb_item` VALUES ('150642571432836', 'Smartisan T恤 伍迪·艾伦出生', '一件内外兼修的舒适T恤', '149.00', '999', '100', 'https://resource.smartisan.com/resource/f96f0879768bc317b74e7cf9e3d96884.jpg,https://resource.smartisan.com/resource/095b46c25f9df5b13ee51f3e512d1e96.jpg,https://resource.smartisan.com/resource/0c9c397c8ac68a2ad327e1da8a5cb7d0.jpg,https://resource.smartisan.com/resource/154b35897ed3c1cb8dc1c7aae7b88f1f.jpg,https://resource.smartisan.com/resource/4a1686f2fde86e0aaac49c92395d4b32.jpg', '1184', '1', '2018-04-18 20:41:41', '2018-04-20 00:21:01');
INSERT INTO `tb_item` VALUES ('150642571432837', '坚果 3 前屏钢化玻璃保护膜', '超强透光率、耐刮花、防指纹', '49.00', '999', '100', 'https://resource.smartisan.com/resource/3a7522290397a9444d7355298248f197.jpg', '560', '1', '2018-04-19 00:34:06', '2018-04-19 23:49:38');
INSERT INTO `tb_item` VALUES ('150642571432838', '坚果 3 绒布国旗保护套', '质感精良、完美贴合、周到防护', '79.00', '999', '100', 'https://resource.smartisan.com/resource/63ea40e5c64db1c6b1d480c48fe01272.jpg,https://resource.smartisan.com/resource/4b8d00ab6ba508a977a475988e0fdb53.jpg,https://resource.smartisan.com/resource/87ea3888491d172b7d7a0e78e4294b4b.jpg,https://resource.smartisan.com/resource/8d30265188ddd1ba05e34f669c5dcf1c.jpg', '560', '1', '2018-04-19 00:35:50', '2018-04-20 00:01:08');
INSERT INTO `tb_item` VALUES ('150642571432839', '坚果 3 TPU 软胶透明保护套', '轻薄透明、完美贴合、TPU 环保材质', '29.00', '999', '100', 'https://resource.smartisan.com/resource/5e4b1feddb13639550849f12f6b2e202.jpg,https://resource.smartisan.com/resource/0477d8b177db197a0b09a18e116b2bca.jpg,https://resource.smartisan.com/resource/b66d7e832339cf240b13a9a91107abdc.jpg,https://resource.smartisan.com/resource/56db3c83cca697572fa8a1df2e3172d7.jpg', '560', '1', '2018-04-19 00:38:07', '2018-04-20 00:02:54');
INSERT INTO `tb_item` VALUES ('150642571432840', 'Smartisan 半入耳式耳机', '经典配色、专业调音、高品质麦克风', '89.00', '999', '100', 'https://resource.smartisan.com/resource/9c1d958f10a811df841298d50e1ca7c0.jpg,https://resource.smartisan.com/resource/afa4ecdb54d7f50d0b6265bbcf31d6c8.jpg,https://resource.smartisan.com/resource/eb1bf1dee365c7855e6b047d8b9c5b1e.jpg,https://resource.smartisan.com/resource/dfcc9fa16ab354a41683959398bff128.jpg', '560', '1', '2018-04-19 00:40:40', '2018-04-20 00:04:41');
INSERT INTO `tb_item` VALUES ('150642571432841', '坚果 3 TPU 软胶保护套', 'TPU 环保材质、完美贴合、周到防护', '49.00', '999', '100', 'https://resource.smartisan.com/resource/b899d9b82c4bc2710492a26af021d484.jpg,https://resource.smartisan.com/resource/bb8859032d6060ccb4e733a2c8e2f51d.jpg,https://resource.smartisan.com/resource/df5b3d3539481eb1c00333a5bc5b60b6.jpg', '560', '1', '2018-04-19 00:43:48', '2018-04-20 00:06:58');
INSERT INTO `tb_item` VALUES ('150642571432842', '坚果 3 \"足迹\"背贴 乐高创始人出生', '1891 年 4 月 7 日', '79.00', '999', '100', 'https://resource.smartisan.com/resource/abb6986430536cd9365352b434f3c568.jpg', '560', '1', '2018-04-19 00:45:14', '2018-04-20 00:08:21');
INSERT INTO `tb_item` VALUES ('150642571432843', '坚果 3', '漂亮得不像实力派', '1999.00', '999', '100', 'https://resource.smartisan.com/resource/718bcecced0df1cd23bbdb9cc1f70b7d.png', '560', '1', '2018-04-19 22:13:31', '2018-07-13 22:03:52');
INSERT INTO `tb_item` VALUES ('150642571432844', '畅呼吸智能空气净化器超级除甲醛版', '购新空净 赠价值 699 元活性炭滤芯', '2999.00', '999', '100', 'https://resource.smartisan.com/resource/71432ad30288fb860a4389881069b874.png', '560', '1', '2018-04-19 22:16:05', '2018-07-13 22:06:58');
INSERT INTO `tb_item` VALUES ('150642571432845', 'Smartisan 帆布鞋', '一双踏实、舒适的帆布鞋', '199.00', '999', '100', 'https://resource.smartisan.com/resource/2f9a0f5f3dedf0ed813622003f1b287b.jpg,https://resource.smartisan.com/resource/0cd8f107c70d002caf902745355e269a.jpg,https://resource.smartisan.com/resource/fa42dcd439e9fb990831f1d21c3f19b8.jpg', '1184', '1', '2018-04-19 22:22:02', '2018-04-20 00:19:39');
INSERT INTO `tb_item` VALUES ('150642571432846', 'Smartisan T恤 任天堂发售“红白机”', '100% 美国 SUPIMA 棉、舒适拉绒质地', '149.00', '999', '100', 'https://resource.smartisan.com/resource/804edf579887b3e1fae4e20a379be5b5.png,https://resource.smartisan.com/resource/6a92fe5ab90b379d5315c0ee2610f467.png,https://resource.smartisan.com/resource/76c811504b910e04c448dda8d47a09c3.png', '1184', '1', '2018-04-19 22:23:39', '2018-04-20 00:23:09');
INSERT INTO `tb_item` VALUES ('150642571432847', 'Smartisan Polo衫 经典款', '一件表里如一的舒适 POLO 衫', '249.00', '999', '100', 'https://resource.smartisan.com/resource/daa975651d6d700c0f886718c520ee19.jpg,https://resource.smartisan.com/resource/8b4884f04835f8de3c33817732fdcb46.jpg,https://resource.smartisan.com/resource/057f6010d6cb7afc964f812093742283.jpg,https://resource.smartisan.com/resource/3cc4b5a1e0a6136eb9725a88d6c1d3be.jpg,https://resource.smartisan.com/resource/f35c053b87dd0e1255be2a8bfa773232.jpg', '1184', '1', '2018-04-19 22:25:41', '2018-04-20 10:51:53');
INSERT INTO `tb_item` VALUES ('150642571432848', 'Smartisan 牛津纺衬衫', '一件无拘无束的舒适衬衫', '199.00', '999', '100', 'https://resource.smartisan.com/resource/a1c53b5f12dd7ef790cadec0eaeaf466.jpg,https://resource.smartisan.com/resource/dccec50aa1480c23a6d62648d2271c0a.jpg,https://resource.smartisan.com/resource/28c798c14b3cc9cfe7100567df6e5999.jpg,https://resource.smartisan.com/resource/da87105789046c13412f6f6a32032df7.jpg,https://resource.smartisan.com/resource/cf9704df83dc6d6ff5404da154388a58.jpg', '1184', '1', '2018-04-19 22:28:11', '2018-04-20 10:53:15');
INSERT INTO `tb_item` VALUES ('150642571432849', 'Smartisan 明信片', '优质卡纸、包装精致、色彩饱满', '9.90', '999', '100', 'https://resource.smartisan.com/resource/3973d009d182d8023bea6250b9a3959e.jpg,https://resource.smartisan.com/resource/1901bf9f58d83978353cf1ec58442cc6.jpg,https://resource.smartisan.com/resource/4e0b690102858fc3013ea650fb1e1a8e.jpg,https://resource.smartisan.com/resource/51765f60367d6e40e4ae6f2b9ce46a91.jpg,https://resource.smartisan.com/resource/5108b5e7448c14e5241b60ba41fafc8e.jpg', '1184', '1', '2018-04-19 22:31:09', '2018-04-21 11:26:31');
INSERT INTO `tb_item` VALUES ('150642571432850', 'ACIL E1 颈挂式蓝牙耳机', '无感佩戴，一切变得简单', '199.00', '999', '100', 'https://resource.smartisan.com/resource/406eddef8808fa5a9be9594d07ef8643.jpg,https://resource.smartisan.com/resource/548de41c48d47232b4ed541c1df57c2f.jpg,https://resource.smartisan.com/resource/aee0949bc33654bc18cedb8cd7dfbcff.jpg', '560', '1', '2018-04-19 22:32:38', '2018-04-20 11:17:31');
INSERT INTO `tb_item` VALUES ('150642571432851', '优点智能 E1 推拉式智能指纹密码锁', '推拉一下，轻松开关', '2699.00', '999', '100', 'https://resource.smartisan.com/resource/7ac3af5a92ad791c2b38bfe1e38ee334.jpg,https://resource.smartisan.com/resource/e37029b8cd3194ad9581de0ee6512acb.jpg,https://resource.smartisan.com/resource/1501eaf68c9771e5599eec45a5f6320a.jpg,https://resource.smartisan.com/resource/a8c6a41637041c576aaa2a5162d2ab56.jpg,https://resource.smartisan.com/resource/3934e0c458922c0049d311b84ddb73e0.jpg', '560', '1', '2018-04-19 22:36:50', '2018-04-20 00:13:44');
INSERT INTO `tb_item` VALUES ('150642571432852', 'FIIL Driifter 脖挂蓝牙耳机', '全天佩戴 抬手就听 HEAC稳连技术', '499.00', '999', '100', 'https://resource.smartisan.com/resource/367d93db1d58f9f3505bc0ec18efaa44.jpg,https://resource.smartisan.com/resource/8ecc94c0f0c4ebc861f06c86789a66e6.jpg,https://resource.smartisan.com/resource/58a2cdb44f722202b05dd09d6fd959de.jpg,https://resource.smartisan.com/resource/2b811a93a2915310f72291e46bd944ad.jpg,https://resource.smartisan.com/resource/8cd011adef99f9734ed974ea9732e6e7.jpg', '560', '1', '2018-06-02 19:43:12', '2018-06-02 19:43:14');

-- ----------------------------
-- Table structure for `tb_item_desc`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_desc`;
CREATE TABLE `tb_item_desc` (
  `item_id` bigint NOT NULL COMMENT '商品ID',
  `item_desc` text COMMENT '商品描述',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='商品描述表';

-- ----------------------------
-- Records of tb_item_desc
-- ----------------------------
INSERT INTO `tb_item_desc` VALUES ('562379', '<img src=\"http://image.smartisanos.cn/resource/98521dbfe1dd1e67db3f7ca21e76c9ef.jpg\" style=\"width:1220px;height:7000px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:19:07');
INSERT INTO `tb_item_desc` VALUES ('679532', '<img src=\"http://image.smartisanos.cn/resource/4a7b87fe01ec8339985702ee922d205a.jpg\" style=\"width:1220px;height:4526px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-09 22:31:04');
INSERT INTO `tb_item_desc` VALUES ('679533', '<img src=\"http://image.smartisanos.cn/resource/0bb13cf0b2e0b4817f4914a317fb1445.png\" style=\"width:1220px;height:6481px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:28:04');
INSERT INTO `tb_item_desc` VALUES ('691300', '<img src=\"http://image.smartisanos.cn/resource/d930be42185ab064035d0894f37ea179.jpg\" style=\"width:1220px;height:6478px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-09 22:22:53');
INSERT INTO `tb_item_desc` VALUES ('738388', '<img src=\"http://image.smartisanos.cn/resource/b3d7b420e3e609e858a8d75f19cbfd7c.jpg\" style=\"width:1220px;height:4829px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:21:41');
INSERT INTO `tb_item_desc` VALUES ('741524', '<img src=\"http://image.smartisanos.cn/resource/73fdd5f948cd6248c51521e87acb33e5.jpg\" style=\"width:1220px;height:8703px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:31:05');
INSERT INTO `tb_item_desc` VALUES ('816448', '<img src=\"http://image.smartisanos.cn/resource/e7ed8222dcd7c9f67af3097bd7bd5c2b.jpg\" style=\"width:1220px;height:12257px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:42:22');
INSERT INTO `tb_item_desc` VALUES ('816753', '<img src=\"http://image.smartisanos.cn/resource/62a60be80e9cd3307ef334ede82b430a.jpg\" style=\"width:1220px;height:8267px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:41:18');
INSERT INTO `tb_item_desc` VALUES ('830972', '<img src=\"http://image.smartisanos.cn/resource/102ed8a03b5f4452dda7dc513c016f12.jpg\" style=\"width:1220px;height:8811px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:37:54');
INSERT INTO `tb_item_desc` VALUES ('832739', '<img src=\"http://image.smartisanos.cn/resource/f86802b6a7b207d02f5c69163fa5e347.jpg\" style=\"width:1220px;height:13682px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:39:10');
INSERT INTO `tb_item_desc` VALUES ('844022', '<img src=\"http://image.smartisanos.cn/resource/bcd85c778a0b54b61afe813bd7b674d4.jpg\" style=\"width:1220px;height:12384px;\" alt=\"\" />', '2017-09-05 21:27:54', '2017-11-19 21:40:07');
INSERT INTO `tb_item_desc` VALUES ('847276', '<img src=\"http://image.smartisanos.cn/resource/9be6229b3498749c4afd173a3b1fe165.png\" style=\"width:1220px;height:15514px;\" alt=\"\" />', '2017-09-05 21:27:54', '2018-04-20 22:18:11');
INSERT INTO `tb_item_desc` VALUES ('856645', '<div class=\"ssd-module-wrap\" style=\"margin:0 auto;text-align:left;\">\n	<div class=\"outer-container\">\n		<div class=\" \" id=\"\">\n			<div class=\"ssd-1080\">\n				<div class=\"ssd-j-module item_tit\">\n					<div class=\"tit1\">\n						产品特色\n					</div>\n					<div class=\"tit2\">\n						PRODUCTION\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-1183\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#ffffff url(http://img30.360buyimg.com/sku/jfs/t460/159/849423048/82352/867f75ff/548e5a88N73b12dd9.jpg) no-repeat;\">\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n						</div>\n						<div class=\"title2\">\n						</div>\n					</div>\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t451/241/814906943/1043/fa877bc/548e4238N585a2a89.png\" alt=\"\" /> \n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-2165\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#ffffff url(http://img30.360buyimg.com/cms/jfs/t217/232/2460673136/4947/e76ec4b7/541a3cf8Ne7d0749d.png) no-repeat;\">\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t598/141/839042103/90325/3ea2cf8a/548e7e74Nc2025337.jpg\" alt=\"\" /> \n					</div>\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n						</div>\n						<div class=\"title2\">\n						</div>\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-2165\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#ffffff;\">\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t472/95/847651115/101211/6b98601c/548e7fc5Ncb21ef86.jpg\" alt=\"\" /> \n					</div>\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n							、\n						</div>\n						<div class=\"title2\">\n							、\n						</div>\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \" id=\"\">\n			<div class=\"ssd-1080\">\n				<div class=\"ssd-j-module item_tit\">\n					<div class=\"tit1\">\n						产品功能\n					</div>\n					<div class=\"tit2\">\n						PRODUCT FUNCTION\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-2165\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#ffffff url(http://img30.360buyimg.com/cms/jfs/t217/232/2460673136/4947/e76ec4b7/541a3cf8Ne7d0749d.png) no-repeat;\">\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t745/150/148843066/79290/604280fc/548e8318Na94c82f9.jpg\" alt=\"\" /> \n					</div>\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n							、\n						</div>\n						<div class=\"title2\">\n							、\n						</div>\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-1829\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#ffffff;\">\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t769/364/157509045/89868/d97d79ef/548e9d75N18fc06d2.jpg\" alt=\"\" /> \n					</div>\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n						</div>\n						<div class=\"title2\">\n						</div>\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-2136\">\n				<div class=\"ssd-j-module img_box\" style=\"background:#cccccc;\">\n					<div class=\"imglab\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t685/187/809086881/59964/1802066d/548ea270N6853bbcd.jpg\" alt=\"\" /> \n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-2136\">\n				<div class=\"ssd-j-module img_box\" style=\"background:#cccccc;\">\n					<div class=\"imglab\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t685/96/811972813/58610/d1082b4d/548ea4ceN7273b5bd.jpg\" alt=\"\" /> \n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-1243\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#f3f3f3 url(http://img30.360buyimg.com/sku/jfs/t487/290/816270587/57176/b1d5d9d0/548ea5afN026eaf9a.jpg) no-repeat;\">\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n							、\n						</div>\n						<div class=\"title2\">\n							、\n						</div>\n					</div>\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t547/275/827858003/1121/958dc0d9/548ea626Na2fb0cd4.png\" alt=\"\" /> \n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \" id=\"\">\n			<div class=\"ssd-1080\">\n				<div class=\"ssd-j-module item_tit\">\n					<div class=\"tit1\">\n						产品细节\n					</div>\n					<div class=\"tit2\">\n						PRODUCT DETAILS\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-2165\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#ffffff url(http://img30.360buyimg.com/cms/jfs/t217/232/2460673136/4947/e76ec4b7/541a3cf8Ne7d0749d.png) no-repeat;\">\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t766/326/165583398/153663/931a1487/548ea712N54c54c32.jpg\" alt=\"\" /> \n					</div>\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n						</div>\n						<div class=\"title2\">\n						</div>\n					</div>\n				</div>\n			</div>\n		</div>\n		<div class=\" \">\n			<div class=\"ssd-2165\">\n				<div class=\"ssd-j-module item_tw_1\" style=\"background:#ffffff url(http://img30.360buyimg.com/cms/jfs/t217/232/2460673136/4947/e76ec4b7/541a3cf8Ne7d0749d.png) no-repeat;\">\n					<div class=\"img-pic\">\n						<img class=\"ssd-err-product\" src=\"http://img30.360buyimg.com/sku/jfs/t571/63/809585802/77035/720c5d87/548ea7d9N22f04056.jpg\" alt=\"\" /> \n					</div>\n					<div class=\"txt_box\">\n						<div class=\"title1\">\n						</div>\n						<div class=\"title2\">\n						</div>\n					</div>\n				</div>\n			</div>\n		</div>\n	</div>\n</div>', '2017-09-05 21:27:54', '2017-10-22 22:04:26');
INSERT INTO `tb_item_desc` VALUES ('150635087972564', '<p style=\"text-align:center;\">\n	<img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t7843/137/3005340945/124833/dc7c71f2/59b8ccd1N2bffd055.jpg\" alt=\"\" /><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t8764/314/1380452846/296346/d62490e2/59b8ccd1N96ce760d.jpg\" alt=\"\" /><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t8710/275/1373463301/363710/ebf00bff/59b8ccbaN2d563f74.jpg\" alt=\"\" /><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t8632/330/1390725687/229853/e56f9e1b/59b8ccd1N7b8b6bdb.jpg\" alt=\"\" /><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t9115/290/1376678976/488369/591760dc/59b8ccc6N1563a61b.jpg\" alt=\"\" /><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t8233/331/1431263348/183032/b875528c/59b8ccd1Ne7e633e3.jpg\" alt=\"\" /><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t8785/253/890847377/186916/c467a464/59b8ccd1N4551397c.jpg\" alt=\"\" /> <img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t8728/276/1416802585/172158/1516ec08/59b8ccd1N95aae9c9.jpg\" alt=\"\" /> <img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t9082/133/1223014275/307097/58f97021/59b8ccd2Nebfc633a.jpg\" alt=\"\" /><img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t9052/275/1400615286/155643/1b0ecf44/59b8ccd2N46bd82bf.jpg\" alt=\"\" /> <img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t9169/240/1361662217/193435/24ed9b93/59b8ccd4N03cec407.jpg\" alt=\"\" /> <img src=\"https://img30.360buyimg.com/popWaterMark/jfs/t7390/232/3008585906/285016/56cbb12/59b8ccd4Nc8434af8.jpg\" alt=\"\" /> \n</p>', '2017-09-05 21:27:54', '2018-07-13 21:59:05');
INSERT INTO `tb_item_desc` VALUES ('150642571432835', '<br />\n<br />\n<br />\n<span style=\"font-size:18px;\">为什么要捐赠？</span> <br />\n<br />\n<span style=\"font-size:18px;\">在开始之前，首先感谢用户的支持和慷慨解囊，不得不说，你们的支持给予了很大的动力</span> <br />\n<br />\n<span style=\"font-size:18px;\">没有广告和第三方推广的收入，主动捐赠是项目受欢迎程度最直观的体现</span> <br />\n<br />\n<span style=\"font-size:18px;\">当支付宝或微信偶尔收到一笔捐赠，就感觉多了一分认可，多了一分责任</span> <br />\n<br />\n<span style=\"font-size:18px;\">一个非盈利项目仅仅依靠兴趣的支撑，很难确定它还能走多远</span> <br />\n<br />\n<span style=\"font-size:18px;\">它以后会变成怎样，我不知道。我只知道，有些东西，要靠双方去维持</span> <br />\n<br />\n<span style=\"font-size:18px;\">捐赠的渠道</span> <br />\n<br />\n<span style=\"font-size:18px;\">支付宝：</span> <br />\n<br />\n<img src=\"https://i.loli.net/2018/07/13/5b48a7f46be51.png\" width=\"250px\" alt=\"\" /> <br />\n<br />\n<span style=\"font-size:18px;\">微信支付：</span> <br />\n<br />\n<img src=\"https://i.loli.net/2018/07/13/5b48a952de430.png\" width=\"250px\" alt=\"\" /> <br />\n<br />\n<span style=\"font-size:18px;\">微信打赏请留言备注您的信息</span> <br />\n<br />\n<br />\n<br />\n<br />', '2017-09-05 21:27:54', '2018-07-13 21:38:52');
INSERT INTO `tb_item_desc` VALUES ('150642571432836', '<img src=\"https://resource.smartisan.com/resource/36fee45879f4f252492ec552dfd4c323.jpg\" style=\"width:1220px;height:6982px;\" alt=\"\" />', '2018-04-18 20:41:41', '2018-04-20 00:21:01');
INSERT INTO `tb_item_desc` VALUES ('150642571432837', '<img src=\"https://resource.smartisan.com/resource/5dcbe27f36e1f8f2bfdfabb0b2681879.jpg\" style=\"width:1220px;height:3665px;\" alt=\"\" />', '2018-04-19 00:34:06', '2018-04-19 23:49:38');
INSERT INTO `tb_item_desc` VALUES ('150642571432838', '<img src=\"https://resource.smartisan.com/resource/3aa27e8caf798b5e7e3796388190b43b.jpg\" style=\"width:1220px;height:5797px;\" alt=\"\" />', '2018-04-19 00:35:50', '2018-04-20 00:01:08');
INSERT INTO `tb_item_desc` VALUES ('150642571432839', '<img src=\"https://resource.smartisan.com/resource/fda5d962cc2b2e579c5df1c3d9e2f2c8.jpg\" style=\"width:1220px;height:4957px;\" alt=\"\" />', '2018-04-19 00:38:07', '2018-04-20 00:02:54');
INSERT INTO `tb_item_desc` VALUES ('150642571432840', '<img src=\"https://resource.smartisan.com/resource/14497b77e21fc6d0807e57bb9deabe28.jpg\" style=\"width:1220px;height:9527px;\" alt=\"\" />', '2018-04-19 00:40:40', '2018-04-20 00:04:41');
INSERT INTO `tb_item_desc` VALUES ('150642571432841', '<img src=\"https://resource.smartisan.com/resource/4272e7737eed967a8f2f10ebef9b84dc.jpg style=\"width:1220px;height:4990px;\" alt=\"\" />', '2018-04-19 00:43:48', '2018-04-20 00:06:59');
INSERT INTO `tb_item_desc` VALUES ('150642571432842', '<img src=\"https://resource.smartisan.com/resource/4cbe4a14ab225c9466e16f8c8ef4e29e.jpg\" style=\"width:1220px;height:4083px;\" alt=\"\" />', '2018-04-19 00:45:14', '2018-04-20 00:08:21');
INSERT INTO `tb_item_desc` VALUES ('150642571432843', '<img src=\"https://img20.360buyimg.com/vc/jfs/t17368/340/1617561606/1069487/9676971/5ad014b1Nb8463c4e.jpg\" width=\"1220px\" alt=\"\" />\n<img src=\"https://img20.360buyimg.com/vc/jfs/t17278/52/1620296383/1157116/3d0f473/5ad014b8N32c9c183.jpg\" width=\"1220px\" alt=\"\" />\n<img src=\"https://img20.360buyimg.com/vc/jfs/t19420/87/1609028179/1135327/9b3e0f97/5ad014c0N6234ba19.jpg\" width=\"1220px\" alt=\"\" />', '2018-04-19 22:13:31', '2018-07-13 22:03:52');
INSERT INTO `tb_item_desc` VALUES ('150642571432844', '<img src=\"https://img20.360buyimg.com/vc/jfs/t11503/47/1494539812/1725396/3defedb6/5a046e18Ne0a5d5da.jpg\" alt=\"\" />', '2018-04-19 22:16:05', '2018-07-13 22:06:58');
INSERT INTO `tb_item_desc` VALUES ('150642571432845', '<img src=\"https://resource.smartisan.com/resource/27a054301d8e10c40461443928dccd77.jpg\" style=\"width:1220px;height:7451px;\" alt=\"\" />', '2018-04-19 22:22:02', '2018-04-20 00:19:39');
INSERT INTO `tb_item_desc` VALUES ('150642571432846', '<img src=\"https://resource.smartisan.com/resource/65e89427674ee370fa58f5fe98120679.png\" style=\"width:1220px;height:7881px;\" alt=\"\" />', '2018-04-19 22:23:39', '2018-04-20 00:23:09');
INSERT INTO `tb_item_desc` VALUES ('150642571432847', '<img src=\"https://resource.smartisan.com/resource/41338ebac06fc82450f8b8e4867df257.jpg\" style=\"width:1220px;height:5043px;\" alt=\"\" />', '2018-04-19 22:25:41', '2018-04-20 10:51:53');
INSERT INTO `tb_item_desc` VALUES ('150642571432848', '<img src=\"https://resource.smartisan.com/resource/debb893778547fb9d5a6119b376d9ec1.jpg\" style=\"width:1220px;height:6879px;\" alt=\"\" />', '2018-04-19 22:28:11', '2018-04-20 10:53:15');
INSERT INTO `tb_item_desc` VALUES ('150642571432849', '<img src=\"https://resource.smartisan.com/resource/f03a523847e63f28f9238aad45567b37.jpg\" style=\"width:1220px;height:7935px;\" alt=\"\" />', '2018-04-19 22:31:09', '2018-04-21 11:26:31');
INSERT INTO `tb_item_desc` VALUES ('150642571432850', '<img src=\"https://resource.smartisan.com/resource/f6de19257228641b1a0c1964239b19b7.jpg\" style=\"width:1220px;height:9970px;\" alt=\"\" />', '2018-04-19 22:32:38', '2018-04-20 11:17:31');
INSERT INTO `tb_item_desc` VALUES ('150642571432851', '<img src=\"https://resource.smartisan.com/resource/a1f3fbf662376e8684e528f05721b286.jpg\" style=\"width:1220px;height:14998px;\" alt=\"\" />', '2018-04-19 22:36:50', '2018-04-20 00:13:44');

-- ----------------------------
-- Table structure for `tb_member`
-- ----------------------------
DROP TABLE IF EXISTS `tb_member`;
CREATE TABLE `tb_member` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `state` int DEFAULT '0' COMMENT '会员级别 默认0',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '可以不管',
  `points` bigint DEFAULT '0' COMMENT '经验值',
  `starttime` datetime DEFAULT NULL COMMENT '开通日期',
  `endtime` datetime DEFAULT NULL COMMENT '过期日期',
  `updated` datetime NOT NULL COMMENT '更新时间',
  `created` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of tb_member
-- ----------------------------
INSERT INTO `tb_member` VALUES ('62', '1', null, null, null, null, '2017-10-08 18:13:51', '2017-09-05 21:27:54');
INSERT INTO `tb_member` VALUES ('63', '2', null, null, null, null, '2018-04-18 14:43:32', '2017-09-05 21:27:54');
INSERT INTO `tb_member` VALUES ('64', '2', null, null, null, null, '2018-04-18 14:43:33', '2017-09-05 21:27:54');
INSERT INTO `tb_member` VALUES ('65', '0', null, null, null, null, '2017-10-17 21:21:32', '2017-09-05 21:27:54');

-- ----------------------------
-- Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `payment` decimal(10,2) DEFAULT NULL COMMENT '实付金额',
  `payment_type` int DEFAULT NULL COMMENT '支付类型 1在线支付 2货到付款',
  `post_fee` decimal(10,2) DEFAULT NULL COMMENT '邮费',
  `status` int DEFAULT NULL COMMENT '状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `shipping_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `buyer_message` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
  `buyer_nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '买家昵称',
  `buyer_comment` tinyint(1) DEFAULT NULL COMMENT '买家是否已经评价',
  PRIMARY KEY (`order_id`),
  KEY `create_time` (`create_time`),
  KEY `buyer_nick` (`buyer_nick`),
  KEY `status` (`status`),
  KEY `payment_type` (`payment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('150787555927616', '1.00', null, null, '5', '2017-10-13 14:19:19', '2017-10-13 14:19:19', null, null, null, '2017-10-13 14:19:35', null, null, '63', null, 'admin', null);

-- ----------------------------
-- Table structure for `tb_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `total_fee` decimal(10,2) DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------
INSERT INTO `tb_order_item` VALUES ('150787555927880', '150642571432835', '150787555927616', '1', '捐赠商品', '1.00', '1.00', 'http://ow2h3ee9w.bkt.clouddn.com/FgwHSk1Rnd-8FKqNJhFSSdcq2QVB');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '超级管理员', '拥有至高无上的权力');
INSERT INTO `tb_role` VALUES ('2', '游客', '只是个过客');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '瀵嗙爜 md5鍔犲瘑瀛樺偍',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `sex` varchar(2) DEFAULT '',
  `address` varchar(255) DEFAULT NULL,
  `state` int DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT '0',
  `file` varchar(255) DEFAULT NULL COMMENT '头像',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '17621230884', '1012139570@qq.com', '男', null, '1', '超级管理员', '1', 'http://ow2h3ee9w.bkt.clouddn.com/1507866340369.png', '2017-09-05 21:27:54', '2017-10-18 22:57:08');
INSERT INTO `tb_user` VALUES ('2', 'test', '$2a$10$zQTbhwCQKXeTqzKxAI9IbO8hvOkGgijtwMN2ebpKAJjAAUMBGmmf6', '12345678901', '123@qq.com', '女', null, '1', '游客', '0', null, '2017-09-05 21:27:54', '2018-04-18 14:35:19');

-- ----------------------------
-- Table structure for `tb_user_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_coupon`;
CREATE TABLE `tb_user_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint NOT NULL COMMENT '用户id',
  `cid` bigint NOT NULL COMMENT '优惠卷id',
  `end_time` datetime NOT NULL COMMENT '到期时间',
  `created` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tb_user_coupon
-- ----------------------------
