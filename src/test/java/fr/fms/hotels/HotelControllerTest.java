package fr.fms.hotels;

import fr.fms.hotels.service.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelServiceImpl hotelServiceImpl;
    @Test
    public void testGetHotels()throws Exception{
        mockMvc.perform(get("/hotels")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void testGetCities()throws Exception{
        mockMvc.perform(get("/cities")).andDo(print()).andExpect(status().isOk());
    }

}
