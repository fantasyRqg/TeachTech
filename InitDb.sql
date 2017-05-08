DROP DATABASE IF EXISTS teach;
CREATE DATABASE teach;
USE teach;

CREATE TABLE teach.user
(
  id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login_name VARCHAR(30)     NOT NULL,
  password   CHAR(16)        NOT NULL,
  nick_name  NVARCHAR(20)    NOT NULL,
  protrait   VARCHAR(20) COMMENT '用户头像文件名',
  phone      VARCHAR(20)     NOT NULL,
  token      CHAR(20),
  remaining  REAL COMMENT '余额'
);
CREATE UNIQUE INDEX user_login_name_uindex
  ON teach.user (login_name);
CREATE UNIQUE INDEX user_name_phone_uindex
  ON teach.user (phone);


