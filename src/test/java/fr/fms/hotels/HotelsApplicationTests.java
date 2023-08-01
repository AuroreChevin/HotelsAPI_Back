package fr.fms.hotels;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HotelsApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	@Test
	void testGetHotelAndTestName() throws Exception{
		this.mockMvc.perform(get("/hotels"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("[0].hotelName").value("Au Château Des Termes"));
	}
	@Test
	void testGetCityAndTestName() throws Exception{
		this.mockMvc.perform(get("/cities"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("[0].cityName").value("Lille"));
	}
}
