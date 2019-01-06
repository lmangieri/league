package leandroportfolio.league.leaguetypes;

import java.util.List;

import leandroportfolio.league.model.Round;
import leandroportfolio.league.utils.Dupla;

public class EquallyDistributedLeague extends CommonLeague {

	public EquallyDistributedLeague(List<String> nicks, long leagueid) {
		super(nicks, leagueid);
	}

	@Override
	public List<Round> createRoundList() {
		loadUsedNickTimes();
		loadDuplasList();

		int order = 0;

		Dupla dupla = duplasList.remove(0);
		Round round = new Round.RoundBuilder().leagueid(this.leagueid)
				.nick1(dupla.nick1).nick2(dupla.nick2).order(order).build();

		this.roundList.add(round);

		usedNickTimes.replace(dupla.nick1, usedNickTimes.get(dupla.nick1) + 1);
		usedNickTimes.replace(dupla.nick2, usedNickTimes.get(dupla.nick2) + 1);
		order = order + 1;

		while (duplasList.size() > 0) {
			dupla = identifyNextDuplaWithLessGames();

			round = new Round.RoundBuilder().leagueid(this.leagueid)
					.nick1(dupla.nick1).nick2(dupla.nick2).order(order).build();
			this.roundList.add(round);
			usedNickTimes.replace(dupla.nick1,
					usedNickTimes.get(dupla.nick1) + 1);
			usedNickTimes.replace(dupla.nick2,
					usedNickTimes.get(dupla.nick2) + 1);
			order = order + 1;

			this.duplasList.remove(dupla);
		}

		return roundList;
	}

	private Dupla identifyNextDuplaWithLessGames() {
		Dupla duplaWithLessGames = this.duplasList.get(0);
		int total = this.usedNickTimes.get(duplaWithLessGames.nick1)
				+ this.usedNickTimes.get(duplaWithLessGames.nick2);

		int aux;
		
		for (Dupla dupla : this.duplasList) {
			aux = getTotalOfGamesFromDupla(dupla);
			if(total > aux) {
				duplaWithLessGames = dupla;
				total = aux;
			}
		}
		
		return duplaWithLessGames;
	}
	
	private int getTotalOfGamesFromDupla(Dupla dupla) {
		int total = this.usedNickTimes.get(dupla.nick1)
				+ this.usedNickTimes.get(dupla.nick2);
		return total;
	}
}
