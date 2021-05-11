package ru.laptev.carshstat.integration.controller;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.laptev.carshstat.util.ResourceLoader;

import javax.validation.constraints.AssertTrue;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class RideControllerTest {

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
    public void testGetAllRides() throws Exception {
        mockMvc.perform(get("/rides/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(6)));
    }

    @Test
    public void testGetRideByID() throws Exception {
        mockMvc.perform(get("/rides/show/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("car.plateNumber", is("E123KO178")))
                .andExpect(jsonPath("car.model", is("Nissan")))
                .andExpect(jsonPath("rideDate", is("1992-03-26T06:15:15")))
                .andExpect(jsonPath("rideCost", is(32.40)))
                .andExpect(jsonPath("company", is("YANDEX_DRIVE")));
    }

    @Test
    public void testCreateRide() throws Exception {
        mockMvc.perform(post("/rides/add")
                        .contentType(MediaType.APPLICATION_JSON).content(resourceLoader.getResourceAsString("json/testCreateRide.json")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("car.plateNumber", is("T123EC78")))
                .andExpect(jsonPath("car.model", is("Lada")))
                .andExpect(jsonPath("rideDate", is("2012-01-01T15:15:05")))
                .andExpect(jsonPath("rideCost", is(222.11)))
                .andExpect(jsonPath("company", is("DELIMOBIL")));
    }

    @Test
    public void testUpdateRide() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/rides/update/5")
                .contentType(MediaType.APPLICATION_JSON).content(resourceLoader.getResourceAsString("json/testUpdateRide.json")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("car.plateNumber", is("H999HH05")))
                .andExpect(jsonPath("car.model", is("Lada")))
                .andExpect(jsonPath("rideDate", is("2000-01-01T15:15:05")))
                .andExpect(jsonPath("rideCost", is(923.10)))
                .andExpect(jsonPath("company", is("DELIMOBIL"))).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(contentAsString);
        long rideId = jsonObject.getLong("rideId");
        Assertions.assertEquals(rideId, 5L);
    }

    @Test
    public void testDeleteRide() throws Exception {
        mockMvc.perform(delete("/rides/delete/7"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rides/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(5)));
    }






}
