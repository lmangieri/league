package baseArtifact.spring;

import static org.junit.Assert.assertEquals;
import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.model.Player;
import leandroportfolio.league.resources.dto.CreatePlayerDto;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
@DbUnitConfiguration(databaseConnection="hsqlMemoryDb")
public class PlayerResourceTest {


    @Autowired
    private WebApplicationContext context;	

	private MockMvc mockMvc;
	
	@Autowired PlayerRepository playerRepository;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	//@Test
	@DatabaseSetup("/sampleData.xml")
	public void createPlayerTestBasicFlow() throws Exception {
		CreatePlayerDto bean = new CreatePlayerDto();
		bean.setEmail("testEmail@mail.com");
		bean.setName("testName");
		bean.setNick("testNick");
		ObjectMapper objMapper = new ObjectMapper();
		MvcResult mvcResult = mockMvc.
				perform(post("/player")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(bean)
				))
				.andExpect(status().isOk())
				.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		Player p1 = objMapper.readValue(response.getContentAsString(),Player.class);
		
		Player p2 = playerRepository.getPlayer("testEmail@mail.com");

		assertEquals(p1,p2);
	}
	
	//@Test
	@DatabaseSetup("/sampleData.xml")
	public void createTwoPlayersWithSameEmail() throws Exception {
		CreatePlayerDto bean = new CreatePlayerDto();
		bean.setEmail("testEmail@mail.com");
		bean.setName("testName");
		bean.setNick("testNick");
		ObjectMapper objMapper = new ObjectMapper();
		MvcResult mvcResult = mockMvc.
				perform(post("/player")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(bean)
				))
				.andExpect(status().isOk())
				.andReturn();
		/*
	    mvcResult = mockMvc.
				perform(post("/player")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objMapper.writeValueAsString(bean)
				))
				.andExpect(status().isBadRequest())
                .andReturn();
        */
		
	}
	
	@Test
	public void test0(){
		System.out.println("z");
	}
		
}