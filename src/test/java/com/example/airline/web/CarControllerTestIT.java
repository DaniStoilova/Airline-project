package com.example.airline.web;

import com.example.airline.model.dto.CarDto;
import com.example.airline.model.entity.RentACar;
import com.example.airline.model.enums.CountryEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    private RentACar testRent;

    private CarDto carDto;



    @BeforeEach
    void setUp(){
        ModelMapper modelMapper = new ModelMapper();
        testRent = new RentACar();
        testRent.setId(2L);
        testRent.setModel("Fiat");
        testRent.setSeats("5");
        testRent.setBag("2");
        testRent.setDoors("5");
        testRent.setFuelType("Petrol");
        testRent.setPickUpAndDropLocation(CountryEnum.Berlin);
        testRent.setPrice(BigDecimal.valueOf(35));
        carDto = modelMapper.map(testRent,CarDto.class);

    }

    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/car"))
                .andExpect(status().isOk())
                .andExpect(view().name("rent-car"));
    }
    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testAddCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/car")
                        .param("model","Fiat")
                        .param("seats", "5")
                        .param("bag","2")
                        .param("doors","5")
                        .param("fuelType","Petrol")
                        .param("price","55")
                        .param("pickUpAndDropLocation","Sofia")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }
    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testAddCarWithWrongData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/car")
                        .param("model","5")
                        .param("seats", "5")
                        .param("bag","2")
                        .param("doors","5")
                        .param("fuelType","Petrol")
                        .param("price","55")
                        .param("pickUpAndDropLocation","Sofia")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/car"));
    }
//    @Test
//    void testGetRentAll() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/rentAll"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("cars"))
//                .andExpect(model().attributeExists("cars"));
//    }

    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testDeleteCarByAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteCar/{id}",testRent.getId())
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/rentAll"));
    }

    @Test
    @WithMockUser(username = "pesho@abv.bg",
            roles = {"USER"})
    void testBookCarByUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bookCar/3")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }
    @Test
    @WithMockUser(username = "pesho@abv.bg",
            roles = {"USER"})
    void testDeleteCarByUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/bookCar/3")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/rentAll"));
    }




}
