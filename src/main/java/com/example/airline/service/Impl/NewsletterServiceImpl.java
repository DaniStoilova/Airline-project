package com.example.airline.service.Impl;

import com.example.airline.model.dto.NewsletterDTO;
import com.example.airline.model.entity.Newsletter;
import com.example.airline.repository.NewsletterRepository;
import com.example.airline.service.NewsletterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    private NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public Newsletter addEmail(NewsletterDTO newsletterDTO) {

        if (this.newsletterRepository.findByEmail(newsletterDTO.getEmail()).isPresent()) {
            return null;
        }

        return this.newsletterRepository.save(new Newsletter()
                .setEmail(newsletterDTO.getEmail()));
    }

    @Override
    public List<String> getAllEmails() {
        return this.newsletterRepository.findAll()
                .stream().map(Newsletter::getEmail)
                .toList();
    }


}
