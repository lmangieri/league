package leandroportfolio.league.resources.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

public class CreatePlayerDto implements Serializable{
	public CreatePlayerDto() {
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
