/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : chengfeng1_5

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/03/2019 19:06:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_book_repair
-- ----------------------------
DROP TABLE IF EXISTS `tb_book_repair`;
CREATE TABLE `tb_book_repair`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `address` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `description` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '报修描述',
  `hope_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '期望上门修复时间',
  `user_id` int(11) NULL DEFAULT 1 COMMENT '报修用户',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_book_repair
-- ----------------------------
INSERT INTO `tb_book_repair` VALUES (1, '17864195311', '北京市西城区', '报修报修', '2019-03-14 15:28:38', 1, '2019-02-23 22:48:09', '2019-02-23 22:48:09');
INSERT INTO `tb_book_repair` VALUES (2, '17864195311', '北京市东城区', 'hdfs报修服务', '2019-02-26 18:20:10', 7, '2019-02-26 18:20:10', '2019-02-26 18:20:10');
INSERT INTO `tb_book_repair` VALUES (3, '17864195311', '山东省济南市长清区山东师范大学', NULL, NULL, 1, '2019-03-14 16:03:56', '2019-03-14 16:03:56');
INSERT INTO `tb_book_repair` VALUES (4, '17864195311', '山东省济南市长清区山东师范大学', NULL, NULL, 2, '2019-03-14 16:05:01', '2019-03-14 16:05:01');

-- ----------------------------
-- Table structure for tb_book_secretary
-- ----------------------------
DROP TABLE IF EXISTS `tb_book_secretary`;
CREATE TABLE `tb_book_secretary`  (
  `id` int(11) NOT NULL DEFAULT 1,
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书记电话',
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书记邮箱',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_book_watersuply
-- ----------------------------
DROP TABLE IF EXISTS `tb_book_watersuply`;
CREATE TABLE `tb_book_watersuply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `hope_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '期望送水时间',
  `address` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '送水地址',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他附加信息',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_book_watersuply
-- ----------------------------
INSERT INTO `tb_book_watersuply` VALUES (1, '1786195311', '2019-02-26 14:49:36', '北京市海淀区什刹海恭王府', '水不多了快点送水', '2019-02-22 23:33:45', '2019-02-22 23:33:45');
INSERT INTO `tb_book_watersuply` VALUES (2, '17864195311', '2019-02-26 14:49:36', '北京市昌平区五道口', '上次送的水不错再送点来', '2019-02-23 22:47:08', '2019-02-23 22:47:08');
INSERT INTO `tb_book_watersuply` VALUES (3, '17864195311', '1974-12-11 17:58:18', '北京市昌平区五道口', '水不多了', '2019-02-26 18:58:42', '2019-02-26 18:58:42');
INSERT INTO `tb_book_watersuply` VALUES (4, '17864195311', '1974-12-11 17:58:18', '北京市昌平区五道口', '水不多了', '2019-02-26 18:59:08', '2019-02-26 18:59:08');
INSERT INTO `tb_book_watersuply` VALUES (5, '17864195311', '1974-12-11 17:58:18', '北京市昌平区五道口', '水不多了', '2019-02-26 18:59:08', '2019-02-26 18:59:08');
INSERT INTO `tb_book_watersuply` VALUES (6, '17864195311', '2019-03-14 17:20:10', '山东省滨州市邹平县', NULL, '2019-03-14 17:20:10', '2019-03-14 17:20:10');
INSERT INTO `tb_book_watersuply` VALUES (7, '17864195311', '2019-03-14 17:22:41', '山东省滨州市邹平县', NULL, '2019-03-14 17:22:41', '2019-03-14 17:22:41');
INSERT INTO `tb_book_watersuply` VALUES (8, '17864195311', '2019-03-14 17:26:38', '山东省滨州市邹平县', NULL, '2019-03-14 17:26:38', '2019-03-14 17:26:38');
INSERT INTO `tb_book_watersuply` VALUES (9, '17864195311', '2019-03-14 17:35:46', '山东省滨州市邹平县', NULL, '2019-03-14 17:35:46', '2019-03-14 17:35:46');
INSERT INTO `tb_book_watersuply` VALUES (10, '17864195311', '2019-03-14 17:40:46', '山东省滨州市邹平县', NULL, '2019-03-14 17:40:46', '2019-03-14 17:40:46');
INSERT INTO `tb_book_watersuply` VALUES (11, '17864195311', '2019-03-14 17:42:02', '山东省滨州市邹平县', NULL, '2019-03-14 17:42:02', '2019-03-14 17:42:02');
INSERT INTO `tb_book_watersuply` VALUES (12, '17864195311', '2019-03-14 17:43:08', '山东省滨州市邹平县', NULL, '2019-03-14 17:43:08', '2019-03-14 17:43:08');

-- ----------------------------
-- Table structure for tb_brand_water
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand_water`;
CREATE TABLE `tb_brand_water`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '品牌',
  `price` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '价格',
  `user_id` int(11) NOT NULL DEFAULT 1 COMMENT '所属用户',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_brand_water
-- ----------------------------
INSERT INTO `tb_brand_water` VALUES (1, '百岁山', 41, 1, '2019-02-23 22:18:46', '2019-02-23 22:18:46');
INSERT INTO `tb_brand_water` VALUES (2, '雀巢纯水', 23, 1, '2019-02-23 22:19:04', '2019-02-23 22:19:04');
INSERT INTO `tb_brand_water` VALUES (3, '怡宝纯净水', 20, 1, '2019-02-23 22:19:23', '2019-02-23 22:19:23');
INSERT INTO `tb_brand_water` VALUES (4, '农夫山泉', 20, 1, '2019-02-23 22:19:52', '2019-02-23 22:19:52');
INSERT INTO `tb_brand_water` VALUES (5, '娃哈哈矿泉水', 25, 1, '2019-02-23 22:20:04', '2019-02-23 22:20:04');
INSERT INTO `tb_brand_water` VALUES (6, '恒大天然', 24, 2, '2019-02-23 22:20:29', '2019-02-23 22:20:29');

-- ----------------------------
-- Table structure for tb_community
-- ----------------------------
DROP TABLE IF EXISTS `tb_community`;
CREATE TABLE `tb_community`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '社区名',
  `address` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '社区地址',
  `admin` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '社区管理员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_community
-- ----------------------------
INSERT INTO `tb_community` VALUES (1, '中北路社区', '北京市朝阳区中北路社区', '习近平', '2019-02-23 19:33:05', '2019-02-23 19:33:05');
INSERT INTO `tb_community` VALUES (2, '崮云湖社区', '山东省济南市崮云湖社区', '王沪宁', '2019-02-24 21:25:14', '2019-02-24 21:25:14');
INSERT INTO `tb_community` VALUES (3, '邹平县西董社区', '山东省邹平县西董街道办事处', '王小灏', '2019-03-11 13:10:32', '2019-03-11 13:10:32');

-- ----------------------------
-- Table structure for tb_crypt_password
-- ----------------------------
DROP TABLE IF EXISTS `tb_crypt_password`;
CREATE TABLE `tb_crypt_password`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crypt_password` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '加密生成的密码',
  `crypt_mode` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '加密模式',
  `crypt_salt` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '加密盐值',
  `user_id` int(11) NOT NULL DEFAULT 1 COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_crypt_password
-- ----------------------------
INSERT INTO `tb_crypt_password` VALUES (1, 'password', 'BCrypt', '', 2);
INSERT INTO `tb_crypt_password` VALUES (2, '53AFC77627779DA2A5BC47F4B008A4A5', 'BCrypt', '', 3);
INSERT INTO `tb_crypt_password` VALUES (3, '80563D5A49AB5196A26D6ADCAD9B77C3', 'BCrypt', '', 4);
INSERT INTO `tb_crypt_password` VALUES (4, '53AFC77627779DA2A5BC47F4B008A4A5', 'BCrypt', '', 5);
INSERT INTO `tb_crypt_password` VALUES (5, '80563D5A49AB5196A26D6ADCAD9B77C3', 'BCrypt', '', 6);
INSERT INTO `tb_crypt_password` VALUES (6, '70870CEECF2ED6F968D244B39C6826E8', 'BCrypt', '', 7);
INSERT INTO `tb_crypt_password` VALUES (7, '53AFC77627779DA2A5BC47F4B008A4A5', 'BCrypt', '', 8);
INSERT INTO `tb_crypt_password` VALUES (8, '53AFC77627779DA2A5BC47F4B008A4A5', 'BCrypt', '', 9);
INSERT INTO `tb_crypt_password` VALUES (9, '123456', 'BCrypt', '', 1);
INSERT INTO `tb_crypt_password` VALUES (10, 'password', '', '', 10);
INSERT INTO `tb_crypt_password` VALUES (11, 'password', '', '', 11);

-- ----------------------------
-- Table structure for tb_journalism
-- ----------------------------
DROP TABLE IF EXISTS `tb_journalism`;
CREATE TABLE `tb_journalism`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新闻标题',
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻描述',
  `images` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻中的图片地址,逗号分隔',
  `videos` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻中的视频地址,逗号分隔',
  `publish_time` timestamp(0) NULL DEFAULT NULL COMMENT '发布时间',
  `publish_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_journalism_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_journalism_comment`;
CREATE TABLE `tb_journalism_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论标题',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论人名',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论人头像',
  `comment_time` timestamp(0) NULL DEFAULT NULL COMMENT '评论日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_journalism_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_journalism_content`;
CREATE TABLE `tb_journalism_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文字内容',
  `image_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片内容',
  `video_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频内容',
  `journalism_id` int(11) NULL DEFAULT NULL COMMENT '新闻id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_notice_community
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice_community`;
CREATE TABLE `tb_notice_community`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '通知名称',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述信息',
  `showtime` timestamp(0) NULL DEFAULT NULL COMMENT '添加的时间',
  `community_id` int(11) NOT NULL DEFAULT 1 COMMENT '所属社区',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_notice_community
-- ----------------------------
INSERT INTO `tb_notice_community` VALUES (1, '东门社区党群服务中心3月份活动预告', '社区居民可前来参与会议，特此公告。地址：解放路2003号金世界六楼东门社区工作站', '2019-02-03 00:00:00', 1, '2019-02-22 16:39:14', '2019-02-22 16:39:14');
INSERT INTO `tb_notice_community` VALUES (2, '“榜样就在身边”东门社区党员志愿服务活动', '社区居民可前来参与会议，特此公告。地址：解放路2003号金世界六楼东门社区工作站', '2019-01-03 00:00:00', 1, '2019-02-22 16:39:27', '2019-02-22 16:39:27');
INSERT INTO `tb_notice_community` VALUES (3, '东门社区关于召开2019年民生微实事第一次居民议事会议公告', '基本上是不会同意的，但是就算心理建设时候可以做到以后和家人一刀两断因为够独立，但是请不要说或者做，因为我们是社会人，父母的感情真的不能断，是可以有能力而不能切断的感情，各个层次都是。', '2014-12-03 00:00:00', 1, '2019-02-22 16:40:15', '2019-02-22 16:40:15');
INSERT INTO `tb_notice_community` VALUES (4, '东门社区关于召开2019年民生微实事第一次居民议事会议公告', '感情有着极大的鼓舞力量，因此，它是一切道德行为的重要前提，谁要是没有强烈的志向，也就不能够热烈地把这个志向体现于事业中。', '2015-07-03 00:00:00', 1, '2019-02-22 16:41:44', '2019-02-22 16:41:44');
INSERT INTO `tb_notice_community` VALUES (5, '东门社区关于召开2019年民生微实事第一次居民议事会议公告', '生活赋予我们一种巨大的和无限高贵的礼品，这就是青春：充满着力量，充满着期待志愿，充满着求知和斗争的志向，充满着希望信心和青春', '2018-10-06 16:45:45', 1, '2019-02-22 16:43:50', '2019-02-22 16:43:50');
INSERT INTO `tb_notice_community` VALUES (6, '东门社区党群服务中心3月份活动预告', '有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了', '2017-09-22 16:46:16', 1, '2019-02-22 16:43:57', '2019-02-22 16:43:57');
INSERT INTO `tb_notice_community` VALUES (7, '“榜样就在身边”东门社区党员志愿服务活动', '你的假装努力，欺骗的只有你自己，永远不要用战术上的勤奋，来掩饰战略上的懒惰。　　2、成长是一场和自己的比赛，不要担心别人会做得比你好，你只需要每天都做得比前一天好就可以了', '2013-08-22 16:46:36', 1, '2019-02-22 16:44:04', '2019-02-22 16:44:04');
INSERT INTO `tb_notice_community` VALUES (8, '东门社区党群服务中心3月份活动预告', '命运要你成长的时候，总会安排一些让你不顺心的人或事刺激你　　1、不要做刺猬，能不与人结仇就不与人结仇，谁也不跟谁一辈子，有些事情没必要记在心上。', '2018-07-12 16:46:59', 1, '2019-02-22 16:44:28', '2019-02-22 16:44:28');
INSERT INTO `tb_notice_community` VALUES (9, '“榜样就在身边”东门社区党员志愿服务活动', '一定不要把别人都当傻子，事实上，所有你能遇到的人都比你聪明。如果你能抱着这样的心态为人处世，那么你的人脉会越来越宽，财富越来越多，人生也就越来越好！', '2018-06-09 16:47:07', 1, '2019-02-22 16:44:32', '2019-02-22 16:44:32');
INSERT INTO `tb_notice_community` VALUES (10, '“榜样就在身边”东门社区党员志愿服务活动', '慢慢的才知道：坚持未必就是胜利，放弃未必就是认输，。给自己一个迂回的空间，学会思索，学会等待，学会调整。人生没有假设，当下即是全部。背不动的，放下了；伤不起的，看淡了；想', '2018-05-08 16:47:15', 1, '2019-02-22 16:44:34', '2019-02-22 16:44:34');
INSERT INTO `tb_notice_community` VALUES (11, '“榜样就在身边”东门社区党员志愿服务活动', '你们应该培养对自己，对自己的力量的信心，百这种信心是靠克服障碍，培养意志和锻炼意志而获得的。　　3、坚强的信念能赢得强者的心，并使他们变得更坚强。', '2018-04-24 16:47:25', 1, '2019-02-22 16:44:35', '2019-02-22 16:44:35');
INSERT INTO `tb_notice_community` VALUES (12, '东门社区党群服务中心3月份活动预告', '离别过方知道相聚的欢乐，失去过方知道拥有的满足。故此应珍惜眼前之人，彼此要懂得相亲相爱。', '2018-03-16 16:47:36', 1, '2019-02-22 16:44:37', '2019-02-22 16:44:37');
INSERT INTO `tb_notice_community` VALUES (13, '东门社区党群服务中心3月份活动预告', '人为善，福虽未至，祸已远离；人为恶，祸虽未至，福已远离。　　2、身安不如心安，屋宽不如心宽。　　3、择善人而交，择善书而读，择善言而听，择善行而从。　　', '2018-02-22 16:47:44', 1, '2019-02-22 16:44:39', '2019-02-22 16:44:39');
INSERT INTO `tb_notice_community` VALUES (14, '东门社区党群服务中心3月份活动预告', '智者顺时而谋，愚者逆时而动。　　2、“我欲”是贫穷的标志。事能常足，心常惬，人到无求品自高。　　3、人之心胸，多欲则窄，寡欲则宽。', '2018-01-10 16:47:52', 1, '2019-02-22 16:44:41', '2019-02-22 16:44:41');
INSERT INTO `tb_notice_community` VALUES (15, '12月份补缴水费通知', '青春并不是生命中一段时光，它是心灵上的一种状况。它跟丰润的面颊，殷红的嘴唇，柔滑的膝盖无关。它是一种沉静的意志，想象的能力，感情的活力，它更是生命之泉的新血液。 —', '2017-12-31 16:48:14', 1, '2019-02-22 16:44:43', '2019-02-22 16:44:43');
INSERT INTO `tb_notice_community` VALUES (16, '东门社区党群服务中心3月份活动预告', '大多数人想要改造这个世界，但却罕有人想改造自己。　　2、积极的人在每一次忧患中都看到一个机会， 而消极的人则在每个机会都看到某种忧患。', '2017-11-01 16:48:21', 1, '2019-02-22 16:44:46', '2019-02-22 16:44:46');
INSERT INTO `tb_notice_community` VALUES (17, '10月份补缴水费通知', '没法直视他的舞蹈，令人反胃。性感的顶胯动作可以为舞蹈增添魅力，可是性感不等于骚，他做出来的动作，不是让我脸红心跳，是反胃想吐，明白吗？', '2017-01-06 00:00:00', 1, '2019-02-22 16:39:14', '2019-02-22 16:39:14');
INSERT INTO `tb_notice_community` VALUES (18, '9月份补缴水费通知\r\n', '这是淘宝造物节走红毯的蔡徐坤，视觉中国拍的图，都说能经过视觉中国检验的才是真帅哥，蔡徐坤显然脸塌下来了，这身材五五分，脸上油腻腻，他嘴巴本来就很厚，香肠嘴，又没气质，这图还没脖子？看起来好猥琐。', '2016-01-03 00:00:00', 1, '2019-02-22 16:39:27', '2019-02-22 16:39:27');
INSERT INTO `tb_notice_community` VALUES (19, '8月份补缴水费通知\r\n', '基本上是不会同意的，但是就算心理建设时候可以做到以后和家人一刀两断因为够独立，但是请不要说或者做，因为我们是社会人，父母的感情真的不能断，是可以有能力而不能切断的感情，各个层次都是。', '2018-12-03 00:00:00', 1, '2019-02-22 16:40:15', '2019-02-22 16:40:15');
INSERT INTO `tb_notice_community` VALUES (20, '东门社区党群服务中心3月份活动预告', '感情有着极大的鼓舞力量，因此，它是一切道德行为的重要前提，谁要是没有强烈的志向，也就不能够热烈地把这个志向体现于事业中。', '2018-11-03 00:00:00', 1, '2019-02-22 16:41:44', '2019-02-22 16:41:44');
INSERT INTO `tb_notice_community` VALUES (21, '东门社区党群服务中心3月份活动预告', '生活赋予我们一种巨大的和无限高贵的礼品，这就是青春：充满着力量，充满着期待志愿，充满着求知和斗争的志向，充满着希望信心和青春', '2018-10-01 16:45:45', 1, '2019-02-22 16:43:50', '2019-02-22 16:43:50');
INSERT INTO `tb_notice_community` VALUES (22, '5月份补缴水费通知', '有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了', '2018-09-22 16:46:16', 1, '2019-02-22 16:43:57', '2019-02-22 16:43:57');
INSERT INTO `tb_notice_community` VALUES (23, '4月份补缴水费通知', '你的假装努力，欺骗的只有你自己，永远不要用战术上的勤奋，来掩饰战略上的懒惰。　　2、成长是一场和自己的比赛，不要担心别人会做得比你好，你只需要每天都做得比前一天好就可以了', '2018-08-22 16:46:36', 1, '2019-02-22 16:44:04', '2019-02-22 16:44:04');
INSERT INTO `tb_notice_community` VALUES (24, '3月份补缴水费通知', '命运要你成长的时候，总会安排一些让你不顺心的人或事刺激你　　1、不要做刺猬，能不与人结仇就不与人结仇，谁也不跟谁一辈子，有些事情没必要记在心上。', '2018-07-12 16:46:59', 1, '2019-02-22 16:44:28', '2019-02-22 16:44:28');
INSERT INTO `tb_notice_community` VALUES (25, '2月份补缴水费通知', '一定不要把别人都当傻子，事实上，所有你能遇到的人都比你聪明。如果你能抱着这样的心态为人处世，那么你的人脉会越来越宽，财富越来越多，人生也就越来越好！', '2018-06-09 16:47:07', 1, '2019-02-22 16:44:32', '2019-02-22 16:44:32');
INSERT INTO `tb_notice_community` VALUES (26, '1月份补缴水费通知', '慢慢的才知道：坚持未必就是胜利，放弃未必就是认输，。给自己一个迂回的空间，学会思索，学会等待，学会调整。人生没有假设，当下即是全部。背不动的，放下了；伤不起的，看淡了；想', '2018-05-08 16:47:15', 1, '2019-02-22 16:44:34', '2019-02-22 16:44:34');
INSERT INTO `tb_notice_community` VALUES (27, '4月份补缴水费通知', '你们应该培养对自己，对自己的力量的信心，百这种信心是靠克服障碍，培养意志和锻炼意志而获得的。　　3、坚强的信念能赢得强者的心，并使他们变得更坚强。', '2018-04-24 16:47:25', 1, '2019-02-22 16:44:35', '2019-02-22 16:44:35');
INSERT INTO `tb_notice_community` VALUES (28, '3月份补缴水费通知', '离别过方知道相聚的欢乐，失去过方知道拥有的满足。故此应珍惜眼前之人，彼此要懂得相亲相爱。', '2018-03-16 16:47:36', 1, '2019-02-22 16:44:37', '2019-02-22 16:44:37');
INSERT INTO `tb_notice_community` VALUES (29, '2月份补缴水费通知', '人为善，福虽未至，祸已远离；人为恶，祸虽未至，福已远离。　　2、身安不如心安，屋宽不如心宽。　　3、择善人而交，择善书而读，择善言而听，择善行而从。　　', '2018-02-22 16:47:44', 1, '2019-02-22 16:44:39', '2019-02-22 16:44:39');
INSERT INTO `tb_notice_community` VALUES (30, '1月份补缴水费通知', '智者顺时而谋，愚者逆时而动。　　2、“我欲”是贫穷的标志。事能常足，心常惬，人到无求品自高。　　3、人之心胸，多欲则窄，寡欲则宽。', '2018-01-10 16:47:52', 1, '2019-02-22 16:44:41', '2019-02-22 16:44:41');
INSERT INTO `tb_notice_community` VALUES (31, '12月份补缴水费通知', '青春并不是生命中一段时光，它是心灵上的一种状况。它跟丰润的面颊，殷红的嘴唇，柔滑的膝盖无关。它是一种沉静的意志，想象的能力，感情的活力，它更是生命之泉的新血液。 —', '2017-12-31 16:48:14', 1, '2019-02-22 16:44:43', '2019-02-22 16:44:43');
INSERT INTO `tb_notice_community` VALUES (32, '11月份补缴水费通知', '大多数人想要改造这个世界，但却罕有人想改造自己。　　2、积极的人在每一次忧患中都看到一个机会， 而消极的人则在每个机会都看到某种忧患。', '2017-11-01 16:48:21', 1, '2019-02-22 16:44:46', '2019-02-22 16:44:46');
INSERT INTO `tb_notice_community` VALUES (34, '7月份补缴水费通知', '大多数人想要改造这个世界，但却罕有人想改造自己。　　2、积极的人在每一次忧患中都看到一个机会， 而消极的人则在每个机会都看到某种忧患。', '2019-02-23 10:10:24', 1, '2019-02-23 10:10:10', '2019-02-23 10:10:10');
INSERT INTO `tb_notice_community` VALUES (35, '5月份补缴水费通知', '青春并不是生命中一段时光，它是心灵上的一种状况。它跟丰润的面颊，殷红的嘴唇，柔滑的膝盖无关。它是一种沉静的意志，想象的能力，感情的活力，它更是生命之泉的新血液。 —', '2016-05-23 10:15:23', 1, '2019-02-23 10:15:17', '2019-02-23 10:15:17');
INSERT INTO `tb_notice_community` VALUES (36, '3月份补缴水费通知', '万事起于忽微，量变引起质变', '2019-03-01 22:49:27', 1, '2019-02-23 22:49:36', '2019-02-23 22:49:36');

-- ----------------------------
-- Table structure for tb_notice_proper
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice_proper`;
CREATE TABLE `tb_notice_proper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '通知名称',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述信息',
  `showtime` timestamp(0) NULL DEFAULT NULL COMMENT '添加的时间',
  `user_id` int(11) NOT NULL DEFAULT 1 COMMENT '所属用户',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_notice_proper
-- ----------------------------
INSERT INTO `tb_notice_proper` VALUES (1, '2月份补缴水费通知', '没法直视他的舞蹈，令人反胃。性感的顶胯动作可以为舞蹈增添魅力，可是性感不等于骚，他做出来的动作，不是让我脸红心跳，是反胃想吐，明白吗？', '2019-02-03 00:00:00', 1, '2019-02-22 16:39:14', '2019-02-22 16:39:14');
INSERT INTO `tb_notice_proper` VALUES (2, '1月份补缴水费通知\r\n', '这是淘宝造物节走红毯的蔡徐坤，视觉中国拍的图，都说能经过视觉中国检验的才是真帅哥，蔡徐坤显然脸塌下来了，这身材五五分，脸上油腻腻，他嘴巴本来就很厚，香肠嘴，又没气质，这图还没脖子？看起来好猥琐。', '2019-01-03 00:00:00', 1, '2019-02-22 16:39:27', '2019-02-22 16:39:27');
INSERT INTO `tb_notice_proper` VALUES (3, '12月份补缴水费通知\r\n', '基本上是不会同意的，但是就算心理建设时候可以做到以后和家人一刀两断因为够独立，但是请不要说或者做，因为我们是社会人，父母的感情真的不能断，是可以有能力而不能切断的感情，各个层次都是。', '2018-12-03 00:00:00', 1, '2019-02-22 16:40:15', '2019-02-22 16:40:15');
INSERT INTO `tb_notice_proper` VALUES (4, '11月份补缴水费通知', '感情有着极大的鼓舞力量，因此，它是一切道德行为的重要前提，谁要是没有强烈的志向，也就不能够热烈地把这个志向体现于事业中。', '2018-11-03 00:00:00', 1, '2019-02-22 16:41:44', '2019-02-22 16:41:44');
INSERT INTO `tb_notice_proper` VALUES (5, '10月份补缴水费通知', '生活赋予我们一种巨大的和无限高贵的礼品，这就是青春：充满着力量，充满着期待志愿，充满着求知和斗争的志向，充满着希望信心和青春', '2018-10-01 16:45:45', 1, '2019-02-22 16:43:50', '2019-02-22 16:43:50');
INSERT INTO `tb_notice_proper` VALUES (6, '9月份补缴水费通知', '有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了', '2018-09-22 16:46:16', 1, '2019-02-22 16:43:57', '2019-02-22 16:43:57');
INSERT INTO `tb_notice_proper` VALUES (7, '8月份补缴水费通知', '你的假装努力，欺骗的只有你自己，永远不要用战术上的勤奋，来掩饰战略上的懒惰。　　2、成长是一场和自己的比赛，不要担心别人会做得比你好，你只需要每天都做得比前一天好就可以了', '2018-08-22 16:46:36', 1, '2019-02-22 16:44:04', '2019-02-22 16:44:04');
INSERT INTO `tb_notice_proper` VALUES (8, '7月份补缴水费通知', '命运要你成长的时候，总会安排一些让你不顺心的人或事刺激你　　1、不要做刺猬，能不与人结仇就不与人结仇，谁也不跟谁一辈子，有些事情没必要记在心上。', '2018-07-12 16:46:59', 1, '2019-02-22 16:44:28', '2019-02-22 16:44:28');
INSERT INTO `tb_notice_proper` VALUES (9, '6月份补缴水费通知', '一定不要把别人都当傻子，事实上，所有你能遇到的人都比你聪明。如果你能抱着这样的心态为人处世，那么你的人脉会越来越宽，财富越来越多，人生也就越来越好！', '2018-06-09 16:47:07', 1, '2019-02-22 16:44:32', '2019-02-22 16:44:32');
INSERT INTO `tb_notice_proper` VALUES (10, '5月份补缴水费通知', '慢慢的才知道：坚持未必就是胜利，放弃未必就是认输，。给自己一个迂回的空间，学会思索，学会等待，学会调整。人生没有假设，当下即是全部。背不动的，放下了；伤不起的，看淡了；想', '2018-05-08 16:47:15', 1, '2019-02-22 16:44:34', '2019-02-22 16:44:34');
INSERT INTO `tb_notice_proper` VALUES (11, '4月份补缴水费通知', '你们应该培养对自己，对自己的力量的信心，百这种信心是靠克服障碍，培养意志和锻炼意志而获得的。　　3、坚强的信念能赢得强者的心，并使他们变得更坚强。', '2018-04-24 16:47:25', 1, '2019-02-22 16:44:35', '2019-02-22 16:44:35');
INSERT INTO `tb_notice_proper` VALUES (12, '3月份补缴水费通知', '离别过方知道相聚的欢乐，失去过方知道拥有的满足。故此应珍惜眼前之人，彼此要懂得相亲相爱。', '2018-03-16 16:47:36', 1, '2019-02-22 16:44:37', '2019-02-22 16:44:37');
INSERT INTO `tb_notice_proper` VALUES (13, '2月份补缴水费通知', '人为善，福虽未至，祸已远离；人为恶，祸虽未至，福已远离。　　2、身安不如心安，屋宽不如心宽。　　3、择善人而交，择善书而读，择善言而听，择善行而从。　　', '2018-02-22 16:47:44', 1, '2019-02-22 16:44:39', '2019-02-22 16:44:39');
INSERT INTO `tb_notice_proper` VALUES (14, '1月份补缴水费通知', '智者顺时而谋，愚者逆时而动。　　2、“我欲”是贫穷的标志。事能常足，心常惬，人到无求品自高。　　3、人之心胸，多欲则窄，寡欲则宽。', '2018-01-10 16:47:52', 1, '2019-02-22 16:44:41', '2019-02-22 16:44:41');
INSERT INTO `tb_notice_proper` VALUES (15, '12月份补缴水费通知', '青春并不是生命中一段时光，它是心灵上的一种状况。它跟丰润的面颊，殷红的嘴唇，柔滑的膝盖无关。它是一种沉静的意志，想象的能力，感情的活力，它更是生命之泉的新血液。 —', '2017-12-31 16:48:14', 1, '2019-02-22 16:44:43', '2019-02-22 16:44:43');
INSERT INTO `tb_notice_proper` VALUES (16, '11月份补缴水费通知', '大多数人想要改造这个世界，但却罕有人想改造自己。　　2、积极的人在每一次忧患中都看到一个机会， 而消极的人则在每个机会都看到某种忧患。', '2017-11-01 16:48:21', 1, '2019-02-22 16:44:46', '2019-02-22 16:44:46');
INSERT INTO `tb_notice_proper` VALUES (17, '10月份补缴水费通知', '没法直视他的舞蹈，令人反胃。性感的顶胯动作可以为舞蹈增添魅力，可是性感不等于骚，他做出来的动作，不是让我脸红心跳，是反胃想吐，明白吗？', '2017-01-06 00:00:00', 1, '2019-02-22 16:39:14', '2019-02-22 16:39:14');
INSERT INTO `tb_notice_proper` VALUES (18, '9月份补缴水费通知\r\n', '这是淘宝造物节走红毯的蔡徐坤，视觉中国拍的图，都说能经过视觉中国检验的才是真帅哥，蔡徐坤显然脸塌下来了，这身材五五分，脸上油腻腻，他嘴巴本来就很厚，香肠嘴，又没气质，这图还没脖子？看起来好猥琐。', '2016-01-03 00:00:00', 1, '2019-02-22 16:39:27', '2019-02-22 16:39:27');
INSERT INTO `tb_notice_proper` VALUES (19, '8月份补缴水费通知\r\n', '基本上是不会同意的，但是就算心理建设时候可以做到以后和家人一刀两断因为够独立，但是请不要说或者做，因为我们是社会人，父母的感情真的不能断，是可以有能力而不能切断的感情，各个层次都是。', '2018-12-03 00:00:00', 1, '2019-02-22 16:40:15', '2019-02-22 16:40:15');
INSERT INTO `tb_notice_proper` VALUES (20, '7月份补缴水费通知', '感情有着极大的鼓舞力量，因此，它是一切道德行为的重要前提，谁要是没有强烈的志向，也就不能够热烈地把这个志向体现于事业中。', '2018-11-03 00:00:00', 1, '2019-02-22 16:41:44', '2019-02-22 16:41:44');
INSERT INTO `tb_notice_proper` VALUES (21, '6月份补缴水费通知', '生活赋予我们一种巨大的和无限高贵的礼品，这就是青春：充满着力量，充满着期待志愿，充满着求知和斗争的志向，充满着希望信心和青春', '2018-10-01 16:45:45', 1, '2019-02-22 16:43:50', '2019-02-22 16:43:50');
INSERT INTO `tb_notice_proper` VALUES (22, '5月份补缴水费通知', '有时候，总觉得自己的梦想很大，终点很远，像是异想天开，但其实也没有那么遥远，就这样一步一步的走，走着走着就到了', '2018-09-22 16:46:16', 1, '2019-02-22 16:43:57', '2019-02-22 16:43:57');
INSERT INTO `tb_notice_proper` VALUES (23, '4月份补缴水费通知', '你的假装努力，欺骗的只有你自己，永远不要用战术上的勤奋，来掩饰战略上的懒惰。　　2、成长是一场和自己的比赛，不要担心别人会做得比你好，你只需要每天都做得比前一天好就可以了', '2018-08-22 16:46:36', 1, '2019-02-22 16:44:04', '2019-02-22 16:44:04');
INSERT INTO `tb_notice_proper` VALUES (24, '3月份补缴水费通知', '命运要你成长的时候，总会安排一些让你不顺心的人或事刺激你　　1、不要做刺猬，能不与人结仇就不与人结仇，谁也不跟谁一辈子，有些事情没必要记在心上。', '2018-07-12 16:46:59', 1, '2019-02-22 16:44:28', '2019-02-22 16:44:28');
INSERT INTO `tb_notice_proper` VALUES (25, '2月份补缴水费通知', '一定不要把别人都当傻子，事实上，所有你能遇到的人都比你聪明。如果你能抱着这样的心态为人处世，那么你的人脉会越来越宽，财富越来越多，人生也就越来越好！', '2018-06-09 16:47:07', 1, '2019-02-22 16:44:32', '2019-02-22 16:44:32');
INSERT INTO `tb_notice_proper` VALUES (26, '1月份补缴水费通知', '慢慢的才知道：坚持未必就是胜利，放弃未必就是认输，。给自己一个迂回的空间，学会思索，学会等待，学会调整。人生没有假设，当下即是全部。背不动的，放下了；伤不起的，看淡了；想', '2018-05-08 16:47:15', 1, '2019-02-22 16:44:34', '2019-02-22 16:44:34');
INSERT INTO `tb_notice_proper` VALUES (27, '4月份补缴水费通知', '你们应该培养对自己，对自己的力量的信心，百这种信心是靠克服障碍，培养意志和锻炼意志而获得的。　　3、坚强的信念能赢得强者的心，并使他们变得更坚强。', '2018-04-24 16:47:25', 1, '2019-02-22 16:44:35', '2019-02-22 16:44:35');
INSERT INTO `tb_notice_proper` VALUES (28, '3月份补缴水费通知', '离别过方知道相聚的欢乐，失去过方知道拥有的满足。故此应珍惜眼前之人，彼此要懂得相亲相爱。', '2018-03-16 16:47:36', 1, '2019-02-22 16:44:37', '2019-02-22 16:44:37');
INSERT INTO `tb_notice_proper` VALUES (29, '2月份补缴水费通知', '人为善，福虽未至，祸已远离；人为恶，祸虽未至，福已远离。　　2、身安不如心安，屋宽不如心宽。　　3、择善人而交，择善书而读，择善言而听，择善行而从。　　', '2018-02-22 16:47:44', 1, '2019-02-22 16:44:39', '2019-02-22 16:44:39');
INSERT INTO `tb_notice_proper` VALUES (30, '1月份补缴水费通知', '智者顺时而谋，愚者逆时而动。　　2、“我欲”是贫穷的标志。事能常足，心常惬，人到无求品自高。　　3、人之心胸，多欲则窄，寡欲则宽。', '2018-01-10 16:47:52', 1, '2019-02-22 16:44:41', '2019-02-22 16:44:41');
INSERT INTO `tb_notice_proper` VALUES (31, '12月份补缴水费通知', '青春并不是生命中一段时光，它是心灵上的一种状况。它跟丰润的面颊，殷红的嘴唇，柔滑的膝盖无关。它是一种沉静的意志，想象的能力，感情的活力，它更是生命之泉的新血液。 —', '2017-12-31 16:48:14', 1, '2019-02-22 16:44:43', '2019-02-22 16:44:43');
INSERT INTO `tb_notice_proper` VALUES (32, '11月份补缴水费通知', '大多数人想要改造这个世界，但却罕有人想改造自己。　　2、积极的人在每一次忧患中都看到一个机会， 而消极的人则在每个机会都看到某种忧患。', '2017-11-01 16:48:21', 1, '2019-02-22 16:44:46', '2019-02-22 16:44:46');
INSERT INTO `tb_notice_proper` VALUES (34, '7月份补缴水费通知', '大多数人想要改造这个世界，但却罕有人想改造自己。　　2、积极的人在每一次忧患中都看到一个机会， 而消极的人则在每个机会都看到某种忧患。', '2019-02-23 10:10:24', 1, '2019-02-23 10:10:10', '2019-02-23 10:10:10');
INSERT INTO `tb_notice_proper` VALUES (35, '5月份补缴水费通知', '青春并不是生命中一段时光，它是心灵上的一种状况。它跟丰润的面颊，殷红的嘴唇，柔滑的膝盖无关。它是一种沉静的意志，想象的能力，感情的活力，它更是生命之泉的新血液。 —', '2016-05-23 10:15:23', 1, '2019-02-23 10:15:17', '2019-02-23 10:15:17');
INSERT INTO `tb_notice_proper` VALUES (36, '3月份补缴水费通知', '万事起于忽微，量变引起质变', '2019-03-01 22:49:27', 1, '2019-02-23 22:49:36', '2019-02-23 22:49:36');

-- ----------------------------
-- Table structure for tb_post_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_post_news`;
CREATE TABLE `tb_post_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '帖子标题',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '帖子描述',
  `news_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '帖子详情',
  `img_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片url地址,多张图片地址，分隔',
  `posted` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '帖子发布的时间',
  `star` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `comments` int(11) NULL DEFAULT 0 COMMENT '评论数',
  `type` tinyint(10) NULL DEFAULT 0 COMMENT '帖子的类型,0表示普通帖子,1表示热门帖子,2表示精华帖子',
  `user_id` int(11) NOT NULL DEFAULT 1 COMMENT '发布者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_post_news
-- ----------------------------
INSERT INTO `tb_post_news` VALUES (1, '我最近都在做什么呢?', '从前有座山,山上有个庙', NULL, 'https://dads_da.jpg', '2019-03-04 15:03:44', 0, 0, 1, 1);
INSERT INTO `tb_post_news` VALUES (2, '你说你在做什么呢', '从前有座山,山上有个庙', NULL, 'https://dads_da.jpg', '2019-02-24 20:45:23', 0, 0, 0, 1);
INSERT INTO `tb_post_news` VALUES (3, '我最近都在做什么呢?', '从前有座山,山上有个庙', NULL, 'https://dads_da.jpg', '2019-03-04 15:03:47', 0, 0, 1, 5);
INSERT INTO `tb_post_news` VALUES (4, 'Nginx实现动静分离', '本文利用Nginx实现简单动静分离，本文使用Nginx和一个SpringBoot简单Web应用实现。', NULL, 'https://dads_da.jpg', '2019-02-25 13:18:21', 0, 0, 0, 5);
INSERT INTO `tb_post_news` VALUES (5, '如何给女朋友解释什么是反向代理？', '反向代理包括正向代理和反向代理', NULL, 'https://dads_da.jpg', '2019-02-25 19:46:34', 0, 0, 0, 5);
INSERT INTO `tb_post_news` VALUES (6, 'JVM虚拟机', 'JVM虚拟机正在变好', NULL, 'https://dads_da.jpg', '2019-03-04 15:03:50', 0, 0, 1, 7);
INSERT INTO `tb_post_news` VALUES (7, '我最近都在做什么呢?', '从前有座山,山上有个庙', NULL, NULL, '2019-02-26 19:59:43', 0, 0, 0, 9);
INSERT INTO `tb_post_news` VALUES (8, '我最近都在做什么呢?', '从前有座山,山上有个庙', NULL, NULL, '2019-02-26 20:00:52', 0, 0, 0, 9);
INSERT INTO `tb_post_news` VALUES (9, '我最近都在做什么呢?', '从前有座山,山上有个庙', NULL, 'http://pmt5ma5mu.bkt.clouddn.com/52350542-29b6-4c96-988c-b251ac649c9b.jpg', '2019-02-26 20:11:08', 0, 0, 0, 10);
INSERT INTO `tb_post_news` VALUES (10, '我最近都在做什么呢?', '从前有座山,山上有个庙', NULL, 'http://pmt5ma5mu.bkt.clouddn.com/52350542-29b6-4c96-988c-b251ac649c9b.jpg', '2019-03-04 14:35:34', 0, 0, 0, 11);

-- ----------------------------
-- Table structure for tb_post_reply
-- ----------------------------
DROP TABLE IF EXISTS `tb_post_reply`;
CREATE TABLE `tb_post_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回帖内容',
  `img_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片url,多张图片,分隔',
  `reply_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '回帖的时间',
  `star` int(11) NULL DEFAULT 0 COMMENT '回帖点赞数',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '0表示没有父级id,表明这就是回帖而不是回帖的回帖',
  `is_parent` int(11) NULL DEFAULT 0 COMMENT '是否含有子结点,0表示不含子结点,1表示含子结点',
  `post_id` int(11) NULL DEFAULT 1 COMMENT '帖子id或者回帖id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_post_reply
-- ----------------------------
INSERT INTO `tb_post_reply` VALUES (4, '你这个帖子还行', 'https://dasda.jpg', '2019-02-25 02:22:13', 0, 0, 1, 3);
INSERT INTO `tb_post_reply` VALUES (5, '你这个帖子还凑合', 'https://dasda.jpg', '2019-02-25 02:01:49', 0, 0, 1, 3);
INSERT INTO `tb_post_reply` VALUES (6, '冬雪心境你的回帖不行啊', 'https://dasda.jpg', '2019-02-25 02:17:42', 0, 4, 1, 3);
INSERT INTO `tb_post_reply` VALUES (7, '冬雪心境你的回帖不行啊', 'https://dasda.jpg', '2019-02-25 02:18:29', 0, 4, 0, 3);
INSERT INTO `tb_post_reply` VALUES (8, '寒泉子就你的回帖行？', 'https://dasda.jpg', '2019-02-25 02:18:10', 0, 6, 0, 3);
INSERT INTO `tb_post_reply` VALUES (9, '你这个帖子可真有意思', 'https://dasda.jpg', '2019-02-25 02:50:12', 0, 5, 0, 3);
INSERT INTO `tb_post_reply` VALUES (10, '你这个帖子可真有意思', 'https://dasda.jpg', '2019-02-25 19:50:11', 0, 0, 0, 6);
INSERT INTO `tb_post_reply` VALUES (11, '你这个帖子可真有意思', 'https://dasda.jpg', '2019-03-04 12:56:16', 0, 0, 0, 1);
INSERT INTO `tb_post_reply` VALUES (12, '你这个帖子可真有意思', 'https://dasda.jpg', '2019-03-04 12:56:27', 0, 0, 0, 1);
INSERT INTO `tb_post_reply` VALUES (13, '你这个帖子可真有意思', 'https://dasda.jpg', '2019-03-04 13:16:26', 0, 11, 0, 1);
INSERT INTO `tb_post_reply` VALUES (14, '你这个帖子可真有意思', 'https://dasda.jpg', '2019-03-04 13:20:06', 0, 6, 0, 3);

-- ----------------------------
-- Table structure for tb_post_vote
-- ----------------------------
DROP TABLE IF EXISTS `tb_post_vote`;
CREATE TABLE `tb_post_vote`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '投票标题',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题描述',
  `vote_total` int(11) NULL DEFAULT NULL COMMENT '总的投票人数',
  `choice` int(11) NULL DEFAULT 0 COMMENT '0表示单选1表示多选',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `validity_time` timestamp(0) NULL DEFAULT NULL COMMENT '截止日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_post_vote
-- ----------------------------
INSERT INTO `tb_post_vote` VALUES (1, 'rqwerqwerqwqrwr', 'toudaasdadfqfwer2erqwerqwerqw', NULL, 0, 1, NULL);
INSERT INTO `tb_post_vote` VALUES (2, 'rqwerqwerqwqrwr', 'toudaasdadfqfwer2erqwerqwerqw', NULL, 0, 1, NULL);
INSERT INTO `tb_post_vote` VALUES (3, 'rqwerqwerqwqrwr', 'toudaasdadfqfwer2erqwerqwerqw', NULL, 0, 1, NULL);
INSERT INTO `tb_post_vote` VALUES (4, 'xxx投票', 'xxx描述', NULL, 0, 1, NULL);
INSERT INTO `tb_post_vote` VALUES (5, 'xxx投票', 'xxx描述', NULL, 0, 1, NULL);
INSERT INTO `tb_post_vote` VALUES (6, 'xxx投票', 'xxx描述', NULL, 0, 1, NULL);

-- ----------------------------
-- Table structure for tb_purchase_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_category`;
CREATE TABLE `tb_purchase_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100031 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase_category
-- ----------------------------
INSERT INTO `tb_purchase_category` VALUES (1, 0, '家用电器');
INSERT INTO `tb_purchase_category` VALUES (2, 0, '数码3C');
INSERT INTO `tb_purchase_category` VALUES (3, 0, '服装箱包');
INSERT INTO `tb_purchase_category` VALUES (4, 0, '食品生鲜');
INSERT INTO `tb_purchase_category` VALUES (5, 0, '酒水饮料');
INSERT INTO `tb_purchase_category` VALUES (6, 1, '冰箱');
INSERT INTO `tb_purchase_category` VALUES (7, 1, '电视');
INSERT INTO `tb_purchase_category` VALUES (8, 1, '洗衣机');
INSERT INTO `tb_purchase_category` VALUES (9, 1, '空调');
INSERT INTO `tb_purchase_category` VALUES (10, 1, '电热水器');
INSERT INTO `tb_purchase_category` VALUES (11, 2, '电脑');
INSERT INTO `tb_purchase_category` VALUES (12, 2, '手机');
INSERT INTO `tb_purchase_category` VALUES (13, 2, '平板电脑');
INSERT INTO `tb_purchase_category` VALUES (14, 2, '数码相机');
INSERT INTO `tb_purchase_category` VALUES (15, 2, '3C配件');
INSERT INTO `tb_purchase_category` VALUES (16, 3, '女装');
INSERT INTO `tb_purchase_category` VALUES (17, 3, '帽子');
INSERT INTO `tb_purchase_category` VALUES (18, 3, '旅行箱');
INSERT INTO `tb_purchase_category` VALUES (19, 3, '手提包');
INSERT INTO `tb_purchase_category` VALUES (20, 3, '保暖内衣');
INSERT INTO `tb_purchase_category` VALUES (21, 4, '零食');
INSERT INTO `tb_purchase_category` VALUES (22, 4, '生鲜');
INSERT INTO `tb_purchase_category` VALUES (23, 4, '半成品菜');
INSERT INTO `tb_purchase_category` VALUES (24, 4, '速冻食品');
INSERT INTO `tb_purchase_category` VALUES (25, 4, '进口食品');
INSERT INTO `tb_purchase_category` VALUES (26, 5, '白酒');
INSERT INTO `tb_purchase_category` VALUES (27, 5, '红酒');
INSERT INTO `tb_purchase_category` VALUES (28, 5, '饮料');
INSERT INTO `tb_purchase_category` VALUES (29, 5, '调制鸡尾酒');
INSERT INTO `tb_purchase_category` VALUES (30, 5, '进口洋酒');

-- ----------------------------
-- Table structure for tb_purchase_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_evaluation`;
CREATE TABLE `tb_purchase_evaluation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 1 COMMENT '评论用户id',
  `star` int(11) NULL DEFAULT 0 COMMENT 'star数',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '评价内容',
  `evaluation_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评价时间',
  `type` int(11) NULL DEFAULT 1 COMMENT '1表示好评,2表示中评,3表示差评',
  `product_id` int(11) NOT NULL DEFAULT 1 COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase_evaluation
-- ----------------------------
INSERT INTO `tb_purchase_evaluation` VALUES (1, 1, 5, '这个冰箱挺好的', '2019-03-04 21:12:34', 1, 1);
INSERT INTO `tb_purchase_evaluation` VALUES (2, 10, 4, '这个冰箱还不错', '2019-03-04 21:12:53', 1, 1);
INSERT INTO `tb_purchase_evaluation` VALUES (3, 11, 4, '这个冰箱Very Nice', '2019-03-04 21:13:11', 1, 1);
INSERT INTO `tb_purchase_evaluation` VALUES (4, 9, 3, '这个冰箱还可以吧', '2019-03-04 21:13:29', 2, 1);

-- ----------------------------
-- Table structure for tb_purchase_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_image`;
CREATE TABLE `tb_purchase_image`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片链接地址',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `is_valid` tinyint(4) NULL DEFAULT NULL COMMENT '是否有效1表示有效0表示无效',
  `add_time` timestamp(0) NULL DEFAULT NULL COMMENT '添加时间',
  `keyword` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关键词',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase_image
-- ----------------------------
INSERT INTO `tb_purchase_image` VALUES (1, 'http://123.207.157.129/banner1.webp', 4, 1, '2019-03-15 16:18:07', '鸡肉');
INSERT INTO `tb_purchase_image` VALUES (2, 'http://123.207.157.129/banner2.webp', 4, 1, '2019-03-15 16:19:39', '冰食');
INSERT INTO `tb_purchase_image` VALUES (3, 'http://123.207.157.129/banner3.webp', 4, 1, '2019-03-15 16:20:29', '牛奶');

-- ----------------------------
-- Table structure for tb_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_order`;
CREATE TABLE `tb_purchase_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) NULL DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `shipping_id` int(11) NULL DEFAULT NULL,
  `payment` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) NULL DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) NULL DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) NULL DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_no_index`(`order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_order_item`;
CREATE TABLE `tb_purchase_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) NULL DEFAULT NULL,
  `order_no` bigint(20) NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_no_index`(`order_no`) USING BTREE,
  INDEX `order_no_user_id_index`(`user_id`, `order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_product`;
CREATE TABLE `tb_purchase_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '商品分类id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `subtitle` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品副标题',
  `stock` int(255) NULL DEFAULT NULL COMMENT '商品库存',
  `sales` int(255) NULL DEFAULT NULL COMMENT '商品销量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `status` int(255) NULL DEFAULT NULL COMMENT '商品状态1表示正常0表示下架',
  `spell_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品拼团价',
  `evaluation_nums` int(11) NULL DEFAULT NULL COMMENT '商品评价数',
  `main_image` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品主图',
  `sub_images` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品子图',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_product_sku`;
CREATE TABLE `tb_purchase_product_sku`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '属性名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_shipping
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_shipping`;
CREATE TABLE `tb_purchase_shipping`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货固定电话',
  `receiver_province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase_shipping
-- ----------------------------
INSERT INTO `tb_purchase_shipping` VALUES (1, 1, 'BeautifulSoup', '17864195333', '山东省', '济南市', '长清区', '山东省济南市长清区崮云湖街道山东师范大学', '250358');
INSERT INTO `tb_purchase_shipping` VALUES (2, 1, 'BeautifulSoup', '17864195333', NULL, NULL, NULL, '北京市海淀区', '250358');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
  `nickname` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称不能重复',
  `idcard` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '身份证号',
  `integral` int(11) NULL DEFAULT 0 COMMENT '积分',
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'female表示女,male表示男',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `avatar` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户头像地址',
  `motto` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '个性签名',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱地址',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `community_id` int(11) NULL DEFAULT 0 COMMENT '社区id',
  `sign_up` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_nickname`(`nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'BeautifulSoup', 'BeautifulSoup', '372330199706290001', 129, 'male', '1997-06-29', 'xxxx', '万事起于忽微,量变引起质变', 'beautifulsoup@163.com', '17864195311', 1, '2019-03-13 11:04:25', '2019-02-12 15:56:26', '2019-02-12 15:56:29');
INSERT INTO `tb_user` VALUES (2, '王福运', 'BeautifulSoup2', '372330199706290000', 100, 'male', '2019-02-05', 'http://pmt5ma5mu.bkt.clouddn.com/34a19b7f-7b6a-4710-9431-01d3279277cd.jpg', '6666666', 'beautifulsoup@163.com', '18864190000', 2, '2019-03-14 12:16:22', '2019-03-05 16:44:44', '2019-03-05 16:44:44');
INSERT INTO `tb_user` VALUES (3, '王小灏', 'BeautifulSoup3', '159100754214', NULL, 'female', NULL, 'http://pmt5ma5mu.bkt.clouddn.com/avatar.jpg', '生命不息,奋斗不止', 'beautifulsoup@126.com', '18062195241', 2, '2019-03-14 12:16:17', NULL, NULL);
INSERT INTO `tb_user` VALUES (4, 'Gopher', 'BeautifulSoup4', '159100754214', NULL, 'female', '1997-06-29', 'http://pmt5ma5mu.bkt.clouddn.com/avatar.jpg', '', 'beautifulsoup@126.com', '18062195241', 1, '2019-03-13 11:14:11', '2019-02-26 17:27:35', '2019-02-26 17:27:35');
INSERT INTO `tb_user` VALUES (5, '赵云', 'BeautifulSoup5', '15987416639522', NULL, 'male', NULL, 'http://pmt5ma5mu.bkt.clouddn.com/avatar.jpg', '书山有路勤为径 学海无涯苦作舟', 'zhaoyun@qq.com', '17466541298', 1, '2019-03-13 11:14:12', '2019-02-22 13:08:17', '2019-02-22 13:08:17');
INSERT INTO `tb_user` VALUES (6, 'James_shu', 'BeautifulSoup6', '372330199706291075', 151, 'male', '1997-06-29', 'http://pmt5ma5mu.bkt.clouddn.com/avatar.jpg', '书山有路勤为径 学海无涯苦作舟', 'zhaoyun@qq.com', '15550616509', 1, '2019-03-13 11:14:14', '2019-02-22 13:34:45', '2019-02-22 13:41:22');
INSERT INTO `tb_user` VALUES (7, 'hdfs', 'BeautifulSoup7', '372330199706290000', 0, 'male', '1970-07-01', 'http://pmt5ma5mu.bkt.clouddn.com/944f2ca0-d8ad-4eae-8b50-ea481a5a4a0d.jpg', '生命不息,奋斗不止', 'hdfs@113.com', '18765365111', 1, '2019-03-13 11:14:17', '2019-02-26 16:12:39', '2019-02-26 16:12:39');
INSERT INTO `tb_user` VALUES (8, 'Qiaofeng', 'BeautifulSoup8', '371330199706290000', 0, 'female', NULL, 'http://pmt5ma5mu.bkt.clouddn.com/avatar.jpg', 'QF', 'qiaofeng@163.com', '17864195311', 1, '2019-03-13 11:14:18', '2019-03-04 14:32:43', '2019-03-04 14:32:43');
INSERT INTO `tb_user` VALUES (9, 'Beautifu4lSoup', 'BeautifulSoup9', '372330199706290000', 0, 'male', NULL, 'http://pmt5ma5mu.bkt.clouddn.com/34a19b7f-7b6a-4710-9431-01d3279277cd.jpg', '生命不息,奋斗不止', 'beautifulsoup@163.com', '18864190000', 1, '2019-03-13 11:14:21', '2019-03-05 16:34:20', '2019-03-05 16:34:20');
INSERT INTO `tb_user` VALUES (10, '', '张无忌', '', 0, '', NULL, '', '', '', '17864195300', 0, '2019-03-13 17:29:12', NULL, NULL);
INSERT INTO `tb_user` VALUES (11, '', '张无忌222', '', 0, '', NULL, '', '', '', '17864195300', 0, '2019-03-13 17:32:06', NULL, NULL);

-- ----------------------------
-- Table structure for tb_vote_option
-- ----------------------------
DROP TABLE IF EXISTS `tb_vote_option`;
CREATE TABLE `tb_vote_option`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '投票选项内容',
  `vote_id` int(11) NOT NULL DEFAULT 1 COMMENT '所属投票id',
  `vote_nums` int(11) NULL DEFAULT 0 COMMENT '投票数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_vote_option
-- ----------------------------
INSERT INTO `tb_vote_option` VALUES (1, 'fadsfs', 1, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (2, 'fadsfs', 2, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (3, 'bhjhkbjbkj', 2, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (4, 'fadsfs', 3, 3, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (5, 'bhjhkbjbkj', 3, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (6, '', 3, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (7, '', 3, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (8, '选项1', 4, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (9, '选项2', 4, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (10, '选项1', 5, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (11, '选项2', 5, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (12, '选项1', 6, 0, NULL, NULL);
INSERT INTO `tb_vote_option` VALUES (13, '选项2', 6, 0, NULL, NULL);

-- ----------------------------
-- Table structure for tb_watersuply_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_watersuply_details`;
CREATE TABLE `tb_watersuply_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suply_id` int(11) NOT NULL COMMENT '送水主表id',
  `brand_id` int(11) NOT NULL COMMENT '水品牌id',
  `water_nums` int(11) NULL DEFAULT NULL COMMENT '水的数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_watersuply_details
-- ----------------------------
INSERT INTO `tb_watersuply_details` VALUES (1, 10, 2, 5);
INSERT INTO `tb_watersuply_details` VALUES (2, 10, 3, 1);
INSERT INTO `tb_watersuply_details` VALUES (3, 11, 2, 5);
INSERT INTO `tb_watersuply_details` VALUES (4, 11, 3, 0);
INSERT INTO `tb_watersuply_details` VALUES (5, 12, 2, 5);
INSERT INTO `tb_watersuply_details` VALUES (6, 12, 3, 0);

SET FOREIGN_KEY_CHECKS = 1;
