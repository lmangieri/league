package leandroportfolio.league.leaguetypes;

import java.util.List;

import leandroportfolio.league.model.Round;
import leandroportfolio.league.utils.Dupla;

public class SomeonePlaysAllLeague extends CommonLeague {

	public SomeonePlaysAllLeague(List<String> nicks, long leagueid) {
		super(nicks, leagueid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Round> createRoundList() {
		loadDuplasList();
		int order = 0;
		
		for(Dupla dupla : this.duplasList) {
			Round round = new Round.RoundBuilder().leagueid(this.leagueid).nick1(dupla.nick1)
					.nick2(dupla.nick2).order(order).build();

			this.roundList.add(round);
			order++;
		}
		
		return this.roundList;
	}

}
