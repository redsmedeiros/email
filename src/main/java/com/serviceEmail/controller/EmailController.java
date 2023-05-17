package com.serviceEmail.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceEmail.dto.EmailDto;
import com.serviceEmail.model.Email;
import com.serviceEmail.service.EmailService;


import jakarta.validation.Valid;

@RestController
@RequestMapping
public class EmailController {
    
    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDto emailDto){

        Email emailModel = new Email();

        BeanUtils.copyProperties(emailDto, emailModel);

        Email response = emailService.sendEmail(emailModel);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
