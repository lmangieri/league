package leandroportfolio.league.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import leandroportfolio.league.handler.exceptions.ConstantsMessageError;
import leandroportfolio.league.handler.exceptions.LeagueCreationException;
import leandroportfolio.league.model.League;
import leandroportfolio.league.model.Player;
import leandroportfolio.league.model.Round;
import leandroportfolio.league.resources.dto.PlayerScoreInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeagueRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<PlayerScoreInfo> getListPlayerScoreInfo() {
		List<PlayerScoreInfo> listPlayerScoreInfo = new ArrayList<PlayerScoreInfo>();
		
	    List<Object[]> list = em.createNativeQuery("select P.NAME,P.NICK,P.EMAIL,P.CREATEDDATE, consolidatedround.victoryTotal, consolidatedround.totalGames  from PLAYER P left join (select roundcon.nick nick, sum(roundcon.victoryTotal) as victoryTotal, sum(roundcon.totalGames) totalGames from "
				+ "(select nick1 as nick, sum(score1) as victoryTotal, (sum(score1) + sum(score2)) as totalGames from ROUND group by nick1  union all select nick2 as nick, sum(score2) as victoryTotal, (sum(score1) + sum(score2)) as totalGames from ROUND group by nick2) roundcon group by nick) consolidatedround "
				+ "on P.NICK = consolidatedround.NICK").getResultList();
		
		for(Object[] objects : list) {
			String name = (String)objects[0];
			String nick = (String)objects[1];
			String email = (String)objects[2];
			String createddate = (String)objects[3].toString();
			
			String totalVictory;
			if(objects[4] == null) {
				totalVictory = "0";
			} else {
				totalVictory = objects[4].toString();
			}
			String totalGames;
			if(objects[5] == null) {
				totalGames ="0";
			} else {
				totalGames = objects[5].toString();
			}
			PlayerScoreInfo playerScoreInfo = new PlayerScoreInfo(name,nick,email,createddate,totalVictory,totalGames);
			listPlayerScoreInfo.add(playerScoreInfo);
		}
		return listPlayerScoreInfo;
	}
	
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
	
	public League getLeague(Long leagueid) {
		League league = em.createQuery("select l from League l where l.leagueid = :leagueid",League.class)
				.setParameter("leagueid",leagueid)
				.getSingleResult();
		
		if(league == null) {
			throw new LeagueCreationException(ConstantsMessageError.INVALID_LEAGUEID + " => "+leagueid);
		}
		
		return league;
	}

	public List<Round> getRounds(Long leagueid) {
		List<Round> rounds = em.createQuery("select r from Round r where r.leagueid = :leagueid",Round.class)
				.setParameter("leagueid", leagueid)
				.getResultList();
		return rounds;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRound(Round roundOrig) {
		em.merge(roundOrig);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateLeague(League league) {
		em.merge(league);
	}
}
