package leandroportfolio.league.resources.dto;

import leandroportfolio.league.utils.Utils;

public class PlayerScoreInfo {
	public String name;
	public String nick;
	public String email;
	
	public String createdDate;
	
	public int totalGames;
	public int totalVictory;
	public int totalLost;
	
	public String winrate;

	PlayerScoreInfo(String name,String nick,String email,long createdDate,int victoryTotal,int totalGames ) {
		this.name = name;
		this.nick = nick;
		this.email = email;
		
		this.createdDate = Utils.getFormatedDate(createdDate);
		this.totalGames = totalGames;
		this.totalLost = totalGames - victoryTotal;
		this.totalVictory = victoryTotal;
	}
}
