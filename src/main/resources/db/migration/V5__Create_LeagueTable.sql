create table IF NOT EXISTS LEAGUE (
	leagueid INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	leagueType varchar(60) NOT NULL,
	createdDate long NOT NULL,
	endDate long,
	isClosed boolean);

commit;



