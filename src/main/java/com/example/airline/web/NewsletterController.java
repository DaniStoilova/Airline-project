package com.example.airline.web;

import com.example.airline.model.dto.NewsletterDTO;
import com.example.airline.service.NewsletterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NewsletterController {

    private final NewsletterService newsletterService;

    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @GetMapping("/addNews")
    public String addEmail(){
        return "news";
    }

    @PostMapping("/addNews")
    public String addNewEmail(@Valid NewsletterDTO newsletterDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("newsletterDTO", newsletterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsletterDTO", bindingResult);

            return "redirect:/";
        }


        if(this.newsletterService.addEmail(newsletterDTO) == null) {
            redirectAttributes.addFlashAttribute("emailExists", true);
            redirectAttributes.addFlashAttribute("newsletterDTO", newsletterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsletterDTO", bindingResult);
            return "redirect:/";
        }

        return "redirect:/";
    }
    @ModelAttribute
    public NewsletterDTO newsletterDTO(){
        return new NewsletterDTO();
    }

}
