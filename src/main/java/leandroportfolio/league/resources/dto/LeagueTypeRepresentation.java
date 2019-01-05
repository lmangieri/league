package leandroportfolio.league.resources.dto;

import java.util.List;

import leandroportfolio.league.model.LeagueType;

public class LeagueTypeRepresentation {
	public List<LeagueType> list;
	
	public LeagueTypeRepresentation(List<LeagueType> list) {
		this.list = list;
	}
}
