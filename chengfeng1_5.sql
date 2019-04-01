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

 Date: 30/03/2019 20:23:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_banner_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner_image`;
CREATE TABLE `tb_banner_image`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `image_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片链接地址',
                                  `journalism_id` int(11) NULL DEFAULT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
                                 `nickname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                                 `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                                 `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_book_secretary
-- ----------------------------
DROP TABLE IF EXISTS `tb_book_secretary`;
CREATE TABLE `tb_book_secretary`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '书记电话',
                                    `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书记邮箱',
                                    `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
                                    `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                                    `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                                    `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
                                     `nickname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                                     `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                                     `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                                     `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_journalism
-- ----------------------------
DROP TABLE IF EXISTS `tb_journalism`;
CREATE TABLE `tb_journalism`  (
                                `id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                `title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新闻标题',
                                `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者名字',
                                `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻描述',
                                `images` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻中的图片地址,逗号分隔',
                                `videos` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻中的视频地址,逗号分隔',
                                `publish_time` timestamp(0) NULL DEFAULT NULL COMMENT '发布时间',
                                `publish_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布者',
                                `community_id` int(11) NULL DEFAULT 3 COMMENT '社区id',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
                                        `journalism_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻主键',
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
                                        `journalism_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新闻id',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
                                `nickname` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人昵称',
                                `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人头像地址',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_post_vote
-- ----------------------------
DROP TABLE IF EXISTS `tb_post_vote`;
CREATE TABLE `tb_post_vote`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '投票标题',
                               `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '标题描述',
                               `vote_total` int(11) NULL DEFAULT NULL COMMENT '总的投票人数',
                               `choice` int(11) NULL DEFAULT 0 COMMENT '1表示单选2表示多选',
                               `user_id` int(11) NULL DEFAULT 0 COMMENT '创建者id',
                               `validity_time` timestamp(0) NULL DEFAULT NULL COMMENT '截止日期',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_assemble
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_assemble`;
CREATE TABLE `tb_purchase_assemble`  (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼单名称',
                                       `spell_nums` int(11) NULL DEFAULT NULL COMMENT '拼单人数',
                                       `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
                                       `status` int(11) NULL DEFAULT NULL COMMENT '拼单状态,1表示正在拼单,0表示拼单失效,2表示拼单成功',
                                       `deadline` timestamp(0) NULL DEFAULT NULL COMMENT '拼单截止日期',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_assemble_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_assemble_item`;
CREATE TABLE `tb_purchase_assemble_item`  (
                                            `id` int(11) NOT NULL AUTO_INCREMENT,
                                            `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼单人昵称',
                                            `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼单人头像',
                                            `assemble_id` int(11) NULL DEFAULT NULL COMMENT '所属拼单id',
                                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_category`;
CREATE TABLE `tb_purchase_category`  (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父类别id当id=0时说明是根节点,一级类别',
                                       `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类别名称',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_evaluation`;
CREATE TABLE `tb_purchase_evaluation`  (
                                         `id` int(11) NOT NULL AUTO_INCREMENT,
                                         `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '评论昵称',
                                         `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评价内容',
                                         `image_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价配图',
                                         `attribute_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品属性',
                                         `evaluation_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评价时间',
                                         `type` int(11) NULL DEFAULT 1 COMMENT '1表示好评,2表示中评,3表示差评',
                                         `product_id` int(11) NULL DEFAULT 1 COMMENT '商品id',
                                         `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者头像',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_order`;
CREATE TABLE `tb_purchase_order`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
                                    `order_no` bigint(20) NULL DEFAULT NULL COMMENT '订单号,uuid',
                                    `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                                    `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                                    `shipping_id` int(11) NULL DEFAULT NULL COMMENT '收获地址id',
                                    `payment` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
                                    `payment_type` int(11) NULL DEFAULT NULL COMMENT '支付类型,拼团还是单独支付。0表示单独支付,拼团就为拼单id',
                                    `postage` int(10) NULL DEFAULT NULL COMMENT '运费,单位是元',
                                    `status` int(10) NULL DEFAULT NULL COMMENT '订单状态:0表示取消,1表示未支付,2表示已支付,3表示未收货,4表示已收货',
                                    `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
                                    `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
                                    `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
                                    `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `order_no_index`(`order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_order_item`;
CREATE TABLE `tb_purchase_order_item`  (
                                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
                                         `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                                         `order_no` bigint(20) NULL DEFAULT NULL COMMENT '订单编号',
                                         `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
                                         `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
                                         `product_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片地址',
                                         `current_unit_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
                                         `quantity` int(10) NULL DEFAULT NULL COMMENT '商品数量',
                                         `total_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
                                         PRIMARY KEY (`id`) USING BTREE,
                                         INDEX `order_no_index`(`order_no`) USING BTREE,
                                         INDEX `order_no_user_id_index`(`nickname`, `order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_product`;
CREATE TABLE `tb_purchase_product`  (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `category_id` int(11) NOT NULL COMMENT '商品分类id',
                                      `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品标题',
                                      `subtitle` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品副标题',
                                      `status` int(255) NULL DEFAULT NULL COMMENT '商品状态1表示正常0表示下架',
                                      `evaluation_nums` int(11) NULL DEFAULT NULL COMMENT '商品评价数',
                                      `good_evaluation_nums` int(11) NULL DEFAULT NULL COMMENT '商品好评数',
                                      `main_image` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品主图',
                                      `sub_images` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品子图',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_purchase_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_product_sku`;
CREATE TABLE `tb_purchase_product_sku`  (
                                          `id` int(11) NOT NULL AUTO_INCREMENT,
                                          `attribute_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '属性名',
                                          `sales` int(11) NULL DEFAULT NULL COMMENT '销量',
                                          `stock` int(11) NULL DEFAULT NULL COMMENT '库存',
                                          `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
                                          `spell_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品拼团价',
                                          `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
