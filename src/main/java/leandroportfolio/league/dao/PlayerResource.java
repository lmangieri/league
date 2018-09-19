package leandroportfolio.league.dao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import leandroportfolio.league.resources.dto.CreatePlayerDto;

@Path("/players")
public class PlayerResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CreatePlayerDto createPlayer(CreatePlayerDto createPlayerBean) {

		return createPlayerBean;
	}
}
