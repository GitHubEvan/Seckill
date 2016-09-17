-- 数据库 初始化

--CREATE DATABASE seckill;

use seckill;

CREATE TABLE seckill (
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'product id',
  name VARCHAR(120) NOT NULL COMMENT 'product name',
  number INT NOT NULL COMMENT 'stock number',
  start_time TIMESTAMP NOT NULL COMMENT 'seckill start time',
  end_time TIMESTAMP NOT NULL COMMENT 'seckill end time',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT 'create time',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT 'seckill stock table';


INSERT INTO seckill(name,number,start_time,end_time)
    VALUES
      ('1000元秒杀iphone6',200,'2016-09-14 00:00:00','2016-09-15 00:00:00'),
      ('500元秒杀ipad',300,'2016-09-14 00:00:00','2016-09-15 00:00:00'),
      ('300元秒杀红米note',1000,'2016-09-14 00:00:00','2016-09-15 00:00:00');

use seckill;
DROP TABLE  IF EXISTS success_killed;
CREATE TABLE success_killed(
  seckill_id BIGINT NOT NULL COMMENT 'product id',
  user_phone INT NOT NULL COMMENT 'user phone',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '-1: invalid 0:sucess 1:paied',
  create_time TIMESTAMP NOT NULL COMMENT 'create time',
  PRIMARY KEY (seckill_id,user_phone),
  FOREIGN KEY (seckill_id) REFERENCES seckill (seckill_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT 'seckill success table'
