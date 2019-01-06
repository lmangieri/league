package leandroportfolio.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import leandroportfolio.league.dao.LeagueRepository;
import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.handler.exceptions.ConstantsMessageError;
import leandroportfolio.league.handler.exceptions.LeagueCreationException;
import leandroportfolio.league.leaguetypes.CommonLeague;
import leandroportfolio.league.leaguetypes.EquallyDistributedLeague;
import leandroportfolio.league.leaguetypes.KeepDoubleGameLeague;
import leandroportfolio.league.leaguetypes.RandomLeague;
import leandroportfolio.league.leaguetypes.SomeonePlaysAllLeague;
import leandroportfolio.league.model.League;
import leandroportfolio.league.model.LeagueType;
import leandroportfolio.league.model.Round;
import leandroportfolio.league.resources.dto.CreateLeagueDTO;
import leandroportfolio.league.resources.dto.LeagueRepresentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {
	
	@Autowired 
	PlayerService playerService;
	
	@Autowired
	LeagueRepository leagueRepository;

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

	//TODO: TUDO o que está aqui.... precisa estar em uma transaction....
	public LeagueRepresentation createLeague(CreateLeagueDTO bean) {
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
		
		League league = new League.LeagueBuilder().leagueTypeId(bean.leagueTypeId).build();
		
		leagueRepository.createLeague(league);
		/*create league here on db....*/
		
		List<Round> roundList = new ArrayList<Round>();
		CommonLeague leagueaux;
		
		if(0 == bean.leagueTypeId){
			leagueaux = new RandomLeague(bean.nicks, league.getLeagueid());
		} else if(1 == bean.leagueTypeId){
			leagueaux = new KeepDoubleGameLeague(bean.nicks, league.getLeagueid());
		} else if(2 == bean.leagueTypeId){
			leagueaux = new EquallyDistributedLeague(bean.nicks, league.getLeagueid());
		} else if(3 == bean.leagueTypeId){
			leagueaux = new RandomLeague(bean.nicks, league.getLeagueid());
		} else if(4 == bean.leagueTypeId){
			leagueaux = new SomeonePlaysAllLeague(bean.nicks, league.getLeagueid());
		 }else {
			throw new LeagueCreationException(ConstantsMessageError.INVALID_LEAGUE_TYPE + " => "+bean.leagueTypeId);
		}
		
		roundList.addAll(leagueaux.createRoundList());
		for(Round round : roundList) {
			leagueRepository.createRound(round);
		}
		
		LeagueRepresentation leagueRepresentation = new LeagueRepresentation(league.getLeagueid(),roundList);
		return leagueRepresentation;
	}
}
