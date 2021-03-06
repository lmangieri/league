package leandroportfolio.league.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import leandroportfolio.league.model.Player;

@Service
public class PlayerRepository {

	@PersistenceContext
	private EntityManager em;
    
	@Transactional(propagation = Propagation.REQUIRED)
    public Player createPlayer(Player player) {
    	em.persist(player);
    	return player;
    }
	
	public Player getPlayerByNick(String nick) {
		List<Player> players = em.createQuery("select p from Player p where p.nick = :nick",Player.class)
				.setParameter("nick",nick)
				.getResultList();
			
			if(players.isEmpty()) {
				return null;
			}
			
			return players.get(0);
	}
	
	public Player getPlayerByNickAndEmail(final String email, final String nick) {
		List<Player> players = em.createQuery("select p from Player p where p.email = :email or p.nick = :nick",Player.class)
			.setParameter("email",email)
			.setParameter("nick",nick)
			.getResultList();
		
		if(players.isEmpty()) {
			return null;
		}
		
		return players.get(0);
	}
	
	public Player getPlayer(final String email) {
		List<Player> players = em.createQuery("select p from Player p where p.email = :email",Player.class)
			.setParameter("email",email)
			.getResultList();
		
		if(players.isEmpty()) {
			return null;
		}
		
		return players.get(0);
	}
}
