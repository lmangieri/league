package leandroportfolio.league.dao;

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
    	if(em == null) {
    		System.out.println("EM Is null ...!!!!!!!!!!!!!!!!!");
    	} else {
    		System.out.println("EM is not null...");
    	}
    	
    	em.persist(player);
    	return player;
    }
}
