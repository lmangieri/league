package leandroportfolio.league.leaguetypes;

import java.util.List;
import java.util.Random;

import leandroportfolio.league.model.Round;
import leandroportfolio.league.utils.Dupla;

public class RandomLeague extends CommonLeague {

	private Random random;
	// random.nextInt(int);
	
	public RandomLeague(List<String> nicks, long leagueid) {
		super(nicks,leagueid);
		this.random = new Random();
	}

	@Override
	public List<Round> createRoundList() {
		loadDuplasList();
		int order = 0;
		
		while(this.duplasList.size() > 0) {
			Dupla dupla = removeRandomDuplaFromList();
			Round round = new Round.RoundBuilder().leagueid(this.leagueid).nick1(dupla.nick1)
					.nick2(dupla.nick2).order(order).build();

			this.roundList.add(round);
			order++;
		}
		
		return this.roundList;
	}
	
	private Dupla removeRandomDuplaFromList() {
		return this.duplasList.remove(random.nextInt(this.duplasList.size()));
	}

}
