create table IF NOT EXISTS PLAYER (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(60) NOT NULL,
	NICK varchar(60) NOT NULL,
	EMAIL varchar(60) NOT NULL);

commit;
