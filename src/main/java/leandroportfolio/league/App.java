package leandroportfolio.league;

import leandroportfolio.league.dao.PlayerDAO;
import leandroportfolio.league.model.Player;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		PlayerDAO dao = new PlayerDAO();
		for (Player p : dao.getAllPlayers()) {
			System.out.println("Player name = " + p.getName());
		}
	}
}
