CREATE DATABASE IF NOT EXISTS red_social;
USE red_social;

CREATE TABLE users(
id              int(255) auto_increment not null,
name            varchar(50) NOT NULL,
surname         varchar(100),
direction		varchar(255) NOT NULL,
country			varchar(255) NOT NULL,
birthday		datetime NOT NULL,
nick			varchar(100) NOT NULL,
email           varchar(100) NOT NULL,
password		varchar(255) NOT NULL,
role			varchar(100) NOT NULL,
image			varchar(255),
updated_at		datetime,
created_at		datetime,
CONSTRAINT pk_users PRIMARY KEY(id)
)ENGINE=InnoDb;

CREATE TABLE follows(
id				int(255) auto_increment NOT NULL,
user			int(255) NOT NULL,
followed		int(255) NOT NULL,
CONSTRAINT pk_follows PRIMARY KEY(id),
CONSTRAINT fk_follows_user FOREIGN KEY(user) REFERENCES users(id),
CONSTRAINT fk_follows_followed FOREIGN KEY(followed) REFERENCES users(id)
)ENGINE=InnoDb;

CREATE TABLE publications(
id              int(255) auto_increment NOT NULL,
id_user         int(255) NOT NULL,
text			varchar(255) NOT NULL,
file			varchar(255),
created_at		datetime NOT NULL,
updated_at              datetime NOT NULL,
CONSTRAINT pk_publication PRIMARY KEY(id),
CONSTRAINT fk_publications_user FOREIGN KEY(id_user) REFERENCES users(id)
)ENGINE=InnoDb;

CREATE TABLE messages(
id				int(255) auto_increment NOT NULL,
emmiter			int(255) NOT NULL,
reciver			int(255) NOT NULL,
text			varchar(255) NOT NULL,
created_at		datetime NOT NULL,
CONSTRAINT pk_messages PRIMARY KEY(id),
CONSTRAINT fk_messages_emmiter FOREIGN KEY(emmiter) REFERENCES users(id),
CONSTRAINT fk_messages_reciver FOREIGN KEY(reciver) REFERENCES users(id)
)ENGINE=InnoDb;

CREATE TABLE likes(
id              	int(255) auto_increment not null,
id_publication		int(255) NOT NULL,
id_user_emmiter		int(255) NOT NULL,
id_user_reciver		int(255) NOT NULL,
CONSTRAINT pk_likes PRIMARY KEY(id),
CONSTRAINT fk_likes_message FOREIGN KEY(id_publication) REFERENCES publications(id),
CONSTRAINT fk_likes_userEmmiter FOREIGN KEY(id_user_emmiter) REFERENCES users(id),
CONSTRAINT fk_likes_userReciver FOREIGN KEY(id_user_reciver) REFERENCES users(id)
)ENGINE=InnoDb;

CREATE TABLE notifications(
id 				int(255) auto_increment NOT NULL,
id_user_emmit	int(255) NOT NULL,
id_user_recep	int(255) NOT NULL,
description 	varchar(255) NOT NULL,
CONSTRAINT pk_notifications PRIMARY KEY(id),
CONSTRAINT fk_likes_emmit FOREIGN KEY(id_user_emmit) REFERENCES users(id),
CONSTRAINT fk_likes_recep FOREIGN KEY(id_user_recep) REFERENCES users(id)
)ENGINE=InnoDb;