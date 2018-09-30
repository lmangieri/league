package leandroportfolio.league.service;

import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.model.Player;
import leandroportfolio.league.resources.dto.CreatePlayerDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	
	public Player createPlayer(CreatePlayerDto bean) {
		Player player = new Player.PlayerBuilder().name(bean.getName())
											.email(bean.getEmail())
											.nick(bean.getNick())
											.build();
		
		player = playerRepository.createPlayer(player);
		return player;
	}
	
}
