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
    	em.getTransaction().begin();
    	em.persist(player);
    	em.getTransaction().commit();
    	return player;
    }
}
