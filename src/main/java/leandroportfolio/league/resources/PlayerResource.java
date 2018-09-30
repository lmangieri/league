package leandroportfolio.league.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Beans.BeanExample;
import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.model.Player;
import leandroportfolio.league.resources.dto.CreatePlayerDto;
import leandroportfolio.league.service.PlayerService;

@Controller
@RequestMapping("player")
public class PlayerResource {

	@Autowired
	PlayerService playerService;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST, consumes="application/json")
	public Player createPlayer(@RequestBody CreatePlayerDto bean) {
		System.out.println("Zebra");
		Player player = playerService.createPlayer(bean);
		return player;
	}
	
}
