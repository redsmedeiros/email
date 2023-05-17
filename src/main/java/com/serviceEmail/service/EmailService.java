package com.serviceEmail.service;


import com.serviceEmail.model.Email;

public interface EmailService {

    Email sendEmail(Email email);
    
}
