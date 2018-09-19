package leandroportfolio.league;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import leandroportfolio.league.dao.DummyClass;
import leandroportfolio.league.model.Player;
import leandroportfolio.league.resources.PlayerResource;
import leandroportfolio.league.resources.dto.CreatePlayerDto;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class PlayerResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(PlayerResource.class);
	}

	//@Test
	public void testCreatePlayer() {
		CreatePlayerDto createPlayeDto = new CreatePlayerDto();
		createPlayeDto.setEmail("email");
		createPlayeDto.setName("name");
		createPlayeDto.setNick("nick");
		Response output = target("/players").
				request().
				post(Entity.entity(createPlayeDto, MediaType.APPLICATION_JSON));
		
		assertEquals("should return status 200", 200, output.getStatus());
	}
	
	/*
	@Test
	public void testJPA() {
		Player player = new Player();
		player.setName("test654");
		player.setEmail("email");
		DummyClass dummy = new DummyClass();
		dummy.createPlayer(player);
		System.out.println("player criado");
	}*/
}
