package com.serviceEmail.service.impl;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.serviceEmail.enums.StatusEmail;
import com.serviceEmail.model.Email;
import com.serviceEmail.repository.EmailRepository;
import com.serviceEmail.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Email sendEmail(Email emailModel) {
        
        emailModel.setSendDateEmail(LocalDateTime.now());

        try{

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        }catch(MailException e){

            emailModel.setStatusEmail(StatusEmail.ERROR);

        }finally{

            return emailRepository.save(emailModel);
        }

    
    }
    
}
