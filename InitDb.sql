DROP DATABASE IF EXISTS teach;
CREATE DATABASE teach;
USE teach;

CREATE TABLE teach.user (
  id         INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login_name VARCHAR(30)              NOT NULL,
  password   CHAR(16)                 NOT NULL,
  nick_name  NVARCHAR(20)             NOT NULL,
  protrait   VARCHAR(20) COMMENT '用户头像文件名',
  phone      VARCHAR(20)              NOT NULL,
  token      CHAR(20),
  remaining  DECIMAL COMMENT '余额'
);
CREATE UNIQUE INDEX user_login_name_uindex
  ON teach.user (login_name);
CREATE UNIQUE INDEX user_name_phone_uindex
  ON teach.user (phone);


CREATE TABLE teach.teacher (
  id           INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
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
  id           INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name         NVARCHAR(20)             NOT NULL
  COMMENT '课程名称',
  introdcution NVARCHAR(100) COMMENT '课程描述',
  teacherId    INT UNSIGNED             NOT NULL
  COMMENT '教师id',
  video        VARCHAR(30)              NOT NULL
  COMMENT '课程视频文件',
  price        DECIMAL                  NOT NULL
);

CREATE TABLE teach.join_course_user
(
  id        INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id   INT UNSIGNED             NOT NULL,
  course_id INT UNSIGNED             NOT NULL
);

CREATE TABLE teach.comment
(
  id        INT UNSIGNED PRIMARY KEY  NOT NULL  AUTO_INCREMENT,
  user_id   INT UNSIGNED              NOT NULL,
  course_id INT UNSIGNED              NOT NULL,
  content   NVARCHAR(100),
  tiemstamp TIMESTAMP                 NOT NULL
);


CREATE TABLE teach.verification
(
  id      INT UNSIGNED PRIMARY KEY  NOT NULL  AUTO_INCREMENT,
  code    VARCHAR(5)                NOT NULL,
  picture VARCHAR(30)               NOT NULL
);


