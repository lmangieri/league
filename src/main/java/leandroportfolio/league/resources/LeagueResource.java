package leandroportfolio.league.resources;

import java.util.List;

import leandroportfolio.league.model.LeagueType;
import leandroportfolio.league.resources.dto.CreateLeagueDTO;
import leandroportfolio.league.resources.dto.LeagueTypeRepresentation;
import leandroportfolio.league.service.LeagueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "", method = RequestMethod.POST , consumes="application/json")
	public boolean createLeague(@RequestBody CreateLeagueDTO bean) {
		leagueService.createLeague(bean);
	// TODO: actually, here will return a complicated one.
		return true;
	}
}
