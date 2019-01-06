package leandroportfolio.league.resources.dto;

import java.util.List;

import leandroportfolio.league.model.Round;

public class LeagueRepresentation {
	public Long leagueid;
	
	public List<Round> listRound;
	
	public LeagueRepresentation(Long leagueid,List<Round> listRound) {
		this.leagueid = leagueid;
		this.listRound = listRound;
	}
}
