select * from user;

insert into user values ("11","601653489","wjasdasd","王俊","123@qq.com","110","武汉","1","asd");

CREATE TABLE user_info (
	username VARCHAR(255),
	password VARCHAR(255),
	name VARCHAR(255),
	state INT,
	code VARCHAR(64),
	createTime datetime,
	lastLoginTime ,
	PRIMARY KEY (username)
) ENGINE=InnoDB;