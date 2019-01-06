package leandroportfolio.league.resources.dto;

import java.util.List;

public class RankingRepresentation {
	public List<PlayerScoreInfo> list;
	
	public RankingRepresentation(List<PlayerScoreInfo> list ) {
		this.list = list;
	}
}
