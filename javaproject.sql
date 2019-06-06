/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : javaproject

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 06/06/2019 14:20:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer_sheet
-- ----------------------------
DROP TABLE IF EXISTS `answer_sheet`;
CREATE TABLE `answer_sheet` (
  `stu_id` int(11) DEFAULT NULL,
  `stu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sel_chapter` int(11) DEFAULT NULL,
  `sel_que_id` int(11) DEFAULT NULL,
  `answer` varchar(256) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `exam_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `true_counter` varchar(255) DEFAULT NULL,
  `false_counter` varchar(255) DEFAULT NULL,
  KEY `sel_chapter` (`sel_chapter`,`sel_que_id`),
  KEY `stu_id` (`stu_id`),
  KEY `answer_sheet_lib` (`teacher_id`),
  CONSTRAINT `answer_sheet_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for login_state
-- ----------------------------
DROP TABLE IF EXISTS `login_state`;
CREATE TABLE `login_state` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `state` int(20) DEFAULT NULL,
  PRIMARY KEY (`number`),
  KEY `teacher_id` (`teacher_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `login_state_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `login_state_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `chapter` int(11) NOT NULL,
  `que_id` int(11) NOT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `que_describe` varchar(256) DEFAULT NULL,
  `answer_A` varchar(256) DEFAULT NULL,
  `answer_B` varchar(256) DEFAULT NULL,
  `answer_C` varchar(256) DEFAULT NULL,
  `answer_D` varchar(256) DEFAULT NULL,
  `correct_answer` varchar(256) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`chapter`,`que_id`,`teacher_id`) USING BTREE,
  KEY `teacher_id` (`teacher_id`),
  KEY `chapter` (`chapter`,`que_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthday` varbinary(20) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1600011 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
