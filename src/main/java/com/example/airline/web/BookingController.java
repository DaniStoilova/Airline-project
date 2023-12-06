package com.example.airline.web;

import com.example.airline.model.binding.BookingBindingModel;
import com.example.airline.model.dto.AllBookings;
import com.example.airline.model.entity.Flight;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.service.BookingService;
import com.example.airline.service.FlightService;
import com.example.airline.service.Impl.EmailServiceImpl;
import com.example.airline.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {


    private BookingService bookingService;

    private UserService userService;

    private FlightService flightService;

    private EmailServiceImpl emailService;



    public BookingController(BookingService bookingService, UserService userService, FlightService flightService, EmailServiceImpl emailService) {
        this.bookingService = bookingService;
        this.userService = userService;

        this.flightService = flightService;
        this.emailService = emailService;
    }

    @GetMapping("/buy")
    public String buy() {

        return "booking";
    }

    @PostMapping("/buy")
    public String buyItem(@Valid BookingBindingModel bookingBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookingBindingModel", bookingBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookingBindingModel", bindingResult);

            return "redirect:/buy";
        }


        bookingService.addBooking(bookingBindingModel);

        emailService.sendBooking(bookingBindingModel.getEmail(),bookingBindingModel.getFullName(),
                bookingBindingModel.getPassportNumber(),bookingBindingModel.getFlight().getFlightNumber());



        return "successfull-flight";


    }

    @GetMapping("/allBooking")
    public String allBook(Model model) {

       List<AllBookings> allBook = bookingService.getAllBookings();

        model.addAttribute("allBook", allBook);


        return "booking-orders";
    }


    @GetMapping("/cancelBooking/{id}")
    public String buyHotel(@PathVariable("id") Long id){

        bookingService.cancell(id);

        return "redirect:/";

    }

    @ModelAttribute
    public BookingBindingModel bookingBindingModel(){
        return new BookingBindingModel();
    }

}
