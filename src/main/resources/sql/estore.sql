/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : estore

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-04-28 19:17:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '类别名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='大类别表';

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO `categories` VALUES ('0', '文学类');
INSERT INTO `categories` VALUES ('1', '教育类');
INSERT INTO `categories` VALUES ('2', '计算机');
INSERT INTO `categories` VALUES ('3', '儿童类');
INSERT INTO `categories` VALUES ('4', '漫画类');
INSERT INTO `categories` VALUES ('5', '工具类');
INSERT INTO `categories` VALUES ('6', '期刊');

-- ----------------------------
-- Table structure for category_detail
-- ----------------------------
DROP TABLE IF EXISTS `category_detail`;
CREATE TABLE `category_detail` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_id` int(11) NOT NULL DEFAULT '0' COMMENT '类别id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '详细类别名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=gb2312 COMMENT='大类别下详细类型表';

-- ----------------------------
-- Records of category_detail
-- ----------------------------
INSERT INTO `category_detail` VALUES ('1', '0', '历史');
INSERT INTO `category_detail` VALUES ('2', '0', '心理学');
INSERT INTO `category_detail` VALUES ('3', '0', '法律');
INSERT INTO `category_detail` VALUES ('4', '1', '教材');
INSERT INTO `category_detail` VALUES ('5', '1', '外语');
INSERT INTO `category_detail` VALUES ('6', '2', 'C#');
INSERT INTO `category_detail` VALUES ('7', '2', 'java');
INSERT INTO `category_detail` VALUES ('8', '2', '大数据');
INSERT INTO `category_detail` VALUES ('9', '2', 'C++');
INSERT INTO `category_detail` VALUES ('10', '3', '科普');
INSERT INTO `category_detail` VALUES ('11', '3', '百科');
INSERT INTO `category_detail` VALUES ('12', '4', '连环画');
INSERT INTO `category_detail` VALUES ('13', '4', '童话故事');
INSERT INTO `category_detail` VALUES ('14', '5', '汽修');
INSERT INTO `category_detail` VALUES ('15', '5', '电修');
INSERT INTO `category_detail` VALUES ('16', '5', '水利维护');
INSERT INTO `category_detail` VALUES ('17', '6', '时尚周刊');
INSERT INTO `category_detail` VALUES ('18', '6', '汽车');
INSERT INTO `category_detail` VALUES ('19', '6', '美食');

-- ----------------------------
-- Table structure for idempotency
-- ----------------------------
DROP TABLE IF EXISTS `idempotency`;
CREATE TABLE `idempotency` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `idem_key` varchar(255) NOT NULL DEFAULT '' COMMENT '幂等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=gb2312 COMMENT='幂等表';

-- ----------------------------
-- Records of idempotency
-- ----------------------------
INSERT INTO `idempotency` VALUES ('83', 'createUser3283');
INSERT INTO `idempotency` VALUES ('84', 'createUser98020');
INSERT INTO `idempotency` VALUES ('85', 'createUser29590');
INSERT INTO `idempotency` VALUES ('86', 'createUser92566');
INSERT INTO `idempotency` VALUES ('87', 'createUser33649');
INSERT INTO `idempotency` VALUES ('88', 'createUser30889');
INSERT INTO `idempotency` VALUES ('89', 'createOrderLine76640');
INSERT INTO `idempotency` VALUES ('90', 'createReceiver91717');
INSERT INTO `idempotency` VALUES ('91', 'createOrder35669');
INSERT INTO `idempotency` VALUES ('92', 'createUser1448');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `receiver_id` int(11) NOT NULL DEFAULT '0' COMMENT '接收人信息',
  `product_id` int(11) DEFAULT '0' COMMENT '产品id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `num` int(10) DEFAULT '0' COMMENT '购买量',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '支付费用',
  `pay_way` varchar(20) NOT NULL DEFAULT '' COMMENT '支付方式',
  `attributes` varchar(1024) DEFAULT NULL COMMENT '拓展信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gb2312 COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('4', '3', '2', '0', '2018-04-24 23:08:14', '2018-04-24 23:08:14', '0', '264.00', '支付宝', null);
INSERT INTO `orders` VALUES ('5', '3', '2', '0', '2018-04-26 12:26:28', '2018-04-26 12:26:28', '0', '219.00', '微信', null);
INSERT INTO `orders` VALUES ('6', '3', '2', '0', '2018-04-26 18:35:04', '2018-04-26 18:35:04', '0', '264.00', '银行卡支付', null);
INSERT INTO `orders` VALUES ('7', '3', '2', '0', '2018-04-26 19:02:09', '2018-04-26 19:02:09', '0', '633.00', '微信', null);
INSERT INTO `orders` VALUES ('8', '3', '2', '0', '2018-04-27 13:57:09', '2018-04-27 13:57:09', '0', '116.00', '银行卡支付', null);
INSERT INTO `orders` VALUES ('9', '9', '3', '0', '2018-04-27 22:46:35', '2018-04-27 22:46:35', '0', '88.00', '支付宝', null);
INSERT INTO `orders` VALUES ('10', '9', '3', '0', '2018-04-28 01:36:09', '2018-04-28 01:36:09', '0', '76.00', '微信', null);
INSERT INTO `orders` VALUES ('11', '3', '2', '0', '2018-04-28 17:19:24', '2018-04-28 17:19:24', '0', '40.00', '微信', null);
INSERT INTO `orders` VALUES ('12', '18', '4', '0', '2018-04-28 18:09:48', '2018-04-28 18:09:48', '0', '176.00', '支付宝', null);

-- ----------------------------
-- Table structure for order_line
-- ----------------------------
DROP TABLE IF EXISTS `order_line`;
CREATE TABLE `order_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(20) NOT NULL DEFAULT '0' COMMENT '唯一的用户id',
  `order_id` int(11) DEFAULT '0' COMMENT '属于的订单id',
  `product_id` int(11) NOT NULL DEFAULT '0' COMMENT '产品的id',
  `version` varchar(10) DEFAULT '' COMMENT '具体的版本信息',
  `amount` int(10) NOT NULL DEFAULT '0' COMMENT '产品的数量',
  `total_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总价',
  `create_time` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=gb2312 COMMENT='购物车中的每一个项';

-- ----------------------------
-- Records of order_line
-- ----------------------------
INSERT INTO `order_line` VALUES ('4', '3', '3', '7', 'Paperback', '7', '770.00', '2018-04-24 14:48:47');
INSERT INTO `order_line` VALUES ('5', '3', '3', '1', 'Hardback', '7', '434.00', '2018-04-24 16:13:22');
INSERT INTO `order_line` VALUES ('6', '3', '4', '6', 'Hardback', '5', '440.00', '2018-04-24 23:07:23');
INSERT INTO `order_line` VALUES ('7', '3', '11', '5', 'Hardback', '3', '219.00', '2018-04-26 12:26:10');
INSERT INTO `order_line` VALUES ('8', '3', '6', '9', 'Hardback', '3', '264.00', '2018-04-26 18:34:46');
INSERT INTO `order_line` VALUES ('9', '3', '7', '51', 'Hardback', '1', '633.00', '2018-04-26 19:01:55');
INSERT INTO `order_line` VALUES ('10', '3', '8', '13', 'Hardback', '2', '116.00', '2018-04-27 13:56:57');
INSERT INTO `order_line` VALUES ('11', '9', '9', '6', 'Paperback', '1', '88.00', '2018-04-27 22:46:11');
INSERT INTO `order_line` VALUES ('12', '9', '10', '17', 'Hardback', '2', '76.00', '2018-04-28 01:35:58');
INSERT INTO `order_line` VALUES ('13', '18', '12', '6', 'Paperback', '2', '176.00', '2018-04-28 18:09:29');

-- ----------------------------
-- Table structure for price_rank
-- ----------------------------
DROP TABLE IF EXISTS `price_rank`;
CREATE TABLE `price_rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `min_price` decimal(10,2) DEFAULT NULL COMMENT '最小价格',
  `max_price` decimal(10,2) DEFAULT NULL COMMENT '最大价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312 COMMENT='价格范围表';

-- ----------------------------
-- Records of price_rank
-- ----------------------------
INSERT INTO `price_rank` VALUES ('1', '0.00', '599.00');
INSERT INTO `price_rank` VALUES ('2', '600.00', '999.00');
INSERT INTO `price_rank` VALUES ('3', '1000.00', '1599.00');
INSERT INTO `price_rank` VALUES ('4', '1600.00', '1999.00');
INSERT INTO `price_rank` VALUES ('5', '2000.00', '2999.00');
INSERT INTO `price_rank` VALUES ('6', '3000.00', '3999.00');
INSERT INTO `price_rank` VALUES ('7', '4000.00', '4999.00');
INSERT INTO `price_rank` VALUES ('8', '5000.00', '5999.00');
INSERT INTO `price_rank` VALUES ('9', '6000.00', '6495.00');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '产品名',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '产品价格',
  `service_fg` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `service_myf` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '免邮费',
  `service_zt` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '包邮',
  `service_th` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '服务',
  `version` varchar(10) DEFAULT '' COMMENT '产品的版本',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `description` varchar(2014) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '简介',
  `writer` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '作者',
  `publish_id` int(11) NOT NULL DEFAULT '0' COMMENT '出版社id',
  `pages` int(11) NOT NULL DEFAULT '0' COMMENT '页码',
  `isbn` varchar(255) DEFAULT NULL COMMENT '书籍的ISBN',
  `publish_date` date DEFAULT NULL COMMENT '出版时间',
  `feature_images` varchar(40) CHARACTER SET utf8 DEFAULT NULL COMMENT '封面图片',
  `images` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片信息',
  `bill` varchar(1024) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '目录简介',
  `cat_detail_id` int(11) NOT NULL DEFAULT '0' COMMENT '类型id',
  `start_date` datetime DEFAULT NULL,
  `sale_num` int(11) DEFAULT '0' COMMENT '销量',
  `flag` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '推荐标识',
  `explains` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '拓展信息',
  PRIMARY KEY (`id`),
  KEY `idx_product_name` (`name`) COMMENT '产品名索引',
  KEY `idx_product_price` (`price`) USING BTREE COMMENT '价格索引',
  KEY `idx_product_cat` (`cat_detail_id`) COMMENT '分类查询索引'
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=gb2312 COMMENT='产品表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '未来简史', '62.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '2000', '★人类未来面临的三大议题<br>进入21世纪后，曾经长期威胁人类生存、发展的瘟疫、饥荒和战争已经被攻克，智人面临着新的待办议题：永生不老、幸福快乐和成为具有“神性”的升级人类。<br>★人类将迎来第二次认知革命<br>如果说*次认知革命是因为智人的DNA起了一点小变化，让人类拥有了虚构的能力，创造了宗教、国家、企业等概念，使其成为地球的统治者。那么，未来算法和生物技术将带来人类的第二次认知革命，完成从智人到神人的物种进化。<br>★人类认知升级的三个知识公式<br>中世纪时期，人类获取的知识公式：知识=经文×逻辑。<br>想知道某个重要问题的答案，我们会阅读相关经文，再用逻辑来理解经文的确切含义。', '[以色列］尤瓦尔·赫拉利', '4', '369', '9787508672069', '2016-12-21', 'product/weilaijianjiefeature.jpg', 'product/weilaijianshi1.jpg#product/weilaijianshi2.jpg#product/weilaijianshi2.jpg', '目录<br>\r\n第1 章人类的新议题 1<br>\r\n生物贫穷线 2<br>\r\n看不见的舰队 5<br>\r\n打破弱肉强食的“丛林法则” 13<br>\r\n死亡的末日 18<br>\r\n幸福快乐的权利 26<br>\r\n地球的神 38<br>\r\n可以请哪位踩个刹车吗？ 44<br>\r\n知识的矛盾 49<br>\r\n一段关于草坪的历史 52<br>\r\n第一幕中出现的一把枪 58<br>\r\n <br>\r\n第一部分智人征服世界<br>\r\n第2 章人类世 65<br>\r\n蛇的孩子 68<br>\r\n祖先的需求 71<br>\r\n生物也是算法 75<br>\r\n农业交易 81<br>\r\n五百年孤寂 87<br>\r\n <br>\r\n第3 章人类的特殊之处 91<br>\r\n谁怕达尔文？ 92<br>\r\n为什么股票交易所没有意识？ 95<br>\r\n生命的等式 99<br>\r\n实验室大鼠的抑郁生活 109<br>\r\n有自我意识的黑猩猩 112<br>\r\n聪明的马 114<br>\r\n革命万岁！ 119<br>\r\n在色情与暴力之外 123<br>\r\n意义的网 128<br>\r\n大同世界 134<br>\r\n <br>\r\n第二部分智人为世界赋予意义<br>\r\n第4 章说书人 139<br>\r\n纸上的生活 146<br>\r\n神圣的经文 149<br>\r\n但这就是有用 156<br>\r\n <br>\r\n第5 章一对冤家 161<br>\r\n细菌和恶魔 162<br>\r\n如果遇见佛陀 166<br>\r\n伪造上帝 169<br>\r\n神圣教条 176<br>\r\n猎巫行动 178<br>\r\n <br>\r\n第6 章与“现代”的契约 181<br>\r\n银行家与吸血蝙蝠有何不同？ 183<br>\r\n奇迹般的大饼 186<br>\r\n方舟综合征 191<br>\r\n毫无意义的竞赛追逐 197<br>\r\n <br>\r\n第11 章信数据得永生 335<br>\r\n所有的权力都去了哪？ 340<br>\r\n概括历史 344<br>\r\n信息渴求自由 346<br>\r\n记录、上传、分享！ 350<br>\r\n认识你自己 352<br>\r\n数据流里的一片涟漪 357<br>\r\n图片来源 363<br>\r\n致谢 366<br>\r\n注释 369<br>', '0', '2017-03-12 00:00:00', '0', '1', '从以知的历史和科学角度看待未来');
INSERT INTO `product` VALUES ('2', '爱的艺术', '20.00', '网上支付', '不免邮费', '送货上门', '7天无理由退换货', '2', '1000', '　　《爱的艺术》是德裔美籍心理学家和哲学家、法 兰克福学派重要成员艾·弗洛姆最著名的作品，自 1956年出版至今已被翻译成32种文字，在全世界畅销 不衰，被誉为当代爱的艺术理论专著最著名的作品。<br>\r\n     在这本《爱的艺术》中，弗洛姆认为，爱情不是 一种与人的成熟程度无关，只需要投入身心的感情。<br>\r\n    如果不努力发展自己的全部人格并以此达到一种创造 倾向性，那么每种爱的试图都会失败，如果没有爱他 人的能力，如果不能真正谦恭地、勇敢地、真诚地和 有纪律地爱他人，那么人们在自己的爱情生活中也永 远得不到满足。<br>\r\n     弗洛姆进而提出，爱是一门艺术，要求想要掌握 这门艺术的人有这方面的知识并付出努力。在这里， 爱不仅仅是狭隘的男女爱情，也并非通过磨练增进技 巧即可获得。爱是人格整体的展现，要发展爱的能力 ，就需要努力发展自己的人格，并朝着有益的目标迈 进。', 'Erich Fromm', '5', '400', '9787532745159', '2014-08-01', null, 'product/aideyishu1.jpg#product/aideyishu2.png', '前言\r\n第一章  爱是一门艺术吗?<br>\r\n第二章  爱情的理论<br>\r\n  （一）爱情是对人类生存问题的回答<br>\r\n  （二）父母和孩子之间的爱<br>\r\n  （三）爱的对象<br>\r\n    （1）博爱<br>\r\n    （2）母爱<br>\r\n    （3）性爱<br>\r\n    （4）自爱<br>\r\n    （5）神爱<br>\r\n第三章  爱情及其在当代西方社会的衰亡<br>\r\n第四章  爱的实践<br>\r\n附录<br>\r\n  艾里希·弗洛姆生命中的', '1', '2017-02-12 00:00:00', '0', '1', '爱是什么，为什么我们需要爱');
INSERT INTO `product` VALUES ('3', '法治及其本土资源', '33.00', '网上支付', '不免邮费', '送货上门', '7天无理由退换货', '3', '80', '《法治及其本土资源》内容以交叉学科为背景，从浅近的社会法律问题入手，集中讨论了中国当代法律和法学一系列重要理论问题。例如：法律规避和法律多元、法律本土化、法律专业化、市场与法律的替代问题，以及法学研究方法论等。其学术背景涉及经济学、人类学、社会学、阐释学、语言哲学等。以力求从平易中展现法学与其他学科的不可分割的关系，创造性地把交叉学科的知识引入到中国的法学研究中来，并融合进中国法学。', '苏力', '6', '555', '9787301250051', '2015-01-01', null, 'product/fazhi.jpg', '第三版序<br>\r\n修订版序<br>\r\n序（赵晓力）<br>\r\n什么是你的贡献？（自序）<br>\r\n致 谢<br>\r\n<br>\r\n第一编 变法与法制<br>\r\n变法、法治建设及其本土资源<br>\r\n秋菊的困惑和山杠爷的悲剧<br>\r\n附录：从文学艺术作品来研究法律与社会？<br>\r\n法律规避和法律多元<br>\r\n再论法律规避<br>\r\n市场经济对立法的启示<br>\r\n市场经济需要什么样的法律？<br>\r\n市场经济形成中的犯罪违法现象<br>\r\n<br>\r\n第二编 司法制度研究<br>\r\n论法律活动的专门化<br>\r\n关于抗辩制改革<br>\r\n《秋菊打官司》的官司、邱氏鼠药案和言论自由<br>\r\n附录：关于贾桂花案件的几个民法问题<br>\r\n<br>\r\n第三编 法学研究的规范化<br>\r\n法学研究的规范化、传统与本土化<br>\r\n什么是法理学？<br>\r\n读《走向权利的时代》<br>\r\n读劳伦斯?却伯的《美国宪法》<br>\r\n后现代思潮与中国法学和法制<br>\r\n法学本科教育的研究和思考', '2', '2016-02-12 00:00:00', '0', '1', '集中讨论当代法学和法学等问题');
INSERT INTO `product` VALUES ('4', 'C#入门经典', '63.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '1', '534', 'C# 6和VisualStudio 2015编程实战指南<br>\r\n《C#入门经典》系列是屡获殊荣的C#名著和超级畅销书。*版的 C#入门经典(第7版) C# 6.0 Visual Studio2015 全面介绍使用C# 6和.NET Framework编写程序的基础知识，是编程新手的理想读物。这本分步讲解的实用教程从最基本的面向对象编程讲起，浓墨重彩地描述初学者最常用的工具，不要求读者具有任何编程经验。紧贴实用的示例使用Visual Studio 2015中的C#环境，涵盖微软为使C#更好兼容其他编程语言所做的*改进。本书呈现微软资深开发人员的专家级建议，将指导初学者立即上手编写Windows和Web应用程序。<br>\r\n主要内容<br>\r\n◆ 首先讲解编程基础知识，如变量、流控制、面向对象编程、类、函数、集合、比较和转换等<br>\r\n◆ 重点介绍Visual Studio 2015中初学者喜欢的C#6开发环境，囊括所有*功能和语言改进<br>\r\n◆ 包括云和Windows编程中级内容，涵盖数据库和XML<br>\r\n◆ 揭密错误处理技术和调试过程', 'briup', '1', '235', '9787302444060', '2016-08-01', null, 'product/C1.jpg', '目 录<br>\r\n第Ⅰ部分 OOP 语言<br>\r\n第1章 C#简介 3<br>\r\n1.1 .NET Framework的含义 3<br>\r\n1.1.1 .NET Framework的内容 4<br>\r\n1.1.2 使用.NETFramework编写应用程序 4<br>\r\n1.2 C#的含义 7<br>\r\n1.2.1 用C#能编写什么样的应用程序 8<br>\r\n1.2.2 本书中的C# 8<br>\r\n1.3 Visual Studio 2015 8<br>\r\n1.3.1 Visual Studio Express 2015产品 9<br>\r\n1.3.2 解决方案 9<br>\r\n1.4 本章要点 9<br>\r\n第2章 编写C#程序 11', '5', '2016-06-12 00:00:00', '0', '1', 'C#的基本原理和操作');
INSERT INTO `product` VALUES ('5', 'Java从入门到精通', '73.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '55', '   《Java从入门到精通（第4版）》全新改版本<br>\r\n“软件开发视频大讲堂”丛书系清华社“视频大讲堂”重点大系之一。该大系包括多个子系列，每个子系列的图书在其同品种的图书中销售名列前茅，其中：\r\n4个品种荣获“全行业优秀畅销品种”\r\n1个品种荣获2012年清华大学出版社“专业畅销书”一等奖\r\n绝大多数品种在“全国计算机零售图书排行榜”同品种排行中名列前茅\r\n截至目前该大系累计销售超过55万册\r\n该大系已成为近年来清华社计算机专业基础类零售图书*畅销的品牌之一\r\n', '苏力ddd', '1', '666', '9787302287568', '2012-09-01', 'product/javafeature.jpg', 'product/java1.jpg', '第1篇　基础知识<br>	\r\n<br>\r\n第1章　初识Java<br>	\r\n视频讲解：34分钟	<br>\r\n1.1　Java简介	<br>\r\n1.1.1　什么是Java语言	<br>\r\n1.1.2　Java的应用领域	<br>\r\n1.1.3　Java的版本	<br>\r\n1.1.4　怎样学好Java	<br>\r\n1.1.5　JavaAPI文档<br>	\r\n1.2　Java语言的特性	<br>\r\n1.2.1　简单	<br>\r\n1.2.2　面向对象', '6', '2016-07-12 00:00:00', '9', '1', 'java的基本语法和操作');
INSERT INTO `product` VALUES ('6', 'Java核心技术', '88.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '123', '  　Java领域最有影响力和价值的著作之一，拥有20多年教学与研究经验的资深Java技术专家撰写（获Jolt大奖），与《Java编程思想》齐名，10余年全球畅销不衰，广受好评。第9版根据JavaSE7全面更新，同时修正了第8版中的不足，系统全面讲解Java语言的核心概念、语法、重要特性和开发方法，包含大量案例，实践性强。<br>\r\n　　《Java核心技术·卷1：基础知识》共14章。第1章概述了Java语言与其他程序设计语言不同的性能；第2章讲解了如何下载和安装JDK及本书的程序示例；第3章介绍了变量、循环和简单的函数；第4章讲解了类和封装；第5章介绍了继承；第6章解释了接口和内部类；第7章概述了图形用户界面程序设计知识；第8章讨论AWT的事件模型；第9章探讨了SwingGUI工具箱；第10章讲解如何部署自己的应用程序或applet；第11章讨论异常处理；第12章概要介绍泛型程序设计；第13章讲解Java平台的集合框架；第14章介绍了多线程。本书最后还有一个附录，其中列出了Java语言的保留字。\r\n', '苏力test', '7', '343', '9787111445142', '2014-01-01', null, 'product/javaCore.png', '译者序<br>\r\n前言<br>\r\n致谢<br>\r\n\r\n第1章 Java程序设计概述<br>\r\n1.1 Java程序设计平台<br>\r\n1.2 Java“白皮书”的关键术语<br>\r\n1.2.1 简单性<br>\r\n1.2.2 面向对象<br>\r\n1.2.3 网络技能<br>\r\n1.2.4 健壮性<br>\r\n1.2.5 安全性<br>\r\n1.2.6 体系结构中立<br>\r\n1.2.7 可移植性', '6', '2016-07-12 00:00:00', '3', '3', '核心java阐述');
INSERT INTO `product` VALUES ('7', 'Java编程思想', '110.00', '网上支付', '免邮费', '送货上门', '30天无理由退换货', '3', '444', ' 《计算机科学丛书：Java编程思想（第4版）》赢得了全球程序员的广泛赞誉，即使是晦涩的概念，在BruceEckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。<br>\r\n　　从《计算机科学丛书：Java编程思想（第4版）》获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作。本书的作者拥有多年教学经验，对C、C 以及Java语言都有独到、深入的见解，以通俗易懂及小而直接的示例解释了一个个晦涩抽象的概念。本书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究、JavaI/O系统、枚举类型、并发以及图形化用户界面等内容。这些丰富的内容，包含了Java语言基础语法以及高级特性，适合各个层次的Java程序员阅读，同时也是高等院校讲授面向对象程序设计语言以及Java语言的好教材和参考书。<br>\r\n　　《计算机科学丛书：Java编程思想（第4版）》特点：<br>\r\n　　适合初学者与专业人员的经典的面向对象叙述方式，为更新的JavaSE5/6增加了新的示例和章节。<br>\r\n　　测验框架显示程序输出。', 'Bruce Eckel', '7', '363', '9787111213826', '2007-06-01', null, 'product/javaB.jpg', '读者评论<br>\r\n前言<br>\r\n简介<br>\r\n第1章 对象导论<br>\r\n1.1 抽象过程<br>\r\n1.2 每个对象都有一个接口<br>\r\n1.3 每个对象都提供服务<br>\r\n1.4 被隐藏的具体实现<br>\r\n1.5 复用具体实现<br>\r\n1.6 继承<br>\r\n1.6.1 “是一个”（is-a）与“像是一个”（is-like-a）关系<br>\r\n1.7 伴随多态的可互换对象<br>\r\n1.8 单根继承结构<br>\r\n1.9 容器', '6', '2016-09-15 00:00:00', '12', '1', '如何用面向对象的思想编程');
INSERT INTO `product` VALUES ('8', 'Hadoop权威指南', '80.00', '网上支付', '免邮费', '送货上门', '30天无理由退换货', '3', '992', ' 准备好释放数据的强大潜能了吗？借助于这本《Hadoop权威指南》，你将学习如何使用Apache Hadoop构建和维护稳定性高、伸缩性强的分布式系统。本书是为程序员写的，可帮助他们分析任何大小的数据集。本书同时也是为管理员写的，帮助他们了解如何设置和运行Hadoop集群。<br>\r\n　　本书通过丰富的案例学习来解释Hadoop的幕后机理，阐述了Hadoop如何解决现实生活中的具体问题。第3版覆盖Hadoop的*动态，包括新增的MapReduce API，以及MapReduce 2及其灵活性更强的执行模型（YARN）。', 'Tom White', '5', '563', '9787302370857', '2015-01-01', 'product/hadoopFeature.jpg', 'product/hadoop1.jpg#product/hadoop2.jpg', '第1章 初识Hadoop<br>\r\n1.1 数据！数据！<br>\r\n1.2 数据的存储与分析<br>\r\n1.3 相较于其他系统的优势<br>\r\n1.3.1 关系型数据库管理系统<br>\r\n1.3.2 网格计算<br>\r\n1.3.3 志愿计算<br>\r\n1.4 Hadoop发展简史<br>\r\n1.5 Apache Hadoop和Hadoop生态系统<br>\r\n1.6 Hadoop的发行版本<br>\r\n1.6.1 本书包含的内容<br>\r\n1.6.2 兼容性', '7', '2017-04-15 00:00:00', '15', '1', '大数据hadoop对静态数据的处理');
INSERT INTO `product` VALUES ('9', 'Spark最佳实践', '88.00', '网上支付', '免邮费', '送货上门', '30天无理由退换货', '7', '673', ' 本书是Spark实战指南，全书共分8章。前4章介绍Spark的部署、工作机制和内核，后4章分别通过实战项目介绍Spark SQL、Spark Streaming、Spark GraphX和Spark MLib功能模块。此外，本书详细介绍了常见的实战问题，比如大数据环境下的配置设置、程序调优等。本书附带的一键安装脚本，更能为初学者提供很大帮助。', '陈欢 林世飞', '2', '456', '9787115422286', '2016-03-03', null, 'product/spark.png', '第1章 　Spark与大数据　　1<br>\r\n1.1 　大数据的发展及现状　　1<br>\r\n1.1.1 　大数据时代所面临的问题　　1<br>\r\n1.1.2 　谷歌的大数据解决方案　　2<br>\r\n1.1.3 　Hadoop生态系统　　3<br>\r\n1.2 　Spark应时而生　　4<br>\r\n1.2.1 　Spark的起源　　4<br>\r\n1.2.2 　Spark的特点　　5<br>\r\n1.2.3 　Spark的未来发展　　6<br>\r\n第2章 　Spark基础　　8<br>\r\n2.1 　Spark本地单机模式体验　　8<br>\r\n2.1.1 　安装虚拟机　　8<br>\r\n2.1.2 　安装JDK　　19<br>\r\n2.1.3 　下载Spark预编译包　　21', '7', '2017-04-17 00:00:00', '34', '3', '大数据对动态数据时时分析');
INSERT INTO `product` VALUES ('10', 'C++', '333.00', '货到付款', '免邮费', '送货上门', '15天无理由退换货', '3', '867', 'Essential skills made easy Written by Herb Schildt, the world.sleading programming author, this step-by-step book is ideal forfirst-time programmers or those new to C++. The modular approach ofthis series, including sample projects and progress checks, makesit easy to learn to use C++ at y', 'Schildt, Herbert', '5', '556', 'Y9780072232158', '2017-03-03', 'product/CT.jpg', 'images/list_p1.png', '共计8章', '8', '2017-04-17 00:00:00', '99', '3', 'C++语法和基本的使用');
INSERT INTO `product` VALUES ('11', '心理学指南1', '38.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '1', null, '18', '1', '健康成长');
INSERT INTO `product` VALUES ('12', '娱乐至死', '68.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '1', null, '19', null, '慢性死亡');
INSERT INTO `product` VALUES ('13', '厚黑学', '58.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '1', null, '20', null, '又红又专');
INSERT INTO `product` VALUES ('14', '中国人的心理', '48.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '1', null, '30', null, '深度剖析');
INSERT INTO `product` VALUES ('15', '24史', '468.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '0', null, '5', null, '历史大事件');
INSERT INTO `product` VALUES ('16', '史记', '58.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '0', null, '20', null, '王侯将相一览');
INSERT INTO `product` VALUES ('17', '诸侯传', '38.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '0', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('18', '黄帝内经', '48.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '0', null, '50', null, '不可说...');
INSERT INTO `product` VALUES ('19', '法制', '123.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '2', null, '5', null, '历史大事件');
INSERT INTO `product` VALUES ('20', '法律', '52.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '2', null, '20', null, '王侯将相一览');
INSERT INTO `product` VALUES ('21', '法纪', '32.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '2', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('22', '法人', '42.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '2', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('23', '语文', '123.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '3', null, '5', null, '历史大事件');
INSERT INTO `product` VALUES ('24', '数学', '52.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '3', null, '20', null, '王侯将相一览');
INSERT INTO `product` VALUES ('25', '物理', '32.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '3', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('26', '化学', '42.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '3', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('27', '生物', '42.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '3', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('28', 'English', '122.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '4', null, '5', null, '历史大事件');
INSERT INTO `product` VALUES ('29', 'American', '222.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '4', null, '20', null, '王侯将相一览');
INSERT INTO `product` VALUES ('30', 'Australia', '633.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '4', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('31', 'Canada', '744.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '4', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('32', 'Russia', '855.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '4', null, '72', null, '说...');
INSERT INTO `product` VALUES ('33', 'C#1', '122.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '5', null, '5', null, '历史大事件');
INSERT INTO `product` VALUES ('34', 'C#2', '222.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '5', null, '20', null, '王侯将相一览');
INSERT INTO `product` VALUES ('35', 'C#3', '633.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '5', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('36', 'C#4', '744.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '5', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('37', 'C#5', '855.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '5', null, '72', null, '说...');
INSERT INTO `product` VALUES ('38', 'java1', '633.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '6', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('39', 'java2', '744.00', '网上支付', '免邮费', '送货上门', '7天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '6', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('40', 'java3', '855.00', null, null, null, null, '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '6', null, '72', null, '说...');
INSERT INTO `product` VALUES ('41', 'C++1', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '8', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('42', 'C++2', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '8', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('43', 'C++3', '855.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '8', null, '72', null, '说...');
INSERT INTO `product` VALUES ('44', 'C++4', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '8', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('45', 'C++5', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '8', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('46', '科普1', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '9', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('47', '科普2', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '9', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('48', '科普3', '855.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '9', null, '72', null, '说...');
INSERT INTO `product` VALUES ('49', '科普4', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '9', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('50', '科普5', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '9', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('51', '百科1', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '10', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('52', '百科2', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '10', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('53', '百科3', '855.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '10', null, '72', null, '说...');
INSERT INTO `product` VALUES ('54', '百科4', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '10', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('55', '百科5', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '10', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('56', '汽修1', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '13', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('57', '汽修2', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '13', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('58', '汽修3', '855.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '13', null, '72', null, '说...');
INSERT INTO `product` VALUES ('59', '汽修4', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '1', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '13', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('60', '汽修5', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '13', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('61', '电修1', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '14', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('62', '电修2', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '3', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '14', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('63', '电修3', '855.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '14', null, '72', null, '说...');
INSERT INTO `product` VALUES ('64', '电修4', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '14', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('65', '电修5', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '14', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('66', '水利维护1', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '15', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('67', '水利维护2', '744.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '15', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('68', '水利维护3', '855.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '15', null, '72', null, '说...');
INSERT INTO `product` VALUES ('69', '水利维护4', '633.00', '货到付款', '不免邮费', '送货上门', '30天无理由退换货', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '15', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('70', '水利维护5', '744.00', null, null, null, null, '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '15', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('71', '时尚周刊1', '633.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '16', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('72', '时尚周刊2', '744.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '16', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('73', '时尚周刊3', '855.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '16', null, '72', null, '说...');
INSERT INTO `product` VALUES ('74', '时尚周刊4', '633.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '16', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('75', '时尚周刊5', '744.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '16', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('76', '汽车1', '633.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '17', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('77', '汽车2', '744.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '17', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('78', '汽车3', '855.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '17', null, '72', null, '说...');
INSERT INTO `product` VALUES ('79', '汽车4', '633.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '17', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('80', '汽车5', '744.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '17', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('81', '美食1', '633.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '18', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('82', '美食2', '744.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '18', null, '52', null, '不可说...');
INSERT INTO `product` VALUES ('83', '美食3', '855.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '18', null, '72', null, '说...');
INSERT INTO `product` VALUES ('84', '美食4', '633.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '18', null, '30', null, '上逆天下改命');
INSERT INTO `product` VALUES ('85', '美食5', '744.00', '网上支付', '免邮费', null, '保修1年', '7', '0', '', '', '0', '0', null, null, null, 'product/aideyishu1.jpg#product/aideyishu2.png', '', '18', null, '52', null, '不可说...');

-- ----------------------------
-- Table structure for publish
-- ----------------------------
DROP TABLE IF EXISTS `publish`;
CREATE TABLE `publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '出版社名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gb2312 COMMENT='出版社表';

-- ----------------------------
-- Records of publish
-- ----------------------------
INSERT INTO `publish` VALUES ('1', '清华大学出版社');
INSERT INTO `publish` VALUES ('2', '电子工业出版社');
INSERT INTO `publish` VALUES ('3', '中华书局');
INSERT INTO `publish` VALUES ('4', '中信出版社');
INSERT INTO `publish` VALUES ('5', '上海译文出版社');
INSERT INTO `publish` VALUES ('6', '北京大学出版社');
INSERT INTO `publish` VALUES ('7', '机械工业出版社');
INSERT INTO `publish` VALUES ('8', '人民出版社');

-- ----------------------------
-- Table structure for receiver
-- ----------------------------
DROP TABLE IF EXISTS `receiver`;
CREATE TABLE `receiver` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '收件人姓名',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '收件人地址',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '收件人电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312 COMMENT='收件人表';

-- ----------------------------
-- Records of receiver
-- ----------------------------
INSERT INTO `receiver` VALUES ('2', '3', '李四', '上海', '1234567891');
INSERT INTO `receiver` VALUES ('3', '9', '王五', '北京', '1234');
INSERT INTO `receiver` VALUES ('4', '18', 'test1', '上海', '1234');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '推荐理由',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312 COMMENT='推荐理由表';

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('1', '竭诚向你推荐');
INSERT INTO `report` VALUES ('2', '最近热卖');
INSERT INTO `report` VALUES ('3', '洋溢的清楚');
INSERT INTO `report` VALUES ('4', '学习技术必备');
INSERT INTO `report` VALUES ('5', '大数据推荐');

-- ----------------------------
-- Table structure for report_products
-- ----------------------------
DROP TABLE IF EXISTS `report_products`;
CREATE TABLE `report_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` int(11) NOT NULL DEFAULT '0' COMMENT '推荐理由id',
  `product_id` int(20) NOT NULL DEFAULT '0' COMMENT '产品号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=gb2312 COMMENT='推荐产品表';

-- ----------------------------
-- Records of report_products
-- ----------------------------
INSERT INTO `report_products` VALUES ('1', '1', '1');
INSERT INTO `report_products` VALUES ('2', '1', '2');
INSERT INTO `report_products` VALUES ('3', '1', '3');
INSERT INTO `report_products` VALUES ('4', '2', '4');
INSERT INTO `report_products` VALUES ('5', '2', '5');
INSERT INTO `report_products` VALUES ('6', '3', '1');
INSERT INTO `report_products` VALUES ('7', '3', '6');
INSERT INTO `report_products` VALUES ('8', '3', '8');
INSERT INTO `report_products` VALUES ('9', '4', '7');
INSERT INTO `report_products` VALUES ('10', '4', '8');
INSERT INTO `report_products` VALUES ('11', '4', '9');
INSERT INTO `report_products` VALUES ('12', '5', '10');
INSERT INTO `report_products` VALUES ('13', '5', '11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`name`) COMMENT '用户姓名的唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gb2312 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', '1234', null, null);
INSERT INTO `user` VALUES ('2', 'zhangsan2', '1234', null, null);
INSERT INTO `user` VALUES ('3', '李四', '1234', '', '1234567891');
INSERT INTO `user` VALUES ('4', '李四2', '1234', '', '1234567890');
INSERT INTO `user` VALUES ('5', '李四3', '1234', '', '1234567890');
INSERT INTO `user` VALUES ('6', '李四5', '1234', '', '1234567890');
INSERT INTO `user` VALUES ('7', '李四6', '1234', '', '1234567890');
INSERT INTO `user` VALUES ('8', '李四7', '', '', '1234567890');
INSERT INTO `user` VALUES ('9', '王五', '1234', '', '1234567890');
INSERT INTO `user` VALUES ('10', '赵六', '1234', null, null);
INSERT INTO `user` VALUES ('11', '赵六2', '1234', null, null);
INSERT INTO `user` VALUES ('12', 'test1', '1234', '上海', '');
INSERT INTO `user` VALUES ('13', 'test2', '1234', '北京', '');
INSERT INTO `user` VALUES ('14', 'test3', '1234', '北京', '');
INSERT INTO `user` VALUES ('15', 'test5', '1234', '北京', '');
INSERT INTO `user` VALUES ('16', 'testus1', '1234', '上海', '');
INSERT INTO `user` VALUES ('17', 'testun3', '1234', '上海', '');
INSERT INTO `user` VALUES ('18', '王者', '1234', '', '');
