package com.serviceEmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceEmail.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
    
}
