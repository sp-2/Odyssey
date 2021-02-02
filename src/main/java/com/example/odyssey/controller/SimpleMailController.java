package com.example.odyssey.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleMailController {
    @Autowired
    private JavaMailSender sender;
    
    @RequestMapping(value="/pianos/sendMailMessage/{pid}", method=RequestMethod.POST)
    public String sendMailMessage(@PathVariable("pid") Long pid, @RequestParam("mail_from") String mail_from, @RequestParam("mail_subject") String mail_subject,@RequestParam("mail_text") String mail_text) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("soumyap207@gmail.com");
            helper.setFrom("soumyap207@gmail.com");
            //helper.setTo("support@admin.odyssey.com");
            //helper.setFrom(mail_from);
            helper.setText(mail_text,true);
            helper.setSubject(mail_subject);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "redirect:/pianos/show/{pid}";
    }
}
