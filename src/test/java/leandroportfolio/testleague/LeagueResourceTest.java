package leandroportfolio.testleague;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.handler.ApiError;
import leandroportfolio.league.handler.Constants;
import leandroportfolio.league.handler.exceptions.ConstantsMessageError;
import leandroportfolio.league.model.Player;
import leandroportfolio.league.resources.dto.CreateLeagueDTO;
import leandroportfolio.league.resources.dto.CreatePlayerDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import Beans.BeanExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-test.xml")
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DbUnitConfiguration(databaseConnection = "hsqlMemoryDb")
public class LeagueResourceTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	@DatabaseSetup("/dataLeagueTests.xml")
	public void testListOfNicksMustBeUniqueCreateLeague() throws Exception {
		CreateLeagueDTO createLeagueDTO = new CreateLeagueDTO();

		createLeagueDTO.leagueTypeId = 0;
		createLeagueDTO.nicks = new ArrayList<String>();
		createLeagueDTO.nicks.add("coke kills");
		createLeagueDTO.nicks.add("coke kills");
		createLeagueDTO.nicks.add("coke kills2");
		
		ObjectMapper objMapper = new ObjectMapper();
		MvcResult mvcResult = mockMvc
				.perform(
						post("/league").contentType(MediaType.APPLICATION_JSON)
								.content(objMapper.writeValueAsString(createLeagueDTO)))
								.andExpect(status().isBadRequest()).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		ApiError err = objMapper.readValue(response.getContentAsString(),
				ApiError.class);
		
		assertEquals(err.getMessage().contains(ConstantsMessageError.LIST_OF_NICKS_MUST_BE_UNIQUE),true);

	}
	
	@Test
	@DatabaseSetup("/dataLeagueTests.xml")
	public void numberOfNicksMustBeHigherThan0() throws Exception {
		CreateLeagueDTO createLeagueDTO = new CreateLeagueDTO();

		createLeagueDTO.leagueTypeId = 0;
		createLeagueDTO.nicks = new ArrayList<String>();
		
		ObjectMapper objMapper = new ObjectMapper();
		MvcResult mvcResult = mockMvc
				.perform(
						post("/league").contentType(MediaType.APPLICATION_JSON)
								.content(objMapper.writeValueAsString(createLeagueDTO)))
								.andExpect(status().isBadRequest()).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		ApiError err = objMapper.readValue(response.getContentAsString(),
				ApiError.class);
		
		assertEquals(err.getMessage().contains(ConstantsMessageError.INVALID_NUMBER_OF_NICKS),true);
	}
	
	@Test
	@DatabaseSetup("/dataLeagueTests.xml")
	public void invalidNickOnCreateLeague() throws Exception {
		CreateLeagueDTO createLeagueDTO = new CreateLeagueDTO();

		createLeagueDTO.leagueTypeId = 0;
		createLeagueDTO.nicks = new ArrayList<String>();
		createLeagueDTO.nicks.add("InexistentNick1");
		createLeagueDTO.nicks.add("InexistentNick2");
		createLeagueDTO.nicks.add("InexistentNick3");
		
		ObjectMapper objMapper = new ObjectMapper();
		MvcResult mvcResult = mockMvc
				.perform(
						post("/league").contentType(MediaType.APPLICATION_JSON)
								.content(objMapper.writeValueAsString(createLeagueDTO)))
								.andExpect(status().isBadRequest()).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		ApiError err = objMapper.readValue(response.getContentAsString(),
				ApiError.class);
		
		assertEquals(err.getMessage().contains(ConstantsMessageError.INVALID_NICK),true);
	}
	
	@Test
	@DatabaseSetup("/dataLeagueTests.xml")
	public void invalidLeagueType() throws Exception {
		CreateLeagueDTO createLeagueDTO = new CreateLeagueDTO();

		createLeagueDTO.leagueTypeId = 20;
		createLeagueDTO.nicks = new ArrayList<String>();
		createLeagueDTO.nicks.add("alf0");
		createLeagueDTO.nicks.add("alf1");
		createLeagueDTO.nicks.add("alf2");
		createLeagueDTO.nicks.add("alf3");
		createLeagueDTO.nicks.add("alf4");
		
		ObjectMapper objMapper = new ObjectMapper();
		MvcResult mvcResult = mockMvc
				.perform(
						post("/league").contentType(MediaType.APPLICATION_JSON)
								.content(objMapper.writeValueAsString(createLeagueDTO)))
								.andExpect(status().isBadRequest()).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		ApiError err = objMapper.readValue(response.getContentAsString(),
				ApiError.class);
		
		assertEquals(err.getMessage().contains(ConstantsMessageError.INVALID_LEAGUE_TYPE),true);
	}
	
}
