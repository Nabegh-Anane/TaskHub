package com.taskhub.taskhub_backend.services;

import com.taskhub.taskhub_backend.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendRegistrationEmail(User user, String rawPassword) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setFrom("contact@task-hub.tech");
            message.setSubject("Bienvenue sur TaskHub !");

            String mailBody = String.format("""
                Bonjour %s,

                Merci pour votre inscription sur TaskHub.
                Votre compte a été créé avec succès.

                📛 Nom complet : %s
                🧑 Rôle : %s
                📧 Identifiant (email) : %s
                🔑 Mot de passe : %s

                Vous pouvez maintenant vous connecter à TaskHub.

                Cordialement,
                L'équipe TaskHub
                """,
                    user.getFullName(),
                    user.getFullName(),
                    user.getRole(),
                    user.getEmail(),
                    rawPassword
            );

            message.setText(mailBody);
            mailSender.send(message);

        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'envoi de l'email d'inscription : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
