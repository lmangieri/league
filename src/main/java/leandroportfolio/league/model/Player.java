package leandroportfolio.league.model;

import javax.persistence.*;

@Entity
@Table(name="PLAYER")
public class Player {
	
	public Player(long id,String name,String nick,String email) {
		this.id = id;
		this.name = name;
		this.nick = nick;
		this.email = email;
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
	
	@Column(name="EMAIL")
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
	
	
}
