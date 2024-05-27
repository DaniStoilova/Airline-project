package com.example.airline.web;

import com.example.airline.model.enums.RoleEnum;
import com.example.airline.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {

    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/role")
    public String add(Model model){

        model.addAttribute("names",userService.findAllUser());
        return  "add-role";
    }

    @PostMapping("/role")
    public String changeRole(@RequestParam String email,
                             @RequestParam String role){

        userService.changeRole(email, RoleEnum.valueOf(role.toUpperCase()));

        return  "redirect:/";
    }


}
