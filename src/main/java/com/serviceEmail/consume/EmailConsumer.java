package com.serviceEmail.consume;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.serviceEmail.dto.EmailDto;
import com.serviceEmail.model.Email;
import com.serviceEmail.service.EmailService;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto){

        Email emailModel = new Email();

        BeanUtils.copyProperties(emailDto, emailModel);

        emailService.sendEmail(emailModel);

        System.out.println("Email status" + emailModel.getStatusEmail().toString());
    }    
    
}
