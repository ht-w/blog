/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.27 : Database - project_blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`project_blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `project_blog`;

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `t_category` */

insert  into `t_category`(`id`,`name`) values 
(9,'Art艺术'),
(13,'哲学'),
(15,'编程');

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `parent_id` bigint DEFAULT NULL,
  `foreign_id` bigint NOT NULL DEFAULT '0',
  `is_admin` tinyint NOT NULL DEFAULT '0',
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`nickname`,`email`,`avatar`,`content`,`create_time`,`parent_id`,`foreign_id`,`is_admin`,`is_deleted`) values 
(1,'kangting','','','后现代式的颓废主义','2021-11-19 09:00:31',NULL,12,0,1),
(2,'Visitor5','','','haha','2021-11-19 09:01:07',1,12,0,0),
(3,'Visitor6','','','测试','2021-11-23 10:47:31',2,12,0,0),
(4,'Visitor5 ','','','oh yea','2021-12-13 04:19:13',NULL,12,0,0),
(5,'asd','','','asd','2021-12-13 09:56:28',NULL,12,0,0),
(6,'asda','','','asdasd','2021-12-13 09:57:27',NULL,12,0,0),
(7,'asdasd','','','asd','2021-12-13 09:58:35',12,12,0,0),
(8,'adsasd','','','sadasd','2021-12-13 09:59:10',NULL,12,0,0),
(9,'asdasd','','','asdasd','2021-12-13 10:06:12',NULL,12,0,0),
(10,'asdasd','','','asdasd','2021-12-13 10:06:21',10,12,0,0),
(11,'asd','','','asd','2021-12-14 06:47:36',NULL,12,0,0),
(12,'asdasd','','','asd','2021-12-14 06:49:28',4,12,0,1),
(13,'asd','','','asd','2021-12-14 06:53:01',NULL,12,0,1),
(14,'wawaw','','','wawawa','2021-12-14 06:58:22',NULL,12,0,0),
(15,'测试','','','测试','2021-12-14 07:15:35',NULL,12,0,1),
(16,'asdasdasd','','','Poetry is a form of literature that uses aesthetic and often rhythmic qualities of language − such as phonaesthetics, sound symbolism, and metre − to evoke','2021-12-14 10:25:42',11,12,0,0),
(17,'','','','第一次见完整运用新拟物风格而且执行非常到位的app，加油！','2021-12-14 10:31:29',NULL,12,0,1),
(18,'此','','','测试\n测试','2021-12-14 10:31:59',NULL,12,0,1),
(19,'','','','好啊好啊','2021-12-14 12:13:20',NULL,12,0,1),
(20,'','','','@asdasd 真有趣','2021-12-15 02:14:58',NULL,12,0,1),
(21,'','','','。。。','2021-12-15 02:48:18',2,12,0,1),
(22,'','','','testtesttest','2021-12-15 02:49:54',NULL,12,0,1),
(23,'','','','废物','2021-12-15 02:51:14',3,12,0,1),
(24,'','','','i like your comment','2021-12-15 03:03:58',14,12,0,1),
(25,'','','','傻逼','2021-12-22 12:56:48',1,12,0,1),
(26,'','','','','2022-01-06 12:49:44',NULL,12,0,1),
(27,'康庭','hongtingwong828@gmail.com','','测试一下评论功能','2022-01-17 06:28:01',NULL,12,0,0),
(28,'得贤','asdzxc@gmai.com','','test the comment ','2022-01-17 06:33:05',27,12,0,0),
(29,'asd','asdzxc@gmail.com','','asd','2022-01-17 08:09:30',NULL,13,0,1),
(30,'ASD','asdzxc@gmail.com','','ASDASD','2022-01-31 15:49:49',NULL,1,0,1),
(31,'康庭','asdzxc@gmail.com.com','','一群智障','2022-01-31 16:00:09',NULL,12,0,0),
(32,'ASDADS','ASD@gmail.com','','AASD','2022-02-08 07:06:16',NULL,12,0,0),
(33,'the beatle','asdasdasd@gmail.com','','test','2022-02-08 12:24:31',NULL,11,0,0),
(34,'asdasd','asd@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar0.jpg','asdasd','2022-02-08 12:31:59',NULL,11,0,0),
(35,'康庭','rachel@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar6.jpg','测试','2022-02-08 12:34:42',NULL,11,0,0),
(36,'asd','asd@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar2.jpg','ra','2022-02-08 12:35:12',NULL,11,0,0),
(37,'得闲','hongting@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar2.jpg','测试你麻痹','2022-02-08 12:42:05',35,11,0,0),
(38,'小白','xiaobai@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar10.jpg','好有趣的文章','2022-02-08 12:43:01',NULL,11,0,0),
(39,'飒飒','sss@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar10.jpg','飒飒','2022-02-08 13:06:30',NULL,11,0,0),
(40,'sdas','dasd@qq.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar2.jpg','asd','2022-02-08 13:07:01',NULL,11,0,0),
(69,'hongting','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar7.jpg','test email','2022-02-09 16:59:18',NULL,16,0,0),
(72,'rachel','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar7.jpg','hi','2022-02-09 17:02:30',69,16,0,0),
(73,'yiler','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar1.jpg','傻逼','2022-02-09 17:10:43',72,16,0,0),
(74,'康庭','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar1.jpg','好蠢','2022-02-09 17:13:46',73,16,0,0),
(75,'名扬','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar5.jpg','测试一下速度','2022-02-09 17:18:28',74,16,0,0),
(76,'康庭','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar9.jpg','浪漫的我','2022-02-15 05:17:43',75,16,0,0),
(77,'康庭','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar4.jpg','再度测试','2022-02-15 05:26:02',NULL,7,0,0),
(78,'kangting','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar8.jpg','try','2022-02-15 17:19:31',NULL,13,0,0),
(79,'hongting','hongtingwong828@gmail.com','https://raw.githubusercontent.com/ht-w/blog-images/main/img/profile.jpg','try ','2022-02-25 08:17:42',NULL,5,0,0),
(80,'康庭','hongtingwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar4.jpg','test','2022-02-25 08:25:34',NULL,5,0,0),
(81,'asdasd','asda@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar1.jpg','asdasdasd','2022-02-25 08:26:16',NULL,5,0,0),
(82,'康庭','asd@gmail.com','https://raw.githubusercontent.com/ht-w/blog-images/main/img/blogavatar.jpg','康庭','2022-02-25 08:27:11',81,5,0,0),
(83,'康庭','asd@gmail.com','https://raw.githubusercontent.com/ht-w/blog-images/main/img/blogavatar.jpg','test the comment','2022-02-25 08:27:46',NULL,5,0,0),
(84,'康庭','asd@gmail.com','https://raw.githubusercontent.com/ht-w/blog-images/main/img/blogavatar.jpg','test test test','2022-02-25 09:07:15',NULL,5,1,0),
(85,'康庭','test@asd.com','https://raw.githubusercontent.com/ht-w/blog-images/main/img/blogavatar.jpg','test admin comment','2022-02-25 11:44:28',NULL,5,1,0),
(86,'康庭','hongtngwong828@gmail.com','https://resources-blog.oss-ap-southeast-3.aliyuncs.com/comment/avatar2.jpg','try','2022-02-27 05:04:37',NULL,13,0,0),
(87,'康庭','asd@gmail.com','https://raw.githubusercontent.com/ht-w/blog-images/main/img/blogavatar.jpg','asd','2022-02-27 05:08:32',NULL,13,1,0);

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `t_link` */

insert  into `t_link`(`id`,`name`,`url`,`avatar`,`status`,`create_time`,`update_time`,`is_deleted`) values 
(1,'大问题 Dialetic','https://space.bilibili.com/1871365234','https://raw.githubusercontent.com/ht-w/blog-images/main/img/dialectic.jpg',1,'2022-02-22 05:04:30','2022-02-24 10:27:25',0),
(2,'得意忘形','https://podbay.fm/p/1200767928','https://raw.githubusercontent.com/ht-w/blog-images/main/img/deyiwangxin.jpg',1,'2022-02-24 05:51:45','2022-02-24 07:07:26',1),
(3,'得意忘形','https://podbay.fm/p/1200767928','https://raw.githubusercontent.com/ht-w/blog-images/main/img/deyiwangxin.jpg',1,'2022-02-24 07:52:50','2022-02-24 10:27:26',0),
(4,'得意忘形','https://podbay.fm/p/1200767928','https://raw.githubusercontent.com/ht-w/blog-images/main/img/deyiwangxin.jpg',0,'2022-02-24 07:54:07','2022-02-24 11:07:51',1),
(5,'asdasd','asdasd','asdasd',0,'2022-02-27 07:41:58','2022-02-27 07:42:52',1);

/*Table structure for table `t_post` */

DROP TABLE IF EXISTS `t_post`;

CREATE TABLE `t_post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `category_id` bigint NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `view` bigint NOT NULL DEFAULT '0',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `t_post` */

insert  into `t_post`(`id`,`title`,`category_id`,`description`,`content`,`view`,`image`,`create_time`,`update_time`,`is_deleted`) values 
(1,'我要成为海贼王！',1,'testing1234',NULL,0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-10 15:56:23','2022-02-08 07:03:06',0),
(2,'testing2',1,'testing2',NULL,0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:37:54','2022-02-08 07:03:08',0),
(3,'testing3',1,'testing3',NULL,0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:38:08','2022-02-15 16:46:00',1),
(4,'testing4',1,'testing4',NULL,0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:38:30','2022-02-08 07:03:09',0),
(5,'testing5',1,'\nIt’s been a while.  Last week I flew to Seattle to attend Microsoft’s Imagine Cup World Finals.  I was lucky enough to be among the three Canadian finalists to attend the competition, meeting bright minds all around the world.','It’s been a while.  Last week I flew to Seattle to attend Microsoft’s Imagine Cup World Finals.  I was lucky enough to be among the three Canadian finalists to attend the competition, meeting bright minds all around the world',0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:38:37','2022-02-08 07:03:10',0),
(6,'testing6',5,'testing6','书中主要是在探讨政治哲学，对后来的学者有巨大的影响，成为政治学领域的基本经典。本篇对话录关心到底什么是“世事的道理”，并且探讨的领域包括了经济学、政治社会学、政治哲学、伦理学、正义及知识。上述领域都是从研究正义之性质的角度作思考——这就是对话的中心问题：什么是正义？在对话中，柏拉图借苏格拉底之口讨论了正义的涵义以及正义的人是否比非正义的人生活的更快乐。[6]\n\n从这个基点出发，柏拉图（通过苏格拉底）考虑了现有政治制度的本质、并构造一个理想城市的模型。这个城市的司法理论是完美的。柏拉图的理想政体模式是依靠德性，建立在知识和真理之上的贵族政体。如果理想的贵族政体发生蜕变，荣誉取代了知识和德性，则形成勋阀政体；勋阀政体的荣誉被财富取代，则形成寡头政体；寡头政体蜕变形成民主政体，再蜕变则形成僭主政体[7]。\n\n在《政治家篇》和《法律篇》中，柏拉图对理想政体和现实政体根据统治者数量进行了区分，分为君主政体、僭主政体、贵族政体、寡头政体、民主政体和共和政体。此理论被亚里士多德进一步完善，一直为政治家们所采用。\n\n此外，柏拉图在《法律篇》还提出了“混合政体”。他认为这是最好最稳定的政体，综合了君主政体跟贵族政体的智慧和德性(德行)以及稳定、和平及秩序还有平民政体、民主政体跟共和政体的平等(又叫做公正，也就是公平)和自由。\n\n第一卷',0,'https://resources-blog.oss-ap-southeast-3.aliyuncs.com/article/2022/01/13//2207a88b-9e06-4da0-b899-4e159efc8838.jpg','2021-11-14 04:38:54','2022-02-08 07:03:10',0),
(7,'testing7',5,'testing7','asdasdasd',0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:39:07','2022-02-08 06:32:25',0),
(8,'testing8',7,'testing123123123','asdasdasd',0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:39:13','2022-02-08 06:31:53',0),
(9,'testing9',7,'testing9','zxcdasd',0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:39:29','2022-02-08 06:26:36',0),
(10,'testing10',7,'testing10','[![](https://raw.githubusercontent.com/ht-w/blog-images/main/img/profile.jpg)]()\n\njhkjhkhk',0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-14 04:39:45','2022-02-08 07:16:10',0),
(11,'testing11',9,'IMAGINE ALL THE PEOPLE IMAGINE ALL THE PEOPLE IMAGINE ALL THE PEOPLEIMAGINE ALL THE PEOPLE IMAGINE ALL THE PEOPLE  IMAGINE ALL THE PEOPLE IMAGINE ALL THE PEOPLE IMAGINE ALL THE PEOPLEIMAGINE ALL THE PEOPLE IMAGINE ALL THE PEOPLE  ','## 二级标题\nasdasdasdasd',0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-13 20:39:55','2022-02-08 07:03:12',0),
(12,'如何成为优秀的人',1,'testing12','# 在springboot中实现发送邮件\n\n## 前言\n\n最近增加了发送评论回复邮件的功能，当博主对一个评论进行回复时，如果被回复的评论的邮箱正确的话，异步向其留下的邮箱发送提示邮件。实际上，有多地方都会用到邮件发送，可以把异常的信息发送到自己的邮箱，后面会增加的用户注册和登录功能，在用户注册账户和找回密码时，会通过邮件发送验证码。\n\n事实上，在 SpringBoot 实现邮件发送非常简单。\n\n## 实现步骤\n\n### 1. 首先加入mail依赖\n\n><!-- mail -->  \n><dependency>  \n>    <groupId>org.springframework.boot</groupId>  \n>    <artifactId>spring-boot-starter-mail</artifactId>  \n></dependency>  \n\n\n\n### 2. 获取授权码\n\n我使用的网易邮箱，在网易邮箱的设置中找到**POP3/SMTP/IMAP**这一项，然后开启POP3/SMTP或者IMAP/SMTP服务服务，我们只使用SMTP服务，按照它的提示发送短信验证码后，可以获得授权码，我们把它复制下来。\n\nqq邮箱同理，在设置中可以找到。\n\n### 3. 配置文件进行配置\n\n下面两种任选其一。\n\n在yml文件中进行下列配置：\n\n>spring:\n>  mail:\n>\n>​     #网易邮箱服务器\n>\n>​      host:  smtp.163.com\n>\n>​      #你的QQ邮箱账户\n>\n>​      username: yourAccount@163.com\n>\n>​     #你的163邮箱第三方授权码\n>\n>​      password: yourPassword  \n>\n>​     #编码类型\n>\n>​      default-encoding: UTF-8\n\n在properties文件中的配置如下：\n\n>#这里以QQ邮箱为例  \n>#QQ邮箱服务器  \n>spring.mail.host=smtp.163.com  \n>#你的QQ邮箱账户  \n>spring.mail.username=yourAccount@qq.com  \n>#你的QQ邮箱第三方授权码  \n>spring.mail.password=yourPassword  \n>#编码类型   \n>spring.mail.default-encoding=UTF-8  \n\n### 4. 编写服务接口和实现类\n\n定义发送邮件接口：\n\n```\npublic interface MailService {\n\n    /**\n     * 发送普通文本邮件\n     *\n     * @param toAccount 收件人\n     * @param subject 主题\n     * @param content 内容\n     */\n    void sendSimpleMail(String toAccount, String subject, String content);\n\n    /**\n     * 发送HTML邮件\n     *\n     * @param toAccount 收件人\n     * @param subject 主题\n     * @param content 内容（可以包含<html>等标签）\n     */\n    void sendHtmlMail(String toAccount, String subject, String content);\n\n\n}\n```\n\n定义发送邮件的实现类：\n\n```\n@Service\npublic class MailServiceImpl implements MailService {\n\n    @Autowired\n    private JavaMailSender javaMailSender;\n\n    @Autowired\n    private MailProperties mailProperties;\n\n    /**\n     * 发送普通文本邮件\n     *\n     * @param toAccount 收件人\n     * @param subject 主题\n     * @param content 内容\n     */\n    @Override\n    @Async\n    public void sendSimpleMail(String toAccount, String subject, String content) {\n\n        try {\n            SimpleMailMessage message = new SimpleMailMessage();\n            message.setFrom(mailProperties.getUsername());\n            message.setTo(toAccount);\n            message.setSubject(subject);\n            message.setText(content);\n            javaMailSender.send(message);\n        } catch (Exception e) {\n            e.printStackTrace();\n        }\n    }\n\n    /**\n     * 发送HTML邮件\n     *\n     * @param toAccount 收件人\n     * @param subject 主题\n     * @param content 内容（可以包含<html>等标签）\n     */\n    @Override\n    @Async\n    public void sendHtmlMail(String toAccount, String subject, String content) {\n        try {\n            MimeMessage mimeMessage = javaMailSender.createMimeMessage();\n            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);\n            messageHelper.setFrom(mailProperties.getUsername());\n            messageHelper.setTo(toAccount);\n            messageHelper.setSubject(subject);\n            messageHelper.setText(content, true);\n            javaMailSender.send(mimeMessage);\n        } catch (Exception e) {\n            e.printStackTrace();\n        }\n    }\n    \n}\n```\n\n\n\n### 5. 发送邮件\n\n在需要的地方先注入MailService，然后调用\n\n>@Autowired  \n>MailService mailService;  \n\n\n\n## 最后\n\n上面只实现了发送简单文本和html格式的邮件，如果需要发送附件和图片，百度找一下，有很多模板的。\n\n最后提供一个检查邮箱格式是否正确的正则表达式：\n\n>public static boolean orEmail(String email) {  \n>        if (email == null || \"\".equals(email)) return false;  \n>       String regex = \"\\\\w+([-+.]\\\\w+)*@\\\\w+([-.]\\\\w+)*\\\\.\\\\w+([-.]\\\\w+)*\";  \n>       return email.matches(regex);  \n> }  \n\n',0,'http://project-crowd-test.oss-ap-southeast-3.aliyuncs.com/20211021/f130995515d64b2bad490819129d21ce.jpg','2021-11-13 12:40:05','2022-02-08 07:03:12',0),
(13,'ELASTICSEARCH教学',1,'从 2010 年起，我每年都会重新设计与开发一次自己的个人网站，每次改版都会整理自己在过去一年里所学到的东西并与大家分享。直到 2015 年，几乎是在准备第六次改版的同时，我忽然有了二维信息流、模块化内容和私密社交网络的想。','# 一级标题',0,'https://resources-blog.oss-ap-southeast-3.aliyuncs.com/article/2022/01/12//de6bfe78-7aa7-46c2-b807-3322e5b1df8f.jpg','2021-12-21 07:06:16','2022-02-08 07:03:13',0),
(14,'asd',9,'asdasd','asdasd',0,'','2022-02-08 07:11:52','2022-02-08 07:15:17',1),
(15,'asd',9,'asd','asd',0,'','2022-02-08 07:18:11','2022-02-08 07:50:03',1),
(16,'asd',9,'asd','#### 四级标题',0,'https://resources-blog.oss-ap-southeast-3.aliyuncs.com/article/2022/02/08//8ff25c9b-a142-44b9-97d8-2d0c758aebdf.jpg','2022-02-08 07:49:53','2022-02-08 07:50:30',0);

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) NOT NULL,
  `method` varchar(10) NOT NULL,
  `status` smallint NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_resource` */

insert  into `t_resource`(`id`,`uri`,`method`,`status`,`create_time`,`update_time`,`is_deleted`) values 
(1,'/auth/login','POST',9,NULL,NULL,0),
(2,'/api/post/**','GET',9,NULL,NULL,0),
(3,'/api/category/**','GET',9,NULL,NULL,0),
(4,'/api/comment/**','GET',9,NULL,NULL,0),
(5,'/api/archives/**','GET',9,NULL,NULL,0),
(6,'/api/search/**','GET',9,NULL,NULL,0),
(7,'/api/**','DELETE',1,NULL,NULL,0),
(8,'/api/**','POST',1,NULL,NULL,0),
(9,'/api/user/**','GET',1,NULL,'2022-02-27 11:19:44',0),
(10,'/api/**','PUT',1,NULL,NULL,0),
(11,'/api/comment/*','POST',9,NULL,NULL,0),
(12,'/api/roleResourceBind/**','GET',1,NULL,NULL,0),
(13,'/api/link/','POST',9,NULL,'2022-02-27 07:31:43',0),
(14,'/api/link/**','GET',9,NULL,'2022-02-27 07:31:46',0),
(15,'/api/link/admin','POST',1,NULL,NULL,0),
(43,'asda','GET',1,NULL,'2022-02-27 08:39:43',1),
(44,'asdaadaasd','GET',1,NULL,'2022-02-27 08:39:45',1),
(46,'asdaadaasdasd','GET',1,NULL,'2022-02-27 08:39:47',1),
(47,'asdaadaasdasdasdasd','GET',1,NULL,'2022-02-27 08:39:41',1),
(48,'asdasdassssssssssssssssssssssssss','PUT',1,NULL,'2022-02-27 08:39:39',1),
(49,'asdddddddddddddddddd','GET',9,NULL,'2022-02-27 08:39:34',1),
(50,'asdasdasd','GET',1,NULL,'2022-02-27 11:10:53',1),
(51,'asdddddddddddddddddddddd','DELETE',1,NULL,'2022-02-27 11:10:50',1),
(52,'asddddddddddddddasdasddddddddddddd','GET',1,NULL,'2022-02-27 11:10:51',1),
(53,'ilvwuansiew','PUT',1,NULL,'2022-02-27 11:10:48',1),
(54,'test','POST',9,NULL,'2022-02-27 11:10:46',1),
(55,'asdasdasd','GET',9,NULL,'2022-02-27 11:10:35',1),
(56,'asdasd','GET',9,NULL,'2022-02-27 11:10:37',1),
(57,'asdzxcasdzxc','GET',9,NULL,'2022-02-27 11:10:33',1),
(58,'asdzxcasdzxc','GET',9,NULL,'2022-02-27 11:10:32',1),
(59,'asdzxcasdzxcasd','PUT',9,NULL,'2022-02-27 11:10:31',1),
(60,'test','GET',9,NULL,'2022-02-27 11:55:17',1),
(61,'test','GET',9,NULL,'2022-02-27 11:59:34',1);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`code`) values 
(1,'Admin','role_admin'),
(2,'Visitor','role_guest');

/*Table structure for table `t_role_resource_bind` */

DROP TABLE IF EXISTS `t_role_resource_bind`;

CREATE TABLE `t_role_resource_bind` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `resource_id` bigint NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bind` (`role_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_role_resource_bind` */

insert  into `t_role_resource_bind`(`id`,`role_id`,`resource_id`,`create_time`,`update_time`,`is_deleted`) values 
(7,1,7,'2022-01-24 13:01:07','2022-01-24 13:01:07',0),
(8,1,8,'2022-01-24 13:01:07','2022-01-24 13:01:07',0),
(9,1,9,'2022-01-24 13:01:07','2022-01-24 13:01:07',0),
(10,1,10,'2022-02-08 06:58:16','2022-02-08 06:58:16',0),
(12,1,12,'2022-02-08 07:09:05','2022-02-08 07:09:05',0),
(13,1,15,'2022-02-16 15:26:19','2022-02-16 15:47:23',0),
(14,1,18,'2022-02-16 15:31:23','2022-02-16 15:47:25',0),
(16,0,23,'2022-02-27 06:57:56','2022-02-27 06:57:56',0),
(17,0,25,'2022-02-27 06:59:34','2022-02-27 06:59:34',0),
(18,0,26,'2022-02-27 06:59:44','2022-02-27 06:59:44',0),
(19,0,28,'2022-02-27 07:00:06','2022-02-27 07:00:06',0),
(20,1,29,'2022-02-27 07:07:26','2022-02-27 07:07:26',0),
(21,2,30,'2022-02-27 07:07:33','2022-02-27 07:07:33',0),
(22,1,31,'2022-02-27 07:07:57','2022-02-27 07:07:57',0),
(23,0,32,'2022-02-27 07:08:12','2022-02-27 07:08:12',0),
(25,1,34,'2022-02-27 07:59:20','2022-02-27 07:59:20',0),
(26,2,36,'2022-02-27 07:59:29','2022-02-27 07:59:29',0),
(27,0,43,'2022-02-27 08:21:04','2022-02-27 08:21:04',0),
(28,0,44,'2022-02-27 08:22:13','2022-02-27 08:22:13',0),
(29,0,46,'2022-02-27 08:22:21','2022-02-27 08:22:21',0),
(30,0,47,'2022-02-27 08:22:35','2022-02-27 08:22:35',0),
(31,0,48,'2022-02-27 08:35:19','2022-02-27 08:35:19',0),
(32,0,49,'2022-02-27 08:38:40','2022-02-27 08:38:40',0),
(33,2,50,'2022-02-27 08:41:13','2022-02-27 08:41:13',0),
(34,0,51,'2022-02-27 08:43:36','2022-02-27 08:43:36',0),
(35,2,52,'2022-02-27 08:45:37','2022-02-27 08:45:37',0),
(36,1,53,'2022-02-27 08:45:54','2022-02-27 08:45:54',0),
(37,1,54,'2022-02-27 10:36:24','2022-02-27 10:36:24',0),
(38,1,60,'2022-02-27 11:25:32','2022-02-27 11:52:16',0),
(39,0,61,'2022-02-27 11:55:35','2022-02-27 11:55:35',0);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `salt` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`nickname`,`password`,`salt`,`email`,`avatar`,`description`,`create_time`,`update_time`,`is_deleted`) values 
(1,'kangting','kangting','5+TWHy7sml7EGieF5WKLaTB8fk6VPH0j9OPJn5yHi0s=',NULL,'hongtingwong@gmail.com',NULL,NULL,'2021-11-08 10:12:47','2022-01-24 17:08:59',1),
(6,'blogvisitor','blogvisitor','E1rgZcx2DfX9YEx58/LFSdRG2/dGM+NnkGJdVpXwaYc=',NULL,'blogvisitor@gmail.com',NULL,'Testing.....','2021-11-11 04:21:52','2021-11-12 09:37:53',1),
(7,'Visitor2 ','blogvisitor2','gao5h7MBmspNICnqFhM/q/646wIOmG6Hp0ZmONMXXAs=',NULL,'blogvisitor2gmail.com',NULL,'Test .....2nd time','2021-11-10 12:37:13','2022-01-24 17:10:51',1),
(8,'Visitor3','blogvisitor3','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'blogvisitor3yahoo.com',NULL,'test to edit the user','2021-11-05 21:24:45','2021-11-13 13:54:57',1),
(9,'Visitor5','Visitor5','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'asdxzc@gmail.com',NULL,'asdzxc','2021-11-09 06:50:55','2022-02-08 07:32:36',1),
(10,'Visitor4','Visitor4','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'Visitor4@gmail.com',NULL,'test','2021-11-06 16:05:57','2022-02-08 07:33:33',1),
(11,'Visitor6','Visitor6','et1noN1NSLyOD7TzMLPmGdXmNlbUYnNyr56lTNc7chM=',NULL,'Visitor6@gmail.com',NULL,'test authority assignation','2021-11-11 16:57:15','2022-02-08 07:34:09',1),
(12,'ka','kan','1c23/p0EvfdKdvD0WHLzOZnTY1AmCgDW6D63Mr7g3sM=',NULL,'hongtingwong828gmail.com',NULL,NULL,'2021-11-13 17:28:08','2021-11-13 17:28:56',1),
(14,'Visitor7','Visitor7','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'zxcasd@gmail.com',NULL,'asdasdasd','2021-11-14 05:20:56','2022-02-08 07:33:28',1),
(15,'Visitor8','Visitor8','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'zxcasd@gmail.com',NULL,'asdasdasd','2021-11-14 05:21:12','2022-02-08 07:35:11',1),
(16,'Visitor9','Visitor9','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'zxcasd@gmail.com',NULL,'asdasdasd','2021-11-14 05:21:22','2022-02-08 07:31:53',1),
(17,'Visitor10','Visitor10','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'zxcasd@gmail.com',NULL,'asdasdasd','2021-11-14 05:21:31','2022-02-08 07:35:09',1),
(18,'Visitor11','Visitor11','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'zxcasd@gmail.com',NULL,'asdasdasd','2021-11-14 05:21:38','2022-01-20 07:40:15',1),
(19,'Visitor12','Visitor12','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'zxcasd@gmail.com',NULL,'asdasdasd','2021-11-14 05:21:47','2022-01-24 17:12:01',1),
(20,'Visitor13','Visitor13','3X4e+cuct229lU+se6e1aC5zgvxS1rgc9ejobtt/e0M=',NULL,'zxcasd@gmail.com',NULL,'asdasdasd','2021-11-14 05:22:02','2022-02-08 07:30:23',1),
(21,'asdasd','asdasdasd','cXEps/+NdpOKXfzsf1Ikmkb0ttGIKNdkWqpMYbJE6i4=',NULL,'asdasdasd@gmail.com',NULL,'','2021-11-19 05:11:32','2022-02-08 07:29:27',1),
(22,'test','test','xyGF/VPaVh+hbb99EOsMu7SYx0Jif8CQlHvK6rHfy24=',NULL,'test123@gmail.com',NULL,'asdzxc','2022-01-19 05:39:10','2022-02-08 07:10:49',1),
(23,'hongting','康庭','C1C661A152E64FA188B84A25C1F35EC0','qfwnz8','hongtingwong828gmail.com',NULL,NULL,'2022-01-24 14:56:32','2022-02-25 08:25:44',0),
(24,'guest','guest','B5C38EAEAB102B8C41478B1ACB1D67C7','c09ves','guest123@gmail.com',NULL,'','2022-01-24 17:10:09','2022-01-24 17:10:09',0),
(25,'rachel','rachel','$2a$10$OYOVKOm/FmcgzhZNj5ZyGuUf6um.u5d8pslBwGqI9Lkwux6/f.j/2',NULL,'hongtingwong828gmail.com',NULL,NULL,'2022-01-29 06:59:54','2022-02-08 07:35:15',1),
(29,'visitor1234','visitor1234','6B343198CD185B6DB1DAA168B5049077','xy4w4u','hongtingwong828@gmail.com',NULL,'test1234','2022-02-16 06:36:38','2022-02-27 08:28:35',1),
(30,'kifeng23','kifeng23','EFE70463318A73B735E1D552AA694798','qejbmz','asd@gmail.com',NULL,'asdasdasd','2022-02-16 06:38:43','2022-02-16 06:38:43',0),
(31,'yilerchia','yilerchia','F189E671A9E06E33E84DCBB4B50DF301','6hyepu','asdzxc@gmail.com',NULL,'yi ler i love you','2022-02-16 15:34:09','2022-02-16 15:34:09',0),
(33,'test1231','test','9347D1D3EF12C446EB821D19643EF8BC','u9j78e','test@asd.com',NULL,'asdasdasd','2022-02-27 08:26:48','2022-02-27 08:28:08',1),
(35,'asdasdasdasd','asdasd','9DAB8D26E46B6357B9671D5805556DDF','5389mn','asdasdasd@gmail.com',NULL,'asdasdasd','2022-02-27 08:29:13','2022-02-27 08:29:13',0);

/*Table structure for table `t_user_role_bind` */

DROP TABLE IF EXISTS `t_user_role_bind`;

CREATE TABLE `t_user_role_bind` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bind` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_user_role_bind` */

insert  into `t_user_role_bind`(`id`,`user_id`,`role_id`,`create_time`,`update_time`,`is_deleted`) values 
(1,1,1,'2022-01-24 08:10:08','2022-01-24 08:10:08',0),
(2,24,2,'2022-01-24 08:10:08','2022-01-24 17:11:49',0),
(3,23,1,'2022-01-24 08:10:08','2022-01-31 14:59:33',0),
(4,29,2,'2022-02-16 06:36:38','2022-02-16 06:36:38',0),
(5,30,1,'2022-02-16 06:38:43','2022-02-16 06:38:43',0),
(6,31,2,'2022-02-16 15:34:09','2022-02-16 15:34:09',0),
(7,33,2,'2022-02-27 08:26:48','2022-02-27 08:26:48',0),
(8,35,1,'2022-02-27 08:29:13','2022-02-27 08:29:13',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
