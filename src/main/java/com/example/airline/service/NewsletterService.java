package com.example.airline.service;

import com.example.airline.model.dto.NewsletterDTO;
import com.example.airline.model.entity.Newsletter;

import java.util.List;

public interface NewsletterService {
    Newsletter addEmail(NewsletterDTO newsletterDTO);

    List<String> getAllEmails();

}
