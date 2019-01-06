package leandroportfolio.league.leaguetypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leandroportfolio.league.model.Round;
import leandroportfolio.league.utils.Dupla;

public abstract class CommonLeague {

	protected List<String> nicks;
	
	protected List<Dupla> duplasList;
	
	protected Map<String,Integer> usedNickTimes;
	
	protected Long leagueid;
	
	protected List<Round> roundList;
	
	public CommonLeague(List<String> nicks, long leagueid) {
		this.leagueid = leagueid;
		this.nicks = nicks;
		this.usedNickTimes = new HashMap<String,Integer>();
		this.duplasList = new ArrayList<Dupla>();
		this.roundList = new ArrayList<Round>();
	}
	
	public abstract List<Round> createRoundList();
	
	/*pre condition: check if first is on dupla*/
	protected String identifySecondOfDupla(Dupla dupla, String first) {
		if(first.equals(dupla.nick1)) {
			return dupla.nick2;
		}
		return dupla.nick1;
	}	
	
	protected boolean duplaContainsParticipant(Dupla dupla, String participant) {
		if(participant.equals(dupla.nick1) || participant.equals(dupla.nick2)) {
			return true;
		}
		return false;
	}
	
	protected String getNickWithLessGamesFromDupla(Dupla dupla) {
		String nickWithLessGamesFromDupla = dupla.nick1;
		int totalNick1 = usedNickTimes.get(dupla.nick1);
		
		if(usedNickTimes.get(dupla.nick2) < totalNick1) {
			nickWithLessGamesFromDupla = dupla.nick2;
		}
		return nickWithLessGamesFromDupla;
	}
	
	protected void loadUsedNickTimes() {
		/*load usedNickTimes*/
		for(String nick : nicks) {
			usedNickTimes.put(nick,0);
		}
	}
	
	protected void loadDuplasList() {
		int i = 0;
		int j;
		
		while(i < nicks.size()) {
			j = i + 1;
			while(j < nicks.size()) {
				Dupla dupla = new Dupla(this.nicks.get(i), this.nicks.get(j));
				this.duplasList.add(dupla);
				j++;
			}
			i++;
		}
	}	
}
