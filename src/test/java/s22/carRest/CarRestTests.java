package s22.carRest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class CarRestTests {
	@Autowired
	private WebApplicationContext webAppContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}


	@Test
	public void apiStatusIsOk() throws Exception {
		this.mockMvc.perform(get("/rest/cars")).andExpect(status().isOk());
	}
	
	@Test
	public void checkResponseTypeJSON() throws Exception {
		this.mockMvc.perform(get("/rest/cars")).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void checkResponseTypeHalJson() throws Exception {
		this.mockMvc.perform(get("/api"))
		.andExpect(content().contentType("application/hal+json"));
	}

	//Example: this test case fails because in our case response is in JSON format
	@Test
	public void checkResponseTypeNDJSON() throws Exception {
		this.mockMvc.perform(get("/rest/cars"))
		.andExpect(content().contentType(MediaType.APPLICATION_NDJSON));
	}
	
	@Test
	public void findByModel() throws Exception {
//		this.mockMvc.perform(get("/findByModel/Golf"))
//		.andDo(print())
//		.andExpect(jsonPath("$[0].brand").value("Volkswagen"))
//		.andDo(print());
		MvcResult result = mockMvc.perform(get("/findByModel/Golf"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
		System.out.println("RESULT IS  " + result);		
		String json = result.getResponse().getContentAsString();
		System.out.println("JSON IS " + json);
		//String expected = "{field:'value', anotherField:true}";
		System.out.println("");
		//assertThat(result).
		//assertThat(cars).hasSize(1);(expected, json, true);
	}


}
