package com.example.airline.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTestIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testFlight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("airline-add"));
    }

    @Test
    void testAllFlights() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-flights"))
                .andExpect(model().attributeExists("flights"));
    }
    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testAddFlightWithWrongData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .param("flightNumber","3333")
                        .param("tripEnum","One")
                        .param("origin","London")
                        .param("destination","Sofia")
                        .param("depTime","2023-12-07 12:01:00.000000")
                        .param("ageEnum","Adult")
                        .param("classEnum", "First")
                        .param("price","55")

                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("add"));
    }
    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testAddFlight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .param("flightNumber","3333")
                        .param("tripEnum","One")
                        .param("origin","London")
                        .param("destination","Sofia")
                        .param("depTime","2023-12-07T12:01")
                        .param("ageEnum","Adult")
                        .param("classEnum", "First")
                        .param("price","55")

                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }










}
