package leandroportfolio.league.service;

import java.util.ArrayList;
import java.util.List;

import leandroportfolio.league.model.LeagueType;

import org.springframework.stereotype.Service;

@Service
public class LeagueService {

	public List<LeagueType> getLeagueTypes() {
		List<LeagueType> list = new ArrayList<LeagueType>();
		
		LeagueType type1 = new LeagueType("Random",0L);
		list.add(type1);
		
		LeagueType type2 = new LeagueType("Keep Double Game",1L);
		list.add(type2);
		
		LeagueType type3 = new LeagueType("Equally Distributed",2L);
		list.add(type3);
		
		LeagueType type4 = new LeagueType("Someone plays all",3L);
		list.add(type4);
		
	   /*
		* 	LeagueType type5 = new LeagueType("Higher winrate lays first",4L);
		*	list.add(type5);
	    */
		
		return list;
	}
}
