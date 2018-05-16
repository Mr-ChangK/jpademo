CREATE TABLE demo(
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `age` int NOT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  `modify_time` timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY key(`id`)
);

