package leandroportfolio.league.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import leandroportfolio.league.model.Player;

public class DummyClass {
    EntityManagerFactory factory = null;
    EntityManager manager = null;

    
    public DummyClass() {
    	factory = Persistence.createEntityManagerFactory("leaguebd");
    	manager = factory.createEntityManager();
    	
        //manager.close();
        //factory.close();
    }
    
    public Player createPlayer(Player player) {
    	manager.getTransaction().begin();
    	manager.persist(player);
    	manager.getTransaction().commit();
    	return player;
    }
}
