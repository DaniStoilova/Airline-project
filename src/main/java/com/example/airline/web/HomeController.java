package com.example.airline.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model){


        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about-us";
    }

    @GetMapping("/info")
    public String addHotel(){
        return "info-bag";
    }




}
