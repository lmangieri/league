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
	
	@Column(name="LEAGUETYPE")
	private int leagueTypeId;
	
	@Column(name="CREATEDDATE")
	private long createdDate;

	@Column(name="ENDDATE")
	private long endDate;
	
	@Column(name="ISCLOSED")
	private boolean isClosed;
	
	public static class LeagueBuilder {
		private int leagueTypeId;
		
		public LeagueBuilder leagueTypeId(final int leagueTypeId) {
			this.leagueTypeId = leagueTypeId;
			return this;
		}
		
		public League build() {
			return new League(this);
		}
	}

	public long getLeagueid() {
		return leagueid;
	}

	public int getLeagueTypeId() {
		return leagueTypeId;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	
}
