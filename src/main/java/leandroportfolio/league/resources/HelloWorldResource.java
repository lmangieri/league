package leandroportfolio.league.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import leandroportfolio.league.dao.DummyClass;
import leandroportfolio.league.dao.PlayerDAO;
import leandroportfolio.league.model.Player;

@Path("/hello")
public class HelloWorldResource {

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
        System.out.println( "Hello World!" );
        PlayerDAO dao = new PlayerDAO();
        for(Player p : dao.getAllPlayers()) {
        	System.out.println("Player name = "+ p.getName());
        }

		String output = "Jersey sayyy : " + msg;

		return Response.status(200).entity(output).build();

	}
	

	@GET
	@Path("/test123/{param}")
	public Response getMsg2(@PathParam("param") String msg) {
        System.out.println( "Hello World 2!" );
        
		Player player = new Player();
		player.setName(msg);
		player.setEmail(msg+"@mail.com");
		DummyClass dummy = new DummyClass();
		dummy.createPlayer(player);
		System.out.println("player criado");
		return Response.status(200).entity("player criado").build();

	}
	
	
}
