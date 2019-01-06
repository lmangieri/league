package leandroportfolio.league.leaguetypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leandroportfolio.league.model.Round;
import leandroportfolio.league.utils.Dupla;

public class KeepDoubleGameLeague extends CommonLeague {
	
	public KeepDoubleGameLeague(List<String> nicks, Long leagueid) {
		super(nicks,leagueid);
	}
	
	public List<Round> createRoundList() {
		loadUsedNickTimes();
		loadDuplasList();

		int order = 0;
		
		Dupla dupla = duplasList.get(0);
		Round round = new Round.RoundBuilder().leagueid(this.leagueid).nick1(dupla.nick1)
											.nick2(dupla.nick2).order(order).build();
		
		this.roundList.add(round);
		
		usedNickTimes.replace(dupla.nick1, usedNickTimes.get(dupla.nick1) + 1);
		usedNickTimes.replace(dupla.nick2, usedNickTimes.get(dupla.nick2) + 1);
		order = order + 1;
		
		this.duplasList.remove(dupla);
		
		// identificar quem da ultima dupla tem o menor numero de jogos
		String nickWithLessGamesFromDupla = getNickWithLessGamesFromDupla(dupla);

		
		while(duplasList.size() > 0) {
			dupla = identifyNextDupla(nickWithLessGamesFromDupla);
			
			round = new Round.RoundBuilder().leagueid(this.leagueid).nick1(dupla.nick1)
					.nick2(dupla.nick2).order(order).build();
			this.roundList.add(round);
			usedNickTimes.replace(dupla.nick1, usedNickTimes.get(dupla.nick1) + 1);
			usedNickTimes.replace(dupla.nick2, usedNickTimes.get(dupla.nick2) + 1);
			order = order + 1;
			nickWithLessGamesFromDupla = getNickWithLessGamesFromDupla(dupla);
			
			
			this.duplasList.remove(dupla);
		}
		
		return roundList;
	}
	
	private Dupla identifyNextDupla(String participant) {
		
		Dupla nextDupla = duplasList.get(0);
		int total = this.usedNickTimes.get(identifySecondOfDupla(nextDupla,participant));
		
		int aux;
		
		for(Dupla dupla : duplasList) {
			if(duplaContainsParticipant(dupla,participant)) {
				aux = this.usedNickTimes.get(identifySecondOfDupla(dupla,participant));
				if(total > aux) {
					total = aux;
					nextDupla = dupla;
				}
			}
		}
		
		return nextDupla;
	}
}
