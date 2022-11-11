package s22.carRest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

	//Example: this test case fails because in our case response is in JSON format
	@Test
	public void checkResponseTypeNDJSON() throws Exception {
		this.mockMvc.perform(get("/rest/cars")).andExpect(content().contentType(MediaType.APPLICATION_NDJSON));
	}


}
