package com.example.airline.web;

import com.example.airline.model.dto.HotelDto;
import com.example.airline.model.entity.Hotel;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.model.enums.CountryEnum;
import com.example.airline.model.enums.PropertyType;
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
public class HotelControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    private UserEntity testUser, testAdmin;

    private Hotel testHotel, testAdminOffer;

    private HotelDto hotelDto;



    @BeforeEach
    void setUp(){
        ModelMapper modelMapper = new ModelMapper();
        testHotel = new Hotel();
        testHotel.setId(2L);
        testHotel.setLocation(CountryEnum.Alexandria);
        testHotel.setPropertyType(PropertyType.Apartment);
        testHotel.setRoom(1);
        testHotel.setPrice(BigDecimal.valueOf(55));
        hotelDto = modelMapper.map(testHotel,HotelDto.class);

    }


    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testHotel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-hotel"));
    }
    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testAddHotel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hotel")
                        .param("location","London")
                        .param("propertyType", "Apartment")
                        .param("price","55")
                        .param("room","1")
                        .with(csrf())
                    ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }
    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testAddHotelWithWrongData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hotel")
                        .param("location","London")
                        .param("propertyType", "Apart")
                        .param("price","55")
                        .param("room","1")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/hotel"));
    }
        @Test
        void testGetAllReservation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/allReservation"))
            .andExpect(status().isOk())
            .andExpect(view().name("hotel-reservation"))
            .andExpect(model().attributeExists("hotels"));
        }

    @Test
    @WithMockUser(username = "pesho@abv.bg",
            roles = {"USER"})
    void testDeleteByUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/buyHotel/1")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(username = "admin@abv.bg",
            roles = {"ADMIN"})
    void testDeleteByAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteHotel/{id}",testHotel.getId())
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/hotel"));
    }

}
