show tables;
drop table role_mp;
select * from user_info;
insert into user_info (username,password,name) values ("601653488","wjasdasd","王俊");



--user_info
CREATE TABLE user_info (
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,
	state TINYINT DEFAULT 0,
	createtime DATETIME DEFAULT NULL,
	lastlogintime DATETIME DEFAULT NULL,
	PRIMARY KEY (username)
) ENGINE=InnoDB;

--user_role
CREATE TABLE user_role (
	username VARCHAR(255) NOT NULL,
	roleid VARCHAR(255) NOT NULL,
	createtime DATETIME DEFAULT NULL,
	PRIMARY KEY (username,roleid)
) ENGINE=InnoDB;

--role_info
CREATE TABLE role_info (
	roleid VARCHAR(255) NOT NULL,
	rolename VARCHAR(255) NOT NULL,
	createtime DATETIME DEFAULT NULL,
	PRIMARY KEY (roleid)
) ENGINE=InnoDB;

--role_mp
CREATE TABLE role_mp (
	roleid VARCHAR(255) NOT NULL,
	moduleid VARCHAR(255) NOT NULL,
	permissionid VARCHAR(255) NOT NULL,
	createtime DATETIME DEFAULT NULL,
	PRIMARY KEY (roleid,moduleid,permissionid)
) ENGINE=InnoDB;

--module_info
CREATE TABLE module_info (
	moduleid VARCHAR(255) NOT NULL,
	modulename VARCHAR(255) NOT NULL,
	createtime DATETIME DEFAULT NULL,
	PRIMARY KEY (moduleid)
) ENGINE=InnoDB;

--permission_info
CREATE TABLE permission_info (
	permissionid VARCHAR(255) NOT NULL,
	permissionname VARCHAR(255) NOT NULL,
	createtime DATETIME DEFAULT NULL,
	PRIMARY KEY (permissionid)
) ENGINE=InnoDB;

