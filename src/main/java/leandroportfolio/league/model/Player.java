package leandroportfolio.league.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="PLAYER")
public class Player {
	private Player(PlayerBuilder builder) {
		this.name = builder.name;
		this.nick = builder.nick;
		this.email = builder.email;
		this.createddate = System.currentTimeMillis();
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="NICK")
	private String nick;
	
	@Column(name="CREATEDDATE")
	private long createddate;
	
	@Column(name="EMAIL",unique=true)
	private String email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getCreateddate() {
		return this.createddate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.nick);
	}
	
	@Override
	public boolean equals(Object that) {
		Player other = (Player)that;
		System.out.println("equals result : "+this.email.equals(other.getEmail())) ;
		boolean eq = (this.email.equals(other.getEmail()) && this.nick.equals(other.getNick()));
		return eq;
	}
	
	public static class PlayerBuilder {
		private String name = null;
		private String email = null;
		private String nick = null;
		
		public PlayerBuilder name(final String name) {
			this.name = name;
			return this;
		}
		
		public PlayerBuilder email(final String email) {
			this.email = email;
			return this;
		}
		
		public PlayerBuilder nick(final String nick) {
			this.nick = nick;
			return this;
		}
		
		public Player build() {
			return new Player(this);
		}
	}
	
}
