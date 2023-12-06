package com.example.airline.service.scheduler;


import com.example.airline.service.FlightService;
import com.example.airline.service.Impl.EmailServiceImpl;
import com.example.airline.service.NewsletterService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsletterScheduler {

    private final NewsletterService newsletterService;

    private final FlightService flightService;

    private final EmailServiceImpl emailService;

    public NewsletterScheduler(NewsletterService newsletterService, FlightService flightService, EmailServiceImpl emailService) {
        this.newsletterService = newsletterService;
        this.flightService = flightService;
        this.emailService = emailService;
    }

    @Scheduled(cron="@monthly")
//        @Scheduled(cron = "*/15 * * * * *")
        public void sendNewFlights() {


            List<String> allEmails = newsletterService.getAllEmails();

            if (allEmails.isEmpty()) {
                return;
            }

            allEmails.forEach(this.emailService::sendNewsEmail);

        }

}
