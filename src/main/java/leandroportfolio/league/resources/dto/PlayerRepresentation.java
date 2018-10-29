package leandroportfolio.league.resources.dto;

import leandroportfolio.league.model.Player;

public class PlayerRepresentation {
	public PlayerRepresentation(Player player) {
		this.name = player.getName();
		this.email = player.getEmail();
		this.nick = player.getNick();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	public String name;
	public String email;
	public String nick;
}
