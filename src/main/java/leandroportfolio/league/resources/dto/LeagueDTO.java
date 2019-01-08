package leandroportfolio.league.resources.dto;

import java.io.Serializable;
import java.util.List;

import leandroportfolio.league.model.Round;

public class LeagueDTO implements Serializable{
	public LeagueDTO() {
	}
	
	public Long leagueid;
	
	public List<Round> listRound;
	
	public LeagueDTO(Long leagueid,List<Round> listRound) {
		this.leagueid = leagueid;
		this.listRound = listRound;
	}
	
	public boolean closeLeague;
}
