package leandroportfolio.league.service;

import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.handler.exceptions.UserCreationException;
import leandroportfolio.league.model.Player;
import leandroportfolio.league.resources.dto.CreatePlayerDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	
	public Player createPlayer(CreatePlayerDto bean) {
		
		if(playerRepository.getPlayerByNickAndEmail(bean.getEmail(),bean.getNick()) != null) {
			System.out.println("Exception user creation - user already exists -"+bean.getEmail());
			throw new UserCreationException("User already exists");
		}
		
		Player player = new Player.PlayerBuilder().name(bean.getName())
											.email(bean.getEmail())
											.nick(bean.getNick())
											.build();
		
		player = playerRepository.createPlayer(player);
		return player;
	}
	
	public boolean isValidNick(final String nick) {
		Player player = playerRepository.getPlayerByNick(nick);
		if(player == null) {
			return false;
		}
		return true;
	}
	
}
