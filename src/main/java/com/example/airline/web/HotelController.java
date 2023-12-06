package com.example.airline.web;

import com.example.airline.error.customException.FlightNotFoundException;
import com.example.airline.error.customException.HotelNotFoundException;
import com.example.airline.model.binding.AddHotelReservation;
import com.example.airline.model.dto.AllHotelDto;
import com.example.airline.model.dto.HotelDto;
import com.example.airline.model.dto.UpdateHotelDto;
import com.example.airline.model.entity.Hotel;
import com.example.airline.service.HotelService;
import com.example.airline.service.Impl.EmailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HotelController {

    private final HotelService hotelService;

    private EmailServiceImpl emailService;



    public HotelController(HotelService hotelService, EmailServiceImpl emailService) {
        this.hotelService = hotelService;
        this.emailService = emailService;
    }

    @GetMapping("/hotel")
    public String addHotel(){
        return "add-hotel";
    }

    @PostMapping("/hotel")
    public String confirmHotel(@Valid AddHotelReservation addHotelReservation,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal User user) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addHotelReservation", addHotelReservation)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addHotelReservation", bindingResult);

            return "redirect:/hotel";
        }


        hotelService.addHotelReservation(addHotelReservation, user);

        return "redirect:/";
    }
    @GetMapping("/updateHotel/{id}")
    public String editHotel(@PathVariable("id") Long id, Model model) {

        UpdateHotelDto updateHotelDto = hotelService.getUpdateHotel(id).orElseThrow(() -> new HotelNotFoundException(id));

        model.addAttribute("updateHotel", updateHotelDto);

        return "hotel-edit";
    }
    @PostMapping("/updateHotel/{id}")
    public String updateHotel(@PathVariable(value = "id") Long id,
                         @Valid UpdateHotelDto updateHotelDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        Hotel hotel = this.hotelService.findById(id).orElseThrow(() -> new  HotelNotFoundException(id));

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateHotelDto", updateHotelDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateHotelDto", bindingResult);

            return "redirect:/updateHotel/" + id;
        }

        this.hotelService.updateHotel(hotel, updateHotelDto);

        return "redirect:/allReservation";
    }


    @GetMapping("/allReservation")
    public String allHotel(Model model){

        List<AllHotelDto> allHotel = hotelService.getAllHotel();

        model.addAttribute("hotels", allHotel);


        return "hotel-reservation";
    }

    @GetMapping("/bookHotel/{id}")
    public String bookHotel(@PathVariable("id") Long id,Model model,@AuthenticationPrincipal User user){

        HotelDto hotelDto = hotelService.getHotelById(id,user).orElseThrow(() -> new FlightNotFoundException(id));

        model.addAttribute("hotelDto",hotelDto);


        return "hotel-orders";
    }

    @GetMapping("/buyHotel/{id}")
    public String buyHotel(@PathVariable("id") Long id, @AuthenticationPrincipal User user){

        hotelService.buyHotel(id,user);

        emailService.sendConformation(id,user.getUsername());

        return "redirect:/";

    }
    @PostMapping("/deleteHotel/{id}")
    public String delete(@PathVariable("id") Long id){

        hotelService.deleteHotel(id);

        return "redirect:/hotel";
    }


    @ModelAttribute
    public AddHotelReservation addHotelReservation(){
        return new AddHotelReservation();
    }

}
