package leandroportfolio.league.resources.dto;

import java.io.Serializable;
import java.util.List;

public class CreateLeagueDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CreateLeagueDTO() {
	}
	
	public List<String> nicks;
	public Long leagueTypeId;
}
