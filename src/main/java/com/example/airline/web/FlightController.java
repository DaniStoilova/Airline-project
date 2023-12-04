package com.example.airline.web;

import com.example.airline.error.customException.FlightNotFoundException;
import com.example.airline.model.binding.AddFlightBindingModel;
import com.example.airline.model.dto.AllFlightDTO;
import com.example.airline.model.dto.TicketDto;
import com.example.airline.model.dto.UpdateFlightDto;
import com.example.airline.model.entity.Flight;
import com.example.airline.model.enums.CountryEnum;
import com.example.airline.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class FlightController {


    private final FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/add")
    public String addFlight() {
        return "airline-add";
    }

    @PostMapping("/add")
    public String addBookFlight(@Valid AddFlightBindingModel addFlightBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal User user) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addFlightBindingModel", addFlightBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addFlightBindingModel", bindingResult);

            return "redirect:add";
        }


        flightService.addBook(addFlightBindingModel,user);

        return "redirect:/";
    }

    @ModelAttribute()
    public AddFlightBindingModel addFlightBindingModel() {
        return new AddFlightBindingModel();
    }


    @GetMapping("/all")
    public String all(Model model) {

        List<AllFlightDTO> allFlights = flightService.getAllFlights();

        model.addAttribute("flights", allFlights);

        return "all-flights";

    }
    @GetMapping("/update/{id}")
    public String editFlight(@PathVariable("id") Long id, Model model) {

        UpdateFlightDto updateFlight = flightService.getUpdateFlight(id).orElseThrow(() -> new FlightNotFoundException(id));

        model.addAttribute("updateFlight", updateFlight);

        return "airline-edit";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id,
                                @Valid UpdateFlightDto updateFlightDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        Flight flight = this.flightService.findById(id).orElseThrow(() -> new FlightNotFoundException(id));

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateFlightDto", updateFlightDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateFlightDto", bindingResult);

            return "redirect:/update/" + id;
        }

        this.flightService.updateFlight(flight, updateFlightDto);

        return "redirect:/all";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){

        flightService.deleteFlight(id);

        return "redirect:/all";
    }

    @GetMapping("/ticket/{id}")
    public String confirm(@PathVariable("id")Long id,@AuthenticationPrincipal User user ,Model model){

        TicketDto ticketDto = flightService.getTicketById(id,user).orElseThrow(() -> new FlightNotFoundException(id));

        model.addAttribute("ticketDto",ticketDto);

        BigDecimal total = this.flightService.getTotal(id);

        model.addAttribute("total",total);


        return "ticket";


    }
//    @PostMapping("/buy/{id}")
//    public String buyItem(@PathVariable("id") Long id, Model model,@AuthenticationPrincipal User user) {
//
//        this.flightService.buyTicket(id,user);
//
//        model.addAttribute("flightId", id);
//
//       return "successfull-flight";
//
//    }

    @PostMapping("/cancell/{id}")
    public String back(@PathVariable("id") Long id, Model model) {

        return "redirect:/all";
    }

    @GetMapping("/checkout/{id}")
    public String buyTicket(@PathVariable("id") Long id,Model model,@AuthenticationPrincipal User user) {

        TicketDto ticketDto = flightService.getTicketById(id,user).orElseThrow(() -> new FlightNotFoundException(id));

        model.addAttribute("ticketDto",ticketDto);

        BigDecimal total = this.flightService.getTotal(id);

        model.addAttribute("total",total);

        return "checkout";
    }

    @GetMapping("/search")
    public String searchFlight(Model model){

//        model.addAttribute("flights", flightService.getAllFlights());
        return "search";

    }
    @PostMapping("/search")
    public String searchFlight(@RequestParam(value="origin") String origin,
                               @RequestParam(value="destination") String destination,
                               Model model){

        if  (origin.equals("") || destination.equals("")){
            return "search";
        }

        List<Flight> origins = this.flightService.getAllFlightsWithOrigin(origin,destination);

        model.addAttribute("origins", origins);

        if (origins.isEmpty()) {
            model.addAttribute("notFlightAvailable", "No flight found");
        } else {
            model.addAttribute("origins", origins);
        }

//        model.addAttribute("flights", flightService.getAllFlights());

        return "search";

    }

}






