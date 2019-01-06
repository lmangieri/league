package leandroportfolio.league.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import leandroportfolio.league.model.League;
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
		
		List<PlayerScoreInfo> playerScoreInfoList = em.createQuery("select new PlayerScoreInfo(P.NAME,P.NICK,P.EMAIL,P.CREATEDDATE, "
				+ "consolidatedround.victoryTotal, consolidatedround.totalGames)  from PLAYER P left join (select roundcon.nick nick, sum(roundcon.victoryTotal) as victoryTotal, sum(roundcon.totalGames) totalGames from "
				+"(select nick1 as nick, sum(score1) as victoryTotal, (sum(score1) + sum(score2)) as totalGames from ROUND group by nick1 "
				+" union all "
				+"select nick2 as nick, sum(score2) as victoryTotal, (sum(score1) + sum(score2)) as totalGames from ROUND group by nick2) roundcon group by nick) consolidatedround on P.NICK = consolidatedround.NICK",PlayerScoreInfo.class)
				.getResultList();
		
		return playerScoreInfoList;
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
}
