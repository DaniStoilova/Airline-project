package com.example.airline.web;

import com.example.airline.error.customException.CarNotFoundException;
import com.example.airline.model.binding.UserRegisterBindingModel;
import com.example.airline.model.dto.UpdateCarDto;
import com.example.airline.model.dto.UpdateProfileDto;
import com.example.airline.model.dto.UserProfileDto;
import com.example.airline.model.entity.RentACar;
import com.example.airline.model.entity.UserEntity;
import com.example.airline.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;



    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);

            return "redirect:register";
        }
        userService.register(userRegisterBindingModel);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @PostMapping("/login-error")
    public String onFailure(@ModelAttribute("username") String email, Model model) {

        model.addAttribute("username", email);
        model.addAttribute("bad_credentials", "true");

        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User user){

       UserProfileDto userProfileDto = this.userService.getLoggedUserDTO(user.getUsername());

        model.addAttribute("user",userProfileDto);

        return "profile";
    }
    @GetMapping("/updateProfile/{id}")
    public String editProfile(@PathVariable("id") Long id, Model model) {

        UpdateProfileDto updateProfile = userService.getUpdateProfile(id).orElseThrow();

        model.addAttribute("updateProfile", updateProfile);

        return "edit-profile";
    }
    @PostMapping("/updateProfile/{id}")
    public String updateProfile(@PathVariable(value = "id") Long id,
                                @Valid UpdateProfileDto updateProfile,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        UserEntity user = userService.findById(id).orElseThrow();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateProfile", updateProfile);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateProfile", bindingResult);

            return "redirect:/updateProfile/" + id;
        }

            userService.updateProfile(user, updateProfile);

        return "redirect:/users/profile";
    }
    @PostMapping("/delete-profile")
    public String deleteProfile(@AuthenticationPrincipal User user){
         userService.deleteById(user.getUsername());

         return "redirect:/users/register";
    }



    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }



}
