package com.example.airline.service.Impl;


import com.example.airline.model.entity.Booking;
import com.example.airline.model.entity.Flight;
import com.example.airline.service.BookingService;
import com.example.airline.service.FlightService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.List;


@Service
public class EmailServiceImpl  {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final FlightService flightService;

    private final BookingService bookingService;

    public EmailServiceImpl(TemplateEngine templateEngine,
                            JavaMailSender javaMailSender, FlightService flightService, BookingService bookingService) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    public void sendRegistrationEmail(
            String userEmail,
            String userName
    ) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("flight@example.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Welcome to Airline!");
            mimeMessageHelper.setText(generateMessageContentRegistration(userName), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateMessageContentRegistration(String userName) {
        Context context = new Context();
        context.setVariable("userName", userName);
        return templateEngine.process("email/registration", context);
    }

    public void sendNewsEmail(
            String userEmail
    ) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("flight@example.com");
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setSubject("Find new inspire and book your flight now!");

            List<Flight> flights = this.flightService.getAllFlightsWithLowPrice(BigDecimal.valueOf(50));
            mimeMessageHelper.setText(generateMessageNewFlights(flights), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
        private String generateMessageNewFlights(List<Flight> flights) {
            Context context = new Context();
            context.setVariable("flights", flights);
            return templateEngine.process("email/flights", context);

    }

    public void sendEmailContact(String userEmail, String fullName, String phoneNumber, String message) {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            try {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setFrom(userEmail);
                mimeMessageHelper.setTo("flight@example.com");
                mimeMessageHelper.setSubject("Message");

                StringBuilder sb = new StringBuilder();
                sb.append(message)
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("All the best, " + fullName);

                mimeMessageHelper.setText(sb.toString());

                javaMailSender.send(mimeMessageHelper.getMimeMessage());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    public void sendBooking(String email,String fullName,String passportNumber,String flightNumber) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("flight@example.com");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Confirmation Bookings!");

            List<Booking> bookings = bookingService.getBook(fullName);
            mimeMessageHelper.setText(generateMessageBooking(bookings), true);


            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateMessageBooking(List<Booking> bookings) {
        Context context = new Context();
        context.setVariable("bookings", bookings);
        return templateEngine.process("email/bookings", context);

    }
    public void sendConformation(Long id,String userEmail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(userEmail);
            mimeMessageHelper.setTo("flight@example.com");
            mimeMessageHelper.setSubject("Confirmation Reservation!");

            StringBuilder sb = new StringBuilder();
            sb.append("RESERVATION CONFIRMATION \n" +
                            "\n" +
                            "Dear Mr./Ms., \n" +
                            "\n" +
                            "Thank you for choosing to stay with us." +
                            "\nWe are pleased to confirm your reservation with number: "+ id +".")
                    .append(System.lineSeparator())
                    .append("Please keep your reservation number and your confirmation email.")
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append("For more question please text an email - info@confirm.bg")
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                            .append("All the best");

            mimeMessageHelper.setText(sb.toString());

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
