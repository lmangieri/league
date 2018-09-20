package leandroportfolio.league.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import leandroportfolio.league.model.Player;

@Service
public class PlayerService {
    //EntityManagerFactory factory = null;
    //EntityManager manager = null;

    
    	//factory = Persistence.createEntityManagerFactory("leaguebd");
    	//manager = factory.createEntityManager();
    	
    
    public Player createPlayer(Player player) {
    	/*manager.getTransaction().begin();
    	manager.persist(player);
    	manager.getTransaction().commit();*/
    	return player;
    }
}
