package leandroportfolio.league.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROUND")
public class Round {

	public Round() {
	}
	
	private Round(RoundBuilder roundBuilder) {
		this.leagueid = roundBuilder.leagueid;
		this.nick1 = roundBuilder.nick1;
		this.nick2 = roundBuilder.nick2;
		this.score1 = 0;
		this.score2 = 0;
		this.order = roundBuilder.order;
	}
	
	@Id
	@GeneratedValue
	private long roundid;
	
	@Column(name="LEAGUEID")
	private long leagueid;
	
	@Column(name="NICK1")
	private String nick1;
	
	@Column(name="SCORE1")
	private int score1;
	
	@Column(name="NICK2")
	private String nick2;
	
	@Column(name="SCORE2")
	private int score2;
	
	@Column(name="ORDERR")
	private int order;
	
	public String getNick1() {
		return this.nick1;
	}
	
	public int getScore1() {
		return this.score1;
	}
	
	public String getNick2() {
		return this.nick2;
	}
	
	public int getScore2() {
		return this.score2;
	}	
	
	public int getOrder() {
		return this.order;
	}	
	
	public static class RoundBuilder {
		private long leagueid;
		private String nick1;
		private String nick2;
		private int order;
		
		public Round build(){
			return new Round(this);
		}
		
		public RoundBuilder leagueid(final long leagueid) {
			this.leagueid = leagueid;
			return this;
		}
		
		public RoundBuilder nick1(final String nick1) {
			this.nick1 = nick1;
			return this;
		}
		
		public RoundBuilder nick2(final String nick2) {
			this.nick2 = nick2;
			return this;
		}
		
		public RoundBuilder order(final int order) {
			this.order =order;
			return this;
		}
	}
	
}
