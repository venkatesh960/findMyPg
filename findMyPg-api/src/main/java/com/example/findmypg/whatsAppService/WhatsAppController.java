package com.example.findmypg.whatsAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/findmypg/student")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    @GetMapping("/send-message")
    public String sendMessage(@RequestParam String phoneNumber, @RequestParam String message) {
        try {
            whatsAppService.sendMessage(phoneNumber, message);
            return "Message sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send message.";
        }
    }
}
