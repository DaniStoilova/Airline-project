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
public class BookingControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test@abv.bg",
            roles = {"USER"})
    void testShowBuy() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/buy"))
                .andExpect(status().isOk())
                .andExpect(view().name("booking"));
    }
//    @Test
//    @WithMockUser(username = "ani@abv.bg",
//            roles = {"USER"})
//    void testShowBook() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/buy")
//                        .param("fullName","Test Ivanov")
//                        .param("email", "ani@abv")
//                        .param("passportNumber","1234567")
//                        .param("bookingStatus","booked")
//                        .param("seats","E6")
//                        .param("baggage","small")
//                        .param("flight","1")
//                        .with(csrf())
//                ).
//                andExpect(status().is3xxRedirection()).
//                andExpect(view().name("successfull-flight"));
//    }

    @Test
    @WithMockUser(username = "pepi@abv.bg",
            roles = {"USER"})
    void testAddBookWithWrongData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/buy")
                        .param("fullName","Test Ivanov")
                        .param("email", "ani")
                        .param("passportNumber","12")
                        .param("bookingStatus","booked")
                        .param("seats","E6")
                        .param("baggage","small")
                        .param("flight","1")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/buy"));
    }

    @Test
    void testGetAllReservation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/allBooking"))
                .andExpect(status().isOk())
                .andExpect(view().name("booking-orders"))
                .andExpect(model().attributeExists("allBook"));
    }
    @Test
    @WithMockUser(username = "pesho@abv.bg",
            roles = {"USER"})
    void testDeleteByUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cancelBooking/1")
                        .with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }



}
