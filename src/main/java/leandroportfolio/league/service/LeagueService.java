package leandroportfolio.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.handler.exceptions.ConstantsMessageError;
import leandroportfolio.league.handler.exceptions.LeagueCreationException;
import leandroportfolio.league.model.LeagueType;
import leandroportfolio.league.resources.dto.CreateLeagueDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {
	
	@Autowired 
	PlayerService playerService;

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

	public void createLeague(CreateLeagueDTO bean) {
		Set<String> set = new TreeSet<String>();
		set.addAll(bean.nicks);
		if(set.size() != bean.nicks.size()) {
			throw new LeagueCreationException(ConstantsMessageError.LIST_OF_NICKS_MUST_BE_UNIQUE);
		}
		
		if(bean.nicks.size() < 1) {
			throw new LeagueCreationException(ConstantsMessageError.INVALID_NUMBER_OF_NICKS);
		}
		
		for(String nick : bean.nicks) {
			if(!playerService.isValidNick(nick)) {
				throw new LeagueCreationException(ConstantsMessageError.INVALID_NICK + " => " +nick);
			}
		}
		
		Long leagueId;
		
		if(0L == bean.leagueTypeId){
			System.out.println("leaguetype is 0L");
			leagueId = createRandomLeague(bean.nicks);
		} else if(1L == bean.leagueTypeId){
			System.out.println("");
			leagueId = createKeepDoubleGameLeague(bean.nicks);
		} else if(2L == bean.leagueTypeId){
			System.out.println("");
			leagueId = createEquallyDistributedLeague(bean.nicks);
		} else if(3L == bean.leagueTypeId){
			System.out.println("");
			leagueId = createSomeonePlaysAllLeague(bean.nicks);
		} else {
			throw new LeagueCreationException(ConstantsMessageError.INVALID_LEAGUE_TYPE + " => "+bean.leagueTypeId);
		}
	}
	
	
	private Long createRandomLeague (List<String> nicks){
		return null;
	}
	
	private Long createKeepDoubleGameLeague (List<String> nicks){
		return null;
	}
	
	private Long createEquallyDistributedLeague (List<String> nicks){
		return null;
	}
	
	private Long createSomeonePlaysAllLeague (List<String> nicks){
		return null;
	}
}
