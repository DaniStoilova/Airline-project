package com.example.airline.web;

import com.example.airline.model.dto.ContactUsDto;
import com.example.airline.service.ContactService;
import com.example.airline.service.Impl.EmailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final ContactService contactService;

    private final EmailServiceImpl emailService;


    public ContactController(ContactService contactService, EmailServiceImpl emailService) {
        this.contactService = contactService;
        this.emailService = emailService;
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact-us";
    }
    @PostMapping("/contact")
    public String addContactUs(@Valid ContactUsDto contactUsDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("contactUsDto", contactUsDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactUsDto", bindingResult);

            return "contact-us";
        }


        contactService.addContact(contactUsDto);

        this.emailService.sendEmailContact(contactUsDto.getEmail(), contactUsDto.getFullName(),
                contactUsDto.getPhoneNumber(), contactUsDto.getMessage());


        return "redirect:/";
    }


    @ModelAttribute
    public ContactUsDto contactUsDto() {
        return new ContactUsDto();
    }

}
