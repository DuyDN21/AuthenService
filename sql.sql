-- create db
CREATE DATABASE DemoAuthenDB;
-- create table User
CREATE TABLE DemoAuthenDB.`user`(
	user_id CHAR(36) NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    salt VARCHAR(50) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    dob DATE,
    email VARCHAR(100),
    is_active BIT DEFAULT 1,
    refresh_token CHAR(50)
);
-- create table Team
CREATE TABLE DemoAuthenDB.`team`(
	team_id INT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(50),
	created_date DATE,
    is_active BIT DEFAULT 1
);
-- create table UserTeam
CREATE TABLE DemoAuthenDB.`user_team`(
	id INT AUTO_INCREMENT PRIMARY KEY,
	user_id CHAR(36),
    team_id INT,
    role_id INT -- 1: Owner, 2: Admin, 3: User
);

Insert into DemoAuthenDB.`user`(user_id, username, `password`, salt, full_name) values
('dc6045bf-4e71-45e1-8ee6-3ea8c892180b', 'duydn2', '$2a$12$Ax34HrP649M11bkfju0ovuw/L/lsocUwWaIi/mqDusxwm9bPpEiAi', '$2a$10$RflpeCwsEFXaP5MgIcRKvO','Do Ngoc Duy'),
('5fc10fcf-5560-42c3-a170-3e68fa7f4efe', 'tamvt5', '$2a$12$Ax34HrP649M11bkfju0ovuw/L/lsocUwWaIi/mqDusxwm9bPpEiAi', '$2a$10$3G.1953yFFqyBF9ERCP4Bu', 'Vuong Tran Tam'),
('458129a9-d434-4fa3-92d7-ddd34272a2b2', 'anhttv2', '$2a$12$Ax34HrP649M11bkfju0ovuw/L/lsocUwWaIi/mqDusxwm9bPpEiAi', '$2a$10$iNGRRAqL/i4Wu2LrH6RVje','Ta Thi Van Anh');

Insert into DemoAuthenDB.`team`(team_name, created_date) values
('Team 1', now()),
('Team 2', now());

Insert into DemoAuthenDB.`user_team`(user_id, team_id, role_id) values
('dc6045bf-4e71-45e1-8ee6-3ea8c892180b',1,1), ('5fc10fcf-5560-42c3-a170-3e68fa7f4efe',1,2), ('458129a9-d434-4fa3-92d7-ddd34272a2b2',1,3),
('dc6045bf-4e71-45e1-8ee6-3ea8c892180b',2,2), ('5fc10fcf-5560-42c3-a170-3e68fa7f4efe',2,1), ('458129a9-d434-4fa3-92d7-ddd34272a2b2',2,3);

