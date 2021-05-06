package ru.laptev.carshstat.integration.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.laptev.carshstat.repository.RidesRepository;
import ru.laptev.carshstat.util.ResourceLoader;

import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RideControllerTest {

    @Autowired
    private RidesRepository ridesRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateRide() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/rides/add")
                        .contentType(MediaType.APPLICATION_JSON).content(resourceLoader.getResourceAsString("json/testRide.json")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("car.plateNumber", is("T123EC78")))
                .andExpect(jsonPath("car.model", is("Lada")))
                .andExpect(jsonPath("rideDate", is("2012-01-01T15:15:05")))
                .andExpect(jsonPath("rideCost", is(222.11)))
                .andExpect(jsonPath("company", is("DELIMOBIL")))
                .andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        long rideId = jsonObject.getLong("rideId");
        ridesRepository.deleteById(rideId);
    }
}
