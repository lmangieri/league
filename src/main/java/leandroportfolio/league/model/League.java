package leandroportfolio.league.model;

import javax.persistence.*;

@Entity
@Table(name="LEAGUE")
public class League {

	public League() {
	}
	
	private League(LeagueBuilder builder) {
		this.leagueTypeId = builder.leagueTypeId;
		this.isClosed = false;
		this.endDate = 0L;
		this.createdDate = System.currentTimeMillis();
	}
	
	@Id
	@GeneratedValue
	private long leagueid;
	
	@Column(name="LEAGUETYPEID")
	private long leagueTypeId;
	
	@Column(name="CREATEDDATE")
	private long createdDate;

	@Column(name="ENDDATE")
	private long endDate;
	
	@Column(name="ISCLOSED")
	private boolean isClosed;
	
	public static class LeagueBuilder {
		private long leagueTypeId;
		
		public LeagueBuilder leagueTypeId(final Long leagueTypeId) {
			this.leagueTypeId = leagueTypeId;
			return this;
		}
		
		public League Build() {
			return new League(this);
		}
	}
}
