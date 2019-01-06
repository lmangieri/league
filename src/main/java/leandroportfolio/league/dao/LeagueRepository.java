package leandroportfolio.league.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import leandroportfolio.league.model.League;
import leandroportfolio.league.model.Round;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeagueRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public League createLeague(League league) {
		em.persist(league);
    	return league;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Round createRound(Round round) {
		em.persist(round);
		return round;
	}
}
