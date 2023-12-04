package com.example.airline.web;

import com.example.airline.error.customException.CarNotFoundException;
import com.example.airline.model.binding.AddCarBindingModel;
import com.example.airline.model.dto.AllCarsDto;
import com.example.airline.model.dto.CarDto;
import com.example.airline.model.dto.UpdateCarDto;
import com.example.airline.model.entity.RentACar;
import com.example.airline.service.CarService;
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
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;

    }

    @GetMapping("/car")
    public String addCar(){
        return "rent-car";
    }
    @PostMapping("/car")
    public String confirmCar(@Valid AddCarBindingModel addCarBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal User user) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addCarBindingModel", addCarBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addCarBindingModel", bindingResult);

            return "redirect:/car";
        }


        carService.addCarReservation(addCarBindingModel, user);

        return "redirect:/";
    }


    @GetMapping("/updateCar/{id}")
    public String editCar(@PathVariable("id") Long id, Model model) {

        UpdateCarDto updateCarDto = carService.getUpdateCar(id).orElseThrow(() -> new CarNotFoundException(id));

        model.addAttribute("updateCarDto", updateCarDto);

        return "car-edit";
    }
    @PostMapping("/updateCar/{id}")
    public String updateHotel(@PathVariable(value = "id") Long id,
                              @Valid UpdateCarDto updateCarDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        RentACar rentACar = this.carService.findById(id).orElseThrow(() -> new CarNotFoundException(id));

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateCarDto", updateCarDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateCarDto", bindingResult);

            return "redirect:/updateCar/" + id;
        }

        this.carService.updateCar(rentACar, updateCarDto);

        return "redirect:/rentAll";
    }


    @GetMapping("/rentAll")
    public String all(Model model) {

        List<AllCarsDto> allCars = carService.getAllCars();

        model.addAttribute("cars", allCars);

        return "cars";

    }

    @GetMapping("/rent/{id}")
    public String confirmRent(Model model,@PathVariable("id") Long id,@AuthenticationPrincipal User user) {

        CarDto carDto = carService.getCarById(id,user)
                .orElseThrow(() -> new CarNotFoundException(id));

        model.addAttribute("cars",carDto);


        return "car-orders";

    }

    @GetMapping("/bookCar/{id}")
    public String bookCar(@PathVariable("id") Long id, @AuthenticationPrincipal User user){

        carService.bookCar(id,user);

        return "redirect:/";

    }




    @PostMapping("/bookCar/{id}")
    public String bookCar(@PathVariable("id") Long id){

        carService.deleteCar(id);

        return "redirect:/rentAll";
    }

    @PostMapping("/deleteCar/{id}")
    public String delete(@PathVariable("id") Long id){

        carService.deleteCar(id);

        return "redirect:/rentAll";
    }

    @ModelAttribute
    public AddCarBindingModel addCarBindingModel(){
        return new AddCarBindingModel();
    }






}
