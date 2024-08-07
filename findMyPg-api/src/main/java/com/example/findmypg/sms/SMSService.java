package com.example.findmypg.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class SMSService {

    @Autowired
    private JavaMailSender emailSender;

    public boolean sendEmail(String to, String subject, String text, String imagePath) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("your-email@example.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // Set text to HTML

            // Add the image as an attachment
            File imageFile = new File(imagePath);
            helper.addAttachment("image.jpg", imageFile);

            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
