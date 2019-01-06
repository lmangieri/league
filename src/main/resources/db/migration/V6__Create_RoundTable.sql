CREATE TABLE IF NOT EXISTS ROUND (
	roundid int(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	leagueid int(6) UNSIGNED NOT NULL,
	nick1 varchar(60) NOT NULL,
	score1 int(6) UNSIGNED,
	nick2 varchar(60) NOT NULL,
	score2 int(6) UNSIGNED,
	orderr int(6) UNSIGNED NOT NULL,
	FOREIGN KEY (leagueid) REFERENCES LEAGUE(leagueid),
	FOREIGN KEY (nick1) REFERENCES PLAYER(NICK),
	FOREIGN KEY (nick2) REFERENCES PLAYER(NICK)
);

commit;