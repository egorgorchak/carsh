package ru.laptev.carshstat.integration.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.laptev.carshstat.repository.RidesRepository;

@SpringBootTest
public class RideControllerTest {

    @Autowired
    private RidesRepository ridesRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateRide() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("http://localhost:8888/rides/add")
                        .contentType(MediaType.APPLICATION_JSON).content("{\"car\": {\"plateNumber\": \"T123EC78\",\"model\": \"Lada\"},\"rideDate\": \"2012-01-01T15:15:05\",\"rideCost\": 222.11,\"company\": \"DELIMOBIL\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println();
    }
}
