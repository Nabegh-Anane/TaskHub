package com.taskhub.taskhub_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@RequestMapping("/api/test-mail")
@RequiredArgsConstructor
public class TestMailController {

    private final JavaMailSender mailSender;

    @GetMapping
    public String sendTestMail(@RequestParam(defaultValue = "bidakil620@forcrack.com") String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom("contact@task-hub.tech");
            message.setSubject("✔ Test Email - Zoho SMTP");
            message.setText("Félicitations ! L'envoi d'e-mail fonctionne via smtp.zoho.com ✅");

            mailSender.send(message);
            return "📬 Test mail envoyé avec succès à : " + to;
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Erreur lors de l'envoi du mail : " + e.getMessage();
        }
    }
}
