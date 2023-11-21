package com.example.airline.service;

import com.example.airline.model.dto.NewsletterDTO;
import com.example.airline.model.entity.Newsletter;
import com.example.airline.repository.NewsletterRepository;
import com.example.airline.service.Impl.NewsletterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsletterServiceTest {

    @Mock
    private NewsletterRepository newsletterRepository;

    private NewsletterService newsletterService;

    @BeforeEach
    void setUp() {
        this.newsletterService = new NewsletterServiceImpl(newsletterRepository);
    }

    @Test
    void testAddNewsletter() {

        NewsletterDTO newsletterDTO = new NewsletterDTO("lora@new.com");
        Newsletter expect = new Newsletter().setEmail("lora@new.com");

        when(newsletterRepository.findByEmail("lora@new.com")).thenReturn(Optional.empty());

        when(newsletterRepository.save(Mockito.any(Newsletter.class))).thenReturn(expect);
        Newsletter newsletter = this.newsletterService.addEmail(newsletterDTO);


        Assertions.assertEquals(expect.getEmail(), newsletter.getEmail());
    }

    @Test
    void testAddNewsletterEmailThrows() {

        NewsletterDTO newsletterDTO = new NewsletterDTO("lora@new.com");
        Newsletter expect = new Newsletter().setEmail("lora@new.com");

        when(newsletterRepository.findByEmail("lora@new.com")).thenReturn(Optional.empty());
        Newsletter newsletter = newsletterService.addEmail(newsletterDTO);


        Assertions.assertNull(newsletter);
    }

}
