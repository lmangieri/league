package leandroportfolio.league.resources;

import java.util.List;

import leandroportfolio.league.handler.exceptions.ConstantsMessageError;
import leandroportfolio.league.handler.exceptions.LeagueCreationException;
import leandroportfolio.league.model.LeagueType;
import leandroportfolio.league.resources.dto.CreateLeagueDTO;
import leandroportfolio.league.resources.dto.GetLeagueDTO;
import leandroportfolio.league.resources.dto.LeagueDTO;
import leandroportfolio.league.resources.dto.LeagueRepresentation;
import leandroportfolio.league.resources.dto.LeagueTypeRepresentation;
import leandroportfolio.league.resources.dto.RankingRepresentation;
import leandroportfolio.league.service.LeagueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("league")
public class LeagueResource {

	@Autowired
	LeagueService leagueService;
	
	@ResponseBody
	@RequestMapping(value = "/leagueTypes", method = RequestMethod.GET)
	public LeagueTypeRepresentation getLeagueTypes() {
		List<LeagueType> list = leagueService.getLeagueTypes();
		
		LeagueTypeRepresentation leagueTypeRepresentation = new LeagueTypeRepresentation(list);
		return leagueTypeRepresentation;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public RankingRepresentation getRanking() {
		return leagueService.getRanking();
	}
	
	@ResponseBody
	@RequestMapping(value = "/round", method = RequestMethod.POST , consumes="application/json")
	public boolean updateRound(@RequestBody LeagueDTO bean) {
		return leagueService.updateRound(bean);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST , consumes="application/json")
	public LeagueRepresentation createLeague(@RequestBody CreateLeagueDTO bean) {
		return leagueService.createLeague(bean);
	}
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET )
	public LeagueRepresentation getLeague(@RequestParam(value = "leagueId", required=false) Long leagueId) {
		return leagueService.getLeague(leagueId);
	}
	
}
