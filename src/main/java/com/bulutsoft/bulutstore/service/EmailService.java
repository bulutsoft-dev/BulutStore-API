package com.bulutsoft.bulutstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendWelcomeEmail(String to, String username) {
        String subject = "Welcome to BulutStore!";
        String text = "Hello " + username + ",\n\nWelcome to BulutStore! We're glad to have you.";
        sendEmail(to, subject, text);
    }

    public void sendDeveloperApplicationNotification(String adminEmail, String username) {
        String subject = "New Developer Application";
        String text = "User '" + username + "' has applied to become a developer.";
        sendEmail(adminEmail, subject, text);
    }

    public void sendDeveloperApplicationResult(String to, boolean accepted) {
        String subject = "Developer Application Result";
        String text = accepted ? "Congratulations! Your developer application has been accepted." : "Sorry, your developer application has been rejected.";
        sendEmail(to, subject, text);
    }
}

