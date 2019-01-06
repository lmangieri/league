package leandroportfolio.league.utils;

public class Dupla {
	public Dupla(String nick1, String nick2) {
		this.nick1 = nick1;
		this.nick2 = nick2;
	}
	public String nick1;
	public String nick2;
	
	@Override
	public boolean equals(Object that) {
		Dupla thatDupla = (Dupla)that;
		
		if(this.nick1.equals(thatDupla.nick1) && this.nick2.equals(thatDupla.nick2)) {
			return true;
		}
		return false;
	}
}