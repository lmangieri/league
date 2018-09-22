package leandroportfolio.league.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import leandroportfolio.league.model.Player;

@Service
public class PlayerRepository {

	@PersistenceContext
	private EntityManager em;
    
    public Player createPlayer(Player player) {
    	if(em == null) {
    		System.out.println("EM Is null ...!!!!!!!!!!!!!!!!!");
    	} else {
    		System.out.println("EM is not null...");
    	}
    	
    	em.getTransaction().begin();
    	em.persist(player);
    	em.getTransaction().commit();
    	return player;
    }
}
