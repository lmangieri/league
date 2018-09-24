package baseArtifact.spring;

import static org.junit.Assert.assertEquals;
import leandroportfolio.league.dao.PlayerRepository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/*
	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
		                  "sa", "", "schema.sql", UTF8, false);
	}	*/
	
	@Test
	public void doTestTwo() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello/test")).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		
		String z = response.getContentAsString();
		
		ObjectMapper mapper = new ObjectMapper();
		BeanExample bean = mapper.readValue(z, BeanExample.class);
		
		assertEquals(bean.getAge(), "123");
		assertEquals(bean.getName(),"name");
		
	}
	
	@Test
	@DatabaseSetup("/sampleData.xml")
	public void doTest3() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello/test123/123123123")).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		
	}
	
}