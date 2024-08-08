package com.example.findmypg.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.findmypg.building.BuildingRepositry;
import com.example.findmypg.entities.Building;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class SMSService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private BuildingRepositry buildingRepositry;
    
    public boolean sendEmail(SMSDTO smsdto) {
    
        Building building = buildingRepositry.findByPgName(smsdto.getPgName());
        if (building == null) {
            return false;
        }
        
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(smsdto.getFrom());
            helper.setTo(smsdto.getTo());
            helper.setSubject(smsdto.getSubject());
            helper.setText(smsdto.getTextMessage(), true); 

            // Convert byte array to DataSource
            byte[] imageBytes = building.getBuildingImage();
            if (imageBytes != null) {
                DataSource dataSource = new ByteArrayDataSource(imageBytes, "image/jpeg");
                helper.addAttachment("image.jpg", dataSource);
            } else {
                System.out.println("Image data is null.");
            }

            emailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
