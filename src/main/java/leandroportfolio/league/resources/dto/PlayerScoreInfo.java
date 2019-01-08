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

	public PlayerScoreInfo(String name,String nick,String email,String createdDate,String victoryTotal,String totalGames ) {
		this.name = name;
		this.nick = nick;
		this.email = email;
	
		
		this.createdDate = Utils.getFormatedDate(Long.parseLong(createdDate));
		this.totalGames = Integer.parseInt(totalGames);
		this.totalVictory = Integer.parseInt(victoryTotal);
		this.totalLost = this.totalGames - this.totalVictory;
		
		this.winrate = this.generateWinrate(this.totalVictory, this.totalLost);
	}
	
	private String generateWinrate(int totalVictory,int totalGames) {
		double d = 0;
		if(totalGames > 0) {
			d = totalVictory/totalGames;
		}

		return Double.toString(d)+"%";
	}
}
