DROP DATABASE IF EXISTS teach;
CREATE DATABASE teach;
USE teach;

CREATE TABLE teach.user (
  id         BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login_name VARCHAR(30)                 NOT NULL,
  password   CHAR(16)                    NOT NULL,
  nick_name  NVARCHAR(20)                NOT NULL,
  protrait   VARCHAR(20) COMMENT '用户头像文件名',
  phone      VARCHAR(20)                 NOT NULL,
  token      CHAR(20),
  remaining  DECIMAL COMMENT '余额'
);
CREATE UNIQUE INDEX user_login_name_uindex
  ON teach.user (login_name);
CREATE UNIQUE INDEX user_name_phone_uindex
  ON teach.user (phone);


CREATE TABLE teach.teacher (
  id           BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name         NVARCHAR(30),
  subject      NVARCHAR(30) COMMENT '涉及学科',
  graduate     NVARCHAR(30) COMMENT '毕业院校',
  teach_start  INT COMMENT '开始任教年份',
  introduction NVARCHAR(300) COMMENT '经历介绍',
  photo        VARCHAR(30) COMMENT '头像'
);
ALTER TABLE teach.teacher
  COMMENT = '教师信息表';


CREATE TABLE teach.course (
  id           BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name         NVARCHAR(40)                NOT NULL COMMENT '课程名称',
  introdcution NVARCHAR(100) COMMENT '课程描述',
  teacher_id   BIGINT UNSIGNED             NOT NULL COMMENT '教师id',
  video        VARCHAR(30)                 NOT NULL COMMENT '课程视频文件',
  price        DECIMAL                     NOT NULL,
  image        VARCHAR(30)                 NOT NULL
);

CREATE TABLE teach.join_course_user
(
  id        BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id   BIGINT UNSIGNED             NOT NULL,
  course_id BIGINT UNSIGNED             NOT NULL
);

CREATE TABLE teach.comment
(
  id        BIGINT UNSIGNED PRIMARY KEY  NOT NULL  AUTO_INCREMENT,
  user_id   BIGINT UNSIGNED              NOT NULL,
  course_id BIGINT UNSIGNED              NOT NULL,
  content   NVARCHAR(100),
  tiemstamp TIMESTAMP(6)                 NOT NULL
);


CREATE TABLE teach.verification
(
  id      BIGINT UNSIGNED PRIMARY KEY  NOT NULL  AUTO_INCREMENT,
  code    VARCHAR(5)                   NOT NULL,
  picture VARCHAR(30)                  NOT NULL
);


INSERT INTO teach.course
(name, introdcution, teacher_id, image, video, price)
VALUES
  ('Unity5.x 创造 3D VR游戏', NULL, 1, 'image/c_image_1.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('iOS逆向与安全', NULL, 2, 'image/c_image_2.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('人人都会做游戏-Unity5.x创造2D手机游戏', NULL, 3, 'image/c_image_3.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('App Inventor趣味编程', NULL, 4, 'image/c_image_4.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('iOS开发中的神兵利器 [体验版]', NULL, 5, 'image/c_image_5.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android开发视频教程', NULL, 6, 'image/c_image_6.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('IOS开发全套教程(享精品公开课)', NULL, 7, 'image/c_image_7.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android深入浅出', NULL, 8, 'image/c_image_8.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android4.0应用开发第一季', NULL, 9, 'image/c_image_9.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Unity 2D 制作“植物大战僵尸”', NULL, 10, 'image/c_image_10.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android应用开发浅入深高级研修课', NULL, 11, 'image/c_image_11.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('微信小程序全方位深度解析', NULL, 12, 'image/c_image_12.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Unity 2D 搞定类“宝石迷阵”游戏', NULL, 13, 'image/c_image_13.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('玩转 Android 基础课堂', NULL, 14, 'image/c_image_14.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android开发-Android Studio教程', NULL, 15, 'image/c_image_15.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('零基础iOS开发 iOS教程 App开发', NULL, 16, 'image/c_image_16.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('玩游戏也能学编程-CodeCombat', NULL, 17, 'image/c_image_17.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('iOS培训教程-Swift编程语言学习', NULL, 18, 'image/c_image_18.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android高级应用开发Ⅲ_基础篇', NULL, 19, 'image/c_image_19.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Unity 制作 Google CardBoard VR 全景应用', NULL, 20, 'image/c_image_20.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Unity 3D游戏程序开发公开课', NULL, 21, 'image/c_image_21.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('6小时快速开发APP', NULL, 22, 'image/c_image_22.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('OpenGL进阶课程（萌谷手册）', NULL, 23, 'image/c_image_23.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android基础视频教程', NULL, 24, 'image/c_image_24.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android快速开发教程第一季', NULL, 25, 'image/c_image_25.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('C语言入门课程精讲(享精品公开课)', NULL, 26, 'image/c_image_26.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android自动化测试【第一季、第二季】', NULL, 27, 'image/c_image_27.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('欣宝教你做游戏-Unity2D制作超级马里奥', NULL, 28, 'image/c_image_28.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android开发 Android进阶强化 Android精讲', NULL, 29, 'image/c_image_29.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('APP开发从0到1', NULL, 30, 'image/c_image_30.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('OpenGL着色器基础', NULL, 31, 'image/c_image_31.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android底层嵌入式由浅入深高级研修课', NULL, 32, 'image/c_image_32.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('unity3D－游戏/AR/VR在线就业班 unity引擎', NULL, 33, 'image/c_image_33.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android项目开发实战-秘密APP', NULL, 34, 'image/c_image_34.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android开发基础（Android开发零基础II）', NULL, 35, 'image/c_image_35.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Objective-C  全套课程精讲', NULL, 36, 'image/c_image_36.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Unity 3D 制作“像素塔防”游戏', NULL, 37, 'image/c_image_37.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('C语言贪吃蛇教程', NULL, 38, 'image/c_image_38.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('iOS开发从入门到精通[Xcode8和Swift3]', NULL, 39, 'image/c_image_39.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android高级应用开发Ⅴ_软件工程', NULL, 40, 'image/c_image_40.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('iOS - UI开发视频教程第一季', NULL, 41, 'image/c_image_41.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android项目开发实战 - 智能聊天机器人', NULL, 42, 'image/c_image_42.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100)),
  ('Android开发学习', NULL, 43, 'image/c_image_43.jpg', 'abc.mp4', FLOOR(RAND() * 401 + 100));


INSERT INTO verification
(code, picture) VALUES
  ('5k64', 'vcode1.png'),
  ('nans', 'vcode2.png'),
  ('mgs7', 'vcode3.png'),
  ('tvnq', 'vcode4.jpg'),
  ('6528', 'vcode5.png');